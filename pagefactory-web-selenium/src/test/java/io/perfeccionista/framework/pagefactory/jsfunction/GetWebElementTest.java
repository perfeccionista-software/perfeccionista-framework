package io.perfeccionista.framework.pagefactory.jsfunction;

import io.perfeccionista.framework.AbstractWebSeleniumParallelTest;
import io.perfeccionista.framework.Environment;
import io.perfeccionista.framework.pagefactory.browser.WebBrowserDispatcher;
import io.perfeccionista.framework.pagefactory.elements.locators.WebLocatorChain;
import io.perfeccionista.framework.pagefactory.elements.locators.WebLocatorHolder;
import io.perfeccionista.framework.pagefactory.operation.JsOperation;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebElement;

import static io.perfeccionista.framework.invocation.wrapper.CheckInvocationWrapper.runCheck;
import static io.perfeccionista.framework.pagefactory.elements.locators.WebLocatorStrategy.ID;
import static io.perfeccionista.framework.pagefactory.elements.locators.WebLocatorStrategy.TEXT;

class GetWebElementTest extends AbstractWebSeleniumParallelTest {

    @Test
    void singleElementTest(Environment environment) {
        WebBrowserDispatcher chrome = openDefaultBrowser();

        runCheck(environment, () -> {
            WebLocatorChain linkLocatorChain = WebLocatorChain.empty()
                    .addLastLocator(WebLocatorHolder.of("ROOT", TEXT, "Text List Elements"));
            JsOperation<WebElement> getLinkWebElementOperation = JsOperation.of(linkLocatorChain, new GetWebElement());
            chrome.executor()
                    .executeOperation(getLinkWebElementOperation)
                    .ifException(e -> {
                        throw e;
                    })
                    .getResult()
                    .click();
        });
        String text = runCheck(environment, () -> {
            WebLocatorChain listElementLocatorChain = WebLocatorChain.empty()
                    .addLastLocator(WebLocatorHolder.of("ROOT", ID, "text-list"))
                    .addLastLocator(WebLocatorHolder.of("LI", TEXT, "Ливан")
                            .addInvokedOnCallFunction(new ScrollTo()));
            JsOperation<WebElement> getListWebElementOperation = JsOperation.of(listElementLocatorChain, new GetWebElement());
            return chrome.executor()
                    .executeOperation(getListWebElementOperation)
                    .ifException(e -> {
                        throw e;
                    })
                    .getResult()
                    .getText();
        });
        System.out.println(text);
    }


}
