package io.perfeccionista.framework.pagefactory.web;

import io.perfeccionista.framework.Environment;
import io.perfeccionista.framework.extension.PerfeccionistaExtension;
import io.perfeccionista.framework.pagefactory.driver.WebDriverService;
import io.perfeccionista.framework.pagefactory.driver.context.WebPageContext;
import io.perfeccionista.framework.value.ValueService;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(PerfeccionistaExtension.class)
// TODO: Environment configuration
public class AbstractElementTest {
    public static final String FLUENT = "Fluent";
    public static final String EXTERNAL = "External";

    protected WebPageContext initWebPageContext(Environment env, ValueService value) {
        String browserName = value.stringProcess("${[props] browser}");
        String startUrl = value.stringProcess("${[props] startPageUrl}");
        // Создаем окружение для выполнения теста (браузер, начальный УРЛ, контекст страницы)
        return env.getService(WebDriverService.class).launch(browserName)                           // запускаем вебдрайвер
                .getDriverInstance().openUrl(startUrl)                                              // открываем ссылку
                .getPageContext();
    }

}
