package io.perfeccionista.framework.pagefactory.elements;

import io.perfeccionista.framework.Environment;
import io.perfeccionista.framework.color.Color;
import io.perfeccionista.framework.exceptions.WebElementColor.WebElementColorAssertionError;
import io.perfeccionista.framework.exceptions.WebElementDimensions.WebElementDimensionsAssertionError;
import io.perfeccionista.framework.exceptions.ElementIsDisplayed.ElementIsDisplayedAssertionError;
import io.perfeccionista.framework.exceptions.WebElementIsEnabled.WebElementIsEnabledAssertionError;
import io.perfeccionista.framework.exceptions.ElementIsPresent.ElementIsPresentAssertionError;
import io.perfeccionista.framework.exceptions.WebElementLocation.WebElementLocationAssertionError;
import io.perfeccionista.framework.exceptions.WebElementNotInFocus.WebElementNotInFocusAssertionError;
import io.perfeccionista.framework.exceptions.WebElementTextValue.WebElementTextValueAssertionError;
import io.perfeccionista.framework.invocation.runner.InvocationInfo;
import io.perfeccionista.framework.invocation.timeouts.TimeoutsService;
import io.perfeccionista.framework.invocation.timeouts.type.CheckTimeout;
import io.perfeccionista.framework.matcher.element.WebTextMatcher;
import io.perfeccionista.framework.name.WebElementIdentifier;
import io.perfeccionista.framework.AbstractWebSeleniumParallelTest;
import io.perfeccionista.framework.pagefactory.elements.preferences.DefaultSeleniumWebPageFactoryPreferences;
import io.perfeccionista.framework.pagefactory.dispatcher.WebBrowserDispatcher;
import io.perfeccionista.framework.pagefactory.dispatcher.context.WebPageContext;
import io.perfeccionista.framework.measurements.Dimensions2D;
import io.perfeccionista.framework.measurements.Point2D;
import io.perfeccionista.framework.pagefactory.factory.WebPageFactory;
import io.perfeccionista.framework.pagefactory.pageobjects.ElementsPage;
import io.perfeccionista.framework.pagefactory.pageobjects.HomePage;
import io.perfeccionista.framework.screenshots.Screenshot;
import io.perfeccionista.framework.utils.FileUtils;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.nio.file.Paths;
import java.time.Duration;

import static io.perfeccionista.framework.invocation.wrapper.CheckInvocationWrapper.runCheck;
import static io.perfeccionista.framework.Web.*;
import static io.perfeccionista.framework.pagefactory.elements.ElementActionNames.CLICK_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.ElementActionNames.GET_ELEMENT_BOUNDS_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.ElementActionNames.IS_ON_THE_SCREEN_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.ElementActionNames.SHOULD_FILE_EXIST_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.ElementActionNames.UPLOAD_FROM_CLASSPATH_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.ElementActionNames.UPLOAD_FROM_FILE_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.ElementComponents.LABEL;
import static io.perfeccionista.framework.pagefactory.elements.ElementComponents.ROOT;
import static io.perfeccionista.framework.pagefactory.elements.ElementActionNames.CLEAR_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.ElementActionNames.GET_COLOR_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.ElementActionNames.GET_LABEL_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.ElementActionNames.GET_SCREENSHOT_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.ElementActionNames.GET_TEXT_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.ElementActionNames.HOVER_TO_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.ElementActionNames.IS_COMPONENT_DISPLAYED_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.ElementActionNames.IS_COMPONENT_PRESENT_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.ElementActionNames.IS_DISPLAYED_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.ElementActionNames.IS_ENABLED_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.ElementActionNames.IS_IN_FOCUS_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.ElementActionNames.IS_PRESENT_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.ElementActionNames.SCROLL_TO_METHOD;
import static io.perfeccionista.framework.utils.FileUtils.deleteFileIgnoreExceptions;
import static io.perfeccionista.framework.value.Values.intGreaterThan;
import static io.perfeccionista.framework.value.Values.stringContains;
import static io.perfeccionista.framework.value.Values.stringEmpty;
import static io.perfeccionista.framework.value.Values.stringProcess;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;

@Tag("WebElement") @Tag("WebFileInput")
class WebFileInputElementTest extends AbstractWebSeleniumParallelTest {

    @Test
    void webFileInputInitializationTest(Environment environment) {
        WebPageFactory pageFactory = new WebPageFactory(new DefaultSeleniumWebPageFactoryPreferences());
        ElementsPage elementsPage = (ElementsPage) pageFactory.createWebPage(ElementsPage.class);
        elementsPage.setEnvironment(environment);
        elementsPage.setWebBrowserDispatcher(mock(WebBrowserDispatcher.class));
        WebFileInput fileInput = elementsPage.fileInput();
        WebElementIdentifier elementIdentifier = fileInput.getElementIdentifier();
        assertAll(
                () -> assertNotNull(fileInput.getEnvironment()),
                () -> assertNotNull(fileInput.getLocatorChain()),
                () -> assertNotNull(fileInput.getWebBrowserDispatcher()),
                () -> assertNotNull(fileInput.getOptionalLocator(ROOT)),
                // WebTextInput
                () -> assertNotNull(fileInput.getEndpointHandler(CLEAR_METHOD, Void.class)),
                () -> assertNotNull(fileInput.getEndpointHandler(CLICK_METHOD, Void.class)),
                () -> assertNotNull(fileInput.getEndpointHandler(UPLOAD_FROM_CLASSPATH_METHOD, Void.class)),
                () -> assertNotNull(fileInput.getEndpointHandler(UPLOAD_FROM_FILE_METHOD, Void.class)),
                () -> assertNotNull(fileInput.getEndpointHandler(IS_ENABLED_METHOD, Boolean.class)),
                () -> assertNotNull(fileInput.getEndpointHandler(GET_TEXT_METHOD, String.class)),
                () -> assertNotNull(fileInput.getEndpointHandler(GET_LABEL_METHOD, String.class)),
                // WebChildElement
                () -> assertNotNull(fileInput.getEndpointHandler(GET_COLOR_METHOD, Color.class)),
                () -> assertNotNull(fileInput.getEndpointHandler(GET_ELEMENT_BOUNDS_METHOD, ElementBounds.class)),
                () -> assertNotNull(fileInput.getEndpointHandler(GET_SCREENSHOT_METHOD, Screenshot.class)),
                () -> assertNotNull(fileInput.getEndpointHandler(HOVER_TO_METHOD, Void.class)),
                () -> assertNotNull(fileInput.getEndpointHandler(IS_COMPONENT_DISPLAYED_METHOD, Boolean.class)),
                () -> assertNotNull(fileInput.getEndpointHandler(IS_COMPONENT_PRESENT_METHOD, Boolean.class)),
                () -> assertNotNull(fileInput.getEndpointHandler(IS_DISPLAYED_METHOD, Boolean.class)),
                () -> assertNotNull(fileInput.getEndpointHandler(IS_IN_FOCUS_METHOD, Boolean.class)),
                () -> assertNotNull(fileInput.getEndpointHandler(IS_ON_THE_SCREEN_METHOD, Boolean.class)),
                () -> assertNotNull(fileInput.getEndpointHandler(IS_PRESENT_METHOD, Boolean.class)),
                () -> assertNotNull(fileInput.getEndpointHandler(SCROLL_TO_METHOD, Void.class)),
                // Identifier
                () -> assertEquals("fileInput", elementIdentifier.getElementMethod().getName()),
                () -> assertEquals("File input", elementIdentifier.getLastUsedName()),
                () -> assertTrue(elementIdentifier.containsName("File input")),
                () -> assertFalse(elementIdentifier.isNameDeprecated("File input")),
                () -> assertEquals(2, elementIdentifier.names().size()),
                () -> {
                    WebFileInput fileInputByName = elementsPage.getElementRegistry()
                            .getRequiredElementByPath("File input", WebFileInput.class);
                    assertAll(
                            () -> assertNotNull(fileInputByName),
                            () -> assertEquals("File input", elementIdentifier.getLastUsedName()),
                            () -> assertNotNull(fileInputByName.toString())
                    );
                }
        );
    }

    /**
     * Case: Проверяем что присутствует левое меню
     *  Проверяем текст заголовка
     *  Проверяем основной текст
     */
    @Test
    void webFileInputPositiveTest() {
        WebPageContext context = initWebPageContext();
        context.getPage(HomePage.class).leftMenu()
                .select("Elements");

        ElementsPage elementsPage = context.getPage(ElementsPage.class);
        WebText fileInputText = elementsPage.fileInputText()
                .should(bePresent())
                .should(beDisplayed())
                .should(haveText(stringEmpty()));
        // Check Simple input
        WebFileInput fileInput = elementsPage.fileInput()
                .should(bePresent())
                .should(beDisplayed())
                .should(notBeInFocus())
                .should(beEnabled())
                .scrollTo()
                .should(haveDimensions(Dimensions2D.of(382.5d, 38.0d).setInaccuracy(0.2d)))
                .should(haveScreenLocation(Point2D.of(345.0d, 753.4d).setInaccuracy(0.2d)))
                .should(haveColor(LABEL, "border-color", Color.of(220, 53, 69, 1.0d)))
                .hoverTo(true)
                .should(haveText(stringEmpty()))
                .should(notHaveText(stringContains("input")))
                .should(haveLabel("Choose a file"))
                .should(notHaveLabel("Choose a word"))
                // Метод loadFile(Path filePath)
                .replaceText(this.getClass().getClassLoader().getResource("test.data.properties").getPath())
                .should(haveColor(LABEL, "border-color", Color.of(40, 167, 69, 1.0d)))
                .should(haveText(stringContains("test.data.properties")));
        fileInputText
                .should(haveText("test.data.properties"));
        fileInput.clear()
                .should(haveText(stringEmpty()))
                .should(haveColor(LABEL, "border-color", Color.of(220, 53, 69, 1.0d)));
        fileInputText
                .should(haveText(stringEmpty()));
        fileInput
                .should(beEnabled())
                .should(haveText(stringEmpty()));
        assertAll(
                () -> assertTrue(fileInput.isPresent()),
                () -> assertTrue(fileInput.isDisplayed()),
                () -> assertFalse(fileInput.isInFocus()),
                () -> assertTrue(fileInput.isEnabled()),
                () -> assertEquals(Dimensions2D.of(382.5d, 38.0d).setInaccuracy(0.2d), fileInput.getElementBounds().getDimensions()),
                () -> assertEquals(Point2D.of(345.0d, 753.4d).setInaccuracy(0.2d), fileInput.getElementBounds().getAbsoluteLocation()),
                () -> assertEquals(Color.of(220, 53, 69, 1.0d), fileInput.getColor(LABEL, "border-color")),
                () -> {
                    Screenshot screenshot = fileInput.getScreenshot();
                    assertAll(
                            () -> assertNotNull(screenshot),
                            () -> assertTrue(intGreaterThan(4500).check(screenshot.getRaw().length)),
                            () -> assertEquals(Point2D.of(551.5d, 772.4d).setInaccuracy(0.2d), fileInput.getElementBounds().getCenter())
                    );
                }
        );
    }

    /**
     * Case: Проверяем что присутствует левое меню
     *  Проверяем текст заголовка
     *  Проверяем основной текст
     */
    @Test
    void webFileInputUploadPositiveTest() {
        WebPageContext context = initWebPageContext();
        context.getPage(HomePage.class).leftMenu()
                .select("Elements");

        ElementsPage elementsPage = context.getPage(ElementsPage.class);
        WebText fileInputText = elementsPage.fileInputText()
                .should(beDisplayed())
                .should(haveText(stringEmpty()));
        // Check Simple input
        String filePath = this.getClass().getClassLoader().getResource("test.data.properties").getPath();
        elementsPage.fileInput()
                .should(beDisplayed())
                .uploadFromClasspath("test.data.properties")
                .should(haveText(stringContains("test.data.properties")))
                .clear()
                .should(haveText(stringEmpty()))
                .uploadFromFile(Paths.get(filePath))
                .should(haveText(stringContains("test.data.properties")));
    }

    @Test
    void webTextInputNegativeTest(Environment environment) {
        WebPageContext context = initWebPageContext();
        context.getPage(HomePage.class).leftMenu()
                .select("Elements");

        ElementsPage elementsPage = context.getPage(ElementsPage.class);
        WebFileInput fileInput = elementsPage.fileInput();
        // Выполнение этого метода показывает завершение загрузки страницы
        fileInput
                .should(beDisplayed());
        // Для негативных сценариев меняем время ожидания, чтобы не ждать по 5 секунд проброса ошибки вне враппера
        environment.getService(TimeoutsService.class)
                .setTimeout(CheckTimeout.class, Duration.ofMillis(100L));
        assertAll(
                () -> assertThrows(ElementIsPresentAssertionError.class,
                        () -> fileInput.should(notBePresent())),
                () -> assertThrows(ElementIsDisplayedAssertionError.class,
                        () -> fileInput.should(notBeDisplayed())),
                () -> assertThrows(WebElementNotInFocusAssertionError.class,
                        () -> fileInput.should(beInFocus())),
                () -> assertThrows(WebElementIsEnabledAssertionError.class,
                        () -> fileInput.should(beDisabled())),
                () -> {
                    Dimensions2D elementDimensions = Dimensions2D.of(382.5d, 38.0d).setInaccuracy(0.2d);
                    assertThrows(WebElementDimensionsAssertionError.class,
                            () -> fileInput.should(notHaveDimensions(elementDimensions)));
                },
                () -> {
                    Point2D elementLocation = Point2D.of(345.0d, 753.4d).setInaccuracy(0.2d);
                    assertThrows(WebElementLocationAssertionError.class,
                            () -> fileInput.should(notHaveScreenLocation(elementLocation)));
                },
                () -> {
                    Color elementColor = Color.of(220, 53, 69, 1.0d);
                    assertThrows(WebElementColorAssertionError.class,
                            () -> fileInput.should(notHaveColor(LABEL, "border-color", elementColor)));
                },
                () -> assertThrows(WebElementTextValueAssertionError.class,
                        () -> fileInput.should(notHaveText(stringEmpty()))),
                () -> assertThrows(WebElementTextValueAssertionError.class,
                        () -> fileInput.should(haveText("TextInput text")))
        );
        fileInput.replaceText(stringProcess("${[path to resource file] test.data.properties}"));
        assertAll(
                () -> assertThrows(WebElementTextValueAssertionError.class,
                        () -> fileInput.should(haveText(stringEmpty()))),
                () -> assertThrows(WebElementTextValueAssertionError.class,
                        () -> fileInput.should(notHaveText(stringContains("test.data.properties"))))
        );
    }

    @Test
    void webFileDownloadTest() {
        WebPageContext context = initWebPageContext();
        context.getPage(HomePage.class).leftMenu()
                .select("Elements");

        ElementsPage elementsPage = context.getPage(ElementsPage.class);
        String separator = System.getProperty("file.separator");
        String downloadFile = System.getProperty("user.home") + separator + "Downloads" + separator + "LICENSE.txt";
        deleteFileIgnoreExceptions(Paths.get(downloadFile));
        elementsPage.fileDownloadLink()
                .should(beDisplayed())
                .click()
                .should((WebTextMatcher) element -> {
                    runCheck(InvocationInfo.assertInvocation(SHOULD_FILE_EXIST_METHOD, element.getElementIdentifier().getLastUsedName()),
                            () -> FileUtils.fileShouldExist(Paths.get(downloadFile)));
                });
    }

}
