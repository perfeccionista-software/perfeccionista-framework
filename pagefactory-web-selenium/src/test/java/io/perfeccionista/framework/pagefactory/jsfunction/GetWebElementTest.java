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
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.WebElement;

import static io.perfeccionista.framework.invocation.wrappers.CheckActionWrapper.runCheck;

@ExtendWith(PerfeccionistaExtension.class)
@UseEnvironmentConfiguration(TestEnvironmentConfiguration.class)
public class GetWebElementTest {

    @Test
    void singleElementTest(Environment env, ValueService val) {
        WebBrowserDispatcher chrome = env.getService(WebBrowserService.class)
                .createDispatcher(val.stringProcess("${[props]browser}"))
                .launch();
        chrome.tabs()
                .openUrl(val.stringProcess("${[props]base_url}"));

        runCheck(env, () -> {
            WebLocatorChain linkLocatorChain = WebLocatorChain.empty()
                    .addLocator(WebLocatorHolder.of("ROOT", "text", "Text List Elements"));
            JsOperation<WebElement> getLinkWebElementOperation = JsOperation.of(linkLocatorChain, new GetWebElement());
            chrome.executor()
                    .executeOperation(getLinkWebElementOperation)
                    .singleResult().get()
                    .click();
        });
        String text = runCheck(env, () -> {
            WebLocatorChain listElementLocatorChain = WebLocatorChain.empty()
                    .addLocator(WebLocatorHolder.of("ROOT", "id", "text-list"))
                    .addLocator(WebLocatorHolder.of("LI", "text", "Ливан")
                            .addInvokedOnCallFunction(new ScrollTo()));
            JsOperation<WebElement> getListWebElementOperation = JsOperation.of(listElementLocatorChain, new GetWebElement());
            return chrome.executor()
                    .executeOperation(getListWebElementOperation)
                    .singleResult().get()
                    .getText();
        });
        System.out.println(text);
    }


}
