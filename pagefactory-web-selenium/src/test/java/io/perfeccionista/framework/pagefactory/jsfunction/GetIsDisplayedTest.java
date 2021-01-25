package io.perfeccionista.framework.pagefactory.jsfunction;

import io.perfeccionista.framework.AbstractWebSeleniumParallelTest;
import io.perfeccionista.framework.Environment;
import io.perfeccionista.framework.pagefactory.dispatcher.WebBrowserDispatcher;
import io.perfeccionista.framework.pagefactory.elements.locators.WebLocatorChain;
import io.perfeccionista.framework.pagefactory.elements.locators.WebLocatorHolder;
import io.perfeccionista.framework.pagefactory.operation.handler.JsGetIsDisplayed;
import io.perfeccionista.framework.pagefactory.operation.handler.SeleniumClick;
import io.perfeccionista.framework.pagefactory.operation.WebElementOperation;
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
            WebElementOperation<Void> clickOperation = WebElementOperation.of(linkLocatorChain, new SeleniumClick());
            browser.executor()
                    .executeWebElementOperation(clickOperation);
        });
        boolean visibleLinkIsDisplayed = runCheck(environment, () -> {
            WebLocatorChain linkElementLocatorChain = WebLocatorChain.empty()
                    .addLastLocator(WebLocatorHolder.of("ROOT", ID, "simple-link"));
            WebElementOperation<Boolean> getIsDisplayedOperation = WebElementOperation.of(linkElementLocatorChain, new JsGetIsDisplayed());
            return browser.executor()
                    .executeWebElementOperation(getIsDisplayedOperation)
                    .ifException((exceptionMapper, exception) -> {
                        throw exceptionMapper.map(exception);
                    })
                    .getResult();
        });
        System.out.println(visibleLinkIsDisplayed);
        boolean invisibleLinkIsDisplayed = runCheck(environment, () -> {
            WebLocatorChain textElementLocatorChain = WebLocatorChain.empty()
                    .addLastLocator(WebLocatorHolder.of("ROOT", TEXT, "Simple Link clicked"));
            WebElementOperation<Boolean> getIsDisplayed2Operation = WebElementOperation.of(textElementLocatorChain, new JsGetIsDisplayed());
            return browser.executor()
                    .executeWebElementOperation(getIsDisplayed2Operation)
                    .ifException((exceptionMapper, exception) -> {
                        throw exceptionMapper.map(exception);
                    })
                    .getResult();
        });
        System.out.println(invisibleLinkIsDisplayed);
    }

}
