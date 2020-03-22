package io.perfeccionista.framework.pagefactory.web;

import io.perfeccionista.framework.Environment;
import io.perfeccionista.framework.pagefactory.driver.WebDriverService;
import io.perfeccionista.framework.pagefactory.driver.context.WebPageContext;
import io.perfeccionista.framework.pagefactory.driver.context.WebSearchContext;
import io.perfeccionista.framework.pagefactory.web.pageobjects.HomePage;
import org.junit.jupiter.api.Test;

import static io.perfeccionista.framework.asserts.WebElementsAsserts.assertDisplayed;
import static io.perfeccionista.framework.asserts.WebElementsAsserts.assertElementText;

/**
 * TODO: Описать человеческим языком кейсы проверки
 */
public class SearchContextTest extends AbstractElementTest {

    @Test
    void webTextBlockTest(Environment env) {
        RuStringValueProvider value = new RuStringValueProvider(env);
        RuIntegerValueProvider intValue = new RuIntegerValueProvider(env);
        WebPageContext pc = initWebPageContext(env).usePage(HomePage.class);                                                // устанавливаем контекст страницы

        // Работа с элементами через контекст поиска
        WebSearchContext sc = pc.getSearchContext();
        assertDisplayed(sc.displayableElement("Основное меню"));
        assertElementText(value.of("текст"), sc.textElement("текст Заголовок"));
        assertElementText(value.of("[подстрока] текст"), sc.textElement("текст Описание"));

        env.getService(WebDriverService.class).closeAll();
    }

}
