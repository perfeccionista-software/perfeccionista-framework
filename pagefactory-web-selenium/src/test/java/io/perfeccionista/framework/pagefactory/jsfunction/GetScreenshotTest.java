package io.perfeccionista.framework.pagefactory.jsfunction;

import io.perfeccionista.framework.AbstractWebSeleniumParallelTest;
import io.perfeccionista.framework.Environment;
import io.perfeccionista.framework.pagefactory.browser.WebBrowserDispatcher;
import io.perfeccionista.framework.pagefactory.elements.locators.WebLocatorChain;
import io.perfeccionista.framework.pagefactory.elements.locators.WebLocatorHolder;
import io.perfeccionista.framework.pagefactory.operation.JsOperation;
import io.perfeccionista.framework.screenshots.Screenshot;
import org.junit.jupiter.api.Test;

import java.nio.file.Path;
import java.time.Duration;
import java.util.Map;
import java.util.Set;

import static io.perfeccionista.framework.invocation.wrapper.CheckInvocationWrapper.runCheck;
import static io.perfeccionista.framework.pagefactory.elements.locators.WebLocatorStrategy.CLASS_NAME;
import static io.perfeccionista.framework.pagefactory.elements.locators.WebLocatorStrategy.ID;
import static io.perfeccionista.framework.pagefactory.elements.locators.WebLocatorStrategy.TEXT;
import static io.perfeccionista.framework.utils.FileUtils.deleteFileIgnoreExceptions;

class GetScreenshotTest extends AbstractWebSeleniumParallelTest {

    @Test
    void singleElementTest(Environment environment) {
        WebBrowserDispatcher browser = openDefaultBrowser();

        runCheck(environment, () -> {
            WebLocatorChain linkLocatorChain = WebLocatorChain.empty()
                    .addLastLocator(WebLocatorHolder.of("ROOT", TEXT, "Elements"));
            JsOperation<Void> clickOperation = JsOperation.of(linkLocatorChain, new MouseClickLeftButton());
            browser.executor()
                    .executeOperation(clickOperation);
        });
        deleteFileIgnoreExceptions(Path.of(getHome() + "/Downloads/images/simple-button.png"));
        Screenshot screenshot = runCheck(environment, () -> {
            WebLocatorChain scrollToLocatorChain = WebLocatorChain.empty()
                    .addLastLocator(WebLocatorHolder.of("ROOT", ID, "simple-button")
                            .addInvokedOnCallFunction(new ScrollTo()
                                    // Без задержки скриншот снимается до того, как браузер успеет отрисовать элемент
                                    .setDelay(Duration.ofSeconds(1))));
            JsOperation<Screenshot> getScreenshotOperation = JsOperation.of(scrollToLocatorChain, new GetScreenshot("image/png"));
            return browser.executor()
                    .executeOperation(getScreenshotOperation)
                    .ifException(e -> {
                        throw e;
                    })
                    .getResult();
        });
        screenshot.writeToFile(Path.of(getHome() + "/Downloads/images/simple-button.png"));
    }

    @Test
    void multipleElementTest(Environment environment) {
        WebBrowserDispatcher browser = openDefaultBrowser();

        runCheck(environment, () -> {
            WebLocatorChain linkLocatorChain = WebLocatorChain.empty()
                    .addLastLocator(WebLocatorHolder.of("ROOT", TEXT, "Text List Elements"));
            JsOperation<Void> clickOperation = JsOperation.of(linkLocatorChain, new MouseClickLeftButton());
            browser.executor()
                    .executeOperation(clickOperation);
        });
        Set<Integer> indexes = Set.of(7, 56, 125, 170, 171);
        indexes.forEach(index -> {
            deleteFileIgnoreExceptions(Path.of(getHome() + "/Downloads/images/" + index + "-list-item.jpg"));
        });
        Map<Integer, Screenshot> screenshots = runCheck(environment, () -> {
            WebLocatorChain scrollToLocatorChain = WebLocatorChain.empty()
                    .addLastLocator(WebLocatorHolder.of("ROOT", ID, "text-list"))
                    .addLastLocator(WebLocatorHolder.of("LI", CLASS_NAME, "list-group-item").setIndexes(indexes));
            JsOperation<Screenshot> getScreenshotOperation = JsOperation.of(scrollToLocatorChain, new GetScreenshot("image/jpeg"));
            return browser.executor()
                    .executeOperation(getScreenshotOperation)
                    .ifException(e -> {
                        throw e;
                    })
                    .getResults();
        });
        screenshots.entrySet().forEach((entry) -> {
                    entry.getValue().writeToFile(Path.of(getHome() + "/Downloads/images/" + entry.getKey() + "-list-item.jpg"));
                });
    }

    private String getHome() {
        return System.getProperty("user.home");
    }

}
