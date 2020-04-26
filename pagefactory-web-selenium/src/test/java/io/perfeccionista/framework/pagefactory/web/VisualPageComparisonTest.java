package io.perfeccionista.framework.pagefactory.web;

import io.perfeccionista.framework.Environment;
import io.perfeccionista.framework.pagefactory.browser.context.WebPageContext;
import io.perfeccionista.framework.pagefactory.web.pageobjects.HomePage;
import io.perfeccionista.framework.value.ValueService;
import org.junit.jupiter.api.Test;

import static io.perfeccionista.framework.asserts.WebVisualAsserts.assertImage;

/**
 * TODO: Описать человеческим языком кейсы проверки
 */
public class VisualPageComparisonTest extends AbstractElementTest {

    // TODO: Implement
    @Test
    void visualPageComparisonTest(Environment env, ValueService value) {
        WebPageContext pc = initWebPageContext(env, value);                                         // устанавливаем контекст страницы
        // Работа с элементами через экземпляр страницы
        HomePage homePage = pc.getPage(HomePage.class);
        assertImage(value.stringEquals("${[props] homePageDefault}"));                        // TODO: Понять как передавать туда вебдрайвер для снятия скриншота
    }


}
