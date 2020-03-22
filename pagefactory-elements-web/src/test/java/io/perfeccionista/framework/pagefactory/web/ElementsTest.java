package io.perfeccionista.framework.pagefactory.web;

import io.perfeccionista.framework.Environment;
import io.perfeccionista.framework.action.timeouts.CheckTimeout;
import io.perfeccionista.framework.pagefactory.driver.WebDriverService;
import io.perfeccionista.framework.pagefactory.driver.context.WebPageContext;
import io.perfeccionista.framework.pagefactory.elements.WebRadioButton;
import io.perfeccionista.framework.pagefactory.extractor.radio.WebRadioButtonExtractor;
import io.perfeccionista.framework.pagefactory.extractor.radio.WebRadioButtonLabelExtractor;
import io.perfeccionista.framework.pagefactory.extractor.radio.WebRadioButtonSelectedExtractor;
import io.perfeccionista.framework.pagefactory.filter.MultipleResult;
import io.perfeccionista.framework.pagefactory.filter.WebRadioButtonFilter;
import io.perfeccionista.framework.pagefactory.filter.radio.WebRadioButtonEnabledCondition;
import io.perfeccionista.framework.pagefactory.filter.radio.WebRadioButtonLabelValueCondition;
import io.perfeccionista.framework.pagefactory.filter.radio.WebRadioButtonSelectedCondition;
import io.perfeccionista.framework.pagefactory.web.pageobjects.ElementsPage;
import io.perfeccionista.framework.pagefactory.web.pageobjects.HomePage;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.time.Duration;

import static io.perfeccionista.framework.action.wrappers.LogicActionWrapper.runLogic;
import static io.perfeccionista.framework.asserts.FileActions.deleteFileIfExists;
import static io.perfeccionista.framework.asserts.FileAsserts.assertFileExists;
import static io.perfeccionista.framework.asserts.TimeoutActions.setDefaultTimeoutValue;
import static io.perfeccionista.framework.asserts.TimeoutActions.setTimeoutValue;
import static io.perfeccionista.framework.asserts.WebElementsAsserts.assertDisplayed;
import static io.perfeccionista.framework.asserts.WebElementsAsserts.assertEnabled;
import static io.perfeccionista.framework.asserts.WebElementsAsserts.assertElementLabel;
import static io.perfeccionista.framework.asserts.WebElementsAsserts.assertNotSelected;
import static io.perfeccionista.framework.asserts.WebElementsAsserts.assertElementProperty;
import static io.perfeccionista.framework.asserts.WebElementsAsserts.assertSelected;
import static io.perfeccionista.framework.asserts.WebElementsAsserts.assertElementSize;
import static io.perfeccionista.framework.asserts.WebElementsAsserts.assertElementText;

/**
 * TODO: Написать пример того, как сделать Environment, в котором есть методы для
 *  формирования {@link io.perfeccionista.framework.value.string.StringValue}
 *  и {@link io.perfeccionista.framework.value.number.NumberValue}
 *
 * TODO: Добавить проверки
 *  State
 *  Bounds
 *  Screenshot
 */
public class ElementsTest extends AbstractElementTest {

    @Test
    void webTextBlockTest(Environment env) {
        // TODO: Описать зачем value?
        RuStringValueProvider value = new RuStringValueProvider(env);
        WebPageContext pc = initWebPageContext(env);                                                // устанавливаем контекст страницы

        // Работа с элементами через экземпляр страницы
        HomePage homePage = pc.getPage(HomePage.class);

        // Case: Проверяем что присутствует левое меню
        //  Проверяем текст заголовка
        //  Проверяем основной текст
        assertDisplayed(homePage.leftMenu());
        assertElementText(value.of("текст"), homePage.contentTitle());
        assertElementText(value.of("[подстрока] текст"), homePage.content());

        env.getService(WebDriverService.class).closeAll();
    }

    @Test
    void webButtonTest(Environment env) {
        RuStringValueProvider value = new RuStringValueProvider(env);
        WebPageContext pc = initWebPageContext(env);                                                // устанавливаем контекст страницы

        // Переходим на страницу элементов
        HomePage homePage = pc.getPage(HomePage.class);
        homePage.leftMenu().select(value.of("Elements"));
        ElementsPage elementsPage = pc.getPage(ElementsPage.class);

        // Case: Проверяем текст кнопки
        //  Кликаем по кнопке
        //  Проверяем, что по событию клика появился текст
        assertElementText(value.of("Button"), elementsPage.simpleButton());
        elementsPage.simpleButton().click();
        assertElementText(value.of("Simple button clicked"), elementsPage.textBlockForSimpleButton());

        env.getService(WebDriverService.class).closeAll();
    }

    @Test
    void webLinkTest(Environment env) {
        RuStringValueProvider value = new RuStringValueProvider(env);
        WebPageContext pc = initWebPageContext(env);                                                // устанавливаем контекст страницы

        // Переходим на страницу элементов
        HomePage homePage = pc.getPage(HomePage.class);
        homePage.leftMenu().select(value.of("Elements"));
        ElementsPage elementsPage = pc.getPage(ElementsPage.class);

        // Case: Проверяем текст ссылки
        //  Кликаем по ссылке
        //  Проверяем, что по событию клика появился текст
        assertElementText(value.of("Link"), elementsPage.simpleLink());
        elementsPage.simpleLink().click();
        assertElementText(value.of("Simple link clicked"), elementsPage.textBlockForSimpleLink());

        env.getService(WebDriverService.class).closeAll();
    }

    @Test
    void webButtonHoverTest(Environment env) {
        RuStringValueProvider value = new RuStringValueProvider(env);
        WebPageContext pc = initWebPageContext(env);                                                // устанавливаем контекст страницы

        // Переходим на страницу элементов
        HomePage homePage = pc.getPage(HomePage.class);
        homePage.leftMenu().select(value.of("Elements"));
        ElementsPage elementsPage = pc.getPage(ElementsPage.class);

        // Case: Нужно навести курсор на первую кнопку
        //  При наведении на кнопку появляется ссылка
        //  Переводим курсор с кнопки на ссылку и кликаем по ней
        //  Проверяем, что по событию клика появился текст
        // Оборачивание в runLogic нужно для стабильности.
        // Например, в момент наведения отработал рест и обновил страницу - наведение пропало.
        // Нужно повторить всю последовательность с выводом курсора за границы первого элемента.
        runLogic(env, () -> {
            elementsPage.buttonWithHover().hoverTo(true);
            elementsPage.linkForButtonWithHover().hoverTo(false);
            elementsPage.linkForButtonWithHover().click();
        });
        assertElementText(value.of("Link for hover clicked"), elementsPage.textBlockForLinkForButtonWithHover());

        env.getService(WebDriverService.class).closeAll();
    }

    @Test
    void webTextInputTest(Environment env) {
        RuStringValueProvider value = new RuStringValueProvider(env);
        WebPageContext pc = initWebPageContext(env);                                                // устанавливаем контекст страницы

        // Переходим на страницу элементов
        HomePage homePage = pc.getPage(HomePage.class);
        homePage.leftMenu().select(value.of("Elements"));
        ElementsPage elementsPage = pc.getPage(ElementsPage.class);

        // Case: Проверяем лейбл поля ввода
        //  Проверяем что поле ввода доступно для ввода (эту проверку можно добавить в сам элемент и она будет выполняться при каждом обращении)
        //  Кликаем в поле (эту проверку можно добавить в сам элемент и она будет выполняться при каждом обращении)
        //  Проверяем свойство "плейсхолдер" у поля
        //  Проверяем что в поле ничего не введено
        //  Вводим в поле текст
        //  Проверяем введенный текст
        //  Очищаем введенный текст
        //  Проверяем что поле очистилось
        //  Вводим в поле текст
        //  Проверяем введенный текст
        //  Нажимаем 'Enter' на поле
        //  Проверяем, что по событию нажатия 'Enter' появился текст
        assertElementLabel(value.of("Name"), elementsPage.simpleTextInput());
        assertEnabled(elementsPage.simpleTextInput());
        elementsPage.simpleTextInput().click();
        assertElementProperty("плейсхолдер", value.of("Your name"), elementsPage.simpleTextInput());
        assertElementText(value.of(""), elementsPage.simpleTextInput());
        elementsPage.simpleTextInput().sendKeys("Jack White");
        assertElementText(value.of("Jack White"), elementsPage.simpleTextInput());
        elementsPage.simpleTextInput().clear();
        assertElementText(value.of(""), elementsPage.simpleTextInput());
        elementsPage.simpleTextInput().sendKeys("Jack Black");
        assertElementText(value.of("Jack Black"), elementsPage.simpleTextInput());
        elementsPage.simpleTextInput().sendKeys(Keys.ENTER);
        assertElementText(value.of("Jack Black"), elementsPage.textBlockForSimpleTextInput());

        env.getService(WebDriverService.class).closeAll();
    }

    @Test
    void webModifiedTextInputTest(Environment env) {
        RuStringValueProvider value = new RuStringValueProvider(env);
        WebPageContext pc = initWebPageContext(env);                                                // устанавливаем контекст страницы

        // Переходим на страницу элементов
        HomePage homePage = pc.getPage(HomePage.class);
        homePage.leftMenu().select(value.of("Elements"));
        ElementsPage elementsPage = pc.getPage(ElementsPage.class);

        // Case: Проверяем модифицированное поле ввода (текст хранится не в value, а в атрибуте title)
        //  Проверяем что в поле ничего не введено
        //  Вводим в поле текст
        //  Проверяем введенный текст
        //  Очищаем введенный текст
        //  Проверяем что поле очистилось
        //  Вводим в поле текст
        //  Проверяем введенный текст
        // Модифицированное поле ввода (текст хранится в атрибуте Title). Для теста изменений логики  нет.
        assertElementText(value.of(""), elementsPage.customTextInput());
        elementsPage.customTextInput().sendKeys("Jack White");
        assertElementText(value.of("Jack White"), elementsPage.customTextInput());
        elementsPage.customTextInput().clear();
        assertElementText(value.of(""), elementsPage.customTextInput());
        elementsPage.customTextInput().sendKeys("Jack Black");
        assertElementText(value.of("Jack Black"), elementsPage.customTextInput());

        env.getService(WebDriverService.class).closeAll();
    }

    @Test
    void webCheckboxTest(Environment env) {
        RuStringValueProvider value = new RuStringValueProvider(env);
        WebPageContext pc = initWebPageContext(env);                                                // устанавливаем контекст страницы

        // Переходим на страницу элементов
        HomePage homePage = pc.getPage(HomePage.class);
        homePage.leftMenu().select(value.of("Elements"));
        ElementsPage elementsPage = pc.getPage(ElementsPage.class);

        // Case: Проверяем лейбл у чекбокса
        //  Проверяем что чекбокс доступен для нажатия
        //  Проверяем что чекбокс не выделен
        //  Кликаем по чекбоксу
        //  Проверяем что чекбокс выделен
        //  Проверяем что по событию выделения чекбокса появился текст
        assertElementLabel(value.of("Accept"), elementsPage.simpleCheckbox());
        assertEnabled(elementsPage.simpleCheckbox());
        assertNotSelected(elementsPage.simpleCheckbox());
        elementsPage.simpleCheckbox().click();
        assertSelected(elementsPage.simpleCheckbox());
        assertElementText(value.of("Checkbox selected"), elementsPage.textBlockForSimpleCheckbox());

        env.getService(WebDriverService.class).closeAll();
    }

    // TODO: Добавить кейсы с фильтрацией радиобаттонов
    @Test
    void webRadioGroupTest(Environment env) {
        RuStringValueProvider value = new RuStringValueProvider(env);
        RuIntegerValueProvider intValue = new RuIntegerValueProvider(env);
        WebPageContext pc = initWebPageContext(env);                                                // устанавливаем контекст страницы

        // Переходим на страницу элементов
        HomePage homePage = pc.getPage(HomePage.class);
        homePage.leftMenu().select(value.of("Elements"));
        ElementsPage elementsPage = pc.getPage(ElementsPage.class);

        // Case: Проверяем что радио-кнопка выделена по трем разным условиям выбора (по признаку, лейблу, индексу)
        //  Проверяем лейбл у выделенной радио-кнопки
        //  Проверяем количество радио-кнопок в группе
        //  Кликаем по радио-кнопке с лейблом 'Label 2'
        //  Проверяем что радио-кнопка с указанным индексом выделена
        //  Проверяем что по событию выделения радио-кнопки появился текст
        //  Кликаем по радио-кнопке с индексом равным 3
        //  Проверяем что радио-кнопка с указанным лейблом выделена
        //  Проверяем что по событию выделения радио-кнопки изменился текст
        assertSelected(elementsPage.simpleRadioGroup().getSelected());
        assertSelected(elementsPage.simpleRadioGroup().getByLabel(value.of("Label 1")));
        assertSelected(elementsPage.simpleRadioGroup().getByNumber(intValue.of("1")));
        assertElementLabel(value.of("Label 1"), elementsPage.simpleRadioGroup().getSelected());
        assertElementSize(intValue.of("3"), elementsPage.simpleRadioGroup());
        elementsPage.simpleRadioGroup().getByLabel(value.of("Label 2")).click();
        assertSelected(elementsPage.simpleRadioGroup().getByNumber(intValue.of("2")));
        assertElementText(value.of("Label 2 selected"), elementsPage.textBlockForSimpleRadioGroup());
        elementsPage.simpleRadioGroup().getByNumber(intValue.of("3")).click();
        assertSelected(elementsPage.simpleRadioGroup().getByLabel(value.of("Label 3")));
        assertElementText(value.of("Label 3 selected"), elementsPage.textBlockForSimpleRadioGroup());
        // Case: Получить все лейблы из всех радио-кнопок
        MultipleResult<String> radioButtonLabelsResult = elementsPage.simpleRadioGroup().getValues(new WebRadioButtonLabelExtractor());
        // Case: Получить все лейблы из радио-кнопок, которые не выбраны
        WebRadioButtonFilter selectedFilter = new WebRadioButtonFilter().substract(new WebRadioButtonSelectedCondition());
        MultipleResult<String> filteredRadioButtonLabelsResult = elementsPage.simpleRadioGroup()
                .getValues(new WebRadioButtonLabelExtractor(), selectedFilter);
        // Case: Получить все признаки выделения из всех радио-кнопок
        MultipleResult<Boolean> radioButtonSelectedResult = elementsPage.simpleRadioGroup().getValues(new WebRadioButtonSelectedExtractor());
        // Case: Получить все признаки выделения из радио-кнопок, лейблы которых содержат 'Label'
        WebRadioButtonFilter valueFilter = new WebRadioButtonFilter(new WebRadioButtonLabelValueCondition(value.of("[подстрока]Label")));
        MultipleResult<Boolean> filteredRadioButtonSelectedResult = elementsPage.simpleRadioGroup()
                .getValues(new WebRadioButtonSelectedExtractor(), valueFilter);
        // Case: Получить все экземпляры RadioButton
        MultipleResult<WebRadioButton> radioButtonsResult = elementsPage.simpleRadioGroup().getValues(new WebRadioButtonExtractor());
        // Case: Получить все экземпляры RadioButton, которые доступны для выделения
        WebRadioButtonFilter enabledFilter = new WebRadioButtonFilter(new WebRadioButtonEnabledCondition());
        MultipleResult<WebRadioButton> filteredRadioButtonsResult = elementsPage.simpleRadioGroup()
                .getValues(new WebRadioButtonExtractor(), enabledFilter);

        env.getService(WebDriverService.class).closeAll();
    }

    @Test
    void webFileInputTest(Environment env) {
        RuStringValueProvider value = new RuStringValueProvider(env);
        RuIntegerValueProvider intValue = new RuIntegerValueProvider(env);
        WebPageContext pc = initWebPageContext(env);                                                // устанавливаем контекст страницы

        // Переходим на страницу элементов
        HomePage homePage = pc.getPage(HomePage.class);
        homePage.leftMenu().select(value.of("Elements"));
        ElementsPage elementsPage = pc.getPage(ElementsPage.class);

        // Case: Проверяем лейбл у элемента загрузки файлов
        //  Проверяем что элемент загрузки файлов доступен для работы
        //  Проверяем что в элемент ничего не введено
        //  Вводим в элемент имя файла, заданное в источнике данных с именем [props]
        //  Проверяем введенное имя файла
        //  Очищаем введенное имя файла
        //  Проверяем что в элемент ничего не введено
        //  Вводим в элемент имя файла, заданное в источнике данных с именем [props]
        //  Проверяем введенное имя файла
        //  Загружаем файл
        assertElementLabel(value.of("Export"), elementsPage.simpleFileInput());
        assertEnabled(elementsPage.simpleFileInput());
        assertElementText(value.of(""), elementsPage.simpleFileInput());
        elementsPage.simpleFileInput().sendKeys(value.of("${[props] localFilename}").get());
        assertElementText(value.of("${[props] localFilename}"), elementsPage.simpleFileInput());
        elementsPage.simpleFileInput().clear();
        assertElementText(value.of(""), elementsPage.simpleFileInput());
        elementsPage.simpleFileInput().sendKeys(value.of("${[props] localFilename}").get());
        assertElementText(value.of("${[props] localFilename}"), elementsPage.simpleFileInput());
        elementsPage.simpleFileInput().submit();

        env.getService(WebDriverService.class).closeAll();
    }

    @Test
    void webFileDownloadTest(Environment env) {
        RuStringValueProvider value = new RuStringValueProvider(env);
        RuIntegerValueProvider intValue = new RuIntegerValueProvider(env);
        WebPageContext pc = initWebPageContext(env);                                                // устанавливаем контекст страницы

        // Переходим на страницу элементов
        HomePage homePage = pc.getPage(HomePage.class);
        homePage.leftMenu().select(value.of("Elements"));
        ElementsPage elementsPage = pc.getPage(ElementsPage.class);

        // Case: Удаляем файл с локальной директории (если он там существует)
        //  Кликаем на ссылку загрузки файла на локальную станцию
        //  Увеличиваем таймаут для компенсации времени скачивания
        //    (опциональный фаг, если нужно проверить файл, время скачивания которого больше заданного таймаута)
        //  Проверяем что файл загружен в директорию загрузки файлов
        //  Восстанавливаем таймаут, используемый по умолчанию для проверок
        deleteFileIfExists(value.of("${[props] downloadedFileName}"));
        elementsPage.downloadLink().click();
        setTimeoutValue(CheckTimeout.class, Duration.ofSeconds(30));
        assertFileExists(value.of("${[props] downloadedFileName}"));
        setDefaultTimeoutValue(CheckTimeout.class);

        env.getService(WebDriverService.class).closeAll();
    }

}