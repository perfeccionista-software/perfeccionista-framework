package io.perfeccionista.framework.pagefactory.pageobjects;

import io.perfeccionista.framework.name.Name;
import io.perfeccionista.framework.pagefactory.elements.WebButton;
import io.perfeccionista.framework.pagefactory.elements.WebCheckbox;
import io.perfeccionista.framework.pagefactory.elements.WebFileInput;
import io.perfeccionista.framework.pagefactory.elements.WebImage;
import io.perfeccionista.framework.pagefactory.elements.WebLink;
import io.perfeccionista.framework.pagefactory.elements.WebRadioGroup;
import io.perfeccionista.framework.pagefactory.elements.WebTextBlock;
import io.perfeccionista.framework.pagefactory.elements.WebTextInput;
import io.perfeccionista.framework.pagefactory.elements.locators.WebLocator;
import io.perfeccionista.framework.pagefactory.elements.properties.WebElementProperty;
import io.perfeccionista.framework.pagefactory.pageobjects.extractors.AltAttributeExtractor;
import io.perfeccionista.framework.pagefactory.pageobjects.extractors.HrefAttributeExtractor;
import io.perfeccionista.framework.pagefactory.pageobjects.extractors.SrcAttributeExtractor;

@Name("Elements page")
public interface ElementsPage extends AbstractWebPage {

    @Name("World map")
    @WebLocator(id = "simple-image")
    @WebLocator(component = "Image border", xpath = "self::node()[@class = 'img-thumbnail']")
    @WebElementProperty(name = "prompt", extractor = AltAttributeExtractor.class)
    @WebElementProperty(name = "source", extractor = SrcAttributeExtractor.class)
    WebImage worldMap();





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
