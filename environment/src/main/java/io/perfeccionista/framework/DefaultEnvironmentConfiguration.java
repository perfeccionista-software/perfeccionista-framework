package io.perfeccionista.framework;

import io.perfeccionista.framework.service.ServiceConfigurationManager;
import io.perfeccionista.framework.service.ConfiguredServiceHolder;
import io.perfeccionista.framework.service.DefaultServiceConfiguration;
import io.perfeccionista.framework.service.DefaultServiceOrder;
import io.perfeccionista.framework.service.Service;
import io.perfeccionista.framework.service.ServiceConfiguration;
import io.perfeccionista.framework.utils.FileUtils;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Properties;
import java.util.Set;
import java.util.stream.Collectors;

import static io.perfeccionista.framework.Environment.PERFECCIONISTA_PROPERTIES_FILE;
import static io.perfeccionista.framework.utils.PackageUtils.validatePackageSet;
import static io.perfeccionista.framework.utils.ReflectionUtilsForClasses.findAllClasses;
import static io.perfeccionista.framework.utils.ReflectionUtilsForClasses.newInstance;

public class DefaultEnvironmentConfiguration implements EnvironmentConfiguration {

    protected static final String SERVICE_SCAN_PACKAGES_PROPERTY = "perfeccionista.services.scanPackages";

    protected Map<Class<? extends Service>, ConfiguredServiceHolder> serviceConfigurationsToAddOrOverride = new HashMap<>();

    protected static volatile boolean cacheReady = false;
    protected static Map<Class<? extends Service>, ConfiguredServiceHolder> cachedServiceConfigurations = new HashMap<>();
    protected static Properties perfeccionistaProperties;
    protected static Properties systemProperties;

    @Override
    public void addOrOverrideServiceConfiguration(@NotNull ConfiguredServiceHolder configuredServiceHolder) {
        this.serviceConfigurationsToAddOrOverride.put(configuredServiceHolder.getServiceClass(), configuredServiceHolder);
    }

    @Override
    public @NotNull ServiceConfigurationManager getServiceConfigurations() {
        Map<Class<? extends Service>, ConfiguredServiceHolder> services = new HashMap<>();
        synchronized (DefaultEnvironmentConfiguration.class) {
            if (!cacheReady) {
                readProperties();
                findAllServices().forEach(serviceConfiguration ->
                        cachedServiceConfigurations.put(serviceConfiguration.getServiceClass(), serviceConfiguration));
                cacheReady = true;
            }
        }
        services.putAll(cachedServiceConfigurations);
        services.putAll(serviceConfigurationsToAddOrOverride);
        return ServiceConfigurationManager.of(services);
    }

    protected void readProperties() {
        perfeccionistaProperties = FileUtils.readOptionalPropertyFileFromClasspath(PERFECCIONISTA_PROPERTIES_FILE)
                .orElse(new Properties());
        systemProperties = System.getProperties();
    }

    protected @NotNull Set<ConfiguredServiceHolder> findAllServices() {
        Set<Class<? extends Service>> serviceClasses = findAllClasses(getServiceScanPackages(), Service.class).stream()
                .filter(serviceClass -> !Modifier.isAbstract(serviceClass.getModifiers())
                        && !serviceClass.isInterface()
                        && !serviceClass.isEnum())
                .collect(Collectors.toSet());
        Map<Class<? extends Service>, ConfiguredServiceHolder> servicesWithDefaultConfiguration = new HashMap<>();
        for (Class<? extends Service> serviceClass : serviceClasses) {
            ConfiguredServiceHolder serviceHolder = resolveDefaultServiceConfiguration(serviceClass);
            servicesWithDefaultConfiguration.put(serviceClass, serviceHolder);
        }
        return new HashSet<>(servicesWithDefaultConfiguration.values());
    }

    protected @NotNull Set<String> getServiceScanPackages() {
        // Переменная окружения имеет самый высокий приоритет
        if (systemProperties.containsKey(SERVICE_SCAN_PACKAGES_PROPERTY)) {
            List<String> packages = Arrays.asList(systemProperties.getProperty(SERVICE_SCAN_PACKAGES_PROPERTY).split(","));
            return validatePackageSet(new HashSet<>(packages));
        }
        // Если переменная окружения не задана, то ищем переменную заданную в свойствах проекта
        if (perfeccionistaProperties.containsKey(SERVICE_SCAN_PACKAGES_PROPERTY)) {
            List<String> packages = Arrays.asList(perfeccionistaProperties.getProperty(SERVICE_SCAN_PACKAGES_PROPERTY).split(","));
            return validatePackageSet(new HashSet<>(packages));
        }
        // Если она не задана, то используется базовый набор пакетов для сканирования
        return new HashSet<>(Arrays.asList("io.perfeccionista.framework.value", "io.perfeccionista.framework.datasource", "io.perfeccionista.framework.fixture"));
    }

    protected ConfiguredServiceHolder resolveDefaultServiceConfiguration(Class<? extends Service> serviceClass) {
        ConfiguredServiceHolder configuredServiceHolder = ConfiguredServiceHolder.of(serviceClass, serviceClass);
        DefaultServiceConfiguration defaultConfiguration = serviceClass.getAnnotation(DefaultServiceConfiguration.class);
        if (Objects.nonNull(defaultConfiguration)) {
            Class<? extends ServiceConfiguration> serviceConfigurationClass = defaultConfiguration.value();
            configuredServiceHolder = ConfiguredServiceHolder.of(serviceClass, serviceClass, newInstance(serviceConfigurationClass));
        }
        DefaultServiceOrder defaultOrder = serviceClass.getAnnotation(DefaultServiceOrder.class);
        if (Objects.nonNull(defaultOrder)) {
            configuredServiceHolder.setOrder(defaultOrder.value());
        }
        return configuredServiceHolder;
    }

}
