package io.perfeccionista.framework.pagefactory.pageobjects;

import io.perfeccionista.framework.name.Name;
import io.perfeccionista.framework.pagefactory.elements.WebButton;
import io.perfeccionista.framework.pagefactory.elements.WebCheckbox;
import io.perfeccionista.framework.pagefactory.elements.WebFileInput;
import io.perfeccionista.framework.pagefactory.elements.WebImage;
import io.perfeccionista.framework.pagefactory.elements.WebLink;
import io.perfeccionista.framework.pagefactory.elements.WebRadioButton;
import io.perfeccionista.framework.pagefactory.elements.WebRadioGroup;
import io.perfeccionista.framework.pagefactory.elements.WebRadioGroupSeleniumImpl.DefaultWebRadioButtonMappedBlock;
import io.perfeccionista.framework.pagefactory.elements.WebTextBlock;
import io.perfeccionista.framework.pagefactory.elements.WebTextInput;
import io.perfeccionista.framework.pagefactory.elements.actions.WebElementAction;
import io.perfeccionista.framework.pagefactory.elements.interactions.WebElementInteraction;
import io.perfeccionista.framework.pagefactory.elements.locators.WebLocator;
import io.perfeccionista.framework.pagefactory.elements.mapping.UseWebMappedBlock;
import io.perfeccionista.framework.pagefactory.elements.properties.WebElementProperty;
import io.perfeccionista.framework.pagefactory.pageobjects.extractors.AltAttributeExtractor;
import io.perfeccionista.framework.pagefactory.pageobjects.extractors.NameAttributeExtractor;
import io.perfeccionista.framework.pagefactory.pageobjects.extractors.PlaceholderAttributeExtractor;
import io.perfeccionista.framework.pagefactory.pageobjects.extractors.SrcAttributeExtractor;
import io.perfeccionista.framework.pagefactory.pageobjects.implementations.DoubleClickActionImplementation;
import io.perfeccionista.framework.pagefactory.pageobjects.implementations.DragAndDropInteraction;
import org.jetbrains.annotations.NotNull;

import static io.perfeccionista.framework.pagefactory.elements.components.WebComponents.FOCUS;
import static io.perfeccionista.framework.pagefactory.elements.components.WebComponents.RADIO;

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

    @Name("Button with spinner text")
    @WebLocator(id = "button-with-spinner-text")
    WebTextBlock buttonWithSpinnerText();


    @Name("Simple link")
    @WebLocator(id = "simple-link")
    WebLink simpleLink();

    @Name("Simple link text")
    @WebLocator(id = "simple-link-text")
    WebTextBlock simpleLinkText();


    @Name("Simple input button")
    @WebLocator(id = "simple-input-button")
    @WebLocator(component = "Red", xpath = "self::node()[contains(@class, 'btn-danger')]")
    @WebLocator(component = "Green", xpath = "self::node()[contains(@class, 'btn-success')]")
    WebButton simpleInputButton();

    @Name("Simple input")
    @WebLocator(id = "simple-input")
    @WebElementProperty(name = "placeholder", extractor = PlaceholderAttributeExtractor.class)
    WebTextInput simpleInput();

    @Name("Simple input text")
    @WebLocator(id = "simple-input-text")
    WebTextBlock simpleInputText();


    @Name("Area input button")
    @WebLocator(id = "area-input-button")
    @WebLocator(component = "Red", xpath = "self::node()[contains(@class, 'btn-danger')]")
    @WebLocator(component = "Green", xpath = "self::node()[contains(@class, 'btn-success')]")
    WebButton areaInputButton();

    @Name("Area input")
    @WebLocator(id = "area-input")
    @WebElementProperty(name = "placeholder", extractor = PlaceholderAttributeExtractor.class)
    WebTextInput areaInput();

    @Name("Area input text")
    @WebLocator(id = "area-input-text")
    WebTextBlock areaInputText();


    @Name("Checkbox one")
    @WebLocator(xpath = ".//*[@id = 'checkbox-one']/parent::node()")
    @WebLocator(component = FOCUS, xpath = ".//input")
    @WebElementProperty(name = "name", extractor = NameAttributeExtractor.class, webLocator = @WebLocator(id = "checkbox-one"))
    WebCheckbox checkboxOne();

    @Name("Checkbox two")
    @WebLocator(xpath = ".//*[@id = 'checkbox-two']/parent::node()")
    @WebLocator(component = FOCUS, xpath = ".//input")
    @WebElementProperty(name = "name", extractor = NameAttributeExtractor.class, webLocator = @WebLocator(id = "checkbox-two"))
    WebCheckbox checkboxTwo();

    @Name("Checkbox three")
    @WebLocator(xpath = ".//*[@id = 'checkbox-three']/parent::node()")
    @WebLocator(component = FOCUS, xpath = ".//input")
    @WebElementProperty(name = "name", extractor = NameAttributeExtractor.class, webLocator = @WebLocator(id = "checkbox-three"))
    WebCheckbox checkboxThree();

    @Name("Checkbox text")
    @WebLocator(id = "checkbox-text")
    WebTextBlock checkboxText();


    @Name("RadioButton one")
    @WebLocator(xpath = ".//*[@id = 'radio-one']/parent::node()")
    @WebLocator(component = FOCUS, xpath = ".//input")
    @WebElementProperty(name = "name", extractor = NameAttributeExtractor.class, webLocator = @WebLocator(id = "radio-one"))
    WebRadioButton radioButtonOne();

    @Name("Radio group")
    @WebLocator(id = "radio-group")
    @WebLocator(component = RADIO, xpath = ".//input[@type = 'radio']/parent::node()", single = false)
    // Это дефолтная имплементация настроенного экземпляра кнопки, использовать эту аннотацию имеет смысл только если у вас она другая
    // @UseWebMappedBlock(DefaultWebRadioButtonMappedBlock.class)
    WebRadioGroup radioGroup();

    @Name("RadioButton text")
    @WebLocator(id = "radio-text")
    WebTextBlock radioButtonText();


    @Name("File input")
    @WebLocator(xpath = ".//*[@id = 'file-input']/parent::node()")
    WebFileInput fileInput();

    @Name("File input text")
    @WebLocator(id = "file-input-file-name")
    WebTextBlock fileInputText();

    @Name("File download")
    @WebLocator(id = "file-download")
    WebLink fileDownloadLink();


    @WebLocator(id = "drag-and-drop-source")
    @WebElementInteraction(name = "Drag and Drop", implementation = DragAndDropInteraction.class)
    WebTextBlock sourceBlock();

    @WebLocator(id = "drag-and-drop-target")
    WebTextBlock targetBlock();

    @WebLocator(id = "drag-and-drop-text")
    WebTextBlock dragAndDropText();


    @WebLocator(id = "double-click-button")
    @WebElementAction(name = "Double click", implementation = DoubleClickActionImplementation.class)
    WebButton doubleClickButton();

    @WebLocator(id = "double-click-text")
    WebTextBlock doubleClickText();

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
