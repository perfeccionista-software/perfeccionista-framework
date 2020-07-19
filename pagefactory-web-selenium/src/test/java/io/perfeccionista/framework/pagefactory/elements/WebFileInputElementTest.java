package io.perfeccionista.framework.pagefactory.elements;

import io.perfeccionista.framework.Environment;
import io.perfeccionista.framework.attachment.JsonAttachmentEntry;
import io.perfeccionista.framework.exceptions.ElementColorException;
import io.perfeccionista.framework.exceptions.ElementDimensionsException;
import io.perfeccionista.framework.exceptions.ElementIsDisplayedException;
import io.perfeccionista.framework.exceptions.ElementIsEnabledException;
import io.perfeccionista.framework.exceptions.ElementIsPresentException;
import io.perfeccionista.framework.exceptions.ElementLocationException;
import io.perfeccionista.framework.exceptions.ElementNotDeclaredException;
import io.perfeccionista.framework.exceptions.ElementNotDisplayedException;
import io.perfeccionista.framework.exceptions.ElementNotInFocusException;
import io.perfeccionista.framework.exceptions.ElementTextValueException;
import io.perfeccionista.framework.invocation.timeouts.CheckTimeout;
import io.perfeccionista.framework.name.WebElementIdentifier;
import io.perfeccionista.framework.pagefactory.AbstractUiTest;
import io.perfeccionista.framework.pagefactory.DefaultSeleniumWebElementsConfiguration;
import io.perfeccionista.framework.pagefactory.browser.WebBrowserDispatcher;
import io.perfeccionista.framework.pagefactory.browser.context.WebPageContext;
import io.perfeccionista.framework.pagefactory.elements.methods.Dimensions;
import io.perfeccionista.framework.pagefactory.elements.methods.Location;
import io.perfeccionista.framework.pagefactory.factory.WebPageFactory;
import io.perfeccionista.framework.pagefactory.pageobjects.ElementsPage;
import io.perfeccionista.framework.pagefactory.pageobjects.HomePage;
import io.perfeccionista.framework.pagefactory.screenshots.Screenshot;
import io.perfeccionista.framework.plugin.WebElementColor;
import io.perfeccionista.framework.utils.FileUtils;
import io.perfeccionista.framework.value.ValueService;
import org.junit.jupiter.api.Test;

import java.nio.file.Path;
import java.time.Duration;

import static io.perfeccionista.framework.exceptions.base.ExceptionType.ASSERT;
import static io.perfeccionista.framework.exceptions.messages.PageFactoryMessages.ELEMENT_NOT_DECLARED;
import static io.perfeccionista.framework.exceptions.messages.PageFactoryMessages.ELEMENT_NOT_DISPLAYED;
import static io.perfeccionista.framework.pagefactory.elements.components.WebComponents.LABEL;
import static io.perfeccionista.framework.pagefactory.elements.components.WebComponents.ROOT;
import static io.perfeccionista.framework.pagefactory.elements.methods.AvailableElementMethods.SUBMIT_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.CLEAR_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.COMPONENT_SHOULD_BE_DISPLAYED_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.COMPONENT_SHOULD_BE_PRESENT_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.COMPONENT_SHOULD_NOT_BE_DISPLAYED_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.COMPONENT_SHOULD_NOT_BE_PRESENT_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.GET_COLOR_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.GET_DIMENSIONS_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.GET_LABEL_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.GET_LOCATION_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.GET_PROPERTY_VALUE_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.GET_SCREENSHOT_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.GET_TEXT_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.HOVER_TO_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.IS_COMPONENT_DISPLAYED_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.IS_COMPONENT_PRESENT_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.IS_DISPLAYED_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.IS_ENABLED_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.IS_IN_FOCUS_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.IS_PRESENT_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.SCROLL_TO_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.SEND_KEYS_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.SHOULD_BE_DISABLED_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.SHOULD_BE_DISPLAYED_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.SHOULD_BE_ENABLED_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.SHOULD_BE_IN_FOCUS_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.SHOULD_BE_PRESENT_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.SHOULD_HAVE_COLOR_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.SHOULD_HAVE_DIMENSIONS_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.SHOULD_HAVE_LOCATION_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.SHOULD_HAVE_NUMBER_LABEL_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.SHOULD_HAVE_NUMBER_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.SHOULD_HAVE_PROPERTY_NUMBER_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.SHOULD_HAVE_PROPERTY_VALUE_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.SHOULD_HAVE_TEXT_LABEL_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.SHOULD_HAVE_TEXT_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.SHOULD_LOOKS_LIKE_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.SHOULD_NOT_BE_DISPLAYED_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.SHOULD_NOT_BE_IN_FOCUS_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.SHOULD_NOT_BE_PRESENT_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.SHOULD_NOT_HAVE_COLOR_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.SHOULD_NOT_HAVE_DIMENSIONS_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.SHOULD_NOT_HAVE_LOCATION_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.SHOULD_NOT_HAVE_NUMBER_LABEL_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.SHOULD_NOT_HAVE_NUMBER_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.SHOULD_NOT_HAVE_PROPERTY_NUMBER_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.SHOULD_NOT_HAVE_PROPERTY_VALUE_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.SHOULD_NOT_HAVE_TEXT_LABEL_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.SHOULD_NOT_HAVE_TEXT_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.SHOULD_NOT_LOOKS_LIKE_METHOD;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;

class WebFileInputElementTest extends AbstractUiTest {

    @Test
    void webFileInputInitializationTest(Environment env) {
        WebPageFactory pageFactory = new WebPageFactory(new DefaultSeleniumWebElementsConfiguration());
        ElementsPage elementsPage = (ElementsPage) pageFactory.createWebPage(ElementsPage.class);
        elementsPage.setEnvironment(env);
        elementsPage.setWebBrowserDispatcher(mock(WebBrowserDispatcher.class));
        WebFileInput fileInput = elementsPage.fileInput();
        assertNotNull(fileInput.getEnvironment());
        assertNotNull(fileInput.getLocator(ROOT));
        assertNotNull(fileInput.getLocatorChain());
        assertEquals(elementsPage, fileInput.getParent());
        WebBrowserDispatcher webBrowserDispatcher = fileInput.getWebBrowserDispatcher();
        assertNotNull(webBrowserDispatcher);
        // WebTextInput
        assertNotNull(fileInput.getActionImplementation(CLEAR_METHOD, Void.class));
        assertNotNull(fileInput.getActionImplementation(SEND_KEYS_METHOD, Void.class));
        assertNotNull(fileInput.getActionImplementation(IS_ENABLED_METHOD, Boolean.class));
        assertNotNull(fileInput.getActionImplementation(SHOULD_BE_ENABLED_METHOD, Void.class));
        assertNotNull(fileInput.getActionImplementation(SHOULD_BE_DISABLED_METHOD, Void.class));
        assertNotNull(fileInput.getActionImplementation(GET_TEXT_METHOD, String.class));
        assertNotNull(fileInput.getActionImplementation(SHOULD_HAVE_TEXT_METHOD, Void.class));
        assertNotNull(fileInput.getActionImplementation(SHOULD_HAVE_NUMBER_METHOD, Void.class));
        assertNotNull(fileInput.getActionImplementation(SHOULD_NOT_HAVE_TEXT_METHOD, Void.class));
        assertNotNull(fileInput.getActionImplementation(SHOULD_NOT_HAVE_NUMBER_METHOD, Void.class));
        assertNotNull(fileInput.getActionImplementation(GET_LABEL_METHOD, String.class));
        assertNotNull(fileInput.getActionImplementation(SHOULD_HAVE_TEXT_LABEL_METHOD, Void.class));
        assertNotNull(fileInput.getActionImplementation(SHOULD_HAVE_NUMBER_LABEL_METHOD, Void.class));
        assertNotNull(fileInput.getActionImplementation(SHOULD_NOT_HAVE_TEXT_LABEL_METHOD, Void.class));
        assertNotNull(fileInput.getActionImplementation(SHOULD_NOT_HAVE_NUMBER_LABEL_METHOD, Void.class));
        // WebChildElement
        assertNotNull(fileInput.getActionImplementation(COMPONENT_SHOULD_BE_DISPLAYED_METHOD, Void.class));
        assertNotNull(fileInput.getActionImplementation(COMPONENT_SHOULD_BE_PRESENT_METHOD, Void.class));
        assertNotNull(fileInput.getActionImplementation(COMPONENT_SHOULD_NOT_BE_DISPLAYED_METHOD, Void.class));
        assertNotNull(fileInput.getActionImplementation(COMPONENT_SHOULD_NOT_BE_PRESENT_METHOD, Void.class));
        assertNotNull(fileInput.getActionImplementation(GET_COLOR_METHOD, WebElementColor.class));
        assertNotNull(fileInput.getActionImplementation(GET_DIMENSIONS_METHOD, Dimensions.class));
        assertNotNull(fileInput.getActionImplementation(GET_LOCATION_METHOD, Location.class));
        assertNotNull(fileInput.getActionImplementation(GET_PROPERTY_VALUE_METHOD, String.class));
        assertNotNull(fileInput.getActionImplementation(GET_SCREENSHOT_METHOD, Screenshot.class));
        assertNotNull(fileInput.getActionImplementation(HOVER_TO_METHOD, Void.class));
        assertNotNull(fileInput.getActionImplementation(IS_COMPONENT_DISPLAYED_METHOD, Boolean.class));
        assertNotNull(fileInput.getActionImplementation(IS_COMPONENT_PRESENT_METHOD, Boolean.class));
        assertNotNull(fileInput.getActionImplementation(IS_DISPLAYED_METHOD, Boolean.class));
        assertNotNull(fileInput.getActionImplementation(IS_IN_FOCUS_METHOD, Boolean.class));
        assertNotNull(fileInput.getActionImplementation(IS_PRESENT_METHOD, Boolean.class));
        assertNotNull(fileInput.getActionImplementation(SCROLL_TO_METHOD, Void.class));
        assertNotNull(fileInput.getActionImplementation(SHOULD_BE_DISPLAYED_METHOD, Void.class));
        assertNotNull(fileInput.getActionImplementation(SHOULD_BE_IN_FOCUS_METHOD, Void.class));
        assertNotNull(fileInput.getActionImplementation(SHOULD_BE_PRESENT_METHOD, Void.class));
        assertNotNull(fileInput.getActionImplementation(SHOULD_HAVE_COLOR_METHOD, Void.class));
        assertNotNull(fileInput.getActionImplementation(SHOULD_HAVE_DIMENSIONS_METHOD, Void.class));
        assertNotNull(fileInput.getActionImplementation(SHOULD_HAVE_LOCATION_METHOD, Void.class));
        assertNotNull(fileInput.getActionImplementation(SHOULD_HAVE_PROPERTY_NUMBER_METHOD, Void.class));
        assertNotNull(fileInput.getActionImplementation(SHOULD_HAVE_PROPERTY_VALUE_METHOD, Void.class));
        assertNotNull(fileInput.getActionImplementation(SHOULD_LOOKS_LIKE_METHOD, Void.class));
        assertNotNull(fileInput.getActionImplementation(SHOULD_NOT_BE_DISPLAYED_METHOD, Void.class));
        assertNotNull(fileInput.getActionImplementation(SHOULD_NOT_BE_IN_FOCUS_METHOD, Void.class));
        assertNotNull(fileInput.getActionImplementation(SHOULD_NOT_BE_PRESENT_METHOD, Void.class));
        assertNotNull(fileInput.getActionImplementation(SHOULD_NOT_HAVE_COLOR_METHOD, Void.class));
        assertNotNull(fileInput.getActionImplementation(SHOULD_NOT_HAVE_DIMENSIONS_METHOD, Void.class));
        assertNotNull(fileInput.getActionImplementation(SHOULD_NOT_HAVE_LOCATION_METHOD, Void.class));
        assertNotNull(fileInput.getActionImplementation(SHOULD_NOT_HAVE_PROPERTY_NUMBER_METHOD, Void.class));
        assertNotNull(fileInput.getActionImplementation(SHOULD_NOT_HAVE_PROPERTY_VALUE_METHOD, Void.class));
        assertNotNull(fileInput.getActionImplementation(SHOULD_NOT_LOOKS_LIKE_METHOD, Void.class));
        // Identifier
        WebElementIdentifier elementIdentifier = fileInput.getElementIdentifier();
        assertEquals("fileInput", elementIdentifier.getElementMethod().getName());
        assertEquals("fileInput", elementIdentifier.getLastUsedName());
        assertTrue(elementIdentifier.containsName("File input"));
        assertFalse(elementIdentifier.isNameDeprecated("File input"));
        assertEquals(1, elementIdentifier.names().size());
        WebFileInput fileInputElement = elementsPage.getElementRegistry().getElementByPath("File input", WebFileInput.class)
                .orElseThrow(() -> new ElementNotDeclaredException(ELEMENT_NOT_DECLARED.getMessage("File input")));
        assertNotNull(fileInputElement);
        assertEquals("File input", elementIdentifier.getLastUsedName());
        assertNotNull(fileInput.toString());
        // Смотрим описание элемента
        System.out.println( fileInput.toString() );
    }

    /**
     * Case: Проверяем что присутствует левое меню
     *  Проверяем текст заголовка
     *  Проверяем основной текст
     * @param env - Экземпляр окружения теста
     * @param value - Экземпляр сервиса значений
     */
    @Test
    void webFileInputPositiveTest(Environment env, ValueService value) {
        WebPageContext context = initWebPageContext(env, value);
        context.getPage(HomePage.class)
                .leftMenu()
                .select(value.stringEquals("Elements"));
        ElementsPage elementsPage = context.getPage(ElementsPage.class);
        WebTextBlock fileInputText = elementsPage.fileInputText()
                .shouldBePresent()
                .shouldBeDisplayed()
                .shouldHaveText(value.stringEmpty());
        // Check Simple input
        WebFileInput fileInput = elementsPage.fileInput()
                .shouldBePresent()
                .shouldBeDisplayed()
                .shouldNotBeInFocus()
                .shouldBeEnabled()
                .scrollTo()
                .componentShouldHaveDimensions(ROOT, Dimensions.of(382.5d, 38.0d).setInaccuracy(0.2d))
                .componentShouldHaveLocation(ROOT, Location.relative(345.0d, 753.4d).setInaccuracy(0.2d))
                .componentShouldHaveColor(LABEL, "border-color", WebElementColor.of(220, 53, 69, 1.0d))
                .hoverTo(true)
                .shouldHaveText(value.stringEmpty())
                .shouldNotHaveText(value.stringContains("input"))
                .shouldHaveLabel(value.stringEquals("Choose a file"))
                .shouldNotHaveLabel(value.stringEquals("Choose a word"))
                // Метод loadFile(Path filePath)
                .sendKeys(this.getClass().getClassLoader().getResource("test.data.properties").getPath())
                .componentShouldHaveColor(LABEL, "border-color", WebElementColor.of(40, 167, 69, 1.0d))
                .shouldHaveText(value.stringContains("test.data.properties"));
        fileInputText.shouldHaveText(value.stringEquals("test.data.properties"));
        fileInput.clear()
                .shouldHaveText(value.stringEmpty())
                .componentShouldHaveColor(LABEL, "border-color", WebElementColor.of(220, 53, 69, 1.0d));
        fileInputText
                .shouldHaveText(value.stringEmpty());
        fileInput.shouldBeEnabled()
                .shouldHaveText(value.stringEmpty())
                .should(simpleButtonElement -> {
                    if (simpleButtonElement.isDisplayed()) {
                        return;
                    }
                    throw new ElementNotDisplayedException(ELEMENT_NOT_DISPLAYED.getMessage(simpleButtonElement.getElementIdentifier().getLastUsedName()))
                            .setType(ASSERT)
                            .addAttachmentEntry(JsonAttachmentEntry.of("Element", simpleButtonElement.toJson()));
                });
        assertTrue(fileInput.isPresent());
        assertTrue(fileInput.isDisplayed());
        assertFalse(fileInput.isInFocus());
        assertTrue(fileInput.isEnabled());
        assertEquals(Dimensions.of(382.5d, 38.0d).setInaccuracy(0.2d), fileInput.getDimensions(ROOT));
        assertEquals(Location.absolute(345.0d, 753.4d).setInaccuracy(0.2d), fileInput.getLocation(ROOT));
        assertEquals(WebElementColor.of(220, 53, 69, 1.0d), fileInput.getColor(LABEL, "border-color"));
        Screenshot screenshot = fileInput.getScreenshot(ROOT);
        assertNotNull(screenshot);
        assertTrue(value.intGreaterThan(4500).check(screenshot.getRaw().length));
    }

    @Test
    void webTextInputNegativeTest(Environment env, ValueService value) {
        WebPageContext context = initWebPageContext(env, value);
        context.getPage(HomePage.class)
                .leftMenu()
                .select(value.stringEquals("Elements"));
        ElementsPage elementsPage = context.getPage(ElementsPage.class);
        WebFileInput fileInput = elementsPage.fileInput();
        // Выполнение этого метода показывает завершение загрузки страницы
        fileInput.shouldBeDisplayed();
        // Для негативных сценариев меняем время ожидания, чтобы не ждать по 5 секунд проброса ошибки вне враппера
        env.getEnvironmentConfiguration().getTimeouts().setTimeout(CheckTimeout.class, Duration.ofMillis(100L));
        assertThrows(ElementIsPresentException.class, fileInput::shouldNotBePresent);
        assertThrows(ElementIsDisplayedException.class, fileInput::shouldNotBeDisplayed);
        assertThrows(ElementNotInFocusException.class, fileInput::shouldBeInFocus);
        assertThrows(ElementIsEnabledException.class, fileInput::shouldBeDisabled);
        Dimensions elementDimensions = Dimensions.of(382.5d, 38.0d).setInaccuracy(0.2d);
        assertThrows(ElementDimensionsException.class, () -> fileInput.componentShouldNotHaveDimensions(ROOT, elementDimensions));
        Location elementLocation = Location.of(345.0d, 753.4d, 345.0d, 753.4d).setInaccuracy(0.2d);
        assertThrows(ElementLocationException.class, () -> fileInput.componentShouldNotHaveLocation(ROOT, elementLocation));
        WebElementColor elementColor = WebElementColor.of(220, 53, 69, 1.0d);
        assertThrows(ElementColorException.class, () -> fileInput.componentShouldNotHaveColor(LABEL, "border-color", elementColor));
        assertThrows(ElementTextValueException.class, () -> fileInput.shouldNotHaveText(value.stringEmpty()));
        assertThrows(ElementTextValueException.class, () -> fileInput.shouldHaveText(value.stringEquals("TextInput text")));
        fileInput.sendKeys(this.getClass().getClassLoader().getResource("test.data.properties").getPath());
        assertThrows(ElementTextValueException.class, () -> fileInput.shouldHaveText(value.stringEmpty()));
        assertThrows(ElementTextValueException.class, () -> fileInput.shouldNotHaveText(value.stringContains("test.data.properties")));
    }

    @Test
    void webFileDownloadTest(Environment env, ValueService value) {
        WebPageContext context = initWebPageContext(env, value);
        context.getPage(HomePage.class)
                .leftMenu()
                .select(value.stringEquals("Elements"));
        ElementsPage elementsPage = context.getPage(ElementsPage.class);
        String separator = System.getProperty("file.separator");
        String downloadFile = System.getProperty("user.home") + separator + "Downloads" + separator + "LICENSE.txt";
        FileUtils.deleteIgnoreExceptions(Path.of(downloadFile));
        elementsPage.fileDownloadLink()
                .shouldBeDisplayed()
                .click()
                .should(webChildElement -> {
                    FileUtils.shouldExist(Path.of(downloadFile));
                });
    }

}
