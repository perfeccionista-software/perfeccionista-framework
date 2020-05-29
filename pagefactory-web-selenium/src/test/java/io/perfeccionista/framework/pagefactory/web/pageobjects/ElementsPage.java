package io.perfeccionista.framework.pagefactory.web.pageobjects;

import io.perfeccionista.framework.name.Name;
import io.perfeccionista.framework.pagefactory.elements.WebButton;
import io.perfeccionista.framework.pagefactory.elements.WebCheckbox;
import io.perfeccionista.framework.pagefactory.elements.WebFileInput;
import io.perfeccionista.framework.pagefactory.elements.WebLink;
import io.perfeccionista.framework.pagefactory.elements.WebRadioGroup;
import io.perfeccionista.framework.pagefactory.elements.WebTextBlock;
import io.perfeccionista.framework.pagefactory.elements.WebTextInput;
import io.perfeccionista.framework.pagefactory.elements.actions.JsGetTextFromTitle;
import io.perfeccionista.framework.pagefactory.elements.interactions.ElementInteraction;
import io.perfeccionista.framework.pagefactory.elements.locators.WebLocator;
import io.perfeccionista.framework.pagefactory.elements.actions.ElementAction;
import io.perfeccionista.framework.pagefactory.web.pageobjects.implementations.DoubleClickActionImplementation;
import io.perfeccionista.framework.pagefactory.web.pageobjects.implementations.DragAndDropInteraction;

import static io.perfeccionista.framework.pagefactory.elements.methods.AvailableElementMethods.GET_TEXT_METHOD;

@Name("Страница элементов")
public interface ElementsPage extends AbstractWebPage {

    @Name("Кнопка 1")
//    @WebLocator(invokeOnCall = {IsDisplayedFunctionInvoke.class})
    WebButton simpleButton();
    @Name("текст для Кнопки 1")
    WebTextBlock textBlockForSimpleButton();


    @Name("Ссылка 1")
//    @WebLocator(invokeOnCall = {IsDisplayedFunctionInvoke.class})
    WebLink simpleLink();
    @Name("текст для Ссылки 1")
    WebTextBlock textBlockForSimpleLink();


    @Name("Кнопка 2")
//    @WebLocator(invokeOnCall = {IsDisplayedFunctionInvoke.class})
    WebButton buttonWithHover();
    @Name("Ссылка 2")
//    @WebLocator(invokeOnCall = {IsDisplayedFunctionInvoke.class})
    WebLink linkForButtonWithHover();
    @Name("текст для Ссылки 2")
    WebTextBlock textBlockForLinkForButtonWithHover();


    @Name("Поле ввода")
//    @WebLocator(invokeOnCall = {IsDisplayedFunctionInvoke.class})
    WebTextInput simpleTextInput();
    @Name("текст для Поля ввода")
    WebTextBlock textBlockForSimpleTextInput();


    @Name("Модифицированное Поле ввода")
//    @WebLocator(invokeOnCall = {IsDisplayedFunctionInvoke.class})
    @ElementAction(name = GET_TEXT_METHOD, implementation = JsGetTextFromTitle.class)
    WebTextInput customTextInput();


    @Name("Чекбокс")
//    @WebLocator(invokeOnCall = {IsDisplayedFunctionInvoke.class})
    WebCheckbox simpleCheckbox();
    @Name("текст для Чекбокса")
    WebTextBlock textBlockForSimpleCheckbox();


    @Name("Радио группа")
//    @WebLocator(invokeOnCall = {IsDisplayedFunctionInvoke.class})
    WebRadioGroup simpleRadioGroup();       // 3 доступных для выделения баттона и 1 недоступный
    @Name("текст для Радио группы")
    WebTextBlock textBlockForSimpleRadioGroup();


    @Name("Поле Загрузка файла")
//    @WebLocator(invokeOnCall = {IsDisplayedFunctionInvoke.class})
    WebFileInput simpleFileInput();
    @Name("текст для Поля загрузки файла")
    WebTextBlock textBlockForSimpleFileInput();


    @Name("Ссылка Загрузки файла")
//    @WebLocator(invokeOnCall = {IsDisplayedFunctionInvoke.class})
    WebLink downloadLink();
    @Name("текст для Ссылки загрузки файла")
    WebTextBlock textBlockForDownloadLink();


    @Name("Блок Двойного клика")
    @ElementAction(name = "DoubleClick", implementation = DoubleClickActionImplementation.class)
//    @WebLocator(invokeOnCall = {IsDisplayedFunctionInvoke.class})
    WebTextBlock doubleClickBlock();
    @Name("текст для Блока Двойного клика")
    WebTextBlock textBlockForDoubleClickBlock();


    @Name("Блок который перетаскивают")
//    @WebLocator(invokeOnCall = {IsDisplayedFunctionInvoke.class})
    @ElementInteraction(name = "Drag&Drop", implementation = DragAndDropInteraction.class)
    WebTextBlock dragAndDropSource();
    @Name("Блок на который перетаскивают")
//    @WebLocator(invokeOnCall = {IsDisplayedFunctionInvoke.class})
    WebTextBlock dragAndDropTarget();
    @Name("текстовый блок для проверки Drag&Drop")
    WebTextBlock textBlockForDragAndDrop();

}
