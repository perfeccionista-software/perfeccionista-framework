package io.perfeccionista.framework.pagefactory.dispatcher.preferences;

import io.perfeccionista.framework.matcher.dispatcher.MobileDevicePreferencesDispatcherMatcher;
import io.perfeccionista.framework.measurements.LocationGPS;
import org.jetbrains.annotations.NotNull;

import java.time.LocalDateTime;

public interface MobileDevicePreferencesDispatcher {

    boolean isAirplaneModeEnabled();

    MobileDevicePreferencesDispatcher setAirplaneModeEnabled(boolean enabled);

    boolean isWiFiEnabled();

    MobileDevicePreferencesDispatcher setWiFiEnabled(boolean enabled);

    boolean isDataEnabled();

    MobileDevicePreferencesDispatcher setDataEnabled(boolean enabled);

    @NotNull LocationGPS getLocation();

    MobileDevicePreferencesDispatcher setLocation(@NotNull LocationGPS location);

    // TODO: нужно как-то получать состояние сервиса и включать его
    //    locationService

    @NotNull LocalDateTime getTime();

    MobileDevicePreferencesDispatcher setNetworkType(@NotNull NetworkTechnology networkTechnology);

    MobileDevicePreferencesDispatcher setGsmSignal(@NotNull GsmSignalStrength signalStrength);

    MobileDevicePreferencesDispatcher setGsmVoice(@NotNull GsmVoiceState voiceState);

    MobileDevicePreferencesDispatcher setChargeState(@NotNull ChargeState chargeState);

    MobileDevicePreferencesDispatcher setBatteryCapacity(int capacity);

    MobileDevicePreferencesDispatcher should(@NotNull MobileDevicePreferencesDispatcherMatcher matcher);

}
