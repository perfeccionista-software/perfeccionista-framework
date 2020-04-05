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

import java.time.Duration;

import static io.perfeccionista.framework.action.wrappers.LogicActionWrapper.runLogic;
import static io.perfeccionista.framework.asserts.FileActions.deleteFileIfExists;
import static io.perfeccionista.framework.asserts.FileAsserts.assertFileExists;
import static io.perfeccionista.framework.pagefactory.extractor.WebExtractors.element;
import static io.perfeccionista.framework.pagefactory.extractor.WebExtractors.labelValue;
import static io.perfeccionista.framework.pagefactory.extractor.WebExtractors.selectedMark;
import static io.perfeccionista.framework.pagefactory.filter.WebConditions.enabled;
import static io.perfeccionista.framework.pagefactory.filter.WebConditions.label;
import static io.perfeccionista.framework.pagefactory.filter.WebConditions.radioButtonIndex;
import static io.perfeccionista.framework.pagefactory.filter.WebConditions.selected;
import static io.perfeccionista.framework.pagefactory.filter.WebFilters.with;
import static io.perfeccionista.framework.pagefactory.filter.WebFilters.without;

/**
 * TODO: Добавить проверки
 *  State
 *  Bounds
 *  Screenshot
 */
public class ElementsTest extends AbstractElementTest {

    /**
     * Case: Проверяем что присутствует левое меню
     *  Проверяем текст заголовка
     *  Проверяем основной текст
     * @param env - Экземпляр окружения теста
     * @param value - Экземпляр сервиса значений
     */
    @Test @Tag(FLUENT)
    void webTextBlockTest(Environment env, ValueService value) {
        HomePage homePage = initWebPageContext(env, value).getPage(HomePage.class);
        homePage.leftMenu()
                .shouldBeDisplayed();
        homePage.contentTitle()
                .shouldHaveText(value.stringEquals("текст"));
        homePage.content()
                .shouldHaveText(value.stringContains("текст"));
    }

    /**
     * Case: Проверяем текст кнопки
     *  Кликаем по кнопке
     *  Проверяем, что по событию клика появился текст
     * @param env - Экземпляр окружения теста
     * @param value - Экземпляр сервиса значений
     */
    @Test @Tag(FLUENT)
    void webButtonTest(Environment env, ValueService value) {
        WebPageContext pc = initWebPageContext(env, value);                                                // устанавливаем контекст страницы
        HomePage homePage = pc.getPage(HomePage.class);
        homePage.leftMenu()
                .select(value.stringEquals("Elements"));

        ElementsPage elementsPage = pc.getPage(ElementsPage.class);
        elementsPage.simpleButton()
                .shouldHaveText(value.stringEquals("Button"))
                .click();
        elementsPage.textBlockForSimpleButton()
                .shouldHaveText(value.stringEquals("Simple button clicked"));
    }

    /**
     * Case: Проверяем текст ссылки
     *  Кликаем по ссылке
     *  Проверяем, что по событию клика появился текст
     * @param env - Экземпляр окружения теста
     * @param value - Экземпляр сервиса значений
     */
    @Test @Tag(FLUENT)
    void webLinkTest(Environment env, ValueService value) {
        WebPageContext pc = initWebPageContext(env, value);                                                // устанавливаем контекст страницы
        HomePage homePage = pc.getPage(HomePage.class);
        homePage.leftMenu()
                .select(value.stringEquals("Elements"));

        ElementsPage elementsPage = pc.getPage(ElementsPage.class);
        elementsPage.simpleLink()
                .shouldHaveText(value.stringEquals("Link"))
                .click();
        elementsPage.textBlockForSimpleLink()
                .shouldHaveText(value.stringEquals("Simple link clicked"));
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
    @Test @Tag(FLUENT)
    void webButtonHoverTest(Environment env, ValueService value) {
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
        elementsPage.textBlockForLinkForButtonWithHover()
                .shouldHaveText(value.stringEquals("Link for hover clicked"));
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
    @Test @Tag(FLUENT)
    void webTextInputTest(Environment env, ValueService value) {
        WebPageContext pc = initWebPageContext(env, value);                                                // устанавливаем контекст страницы
        HomePage homePage = pc.getPage(HomePage.class);
        homePage.leftMenu()
                .select(value.stringEquals("Elements"));

        ElementsPage elementsPage = pc.getPage(ElementsPage.class);
        elementsPage.simpleTextInput()
                .shouldHaveLabel(value.stringEquals("Name"))
                .shouldBeEnabled()
                .click()
                .shouldHavePropertyValue("плейсхолдер", value.stringEquals("Your name"))
                .shouldHaveText(value.stringEmpty())
                .sendKeys("Jack White")
                .shouldHaveText(value.stringEquals("Jack White"))
                .clear()
                .shouldHaveText(value.stringEmpty())
                .sendKeys("Jack Black")
                .shouldHaveText(value.stringEquals("Jack Black"))
                .sendKeys(Keys.ENTER);
        elementsPage.textBlockForSimpleTextInput()
                .shouldHaveText(value.stringEquals("Jack Black"));
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
    @Test @Tag(FLUENT)
    void webModifiedTextInputTest(Environment env, ValueService value) {
        WebPageContext pc = initWebPageContext(env, value);                                                // устанавливаем контекст страницы
        HomePage homePage = pc.getPage(HomePage.class);
        homePage.leftMenu()
                .select(value.stringEquals("Elements"));

        ElementsPage elementsPage = pc.getPage(ElementsPage.class);
        elementsPage.customTextInput()
                .shouldHaveText(value.stringEmpty())
                .sendKeys("Jack White")
                .shouldHaveText(value.stringEquals("Jack White"))
                .clear()
                .shouldHaveText(value.stringEmpty())
                .sendKeys("Jack Black")
                .shouldHaveText(value.stringEquals("Jack Black"));
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
    @Test @Tag(FLUENT)
    void webCheckboxTest(Environment env, ValueService value) {
        WebPageContext pc = initWebPageContext(env, value);                                                // устанавливаем контекст страницы
        HomePage homePage = pc.getPage(HomePage.class);
        homePage.leftMenu()
                .select(value.stringEquals("Elements"));

        ElementsPage elementsPage = pc.getPage(ElementsPage.class);
        elementsPage.simpleCheckbox()
                .shouldHaveLabel(value.stringEquals("Accept"))
                .shouldBeEnabled()
                .shouldNotBeSelected()
                .click()
                .shouldBeSelected();
        elementsPage.textBlockForSimpleCheckbox()
                .shouldHaveText(value.stringEquals("Checkbox selected"));
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
    @Test @Tag(FLUENT)
    void webRadioGroupTest(Environment env, ValueService value) {
        WebPageContext pc = initWebPageContext(env, value);                                                // устанавливаем контекст страницы
        HomePage homePage = pc.getPage(HomePage.class);
        homePage.leftMenu()
                .select(value.stringEquals("Elements"));

        ElementsPage elementsPage = pc.getPage(ElementsPage.class);
        elementsPage.simpleRadioGroup()
                .shouldBeDisplayed()
                .getSelected().shouldBeSelected();
        elementsPage.simpleRadioGroup()
                .getByLabel(value.stringEquals("Label 1")).shouldBeSelected();
        elementsPage.simpleRadioGroup()
                .getByIndex(value.intEquals(1)).shouldBeSelected();
        elementsPage.simpleRadioGroup()
                .getSelected().shouldHaveLabel(value.stringEquals("Label 1"));
        elementsPage.simpleRadioGroup()
                .shouldHaveSize(value.intEquals(3))
                .getByLabel(value.stringEquals("Label 2")).click();
        elementsPage.simpleRadioGroup()
                .getByIndex(value.intEquals(2)).shouldBeSelected();
        elementsPage.textBlockForSimpleRadioGroup()
                .shouldHaveText(value.stringEquals("Label 2 selected"));
        elementsPage.simpleRadioGroup()
                .getByIndex(value.intEquals(3)).click();
        elementsPage.simpleRadioGroup()
                .getByLabel(value.stringEquals("Label 3")).shouldBeSelected();
        elementsPage.textBlockForSimpleRadioGroup()
                .shouldHaveText(value.stringEquals("Label 3 selected"));

        // Case: Получить все лейблы из всех радио-кнопок
        MultipleResult<String> radioButtonLabelsResult = elementsPage.simpleRadioGroup()
                .extractAll(labelValue());

        // Case: Получить все лейблы из радио-кнопок, которые не выбраны
        MultipleResult<String> filteredRadioButtonLabelsResult = elementsPage.simpleRadioGroup()
                .filter(without(selected()))
                .extractAll(labelValue());

        // Case: Проверить количество не выбранных элементов
        elementsPage.simpleRadioGroup()
                .filter(without(selected()))
                .shouldHaveSize(value.intEquals(2));

        // Case: Получить все признаки выделения из всех радио-кнопок
        MultipleResult<Boolean> radioButtonSelectedResult = elementsPage.simpleRadioGroup()
                .extractAll(selectedMark());

        // Case: Получить все признаки выделения из радио-кнопок, лейблы которых содержат 'Label' и проверить количество результатов
        MultipleResult<Boolean> filteredByLabelRadioButtonSelectedResult = elementsPage.simpleRadioGroup()
                .filter(with(label(value.stringContains("Label"))))
                .shouldHaveSize(value.intEquals(3))
                .extractAll(selectedMark());

        // Case: Получить все признаки выделения из радио-кнопок, с индексами больше 1
        MultipleResult<Boolean> filteredByIndexRadioButtonSelectedResult = elementsPage.simpleRadioGroup()
                .filter(with(radioButtonIndex(value.intGreaterThan(1))))
                .extractAll(selectedMark());

        // Case: Получить все экземпляры RadioButton и проверить их количество
        MultipleResult<WebRadioButton> radioButtonsResult = elementsPage.simpleRadioGroup()
                .extractAll(element())
                .shouldHaveSize(value.intEquals(3));

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
    @Test @Tag(FLUENT)
    void webFileInputTest(Environment env, ValueService value) {
        WebPageContext pc = initWebPageContext(env, value);                                                // устанавливаем контекст страницы
        HomePage homePage = pc.getPage(HomePage.class);
        homePage.leftMenu()
                .select(value.stringEquals("Elements"));

        ElementsPage elementsPage = pc.getPage(ElementsPage.class);
        elementsPage.simpleFileInput()
                .shouldHaveLabel(value.stringEquals("Export"))
                .shouldBeEnabled()
                .shouldHaveText(value.stringEmpty())
                .sendKeys(value.stringProcess("${[props] localFilename}"))
                .shouldHaveText(value.stringEquals("${[props] localFilename}"))
                .clear()
                .shouldHaveText(value.stringEmpty())
                .sendKeys(value.stringProcess("${[props] localFilename}"))
                .shouldHaveText(value.stringEquals("${[props] localFilename}"))
                .submit();
    }

    /**
     * Case: Удаляем файл с локальной директории (если он там существует)
     *  Кликаем на ссылку загрузки файла на локальную станцию
     *  Увеличиваем таймаут для компенсации времени скачивания
     *   (опциональный флаг, если нужно проверить файл, время скачивания которого больше таймаута по умолчанию)
     *  Проверяем что файл загружен в директорию загрузки файлов
     * @param env - Экземпляр окружения теста
     * @param value - Экземпляр сервиса значений
     */
    @Test @Tag(FLUENT)
    void webFileDownloadTest(Environment env, ValueService value) {
        WebPageContext pc = initWebPageContext(env, value);                                                // устанавливаем контекст страницы
        HomePage homePage = pc.getPage(HomePage.class);
        homePage.leftMenu()
                .select(value.stringEquals("Elements"));

        ElementsPage elementsPage = pc.getPage(ElementsPage.class);
        deleteFileIfExists(value.stringProcess("${[props] downloadedFileName}"));
        elementsPage.downloadLink()
                .click();
        assertFileExists(value.stringProcess("${[props] downloadedFileName}"), Duration.ofSeconds(30));
    }

}