package io.perfeccionista.framework.service;

import io.perfeccionista.framework.preconditions.Preconditions;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

public final class ConfiguredServiceHolder {

    private final Class<? extends Service> serviceClass;
    private final Class<? extends Service> serviceImplementation;
    private final ServiceConfiguration serviceConfiguration;
    private boolean enabled = true;
    private int order = 0;

    private ConfiguredServiceHolder(Class<? extends Service> serviceClass,
                                    Class<? extends Service> serviceImplementation,
                                    ServiceConfiguration configuration) {
        this.serviceClass = serviceClass;
        this.serviceImplementation = serviceImplementation;
        this.serviceConfiguration = configuration;
    }

    public static ConfiguredServiceHolder of(@NotNull Class<? extends Service> serviceClass) {
        Preconditions.notNull(serviceClass, "Service class must not be null");
        return new ConfiguredServiceHolder(serviceClass, serviceClass, null);
    }

    public static ConfiguredServiceHolder of(@NotNull Class<? extends Service> serviceClass,
                                             @NotNull Class<? extends Service> serviceImplementation) {
        Preconditions.notNull(serviceClass, "Service class must not be null");
        Preconditions.notNull(serviceImplementation, "Service implementation must not be null");
        return new ConfiguredServiceHolder(serviceClass, serviceImplementation, null);
    }

    public static ConfiguredServiceHolder of(@NotNull Class<? extends Service> serviceClass,
                                             @Nullable ServiceConfiguration configuration) {
        Preconditions.notNull(serviceClass, "Service class must not be null");
        return new ConfiguredServiceHolder(serviceClass, serviceClass, configuration);
    }

    public static ConfiguredServiceHolder of(@NotNull Class<? extends Service> serviceClass,
                                             @NotNull Class<? extends Service> serviceImplementation,
                                             @Nullable ServiceConfiguration configuration) {
        Preconditions.notNull(serviceClass, "Service class must not be null");
        Preconditions.notNull(serviceImplementation, "Service implementation must not be null");
        return new ConfiguredServiceHolder(serviceClass, serviceImplementation, configuration);
    }

    public static ConfiguredServiceHolder disabled(@NotNull Class<? extends Service> serviceClass) {
        Preconditions.notNull(serviceClass, "Service class must not be null");
        return new ConfiguredServiceHolder(serviceClass, serviceClass, null)
                .disable();
    }

    public static ConfiguredServiceHolder disabled(@NotNull Class<? extends Service> serviceClass,
                                                   @NotNull Class<? extends Service> serviceImplementation) {
        Preconditions.notNull(serviceClass, "Service class must not be null");
        Preconditions.notNull(serviceClass, "Service implementation must not be null");
        return new ConfiguredServiceHolder(serviceClass, serviceImplementation, null)
                .disable();
    }

    public ConfiguredServiceHolder disable() {
        this.enabled = false;
        return this;
    }

    public ConfiguredServiceHolder setOrder(int order) {
        this.order = order;
        return this;
    }

    public boolean isConfigured() {
        return Objects.nonNull(serviceConfiguration);
    }

    public @NotNull Class<? extends Service> getServiceClass() {
        return serviceClass;
    }

    public @NotNull Class<? extends Service> getServiceImplementation() {
        return serviceImplementation;
    }

    public @Nullable ServiceConfiguration getServiceConfiguration() {
        return serviceConfiguration;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public int getOrder() {
        return order;
    }

    public @NotNull String getServiceName() {
        return serviceImplementation.getCanonicalName();
    }

    public @NotNull String getServiceConfigurationName() {
        return serviceConfiguration == null ? "null" : serviceConfiguration.getClass().getCanonicalName();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ConfiguredServiceHolder that = (ConfiguredServiceHolder) o;
        return serviceClass.equals(that.serviceClass);
    }

    @Override
    public int hashCode() {
        return serviceClass.hashCode();
    }

}
