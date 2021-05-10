package io.perfeccionista.framework.pagefactory.dispatcher;

import io.perfeccionista.framework.AbstractWebSeleniumParallelTest;
import io.perfeccionista.framework.pagefactory.elements.locators.WebLocatorChain;
import io.perfeccionista.framework.pagefactory.elements.locators.WebLocatorHolder;
import io.perfeccionista.framework.pagefactory.operation.handler.JsAddLogEntry;
import io.perfeccionista.framework.pagefactory.operation.WebElementOperation;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.util.logging.Level;

import static io.perfeccionista.framework.pagefactory.elements.locators.WebLocatorStrategy.CLASS_NAME;
import static org.junit.jupiter.api.Assertions.assertEquals;

@Tag("Browser") @Tag("Logs")
class LogsDispatcherTest extends AbstractWebSeleniumParallelTest {

    @Test
    void seleniumLogsDispatcherTest() {
        WebBrowserDispatcher browser = openDefaultBrowser();
        browser.logs()
                .setLogLevel(Level.ALL);

        WebLocatorHolder blockLocator = WebLocatorHolder.of("ROOT", CLASS_NAME, "container")
                .setStrictSearch(true);
        WebLocatorChain webLocatorChain = WebLocatorChain.empty()
                .addFirstLocator(blockLocator);
        WebElementOperation<Void> operation = WebElementOperation.of(webLocatorChain, new JsAddLogEntry(Level.WARNING, "Test error message"));

        browser.executor()
                .executeWebElementOperation(operation);

        long expectedMessageCount = browser.logs()
                .getEntries()
                .filter(logMessage -> logMessage.contains("WARNING"))
                .filter(logMessage -> logMessage.contains("Test error message"))
                .count();

        assertEquals(1, expectedMessageCount);
    }

}
