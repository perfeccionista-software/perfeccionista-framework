package io.perfeccionista.framework.pagefactory.jsfunction;

import io.perfeccionista.framework.Environment;
import io.perfeccionista.framework.UseEnvironmentConfiguration;
import io.perfeccionista.framework.extension.PerfeccionistaExtension;
import io.perfeccionista.framework.pagefactory.browser.WebBrowserDispatcher;
import io.perfeccionista.framework.pagefactory.browser.WebBrowserService;
import io.perfeccionista.framework.pagefactory.configurations.TestEnvironmentConfiguration;
import io.perfeccionista.framework.pagefactory.elements.locators.WebLocatorChain;
import io.perfeccionista.framework.pagefactory.elements.locators.WebLocatorHolder;
import io.perfeccionista.framework.pagefactory.operation.JsOperation;
import io.perfeccionista.framework.value.ValueService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.io.IOException;

import static io.perfeccionista.framework.invocation.wrappers.CheckActionWrapper.runCheck;

@ExtendWith(PerfeccionistaExtension.class)
@UseEnvironmentConfiguration(TestEnvironmentConfiguration.class)
public class GetAttributeTest {

    @Test
    void singleElementTest(Environment env, ValueService val) throws IOException {
        WebBrowserDispatcher chrome = env.getService(WebBrowserService.class)
                .createDispatcher(val.stringProcess("${[props]browser}"))
                .launch();
        chrome.tabs()
                .openUrl(val.stringProcess("${[props]base_url}"));

        runCheck(env, () -> {
            WebLocatorChain linkLocatorChain = WebLocatorChain.empty()
                    .addLocator(WebLocatorHolder.of("ROOT", "text", "Elements"));
            JsOperation<Void> clickOperation = JsOperation.of(linkLocatorChain, new Click());
            chrome.executor()
                    .executeOperation(clickOperation);
        });
        String placeholderValue = runCheck(env, () -> {
            WebLocatorChain scrollToLocatorChain = WebLocatorChain.empty()
                    .addLocator(WebLocatorHolder.of("ROOT", "id", "simple-input"));
            JsOperation<String> getAttributeOperation = JsOperation.of(scrollToLocatorChain, new GetAttribute("placeholder"));
            return chrome.executor()
                    .executeOperation(getAttributeOperation)
                    .singleResult().get();
        });
        Assertions.assertEquals("Enter text", placeholderValue);
    }

}