package io.perfeccionista.framework.pagefactory.dispatcher.preferences;

import io.appium.java_client.android.GsmCallActions;
import org.jetbrains.annotations.NotNull;

public class GsmCallActionConverter {

    private GsmCallActionConverter() {
    }

    public static GsmCallActions createAppiumGsmCallAction(@NotNull GsmCallAction callAction) {
        switch (callAction) {
            case CALL: return GsmCallActions.CALL;
            case ACCEPT: return GsmCallActions.ACCEPT;
            case CANCEL: return GsmCallActions.CANCEL;
            default: return GsmCallActions.HOLD;
        }
    }

}
