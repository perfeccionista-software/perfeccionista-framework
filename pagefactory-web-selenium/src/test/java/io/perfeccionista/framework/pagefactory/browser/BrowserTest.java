package io.perfeccionista.framework.pagefactory.browser;

import io.perfeccionista.framework.AbstractWebSeleniumParallelTest;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.time.Duration;

import static io.perfeccionista.framework.utils.ThreadUtils.sleep;

/**
 * Case: Старт браузера, вкладки, переходы по страницам, изменение размера, выполнение Js
 */
class BrowserTest extends AbstractWebSeleniumParallelTest {

    @Test @Disabled
    void startLocalBrowserTest() {
        WebBrowserDispatcher browser = openBrowser("Chrome Local");
        sleep(Duration.ofSeconds(5));
        browser.close();
    }

    @Test @Disabled
    void startWdmBrowserTest() {
        WebBrowserDispatcher browser = openDefaultBrowser();
        sleep(Duration.ofSeconds(5));
        browser.close();
    }

    @Test @Disabled
    void startRemoteBrowserTest() {
        WebBrowserDispatcher browser = openBrowser("Chrome Remote");
        sleep(Duration.ofSeconds(5));
        browser.close();
    }

}
