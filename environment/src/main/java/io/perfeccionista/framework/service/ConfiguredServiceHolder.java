package io.perfeccionista.framework.service;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public final class ConfiguredServiceHolder {

    private final Class<? extends Service> serviceClass;
    private final Class<? extends ServiceConfiguration> serviceConfigurationClass;
    private boolean enabled = true;
    private int order = 0;

    private ConfiguredServiceHolder(Class<? extends Service> serviceClass,
                                    Class<? extends ServiceConfiguration> configuration) {
        this.serviceClass = serviceClass;
        this.serviceConfigurationClass = configuration;
    }

    public static ConfiguredServiceHolder of(@NotNull Class<? extends Service> serviceClass,
                                             @NotNull Class<? extends ServiceConfiguration> configuration) {
        return new ConfiguredServiceHolder(serviceClass, configuration);
    }

    public static ConfiguredServiceHolder disabled(@NotNull Class<? extends Service> serviceClass) {
        return new ConfiguredServiceHolder(serviceClass, null)
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

    public @NotNull Class<? extends Service> getServiceClass() {
        return serviceClass;
    }

    public @Nullable Class<? extends ServiceConfiguration> getServiceConfigurationClass() {
        return serviceConfigurationClass;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public int getOrder() {
        return order;
    }

    public @NotNull String getServiceName() {
        return serviceClass.getCanonicalName();
    }

    public @NotNull String getServiceConfigurationName() {
        return serviceConfigurationClass == null ? "null" : serviceConfigurationClass.getCanonicalName();
    }

}
