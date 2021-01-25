package io.perfeccionista.framework.pagefactory.dispatcher;

import io.perfeccionista.framework.Environment;
import io.perfeccionista.framework.pagefactory.dispatcher.driver.AndroidEspressoDriver;
import org.jetbrains.annotations.NotNull;
import org.openqa.selenium.remote.DesiredCapabilities;

public class LocalAppiumAndroidDeviceEmulatorDispatcher extends AbstractAppiumAndroidDeviceDispatcher {

    protected final String remoteUrl;

    public LocalAppiumAndroidDeviceEmulatorDispatcher(@NotNull Environment environment, @NotNull String remoteUrl, @NotNull DesiredCapabilities options) {
        super(environment, options);
        this.remoteUrl = remoteUrl;
    }

    @Override
    public LocalAppiumAndroidDeviceEmulatorDispatcher launch() {
        setTimeoutCapabilities();
        this.instance = new AndroidEspressoDriver(this.options);
        setTimeouts();
        return this;
    }

}
