package io.perfeccionista.framework.pagefactory.dispatcher.preferences;

import io.perfeccionista.framework.Environment;
import io.perfeccionista.framework.exceptions.mapper.MobileExceptionMapper;
import io.perfeccionista.framework.matcher.dispatcher.MobileDevicePreferencesDispatcherMatcher;
import io.perfeccionista.framework.measurements.LocationGPS;
import io.perfeccionista.framework.pagefactory.dispatcher.driver.AndroidEspressoDriver;
import org.jetbrains.annotations.NotNull;

import java.time.LocalDateTime;

public class DefaultAppiumAndroidPreferencesDispatcher implements MobileDevicePreferencesDispatcher {

    protected final Environment environment;
    protected final AndroidEspressoDriver instance;
    protected final MobileExceptionMapper exceptionMapper;

    public DefaultAppiumAndroidPreferencesDispatcher(Environment environment, AndroidEspressoDriver instance, MobileExceptionMapper exceptionMapper) {
        this.environment = environment;
        this.instance = instance;
        this.exceptionMapper = exceptionMapper;
    }

    @Override
    public boolean isAirplaneModeEnabled() {
        return false;
    }

    @Override
    public MobileDevicePreferencesDispatcher setAirplaneModeEnabled(boolean enabled) {
        return null;
    }

    @Override
    public boolean isWiFiEnabled() {
        return false;
    }

    @Override
    public MobileDevicePreferencesDispatcher setWiFiEnabled(boolean enabled) {
        return null;
    }

    @Override
    public boolean isDataEnabled() {
        return false;
    }

    @Override
    public MobileDevicePreferencesDispatcher setDataEnabled(boolean enabled) {
        return null;
    }

    @Override
    public @NotNull LocationGPS getLocation() {
        return null;
    }

    @Override
    public MobileDevicePreferencesDispatcher setLocation(@NotNull LocationGPS location) {
        return null;
    }

    @Override
    public @NotNull LocalDateTime getTime() {
        return null;
    }

    @Override
    public MobileDevicePreferencesDispatcher setNetworkType(@NotNull NetworkTechnology networkTechnology) {
        return null;
    }

    @Override
    public MobileDevicePreferencesDispatcher setGsmSignal(@NotNull GsmSignalStrength signalStrength) {
        return null;
    }

    @Override
    public MobileDevicePreferencesDispatcher setGsmVoice(@NotNull GsmVoiceState voiceState) {
        return null;
    }

    @Override
    public MobileDevicePreferencesDispatcher setChargeState(@NotNull ChargeState chargeState) {
        return null;
    }

    @Override
    public MobileDevicePreferencesDispatcher setBatteryCapacity(int capacity) {
        return null;
    }

    @Override
    public MobileDevicePreferencesDispatcher should(@NotNull MobileDevicePreferencesDispatcherMatcher matcher) {
        return null;
    }
}
