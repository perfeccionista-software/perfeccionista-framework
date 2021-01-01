package io.perfeccionista.framework.pagefactory.jsfunction;

import io.perfeccionista.framework.AbstractWebSeleniumParallelTest;
import io.perfeccionista.framework.Environment;
import io.perfeccionista.framework.pagefactory.browser.WebBrowserDispatcher;
import io.perfeccionista.framework.pagefactory.elements.locators.WebLocatorChain;
import io.perfeccionista.framework.pagefactory.elements.locators.WebLocatorHolder;
import io.perfeccionista.framework.pagefactory.operation.JsOperation;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.util.Set;

import static io.perfeccionista.framework.invocation.wrapper.CheckInvocationWrapper.runCheck;
import static io.perfeccionista.framework.pagefactory.elements.locators.WebLocatorStrategy.CLASS_NAME;
import static io.perfeccionista.framework.pagefactory.elements.locators.WebLocatorStrategy.ID;
import static io.perfeccionista.framework.pagefactory.elements.locators.WebLocatorStrategy.TEXT;

class ScrollToTest extends AbstractWebSeleniumParallelTest {

    @Test
    void singleElementTest(Environment environment) {
        WebBrowserDispatcher browser = openDefaultBrowser();

        runCheck(environment, () -> {
            WebLocatorChain linkLocatorChain = WebLocatorChain.empty()
                    .addLastLocator(WebLocatorHolder.of("ROOT", TEXT, "Text List Elements"));
            JsOperation<Void> clickOperation = JsOperation.of(linkLocatorChain, new MouseClickLeftButton());
            browser.executor()
                    .executeOperation(clickOperation);

        });
        runCheck(environment, () -> {
            WebLocatorChain scrollToLocatorChain = WebLocatorChain.empty()
                    .addLastLocator(WebLocatorHolder.of("ROOT", ID, "text-list"))
                    .addLastLocator(WebLocatorHolder.of("LI", TEXT, "Ливан"));
            JsOperation<Void> scrollToOperation = JsOperation.of(scrollToLocatorChain, new ScrollTo()
                    // Чтобы было видно результат прокрутки
                    .setDelay(Duration.ofSeconds(2)));
            browser.executor()
                    .executeOperation(scrollToOperation);
        });
    }

    @Test
    void multipleElementsTest(Environment environment) {
        WebBrowserDispatcher browser = openDefaultBrowser();

        runCheck(environment, () -> {
            WebLocatorChain linkLocatorChain = WebLocatorChain.empty()
                    .addLastLocator(WebLocatorHolder.of("ROOT", TEXT, "Text List Elements"));
            JsOperation<Void> clickOperation = JsOperation.of(linkLocatorChain, new MouseClickLeftButton());
            browser.executor()
                    .executeOperation(clickOperation);
        });
        runCheck(environment, () -> {
            WebLocatorChain scrollToLocatorChain = WebLocatorChain.empty()
                    .addLastLocator(WebLocatorHolder.of("ROOT", ID, "text-list"))
                    .addLastLocator(WebLocatorHolder.of("LI", CLASS_NAME, "list-group-item")
                            .setSingle(false));
            JsOperation<Void> scrollToOperation = JsOperation.of(scrollToLocatorChain, new ScrollTo()
                    .setDelay(Duration.ofMillis(50)));
            browser.executor()
                    .executeOperation(scrollToOperation);
        });
    }

    @Test
    void selectedElementsTest(Environment environment) {
        WebBrowserDispatcher browser = openDefaultBrowser();

        runCheck(environment, () -> {
            WebLocatorChain linkLocatorChain = WebLocatorChain.empty()
                    .addLastLocator(WebLocatorHolder.of("ROOT", TEXT, "Text List Elements"));
            JsOperation<Void> clickOperation = JsOperation.of(linkLocatorChain, new MouseClickLeftButton());
            browser.executor()
                    .executeOperation(clickOperation);
        });
        runCheck(environment, () -> {
            WebLocatorChain scrollToLocatorChain = WebLocatorChain.empty()
                    .addLastLocator(WebLocatorHolder.of("ROOT", ID, "text-list"))
                    .addLastLocator(WebLocatorHolder.of("LI", CLASS_NAME, "list-group-item")
                            .setSingle(false).setIndexes(Set.of(4, 65, 78, 170)));
            JsOperation<Void> scrollToOperation = JsOperation.of(scrollToLocatorChain, new ScrollTo()
                    .setDelay(Duration.ofMillis(100)));
            browser.executor()
                    .executeOperation(scrollToOperation);
        });
    }

}
