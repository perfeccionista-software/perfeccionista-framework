package io.perfeccionista.framework.pagefactory;

import io.perfeccionista.framework.Environment;
import io.perfeccionista.framework.UseEnvironmentConfiguration;
import io.perfeccionista.framework.extension.PerfeccionistaExtension;
import io.perfeccionista.framework.pagefactory.browser.WebBrowserDispatcher;
import io.perfeccionista.framework.pagefactory.browser.WebBrowserService;
import io.perfeccionista.framework.pagefactory.browser.context.WebPageContext;
import io.perfeccionista.framework.pagefactory.configurations.TestEnvironmentConfiguration;
import io.perfeccionista.framework.value.ValueService;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(PerfeccionistaExtension.class)
@UseEnvironmentConfiguration(TestEnvironmentConfiguration.class)
public class AbstractUiTest {
    public static final String FLUENT = "Fluent";
    public static final String EXTERNAL = "External";

    protected WebPageContext initWebPageContext(Environment env, ValueService value) {
        String browserName = value.stringProcess("${[props] browser}");
        String startUrl = value.stringProcess("${[props] start_url}");
        // Создаем окружение для выполнения теста (браузер, начальный УРЛ, контекст страницы)
        WebBrowserDispatcher webBrowserDispatcher = env.getService(WebBrowserService.class)
                .createDispatcher(browserName)                      // создаем диспетчер для вебдрайвера
                .launch();                                          // запускаем браузер
        webBrowserDispatcher
                .tabs()
                .openUrl(startUrl);                                 // открываем ссылку
        return webBrowserDispatcher
                .getPageContext();                                  // Возвращаем контекст страницы для активного браузера
    }

}
