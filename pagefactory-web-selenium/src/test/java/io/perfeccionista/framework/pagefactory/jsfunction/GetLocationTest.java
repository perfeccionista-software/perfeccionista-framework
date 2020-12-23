package io.perfeccionista.framework.pagefactory.jsfunction;

import io.perfeccionista.framework.Environment;
import io.perfeccionista.framework.UseEnvironmentConfiguration;
import io.perfeccionista.framework.extension.PerfeccionistaExtension;
import io.perfeccionista.framework.pagefactory.browser.WebBrowserDispatcher;
import io.perfeccionista.framework.pagefactory.browser.WebBrowserService;
import io.perfeccionista.framework.pagefactory.configurations.TestEnvironmentConfiguration;
import io.perfeccionista.framework.pagefactory.elements.locators.WebLocatorChain;
import io.perfeccionista.framework.pagefactory.elements.locators.WebLocatorHolder;
import io.perfeccionista.framework.measurements.Location;
import io.perfeccionista.framework.pagefactory.elements.locators.WebLocatorStrategy;
import io.perfeccionista.framework.pagefactory.operation.JsOperation;
import io.perfeccionista.framework.value.ValueService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.time.Duration;

import static io.perfeccionista.framework.invocation.wrappers.CheckInvocationWrapper.runCheck;
import static io.perfeccionista.framework.pagefactory.elements.locators.WebLocatorStrategy.ID;
import static io.perfeccionista.framework.pagefactory.elements.locators.WebLocatorStrategy.TEXT;

@ExtendWith(PerfeccionistaExtension.class)
@UseEnvironmentConfiguration(TestEnvironmentConfiguration.class)
public class GetLocationTest {

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
        Location location = runCheck(env, () -> {
            WebLocatorChain scrollToLocatorChain = WebLocatorChain.empty()
                    .addLastLocator(WebLocatorHolder.of("ROOT", ID, "text-list"))
                    .addLastLocator(WebLocatorHolder.of("LI", TEXT, "Индия")
                            .addInvokedOnCallFunction(new ScrollTo()
                                    .setDelay(Duration.ofSeconds(1))));
            JsOperation<Location> getLocationOperation = JsOperation.of(scrollToLocatorChain, new GetLocation());
            return chrome.executor()
                    .executeOperation(getLocationOperation)
                    .ifException(e -> {
                        throw e;
                    })
                    .getResult();
        });
        System.out.println(location);
    }

}
