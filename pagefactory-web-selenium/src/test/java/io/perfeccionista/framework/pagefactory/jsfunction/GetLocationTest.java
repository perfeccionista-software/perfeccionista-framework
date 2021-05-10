package io.perfeccionista.framework.pagefactory.jsfunction;

import io.perfeccionista.framework.AbstractWebSeleniumParallelTest;
import io.perfeccionista.framework.Environment;
import io.perfeccionista.framework.pagefactory.dispatcher.WebBrowserDispatcher;
import io.perfeccionista.framework.pagefactory.elements.ElementBounds;
import io.perfeccionista.framework.pagefactory.elements.locators.WebLocatorChain;
import io.perfeccionista.framework.pagefactory.elements.locators.WebLocatorHolder;
import io.perfeccionista.framework.pagefactory.operation.handler.JsGetElementBounds;
import io.perfeccionista.framework.pagefactory.operation.handler.JsScrollTo;
import io.perfeccionista.framework.pagefactory.operation.handler.SeleniumClick;
import io.perfeccionista.framework.pagefactory.operation.WebElementOperation;
import org.junit.jupiter.api.Test;

import java.time.Duration;

import static io.perfeccionista.framework.invocation.wrapper.CheckInvocationWrapper.runCheck;
import static io.perfeccionista.framework.pagefactory.elements.locators.WebLocatorStrategy.ID;
import static io.perfeccionista.framework.pagefactory.elements.locators.WebLocatorStrategy.TEXT;

class GetLocationTest extends AbstractWebSeleniumParallelTest {

    @Test
    void singleElementTest(Environment environment) {
        WebBrowserDispatcher browser = openDefaultBrowser();

        runCheck(environment, () -> {
            WebLocatorChain linkLocatorChain = WebLocatorChain.empty()
                    .addLastLocator(WebLocatorHolder.of("ROOT", TEXT, "Text List Elements"));
            WebElementOperation<Void> clickOperation = WebElementOperation.of(linkLocatorChain, new SeleniumClick());
            browser.executor()
                    .executeWebElementOperation(clickOperation);
        });
        ElementBounds location = runCheck(environment, () -> {
            WebLocatorChain scrollToLocatorChain = WebLocatorChain.empty()
                    .addLastLocator(WebLocatorHolder.of("ROOT", ID, "text-list"))
                    .addLastLocator(WebLocatorHolder.of("LI", TEXT, "Индия")
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
