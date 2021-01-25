package io.perfeccionista.framework.pagefactory.dispatcher.configuration;

import io.perfeccionista.framework.Environment;
import io.perfeccionista.framework.pagefactory.dispatcher.LocalAppiumIosDeviceEmulatorDispatcher;
import io.perfeccionista.framework.pagefactory.dispatcher.MobileDeviceDispatcher;
import org.openqa.selenium.remote.DesiredCapabilities;

import static io.appium.java_client.remote.MobileCapabilityType.AUTOMATION_NAME;
import static io.appium.java_client.remote.MobileCapabilityType.DEVICE_NAME;
import static io.perfeccionista.framework.value.Values.stringProcess;
import static org.openqa.selenium.Platform.IOS;

public class AppiumIosXCUITestLocal implements MobileDeviceConfiguration {

    public DesiredCapabilities getOptions() {
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability("platformName", IOS);
        desiredCapabilities.setCapability("platformVersion", "13.6");
        desiredCapabilities.setCapability(AUTOMATION_NAME, "XCUITest");
        desiredCapabilities.setCapability(DEVICE_NAME, stringProcess("${[config] perfeccionista.mobile.ios.device}"));

        // TODO: Вынести в отдельную сущность Application
//        desiredCapabilities.setCapability("app", stringProcess("${[config] ios.app}"));
        desiredCapabilities.setCapability("bundleId", stringProcess("com.apple.mobilecal"));
//        desiredCapabilities.setCapability("noReset", true);
        return desiredCapabilities;
    }

    @Override
    public MobileDeviceDispatcher get() {
        String remoteUrl = stringProcess("${[config] perfeccionista.mobile.ios.remote}");
        return new LocalAppiumIosDeviceEmulatorDispatcher(Environment.getCurrent(), remoteUrl, getOptions());
    }


}
