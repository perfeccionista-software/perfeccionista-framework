package io.perfeccionista.framework.pagefactory.jsfunction;

import io.perfeccionista.framework.AbstractWebSeleniumParallelTest;
import io.perfeccionista.framework.Environment;
import io.perfeccionista.framework.pagefactory.dispatcher.WebBrowserDispatcher;
import io.perfeccionista.framework.pagefactory.elements.locators.WebLocatorChain;
import io.perfeccionista.framework.pagefactory.elements.locators.WebLocatorHolder;
import io.perfeccionista.framework.pagefactory.operation.handler.JsScrollTo;
import io.perfeccionista.framework.pagefactory.operation.handler.SeleniumGetWebElement;
import io.perfeccionista.framework.pagefactory.operation.WebElementOperation;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebElement;

import static io.perfeccionista.framework.invocation.wrapper.CheckInvocationWrapper.runCheck;
import static io.perfeccionista.framework.pagefactory.elements.locators.WebLocatorStrategy.ID;
import static io.perfeccionista.framework.pagefactory.elements.locators.WebLocatorStrategy.TEXT;

class GetWebElementTest extends AbstractWebSeleniumParallelTest {

    @Test
    void singleElementTest(Environment environment) {
        WebBrowserDispatcher chrome = openDefaultBrowser();

        runCheck(() -> {
            WebLocatorChain linkLocatorChain = WebLocatorChain.empty()
                    .addLastLocator(WebLocatorHolder.of("ROOT", TEXT, "Text List Elements"));
            WebElementOperation<WebElement> getLinkWebElementOperation = WebElementOperation.of(linkLocatorChain, new SeleniumGetWebElement());
            chrome.executor()
                    .executeWebElementOperation(getLinkWebElementOperation)
                    .ifException((exceptionMapper, exception) -> {
                        throw exceptionMapper.map(exception);
                    })
                    .getResult()
                    .click();
        });
        String text = runCheck(() -> {
            WebLocatorChain listElementLocatorChain = WebLocatorChain.empty()
                    .addLastLocator(WebLocatorHolder.of("ROOT", ID, "text-list"))
                    .addLastLocator(WebLocatorHolder.of("LI", TEXT, "Ливан")
                            .addInvokedOnCallFunction(new JsScrollTo()));
            WebElementOperation<WebElement> getListWebElementOperation = WebElementOperation.of(listElementLocatorChain, new SeleniumGetWebElement());
            return chrome.executor()
                    .executeWebElementOperation(getListWebElementOperation)
                    .ifException((exceptionMapper, exception) -> {
                        throw exceptionMapper.map(exception);
                    })
                    .getResult()
                    .getText();
        });
        System.out.println(text);
    }


}
