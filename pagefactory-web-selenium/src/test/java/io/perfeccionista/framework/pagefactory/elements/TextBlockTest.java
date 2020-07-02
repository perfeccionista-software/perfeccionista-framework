package io.perfeccionista.framework.pagefactory.elements;

import io.perfeccionista.framework.Environment;
import io.perfeccionista.framework.attachment.JsonAttachmentEntry;
import io.perfeccionista.framework.attachment.StringAttachmentEntry;
import io.perfeccionista.framework.exceptions.ElementTextValueException;
import io.perfeccionista.framework.pagefactory.AbstractUiTest;
import io.perfeccionista.framework.pagefactory.pageobjects.HomePage;
import io.perfeccionista.framework.value.ValueService;
import io.perfeccionista.framework.value.string.StringValue;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static io.perfeccionista.framework.exceptions.base.ExceptionType.ASSERT;

public class TextBlockTest extends AbstractUiTest {

    /**
     * Case: Проверяем что присутствует левое меню
     *  Проверяем текст заголовка
     *  Проверяем основной текст
     * @param env - Экземпляр окружения теста
     * @param value - Экземпляр сервиса значений
     */
    @Test
    @Tag(FLUENT)
    void webTextBlockTest(Environment env, ValueService value) throws InterruptedException {
        HomePage homePage = initWebPageContext(env, value).getPage(HomePage.class);
        homePage.contentTitle()
                .shouldBeDisplayed()
                .shouldHaveText(value.stringEquals("Государства мира"))
                // custom assert
                .should(contentTitle -> {
                    StringValue expected = value.stringEquals("Государства мира");
                    if (!expected.check(((WebTextBlock) contentTitle).getText())) {
                        throw new ElementTextValueException("Значение элемента не соответствует ожидаемому")
                                .setType(ASSERT)
                                .setProcessed(true)
                                .addAttachmentEntry(JsonAttachmentEntry.of("Element", contentTitle.toJson()))
                                .addAttachmentEntry(StringAttachmentEntry.of("Values", expected.toString()));
                    }
                });
        homePage.firstTextBlock()
                .shouldHaveText(value.stringContains("Государство Палестина и Мальтийский орден"));
        homePage.leftMenu()
                .select(value.stringEquals("Elements"));
    }

}
