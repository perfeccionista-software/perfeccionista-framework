package io.perfeccionista.framework.pagefactory.web;

import io.perfeccionista.framework.Environment;
import io.perfeccionista.framework.extension.PerfeccionistaExtension;
import io.perfeccionista.framework.pagefactory.driver.WebDriverService;
import io.perfeccionista.framework.pagefactory.driver.context.WebPageContext;
import io.perfeccionista.framework.value.number.integer.RuIntegerValue;
import io.perfeccionista.framework.value.string.RuStringValue;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(PerfeccionistaExtension.class)
// TODO: Environment configuration
public class AbstractElementTest {

    protected WebPageContext initWebPageContext(Environment env) {
        RuStringValueProvider value = new RuStringValueProvider(env);
        String browserName = value.of("${[props] browser}").get();
        String startUrl = value.of("${[props] startPageUrl}").get();
        // Создаем окружение для выполнения теста (браузер, начальный УРЛ, контекст страницы)
        return env.getService(WebDriverService.class).launch(browserName)                           // запускаем вебдрайвер
                .getDriverInstance().openUrl(startUrl)                                              // открываем ссылку
                .getPageContext();
    }

    protected static class RuStringValueProvider {
        private final Environment environment;
        public RuStringValueProvider(Environment environment) {
            this.environment = environment;
        }
        public RuStringValue of(String value) {
            return RuStringValue.of(environment, value);
        }
    }

    protected static class RuIntegerValueProvider {
        private final Environment environment;
        public RuIntegerValueProvider(Environment environment) {
            this.environment = environment;
        }
        public RuIntegerValue of(String value) {
            return RuIntegerValue.of(environment, value);
        }
    }

}
