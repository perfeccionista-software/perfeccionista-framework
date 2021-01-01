package io.perfeccionista.framework.pagefactory.browser.executor;

import io.perfeccionista.framework.AbstractWebSeleniumParallelTest;
import io.perfeccionista.framework.pagefactory.browser.WebBrowserDispatcher;
import io.perfeccionista.framework.pagefactory.elements.locators.WebLocatorChain;
import io.perfeccionista.framework.pagefactory.elements.locators.WebLocatorHolder;
import io.perfeccionista.framework.pagefactory.jsfunction.GetAttribute;
import io.perfeccionista.framework.pagefactory.jsfunction.GetInnerText;
import io.perfeccionista.framework.pagefactory.jsfunction.ScrollTo;
import io.perfeccionista.framework.pagefactory.operation.JsOperation;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static io.perfeccionista.framework.pagefactory.elements.locators.WebLocatorStrategy.CLASS_NAME;
import static io.perfeccionista.framework.pagefactory.elements.locators.WebLocatorStrategy.ID;
import static io.perfeccionista.framework.pagefactory.elements.locators.WebLocatorStrategy.TAG_NAME;
import static io.perfeccionista.framework.pagefactory.elements.locators.WebLocatorStrategy.XPATH;

class SeleniumOperationExecutorTest extends AbstractWebSeleniumParallelTest {

    @Test
    void loadJsFunctionsTest() {

    }

    @Test
    void executeOperationTest() {
        WebBrowserDispatcher browser = openDefaultBrowser();

        // TODO: Написать реальные локаторы на:
        //  один элемент
        //  один элемент с индексом
        //  много элементов
        //  много элементов с индексами
        //  с запросом хэша
        //  с проверкой хэша
        //  с действиями
        //  по разным локаторам (id, css, xpath, text и т.п.)
        WebLocatorHolder blockLocator = WebLocatorHolder.of("ROOT", CLASS_NAME, "container")
//                .setCalculateHash(true)
//                .setExpectedHash("40147a539d7214a432b4c3f71978e82b")
                .addInvokedOnCallFunction(new ScrollTo());
        WebLocatorHolder textLocator = WebLocatorHolder.of("ROOT", CLASS_NAME, "row")
                .setIndex(0)
                .addInvokedOnCallFunction(new ScrollTo());
        WebLocatorChain webLocatorChain = WebLocatorChain.empty()
                .addFirstLocator(blockLocator)
                .addFirstLocator(textLocator);
        JsOperation<String> operation = JsOperation.of(webLocatorChain, new GetInnerText());

        browser.executor()
                .executeOperation(operation);

        browser.close();
    }

    @Test
    void scriptLoadingTest() {
        WebBrowserDispatcher browser = openDefaultBrowser();

        // TODO: Вынести тесты в соответствующие пакеты

        // TODO: Написать реальные локаторы на:
        //  один элемент
        //  один элемент с индексом
        //  много элементов
        //  много элементов с индексами
        //  с запросом хэша
        //  с проверкой хэша
        //  с действиями
        WebLocatorHolder tableLocator = WebLocatorHolder.of("ROOT", ID, "payments-table")
                .setExpectedHash("kljfldjf;sodjlfjsvnflgjlfjfgdlk")
                .addInvokedOnCallFunction(new ScrollTo());
        WebLocatorHolder tableRowLocator = WebLocatorHolder.of("TR", TAG_NAME, "TR")
                .setIndexes(Set.of(3, 4, 7, 8, 12, 14))
                .setSingle(false)
                .setStrictSearch(false);
        WebLocatorHolder tableCellLocator = WebLocatorHolder.of("TD", XPATH, ".//td[1]");
        WebLocatorHolder tableCellElementLocator = WebLocatorHolder.of("ROOT", XPATH, ".//span")
                .addInvokedOnCallFunction(new ScrollTo());
        WebLocatorChain webLocatorChain = WebLocatorChain.empty()
                .addFirstLocator(tableLocator)
                .addFirstLocator(tableRowLocator)
                .addFirstLocator(tableCellLocator)
                .addFirstLocator(tableCellElementLocator);

        JsOperation<String> operation = JsOperation.of(webLocatorChain, new GetAttribute("placeholder"));

//        sleep(Duration.ofSeconds(1));

//        long start = System.nanoTime();
//        for (int i = 0; i < 1000; i++) {
        browser.executor()
                .executeOperation(operation);
//        }
//        System.out.println((System.nanoTime() - start)/1_000_000);

//        sleep(Duration.ofSeconds(1));

        browser.close();
    }

}
