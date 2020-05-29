package io.perfeccionista.framework.pagefactory.elements.locators;

import io.perfeccionista.framework.Environment;
import io.perfeccionista.framework.UseEnvironmentConfiguration;
import io.perfeccionista.framework.extension.PerfeccionistaExtension;
import io.perfeccionista.framework.pagefactory.browser.WebBrowserDispatcher;
import io.perfeccionista.framework.pagefactory.browser.WebBrowserService;
import io.perfeccionista.framework.pagefactory.configurations.TestEnvironmentConfiguration;
import io.perfeccionista.framework.pagefactory.jsfunction.GetInnerText;
import io.perfeccionista.framework.pagefactory.jsfunction.ScrollTo;
import io.perfeccionista.framework.pagefactory.operation.JsOperation;
import io.perfeccionista.framework.value.ValueService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.time.Duration;

import static io.perfeccionista.framework.utils.ThreadUtils.sleep;

@ExtendWith(PerfeccionistaExtension.class)
@UseEnvironmentConfiguration(TestEnvironmentConfiguration.class)
public class WebLocatorsTest {

    @Test
    void executeOperationTest(Environment env, ValueService val) {
        WebBrowserDispatcher chrome = env.getService(WebBrowserService.class)
                .createDispatcher(val.stringProcess("${[props]browser}"))
                .launch();
        chrome.tabs()
                .openUrl(val.stringProcess("${[props]base_url}"));


        // TODO: Написать реальные локаторы на:
        //  один элемент
        //  один элемент с индексом
        //  много элементов
        //  много элементов с индексами
        //  с запросом хэша
        //  с проверкой хэша
        //  с действиями
        //  по разным локаторам (id, css, xpath, text и т.п.)
        WebLocatorHolder blockLocator = WebLocatorHolder.of("ROOT", "className", "container")
//                .setCalculateHash(true)
//                .setExpectedHash("40147a539d7214a432b4c3f71978e82b")
                .addInvokedOnCallFunctions(new ScrollTo());
        WebLocatorHolder textLocator = WebLocatorHolder.of("ROOT", "className", "row")
                .setIndex(0)
                .addInvokedOnCallFunctions(new ScrollTo());
        WebLocatorChain webLocatorChain = WebLocatorChain.of()
                .addLocator(blockLocator)
                .addLocator(textLocator);
        JsOperation<String> operation = JsOperation.of(webLocatorChain, new GetInnerText());

        sleep(Duration.ofSeconds(1));

        long start = System.nanoTime();
        for (int i = 0; i < 1000; i++) {
            chrome.executor()
                    .executeOperation(operation);
        }
        System.out.println((System.nanoTime() - start)/1_000_000);

        sleep(Duration.ofSeconds(1));

        chrome.close();
    }
}
