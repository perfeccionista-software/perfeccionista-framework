package io.perfeccionista.framework;

import io.perfeccionista.framework.service.ConfiguredServiceHolder;
import io.perfeccionista.framework.service.DefaultServiceConfiguration;
import io.perfeccionista.framework.service.Service;
import io.perfeccionista.framework.service.ServiceConfiguration;
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
import static io.perfeccionista.framework.utils.ReflectionUtils.findAllClasses;
import static io.perfeccionista.framework.utils.ReflectionUtils.loadClass;

public class DefaultEnvironmentConfiguration implements EnvironmentConfiguration {

    public static final String DEFAULT_PACKAGE_TO_SCAN = "io.perfeccionista.framework";
    public static final String SCAN_PACKAGE_PROPERTY = "perfeccionista.scanPackages";
    public static final String SERVICE_PROPERTY_PREFIX = "perfeccionista.service.";

    protected Properties perfeccionistaProperties;
    protected Properties systemProperties;

    @Override
    public DefaultEnvironmentConfiguration init(@Nullable Properties systemProperties, @Nullable Properties perfeccionistaProperties) {
        this.systemProperties = systemProperties;
        this.perfeccionistaProperties = perfeccionistaProperties;
        return this;
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
