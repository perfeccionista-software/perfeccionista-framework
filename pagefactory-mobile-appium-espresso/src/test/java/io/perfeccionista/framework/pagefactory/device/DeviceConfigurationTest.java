package io.perfeccionista.framework.pagefactory.device;

import io.perfeccionista.framework.AbstractMobileAppiumParallelTest;
import io.perfeccionista.framework.Environment;
import io.perfeccionista.framework.pagefactory.dispatcher.MobileDeviceDispatcher;
import io.perfeccionista.framework.pagefactory.dispatcher.MobileDeviceService;
import io.perfeccionista.framework.utils.ThreadUtils;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.time.Duration;

import static io.perfeccionista.framework.matcher.MobileDeviceAssertions.beUnlocked;

class DeviceConfigurationTest extends AbstractMobileAppiumParallelTest {

    @Disabled
    @ParameterizedTest
    @ValueSource(strings = {
            "Android UiAutomator2 Local",
            "IOS XCUITest Local",
    })
    void deviceConfigurationTest(String config) {
        MobileDeviceDispatcher deviceDispatcher = Environment.getCurrent().getService(MobileDeviceService.class)
                .createDispatcher(config)
                .launch();

        ThreadUtils.sleep(Duration.ofSeconds(2));

        deviceDispatcher
                .should(beUnlocked());





        ThreadUtils.sleep(Duration.ofSeconds(2));
//        // Устанавливаем ориентацию и положение
//        device.window()
//                .setAbsoluteLocation(50, 50)
//                .setOuterWindowSize(1200, 1000);
//        // Открываем приложение
//        device.tabs()
//                .openUrl("http://google.com");
//        // Простая проверка
//        String pageSource = device.tabs()
//                .should(activeTabHaveTitle(stringContains("Google")))
//                .getActiveTabPageSource();
//        assertTrue(pageSource.getBytes().length > 0);
        deviceDispatcher.close();
    }

}

