package io.perfeccionista.framework.pagefactory.jsfunction;

import io.perfeccionista.framework.Environment;
import io.perfeccionista.framework.UseEnvironmentConfiguration;
import io.perfeccionista.framework.extension.PerfeccionistaExtension;
import io.perfeccionista.framework.pagefactory.browser.WebBrowserDispatcher;
import io.perfeccionista.framework.pagefactory.browser.WebBrowserService;
import io.perfeccionista.framework.pagefactory.configurations.TestEnvironmentConfiguration;
import io.perfeccionista.framework.pagefactory.elements.locators.WebLocatorChain;
import io.perfeccionista.framework.pagefactory.elements.locators.WebLocatorHolder;
import io.perfeccionista.framework.pagefactory.filter.MultipleResult;
import io.perfeccionista.framework.pagefactory.operation.JsOperation;
import io.perfeccionista.framework.pagefactory.screenshots.Screenshot;
import io.perfeccionista.framework.value.ValueService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.nio.file.Path;
import java.time.Duration;
import java.util.Set;

import static io.perfeccionista.framework.action.wrappers.CheckActionWrapper.runCheck;
import static io.perfeccionista.framework.utils.FileUtils.deleteIgnoreExceptions;

@ExtendWith(PerfeccionistaExtension.class)
@UseEnvironmentConfiguration(TestEnvironmentConfiguration.class)
public class GetScreenshotTest {

    @Test
    void singleElementTest(Environment env, ValueService val) {
        WebBrowserDispatcher chrome = env.getService(WebBrowserService.class)
                .createDispatcher(val.stringProcess("${[props]browser}"))
                .launch();
        chrome.tabs()
                .openUrl(val.stringProcess("${[props]base_url}"));

        runCheck(env, () -> {
            WebLocatorChain linkLocatorChain = WebLocatorChain.of()
                    .addLocator(WebLocatorHolder.of("ROOT", "text", "Elements"));
            JsOperation<Void> clickOperation = JsOperation.of(linkLocatorChain, new Click());
            chrome.executor()
                    .executeOperation(clickOperation);
        });
        deleteIgnoreExceptions(Path.of(getHome() + "/Downloads/images/simple-button.png"));
        Screenshot screenshot = runCheck(env, () -> {
            WebLocatorChain scrollToLocatorChain = WebLocatorChain.of()
                    .addLocator(WebLocatorHolder.of("ROOT", "id", "simple-button")
                            .addInvokedOnCallFunctions(new ScrollTo()
                                    // Без задержки скриншот снимается до того, как браузер успеет отрисовать элемент
                                    .setDelay(Duration.ofSeconds(1))));
            JsOperation<Screenshot> getScreenshotOperation = JsOperation.of(scrollToLocatorChain, new GetScreenshot("image/png"));
            return chrome.executor()
                    .executeOperation(getScreenshotOperation)
                    .singleResult().get();
        });
        screenshot.writeToFile(Path.of(getHome() + "/Downloads/images/simple-button.png"));
    }

    @Test
    void multipleElementTest(Environment env, ValueService val) {
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
        Set<Integer> indexes = Set.of(7, 56, 125, 170, 171);
        indexes.forEach(index -> {
            deleteIgnoreExceptions(Path.of(getHome() + "/Downloads/images/" + index + "-list-item.jpg"));
        });
        MultipleResult<Screenshot> screenshots = runCheck(env, () -> {
            WebLocatorChain scrollToLocatorChain = WebLocatorChain.of()
                    .addLocator(WebLocatorHolder.of("ROOT", "id", "text-list"))
                    .addLocator(WebLocatorHolder.of("LI", "className", "list-group-item")
                            .setIndexes(indexes));
            JsOperation<Screenshot> getScreenshotOperation = JsOperation.of(scrollToLocatorChain, new GetScreenshot("image/jpeg"));
            return chrome.executor()
                    .executeOperation(getScreenshotOperation)
                    .multipleResult();
        });
        screenshots.getValues()
                .forEach((key, value) -> {
                    value.writeToFile(Path.of(getHome() + "/Downloads/images/" + key + "-list-item.jpg"));
                });
    }

    private String getHome() {
        return System.getProperty("user.home");
    }

}
