package io.perfeccionista.framework.extension;

import io.perfeccionista.framework.DefaultEnvironmentConfiguration;
import io.perfeccionista.framework.exceptions.base.PerfeccionistaException;
import io.perfeccionista.framework.value.ValueService;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.extension.AfterEachCallback;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.api.extension.ParameterResolver;
import org.junit.jupiter.api.extension.TestExecutionExceptionHandler;
import org.junit.jupiter.api.extension.TestInstancePostProcessor;
import org.junit.jupiter.api.extension.TestTemplateInvocationContext;
import org.junit.jupiter.api.extension.TestTemplateInvocationContextProvider;
import org.junit.jupiter.api.extension.TestWatcher;
import org.junit.platform.commons.logging.Logger;
import org.junit.platform.commons.logging.LoggerFactory;
import org.junit.platform.commons.util.Preconditions;
import org.junit.platform.engine.TestExecutionResult;
import io.perfeccionista.framework.Environment;
import io.perfeccionista.framework.EnvironmentConfiguration;
import io.perfeccionista.framework.UseEnvironmentConfiguration;
import io.perfeccionista.framework.exceptions.EnvironmentNotConfigured;
import io.perfeccionista.framework.exceptions.RepeatPolicyInitialization;
import io.perfeccionista.framework.exceptions.TestClassNotFound;
import io.perfeccionista.framework.exceptions.TestMethodNotFound;
import io.perfeccionista.framework.repeater.NoRepeatPolicy;
import io.perfeccionista.framework.repeater.RepeatPolicy;
import io.perfeccionista.framework.repeater.TestRepeatedOnCondition;
import io.perfeccionista.framework.repeater.iterators.NoRepeatTestTemplateIterator;
import io.perfeccionista.framework.repeater.iterators.RepeatIfTestTemplateIterator;
import io.perfeccionista.framework.repeater.iterators.RepeatWhileTestTemplateIterator;
import io.perfeccionista.framework.service.Service;
import io.perfeccionista.framework.utils.ReflectionUtils;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Optional;
import java.util.Spliterator;
import java.util.stream.Stream;

import static java.util.Spliterators.spliteratorUnknownSize;
import static java.util.stream.StreamSupport.stream;
import static org.junit.platform.commons.util.AnnotationUtils.findAnnotation;
import static org.junit.platform.commons.util.AnnotationUtils.isAnnotated;
import static org.junit.platform.commons.util.ReflectionUtils.newInstance;
import static io.perfeccionista.framework.exceptions.messages.EnvironmentMessages.CREATE_REPEAT_POLICY_INSTANCE_EXCEPTION;
import static io.perfeccionista.framework.exceptions.messages.EnvironmentMessages.ENVIRONMENT_NOT_DECLARED;
import static io.perfeccionista.framework.exceptions.messages.EnvironmentMessages.UNEXPECTED_TEST_CLASS_NOT_FOUND;
import static io.perfeccionista.framework.exceptions.messages.EnvironmentMessages.UNEXPECTED_TEST_METHOD_NOT_FOUND;

// TODO: Сделать возможным использование аннотаций @UseService() над тестовым классом или тестом
// TODO: Сделать возможным пробрасывать любой зарегистрированный в Enviroment сервис отдельным аргументом
public class PerfeccionistaExtension implements ParameterResolver, TestInstancePostProcessor, BeforeEachCallback, AfterEachCallback,
        TestTemplateInvocationContextProvider, TestExecutionExceptionHandler, TestWatcher {
    private static final Logger log = LoggerFactory.getLogger(PerfeccionistaExtension.class);

    protected ThreadLocal<Environment> activeEnvironment = new ThreadLocal<>();
    protected ThreadLocal<Map<Method, Deque<TestExecutionResult>>> threadLocalTestResults = new ThreadLocal<>();

    PerfeccionistaExtension() {}

    // Lifecycle methods

    @Override
    public void postProcessTestInstance(Object testInstance, ExtensionContext context) {
        // do nothing
    }

    @Override
    public void beforeEach(ExtensionContext context) {
        Class<?> testClass = context.getTestClass()
                .orElseThrow(() -> TestClassNotFound.exception(UNEXPECTED_TEST_CLASS_NOT_FOUND.getMessage()));
        Method testMethod = context.getTestMethod()
                .orElseThrow(() -> TestMethodNotFound.exception(UNEXPECTED_TEST_METHOD_NOT_FOUND.getMessage()));
        // Ищем конфигурацию для Environment для тестового метода
        Optional<Class<? extends EnvironmentConfiguration>> testMethodConfiguration = findEnvironmentConfiguration(testMethod);
        if (testMethodConfiguration.isPresent()) {
            Class<? extends EnvironmentConfiguration> configurationForTestMethod = testMethodConfiguration.get();
            // Находим или создаем и устанавливаем Environment для конфигурации установленной над тестовым методом
            resolveActiveEnvironmentForTestMethod(configurationForTestMethod, testClass);
        } else {
            // Смотрим была ли для класса
            Optional<Class<? extends EnvironmentConfiguration>> testClassConfiguration = findEnvironmentConfiguration(testClass);
            // Находим или создаем и устанавливаем Environment для конфигурации установленной над тестовым классом
            testClassConfiguration
                    .ifPresentOrElse(configurationForTestClass -> resolveActiveEnvironmentForTestMethod(configurationForTestClass, testClass),
                            () -> resolveActiveEnvironmentForTestMethod(DefaultEnvironmentConfiguration.class, testClass));
        }
    }

    @Override
    public void afterEach(ExtensionContext context) {
        Optional<Environment> environmentInstanceForCurrentThread = getActiveEnvironment();
        environmentInstanceForCurrentThread.ifPresent(environment -> {
            environment.getServices().forEach(Service::afterTest);
            environment.removeEnvironmentForCurrentThread();
        });
        activeEnvironment.remove();
    }

    // Методы для передачи в тестовый метод экземпляра Environment

    @Override
    public boolean supportsParameter(ParameterContext parameterContext, ExtensionContext extensionContext) {
        return Environment.class.isAssignableFrom(parameterContext.getParameter().getType())
                || ValueService.class.isAssignableFrom(parameterContext.getParameter().getType());
    }

    /**
     * @return Экземпляр {@link Environment} для текущего потока. Никогда не может быть {@code null}
     */
    @Override
    public Object resolveParameter(ParameterContext parameterContext, ExtensionContext extensionContext) {
        Optional<Environment> environmentInstanceForCurrentThread = getActiveEnvironment();
        Environment environment = environmentInstanceForCurrentThread
                .orElseThrow(() -> EnvironmentNotConfigured.exception(ENVIRONMENT_NOT_DECLARED.getErrorMessage()));
        if (ValueService.class.isAssignableFrom(parameterContext.getParameter().getType())) {
            return environment.getService(ValueService.class);
        }
        return environment;
    }

    // Repeater methods

    /**
     * Check that test method contain {@link TestRepeatedOnCondition} annotation
     * @param extensionContext - encapsulates the context in which the current test or container is being executed
     * @return true/false
     */
    @Override
    public boolean supportsTestTemplate(ExtensionContext extensionContext) {
        return isAnnotated(extensionContext.getTestMethod(), TestRepeatedOnCondition.class);
    }

    /**
     * Context call TestTemplateInvocationContext
     * @param context - Test Class Context
     * @return Stream of TestTemplateInvocationContext
     * TODO: Test this
     */
    @Override
    public Stream<TestTemplateInvocationContext> provideTestTemplateInvocationContexts(ExtensionContext context) {
        Method testMethod = context.getTestMethod()
                .orElseThrow(() -> TestMethodNotFound.exception(UNEXPECTED_TEST_METHOD_NOT_FOUND.getErrorMessage()));

        RepeatPolicy repeatPolicy = new NoRepeatPolicy();

        Optional<Environment> optionalEnvironment = getActiveEnvironment();
        if (optionalEnvironment.isPresent()) {
            repeatPolicy = optionalEnvironment.get().getEnvironmentConfiguration().getRepeatPolicy();
        }

        Optional<TestRepeatedOnCondition> optionalAnnotation = context.getTestMethod()
                .flatMap(testMethods -> findAnnotation(testMethods, TestRepeatedOnCondition.class));
        if (optionalAnnotation.isPresent()) {
            Class<? extends RepeatPolicy> repeatPolicyClass = optionalAnnotation.get().value();
            if (!RepeatPolicy.class.equals(repeatPolicyClass)) {
                if (org.junit.platform.commons.util.ReflectionUtils.isAbstract(repeatPolicyClass) || repeatPolicyClass.isInterface()) {
                    throw RepeatPolicyInitialization.exception(CREATE_REPEAT_POLICY_INSTANCE_EXCEPTION.getMessage(repeatPolicyClass));
                }
                repeatPolicy = newInstance(repeatPolicyClass);
            }
        }

        Preconditions.condition(repeatPolicy.minAttempt() > 0, "Total repeats must be higher than 0");
        Preconditions.condition(repeatPolicy.maxAttempt() > 0, "Total minimum success must be higher than 0");

        Iterator<TestTemplateInvocationContext> iterator;

        switch (repeatPolicy.getRepeatMode()) {
            case REPEAT_IF: {
                iterator = new RepeatIfTestTemplateIterator(this, repeatPolicy, context.getDisplayName(), testMethod);
                break;
            }
            case REPEAT_BEFORE: {
                iterator = new RepeatWhileTestTemplateIterator(this, repeatPolicy, context.getDisplayName(), testMethod);
                break;
            }
            default: {
                iterator = new NoRepeatTestTemplateIterator(context.getDisplayName());
            }
        }

        return stream(spliteratorUnknownSize(iterator, Spliterator.NONNULL), false);
    }

    // Test result methods

    @Override
    public void testDisabled(ExtensionContext context, Optional<String> reason) {
        // do nothing, no need calculate condition for disabled test
    }

    @Override
    public void testSuccessful(ExtensionContext context) {
        Method testMethod = context.getTestMethod()
                .orElseThrow(() -> TestMethodNotFound.exception(UNEXPECTED_TEST_METHOD_NOT_FOUND.getErrorMessage()));
        getThreadLocalTestResults(testMethod).addLast(TestExecutionResult.successful());
    }

    @Override
    public void testAborted(ExtensionContext context, Throwable cause) {
        Method testMethod = context.getTestMethod()
                .orElseThrow(() -> TestMethodNotFound.exception(UNEXPECTED_TEST_METHOD_NOT_FOUND.getErrorMessage()));
        getThreadLocalTestResults(testMethod).addLast(TestExecutionResult.aborted(cause));
    }

    @Override
    public void testFailed(ExtensionContext context, Throwable cause) {
        Method testMethod = context.getTestMethod()
                .orElseThrow(() -> TestMethodNotFound.exception(UNEXPECTED_TEST_METHOD_NOT_FOUND.getErrorMessage()));
        getThreadLocalTestResults(testMethod).addLast(TestExecutionResult.failed(cause));
    }

    @Override
    public void handleTestExecutionException(ExtensionContext context, Throwable throwable) throws Throwable {
        if (throwable instanceof PerfeccionistaException) {
            log.error(((PerfeccionistaException) throwable)::getAttachmentDescription);
        }
        throw throwable;
    }

    // Методы необходимые для конфигурирования Environment

    /**
     * Метод возвращает конфигурацию, указанную над тестовым классом или над его предком,
     * если она там есть
     * @param testClass тестовый класс
     */
    @SuppressWarnings("WeakerAccess")
    protected Optional<Class<? extends EnvironmentConfiguration>> findEnvironmentConfiguration(Class<?> testClass) {
        Class<?> processedClass = testClass;
        while (!Object.class.equals(processedClass)) {
            Optional<UseEnvironmentConfiguration> optionalAnnotation = findAnnotation(processedClass, UseEnvironmentConfiguration.class);
            if (optionalAnnotation.isPresent()) {
                return Optional.of(optionalAnnotation.get().value());
            }
            processedClass = processedClass.getSuperclass();
        }
        return Optional.empty();
    }

    /**
     * Метод возвращает конфигурацию, указанную над тестовым методом, если она там есть
     * @param testMethod тестовый метод
     */
    @SuppressWarnings("WeakerAccess")
    protected Optional<Class<? extends EnvironmentConfiguration>> findEnvironmentConfiguration(Method testMethod) {
        return findAnnotation(testMethod, UseEnvironmentConfiguration.class).map(UseEnvironmentConfiguration::value);
    }

    /**
     * Создаем экземпляр {@link Environment} используя полученную конфигурацию и тестовый класс
     * @param environmentConfiguration экземпляр конфигурации {@link Environment}
     * @param testClass тестовый класс для которого создается {@link Environment}
     * @param <T> тип {@link Environment}
     * @return экземпляр {@link Environment}
     */
    @SuppressWarnings("WeakerAccess")
    protected <T extends Environment> T createEnvironment(@NotNull Class<?> testClass, @NotNull EnvironmentConfiguration environmentConfiguration) {
        Constructor<? extends Environment> constructor = ReflectionUtils
                .getConstructor(environmentConfiguration.getEnvironmentClass(), Class.class, EnvironmentConfiguration.class);
        // noinspection unchecked
        return (T) newInstance(constructor, testClass, environmentConfiguration);
    }

    /**
     * Устанавливает для теста сконфигурированный для него экземпляр Environment
     * Если требуемый экземпляр Environment был создан ранее и сконфигурирован как shared(),
     * то он устанавливается в качестве активного, иначе создается новый экземпляр.
     * Выполняем beforeEach() для заданного теста
     */
    protected void resolveActiveEnvironmentForTestMethod(Class<? extends EnvironmentConfiguration> configurationClass, Class<?> testClass) {
        EnvironmentConfiguration environmentConfiguration = newInstance(configurationClass);
        // Создаем новый экземпляр Environment для теста
        Environment environmentInstance = createEnvironment(testClass, environmentConfiguration)
                .setEnvironmentForCurrentThread();
        activeEnvironment.set(environmentInstance);
        environmentInstance.getServices().forEach(Service::beforeTest);
    }

    /**
     * ActiveEnvironment может быть null в тех случаях, когда для тестового класса и для его предков не установлена конфигурация для Environment
     */
    protected Optional<Environment> getActiveEnvironment() {
        return Optional.ofNullable(activeEnvironment.get());
    }

    /**
     * TODO: Test and JavaDoc
     */
    public @NotNull Deque<TestExecutionResult> getThreadLocalTestResults(Method method) {
        Map<Method, Deque<TestExecutionResult>> threadLocalTestResultMap = threadLocalTestResults.get();
        if (null == threadLocalTestResultMap) {
            Map<Method, Deque<TestExecutionResult>> newThreadLocalTestResultMap = new HashMap<>();
            Deque<TestExecutionResult> newTestResults = new ArrayDeque<>();
            newThreadLocalTestResultMap.put(method, newTestResults);
            threadLocalTestResults.set(newThreadLocalTestResultMap);
            return newTestResults;
        }
        Deque<TestExecutionResult> testResults = threadLocalTestResultMap.get(method);
        if (null == testResults) {
            Deque<TestExecutionResult> newTestResults = new ArrayDeque<>();
            threadLocalTestResultMap.put(method, newTestResults);
            return newTestResults;
        }
        return new ArrayDeque<>(testResults);
    }

}