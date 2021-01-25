package io.perfeccionista.framework.exceptions.messages;

import org.jetbrains.annotations.NotNull;

public enum PagefactoryMobileAppiumMessages implements Messages {

    APPIUM_DEVICE_INSTANCE_NOT_STARTED("Appium device instance is not started"),
    MOBILE_APPLICATION_STATE_NOT_RECOGNIZED("Mobile application state '%s' is not recognized"),
    UNSUPPORTED_SEARCH_LOGIC("Locator chain can have only one multiple locator"),
    ;

    private String key;

    PagefactoryMobileAppiumMessages(String key) {
        this.key = key;
    }

    @Override
    public @NotNull String getErrorMessage() {
        return key;
    }

}
