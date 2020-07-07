package io.perfeccionista.framework.pagefactory.pageobjects;

import io.perfeccionista.framework.name.Name;
import io.perfeccionista.framework.pagefactory.elements.WebButton;
import io.perfeccionista.framework.pagefactory.elements.WebImage;
import io.perfeccionista.framework.pagefactory.elements.WebLink;
import io.perfeccionista.framework.pagefactory.elements.WebTextBlock;
import io.perfeccionista.framework.pagefactory.elements.locators.WebLocator;
import io.perfeccionista.framework.pagefactory.elements.properties.WebElementProperty;
import io.perfeccionista.framework.pagefactory.pageobjects.extractors.AltAttributeExtractor;
import io.perfeccionista.framework.pagefactory.pageobjects.extractors.SrcAttributeExtractor;

@Name("Elements page")
public interface ElementsPage extends AbstractWebPage {

    @Name("World map")
    @WebLocator(id = "simple-image")
    @WebLocator(component = "Image border", xpath = "self::node()[@class = 'img-thumbnail']")
    @WebElementProperty(name = "prompt", extractor = AltAttributeExtractor.class)
    @WebElementProperty(name = "source", extractor = SrcAttributeExtractor.class)
    WebImage worldMap();


    @Name("Simple button")
    @WebLocator(id = "simple-button")
    WebButton simpleButton();

    @Name("Simple button text")
    @WebLocator(id = "simple-button-text")
    WebTextBlock simpleButtonText();


    @Name("Button for hover")
    @WebLocator(id = "button-for-hover")
    WebButton buttonForHover();

    @Name("Visible link")
    @WebLocator(id = "visible-link")
    WebLink visibleLink();

    @Name("Visible link text")
    @WebLocator(id = "visible-link-text")
    WebTextBlock visibleLinkText();


    @Name("Button with spinner")
    @WebLocator(id = "button-with-spinner")
    WebButton buttonWithSpinner();

    @Name("Spinner")
    @WebLocator(id = "spinner")
    WebImage spinner();

    @Name("Visible link text")
    @WebLocator(id = "button-with-spinner-text")
    WebTextBlock buttonWithSpinnerText();


    @Name("Simple link")
    @WebLocator(id = "simple-link")
    WebLink simpleLink();

    @Name("Simple link text")
    @WebLocator(id = "simple-link-text")
    WebTextBlock simpleLinkText();


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
