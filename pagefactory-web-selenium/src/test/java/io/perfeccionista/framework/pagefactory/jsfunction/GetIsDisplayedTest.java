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

import static io.perfeccionista.framework.invocation.wrappers.CheckInvocationWrapper.runCheck;
import static io.perfeccionista.framework.pagefactory.elements.locators.WebLocatorStrategy.ID;
import static io.perfeccionista.framework.pagefactory.elements.locators.WebLocatorStrategy.TEXT;

@ExtendWith(PerfeccionistaExtension.class)
@UseEnvironmentConfiguration(TestEnvironmentConfiguration.class)
public class GetIsDisplayedTest {

    @Test
    void singleElementTest(Environment env, ValueService val) {
        WebBrowserDispatcher chrome = env.getService(WebBrowserService.class)
                .createDispatcher(val.stringProcess("${[props]browser}"))
                .launch();
        chrome.tabs()
                .openUrl(val.stringProcess("${[props]start_url}"));

        runCheck(env, () -> {
            WebLocatorChain linkLocatorChain = WebLocatorChain.empty()
                    .addFirstLocator(WebLocatorHolder.of("ROOT", TEXT, "Elements"));
            JsOperation<Void> clickOperation = JsOperation.of(linkLocatorChain, new MouseClickLeftButton());
            chrome.executor()
                    .executeOperation(clickOperation);
        });
        boolean visibleLinkIsDisplayed = runCheck(env, () -> {
            WebLocatorChain linkElementLocatorChain = WebLocatorChain.empty()
                    .addFirstLocator(WebLocatorHolder.of("ROOT", ID, "simple-link"));
            JsOperation<Boolean> getIsDisplayedOperation = JsOperation.of(linkElementLocatorChain, new GetIsDisplayed());
            return chrome.executor()
                    .executeOperation(getIsDisplayedOperation)
                    .ifException(e -> {
                        throw e;
                    })
                    .getResult();
        });
        System.out.println(visibleLinkIsDisplayed);
        boolean invisibleLinkIsDisplayed = runCheck(env, () -> {
            WebLocatorChain textElementLocatorChain = WebLocatorChain.empty()
                    .addFirstLocator(WebLocatorHolder.of("ROOT", TEXT, "Simple Link clicked"));
            JsOperation<Boolean> getIsDisplayed2Operation = JsOperation.of(textElementLocatorChain, new GetIsDisplayed());
            return chrome.executor()
                    .executeOperation(getIsDisplayed2Operation)
                    .ifException(e -> {
                        throw e;
                    })
                    .getResult();
        });
        System.out.println(invisibleLinkIsDisplayed);
    }

}
