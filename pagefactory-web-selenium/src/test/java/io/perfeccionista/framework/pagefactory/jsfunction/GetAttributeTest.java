package io.perfeccionista.framework.pagefactory.jsfunction;

import io.perfeccionista.framework.AbstractWebSeleniumParallelTest;
import io.perfeccionista.framework.Environment;
import io.perfeccionista.framework.pagefactory.dispatcher.WebBrowserDispatcher;
import io.perfeccionista.framework.pagefactory.elements.locators.WebLocatorChain;
import io.perfeccionista.framework.pagefactory.elements.locators.WebLocatorHolder;
import io.perfeccionista.framework.pagefactory.operation.handler.JsGetAttributeValue;
import io.perfeccionista.framework.pagefactory.operation.handler.SeleniumClick;
import io.perfeccionista.framework.pagefactory.operation.WebElementOperation;
import org.junit.jupiter.api.Test;

import static io.perfeccionista.framework.invocation.wrapper.CheckInvocationWrapper.runCheck;
import static io.perfeccionista.framework.pagefactory.elements.locators.WebLocatorStrategy.ID;
import static io.perfeccionista.framework.pagefactory.elements.locators.WebLocatorStrategy.TEXT;
import static org.junit.jupiter.api.Assertions.assertEquals;

class GetAttributeTest extends AbstractWebSeleniumParallelTest {

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
        String placeholderValue = runCheck(environment, () -> {
            WebLocatorChain scrollToLocatorChain = WebLocatorChain.empty()
                    .addLastLocator(WebLocatorHolder.of("ROOT", ID, "simple-input"));
            WebElementOperation<String> getAttributeOperation = WebElementOperation.of(scrollToLocatorChain, new JsGetAttributeValue("placeholder"));
            return browser.executor()
                    .executeWebElementOperation(getAttributeOperation)
                    .ifException((exceptionMapper, exception) -> {
                        throw exceptionMapper.map(exception);
                    })
                    .getResult();
        });
        assertEquals("Enter text", placeholderValue);
    }

}
