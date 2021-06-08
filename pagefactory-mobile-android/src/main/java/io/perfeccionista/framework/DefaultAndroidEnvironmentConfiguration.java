package io.perfeccionista.framework;

import io.perfeccionista.framework.service.ConfiguredServiceHolder;
import io.perfeccionista.framework.service.DefaultServiceConfiguration;
import io.perfeccionista.framework.service.Service;
import io.perfeccionista.framework.service.ServiceConfiguration;
import io.perfeccionista.framework.utils.FileUtils;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.Optional;
import java.util.Properties;
import java.util.Set;
import java.util.stream.Collectors;

import static io.perfeccionista.framework.utils.PackageUtils.validatePackageSet;
import static io.perfeccionista.framework.utils.ReflectionUtilsForClasses.findAllClasses;
import static io.perfeccionista.framework.utils.ReflectionUtilsForClasses.loadClass;

public class DefaultAndroidEnvironmentConfiguration implements EnvironmentConfiguration {

    protected static final String PERFECCIONISTA_PROPERTIES_FILE = "perfeccionista.properties";
    protected static final String SERVICE_PROPERTY_PREFIX = "perfeccionista.service.";

    protected static final String SCAN_PACKAGE_PROPERTY = "perfeccionista.scanPackages";
    protected static final String DEFAULT_PACKAGE_TO_SCAN = "io.perfeccionista.framework";

    protected Properties perfeccionistaProperties;
    protected Properties systemProperties;

    public DefaultAndroidEnvironmentConfiguration() {
        readProperties();
    }

    @Override
    public @NotNull Map<Class<? extends Service>, ConfiguredServiceHolder> getServices() {
        // Сканируем все заданные пакеты (дефолтный и дополнительные) и находим сервисы
        Map<Class<? extends Service>, ConfiguredServiceHolder> services = findAllServices()
                .entrySet().stream()
                .map(entry -> Map.entry(entry.getKey(), ConfiguredServiceHolder.of(entry.getKey(), entry.getValue())))
                .collect(Collectors.toMap(Entry::getKey, Entry::getValue));
        processProperties(perfeccionistaProperties).forEach(services::put);
        processProperties(systemProperties).forEach(services::put);
        return services;
    }

    @Override
    public @NotNull Set<String> getScanPackages() {
        if (systemProperties.containsKey(SCAN_PACKAGE_PROPERTY)) {
            List<String> packages = Arrays.asList(systemProperties.getProperty(SCAN_PACKAGE_PROPERTY).split(","));
            packages.add(DEFAULT_PACKAGE_TO_SCAN);
            return validatePackageSet(new HashSet<>(packages));
        }
        if (perfeccionistaProperties.containsKey(SCAN_PACKAGE_PROPERTY)) {
            List<String> packages = Arrays.asList(perfeccionistaProperties.getProperty(SCAN_PACKAGE_PROPERTY).split(","));
            packages.add(DEFAULT_PACKAGE_TO_SCAN);
            return validatePackageSet(new HashSet<>(packages));
        }

        return Set.of(DEFAULT_PACKAGE_TO_SCAN);
    }

    protected Map<Class<? extends Service>, Class<? extends ServiceConfiguration>> findAllServices() {
        Set<Class<? extends Service>> serviceClasses = findAllClasses(getScanPackages(), Service.class).stream()
                .filter(serviceClass -> !Modifier.isAbstract(serviceClass.getModifiers())
                        && !serviceClass.isInterface()
                        && !serviceClass.isEnum())
                .collect(Collectors.toSet());

        Map<Class<? extends Service>, Class<? extends ServiceConfiguration>> servicesWithDefaultConfiguration = new HashMap<>();
        for (Class<? extends Service> serviceClass : serviceClasses) {
            getDefaultConfiguration(serviceClass).ifPresent(configurationClass ->
                    servicesWithDefaultConfiguration.put(serviceClass, configurationClass));
        }

        return servicesWithDefaultConfiguration;
    }

    protected DefaultAndroidEnvironmentConfiguration readProperties() {
        perfeccionistaProperties = FileUtils.readOptionalPropertyFile(PERFECCIONISTA_PROPERTIES_FILE)
                .orElse(new Properties());
        systemProperties = System.getProperties();
        return this;
    }

    protected Map<Class<? extends Service>, ConfiguredServiceHolder> processProperties(@Nullable Properties properties) {
        if (Objects.isNull(properties)) {
            return Map.of();
        }
        return properties.entrySet().stream()
                .map(entry -> Map.entry(entry.getKey().toString(), entry.getValue().toString()))
                .filter(entry -> entry.getKey().startsWith(SERVICE_PROPERTY_PREFIX))
                .map(entry -> {
                    String key = entry.getKey().replaceAll(SERVICE_PROPERTY_PREFIX, "");
                    Class<? extends Service> serviceClass = loadClass(key, Service.class);
                    String value = entry.getValue();
                    if ("disabled".equalsIgnoreCase(value)) {
                        return Map.entry(serviceClass, ConfiguredServiceHolder.disabled(serviceClass));
                    }
                    Class<? extends ServiceConfiguration> serviceConfigurationClass = loadClass(value, ServiceConfiguration.class);
                    return Map.entry(serviceClass, ConfiguredServiceHolder.of(serviceClass, serviceConfigurationClass));
                }).collect(Collectors.toMap(Entry::getKey, Entry::getValue));
    }

    protected Optional<Class<? extends ServiceConfiguration>> getDefaultConfiguration(Class<? extends Service> serviceClass) {
        DefaultServiceConfiguration defaultConfiguration = serviceClass.getAnnotation(DefaultServiceConfiguration.class);
        if (Objects.nonNull(defaultConfiguration)) {
            return Optional.of(defaultConfiguration.value());
        }
        return Optional.empty();
    }

}
