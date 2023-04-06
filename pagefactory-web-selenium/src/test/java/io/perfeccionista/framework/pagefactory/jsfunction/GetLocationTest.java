package io.perfeccionista.framework.pagefactory.jsfunction;

import io.perfeccionista.framework.AbstractWebSeleniumParallelTest;
import io.perfeccionista.framework.Environment;
import io.perfeccionista.framework.invocation.wrapper.MultipleAttemptInvocationWrapper;
import io.perfeccionista.framework.pagefactory.dispatcher.WebBrowserDispatcher;
import io.perfeccionista.framework.pagefactory.elements.ElementBounds;
import io.perfeccionista.framework.pagefactory.elements.selectors.WebSelectorChain;
import io.perfeccionista.framework.pagefactory.elements.selectors.WebSelectorHolder;
import io.perfeccionista.framework.pagefactory.operation.handler.JsGetElementBounds;
import io.perfeccionista.framework.pagefactory.operation.handler.JsScrollTo;
import io.perfeccionista.framework.pagefactory.operation.handler.SeleniumClick;
import io.perfeccionista.framework.pagefactory.operation.WebElementOperation;
import org.junit.jupiter.api.Test;

import java.time.Duration;

import static io.perfeccionista.framework.invocation.wrapper.MultipleAttemptInvocationWrapper.repeatInvocation;
import static io.perfeccionista.framework.pagefactory.elements.selectors.WebSelectorStrategy.ID;
import static io.perfeccionista.framework.pagefactory.elements.selectors.WebSelectorStrategy.EQUALS_TEXT;

class GetLocationTest extends AbstractWebSeleniumParallelTest {

    @Test
    void singleElementTest(Environment environment) {
        WebBrowserDispatcher browser = openDefaultBrowser();

        repeatInvocation(() -> {
            WebSelectorChain linkLocatorChain = WebSelectorChain.empty()
                    .addLastSelector(WebSelectorHolder.of("ROOT", EQUALS_TEXT, "Text List Elements"));
            WebElementOperation<Void> clickOperation = WebElementOperation.of(linkLocatorChain, new SeleniumClick());
            browser.executor()
                    .executeWebElementOperation(clickOperation);
        });
        ElementBounds location = MultipleAttemptInvocationWrapper.repeatInvocation(() -> {
            WebSelectorChain scrollToLocatorChain = WebSelectorChain.empty()
                    .addLastSelector(WebSelectorHolder.of("ROOT", ID, "text-list"))
                    .addLastSelector(WebSelectorHolder.of("LI", EQUALS_TEXT, "Индия")
                            .addInvokedOnCallFunction(new JsScrollTo().setDelay(Duration.ofSeconds(1))));
            WebElementOperation<ElementBounds> getLocationOperation = WebElementOperation.of(scrollToLocatorChain, new JsGetElementBounds());
            return browser.executor()
                    .executeWebElementOperation(getLocationOperation)
                    .ifException((exceptionMapper, exception) -> {
                        throw exceptionMapper.map(exception);
                    })
                    .getResult();
        });
        System.out.println(location);
    }

}
