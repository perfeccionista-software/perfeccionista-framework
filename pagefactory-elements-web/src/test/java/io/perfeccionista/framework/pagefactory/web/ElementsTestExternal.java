package io.perfeccionista.framework.pagefactory.web;

import io.perfeccionista.framework.Environment;
import io.perfeccionista.framework.pagefactory.driver.context.WebPageContext;
import io.perfeccionista.framework.pagefactory.elements.WebRadioButton;
import io.perfeccionista.framework.pagefactory.filter.MultipleResult;
import io.perfeccionista.framework.pagefactory.web.pageobjects.ElementsPage;
import io.perfeccionista.framework.pagefactory.web.pageobjects.HomePage;
import io.perfeccionista.framework.value.ValueService;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import static io.perfeccionista.framework.action.wrappers.LogicActionWrapper.runLogic;
import static io.perfeccionista.framework.asserts.WebElementsAsserts.assertDisplayed;
import static io.perfeccionista.framework.asserts.WebElementsAsserts.assertElementLabel;
import static io.perfeccionista.framework.asserts.WebElementsAsserts.assertElementProperty;
import static io.perfeccionista.framework.asserts.WebElementsAsserts.assertElementSize;
import static io.perfeccionista.framework.asserts.WebElementsAsserts.assertElementText;
import static io.perfeccionista.framework.asserts.WebElementsAsserts.assertEnabled;
import static io.perfeccionista.framework.asserts.WebElementsAsserts.assertNotSelected;
import static io.perfeccionista.framework.asserts.WebElementsAsserts.assertSelected;
import static io.perfeccionista.framework.pagefactory.extractor.WebExtractors.element;
import static io.perfeccionista.framework.pagefactory.extractor.WebExtractors.labelValue;
import static io.perfeccionista.framework.pagefactory.extractor.WebExtractors.selectedMark;
import static io.perfeccionista.framework.pagefactory.filter.WebConditions.enabled;
import static io.perfeccionista.framework.pagefactory.filter.WebConditions.label;
import static io.perfeccionista.framework.pagefactory.filter.WebConditions.radioButtonIndex;
import static io.perfeccionista.framework.pagefactory.filter.WebConditions.selected;
import static io.perfeccionista.framework.pagefactory.filter.WebFilters.with;
import static io.perfeccionista.framework.pagefactory.filter.WebFilters.without;

public class ElementsTestExternal extends AbstractElementTest {

    /**
     * Case: Проверяем что присутствует левое меню
     *  Проверяем текст заголовка
     *  Проверяем основной текст
     * @param env - Экземпляр окружения теста
     * @param value - Экземпляр сервиса значений
     */
    @Test
    @Tag(EXTERNAL)
    void webTextBlockTestExternal(Environment env, ValueService value) {
        HomePage homePage = initWebPageContext(env, value).getPage(HomePage.class);
        assertDisplayed(homePage.leftMenu());
        assertElementText(value.stringEquals("текст"), homePage.contentTitle());
        assertElementText(value.stringContains("текст"), homePage.content());
    }

    /**
     * Case: Проверяем текст кнопки
     *  Кликаем по кнопке
     *  Проверяем, что по событию клика появился текст
     * @param env - Экземпляр окружения теста
     * @param value - Экземпляр сервиса значений
     */
    @Test @Tag(EXTERNAL)
    void webButtonTestExternal(Environment env, ValueService value) {
        WebPageContext pc = initWebPageContext(env, value);                                                // устанавливаем контекст страницы
        HomePage homePage = pc.getPage(HomePage.class);
        homePage.leftMenu()
                .select(value.stringEquals("Elements"));

        ElementsPage elementsPage = pc.getPage(ElementsPage.class);
        assertElementText(value.stringEquals("Button"), elementsPage.simpleButton());
        elementsPage.simpleButton().click();
        assertElementText(value.stringEquals("Simple button clicked"), elementsPage.textBlockForSimpleButton());
    }

    /**
     * Case: Проверяем текст ссылки
     *  Кликаем по ссылке
     *  Проверяем, что по событию клика появился текст
     * @param env - Экземпляр окружения теста
     * @param value - Экземпляр сервиса значений
     */
    @Test @Tag(EXTERNAL)
    void webLinkTestExternal(Environment env, ValueService value) {
        WebPageContext pc = initWebPageContext(env, value);                                                // устанавливаем контекст страницы
        HomePage homePage = pc.getPage(HomePage.class);
        homePage.leftMenu()
                .select(value.stringEquals("Elements"));

        ElementsPage elementsPage = pc.getPage(ElementsPage.class);
        assertElementText(value.stringEquals("Link"), elementsPage.simpleLink());
        elementsPage.simpleLink().click();
        assertElementText(value.stringEquals("Simple link clicked"), elementsPage.textBlockForSimpleLink());
    }

    /**
     * Case: Нужно навести курсор на первую кнопку
     *  При наведении на кнопку появляется ссылка
     *  Переводим курсор с кнопки на ссылку и кликаем по ней
     *  Проверяем, что по событию клика появился текст
     * Оборачивание в runLogic нужно для стабильности.
     * Например, в момент наведения отработал рест и обновил страницу - наведение пропало.
     * Нужно повторить всю последовательность с выводом курсора за границы первого элемента.
     * @param env - Экземпляр окружения теста
     * @param value - Экземпляр сервиса значений
     */
    @Test @Tag(EXTERNAL)
    void webButtonHoverTestExternal(Environment env, ValueService value) {
        WebPageContext pc = initWebPageContext(env, value);                                                // устанавливаем контекст страницы
        HomePage homePage = pc.getPage(HomePage.class);
        homePage.leftMenu()
                .select(value.stringEquals("Elements"));

        ElementsPage elementsPage = pc.getPage(ElementsPage.class);
        runLogic(env, () -> {
            elementsPage.buttonWithHover()
                    .hoverTo(true);
            elementsPage.linkForButtonWithHover()
                    .hoverTo(false)
                    .click();
        });
        assertElementText(value.stringEquals("Link for hover clicked"), elementsPage.textBlockForLinkForButtonWithHover());
    }

    /**
     * Case: Проверяем лейбл поля ввода
     *  Проверяем что поле ввода доступно для ввода (эту проверку можно добавить в сам элемент и она будет выполняться при каждом обращении)
     *  Кликаем в поле (эту проверку можно добавить в сам элемент и она будет выполняться при каждом обращении)
     *  Проверяем свойство "плейсхолдер" у поля
     *  Проверяем что в поле ничего не введено
     *  Вводим в поле текст
     *  Проверяем введенный текст
     *  Очищаем введенный текст
     *  Проверяем что поле очистилось
     *  Вводим в поле текст
     *  Проверяем введенный текст
     *  Нажимаем 'Enter' на поле
     *  Проверяем, что по событию нажатия 'Enter' появился текст
     * @param env - Экземпляр окружения теста
     * @param value - Экземпляр сервиса значений
     */
    @Test @Tag(EXTERNAL)
    void webTextInputTest(Environment env, ValueService value) {
        WebPageContext pc = initWebPageContext(env, value);                                                // устанавливаем контекст страницы
        HomePage homePage = pc.getPage(HomePage.class);
        homePage.leftMenu()
                .select(value.stringEquals("Elements"));

        ElementsPage elementsPage = pc.getPage(ElementsPage.class);
        assertElementLabel(value.stringEquals("Name"), elementsPage.simpleTextInput());
        assertEnabled(elementsPage.simpleTextInput());
        elementsPage.simpleTextInput().click();
        assertElementProperty("плейсхолдер", value.stringEquals("Your name"), elementsPage.simpleTextInput());
        assertElementText(value.stringEmpty(), elementsPage.simpleTextInput());
        elementsPage.simpleTextInput().sendKeys("Jack White");
        assertElementText(value.stringEquals("Jack White"), elementsPage.simpleTextInput());
        elementsPage.simpleTextInput().clear();
        assertElementText(value.stringEmpty(), elementsPage.simpleTextInput());
        elementsPage.simpleTextInput().sendKeys("Jack Black");
        assertElementText(value.stringEquals("Jack Black"), elementsPage.simpleTextInput());
        elementsPage.simpleTextInput().sendKeys(Keys.ENTER);
        assertElementText(value.stringEquals("Jack Black"), elementsPage.textBlockForSimpleTextInput());
    }

    /**
     * Case: Проверяем модифицированное поле ввода (текст хранится не в value, а в атрибуте title)
     *  Проверяем что в поле ничего не введено
     *  Вводим в поле текст
     *  Проверяем введенный текст
     *  Очищаем введенный текст
     *  Проверяем что поле очистилось
     *  Вводим в поле текст
     *  Проверяем введенный текст
     * Модифицированное поле ввода (текст хранится в атрибуте Title). Для теста изменений логики нет.
     * @param env - Экземпляр окружения теста
     * @param value - Экземпляр сервиса значений
     */
    @Test @Tag(EXTERNAL)
    void webModifiedTextInputTest(Environment env, ValueService value) {
        WebPageContext pc = initWebPageContext(env, value);                                                // устанавливаем контекст страницы
        HomePage homePage = pc.getPage(HomePage.class);
        homePage.leftMenu()
                .select(value.stringEquals("Elements"));

        ElementsPage elementsPage = pc.getPage(ElementsPage.class);
        assertElementText(value.stringEmpty(), elementsPage.customTextInput());
        elementsPage.customTextInput().sendKeys("Jack White");
        assertElementText(value.stringEquals("Jack White"), elementsPage.customTextInput());
        elementsPage.customTextInput().clear();
        assertElementText(value.stringEmpty(), elementsPage.customTextInput());
        elementsPage.customTextInput().sendKeys("Jack Black");
        assertElementText(value.stringEquals("Jack Black"), elementsPage.customTextInput());
    }

    /**
     * Case: Проверяем лейбл у чекбокса
     *  Проверяем что чекбокс доступен для нажатия
     *  Проверяем что чекбокс не выделен
     *  Кликаем по чекбоксу
     *  Проверяем что чекбокс выделен
     *  Проверяем что по событию выделения чекбокса появился текст
     * @param env - Экземпляр окружения теста
     * @param value - Экземпляр сервиса значений
     */
    @Test @Tag(EXTERNAL)
    void webCheckboxTest(Environment env, ValueService value) {
        WebPageContext pc = initWebPageContext(env, value);                                                // устанавливаем контекст страницы
        HomePage homePage = pc.getPage(HomePage.class);
        homePage.leftMenu()
                .select(value.stringEquals("Elements"));

        ElementsPage elementsPage = pc.getPage(ElementsPage.class);
        assertElementLabel(value.stringEquals("Accept"), elementsPage.simpleCheckbox());
        assertEnabled(elementsPage.simpleCheckbox());
        assertNotSelected(elementsPage.simpleCheckbox());
        elementsPage.simpleCheckbox().click();
        assertSelected(elementsPage.simpleCheckbox());
        assertElementText(value.stringEquals("Checkbox selected"), elementsPage.textBlockForSimpleCheckbox());
    }

    /**
     * Case: Проверяем что радио-кнопка выделена по трем разным условиям выбора (по признаку, лейблу, индексу)
     *  Проверяем лейбл у выделенной радио-кнопки
     *  Проверяем количество радио-кнопок в группе
     *  Кликаем по радио-кнопке с лейблом 'Label 2'
     *  Проверяем что радио-кнопка с указанным индексом выделена
     *  Проверяем что по событию выделения радио-кнопки появился текст
     *  Кликаем по радио-кнопке с индексом равным 3
     *  Проверяем что радио-кнопка с указанным лейблом выделена
     *  Проверяем что по событию выделения радио-кнопки изменился текст
     * @param env - Экземпляр окружения теста
     * @param value - Экземпляр сервиса значений
     */
    @Test @Tag(EXTERNAL)
    void webRadioGroupTest(Environment env, ValueService value) {
        WebPageContext pc = initWebPageContext(env, value);                                                // устанавливаем контекст страницы
        HomePage homePage = pc.getPage(HomePage.class);
        homePage.leftMenu()
                .select(value.stringEquals("Elements"));

        ElementsPage elementsPage = pc.getPage(ElementsPage.class);
        assertDisplayed(elementsPage.simpleRadioGroup());
        assertSelected(elementsPage.simpleRadioGroup().getSelected());
        assertSelected(elementsPage.simpleRadioGroup().getByLabel(value.stringEquals("Label 1")));
        assertSelected(elementsPage.simpleRadioGroup().getByIndex(value.intEquals(1)));
        assertElementLabel(value.stringEquals("Label 1"), elementsPage.simpleRadioGroup().getSelected());
        assertElementSize(value.intEquals(3), elementsPage.simpleRadioGroup());
        elementsPage.simpleRadioGroup().getByLabel(value.stringEquals("Label 2")).click();
        assertSelected(elementsPage.simpleRadioGroup().getByIndex(value.intEquals(2)));
        assertElementText(value.stringEquals("Label 2 selected"), elementsPage.textBlockForSimpleRadioGroup());
        elementsPage.simpleRadioGroup().getByIndex(value.intEquals(3)).click();
        assertSelected(elementsPage.simpleRadioGroup().getByLabel(value.stringEquals("Label 3")));
        assertElementText(value.stringEquals("Label 3 selected"), elementsPage.textBlockForSimpleRadioGroup());

        // Case: Получить все лейблы из всех радио-кнопок
        MultipleResult<String> radioButtonLabelsResult = elementsPage.simpleRadioGroup()
                .extractAll(labelValue());
        // Case: Получить все лейблы из радио-кнопок, которые не выбраны
        MultipleResult<String> filteredRadioButtonLabelsResult = elementsPage.simpleRadioGroup()
                .filter(without(selected()))
                .extractAll(labelValue());
        // Case: Получить все признаки выделения из всех радио-кнопок
        MultipleResult<Boolean> radioButtonSelectedResult = elementsPage.simpleRadioGroup()
                .extractAll(selectedMark());
        // Case: Получить все признаки выделения из радио-кнопок, лейблы которых содержат 'Label'
        MultipleResult<Boolean> filteredByLabelRadioButtonSelectedResult = elementsPage.simpleRadioGroup()
                .filter(with(label(value.stringContains("Label"))))
                .extractAll(selectedMark());
        // Case: Получить все признаки выделения из радио-кнопок, с индексами больше 1
        MultipleResult<Boolean> filteredByIndexRadioButtonSelectedResult = elementsPage.simpleRadioGroup()
                .filter(with(radioButtonIndex(value.intGreaterThan(1))))
                .extractAll(selectedMark());
        // Case: Получить все экземпляры RadioButton
        MultipleResult<WebRadioButton> radioButtonsResult = elementsPage.simpleRadioGroup()
                .extractAll(element());
        // Case: Получить все экземпляры RadioButton, которые доступны для выделения
        MultipleResult<WebRadioButton> filteredRadioButtonsResult = elementsPage.simpleRadioGroup()
                .filter(with(enabled()))
                .extractAll(element());
    }

    /**
     * Case: Проверяем лейбл у элемента загрузки файлов
     *  Проверяем что элемент загрузки файлов доступен для работы
     *  Проверяем что в элемент ничего не введено
     *  Вводим в элемент имя файла, заданное в источнике данных с именем [props]
     *  Проверяем введенное имя файла
     *  Очищаем введенное имя файла
     *  Проверяем что в элемент ничего не введено
     *  Вводим в элемент имя файла, заданное в источнике данных с именем [props]
     *  Проверяем введенное имя файла
     *  Загружаем файл
     * @param env - Экземпляр окружения теста
     * @param value - Экземпляр сервиса значений
     */
    @Test @Tag(EXTERNAL)
    void webFileInputTest(Environment env, ValueService value) {
        WebPageContext pc = initWebPageContext(env, value);                                                // устанавливаем контекст страницы
        HomePage homePage = pc.getPage(HomePage.class);
        homePage.leftMenu()
                .select(value.stringEquals("Elements"));

        ElementsPage elementsPage = pc.getPage(ElementsPage.class);
        assertElementLabel(value.stringEquals("Export"), elementsPage.simpleFileInput());
        assertEnabled(elementsPage.simpleFileInput());
        assertElementText(value.stringEmpty(), elementsPage.simpleFileInput());
        elementsPage.simpleFileInput().sendKeys(value.stringProcess("${[props] localFilename}"));
        assertElementText(value.stringEquals("${[props] localFilename}"), elementsPage.simpleFileInput());
        elementsPage.simpleFileInput().clear();
        assertElementText(value.stringEmpty(), elementsPage.simpleFileInput());
        elementsPage.simpleFileInput().sendKeys(value.stringProcess("${[props] localFilename}"));
        assertElementText(value.stringEquals("${[props] localFilename}"), elementsPage.simpleFileInput());
        elementsPage.simpleFileInput().submit();
    }

}
