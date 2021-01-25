package io.perfeccionista.framework.pagefactory.dispatcher.screen;

import io.perfeccionista.framework.measurements.Rotation3D;
import org.jetbrains.annotations.NotNull;
import org.openqa.selenium.DeviceRotation;

public class AppiumRotationConverter {

    private AppiumRotationConverter() {
    }

    public static DeviceRotation createDeviceRotation(@NotNull Rotation3D rotation) {
        int x = Double.valueOf(rotation.getX()).intValue();
        int y = Double.valueOf(rotation.getY()).intValue();
        int z = Double.valueOf(rotation.getZ()).intValue();
        return new DeviceRotation(x, y, z);
    }

    public static org.openqa.selenium.ScreenOrientation createAppiumScreenOrientation(@NotNull ScreenOrientation orientation) {
        if (ScreenOrientation.PORTRAIT == orientation) {
            return org.openqa.selenium.ScreenOrientation.PORTRAIT;
        }
        return org.openqa.selenium.ScreenOrientation.LANDSCAPE;
    }

    public static ScreenOrientation createPerfeccionistaScreenOrientation(@NotNull org.openqa.selenium.ScreenOrientation orientation) {
        if (org.openqa.selenium.ScreenOrientation.PORTRAIT == orientation) {
            return ScreenOrientation.PORTRAIT;
        }
        return ScreenOrientation.LANDSCAPE;
    }

}
