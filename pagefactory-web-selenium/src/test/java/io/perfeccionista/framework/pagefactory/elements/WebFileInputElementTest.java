package io.perfeccionista.framework.pagefactory.elements;

import io.perfeccionista.framework.Environment;
import io.perfeccionista.framework.color.Color;
import io.perfeccionista.framework.exceptions.WebElementColor.WebElementColorAssertionError;
import io.perfeccionista.framework.exceptions.WebElementDimensions.WebElementDimensionsAssertionError;
import io.perfeccionista.framework.exceptions.WebElementIsDisplayed.WebElementIsDisplayedAssertionError;
import io.perfeccionista.framework.exceptions.WebElementIsEnabled.WebElementIsEnabledAssertionError;
import io.perfeccionista.framework.exceptions.WebElementIsPresent.WebElementIsPresentAssertionError;
import io.perfeccionista.framework.exceptions.WebElementLocation.WebElementLocationAssertionError;
import io.perfeccionista.framework.exceptions.WebElementNotInFocus.WebElementNotInFocusAssertionError;
import io.perfeccionista.framework.exceptions.WebElementTextValue.WebElementTextValueAssertionError;
import io.perfeccionista.framework.invocation.runner.InvocationName;
import io.perfeccionista.framework.invocation.timeouts.CheckTimeout;
import io.perfeccionista.framework.matcher.element.WebLinkMatcher;
import io.perfeccionista.framework.name.WebElementIdentifier;
import io.perfeccionista.framework.pagefactory.AbstractUiTest;
import io.perfeccionista.framework.pagefactory.elements.preferences.DefaultSeleniumWebPageFactoryPreferences;
import io.perfeccionista.framework.pagefactory.browser.WebBrowserDispatcher;
import io.perfeccionista.framework.pagefactory.context.base.WebPageContext;
import io.perfeccionista.framework.measurements.Dimensions;
import io.perfeccionista.framework.measurements.Location;
import io.perfeccionista.framework.measurements.Point;
import io.perfeccionista.framework.pagefactory.factory.WebPageFactory;
import io.perfeccionista.framework.pagefactory.pageobjects.ElementsPage;
import io.perfeccionista.framework.pagefactory.pageobjects.HomePage;
import io.perfeccionista.framework.screenshots.Screenshot;
import io.perfeccionista.framework.utils.FileUtils;
import io.perfeccionista.framework.value.ValueService;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.nio.file.Path;
import java.time.Duration;

import static io.perfeccionista.framework.invocation.wrappers.CheckInvocationWrapper.runCheck;
import static io.perfeccionista.framework.matcher.WebElementAssertions.beDisabled;
import static io.perfeccionista.framework.matcher.WebElementAssertions.beDisplayed;
import static io.perfeccionista.framework.matcher.WebElementAssertions.beEnabled;
import static io.perfeccionista.framework.matcher.WebElementAssertions.beInFocus;
import static io.perfeccionista.framework.matcher.WebElementAssertions.bePresent;
import static io.perfeccionista.framework.matcher.WebElementAssertions.haveColor;
import static io.perfeccionista.framework.matcher.WebElementAssertions.haveDimensions;
import static io.perfeccionista.framework.matcher.WebElementAssertions.haveLabel;
import static io.perfeccionista.framework.matcher.WebElementAssertions.haveLocation;
import static io.perfeccionista.framework.matcher.WebElementAssertions.haveText;
import static io.perfeccionista.framework.matcher.WebElementAssertions.notBeDisplayed;
import static io.perfeccionista.framework.matcher.WebElementAssertions.notBeInFocus;
import static io.perfeccionista.framework.matcher.WebElementAssertions.notBePresent;
import static io.perfeccionista.framework.matcher.WebElementAssertions.notHaveColor;
import static io.perfeccionista.framework.matcher.WebElementAssertions.notHaveDimensions;
import static io.perfeccionista.framework.matcher.WebElementAssertions.notHaveLabel;
import static io.perfeccionista.framework.matcher.WebElementAssertions.notHaveLocation;
import static io.perfeccionista.framework.matcher.WebElementAssertions.notHaveText;
import static io.perfeccionista.framework.pagefactory.elements.actions.WebElementActionNames.CLICK_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.actions.WebElementActionNames.IS_ON_THE_SCREEN_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.actions.WebElementActionNames.SET_FILENAME_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.actions.WebElementActionNames.SHOULD_FILE_EXIST_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.components.WebComponents.LABEL;
import static io.perfeccionista.framework.pagefactory.elements.components.WebComponents.ROOT;
import static io.perfeccionista.framework.pagefactory.elements.actions.WebElementActionNames.CLEAR_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.actions.WebElementActionNames.GET_COLOR_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.actions.WebElementActionNames.GET_DIMENSIONS_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.actions.WebElementActionNames.GET_LABEL_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.actions.WebElementActionNames.GET_LOCATION_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.actions.WebElementActionNames.GET_PROPERTY_VALUE_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.actions.WebElementActionNames.GET_SCREENSHOT_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.actions.WebElementActionNames.GET_TEXT_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.actions.WebElementActionNames.HOVER_TO_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.actions.WebElementActionNames.IS_COMPONENT_DISPLAYED_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.actions.WebElementActionNames.IS_COMPONENT_PRESENT_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.actions.WebElementActionNames.IS_DISPLAYED_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.actions.WebElementActionNames.IS_ENABLED_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.actions.WebElementActionNames.IS_IN_FOCUS_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.actions.WebElementActionNames.IS_PRESENT_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.actions.WebElementActionNames.SCROLL_TO_METHOD;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;

@Tag("WebElement") @Tag("WebFileInput")
class WebFileInputElementTest extends AbstractUiTest {

    @Test
    void webFileInputInitializationTest(Environment env) {
        WebPageFactory pageFactory = new WebPageFactory(new DefaultSeleniumWebPageFactoryPreferences());
        ElementsPage elementsPage = (ElementsPage) pageFactory.createWebPage(ElementsPage.class);
        elementsPage.setEnvironment(env);
        elementsPage.setWebBrowserDispatcher(mock(WebBrowserDispatcher.class));
        WebFileInput fileInput = elementsPage.fileInput();
        assertNotNull(fileInput.getEnvironment());
        assertNotNull(fileInput.getLocatorChain());
        assertNotNull(fileInput.getWebBrowserDispatcher());
        assertNotNull(fileInput.getOptionalLocator(ROOT));
        // WebTextInput
        assertNotNull(fileInput.getActionImplementation(CLEAR_METHOD, Void.class));
        assertNotNull(fileInput.getActionImplementation(CLICK_METHOD, Void.class));
        assertNotNull(fileInput.getActionImplementation(SET_FILENAME_METHOD, Void.class));
        assertNotNull(fileInput.getActionImplementation(IS_ENABLED_METHOD, Boolean.class));
        assertNotNull(fileInput.getActionImplementation(GET_TEXT_METHOD, String.class));
        assertNotNull(fileInput.getActionImplementation(GET_LABEL_METHOD, String.class));
        // WebChildElement
        assertNotNull(fileInput.getActionImplementation(GET_COLOR_METHOD, Color.class));
        assertNotNull(fileInput.getActionImplementation(GET_DIMENSIONS_METHOD, Dimensions.class));
        assertNotNull(fileInput.getActionImplementation(GET_LOCATION_METHOD, Location.class));
        assertNotNull(fileInput.getActionImplementation(GET_PROPERTY_VALUE_METHOD, String.class));
        assertNotNull(fileInput.getActionImplementation(GET_SCREENSHOT_METHOD, Screenshot.class));
        assertNotNull(fileInput.getActionImplementation(HOVER_TO_METHOD, Void.class));
        assertNotNull(fileInput.getActionImplementation(IS_COMPONENT_DISPLAYED_METHOD, Boolean.class));
        assertNotNull(fileInput.getActionImplementation(IS_COMPONENT_PRESENT_METHOD, Boolean.class));
        assertNotNull(fileInput.getActionImplementation(IS_DISPLAYED_METHOD, Boolean.class));
        assertNotNull(fileInput.getActionImplementation(IS_IN_FOCUS_METHOD, Boolean.class));
        assertNotNull(fileInput.getActionImplementation(IS_ON_THE_SCREEN_METHOD, Boolean.class));
        assertNotNull(fileInput.getActionImplementation(IS_PRESENT_METHOD, Boolean.class));
        assertNotNull(fileInput.getActionImplementation(SCROLL_TO_METHOD, Void.class));
        // Identifier
        WebElementIdentifier elementIdentifier = fileInput.getElementIdentifier();
        assertEquals("fileInput", elementIdentifier.getElementMethod().getName());
        assertEquals("fileInput", elementIdentifier.getLastUsedName());
        assertTrue(elementIdentifier.containsName("File input"));
        assertFalse(elementIdentifier.isNameDeprecated("File input"));
        assertEquals(2, elementIdentifier.names().size());
        WebFileInput fileInputElement = elementsPage.getElementRegistry()
                .getRequiredElementByPath("File input", WebFileInput.class);
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
        context.getPage(HomePage.class).leftMenu()
                .select("Elements");
        ElementsPage elementsPage = context.getPage(ElementsPage.class);
        WebTextBlock fileInputText = elementsPage.fileInputText()
                .should(bePresent())
                .should(beDisplayed())
                .should(haveText(value.stringEmpty()));
        // Check Simple input
        WebFileInput fileInput = elementsPage.fileInput()
                .should(bePresent())
                .should(beDisplayed())
                .should(notBeInFocus())
                .should(beEnabled())
                .scrollTo()
                .should(haveDimensions(Dimensions.of(382.5d, 38.0d).setInaccuracy(0.2d)))
                .should(haveLocation(Location.relative(345.0d, 753.4d).setInaccuracy(0.2d)))
                .should(haveColor(LABEL, "border-color", Color.of(220, 53, 69, 1.0d)))
                .hoverTo(true)
                .should(haveText(value.stringEmpty()))
                .should(notHaveText(value.stringContains("input")))
                .should(haveLabel("Choose a file"))
                .should(notHaveLabel("Choose a word"))
                // Метод loadFile(Path filePath)
                .setFileName(this.getClass().getClassLoader().getResource("test.data.properties").getPath())
                .should(haveColor(LABEL, "border-color", Color.of(40, 167, 69, 1.0d)))
                .should(haveText(value.stringContains("test.data.properties")));
        fileInputText
                .should(haveText("test.data.properties"));
        fileInput.clear()
                .should(haveText(value.stringEmpty()))
                .should(haveColor(LABEL, "border-color", Color.of(220, 53, 69, 1.0d)));
        fileInputText
                .should(haveText(value.stringEmpty()));
        fileInput
                .should(beEnabled())
                .should(haveText(value.stringEmpty()));
        assertTrue(fileInput.isPresent());
        assertTrue(fileInput.isDisplayed());
        assertFalse(fileInput.isInFocus());
        assertTrue(fileInput.isEnabled());
        assertEquals(Dimensions.of(382.5d, 38.0d).setInaccuracy(0.2d), fileInput.getDimensions(ROOT));
        assertEquals(Location.absolute(345.0d, 753.4d).setInaccuracy(0.2d), fileInput.getLocation(ROOT));
        assertEquals(Color.of(220, 53, 69, 1.0d), fileInput.getColor(LABEL, "border-color"));
        Screenshot screenshot = fileInput.getScreenshot(ROOT);
        assertNotNull(screenshot);
        assertTrue(value.intGreaterThan(4500).check(screenshot.getRaw().length));
        assertEquals(Point.of(191.3d, 19d).setInaccuracy(0.2d), fileInput.getDimensions(ROOT).getCenter());
    }

    @Test
    void webTextInputNegativeTest(Environment env, ValueService value) {
        WebPageContext context = initWebPageContext(env, value);
        context.getPage(HomePage.class).leftMenu()
                .select("Elements");
        ElementsPage elementsPage = context.getPage(ElementsPage.class);
        WebFileInput fileInput = elementsPage.fileInput();
        // Выполнение этого метода показывает завершение загрузки страницы
        fileInput
                .should(beDisplayed());
        // Для негативных сценариев меняем время ожидания, чтобы не ждать по 5 секунд проброса ошибки вне враппера
        env.getEnvironmentConfiguration().getTimeouts()
                .setTimeout(CheckTimeout.class, Duration.ofMillis(100L));
        assertThrows(WebElementIsPresentAssertionError.class,
                () -> fileInput.should(notBePresent()));
        assertThrows(WebElementIsDisplayedAssertionError.class,
                () -> fileInput.should(notBeDisplayed()));
        assertThrows(WebElementNotInFocusAssertionError.class,
                () -> fileInput.should(beInFocus()));
        assertThrows(WebElementIsEnabledAssertionError.class,
                () -> fileInput.should(beDisabled()));
        Dimensions elementDimensions = Dimensions.of(382.5d, 38.0d).setInaccuracy(0.2d);
        assertThrows(WebElementDimensionsAssertionError.class,
                () -> fileInput.should(notHaveDimensions(elementDimensions)));
        Location elementLocation = Location.relative(345.0d, 753.4d).setInaccuracy(0.2d);
        assertThrows(WebElementLocationAssertionError.class,
                () -> fileInput.should(notHaveLocation(elementLocation)));
        Color elementColor = Color.of(220, 53, 69, 1.0d);
        assertThrows(WebElementColorAssertionError.class,
                () -> fileInput.should(notHaveColor(LABEL, "border-color", elementColor)));
        assertThrows(WebElementTextValueAssertionError.class,
                () -> fileInput.should(notHaveText(value.stringEmpty())));
        assertThrows(WebElementTextValueAssertionError.class,
                () -> fileInput.should(haveText("TextInput text")));
        fileInput
                .setFileName(this.getClass().getClassLoader().getResource("test.data.properties").getPath());
        assertThrows(WebElementTextValueAssertionError.class,
                () -> fileInput.should(haveText(value.stringEmpty())));
        assertThrows(WebElementTextValueAssertionError.class,
                () -> fileInput.should(notHaveText(value.stringContains("test.data.properties"))));
    }

    @Test
    void webFileDownloadTest(Environment env, ValueService value) {
        WebPageContext context = initWebPageContext(env, value);
        context.getPage(HomePage.class).leftMenu()
                .select("Elements");
        ElementsPage elementsPage = context.getPage(ElementsPage.class);
        String separator = System.getProperty("file.separator");
        String downloadFile = System.getProperty("user.home") + separator + "Downloads" + separator + "LICENSE.txt";
        FileUtils.deleteFileIgnoreExceptions(Path.of(downloadFile));
        elementsPage.fileDownloadLink()
                .should(beDisplayed())
                .click()
                .should((WebLinkMatcher) element -> {
                    runCheck(element.getEnvironment(), InvocationName.assertInvocation(SHOULD_FILE_EXIST_METHOD, element),
                            () -> FileUtils.fileShouldExist(Path.of(downloadFile)));
                });
    }

}
