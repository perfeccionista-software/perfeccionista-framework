package io.perfeccionista.framework.pagefactory.browser;

import io.perfeccionista.framework.AbstractWebSeleniumParallelTest;
import io.perfeccionista.framework.Environment;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static io.perfeccionista.framework.matcher.WebBrowserAssertions.activeTabHaveTitle;
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
                .setAbsoluteLocation(50, 50)
                .setOuterWindowSize(1200, 1000);
        browser.tabs()
                .openUrl("http://google.com");
        String pageSource = browser.tabs()
                .should(activeTabHaveTitle(stringContains("Google")))
                .getActiveTabPageSource();
        assertTrue(pageSource.getBytes().length > 0);
        browser.close();
    }

}
