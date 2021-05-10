package io.perfeccionista.framework.pagefactory.dispatcher.system;

import io.appium.java_client.appmanagement.ApplicationState;
import io.perfeccionista.framework.exceptions.MobileApplicationStateIsNotRecognized;
import org.jetbrains.annotations.NotNull;

import static io.perfeccionista.framework.exceptions.messages.PagefactoryMobileAppiumMessages.MOBILE_APPLICATION_STATE_NOT_RECOGNIZED;

public class AppiumApplicationStateConverter {

    private AppiumApplicationStateConverter() {
    }

    public static @NotNull MobileApplicationState createMobileApplicationState(@NotNull ApplicationState applicationState) {
        switch (applicationState) {
            case NOT_INSTALLED: return MobileApplicationState.NOT_INSTALLED;
            case NOT_RUNNING: return MobileApplicationState.NOT_RUNNING;
            case RUNNING_IN_FOREGROUND: return MobileApplicationState.RUNNING_IN_FOREGROUND;
            case RUNNING_IN_BACKGROUND: return MobileApplicationState.RUNNING_IN_BACKGROUND;
            case RUNNING_IN_BACKGROUND_SUSPENDED: return MobileApplicationState.RUNNING_IN_BACKGROUND_SUSPENDED;
            default: throw MobileApplicationStateIsNotRecognized.exception(MOBILE_APPLICATION_STATE_NOT_RECOGNIZED.getMessage(applicationState));
        }
    }

}
