package io.perfeccionista.framework;

import io.perfeccionista.framework.extension.PerfeccionistaExtension;
import io.perfeccionista.framework.measurements.Dimensions2D;
import io.perfeccionista.framework.pagefactory.dispatcher.WebBrowserDispatcher;
import io.perfeccionista.framework.pagefactory.dispatcher.WebBrowserService;
import io.perfeccionista.framework.pagefactory.configurations.PagefactoryWebSeleniumEnvironmentConfiguration;
import io.perfeccionista.framework.pagefactory.dispatcher.context.WebPageContext;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;

import java.io.IOException;
import java.util.logging.LogManager;

import static io.perfeccionista.framework.value.Values.stringEquals;
import static io.perfeccionista.framework.value.Values.stringProcess;

@TestInstance(Lifecycle.PER_METHOD)
@Execution(ExecutionMode.CONCURRENT)
@ExtendWith(PerfeccionistaExtension.class)
@UseEnvironment(PagefactoryWebSeleniumEnvironmentConfiguration.class)
public abstract class AbstractWebSeleniumParallelTest {

    // Вынести в экстеншн + написать свой форматтер для красивого вывода
    static {
        try {
            LogManager.getLogManager().readConfiguration(AbstractWebSeleniumParallelTest.class.getClassLoader().getResourceAsStream("logging.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected WebPageContext initWebPageContext() {
        return openDefaultBrowser().getWebPageContext();                                  // Возвращаем контекст страницы для активного браузера
    }

    protected WebBrowserDispatcher openDefaultBrowser() {
        return openBrowser("${[config] browser}");
    }

    protected WebBrowserDispatcher openBrowser(@NotNull String browser) {
        Environment environment = Environment.getCurrent();
        String browserName = stringEquals(browser).get();
        String startUrl = stringProcess("${[config] start_url}");
        // Создаем окружение для выполнения теста (браузер, начальный УРЛ, контекст страницы)
        WebBrowserDispatcher webBrowserDispatcher = environment.getService(WebBrowserService.class)
                .createDispatcher(browserName)                      // создаем диспетчер для вебдрайвера
                .launch();                                          // запускаем браузер
        webBrowserDispatcher.window()
                .setOuterWindowSize(Dimensions2D.of(1200, 1000));           // Возвращаем контекст страницы для активного браузера
        webBrowserDispatcher.tabs()
                .openUrl(startUrl);                                 // открываем ссылку
        return webBrowserDispatcher;
    }

}
