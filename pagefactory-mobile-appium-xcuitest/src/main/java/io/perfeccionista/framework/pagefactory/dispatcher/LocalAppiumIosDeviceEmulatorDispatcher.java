package io.perfeccionista.framework.pagefactory.dispatcher;

import io.appium.java_client.ios.IOSDriver;
import io.perfeccionista.framework.Environment;
import org.jetbrains.annotations.NotNull;
import org.openqa.selenium.remote.DesiredCapabilities;

public class LocalAppiumIosDeviceEmulatorDispatcher extends AbstractAppiumIosDeviceDispatcher {

    protected final String remoteUrl;

    public LocalAppiumIosDeviceEmulatorDispatcher(@NotNull Environment environment, @NotNull String remoteUrl, @NotNull DesiredCapabilities options) {
        super(environment, options);
        this.remoteUrl = remoteUrl;
    }

    @Override
    public LocalAppiumIosDeviceEmulatorDispatcher launch() {
        setTimeoutCapabilities();
        this.instance = new IOSDriver<>(this.options);
        setTimeouts();
        return this;
    }

}

