package io.perfeccionista.framework.pagefactory.jsfunction;

import io.perfeccionista.framework.Environment;
import io.perfeccionista.framework.UseEnvironmentConfiguration;
import io.perfeccionista.framework.extension.PerfeccionistaExtension;
import io.perfeccionista.framework.pagefactory.browser.WebBrowserDispatcher;
import io.perfeccionista.framework.pagefactory.browser.WebBrowserService;
import io.perfeccionista.framework.pagefactory.configurations.TestEnvironmentConfiguration;
import io.perfeccionista.framework.pagefactory.elements.locators.WebLocatorChain;
import io.perfeccionista.framework.pagefactory.elements.locators.WebLocatorHolder;
import io.perfeccionista.framework.pagefactory.elements.locators.WebLocatorStrategy;
import io.perfeccionista.framework.pagefactory.operation.JsOperation;
import io.perfeccionista.framework.value.ValueService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.time.Duration;
import java.util.Set;

import static io.perfeccionista.framework.invocation.wrappers.CheckInvocationWrapper.runCheck;
import static io.perfeccionista.framework.pagefactory.elements.locators.WebLocatorStrategy.CLASS_NAME;
import static io.perfeccionista.framework.pagefactory.elements.locators.WebLocatorStrategy.ID;
import static io.perfeccionista.framework.pagefactory.elements.locators.WebLocatorStrategy.TEXT;

@ExtendWith(PerfeccionistaExtension.class)
@UseEnvironmentConfiguration(TestEnvironmentConfiguration.class)
public class ScrollToTest {

    @Test
    void singleElementTest(Environment env, ValueService val) {
        WebBrowserDispatcher chrome = env.getService(WebBrowserService.class)
                .createDispatcher(val.stringProcess("${[props]browser}"))
                .launch();
        chrome.tabs()
                .openUrl(val.stringProcess("${[props]start_url}"));

        runCheck(env, () -> {
            WebLocatorChain linkLocatorChain = WebLocatorChain.empty()
                    .addLastLocator(WebLocatorHolder.of("ROOT", TEXT, "Text List Elements"));
            JsOperation<Void> clickOperation = JsOperation.of(linkLocatorChain, new MouseClickLeftButton());
            chrome.executor()
                    .executeOperation(clickOperation);

        });
        runCheck(env, () -> {
            WebLocatorChain scrollToLocatorChain = WebLocatorChain.empty()
                    .addLastLocator(WebLocatorHolder.of("ROOT", ID, "text-list"))
                    .addLastLocator(WebLocatorHolder.of("LI", TEXT, "Ливан"));
            JsOperation<Void> scrollToOperation = JsOperation.of(scrollToLocatorChain, new ScrollTo()
                    // Чтобы было видно результат прокрутки
                    .setDelay(Duration.ofSeconds(2)));
            chrome.executor()
                    .executeOperation(scrollToOperation);
        });
    }

    @Test
    void multipleElementsTest(Environment env, ValueService val) {
        WebBrowserDispatcher chrome = env.getService(WebBrowserService.class)
                .createDispatcher(val.stringProcess("${[props]browser}"))
                .launch();
        chrome.tabs()
                .openUrl(val.stringProcess("${[props]base_url}"));

        runCheck(env, () -> {
            WebLocatorChain linkLocatorChain = WebLocatorChain.empty()
                    .addLastLocator(WebLocatorHolder.of("ROOT", TEXT, "Text List Elements"));
            JsOperation<Void> clickOperation = JsOperation.of(linkLocatorChain, new MouseClickLeftButton());
            chrome.executor()
                    .executeOperation(clickOperation);
        });
        runCheck(env, () -> {
            WebLocatorChain scrollToLocatorChain = WebLocatorChain.empty()
                    .addLastLocator(WebLocatorHolder.of("ROOT", ID, "text-list"))
                    .addLastLocator(WebLocatorHolder.of("LI", CLASS_NAME, "list-group-item")
                            .setSingle(false));
            JsOperation<Void> scrollToOperation = JsOperation.of(scrollToLocatorChain, new ScrollTo()
                    .setDelay(Duration.ofMillis(50)));
            chrome.executor()
                    .executeOperation(scrollToOperation);
        });
    }

    @Test
    void selectedElementsTest(Environment env, ValueService val) {
        WebBrowserDispatcher chrome = env.getService(WebBrowserService.class)
                .createDispatcher(val.stringProcess("${[props]browser}"))
                .launch();
        chrome.tabs()
                .openUrl(val.stringProcess("${[props]base_url}"));

        runCheck(env, () -> {
            WebLocatorChain linkLocatorChain = WebLocatorChain.empty()
                    .addLastLocator(WebLocatorHolder.of("ROOT", TEXT, "Text List Elements"));
            JsOperation<Void> clickOperation = JsOperation.of(linkLocatorChain, new MouseClickLeftButton());
            chrome.executor()
                    .executeOperation(clickOperation);
        });
        runCheck(env, () -> {
            WebLocatorChain scrollToLocatorChain = WebLocatorChain.empty()
                    .addLastLocator(WebLocatorHolder.of("ROOT", ID, "text-list"))
                    .addLastLocator(WebLocatorHolder.of("LI", CLASS_NAME, "list-group-item")
                            .setSingle(false).setIndexes(Set.of(4, 65, 78, 170)));
            JsOperation<Void> scrollToOperation = JsOperation.of(scrollToLocatorChain, new ScrollTo()
                    .setDelay(Duration.ofMillis(100)));
            chrome.executor()
                    .executeOperation(scrollToOperation);
        });
    }

}
