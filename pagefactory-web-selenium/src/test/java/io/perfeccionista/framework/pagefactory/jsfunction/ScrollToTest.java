package io.perfeccionista.framework.pagefactory.jsfunction;

import io.perfeccionista.framework.AbstractWebSeleniumParallelTest;
import io.perfeccionista.framework.Environment;
import io.perfeccionista.framework.pagefactory.dispatcher.WebBrowserDispatcher;
import io.perfeccionista.framework.pagefactory.elements.selectors.WebSelectorChain;
import io.perfeccionista.framework.pagefactory.elements.selectors.WebSelectorHolder;
import io.perfeccionista.framework.pagefactory.operation.handler.JsScrollTo;
import io.perfeccionista.framework.pagefactory.operation.handler.SeleniumClick;
import io.perfeccionista.framework.pagefactory.operation.WebElementOperation;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.util.Arrays;
import java.util.HashSet;

import static io.perfeccionista.framework.invocation.wrapper.MultipleAttemptInvocationWrapper.repeatInvocation;
import static io.perfeccionista.framework.pagefactory.elements.selectors.WebSelectorStrategy.CLASS_NAME;
import static io.perfeccionista.framework.pagefactory.elements.selectors.WebSelectorStrategy.ID;
import static io.perfeccionista.framework.pagefactory.elements.selectors.WebSelectorStrategy.EQUALS_TEXT;

class ScrollToTest extends AbstractWebSeleniumParallelTest {

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
        repeatInvocation(() -> {
            WebSelectorChain scrollToLocatorChain = WebSelectorChain.empty()
                    .addLastSelector(WebSelectorHolder.of("ROOT", ID, "text-list"))
                    .addLastSelector(WebSelectorHolder.of("LI", EQUALS_TEXT, "Ливан"));
            WebElementOperation<Void> scrollToOperation = WebElementOperation.of(scrollToLocatorChain, new JsScrollTo()
                    // Чтобы было видно результат прокрутки
                    .setDelay(Duration.ofSeconds(2)));
            browser.executor()
                    .executeWebElementOperation(scrollToOperation);
        });
    }

    @Test
    void multipleElementsTest(Environment environment) {
        WebBrowserDispatcher browser = openDefaultBrowser();

        repeatInvocation(() -> {
            WebSelectorChain linkLocatorChain = WebSelectorChain.empty()
                    .addLastSelector(WebSelectorHolder.of("ROOT", EQUALS_TEXT, "Text List Elements"));
            WebElementOperation<Void> clickOperation = WebElementOperation.of(linkLocatorChain, new SeleniumClick());
            browser.executor()
                    .executeWebElementOperation(clickOperation);
        });
        repeatInvocation(() -> {
            WebSelectorChain scrollToLocatorChain = WebSelectorChain.empty()
                    .addLastSelector(WebSelectorHolder.of("ROOT", ID, "text-list"))
                    .addLastSelector(WebSelectorHolder.of("LI", CLASS_NAME, "list-group-item")
                            .setSingle(false));
            WebElementOperation<Void> scrollToOperation = WebElementOperation.of(scrollToLocatorChain, new JsScrollTo()
                    .setDelay(Duration.ofMillis(50)));
            browser.executor()
                    .executeWebElementOperation(scrollToOperation);
        });
    }

    @Test
    void selectedElementsTest(Environment environment) {
        WebBrowserDispatcher browser = openDefaultBrowser();

        repeatInvocation(() -> {
            WebSelectorChain linkLocatorChain = WebSelectorChain.empty()
                    .addLastSelector(WebSelectorHolder.of("ROOT", EQUALS_TEXT, "Text List Elements"));
            WebElementOperation<Void> clickOperation = WebElementOperation.of(linkLocatorChain, new SeleniumClick());
            browser.executor()
                    .executeWebElementOperation(clickOperation);
        });
        repeatInvocation(() -> {
            WebSelectorChain scrollToLocatorChain = WebSelectorChain.empty()
                    .addLastSelector(WebSelectorHolder.of("ROOT", ID, "text-list"))
                    .addLastSelector(WebSelectorHolder.of("LI", CLASS_NAME, "list-group-item")
                            .setSingle(false).setIndexes(new HashSet<>(Arrays.asList(4, 65, 78, 170))));
            WebElementOperation<Void> scrollToOperation = WebElementOperation.of(scrollToLocatorChain, new JsScrollTo()
                    .setDelay(Duration.ofMillis(100)));
            browser.executor()
                    .executeWebElementOperation(scrollToOperation);
        });
    }

}
