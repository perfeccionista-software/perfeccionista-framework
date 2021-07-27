package io.perfeccionista.framework.pagefactory.elements;

import io.perfeccionista.framework.Environment;
import io.perfeccionista.framework.color.Color;
import io.perfeccionista.framework.exceptions.WebElementColor.WebElementColorAssertionError;
import io.perfeccionista.framework.exceptions.WebElementDimensions.WebElementDimensionsAssertionError;
import io.perfeccionista.framework.exceptions.WebElementInFocus.WebElementInFocusAssertionError;
import io.perfeccionista.framework.exceptions.WebElementIsDisabled.WebElementIsDisabledAssertionError;
import io.perfeccionista.framework.exceptions.ElementIsDisplayed.ElementIsDisplayedAssertionError;
import io.perfeccionista.framework.exceptions.WebElementIsEnabled.WebElementIsEnabledAssertionError;
import io.perfeccionista.framework.exceptions.ElementIsPresent.ElementIsPresentAssertionError;
import io.perfeccionista.framework.exceptions.ElementIsSelected.ElementIsSelectedAssertionError;
import io.perfeccionista.framework.exceptions.WebElementLabelValue.WebElementLabelValueAssertionError;
import io.perfeccionista.framework.exceptions.WebElementLocation.WebElementLocationAssertionError;
import io.perfeccionista.framework.exceptions.WebElementNotInFocus.WebElementNotInFocusAssertionError;
import io.perfeccionista.framework.exceptions.WebElementNotSelected.WebElementNotSelectedAssertionError;
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
import io.perfeccionista.framework.pagefactory.pageobjects.ElementsPage;
import io.perfeccionista.framework.pagefactory.pageobjects.HomePage;
import io.perfeccionista.framework.screenshots.Screenshot;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.time.Duration;

import static io.perfeccionista.framework.Web.*;
import static io.perfeccionista.framework.pagefactory.elements.ElementActionNames.GET_ELEMENT_BOUNDS_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.ElementActionNames.IS_ON_THE_SCREEN_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.ElementComponents.LABEL;
import static io.perfeccionista.framework.pagefactory.elements.ElementComponents.ROOT;
import static io.perfeccionista.framework.pagefactory.elements.ElementActionNames.CLICK_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.ElementActionNames.GET_COLOR_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.ElementActionNames.GET_LABEL_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.ElementActionNames.GET_SCREENSHOT_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.ElementActionNames.HOVER_TO_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.ElementActionNames.IS_COMPONENT_DISPLAYED_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.ElementActionNames.IS_COMPONENT_PRESENT_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.ElementActionNames.IS_DISPLAYED_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.ElementActionNames.IS_ENABLED_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.ElementActionNames.IS_IN_FOCUS_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.ElementActionNames.IS_PRESENT_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.ElementActionNames.IS_SELECTED_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.ElementActionNames.SCROLL_TO_METHOD;
import static io.perfeccionista.framework.value.Values.intGreaterThan;
import static io.perfeccionista.framework.value.Values.stringContains;
import static io.perfeccionista.framework.value.Values.stringContainsAll;
import static io.perfeccionista.framework.value.Values.stringEquals;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;

@Tag("WebElement") @Tag("WebRadioButton")
class WebRadioButtonElementTest  extends AbstractWebSeleniumParallelTest {

    @Test
    void webRadioButtonInitializationTest(Environment environment) {
        WebPageFactory pageFactory = new WebPageFactory(new DefaultSeleniumWebPageFactoryPreferences());
        ElementsPage elementsPage = (ElementsPage) pageFactory.createWebPage(ElementsPage.class);
        elementsPage.setEnvironment(environment);
        elementsPage.setWebBrowserDispatcher(mock(WebBrowserDispatcher.class));
        WebRadioButton radioButtonOne = elementsPage.radioButtonOne();
        WebElementIdentifier elementIdentifier = radioButtonOne.getElementIdentifier();
        assertAll(
                () -> assertNotNull(radioButtonOne.getEnvironment()),
                () -> assertNotNull(radioButtonOne.getLocatorChain()),
                () -> assertNotNull(radioButtonOne.getWebBrowserDispatcher()),
                () -> assertNotNull(radioButtonOne.getOptionalLocator(ROOT)),
                // WebCheckbox
                () -> assertNotNull(radioButtonOne.getEndpointHandler(CLICK_METHOD, Void.class)),
                () -> assertNotNull(radioButtonOne.getEndpointHandler(GET_LABEL_METHOD, String.class)),
                () -> assertNotNull(radioButtonOne.getEndpointHandler(IS_SELECTED_METHOD, Boolean.class)),
                () -> assertNotNull(radioButtonOne.getEndpointHandler(IS_ENABLED_METHOD, Boolean.class)),
                // WebChildElement
                () -> assertNotNull(radioButtonOne.getEndpointHandler(GET_COLOR_METHOD, Color.class)),
                () -> assertNotNull(radioButtonOne.getEndpointHandler(GET_ELEMENT_BOUNDS_METHOD, ElementBounds.class)),
                () -> assertNotNull(radioButtonOne.getEndpointHandler(GET_SCREENSHOT_METHOD, Screenshot.class)),
                () -> assertNotNull(radioButtonOne.getEndpointHandler(HOVER_TO_METHOD, Void.class)),
                () -> assertNotNull(radioButtonOne.getEndpointHandler(IS_COMPONENT_DISPLAYED_METHOD, Boolean.class)),
                () -> assertNotNull(radioButtonOne.getEndpointHandler(IS_COMPONENT_PRESENT_METHOD, Boolean.class)),
                () -> assertNotNull(radioButtonOne.getEndpointHandler(IS_DISPLAYED_METHOD, Boolean.class)),
                () -> assertNotNull(radioButtonOne.getEndpointHandler(IS_IN_FOCUS_METHOD, Boolean.class)),
                () -> assertNotNull(radioButtonOne.getEndpointHandler(IS_ON_THE_SCREEN_METHOD, Boolean.class)),
                () -> assertNotNull(radioButtonOne.getEndpointHandler(IS_PRESENT_METHOD, Boolean.class)),
                () -> assertNotNull(radioButtonOne.getEndpointHandler(SCROLL_TO_METHOD, Void.class)),
                // Identifier
                () -> assertEquals("radioButtonOne", elementIdentifier.getElementMethod().getName()),
                () -> assertEquals("RadioButton one", elementIdentifier.getLastUsedName()),
                () -> assertTrue(elementIdentifier.containsName("RadioButton one")),
                () -> assertFalse(elementIdentifier.isNameDeprecated("RadioButton one")),
                () -> assertEquals(2, elementIdentifier.names().size()),
                () -> {
                    WebRadioButton radioButtonOneByName = elementsPage.getElementRegistry()
                            .getRequiredElementByPath("RadioButton one", WebRadioButton.class);
                    assertAll(
                            () -> assertNotNull(radioButtonOneByName),
                            () -> assertEquals("RadioButton one", elementIdentifier.getLastUsedName()),
                            () -> assertNotNull(radioButtonOneByName.toString())
                    );
                }
        );
    }

    @Test
    void webRadioButtonPositiveTest() {
        WebPageContext context = initWebPageContext();
        context.getPage(HomePage.class).leftMenu()
                .select("Elements");

        ElementsPage elementsPage = context.getPage(ElementsPage.class);
        WebText radioButtonText = elementsPage.radioButtonText()
                .should(bePresent())
                .should(beDisplayed())
                .should(haveText("Label 2"));
        WebRadioButton radioButtonOne = elementsPage.radioButtonOne()
                .should(bePresent())
                .should(beDisplayed())
                .should(beEnabled())
                .should(notBeSelected())
                .should(notBeInFocus())
                .scrollTo()
                .should(haveDimensions(Dimensions2D.of(176.3d, 24.0d).setInaccuracy(0.2d)))
                .should(haveScreenLocation(Point2D.of(345.0d, 713.4d).setInaccuracy(0.2d)))
                .should(haveColor(LABEL, "color", Color.of(33, 37, 41, 1.0d)))
                .hoverTo(true) // 280 ms
                .should(haveLabel("Label 1"))
                .should(notHaveLabel(stringContains("Label 2")))
                .should(havePropertyValue("name","RadioButton 1"))
                .click()
                .should(beInFocus())
                .should(beSelected());
        radioButtonText
                .should(haveText(stringEquals("Label 1")));
        assertAll(
                () -> assertTrue(radioButtonOne.isPresent()),
                () -> assertTrue(radioButtonOne.isDisplayed()),
                () -> assertTrue(radioButtonOne.isInFocus()),
                () -> assertEquals(Dimensions2D.of(176.3d, 24.0d).setInaccuracy(0.2d), radioButtonOne.getElementBounds().getDimensions()),
                () -> assertEquals(Point2D.of(345.0d, 713.4d).setInaccuracy(0.2d), radioButtonOne.getElementBounds().getScreenLocation()),
                () -> assertEquals(Color.of(33, 37, 41, 1.0d), radioButtonOne.getColor(LABEL, "color")),
                () -> {
                    Screenshot screenshot = radioButtonOne.getScreenshot(); // 400 ms
                    assertAll(
                            () -> assertNotNull(screenshot),
                            () -> assertTrue(intGreaterThan(3500).check(screenshot.getRaw().length))
                    );
                }
        );
        radioButtonText
                .should(haveText(stringContainsAll("Label 1")));
        assertEquals(Point2D.of(448.0d, 725.4d).setInaccuracy(0.2d), radioButtonOne.getElementBounds().getCenter());
    }

    @Test
    void webRadioButtonNegativeTest(Environment environment) {
        WebPageContext context = initWebPageContext();
        context.getPage(HomePage.class).leftMenu()
                .select(stringEquals("Elements"));

        ElementsPage elementsPage = context.getPage(ElementsPage.class);
        WebRadioButton radioButtonOne = elementsPage.radioButtonOne();
        // Выполнение этого метода показывает завершение загрузки страницы
        radioButtonOne
                .should(beDisplayed());
        // Для негативных сценариев меняем время ожидания, чтобы не ждать по 5 секунд проброса ошибки вне враппера
        environment.getService(TimeoutsService.class)
                .setTimeout(CheckTimeout.class, Duration.ofMillis(100L));
        assertAll(
                () -> assertThrows(ElementIsPresentAssertionError.class,
                        () -> radioButtonOne.should(notBePresent())),
                () -> assertThrows(ElementIsDisplayedAssertionError.class,
                        () -> radioButtonOne.should(notBeDisplayed())),
                () -> assertThrows(WebElementNotInFocusAssertionError.class,
                        () -> radioButtonOne.should(beInFocus())),
                () -> assertThrows(WebElementIsEnabledAssertionError.class,
                        () -> radioButtonOne.should(beDisabled())),
                () -> assertThrows(WebElementNotSelectedAssertionError.class,
                        () -> radioButtonOne.should(beSelected())),
                () -> {
                    Dimensions2D elementDimensions = Dimensions2D.of(176.3d, 26.0d).setInaccuracy(0.2d);
                    assertThrows(WebElementDimensionsAssertionError.class,
                            () -> radioButtonOne.should(haveDimensions(elementDimensions)));
                },
                () -> {
                    Point2D elementLocation = Point2D.of(345.0d, 713.4d).setInaccuracy(0.2d);
                    assertAll(
                            () -> assertThrows(WebElementLocationAssertionError.class,
                                    () -> radioButtonOne.should(haveScreenLocation(elementLocation.offset(12d, 10d)))),
                            () -> assertThrows(WebElementLocationAssertionError.class,
                                    () -> radioButtonOne.should(notHaveScreenLocation(elementLocation)))
                    );
                },
                () -> {
                    Color elementColor = Color.of(33, 37, 41, 1.0d);
                    assertThrows(WebElementColorAssertionError.class,
                            () -> radioButtonOne.should(notHaveColor(LABEL, "color", elementColor)));
                },
                () -> assertThrows(WebElementLabelValueAssertionError.class,
                        () -> radioButtonOne.should(notHaveLabel("Label 1"))),
                () -> assertThrows(WebElementLabelValueAssertionError.class,
                        () -> radioButtonOne.should(haveLabel("Label unknown")))
        );
        radioButtonOne
                .click();
        assertAll(
                () -> assertThrows(ElementIsSelectedAssertionError.class,
                        () -> radioButtonOne.should(notBeSelected())),
                () -> assertThrows(WebElementInFocusAssertionError.class,
                        () -> radioButtonOne.should(notBeInFocus())),
                () -> assertThrows(WebElementIsDisabledAssertionError.class,
                        () -> elementsPage.checkboxThree().should(beEnabled()))
        );
    }

}
