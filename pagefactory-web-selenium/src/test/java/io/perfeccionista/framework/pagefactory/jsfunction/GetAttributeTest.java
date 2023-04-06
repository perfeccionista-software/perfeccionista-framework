package io.perfeccionista.framework.pagefactory.jsfunction;

import io.perfeccionista.framework.AbstractWebSeleniumParallelTest;
import io.perfeccionista.framework.Environment;
import io.perfeccionista.framework.invocation.wrapper.MultipleAttemptInvocationWrapper;
import io.perfeccionista.framework.pagefactory.dispatcher.WebBrowserDispatcher;
import io.perfeccionista.framework.pagefactory.elements.selectors.WebSelectorChain;
import io.perfeccionista.framework.pagefactory.elements.selectors.WebSelectorHolder;
import io.perfeccionista.framework.pagefactory.operation.handler.JsGetAttributeValue;
import io.perfeccionista.framework.pagefactory.operation.handler.SeleniumClick;
import io.perfeccionista.framework.pagefactory.operation.WebElementOperation;
import org.junit.jupiter.api.Test;

import static io.perfeccionista.framework.invocation.wrapper.MultipleAttemptInvocationWrapper.repeatInvocation;
import static io.perfeccionista.framework.pagefactory.elements.selectors.WebSelectorStrategy.ID;
import static io.perfeccionista.framework.pagefactory.elements.selectors.WebSelectorStrategy.EQUALS_TEXT;
import static org.junit.jupiter.api.Assertions.assertEquals;

class GetAttributeTest extends AbstractWebSeleniumParallelTest {

    @Test
    void singleElementTest(Environment environment) {
        WebBrowserDispatcher browser = openDefaultBrowser();

        repeatInvocation(() -> {
            WebSelectorChain linkLocatorChain = WebSelectorChain.empty()
                    .addLastSelector(WebSelectorHolder.of("ROOT", EQUALS_TEXT, "Elements"));
            WebElementOperation<Void> clickOperation = WebElementOperation.of(linkLocatorChain, new SeleniumClick());
            browser.executor()
                    .executeWebElementOperation(clickOperation);
        });
        String placeholderValue = MultipleAttemptInvocationWrapper.repeatInvocation(() -> {
            WebSelectorChain scrollToLocatorChain = WebSelectorChain.empty()
                    .addLastSelector(WebSelectorHolder.of("ROOT", ID, "simple-input"));
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
