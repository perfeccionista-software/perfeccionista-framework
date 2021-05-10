package io.perfeccionista.framework.pagefactory.dispatcher.driver;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.remote.HttpCommandExecutor;
import org.openqa.selenium.remote.http.HttpClient.Factory;

import java.net.URL;

public class AndroidEspressoDriver extends AndroidDriver<AndroidElement> {

    public AndroidEspressoDriver(HttpCommandExecutor executor, Capabilities capabilities) {
        super(executor, capabilities);
        setElementConverter(new JsonToAndroidElementConverter(this));
    }

    public AndroidEspressoDriver(URL remoteAddress, Capabilities desiredCapabilities) {
        super(remoteAddress, desiredCapabilities);
        setElementConverter(new JsonToAndroidElementConverter(this));
    }

    public AndroidEspressoDriver(URL remoteAddress, Factory httpClientFactory, Capabilities desiredCapabilities) {
        super(remoteAddress, httpClientFactory, desiredCapabilities);
        setElementConverter(new JsonToAndroidElementConverter(this));
    }

    public AndroidEspressoDriver(AppiumDriverLocalService service, Capabilities desiredCapabilities) {
        super(service, desiredCapabilities);
        setElementConverter(new JsonToAndroidElementConverter(this));
    }

    public AndroidEspressoDriver(AppiumDriverLocalService service, Factory httpClientFactory, Capabilities desiredCapabilities) {
        super(service, httpClientFactory, desiredCapabilities);
        setElementConverter(new JsonToAndroidElementConverter(this));
    }

    public AndroidEspressoDriver(AppiumServiceBuilder builder, Capabilities desiredCapabilities) {
        super(builder, desiredCapabilities);
        setElementConverter(new JsonToAndroidElementConverter(this));
    }

    public AndroidEspressoDriver(AppiumServiceBuilder builder, Factory httpClientFactory, Capabilities desiredCapabilities) {
        super(builder, httpClientFactory, desiredCapabilities);
        setElementConverter(new JsonToAndroidElementConverter(this));
    }

    public AndroidEspressoDriver(Factory httpClientFactory, Capabilities desiredCapabilities) {
        super(httpClientFactory, desiredCapabilities);
        setElementConverter(new JsonToAndroidElementConverter(this));
    }

    public AndroidEspressoDriver(Capabilities desiredCapabilities) {
        super(desiredCapabilities);
        setElementConverter(new JsonToAndroidElementConverter(this));
    }

}
