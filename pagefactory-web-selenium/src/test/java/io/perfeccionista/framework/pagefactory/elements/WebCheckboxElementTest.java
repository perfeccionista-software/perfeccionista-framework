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
import static io.perfeccionista.framework.value.Values.stringEmpty;
import static io.perfeccionista.framework.value.Values.stringEquals;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;

@Tag("WebElement") @Tag("WebCheckbox")
class WebCheckboxElementTest extends AbstractWebSeleniumParallelTest {

    @Test
    void webCheckboxInitializationTest(Environment environment) {
        WebPageFactory pageFactory = new WebPageFactory(new DefaultSeleniumWebPageFactoryPreferences());
        ElementsPage elementsPage = (ElementsPage) pageFactory.createWebPage(ElementsPage.class);
        elementsPage.setEnvironment(environment);
        elementsPage.setWebBrowserDispatcher(mock(WebBrowserDispatcher.class));

        WebCheckbox checkboxOne = elementsPage.checkboxOne();
        WebElementIdentifier elementIdentifier = checkboxOne.getElementIdentifier();
        assertAll(
                () -> assertNotNull(checkboxOne.getEnvironment()),
                () -> assertNotNull(checkboxOne.getLocatorChain()),
                () -> assertNotNull(checkboxOne.getWebBrowserDispatcher()),
                () -> assertNotNull(checkboxOne.getRequiredLocator(ROOT)),
                // WebCheckbox
                () -> assertNotNull(checkboxOne.getEndpointHandler(CLICK_METHOD, Void.class)),
                () -> assertNotNull(checkboxOne.getEndpointHandler(GET_LABEL_METHOD, String.class)),
                () -> assertNotNull(checkboxOne.getEndpointHandler(IS_SELECTED_METHOD, Boolean.class)),
                () -> assertNotNull(checkboxOne.getEndpointHandler(IS_ENABLED_METHOD, Boolean.class)),
                // WebChildElement
                () -> assertNotNull(checkboxOne.getEndpointHandler(GET_COLOR_METHOD, Color.class)),
                () -> assertNotNull(checkboxOne.getEndpointHandler(GET_ELEMENT_BOUNDS_METHOD, ElementBounds.class)),
                () -> assertNotNull(checkboxOne.getEndpointHandler(GET_SCREENSHOT_METHOD, Screenshot.class)),
                () -> assertNotNull(checkboxOne.getEndpointHandler(HOVER_TO_METHOD, Void.class)),
                () -> assertNotNull(checkboxOne.getEndpointHandler(IS_COMPONENT_DISPLAYED_METHOD, Boolean.class)),
                () -> assertNotNull(checkboxOne.getEndpointHandler(IS_COMPONENT_PRESENT_METHOD, Boolean.class)),
                () -> assertNotNull(checkboxOne.getEndpointHandler(IS_DISPLAYED_METHOD, Boolean.class)),
                () -> assertNotNull(checkboxOne.getEndpointHandler(IS_IN_FOCUS_METHOD, Boolean.class)),
                () -> assertNotNull(checkboxOne.getEndpointHandler(IS_ON_THE_SCREEN_METHOD, Boolean.class)),
                () -> assertNotNull(checkboxOne.getEndpointHandler(IS_PRESENT_METHOD, Boolean.class)),
                () -> assertNotNull(checkboxOne.getEndpointHandler(SCROLL_TO_METHOD, Void.class)),
                // Identifier
                () -> assertEquals("checkboxOne", elementIdentifier.getElementMethod().getName()),
                () -> assertEquals("checkboxOne", elementIdentifier.getLastUsedName()),
                () -> assertTrue(elementIdentifier.containsName("Checkbox one")),
                () -> assertFalse(elementIdentifier.isNameDeprecated("Checkbox one")),
                () -> assertEquals(2, elementIdentifier.names().size()),
                () -> {
                    WebCheckbox checkboxOneByName = elementsPage.getElementRegistry()
                            .getRequiredElementByPath("Checkbox one", WebCheckbox.class);
                    assertAll(
                            () -> assertNotNull(checkboxOneByName),
                            () -> assertEquals("Checkbox one", elementIdentifier.getLastUsedName()),
                            () -> assertNotNull(checkboxOneByName.toString())
                    );
                }
        );
    }

    @Test
    void webCheckboxPositiveTest() {
        WebPageContext context = initWebPageContext();
        context.getPage(HomePage.class).leftMenu()
                .select("Elements");

        ElementsPage elementsPage = context.getPage(ElementsPage.class);
        WebText checkboxText = elementsPage.checkboxText()
                .should(bePresent())
                .should(beDisplayed())
                .should(haveText(stringEmpty()));
        WebCheckbox checkboxOne = elementsPage.checkboxOne()
                .should(bePresent())
                .should(beDisplayed())
                .should(beEnabled())
                .should(notBeSelected())
                .should(notBeInFocus())
                .scrollTo()
                .should(haveDimensions(Dimensions2D.of(176.3d, 24.0d).setInaccuracy(0.2d)))
                .should(haveScreenLocation(Point2D.of(345.0d, 673.4d).setInaccuracy(0.2d)))
                .should(haveColor(LABEL, "color", Color.of(33, 37, 41, 1.0d)))
                .hoverTo(true) // 280 ms
                .should(haveLabel("Label 1"))
                .should(notHaveLabel(stringContains("Label 2")))
                .should(havePropertyValue("name", "Checkbox 1"))
                .click()
                .should(beInFocus())
                .should(beSelected());
        checkboxText
                .should(haveText("Label 1"));
        elementsPage.checkboxThree()
                .should(bePresent())
                .should(beDisplayed())
                .should(beDisabled())
                .should(notBeSelected())
                .should(notBeInFocus())
                .click();
        checkboxText
                .should(haveText("Label 1"));
        assertAll(
                () -> assertTrue(checkboxOne.isPresent()),
                () -> assertTrue(checkboxOne.isDisplayed()),
                () -> assertFalse(checkboxOne.isInFocus()),
                () -> assertEquals(Dimensions2D.of(176.3d, 24.0d).setInaccuracy(0.2d), checkboxOne.getElementBounds().getDimensions()),
                () -> assertEquals(Point2D.of(345.0d, 673.4d).setInaccuracy(0.2d), checkboxOne.getElementBounds().getAbsoluteLocation()),
                () -> assertEquals(Color.of(33, 37, 41, 1.0d), checkboxOne.getColor(LABEL, "color")),
                () -> {
                    Screenshot screenshot = checkboxOne.getScreenshot(); // 400 ms
                    assertAll(
                            () -> assertNotNull(screenshot),
                            () -> assertTrue(intGreaterThan(3500).check(screenshot.getRaw().length))
                    );
                }
        );
        elementsPage.checkboxTwo()
                .should(bePresent())
                .should(beDisplayed())
                .should(notBeSelected())
                .should(notBeInFocus())
                .click()
                .should(beInFocus())
                .should(beSelected());
        checkboxText
                .should(haveText(stringContainsAll("Label 1", "Label 2")));
        assertEquals(Point2D.of(433.2d, 685.4d).setInaccuracy(0.2d), checkboxOne.getElementBounds().getCenter());
    }

    @Test
    void webCheckboxNegativeTest(Environment environment) {
        WebPageContext context = initWebPageContext();
        context.getPage(HomePage.class).leftMenu()
                .select(stringEquals("Elements"));

        ElementsPage elementsPage = context.getPage(ElementsPage.class);
        WebCheckbox checkboxOne = elementsPage.checkboxOne();
        // Выполнение этого метода показывает завершение загрузки страницы
        checkboxOne
                .should(beDisplayed());
        // Для негативных сценариев меняем время ожидания, чтобы не ждать по 5 секунд проброса ошибки вне враппера
        environment.getService(TimeoutsService.class)
                .setTimeout(CheckTimeout.class, Duration.ofMillis(100L));
        assertAll(
                () -> assertThrows(ElementIsPresentAssertionError.class,
                        () -> checkboxOne.should(notBePresent())),
                () -> assertThrows(ElementIsDisplayedAssertionError.class,
                        () -> checkboxOne.should(notBeDisplayed())),
                () -> assertThrows(WebElementNotInFocusAssertionError.class,
                        () -> checkboxOne.should(beInFocus())),
                () -> assertThrows(WebElementIsEnabledAssertionError.class,
                        () -> checkboxOne.should(beDisabled())),
                () -> assertThrows(WebElementNotSelectedAssertionError.class,
                        () -> checkboxOne.should(beSelected())),
                () -> {
                    Dimensions2D elementDimensions = Dimensions2D.of(176.3d, 24.0d).setInaccuracy(0.2d);
                    assertThrows(WebElementDimensionsAssertionError.class,
                            () -> checkboxOne.should(notHaveDimensions(elementDimensions)));
                },
                () -> {
                    Point2D elementLocation = Point2D.of(345.0d, 673.4d).setInaccuracy(0.2d);
                    assertAll(
                            () -> assertThrows(WebElementLocationAssertionError.class,
                                    () -> checkboxOne.should(haveScreenLocation(elementLocation.offset(12d, 10d)))),
                            () -> assertThrows(WebElementLocationAssertionError.class,
                                    () -> checkboxOne.should(notHaveScreenLocation(elementLocation)))
                    );
                },
                () -> {
                    Color elementColor = Color.of(33, 37, 41, 1.0d);
                    assertThrows(WebElementColorAssertionError.class,
                            () -> checkboxOne.should(notHaveColor(LABEL, "color", elementColor)));
                },
                () -> assertThrows(WebElementLabelValueAssertionError.class,
                        () -> checkboxOne.should(notHaveLabel("Label 1"))),
                () -> assertThrows(WebElementLabelValueAssertionError.class,
                        () -> checkboxOne.should(haveLabel("Label unknown")))
        );
        checkboxOne
                .click();
        assertAll(
                () -> assertThrows(ElementIsSelectedAssertionError.class,
                        () -> checkboxOne.should(notBeSelected())),
                () -> assertThrows(WebElementInFocusAssertionError.class,
                        () -> checkboxOne.should(notBeInFocus())),
                () -> assertThrows(WebElementIsDisabledAssertionError.class,
                        () -> elementsPage.checkboxThree().should(beEnabled()))
        );
    }

}
