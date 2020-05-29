package io.perfeccionista.framework.pagefactory.jsfunction;

import io.perfeccionista.framework.Environment;
import io.perfeccionista.framework.UseEnvironmentConfiguration;
import io.perfeccionista.framework.extension.PerfeccionistaExtension;
import io.perfeccionista.framework.pagefactory.browser.WebBrowserDispatcher;
import io.perfeccionista.framework.pagefactory.browser.WebBrowserService;
import io.perfeccionista.framework.pagefactory.configurations.TestEnvironmentConfiguration;
import io.perfeccionista.framework.pagefactory.elements.locators.WebLocatorChain;
import io.perfeccionista.framework.pagefactory.elements.locators.WebLocatorHolder;
import io.perfeccionista.framework.pagefactory.elements.methods.Location;
import io.perfeccionista.framework.pagefactory.operation.JsOperation;
import io.perfeccionista.framework.value.ValueService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.time.Duration;

import static io.perfeccionista.framework.action.wrappers.CheckActionWrapper.runCheck;

@ExtendWith(PerfeccionistaExtension.class)
@UseEnvironmentConfiguration(TestEnvironmentConfiguration.class)
public class GetLocationTest {

    @Test
    void singleElementTest(Environment env, ValueService val) {
        WebBrowserDispatcher chrome = env.getService(WebBrowserService.class)
                .createDispatcher(val.stringProcess("${[props]browser}"))
                .launch();
        chrome.tabs()
                .openUrl(val.stringProcess("${[props]base_url}"));

        runCheck(env, () -> {
            WebLocatorChain linkLocatorChain = WebLocatorChain.of()
                    .addLocator(WebLocatorHolder.of("ROOT", "text", "Text List Elements"));
            JsOperation<Void> clickOperation = JsOperation.of(linkLocatorChain, new Click());
            chrome.executor()
                    .executeOperation(clickOperation);
        });
        Location location = runCheck(env, () -> {
            WebLocatorChain scrollToLocatorChain = WebLocatorChain.of()
                    .addLocator(WebLocatorHolder.of("ROOT", "id", "text-list"))
                    .addLocator(WebLocatorHolder.of("LI", "text", "Индия")
                            .addInvokedOnCallFunctions(new ScrollTo()
                                    .setDelay(Duration.ofSeconds(5))));
            JsOperation<Location> getLocationOperation = JsOperation.of(scrollToLocatorChain, new GetLocation());
            return chrome.executor()
                    .executeOperation(getLocationOperation)
                    .singleResult().get();
        });
        System.out.println(location);
    }

}
