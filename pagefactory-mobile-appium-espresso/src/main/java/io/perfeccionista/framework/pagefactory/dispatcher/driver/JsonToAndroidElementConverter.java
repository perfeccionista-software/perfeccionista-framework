package io.perfeccionista.framework.pagefactory.dispatcher.driver;

import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.internal.CapabilityHelpers;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.remote.internal.JsonToWebElementConverter;

import java.lang.reflect.Constructor;
import java.util.Optional;

import static io.appium.java_client.internal.ElementMap.getElementClass;

public class JsonToAndroidElementConverter extends JsonToWebElementConverter {

    protected final RemoteWebDriver driver;

    private final String platform;
    private final String automation;

    /**
     * Creates a new instance based on {@code driver} and object with session details.
     *
     * @param driver an instance of {@link RemoteWebDriver} subclass
     */
    public JsonToAndroidElementConverter(RemoteWebDriver driver) {
        super(driver);
        this.driver = driver;
        Capabilities caps = driver.getCapabilities();
        this.platform = CapabilityHelpers.getCapability(caps, "platformName", String.class);
        this.automation = CapabilityHelpers.getCapability(caps, "automationName", String.class);
    }

    @Override
    public Object apply(Object result) {
        Object toBeReturned = result;
        if (toBeReturned instanceof RemoteWebElement) {
            toBeReturned = newRemoteWebElement();
            ((RemoteWebElement) toBeReturned).setId(((RemoteWebElement) result).getId());
        }

        return super.apply(toBeReturned);
    }

    @Override
    protected RemoteWebElement newRemoteWebElement() {
        Class<? extends RemoteWebElement> target;
        if ("espresso".equalsIgnoreCase(Optional.ofNullable(platform).orElse(automation).trim())) {
            target = AndroidElement.class;
        } else {
            target = getElementClass(platform, automation);
        }

        try {
            Constructor<? extends RemoteWebElement> constructor = target.getDeclaredConstructor();
            constructor.setAccessible(true);
            RemoteWebElement result = constructor.newInstance();

            result.setParent(driver);
            result.setFileDetector(driver.getFileDetector());

            return result;
        } catch (Exception e) {
            throw new WebDriverException(e);
        }
    }

}
