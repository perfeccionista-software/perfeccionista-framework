package io.perfeccionista.framework.utils;

import io.perfeccionista.framework.DefaultEnvironmentConfiguration;
import io.perfeccionista.framework.EnvironmentConfiguration;
import io.perfeccionista.framework.SetEnvironmentConfiguration;
import io.perfeccionista.framework.service.ConfiguredServiceHolder;
import io.perfeccionista.framework.service.Service;
import io.perfeccionista.framework.service.SetServiceConfiguration;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Method;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Optional;
import java.util.Properties;
import java.util.Set;

import static io.perfeccionista.framework.Environment.PERFECCIONISTA_PROPERTIES_FILE;
import static io.perfeccionista.framework.utils.AnnotationUtils.findAnnotation;
import static io.perfeccionista.framework.utils.AnnotationUtils.findRepeatableAnnotations;
import static io.perfeccionista.framework.utils.ReflectionUtilsForClasses.loadClass;
import static io.perfeccionista.framework.utils.ReflectionUtilsForClasses.newInstance;

// TODO: На этот класс нужны тесты
public class EnvironmentConfigurationResolver {

    private EnvironmentConfigurationResolver() {
    }

    private static final String ENVIRONMENT_CONFIGURATION_PROPERTY_NAME = "perfeccionista.environment";

    public static @NotNull EnvironmentConfiguration resolveEnvironmentConfiguration(Method testMethod, Class<?> testClass) {
        Optional<Class<? extends EnvironmentConfiguration>> environmentConfiguration = findEnvironmentConfiguration(testMethod, testClass);
        if (environmentConfiguration.isPresent()) {
            return newInstance(environmentConfiguration.get());
        } else {
            return resolveEnvironmentConfiguration();
        }
    }

    public static @NotNull EnvironmentConfiguration resolveEnvironmentConfiguration(Class<?> testClass) {
        Optional<Class<? extends EnvironmentConfiguration>> environmentConfiguration = findEnvironmentConfiguration(testClass);
        if (environmentConfiguration.isPresent()) {
            return newInstance(environmentConfiguration.get());
        } else {
            return resolveEnvironmentConfiguration();
        }
    }

    public static @NotNull EnvironmentConfiguration resolveEnvironmentConfiguration() {
        Properties perfeccionistaProperties = FileUtils.readOptionalPropertyFileFromClasspath(PERFECCIONISTA_PROPERTIES_FILE)
                .orElse(new Properties());
        Properties systemProperties = System.getProperties();
        Class<? extends EnvironmentConfiguration> environmentConfigurationClass;
        if (systemProperties.containsKey(ENVIRONMENT_CONFIGURATION_PROPERTY_NAME)) {
            String environmentConfigurationClassProperty = systemProperties.getProperty(ENVIRONMENT_CONFIGURATION_PROPERTY_NAME);
            environmentConfigurationClass = loadClass(environmentConfigurationClassProperty, EnvironmentConfiguration.class);
        } else if (perfeccionistaProperties.containsKey(ENVIRONMENT_CONFIGURATION_PROPERTY_NAME)) {
            String environmentConfigurationClassProperty = perfeccionistaProperties.getProperty(ENVIRONMENT_CONFIGURATION_PROPERTY_NAME);
            environmentConfigurationClass = loadClass(environmentConfigurationClassProperty, EnvironmentConfiguration.class);
        } else {
            environmentConfigurationClass = DefaultEnvironmentConfiguration.class;
        }
        return newInstance(environmentConfigurationClass);
    }

    // TODO: Добавить поиск аннотаций для переопределения сервисов и возвращать инстанс конфигурации
    public static Optional<Class<? extends EnvironmentConfiguration>> findEnvironmentConfiguration(Class<?> testClass) {
        Class<?> processedClass = testClass;
        while (!Object.class.equals(processedClass)) {
            Optional<SetEnvironmentConfiguration> optionalAnnotation = findAnnotation(processedClass, SetEnvironmentConfiguration.class);
            if (optionalAnnotation.isPresent()) {
                return Optional.of(optionalAnnotation.get().value());
            }
            processedClass = processedClass.getSuperclass();
        }
        return Optional.empty();
    }

    // TODO: Добавить поиск аннотаций для переопределения сервисов и возвращать инстанс конфигурации
    public static Optional<Class<? extends EnvironmentConfiguration>> findEnvironmentConfiguration(Method testMethod, Class<?> testClass) {
        Optional<Class<? extends EnvironmentConfiguration>> testMethodEnvironmentConfiguration =
                findAnnotation(testMethod, SetEnvironmentConfiguration.class)
                        .map(SetEnvironmentConfiguration::value);
        if (testMethodEnvironmentConfiguration.isPresent()) {
            return testMethodEnvironmentConfiguration;
        }
        return findEnvironmentConfiguration(testClass);
    }

    // TODO: Нужны тесты
    public static Set<ConfiguredServiceHolder> resolveExternalServiceConfigurations(Method testMethod, Class<?> testClass) {
        Deque<ConfiguredServiceHolder> serviceConfigurations = new ArrayDeque<>();

        findRepeatableAnnotations(testMethod, SetServiceConfiguration.class)
                .forEach(configuration -> serviceConfigurations.addLast(createConfiguredServiceHolder(configuration)));

        Class<?> processedClass = testClass;
        while (!Object.class.equals(processedClass)) {
            findRepeatableAnnotations(processedClass, SetServiceConfiguration.class)
                    .forEach(configuration -> serviceConfigurations.addLast(createConfiguredServiceHolder(configuration)));
            processedClass = processedClass.getSuperclass();
        }

        Map<Class<? extends Service>, ConfiguredServiceHolder> result = new HashMap<>();

        while (!serviceConfigurations.isEmpty()) {
            ConfiguredServiceHolder serviceHolder = serviceConfigurations.removeLast();
            result.put(serviceHolder.getServiceClass(), serviceHolder);
        }

        return new HashSet<>(result.values());
    }

    private static ConfiguredServiceHolder createConfiguredServiceHolder(SetServiceConfiguration configuration) {
        if (configuration.disabled()) {
            return ConfiguredServiceHolder.disabled(configuration.serviceClass());
        } else {
            return ConfiguredServiceHolder.of(configuration.serviceClass(), configuration.serviceConfigurationClass())
                    .setOrder(configuration.order());
        }
    }

}
