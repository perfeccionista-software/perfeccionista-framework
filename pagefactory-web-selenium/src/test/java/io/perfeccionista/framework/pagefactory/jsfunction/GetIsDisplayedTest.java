package io.perfeccionista.framework.pagefactory.jsfunction;

import io.perfeccionista.framework.AbstractWebSeleniumParallelTest;
import io.perfeccionista.framework.Environment;
import io.perfeccionista.framework.pagefactory.browser.WebBrowserDispatcher;
import io.perfeccionista.framework.pagefactory.elements.locators.WebLocatorChain;
import io.perfeccionista.framework.pagefactory.elements.locators.WebLocatorHolder;
import io.perfeccionista.framework.pagefactory.operation.JsOperation;
import org.junit.jupiter.api.Test;

import static io.perfeccionista.framework.invocation.wrapper.CheckInvocationWrapper.runCheck;
import static io.perfeccionista.framework.pagefactory.elements.locators.WebLocatorStrategy.ID;
import static io.perfeccionista.framework.pagefactory.elements.locators.WebLocatorStrategy.TEXT;

class GetIsDisplayedTest extends AbstractWebSeleniumParallelTest {

    @Test
    void singleElementTest(Environment environment) {
        WebBrowserDispatcher browser = openDefaultBrowser();

        runCheck(environment, () -> {
            WebLocatorChain linkLocatorChain = WebLocatorChain.empty()
                    .addLastLocator(WebLocatorHolder.of("ROOT", TEXT, "Elements"));
            JsOperation<Void> clickOperation = JsOperation.of(linkLocatorChain, new MouseClickLeftButton());
            browser.executor()
                    .executeOperation(clickOperation);
        });
        boolean visibleLinkIsDisplayed = runCheck(environment, () -> {
            WebLocatorChain linkElementLocatorChain = WebLocatorChain.empty()
                    .addLastLocator(WebLocatorHolder.of("ROOT", ID, "simple-link"));
            JsOperation<Boolean> getIsDisplayedOperation = JsOperation.of(linkElementLocatorChain, new GetIsDisplayed());
            return browser.executor()
                    .executeOperation(getIsDisplayedOperation)
                    .ifException(e -> {
                        throw e;
                    })
                    .getResult();
        });
        System.out.println(visibleLinkIsDisplayed);
        boolean invisibleLinkIsDisplayed = runCheck(environment, () -> {
            WebLocatorChain textElementLocatorChain = WebLocatorChain.empty()
                    .addLastLocator(WebLocatorHolder.of("ROOT", TEXT, "Simple Link clicked"));
            JsOperation<Boolean> getIsDisplayed2Operation = JsOperation.of(textElementLocatorChain, new GetIsDisplayed());
            return browser.executor()
                    .executeOperation(getIsDisplayed2Operation)
                    .ifException(e -> {
                        throw e;
                    })
                    .getResult();
        });
        System.out.println(invisibleLinkIsDisplayed);
    }

}
