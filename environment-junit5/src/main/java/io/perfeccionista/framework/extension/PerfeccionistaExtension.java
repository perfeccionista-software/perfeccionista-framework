package io.perfeccionista.framework.extension;

import io.perfeccionista.framework.exceptions.attachments.SetAttachmentProcessor;
import io.perfeccionista.framework.exceptions.attachments.processor.AttachmentProcessor;
import io.perfeccionista.framework.exceptions.base.PerfeccionistaException;
import io.perfeccionista.framework.invocation.InvocationService;
import io.perfeccionista.framework.repeater.RepeatPolicyService;
import io.perfeccionista.framework.service.ConfiguredServiceHolder;
import io.perfeccionista.framework.service.Service;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.extension.AfterEachCallback;
import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.api.extension.ParameterResolver;
import org.junit.jupiter.api.extension.TestExecutionExceptionHandler;
import org.junit.jupiter.api.extension.TestInstancePostProcessor;
import org.junit.jupiter.api.extension.TestTemplateInvocationContext;
import org.junit.jupiter.api.extension.TestTemplateInvocationContextProvider;
import org.junit.jupiter.api.extension.TestWatcher;
import org.junit.platform.commons.util.Preconditions;
import org.junit.platform.engine.TestExecutionResult;
import io.perfeccionista.framework.Environment;
import io.perfeccionista.framework.EnvironmentConfiguration;
import io.perfeccionista.framework.exceptions.EnvironmentNotConfigured;
import io.perfeccionista.framework.exceptions.RepeatPolicyInitialization;
import io.perfeccionista.framework.exceptions.TestClassNotFound;
import io.perfeccionista.framework.exceptions.TestMethodNotFound;
import io.perfeccionista.framework.repeater.policy.NoRepeatPolicy;
import io.perfeccionista.framework.repeater.policy.RepeatPolicy;
import io.perfeccionista.framework.repeater.TestRepeatedOnCondition;
import io.perfeccionista.framework.repeater.iterators.NoRepeatTestTemplateIterator;
import io.perfeccionista.framework.repeater.iterators.RepeatIfTestTemplateIterator;
import io.perfeccionista.framework.repeater.iterators.RepeatWhileTestTemplateIterator;
import io.perfeccionista.framework.utils.ReflectionUtilsForClasses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.Spliterator;
import java.util.stream.Stream;

import static io.perfeccionista.framework.utils.EnvironmentConfigurationResolver.resolveEnvironmentConfiguration;
import static io.perfeccionista.framework.utils.EnvironmentConfigurationResolver.resolveExternalServiceConfigurations;
import static io.perfeccionista.framework.utils.ReflectionUtilsForClasses.newInstance;
import static java.util.Spliterators.spliteratorUnknownSize;
import static java.util.stream.StreamSupport.stream;
import static org.junit.jupiter.api.extension.ExtensionContext.Namespace.GLOBAL;
import static org.junit.platform.commons.util.AnnotationUtils.findAnnotation;
import static org.junit.platform.commons.util.AnnotationUtils.isAnnotated;
import static io.perfeccionista.framework.exceptions.messages.EnvironmentMessages.CREATE_REPEAT_POLICY_INSTANCE_EXCEPTION;
import static io.perfeccionista.framework.exceptions.messages.EnvironmentMessages.ENVIRONMENT_NOT_DECLARED;
import static io.perfeccionista.framework.exceptions.messages.EnvironmentMessages.UNEXPECTED_TEST_CLASS_NOT_FOUND;
import static io.perfeccionista.framework.exceptions.messages.EnvironmentMessages.UNEXPECTED_TEST_METHOD_NOT_FOUND;

public class PerfeccionistaExtension implements ParameterResolver, TestInstancePostProcessor,
        BeforeAllCallback, BeforeEachCallback, AfterEachCallback,
        TestTemplateInvocationContextProvider, TestExecutionExceptionHandler, TestWatcher, ExtensionContext.Store.CloseableResource {
    private static final Logger logger = LoggerFactory.getLogger(PerfeccionistaExtension.class);

    private static boolean started = false;

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
        String testName = testClass.getCanonicalName() + "#" + testMethod.getName();

        EnvironmentConfiguration environmentConfiguration = resolveEnvironmentConfiguration(testMethod, testClass);
        Set<ConfiguredServiceHolder> externalServiceConfigurations = resolveExternalServiceConfigurations(testMethod, testClass);
        resolveActiveEnvironment(environmentConfiguration, externalServiceConfigurations, context, testName);
    }

    @Override
    public void afterEach(ExtensionContext context) {
        Optional<Environment> environmentInstanceForCurrentThread = getActiveEnvironment();
        environmentInstanceForCurrentThread.ifPresent(environment -> {
            // TODO: Возможно, здесь нужен более изящный механизм вывода, который можно настраивать
            context.getExecutionException()
                    .flatMap(throwable -> environment.getEnvironmentAttachment()
                    .getContent())
                    .ifPresent(logger::info);
            environment.shutdown();
            environment.removeForCurrentThread();
        });
        activeEnvironment.remove();
    }

    // Методы для передачи в тестовый метод экземпляра Environment

    @Override
    public boolean supportsParameter(ParameterContext parameterContext, ExtensionContext extensionContext) {
        if (Environment.class.isAssignableFrom(parameterContext.getParameter().getType())) {
            return true;
        }
        Optional<Environment> environmentInstanceForCurrentThread = getActiveEnvironment();
        Environment environment = environmentInstanceForCurrentThread
                .orElseThrow(() -> EnvironmentNotConfigured.exception(ENVIRONMENT_NOT_DECLARED.getErrorMessage()));
        Class<?> parameterType = parameterContext.getParameter().getType();
        return environment.getServiceClasses().stream()
                .anyMatch(serviceClass -> serviceClass.getCanonicalName().equals(parameterType.getCanonicalName()));
    }

    /**
     * @return Экземпляр {@link Environment} для текущего потока. Никогда не может быть {@code null}
     */
    @Override
    public Object resolveParameter(ParameterContext parameterContext, ExtensionContext extensionContext) {
        // TODO: Возможны проблемы, если попробуют передать имплементацию Environment отличную по типу от типа параметра
        Optional<Environment> environmentInstanceForCurrentThread = getActiveEnvironment();
        Environment environment = environmentInstanceForCurrentThread
                .orElseThrow(() -> EnvironmentNotConfigured.exception(ENVIRONMENT_NOT_DECLARED.getErrorMessage()));
        if (Environment.class.isAssignableFrom(parameterContext.getParameter().getType())) {
            return environment;
        }
        Class<?> parameterType = parameterContext.getParameter().getType();
        logger.debug("Test method parameter with type '" + parameterType.getCanonicalName() + "' resolved");
        // Здесь не может быть параметра с отличным от Service типом, так как он проверяется в supportsParameter
        return environment.getService((Class<? extends Service>) parameterType);
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
            Environment environment = optionalEnvironment.get();
            Optional<RepeatPolicyService> optionalRepeatPolicyService = environment.getOptionalService(RepeatPolicyService.class);
            if (optionalRepeatPolicyService.isPresent()) {
                repeatPolicy = optionalRepeatPolicyService.get().getRepeatPolicy();
            }
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
            ((PerfeccionistaException) throwable).getAttachment().ifPresent(attachment -> {
                if (!attachment.isProcessed()) {
                    getActiveEnvironment().ifPresent(environment -> {
                        environment.getOptionalService(InvocationService.class).ifPresent(invocationService -> {
                            invocationService.getAttachmentProcessors()
                                    .forEach(attachmentProcessor -> attachmentProcessor.processAttachment(attachment));
                        });
                    });
                }
                attachment.setProcessed(true);
            });
        }
        throw throwable;
    }

    // Методы необходимые для конфигурирования Environment

    /**
     * Создаем экземпляр {@link Environment} используя полученную конфигурацию и тестовый класс
     * @param environmentConfiguration экземпляр конфигурации {@link Environment}
     * @param <T> тип {@link Environment}
     * @return экземпляр {@link Environment}
     */
    @SuppressWarnings("WeakerAccess")
    protected <T extends Environment> T createEnvironment(@NotNull EnvironmentConfiguration environmentConfiguration,
                                                          @NotNull String testName) {
        Constructor<? extends Environment> constructor = ReflectionUtilsForClasses
                .getConstructor(environmentConfiguration.getEnvironmentClass(), EnvironmentConfiguration.class, String.class);
        // noinspection unchecked
        return (T) newInstance(constructor, environmentConfiguration, testName);
    }

    /**
     * Устанавливает для теста сконфигурированный для него экземпляр Environment
     * Если требуемый экземпляр Environment был создан ранее и сконфигурирован как shared(),
     * то он устанавливается в качестве активного, иначе создается новый экземпляр.
     * Выполняем beforeEach() для заданного теста
     */
    protected void resolveActiveEnvironment(EnvironmentConfiguration environmentConfiguration,
                                            Set<ConfiguredServiceHolder> externalServiceConfigurations,
                                            ExtensionContext context,
                                            String testName) {
        externalServiceConfigurations.forEach(environmentConfiguration::addOrOverrideServiceConfiguration);
        Environment environmentInstance = createEnvironment(environmentConfiguration, testName)
                .addRelatedObject("Context", context)
                .init();
        Environment.setForCurrentThread(environmentInstance);
        activeEnvironment.set(environmentInstance);
    }

    /**
     * ActiveEnvironment может быть null в тех случаях, когда для тестового класса и для его предков не установлена конфигурация для Environment
     */
    protected Optional<Environment> getActiveEnvironment() {
        return Optional.ofNullable(activeEnvironment.get());
    }

    public static Set<AttachmentProcessor> resolveAttachmentProcessors(Method testMethod, Class<?> testClass) {
        Set<Class<? extends AttachmentProcessor>> attachmentProcessors = new HashSet<>();

        findAnnotation(testMethod, SetAttachmentProcessor.class)
                .ifPresent(attachmentProcessorAnnotation ->
                        attachmentProcessors.addAll(Arrays.asList(attachmentProcessorAnnotation.value())));

        Class<?> processedClass = testClass;
        while (!Object.class.equals(processedClass)) {
            findAnnotation(processedClass, SetAttachmentProcessor.class)
                    .ifPresent(attachmentProcessorAnnotation ->
                            attachmentProcessors.addAll(Arrays.asList(attachmentProcessorAnnotation.value())));
            processedClass = processedClass.getSuperclass();
        }

        Set<AttachmentProcessor> result = new HashSet<>();
        attachmentProcessors.forEach(attachmentProcessorClass -> result.add(newInstance(attachmentProcessorClass)));
        return result;
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

    @Override
    public void beforeAll(ExtensionContext context) {
        if (!started) {
            started = true;
            context.getRoot().getStore(GLOBAL).put("PerfeccionistaExtensionInstance", this);
        }
    }

    @Override
    public void close() {
        Environment.executeAfterAllHooks();
    }

}
