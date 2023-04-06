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
import io.perfeccionista.framework.pagefactory.elements.selectors.WebSelector;
import io.perfeccionista.framework.pagefactory.operation.handler.JsDragAndDrop;
import io.perfeccionista.framework.pagefactory.pageobjects.handlers.SeleniumDoubleClickHandler;

import static io.perfeccionista.framework.pagefactory.elements.ElementComponents.FOCUS;

// TODO: @PageActs("ElementsPage feature name")
@Name("Elements page")
@Name("Страница элементов")
public interface ElementsPage extends AbstractWebPage {

    @Name("World map")
    @Name("Карта мира")
    @WebSelector(id = "simple-image")
    @WebSelector(component = "Image border", xpath = "self::node()[@class = 'img-thumbnail']")
    WebImage worldMap();


    @Name("Simple button")
    @Name("Простая кнопка")
    @WebSelector(id = "simple-button")
    WebButton simpleButton();

    @Name("Simple button text")
    @Name("текстовый блок Простой кнопки")
    @WebSelector(id = "simple-button-text")
    WebText simpleButtonText();


    @Name("Button for hover")
    @Name("Кнопка для наведения")
    @WebSelector(id = "button-for-hover")
    WebButton buttonForHover();

    @Name("Visible link")
    @Name("Появляющаяся ссылка")
    @WebSelector(id = "visible-link")
    WebLink visibleLink();

    @Name("Visible link text")
    @Name("текстовый блок Появляющейся ссылки")
    @WebSelector(id = "visible-link-text")
    WebText visibleLinkText();


    @Name("Button with spinner")
    @Name("Кнопка со спиннером")
    @WebSelector(id = "button-with-spinner")
    WebButton buttonWithSpinner();

    @Name("Spinner")
    @Name("Спиннер")
    @WebSelector(id = "spinner")
    WebImage spinner();

    @Name("Button with spinner text")
    @Name("текстовый блок Кнопки со спиннером")
    @WebSelector(id = "button-with-spinner-text")
    WebText buttonWithSpinnerText();


    @Name("Simple link")
    @Name("Простая ссылка")
    @WebSelector(id = "simple-link")
    WebLink simpleLink();

    @Name("Simple link text")
    @Name("текстовый блок Простой ссылки")
    @WebSelector(id = "simple-link-text")
    WebText simpleLinkText();


    @Name("Simple input button")
    @Name("кнопка для Простого поля ввода")
    @WebSelector(id = "simple-input-button")
    @WebSelector(component = "Red", xpath = "self::node()[contains(@class, 'btn-danger')]")
    @WebSelector(component = "Green", xpath = "self::node()[contains(@class, 'btn-success')]")
    WebButton simpleInputButton();

    @Name("Simple input")
    @Name("Простое поле ввода")
    @WebSelector(id = "simple-input")
    WebTextInput simpleInput();

    @Name("Simple input text")
    @Name("текстовый блок Простого поля ввода")
    @WebSelector(id = "simple-input-text")
    WebText simpleInputText();


    @Name("Area input button")
    @Name("кнопка для Многострочного поля ввода")
    @WebSelector(id = "area-input-button")
    @WebSelector(component = "Red", xpath = "self::node()[contains(@class, 'btn-danger')]")
    @WebSelector(component = "Green", xpath = "self::node()[contains(@class, 'btn-success')]")
    WebButton areaInputButton();

    @Name("Area input")
    @Name("Многострочное поле ввода")
    @WebSelector(id = "area-input")
    WebTextInput areaInput();

    @Name("Area input text")
    @Name("текстовый блок Многострочного поля ввода")
    @WebSelector(id = "area-input-text")
    WebText areaInputText();


    @Name("Checkbox one")
    @Name("Первый чекбокс")
    @WebSelector(xpath = ".//*[@id = 'checkbox-one']/parent::node()")
    @WebSelector(component = FOCUS, xpath = ".//input")
    WebCheckbox checkboxOne();

    @Name("Checkbox two")
    @Name("Второй чекбокс")
    @WebSelector(xpath = ".//*[@id = 'checkbox-two']/parent::node()")
    @WebSelector(component = FOCUS, xpath = ".//input")
    WebCheckbox checkboxTwo();

    @Name("Checkbox three")
    @Name("Третий чекбокс")
    @WebSelector(xpath = ".//*[@id = 'checkbox-three']/parent::node()")
    @WebSelector(component = FOCUS, xpath = ".//input")
    WebCheckbox checkboxThree();

    @Name("Checkbox text")
    @Name("текстовый блок Чекбокса")
    @WebSelector(id = "checkbox-text")
    WebText checkboxText();


    @Name("RadioButton one")
    @Name("Первая радио-кнопка")
    @WebSelector(xpath = ".//*[@id = 'radio-one']/parent::node()")
    WebRadioButton radioButtonOne();

    @Name("Radio group")
    @Name("Радио группа")
    @WebSelector(id = "radio-group")
    WebRadioGroup radioGroup();

    @Name("текстовый блок Радио группы")
    @WebSelector(id = "radio-text")
    WebText radioButtonText();


    @Name("File input")
    @Name("поле Загрузки файла")
    @WebSelector(xpath = ".//*[@id = 'file-input']/parent::node()")
    WebFileInput fileInput();

    @Name("File input text")
    @Name("текстовый блок поля Загрузки файла")
    @WebSelector(id = "file-input-file-name")
    WebText fileInputText();

    @Name("File download")
    @Name("ссылка Скачать файл")
    @WebSelector(id = "file-download")
    WebLink fileDownloadLink();


    @Name("Drag&Drop source")
    @Name("Перетаскиваемый блок")
    @WebSelector(id = "drag-and-drop-source")
    @WebElementAction(name = "Drag and Drop", handler = JsDragAndDrop.class)
    WebText sourceBlock();

    @Name("Drag&Drop target")
    @Name("Целевой блок")
    @WebSelector(id = "drag-and-drop-target")
    WebText targetBlock();

    @Name("Drag&Drop text")
    @Name("текстовый блок для Drag&Drop")
    @WebSelector(id = "drag-and-drop-text")
    WebText dragAndDropText();


    @Name("Double click button")
    @Name("Кнопка с двойным кликом")
    @WebSelector(id = "double-click-button")
    @WebElementAction(name = "Double click", handler = SeleniumDoubleClickHandler.class)
    WebButton doubleClickButton();

    @Name("Double click button text")
    @Name("текстовый блок для Кнопки с двойным кликом")
    @WebSelector(id = "double-click-text")
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
