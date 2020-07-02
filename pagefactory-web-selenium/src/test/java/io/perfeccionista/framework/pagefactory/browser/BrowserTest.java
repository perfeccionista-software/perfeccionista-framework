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
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.util.Set;

import static io.perfeccionista.framework.utils.ThreadUtils.sleep;

/**
 * Case: Старт браузера, вкладки, переходы по страницам, изменение размера, выполнение Js
 */
@UseEnvironmentConfiguration(TestEnvironmentConfiguration.class)
public class BrowserTest extends AbstractUiTest {

    @Test
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
        //        chrome.tabs()
//                .openUrl("http://yandex.ru");
        sleep(Duration.ofSeconds(5));
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


    @Test
    void scriptLoadingTest(Environment env) {
        WebBrowserDispatcher chrome = env.getService(WebBrowserService.class)
                .createDispatcher("Chrome")
                .launch();
        chrome.tabs()
                .openUrl("http://localhost:8084");

        // TODO: Вынести тесты в соответствующие пакеты

        // TODO: Написать реальные локаторы на:
        //  один элемент
        //  один элемент с индексом
        //  много элементов
        //  много элементов с индексами
        //  с запросом хэша
        //  с проверкой хэша
        //  с действиями
        WebLocatorHolder tableLocator = WebLocatorHolder.of("ROOT", "id", "payments-table")
                .setExpectedHash("kljfldjf;sodjlfjsvnflgjlfjfgdlk")
                .addInvokedOnCallFunction(new ScrollTo());
        WebLocatorHolder tableRowLocator = WebLocatorHolder.of("TR", "tagName", "TR")
                .setIndexes(Set.of(3, 4, 7, 8, 12, 14))
                .setSingle(false)
                .setStrictSearch(false);
        WebLocatorHolder tableCellLocator = WebLocatorHolder.of("TD", "xpath", ".//td[1]");
        WebLocatorHolder tableCellElementLocator = WebLocatorHolder.of("ROOT", "xpath", ".//span")
                .addInvokedOnCallFunction(new ScrollTo());
        WebLocatorChain webLocatorChain = WebLocatorChain.empty()
                .addLocator(tableLocator)
                .addLocator(tableRowLocator)
                .addLocator(tableCellLocator)
                .addLocator(tableCellElementLocator);

        JsOperation<String> operation = JsOperation.of(webLocatorChain, new GetAttribute("placeholder"));

        sleep(Duration.ofSeconds(1));

//        long start = System.nanoTime();
//        for (int i = 0; i < 1000; i++) {
            chrome.executor()
                    .executeOperation(operation);
//        }
//        System.out.println((System.nanoTime() - start)/1_000_000);

        sleep(Duration.ofSeconds(1));

        chrome.close();
    }


}
