package io.perfeccionista.framework.service;

import io.perfeccionista.framework.preconditions.Preconditions;
import org.jetbrains.annotations.NotNull;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ServiceConfigurationManager {

    private final Map<Class<? extends Service>, ConfiguredServiceHolder> serviceConfigurations;

    private ServiceConfigurationManager() {
        this(new LinkedHashMap<>());
    }

    private ServiceConfigurationManager(Map<Class<? extends Service>, ConfiguredServiceHolder> serviceConfigurations) {
        this.serviceConfigurations = serviceConfigurations;
    }

    public static ServiceConfigurationManager of() {
        return new ServiceConfigurationManager();
    }

    public static ServiceConfigurationManager of(@NotNull Map<Class<? extends Service>, ConfiguredServiceHolder> serviceConfigurations) {
        Preconditions.notNull(serviceConfigurations, "Service configurations map must not be null");
        return new ServiceConfigurationManager(serviceConfigurations);
    }

    public ServiceConfigurationManager put(@NotNull ConfiguredServiceHolder configuredServiceHolder) {
        serviceConfigurations.put(configuredServiceHolder.getServiceClass(), configuredServiceHolder);
        return this;
    }

    public ServiceConfigurationManager putIfAbsent(@NotNull ConfiguredServiceHolder configuredServiceHolder) {
        if (!serviceConfigurations.containsKey(configuredServiceHolder.getServiceClass())) {
            serviceConfigurations.put(configuredServiceHolder.getServiceClass(), configuredServiceHolder);
        }
        return this;
    }

    public ServiceConfigurationManager putAll(@NotNull Set<ConfiguredServiceHolder> configuredServiceHolders) {
        configuredServiceHolders.forEach(configuredServiceHolder ->
                serviceConfigurations.put(configuredServiceHolder.getServiceClass(), configuredServiceHolder));
        return this;
    }

    public ServiceConfigurationManager putAllIfAbsent(@NotNull Set<ConfiguredServiceHolder> configuredServiceHolders) {
        configuredServiceHolders.forEach(configuredServiceHolder ->
                serviceConfigurations.put(configuredServiceHolder.getServiceClass(), configuredServiceHolder));
        return this;
    }

    public ServiceConfigurationManager removeByServiceClass(@NotNull Class<? extends Service> serviceClass) {
        serviceConfigurations.remove(serviceClass);
        return this;
    }

    public ServiceConfigurationManager removeByCondition(@NotNull Predicate<ConfiguredServiceHolder> condition) {
        List<Class<? extends Service>> serviceHoldersForRemoval = serviceConfigurations.values().stream()
                .filter(condition)
                .map(ConfiguredServiceHolder::getServiceClass)
                .collect(Collectors.toList());
        serviceHoldersForRemoval.forEach(serviceConfigurations::remove);
        return this;
    }

    public Stream<ConfiguredServiceHolder> stream() {
        return serviceConfigurations.values().stream();
    }

    public Map<Class<? extends Service>, ConfiguredServiceHolder> getServiceConfigurations() {
        return Map.copyOf(serviceConfigurations);
    }

}
