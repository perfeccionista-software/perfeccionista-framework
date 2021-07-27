package io.perfeccionista.framework.pagefactory.pageobjects;

import io.perfeccionista.framework.name.Name;
import io.perfeccionista.framework.pagefactory.elements.WebButton;
import io.perfeccionista.framework.pagefactory.elements.WebCheckbox;
import io.perfeccionista.framework.pagefactory.elements.WebFileInput;
import io.perfeccionista.framework.pagefactory.elements.WebImage;
import io.perfeccionista.framework.pagefactory.elements.WebLink;
import io.perfeccionista.framework.pagefactory.elements.WebRadioButton;
import io.perfeccionista.framework.pagefactory.elements.WebRadioGroup;
import io.perfeccionista.framework.pagefactory.elements.WebText;
import io.perfeccionista.framework.pagefactory.elements.WebTextInput;
import io.perfeccionista.framework.pagefactory.elements.actions.WebElementAction;
import io.perfeccionista.framework.pagefactory.elements.locators.WebLocator;
import io.perfeccionista.framework.pagefactory.elements.properties.base.WebElementAttributeProperty;
import io.perfeccionista.framework.pagefactory.operation.handler.JsDragAndDrop;
import io.perfeccionista.framework.pagefactory.pageobjects.handlers.SeleniumDoubleClickHandler;

import static io.perfeccionista.framework.pagefactory.elements.ElementComponents.FOCUS;

// TODO: @PageActs("ElementsPage feature name")
@Name("Elements page")
@Name("Страница элементов")
public interface ElementsPage extends AbstractWebPage {

    @Name("World map")
    @Name("Карта мира")
    @WebLocator(id = "simple-image")
    @WebLocator(component = "Image border", xpath = "self::node()[@class = 'img-thumbnail']")
    @WebElementAttributeProperty(name = "prompt", attribute = "alt")
    @WebElementAttributeProperty(name = "source", attribute = "src")
    WebImage worldMap();


    @Name("Simple button")
    @Name("Простая кнопка")
    @WebLocator(id = "simple-button")
    WebButton simpleButton();

    @Name("Simple button text")
    @Name("текстовый блок Простой кнопки")
    @WebLocator(id = "simple-button-text")
    WebText simpleButtonText();


    @Name("Button for hover")
    @Name("Кнопка для наведения")
    @WebLocator(id = "button-for-hover")
    WebButton buttonForHover();

    @Name("Visible link")
    @Name("Появляющаяся ссылка")
    @WebLocator(id = "visible-link")
    WebLink visibleLink();

    @Name("Visible link text")
    @Name("текстовый блок Появляющейся ссылки")
    @WebLocator(id = "visible-link-text")
    WebText visibleLinkText();


    @Name("Button with spinner")
    @Name("Кнопка со спиннером")
    @WebLocator(id = "button-with-spinner")
    WebButton buttonWithSpinner();

    @Name("Spinner")
    @Name("Спиннер")
    @WebLocator(id = "spinner")
    WebImage spinner();

    @Name("Button with spinner text")
    @Name("текстовый блок Кнопки со спиннером")
    @WebLocator(id = "button-with-spinner-text")
    WebText buttonWithSpinnerText();


    @Name("Simple link")
    @Name("Простая ссылка")
    @WebLocator(id = "simple-link")
    WebLink simpleLink();

    @Name("Simple link text")
    @Name("текстовый блок Простой ссылки")
    @WebLocator(id = "simple-link-text")
    WebText simpleLinkText();


    @Name("Simple input button")
    @Name("кнопка для Простого поля ввода")
    @WebLocator(id = "simple-input-button")
    @WebLocator(component = "Red", xpath = "self::node()[contains(@class, 'btn-danger')]")
    @WebLocator(component = "Green", xpath = "self::node()[contains(@class, 'btn-success')]")
    WebButton simpleInputButton();

    @Name("Simple input")
    @Name("Простое поле ввода")
    @WebLocator(id = "simple-input")
    @WebElementAttributeProperty(name = "placeholder", attribute = "placeholder")
    WebTextInput simpleInput();

    @Name("Simple input text")
    @Name("текстовый блок Простого поля ввода")
    @WebLocator(id = "simple-input-text")
    WebText simpleInputText();


    @Name("Area input button")
    @Name("кнопка для Многострочного поля ввода")
    @WebLocator(id = "area-input-button")
    @WebLocator(component = "Red", xpath = "self::node()[contains(@class, 'btn-danger')]")
    @WebLocator(component = "Green", xpath = "self::node()[contains(@class, 'btn-success')]")
    WebButton areaInputButton();

    @Name("Area input")
    @Name("Многострочное поле ввода")
    @WebLocator(id = "area-input")
    @WebElementAttributeProperty(name = "placeholder", attribute = "placeholder")
    WebTextInput areaInput();

    @Name("Area input text")
    @Name("текстовый блок Многострочного поля ввода")
    @WebLocator(id = "area-input-text")
    WebText areaInputText();


    @Name("Checkbox one")
    @Name("Первый чекбокс")
    @WebLocator(xpath = ".//*[@id = 'checkbox-one']/parent::node()")
    @WebLocator(component = FOCUS, xpath = ".//input")
    @WebElementAttributeProperty(name = "name", locator = @WebLocator(id = "checkbox-one"), attribute = "name")
    WebCheckbox checkboxOne();

    @Name("Checkbox two")
    @Name("Второй чекбокс")
    @WebLocator(xpath = ".//*[@id = 'checkbox-two']/parent::node()")
    @WebLocator(component = FOCUS, xpath = ".//input")
    @WebElementAttributeProperty(name = "name", locator = @WebLocator(id = "checkbox-two"), attribute = "name")
    WebCheckbox checkboxTwo();

    @Name("Checkbox three")
    @Name("Третий чекбокс")
    @WebLocator(xpath = ".//*[@id = 'checkbox-three']/parent::node()")
    @WebLocator(component = FOCUS, xpath = ".//input")
    @WebElementAttributeProperty(name = "name", locator = @WebLocator(id = "checkbox-three"), attribute = "name")
    WebCheckbox checkboxThree();

    @Name("Checkbox text")
    @Name("текстовый блок Чекбокса")
    @WebLocator(id = "checkbox-text")
    WebText checkboxText();


    @Name("RadioButton one")
    @Name("Первая радио-кнопка")
    @WebLocator(xpath = ".//*[@id = 'radio-one']/parent::node()")
    @WebElementAttributeProperty(name = "name", locator = @WebLocator(id = "radio-one"), attribute = "name")
    WebRadioButton radioButtonOne();

    @Name("Radio group")
    @Name("Радио группа")
    @WebLocator(id = "radio-group")
    WebRadioGroup radioGroup();

    @Name("текстовый блок Радио группы")
    @WebLocator(id = "radio-text")
    WebText radioButtonText();


    @Name("File input")
    @Name("поле Загрузки файла")
    @WebLocator(xpath = ".//*[@id = 'file-input']/parent::node()")
    WebFileInput fileInput();

    @Name("File input text")
    @Name("текстовый блок поля Загрузки файла")
    @WebLocator(id = "file-input-file-name")
    WebText fileInputText();

    @Name("File download")
    @Name("ссылка Скачать файл")
    @WebLocator(id = "file-download")
    WebLink fileDownloadLink();


    @Name("Drag&Drop source")
    @Name("Перетаскиваемый блок")
    @WebLocator(id = "drag-and-drop-source")
    @WebElementAction(name = "Drag and Drop", handler = JsDragAndDrop.class)
    WebText sourceBlock();

    @Name("Drag&Drop target")
    @Name("Целевой блок")
    @WebLocator(id = "drag-and-drop-target")
    WebText targetBlock();

    @Name("Drag&Drop text")
    @Name("текстовый блок для Drag&Drop")
    @WebLocator(id = "drag-and-drop-text")
    WebText dragAndDropText();


    @Name("Double click button")
    @Name("Кнопка с двойным кликом")
    @WebLocator(id = "double-click-button")
    @WebElementAction(name = "Double click", handler = SeleniumDoubleClickHandler.class)
    WebButton doubleClickButton();

    @Name("Double click button text")
    @Name("текстовый блок для Кнопки с двойным кликом")
    @WebLocator(id = "double-click-text")
    WebText doubleClickText();

//
//
//
//
//
//
//
//
//
//    @Name("Кнопка 1")
////    @WebLocator(invokeOnCall = {IsDisplayedFunctionInvoke.class})
//    WebButton simpleButton();
//    @Name("текст для Кнопки 1")
//    WebTextBlock textBlockForSimpleButton();
//
//
//    @Name("Ссылка 1")
////    @WebLocator(invokeOnCall = {IsDisplayedFunctionInvoke.class})
//    WebLink simpleLink();
//    @Name("текст для Ссылки 1")
//    WebTextBlock textBlockForSimpleLink();
//
//
//    @Name("Кнопка 2")
////    @WebLocator(invokeOnCall = {IsDisplayedFunctionInvoke.class})
//    WebButton buttonWithHover();
//    @Name("Ссылка 2")
////    @WebLocator(invokeOnCall = {IsDisplayedFunctionInvoke.class})
//    WebLink linkForButtonWithHover();
//    @Name("текст для Ссылки 2")
//    WebTextBlock textBlockForLinkForButtonWithHover();
//
//
//    @Name("Поле ввода")
////    @WebLocator(invokeOnCall = {IsDisplayedFunctionInvoke.class})
//    WebTextInput simpleTextInput();
//    @Name("текст для Поля ввода")
//    WebTextBlock textBlockForSimpleTextInput();
//
//
//    @Name("Модифицированное Поле ввода")
////    @WebLocator(invokeOnCall = {IsDisplayedFunctionInvoke.class})
//
//    WebTextInput customTextInput();
//
//
//    @Name("Чекбокс")
////    @WebLocator(invokeOnCall = {IsDisplayedFunctionInvoke.class})
//    WebCheckbox simpleCheckbox();
//    @Name("текст для Чекбокса")
//    WebTextBlock textBlockForSimpleCheckbox();
//
//
//    @Name("Радио группа")
////    @WebLocator(invokeOnCall = {IsDisplayedFunctionInvoke.class})
//    WebRadioGroup simpleRadioGroup();       // 3 доступных для выделения баттона и 1 недоступный
//    @Name("текст для Радио группы")
//    WebTextBlock textBlockForSimpleRadioGroup();
//
//
//    @Name("Поле Загрузка файла")
////    @WebLocator(invokeOnCall = {IsDisplayedFunctionInvoke.class})
//    WebFileInput simpleFileInput();
//    @Name("текст для Поля загрузки файла")
//    WebTextBlock textBlockForSimpleFileInput();
//
//
//    @Name("Ссылка Загрузки файла")
////    @WebLocator(invokeOnCall = {IsDisplayedFunctionInvoke.class})
//    WebLink downloadLink();
//    @Name("текст для Ссылки загрузки файла")
//    WebTextBlock textBlockForDownloadLink();
//
//
//    @Name("Блок Двойного клика")
////    @WebLocator(invokeOnCall = {IsDisplayedFunctionInvoke.class})
//    WebTextBlock doubleClickBlock();
//    @Name("текст для Блока Двойного клика")
//    WebTextBlock textBlockForDoubleClickBlock();
//
//
//    @Name("Блок который перетаскивают")
////    @WebLocator(invokeOnCall = {IsDisplayedFunctionInvoke.class})
//
//    WebTextBlock dragAndDropSource();
//    @Name("Блок на который перетаскивают")
////    @WebLocator(invokeOnCall = {IsDisplayedFunctionInvoke.class})
//    WebTextBlock dragAndDropTarget();
//    @Name("текстовый блок для проверки Drag&Drop")
//    WebTextBlock textBlockForDragAndDrop();

}
