package io.perfeccionista.framework.pagefactory.dispatcher;

import io.perfeccionista.framework.Environment;
import io.perfeccionista.framework.exceptions.ExceptionMapperNotFound;
import io.perfeccionista.framework.exceptions.IncorrectServiceConfiguration;
import io.perfeccionista.framework.exceptions.mapper.ExceptionMapper;
import io.perfeccionista.framework.exceptions.MobileDeviceConfigurationNotFound;
import io.perfeccionista.framework.exceptions.MobileDeviceDispatcherNotStarted;
import io.perfeccionista.framework.service.Service;
import io.perfeccionista.framework.service.ServiceConfiguration;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

import static io.perfeccionista.framework.exceptions.messages.EnvironmentMessages.SERVICE_CONFIGURATION_NOT_VALID;
import static io.perfeccionista.framework.exceptions.messages.PageFactoryMobileApiMessages.MOBILE_DEVICE_CONFIGURATION_NOT_FOUND;
import static io.perfeccionista.framework.exceptions.messages.PageFactoryMobileApiMessages.NO_ACTIVE_MOBILE_DEVICE_DISPATCHER_FOUND;
import static io.perfeccionista.framework.exceptions.messages.PageFactoryMobileApiMessages.NO_ACTIVE_MOBILE_DEVICE_DISPATCHER_WITH_NAME_FOUND;
import static io.perfeccionista.framework.exceptions.messages.UtilsMessages.EXCEPTION_MAPPER_FOR_CLASS_NOT_FOUND;

public class MobileDeviceService implements Service {

    protected Environment environment;
    protected MobileDeviceServiceConfiguration configuration;

    protected Set<MobileDeviceDispatcher> mobileDeviceDispatchers = new HashSet<>();
    protected Map<String, MobileDeviceDispatcher> mobileDeviceDispatchersByName = new HashMap<>();
    protected MobileDeviceDispatcher activeMobileDeviceDispatcher = null;

    @Override
    public void init(@NotNull Environment environment, @NotNull ServiceConfiguration configuration) {
        this.environment = environment;
        this.configuration = validate(configuration);
    }

    @Override
    public void afterTest() {
        if (configuration.isCloseMobileDevicesAfterTest()) {
            closeAll();
        }
    }

    public @NotNull MobileDeviceDispatcher createDispatcher(@NotNull String mobileDeviceConfigurationName) {
        MobileDeviceDispatcher mobileDeviceDispatcher = Optional.ofNullable(configuration.getMobileDeviceConfigurations().get(mobileDeviceConfigurationName))
                .orElseThrow(() -> MobileDeviceConfigurationNotFound.exception(MOBILE_DEVICE_CONFIGURATION_NOT_FOUND.getMessage(mobileDeviceConfigurationName)))
                .get();
        mobileDeviceDispatchers.add(mobileDeviceDispatcher);
        this.activeMobileDeviceDispatcher = mobileDeviceDispatcher;
        return mobileDeviceDispatcher;
    }

    public @NotNull MobileDeviceDispatcher createDispatcher(@NotNull String mobileDeviceConfigurationName, @NotNull String mobileDeviceDispatcherName) {
        MobileDeviceDispatcher mobileDeviceDispatcher = Optional.ofNullable(configuration.getMobileDeviceConfigurations().get(mobileDeviceConfigurationName))
                .orElseThrow(() -> MobileDeviceConfigurationNotFound.exception(MOBILE_DEVICE_CONFIGURATION_NOT_FOUND.getMessage(mobileDeviceConfigurationName)))
                .get();
        mobileDeviceDispatchersByName.put(mobileDeviceDispatcherName, mobileDeviceDispatcher);
        this.activeMobileDeviceDispatcher = mobileDeviceDispatcher;
        return mobileDeviceDispatcher;
    }

    public boolean isActiveDispatcherRunning() {
        return Objects.nonNull(activeMobileDeviceDispatcher);
    }

    public @NotNull MobileDeviceDispatcher getActiveDispatcher() {
        return Optional.ofNullable(activeMobileDeviceDispatcher)
                .orElseThrow(() -> MobileDeviceDispatcherNotStarted.exception(NO_ACTIVE_MOBILE_DEVICE_DISPATCHER_FOUND.getMessage()));
    }

    public @NotNull MobileDeviceDispatcher setActiveDispatcher(@NotNull String mobileDeviceDispatcherName) {
        activeMobileDeviceDispatcher = getDispatcherByName(mobileDeviceDispatcherName);
        return this.activeMobileDeviceDispatcher;
    }

    public @NotNull MobileDeviceDispatcher getDispatcherByName(@NotNull String mobileDeviceDispatcherName) {
        return Optional.ofNullable(mobileDeviceDispatchersByName.get(mobileDeviceDispatcherName))
                .orElseThrow(() -> MobileDeviceDispatcherNotStarted.exception(NO_ACTIVE_MOBILE_DEVICE_DISPATCHER_WITH_NAME_FOUND.getMessage(mobileDeviceDispatcherName)));
    }

    public MobileDeviceService closeAll() {
        mobileDeviceDispatchersByName.forEach((name, mobileDeviceDispatcher) -> {
            mobileDeviceDispatcher.close();
        });
        mobileDeviceDispatchers.forEach(MobileDeviceDispatcher::close);
        return this;
    }

    public @NotNull ExceptionMapper getExceptionMapper(@NotNull Class<? extends ExceptionMapper> exceptionMapperClass) {
        return Optional.ofNullable(this.configuration.getExceptionMappers().get(exceptionMapperClass))
                .orElseThrow(() -> ExceptionMapperNotFound
                        .exception(EXCEPTION_MAPPER_FOR_CLASS_NOT_FOUND.getMessage(exceptionMapperClass.getCanonicalName())));
    }

    protected MobileDeviceServiceConfiguration validate(ServiceConfiguration configuration) {
        if (configuration instanceof MobileDeviceServiceConfiguration) {
            return (MobileDeviceServiceConfiguration) configuration;
        }
        throw IncorrectServiceConfiguration
                .exception(SERVICE_CONFIGURATION_NOT_VALID.getMessage(configuration.getClass().getCanonicalName(), this.getClass().getCanonicalName()));
    }

}
