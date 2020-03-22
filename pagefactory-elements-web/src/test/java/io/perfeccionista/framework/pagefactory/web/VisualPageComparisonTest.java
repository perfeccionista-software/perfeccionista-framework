package io.perfeccionista.framework.pagefactory.web;

import io.perfeccionista.framework.Environment;
import io.perfeccionista.framework.pagefactory.driver.WebDriverService;
import io.perfeccionista.framework.pagefactory.driver.context.WebPageContext;
import io.perfeccionista.framework.pagefactory.web.pageobjects.HomePage;
import org.junit.jupiter.api.Test;

import static io.perfeccionista.framework.asserts.WebVisualAsserts.assertImage;

/**
 * TODO: Описать человеческим языком кейсы проверки
 */
public class VisualPageComparisonTest extends AbstractElementTest {

    // TODO: Implement
    @Test
    void visualPageComparisonTest(Environment env) {
        RuStringValueProvider value = new RuStringValueProvider(env);
        RuIntegerValueProvider intValue = new RuIntegerValueProvider(env);
        WebPageContext pc = initWebPageContext(env);                                                // устанавливаем контекст страницы

        // Работа с элементами через экземпляр страницы
        HomePage homePage = pc.getPage(HomePage.class);

        assertImage(value.of("${[props] homePageDefault}"));                                  // TODO: Понять как передавать туда вебдрайвер для снятия скриншота

        env.getService(WebDriverService.class).closeAll();
    }


}
