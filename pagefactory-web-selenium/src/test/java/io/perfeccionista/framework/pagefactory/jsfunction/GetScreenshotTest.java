package io.perfeccionista.framework.pagefactory.jsfunction;

import io.perfeccionista.framework.AbstractWebSeleniumParallelTest;
import io.perfeccionista.framework.Environment;
import io.perfeccionista.framework.invocation.wrapper.MultipleAttemptInvocationWrapper;
import io.perfeccionista.framework.pagefactory.dispatcher.WebBrowserDispatcher;
import io.perfeccionista.framework.pagefactory.elements.selectors.WebSelectorChain;
import io.perfeccionista.framework.pagefactory.elements.selectors.WebSelectorHolder;
import io.perfeccionista.framework.pagefactory.operation.handler.JsGetScreenshot;
import io.perfeccionista.framework.pagefactory.operation.handler.JsScrollTo;
import io.perfeccionista.framework.pagefactory.operation.handler.SeleniumClick;
import io.perfeccionista.framework.pagefactory.operation.WebElementOperation;
import io.perfeccionista.framework.screenshots.Screenshot;
import org.junit.jupiter.api.Test;

import java.nio.file.Paths;
import java.time.Duration;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static io.perfeccionista.framework.invocation.wrapper.MultipleAttemptInvocationWrapper.repeatInvocation;
import static io.perfeccionista.framework.pagefactory.elements.selectors.WebSelectorStrategy.CLASS_NAME;
import static io.perfeccionista.framework.pagefactory.elements.selectors.WebSelectorStrategy.ID;
import static io.perfeccionista.framework.pagefactory.elements.selectors.WebSelectorStrategy.EQUALS_TEXT;
import static io.perfeccionista.framework.utils.FileUtils.deleteFileIgnoreExceptions;

class GetScreenshotTest extends AbstractWebSeleniumParallelTest {

    @Test
    void singleElementTest(Environment environment) {
        WebBrowserDispatcher browser = openDefaultBrowser();

        repeatInvocation(() -> {
            WebSelectorChain linkLocatorChain = WebSelectorChain.empty()
                    .addLastSelector(WebSelectorHolder.of("ROOT", EQUALS_TEXT, "Elements"));
            WebElementOperation<Void> clickOperation = WebElementOperation.of(linkLocatorChain, new SeleniumClick());
            browser.executor()
                    .executeWebElementOperation(clickOperation);
        });
        deleteFileIgnoreExceptions(Paths.get(getHome() + "/Downloads/images/simple-button.png"));
        Screenshot screenshot = MultipleAttemptInvocationWrapper.repeatInvocation(() -> {
            WebSelectorChain scrollToLocatorChain = WebSelectorChain.empty()
                    .addLastSelector(WebSelectorHolder.of("ROOT", ID, "simple-button")
                            .addInvokedOnCallFunction(new JsScrollTo()
                                    // Без задержки скриншот снимается до того, как браузер успеет отрисовать элемент
                                    .setDelay(Duration.ofSeconds(1))));
            WebElementOperation<Screenshot> getScreenshotOperation = WebElementOperation.of(scrollToLocatorChain, new JsGetScreenshot().setMimeType("image/png"));
            return browser.executor()
                    .executeWebElementOperation(getScreenshotOperation)
                    .ifException((exceptionMapper, exception) -> {
                        throw exceptionMapper.map(exception);
                    })
                    .getResult();
        });
        screenshot.writeToFile(Paths.get(getHome() + "/Downloads/images/simple-button.png"));
    }

    @Test
    void multipleElementTest(Environment environment) {
        WebBrowserDispatcher browser = openDefaultBrowser();

        repeatInvocation(() -> {
            WebSelectorChain linkLocatorChain = WebSelectorChain.empty()
                    .addLastSelector(WebSelectorHolder.of("ROOT", EQUALS_TEXT, "Text List Elements"));
            WebElementOperation<Void> clickOperation = WebElementOperation.of(linkLocatorChain, new SeleniumClick());
            browser.executor()
                    .executeWebElementOperation(clickOperation);
        });
        Set<Integer> indexes = new HashSet<>(Arrays.asList(7, 56, 125, 170, 171));
        indexes.forEach(index -> {
            deleteFileIgnoreExceptions(Paths.get(getHome() + "/Downloads/images/" + index + "-list-item.jpg"));
        });
        Map<Integer, Screenshot> screenshots = MultipleAttemptInvocationWrapper.repeatInvocation(() -> {
            WebSelectorChain scrollToLocatorChain = WebSelectorChain.empty()
                    .addLastSelector(WebSelectorHolder.of("ROOT", ID, "text-list"))
                    .addLastSelector(WebSelectorHolder.of("LI", CLASS_NAME, "list-group-item").setIndexes(indexes));
            WebElementOperation<Screenshot> getScreenshotOperation = WebElementOperation.of(scrollToLocatorChain, new JsGetScreenshot().setMimeType("image/jpeg"));
            return browser.executor()
                    .executeWebElementOperation(getScreenshotOperation)
                    .ifException((exceptionMapper, exception) -> {
                        throw exceptionMapper.map(exception);
                    })
                    .getResults();
        });
        screenshots.entrySet().forEach((entry) -> {
                    entry.getValue().writeToFile(Paths.get(getHome() + "/Downloads/images/" + entry.getKey() + "-list-item.jpg"));
                });
    }

    private String getHome() {
        return System.getProperty("user.home");
    }

}
