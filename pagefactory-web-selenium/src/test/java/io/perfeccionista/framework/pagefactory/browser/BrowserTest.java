package io.perfeccionista.framework.pagefactory.browser;

import io.perfeccionista.framework.Environment;
import io.perfeccionista.framework.UseEnvironmentConfiguration;
import io.perfeccionista.framework.pagefactory.AbstractUiTest;
import io.perfeccionista.framework.pagefactory.elements.locators.WebLocatorChain;
import io.perfeccionista.framework.pagefactory.elements.locators.WebLocatorHolder;
import io.perfeccionista.framework.pagefactory.jsfunction.GetAttribute;
import io.perfeccionista.framework.pagefactory.jsfunction.ScrollTo;
import io.perfeccionista.framework.pagefactory.operation.JsOperation;
import io.perfeccionista.framework.pagefactory.configurations.TestEnvironmentConfiguration;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.util.Set;

import static io.perfeccionista.framework.pagefactory.elements.locators.WebLocatorStrategy.ID;
import static io.perfeccionista.framework.pagefactory.elements.locators.WebLocatorStrategy.TAG_NAME;
import static io.perfeccionista.framework.pagefactory.elements.locators.WebLocatorStrategy.XPATH;
import static io.perfeccionista.framework.utils.ThreadUtils.sleep;

/**
 * Case: Старт браузера, вкладки, переходы по страницам, изменение размера, выполнение Js
 */
@UseEnvironmentConfiguration(TestEnvironmentConfiguration.class)
public class BrowserTest extends AbstractUiTest {

    @Test @Disabled
    void startLocalBrowserTest(Environment env) {
        WebBrowserDispatcher chrome = env.getService(WebBrowserService.class)
                .createDispatcher("Chrome")
                .launch();
//        chrome.tabs()
//                .openUrl("http://yandex.ru");
        sleep(Duration.ofSeconds(5));
        chrome.close();
    }

    @Test
    void startWdmBrowserTest(Environment env) {
        WebBrowserDispatcher chrome = env.getService(WebBrowserService.class)
                .createDispatcher("WdmChrome")
                .launch();
        chrome.tabs()
                .openUrl("http://yandex.ru");
        chrome.close();
    }

    @Test
    void startRemoteBrowserTest(Environment env) {
        WebBrowserDispatcher chrome = env.getService(WebBrowserService.class)
                .createDispatcher("RemoteChrome")
                .launch();
        sleep(Duration.ofSeconds(5));
        chrome.close();
    }

}
