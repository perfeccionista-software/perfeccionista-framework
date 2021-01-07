package io.perfeccionista.framework;

import io.perfeccionista.framework.extension.PerfeccionistaExtension;
import io.perfeccionista.framework.pagefactory.browser.WebBrowserDispatcher;
import io.perfeccionista.framework.pagefactory.browser.WebBrowserService;
import io.perfeccionista.framework.pagefactory.configurations.PagefactoryWebSeleniumEnvironmentConfiguration;
import io.perfeccionista.framework.pagefactory.context.base.WebPageContext;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;

import static io.perfeccionista.framework.value.Values.stringEquals;
import static io.perfeccionista.framework.value.Values.stringProcess;

@TestInstance(Lifecycle.PER_METHOD)
@Execution(ExecutionMode.CONCURRENT)
@ExtendWith(PerfeccionistaExtension.class)
@UseEnvironment(PagefactoryWebSeleniumEnvironmentConfiguration.class)
public abstract class AbstractWebSeleniumParallelTest {

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
                .setOuterWindowSize(1200, 1000);           // Возвращаем контекст страницы для активного браузера
        webBrowserDispatcher.tabs()
                .openUrl(startUrl);                                 // открываем ссылку
        return webBrowserDispatcher;
    }

}
