package io.perfeccionista.framework.pagefactory.dispatcher;

import io.perfeccionista.framework.AbstractWebSeleniumParallelTest;
import io.perfeccionista.framework.pagefactory.elements.locators.WebLocatorChain;
import io.perfeccionista.framework.pagefactory.elements.locators.WebLocatorHolder;
import io.perfeccionista.framework.pagefactory.operation.handler.JsGetAttributeValue;
import io.perfeccionista.framework.pagefactory.operation.handler.JsGetInnerText;
import io.perfeccionista.framework.pagefactory.operation.handler.JsScrollTo;
import io.perfeccionista.framework.pagefactory.operation.WebElementOperation;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static io.perfeccionista.framework.pagefactory.elements.locators.WebLocatorStrategy.CLASS_NAME;
import static io.perfeccionista.framework.pagefactory.elements.locators.WebLocatorStrategy.ID;
import static io.perfeccionista.framework.pagefactory.elements.locators.WebLocatorStrategy.TAG_NAME;
import static io.perfeccionista.framework.pagefactory.elements.locators.WebLocatorStrategy.XPATH;

class OperationExecutorTest extends AbstractWebSeleniumParallelTest {

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
                .addInvokedOnCallFunction(new JsScrollTo());
        WebLocatorHolder textLocator = WebLocatorHolder.of("ROOT", CLASS_NAME, "row")
                .setIndex(0)
                .addInvokedOnCallFunction(new JsScrollTo());
        WebLocatorChain webLocatorChain = WebLocatorChain.empty()
                .addFirstLocator(blockLocator)
                .addFirstLocator(textLocator);
        WebElementOperation<String> operation = WebElementOperation.of(webLocatorChain, new JsGetInnerText());

        browser.executor()
                .executeWebElementOperation(operation);

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
                .addInvokedOnCallFunction(new JsScrollTo());
        WebLocatorHolder tableRowLocator = WebLocatorHolder.of("TR", TAG_NAME, "TR")
                .setIndexes(Set.of(3, 4, 7, 8, 12, 14))
                .setSingle(false)
                .setStrictSearch(false);
        WebLocatorHolder tableCellLocator = WebLocatorHolder.of("TD", XPATH, ".//td[1]");
        WebLocatorHolder tableCellElementLocator = WebLocatorHolder.of("ROOT", XPATH, ".//span")
                .addInvokedOnCallFunction(new JsScrollTo());
        WebLocatorChain webLocatorChain = WebLocatorChain.empty()
                .addFirstLocator(tableLocator)
                .addFirstLocator(tableRowLocator)
                .addFirstLocator(tableCellLocator)
                .addFirstLocator(tableCellElementLocator);

        WebElementOperation<String> operation = WebElementOperation.of(webLocatorChain, new JsGetAttributeValue("placeholder"));

//        sleep(Duration.ofSeconds(1));

//        long start = System.nanoTime();
//        for (int i = 0; i < 1000; i++) {
        browser.executor()
                .executeWebElementOperation(operation);
//        }
//        System.out.println((System.nanoTime() - start)/1_000_000);

//        sleep(Duration.ofSeconds(1));

        browser.close();
    }

}
