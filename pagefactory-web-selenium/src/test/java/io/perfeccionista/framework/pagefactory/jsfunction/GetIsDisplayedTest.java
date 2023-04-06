package io.perfeccionista.framework.pagefactory.jsfunction;

import io.perfeccionista.framework.AbstractWebSeleniumParallelTest;
import io.perfeccionista.framework.Environment;
import io.perfeccionista.framework.invocation.wrapper.MultipleAttemptInvocationWrapper;
import io.perfeccionista.framework.pagefactory.dispatcher.WebBrowserDispatcher;
import io.perfeccionista.framework.pagefactory.elements.selectors.WebSelectorChain;
import io.perfeccionista.framework.pagefactory.elements.selectors.WebSelectorHolder;
import io.perfeccionista.framework.pagefactory.operation.handler.JsGetIsDisplayed;
import io.perfeccionista.framework.pagefactory.operation.handler.SeleniumClick;
import io.perfeccionista.framework.pagefactory.operation.WebElementOperation;
import org.junit.jupiter.api.Test;

import static io.perfeccionista.framework.invocation.wrapper.MultipleAttemptInvocationWrapper.repeatInvocation;
import static io.perfeccionista.framework.pagefactory.elements.selectors.WebSelectorStrategy.ID;
import static io.perfeccionista.framework.pagefactory.elements.selectors.WebSelectorStrategy.EQUALS_TEXT;

class GetIsDisplayedTest extends AbstractWebSeleniumParallelTest {

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
        boolean visibleLinkIsDisplayed = MultipleAttemptInvocationWrapper.repeatInvocation(() -> {
            WebSelectorChain linkElementLocatorChain = WebSelectorChain.empty()
                    .addLastSelector(WebSelectorHolder.of("ROOT", ID, "simple-link"));
            WebElementOperation<Boolean> getIsDisplayedOperation = WebElementOperation.of(linkElementLocatorChain, new JsGetIsDisplayed());
            return browser.executor()
                    .executeWebElementOperation(getIsDisplayedOperation)
                    .ifException((exceptionMapper, exception) -> {
                        throw exceptionMapper.map(exception);
                    })
                    .getResult();
        });
        System.out.println(visibleLinkIsDisplayed);
        boolean invisibleLinkIsDisplayed = MultipleAttemptInvocationWrapper.repeatInvocation(() -> {
            WebSelectorChain textElementLocatorChain = WebSelectorChain.empty()
                    .addLastSelector(WebSelectorHolder.of("ROOT", EQUALS_TEXT, "Simple Link clicked"));
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
