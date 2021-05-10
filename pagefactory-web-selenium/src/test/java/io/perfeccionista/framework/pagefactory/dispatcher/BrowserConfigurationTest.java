package io.perfeccionista.framework.pagefactory.dispatcher;

import io.perfeccionista.framework.AbstractWebSeleniumParallelTest;
import io.perfeccionista.framework.Environment;
import io.perfeccionista.framework.measurements.Dimensions2D;
import io.perfeccionista.framework.measurements.Point2D;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static io.perfeccionista.framework.Web.*;
import static io.perfeccionista.framework.value.Values.stringContains;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Case: Старт браузера, вкладки, переходы по страницам, изменение размера, выполнение Js
 */
class BrowserConfigurationTest extends AbstractWebSeleniumParallelTest {

    @Disabled
    @ParameterizedTest
    @ValueSource(strings = {
            "Chrome Local", "Chrome Remote", "Chrome Wdm", "Chrome Wdm Headless",
            "Edge Local", "Edge Wdm",
            "Firefox Local", "Firefox Remote", "Firefox Wdm",
            "Opera Local", "Opera Remote", "Opera Wdm",
            "Safari Local"
    })
    void browserConfigurationTest(String config) {
        WebBrowserDispatcher browser = Environment.getCurrent().getService(WebBrowserService.class)
                .createDispatcher(config)
                .launch();
        browser.window()
                .setAbsoluteLocation(Point2D.of(50, 50))
                .setOuterWindowSize(Dimensions2D.of(1200, 1000));
        browser.tabs()
                .openUrl("http://google.com")
                .should(activeTabHaveTitle(stringContains("Google")));
        String pageSource = browser.getWebPageContext()
                .getPageSource();
        assertTrue(pageSource.getBytes().length > 0);
        browser.close();
    }

}
