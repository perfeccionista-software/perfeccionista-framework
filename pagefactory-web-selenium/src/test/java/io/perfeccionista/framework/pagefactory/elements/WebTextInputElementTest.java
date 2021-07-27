package io.perfeccionista.framework.pagefactory.elements;

import io.perfeccionista.framework.Environment;
import io.perfeccionista.framework.color.Color;
import io.perfeccionista.framework.exceptions.WebElementColor.WebElementColorAssertionError;
import io.perfeccionista.framework.exceptions.WebElementDimensions.WebElementDimensionsAssertionError;
import io.perfeccionista.framework.exceptions.WebElementInFocus.WebElementInFocusAssertionError;
import io.perfeccionista.framework.exceptions.WebElementIsDisabled.WebElementIsDisabledAssertionError;
import io.perfeccionista.framework.exceptions.WebElementIsDisabled.WebElementIsDisabledException;
import io.perfeccionista.framework.exceptions.ElementIsDisplayed.ElementIsDisplayedAssertionError;
import io.perfeccionista.framework.exceptions.WebElementIsEnabled.WebElementIsEnabledAssertionError;
import io.perfeccionista.framework.exceptions.ElementIsPresent.ElementIsPresentAssertionError;
import io.perfeccionista.framework.exceptions.WebElementLocation.WebElementLocationAssertionError;
import io.perfeccionista.framework.exceptions.WebElementNotInFocus.WebElementNotInFocusAssertionError;
import io.perfeccionista.framework.exceptions.WebElementTextValue.WebElementTextValueAssertionError;
import io.perfeccionista.framework.invocation.timeouts.TimeoutsService;
import io.perfeccionista.framework.invocation.timeouts.type.CheckTimeout;
import io.perfeccionista.framework.name.WebElementIdentifier;
import io.perfeccionista.framework.AbstractWebSeleniumParallelTest;
import io.perfeccionista.framework.pagefactory.elements.preferences.DefaultSeleniumWebPageFactoryPreferences;
import io.perfeccionista.framework.pagefactory.dispatcher.WebBrowserDispatcher;
import io.perfeccionista.framework.pagefactory.dispatcher.context.WebPageContext;
import io.perfeccionista.framework.measurements.Dimensions2D;
import io.perfeccionista.framework.measurements.Point2D;
import io.perfeccionista.framework.pagefactory.factory.WebPageFactory;
import io.perfeccionista.framework.pagefactory.emulator.keys.KeyEventsChain;
import io.perfeccionista.framework.pagefactory.pageobjects.ElementsPage;
import io.perfeccionista.framework.pagefactory.pageobjects.HomePage;
import io.perfeccionista.framework.screenshots.Screenshot;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.time.Duration;

import static io.perfeccionista.framework.Web.*;
import static io.perfeccionista.framework.pagefactory.elements.ElementActionNames.GET_ELEMENT_BOUNDS_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.ElementActionNames.IS_ON_THE_SCREEN_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.ElementComponents.ROOT;
import static io.perfeccionista.framework.pagefactory.elements.ElementActionNames.CLEAR_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.ElementActionNames.CLICK_METHOD;
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
import static io.perfeccionista.framework.pagefactory.elements.ElementActionNames.TYPE_TEXT_METHOD;
import static io.perfeccionista.framework.pagefactory.emulator.keys.Keys.ENTER;
import static io.perfeccionista.framework.value.Values.intGreaterThan;
import static io.perfeccionista.framework.value.Values.stringContains;
import static io.perfeccionista.framework.value.Values.stringEmpty;
import static io.perfeccionista.framework.value.Values.stringEquals;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;

@Tag("WebElement") @Tag("WebTextInput")
class WebTextInputElementTest extends AbstractWebSeleniumParallelTest {

    @Test
    void webTextInputInitializationTest(Environment environment) {
        WebPageFactory pageFactory = new WebPageFactory(new DefaultSeleniumWebPageFactoryPreferences());
        ElementsPage elementsPage = (ElementsPage) pageFactory.createWebPage(ElementsPage.class);
        elementsPage.setEnvironment(environment);
        elementsPage.setWebBrowserDispatcher(mock(WebBrowserDispatcher.class));
        WebTextInput simpleInput = elementsPage.simpleInput();
        WebElementIdentifier elementIdentifier = simpleInput.getElementIdentifier();
        assertAll(
                () -> assertNotNull(simpleInput.getEnvironment()),
                () -> assertNotNull(simpleInput.getLocatorChain()),
                () -> assertNotNull(simpleInput.getWebBrowserDispatcher()),
                () -> assertNotNull(simpleInput.getOptionalLocator(ROOT)),
                // WebTextInput
                () -> assertNotNull(simpleInput.getEndpointHandler(CLICK_METHOD, Void.class)),
                () -> assertNotNull(simpleInput.getEndpointHandler(CLEAR_METHOD, Void.class)),
                () -> assertNotNull(simpleInput.getEndpointHandler(TYPE_TEXT_METHOD, Void.class)),
                () -> assertNotNull(simpleInput.getEndpointHandler(IS_ENABLED_METHOD, Boolean.class)),
                () -> assertNotNull(simpleInput.getEndpointHandler(GET_TEXT_METHOD, String.class)),
                () -> assertNotNull(simpleInput.getEndpointHandler(GET_LABEL_METHOD, String.class)),
                // WebChildElement
                () -> assertNotNull(simpleInput.getEndpointHandler(GET_COLOR_METHOD, Color.class)),
                () -> assertNotNull(simpleInput.getEndpointHandler(GET_ELEMENT_BOUNDS_METHOD, ElementBounds.class)),
                () -> assertNotNull(simpleInput.getEndpointHandler(GET_SCREENSHOT_METHOD, Screenshot.class)),
                () -> assertNotNull(simpleInput.getEndpointHandler(HOVER_TO_METHOD, Void.class)),
                () -> assertNotNull(simpleInput.getEndpointHandler(IS_COMPONENT_DISPLAYED_METHOD, Boolean.class)),
                () -> assertNotNull(simpleInput.getEndpointHandler(IS_COMPONENT_PRESENT_METHOD, Boolean.class)),
                () -> assertNotNull(simpleInput.getEndpointHandler(IS_DISPLAYED_METHOD, Boolean.class)),
                () -> assertNotNull(simpleInput.getEndpointHandler(IS_IN_FOCUS_METHOD, Boolean.class)),
                () -> assertNotNull(simpleInput.getEndpointHandler(IS_ON_THE_SCREEN_METHOD, Boolean.class)),
                () -> assertNotNull(simpleInput.getEndpointHandler(IS_PRESENT_METHOD, Boolean.class)),
                () -> assertNotNull(simpleInput.getEndpointHandler(SCROLL_TO_METHOD, Void.class)),
                // Identifier
                () -> assertEquals("simpleInput", elementIdentifier.getElementMethod().getName()),
                () -> assertEquals("Simple input", elementIdentifier.getLastUsedName()),
                () -> assertTrue(elementIdentifier.containsName("Simple input")),
                () -> assertFalse(elementIdentifier.isNameDeprecated("Simple input")),
                () -> assertEquals(2, elementIdentifier.names().size()),
                () -> {
                    WebTextInput simpleInputByName = elementsPage.getElementRegistry()
                            .getRequiredElementByPath("Simple input", WebTextInput.class);
                    assertAll(
                            () -> assertNotNull(simpleInputByName),
                            () -> assertEquals("Simple input", elementIdentifier.getLastUsedName()),
                            () -> assertNotNull(simpleInput.toString())
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
    void webTextInputPositiveTest() {
        WebPageContext context = initWebPageContext();
        context.getPage(HomePage.class).leftMenu()
                .select(stringEquals("Elements"));

        ElementsPage elementsPage = context.getPage(ElementsPage.class);
        WebButton simpleInputButton = elementsPage.simpleInputButton()
                .should(bePresent())
                .should(beDisplayed())
                .should(componentBeDisplayed("Red"))
                .should(haveText("Disable Input"));
        WebText simpleInputText = elementsPage.simpleInputText()
                .should(bePresent())
                .should(beDisplayed())
                .should(haveText(stringEmpty()));
        // Check Simple input
        WebTextInput simpleInput = elementsPage.simpleInput()
                .should(bePresent())
                .should(beDisplayed())
                .should(notBeInFocus())
                .should(beEnabled())
                .scrollTo()
                .should(haveDimensions(Dimensions2D.of(382.5d, 38.0d).setInaccuracy(0.2d)))
                .should(haveScreenLocation(Point2D.of(551.3d, 517.4d).setInaccuracy(0.2d)))
                .should(haveColor("border-color", Color.of(206, 212, 218, 1.0d)))
                .hoverTo(true)
                .click()
                .should(haveColor("border-color", Color.of(128, 189, 255, 1.0d)))
                .should(haveText(stringEmpty()))
                .should(notHaveText(stringContains("input")))
                .should(havePropertyValue("placeholder", "Enter text"))
                .should(notHavePropertyValue("placeholder", stringContains("Name")))
                .typeText("Let's test text input")
                .should(haveText("Let's test text input"));
        simpleInputText
                .should(beDisplayed())
                .should(haveText("Let's test text input"));
        simpleInput
                .clear()
                .should(haveText(stringEmpty()));
        simpleInputText
                .should(haveText(stringEmpty()));
        simpleInputButton
                .click()
                .should(beInFocus())
                .should(componentBeDisplayed("Green"))
                .should(haveText("Enable Input"));
        simpleInput
                .should(beDisabled())
                .should(haveText(stringEmpty()));
        assertAll(
                () -> assertTrue(simpleInput.isPresent()),
                () -> assertTrue(simpleInput.isDisplayed()),
                () -> assertFalse(simpleInput.isInFocus()),
                () -> assertFalse(simpleInput.isEnabled()),
                () -> assertEquals(Dimensions2D.of(382.5d, 38.0d).setInaccuracy(0.2d), simpleInput.getElementBounds().getDimensions()),
                () -> assertEquals(Point2D.of(551.3d, 517.4d).setInaccuracy(0.2d), simpleInput.getElementBounds().getAbsoluteLocation()),
                () -> assertEquals(Color.of(206, 212, 218, 1.0d), simpleInput.getColor(ROOT, "border-color")),
                () -> {
                    Screenshot screenshot = simpleInput.getScreenshot(ROOT);
                    assertAll(
                            () -> assertNotNull(screenshot),
                            () -> assertTrue(intGreaterThan(4500).check(screenshot.getRaw().length))
                    );
                },
                () -> assertEquals(Point2D.of(757.7d, 536.4d).setInaccuracy(0.2d), simpleInput.getElementBounds().getCenter())
        );
    }

    @Test
    void webTextInputNegativeTest(Environment environment) {
        WebPageContext context = initWebPageContext();
        context.getPage(HomePage.class).leftMenu()
                .select(stringEquals("Elements"));

        ElementsPage elementsPage = context.getPage(ElementsPage.class);
        WebTextInput simpleInput = elementsPage.simpleInput();
        // Выполнение этого метода показывает завершение загрузки страницы
        simpleInput
                .should(beDisplayed());
        // Для негативных сценариев меняем время ожидания, чтобы не ждать по 5 секунд проброса ошибки вне враппера
        environment.getService(TimeoutsService.class)
                .setTimeout(CheckTimeout.class, Duration.ofMillis(100L));
        assertAll(
                () -> assertThrows(ElementIsPresentAssertionError.class,
                        () -> simpleInput.should(notBePresent())),
                () -> assertThrows(ElementIsDisplayedAssertionError.class,
                        () -> simpleInput.should(notBeDisplayed())),
                () -> assertThrows(WebElementNotInFocusAssertionError.class,
                        () -> simpleInput.should(beInFocus())),
                () -> assertThrows(WebElementIsEnabledAssertionError.class,
                        () -> simpleInput.should(beDisabled())),
                () -> {
                    Dimensions2D elementDimensions = Dimensions2D.of(382.5d, 48.0d).setInaccuracy(0.2d);
                    assertThrows(WebElementDimensionsAssertionError.class,
                            () -> simpleInput.should(haveDimensions(elementDimensions)));
                },
                () -> {
                    Point2D elementLocation = Point2D.of(551.3d, 517.4d).setInaccuracy(0.2d).offset(10d, 10d);
                    assertThrows(WebElementLocationAssertionError.class,
                            () -> simpleInput.should(haveScreenLocation(elementLocation)));
                },
                () -> {
                    Color elementColor = Color.of(206, 212, 218, 1.0d);
                    assertThrows(WebElementColorAssertionError.class,
                            () -> simpleInput.should(notHaveColor("border-color", elementColor)));
                },
                () -> assertThrows(WebElementTextValueAssertionError.class,
                        () -> simpleInput.should(notHaveText(stringEmpty()))),
                () -> assertThrows(WebElementTextValueAssertionError.class,
                        () -> simpleInput.should(haveText("TextInput text")))
        );
        elementsPage.simpleInputButton()
                .should(componentBeDisplayed("Red"))
                .click()
                .should(componentBeDisplayed("Green"));
        assertAll(
                () -> assertThrows(WebElementIsDisabledAssertionError.class,
                        () -> simpleInput.should(beEnabled())),
                () -> assertThrows(WebElementIsDisabledException.class,
                        () -> simpleInput.typeText("Let's test disabled input"))
        );
        elementsPage.simpleInputButton()
                .click();
        simpleInput
                .should(beEnabled())
                .click();
        assertThrows(WebElementInFocusAssertionError.class,
                () -> simpleInput.should(notBeInFocus()));
    }

    @Test
    void webTextAreaPositiveTest() {
        WebPageContext context = initWebPageContext();
        context.getPage(HomePage.class).leftMenu()
                .select(stringEquals("Elements"));

        ElementsPage elementsPage = context.getPage(ElementsPage.class);
        WebButton areaInputButton = elementsPage.areaInputButton()
                .should(bePresent())
                .should(beDisplayed())
                .should(componentBeDisplayed("Red"))
                .should(haveText("Disable Area"));
        WebText areaInputText = elementsPage.areaInputText()
                .should(bePresent())
                .should(beDisplayed())
                .should(haveText(stringEmpty()));
        // Check Area input
        WebTextInput areaInput = elementsPage.areaInput()
                .should(bePresent())
                .should(beDisplayed())
                .should(notBeInFocus())
                .should(beEnabled())
                .scrollTo()
                .should(haveDimensions(Dimensions2D.of(382.5d, 86.0d).setInaccuracy(0.2d)))
                .should(haveScreenLocation(Point2D.of(551.3d, 571.4d).setInaccuracy(0.2d)))
                .should(haveColor("border-color", Color.of(206, 212, 218, 1.0d)))
                .hoverTo(true)
                .click()
                .should(haveColor("border-color", Color.of(128, 189, 255, 1.0d)))
                .should(haveText(stringEmpty()))
                .should(notHaveText(stringContains("input")))
                .should(havePropertyValue("placeholder", "Enter text"))
                .should(notHavePropertyValue("placeholder", stringContains("Name")))
                .sendKeyEvents(KeyEventsChain.builder()
                        .addText("Let's test").setDelay(Duration.ofMillis(500))
                        .keyPress(ENTER)
                        .addText("multistring")
                        .keyPress(ENTER)
                        .addText("text input"))
                .should(haveText("Let's test\nmultistring\ntext input"));
        areaInputText
                .should(beDisplayed())
                .should(haveText("Let's test multistring text input"));
        areaInput
                .clear()
                .should(haveText(stringEmpty()));
        areaInputText
                .should(haveText(stringEmpty()));
        areaInputButton
                .click()
                .should(beInFocus())
                .should(componentBeDisplayed("Green"))
                .should(haveText("Enable Area"));
        areaInput
                .should(beDisabled())
                .should(haveText(stringEmpty()));
    }

}
