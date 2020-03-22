package io.perfeccionista.framework.pagefactory.web.pageobjects;

import io.perfeccionista.framework.name.Name;
import io.perfeccionista.framework.pagefactory.elements.WebButton;
import io.perfeccionista.framework.pagefactory.elements.WebCheckbox;
import io.perfeccionista.framework.pagefactory.elements.WebFileInput;
import io.perfeccionista.framework.pagefactory.elements.WebLink;
import io.perfeccionista.framework.pagefactory.elements.WebRadioGroup;
import io.perfeccionista.framework.pagefactory.elements.WebTextBlock;
import io.perfeccionista.framework.pagefactory.elements.WebTextInput;
import io.perfeccionista.framework.pagefactory.elements.locators.WebLocator;
import io.perfeccionista.framework.pagefactory.elements.methods.ElementMethod;
import io.perfeccionista.framework.pagefactory.elements.methods.JsGetTextFromTitle;
import io.perfeccionista.framework.pagefactory.js.checks.IsDisplayedCheck;

import static io.perfeccionista.framework.pagefactory.elements.methods.availability.AvailableMethod.GET_TEXT_METHOD;

public interface ElementsPage extends AbstractWebPage {

    @Name("Кнопка 1")
    @WebLocator(elementChecks = {IsDisplayedCheck.class})
    WebButton simpleButton();
    @Name("текст для Кнопки 1")
    WebTextBlock textBlockForSimpleButton();


    @Name("Ссылка 1")
    @WebLocator(elementChecks = {IsDisplayedCheck.class})
    WebLink simpleLink();
    @Name("текст для Ссылки 1")
    WebTextBlock textBlockForSimpleLink();


    @Name("Кнопка 2")
    @WebLocator(elementChecks = {IsDisplayedCheck.class})
    WebButton buttonWithHover();
    @Name("Ссылка 2")
    @WebLocator(elementChecks = {IsDisplayedCheck.class})
    WebLink linkForButtonWithHover();
    @Name("текст для Ссылки 2")
    WebTextBlock textBlockForLinkForButtonWithHover();


    @Name("Поле ввода")
    @WebLocator(elementChecks = {IsDisplayedCheck.class})
    WebTextInput simpleTextInput();
    @Name("текст для Поля ввода")
    WebTextBlock textBlockForSimpleTextInput();


    @Name("Модифицированное Поле ввода")
    @WebLocator(elementChecks = {IsDisplayedCheck.class})
    @ElementMethod(type = GET_TEXT_METHOD, implementation = JsGetTextFromTitle.class)
    WebTextInput customTextInput();


    @Name("Чекбокс")
    @WebLocator(elementChecks = {IsDisplayedCheck.class})
    WebCheckbox simpleCheckbox();
    @Name("текст для Чекбокса")
    WebTextBlock textBlockForSimpleCheckbox();


    @Name("Радио группа")
    @WebLocator(elementChecks = {IsDisplayedCheck.class})
    WebRadioGroup simpleRadioGroup();       // 3 доступных для выделения баттона и 1 недоступный
    @Name("текст для Радио группы")
    WebTextBlock textBlockForSimpleRadioGroup();


    @Name("Поле Загрузка файла")
    @WebLocator(elementChecks = {IsDisplayedCheck.class})
    WebFileInput simpleFileInput();
    @Name("текст для Поля загрузки файла")
    WebTextBlock textBlockForSimpleFileInput();


    @Name("Ссылка Загрузки файла")
    @WebLocator(elementChecks = {IsDisplayedCheck.class})
    WebLink downloadLink();
    @Name("текст для Ссылки загрузки файла")
    WebTextBlock textBlockForDownloadLink();

}
