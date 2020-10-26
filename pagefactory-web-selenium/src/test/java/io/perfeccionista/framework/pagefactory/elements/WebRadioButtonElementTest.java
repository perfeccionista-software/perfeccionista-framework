package io.perfeccionista.framework.pagefactory.elements;

import io.perfeccionista.framework.Environment;
import io.perfeccionista.framework.exceptions.WebElementColor.WebElementColorAssertionError;
import io.perfeccionista.framework.exceptions.WebElementDimensions.WebElementDimensionsAssertionError;
import io.perfeccionista.framework.exceptions.WebElementInFocus.WebElementInFocusAssertionError;
import io.perfeccionista.framework.exceptions.WebElementIsDisabled.WebElementIsDisabledAssertionError;
import io.perfeccionista.framework.exceptions.WebElementIsDisplayed.WebElementIsDisplayedAssertionError;
import io.perfeccionista.framework.exceptions.WebElementIsEnabled.WebElementIsEnabledAssertionError;
import io.perfeccionista.framework.exceptions.WebElementIsPresent.WebElementIsPresentAssertionError;
import io.perfeccionista.framework.exceptions.WebElementIsSelected.WebElementIsSelectedAssertionError;
import io.perfeccionista.framework.exceptions.WebElementLabelValue.WebElementLabelValueAssertionError;
import io.perfeccionista.framework.exceptions.WebElementLocation.WebElementLocationAssertionError;
import io.perfeccionista.framework.exceptions.WebElementNotInFocus.WebElementNotInFocusAssertionError;
import io.perfeccionista.framework.exceptions.WebElementNotSelected.WebElementNotSelectedAssertionError;
import io.perfeccionista.framework.invocation.timeouts.CheckTimeout;
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
import io.perfeccionista.framework.color.WebColor;
import io.perfeccionista.framework.value.ValueService;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;

import java.time.Duration;

import static io.perfeccionista.framework.matcher.WebElementAssertions.beDisabled;
import static io.perfeccionista.framework.matcher.WebElementAssertions.beDisplayed;
import static io.perfeccionista.framework.matcher.WebElementAssertions.beEnabled;
import static io.perfeccionista.framework.matcher.WebElementAssertions.beInFocus;
import static io.perfeccionista.framework.matcher.WebElementAssertions.bePresent;
import static io.perfeccionista.framework.matcher.WebElementAssertions.beSelected;
import static io.perfeccionista.framework.matcher.WebElementAssertions.haveColor;
import static io.perfeccionista.framework.matcher.WebElementAssertions.haveDimensions;
import static io.perfeccionista.framework.matcher.WebElementAssertions.haveLabel;
import static io.perfeccionista.framework.matcher.WebElementAssertions.haveLocation;
import static io.perfeccionista.framework.matcher.WebElementAssertions.havePropertyValue;
import static io.perfeccionista.framework.matcher.WebElementAssertions.haveText;
import static io.perfeccionista.framework.matcher.WebElementAssertions.notBeDisplayed;
import static io.perfeccionista.framework.matcher.WebElementAssertions.notBeInFocus;
import static io.perfeccionista.framework.matcher.WebElementAssertions.notBePresent;
import static io.perfeccionista.framework.matcher.WebElementAssertions.notBeSelected;
import static io.perfeccionista.framework.matcher.WebElementAssertions.notHaveColor;
import static io.perfeccionista.framework.matcher.WebElementAssertions.notHaveLabel;
import static io.perfeccionista.framework.matcher.WebElementAssertions.notHaveLocation;
import static io.perfeccionista.framework.pagefactory.elements.actions.WebElementActionNames.IS_ON_THE_SCREEN_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.components.WebComponents.LABEL;
import static io.perfeccionista.framework.pagefactory.elements.components.WebComponents.ROOT;
import static io.perfeccionista.framework.pagefactory.elements.actions.WebElementActionNames.CLICK_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.actions.WebElementActionNames.GET_COLOR_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.actions.WebElementActionNames.GET_DIMENSIONS_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.actions.WebElementActionNames.GET_LABEL_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.actions.WebElementActionNames.GET_LOCATION_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.actions.WebElementActionNames.GET_PROPERTY_VALUE_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.actions.WebElementActionNames.GET_SCREENSHOT_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.actions.WebElementActionNames.HOVER_TO_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.actions.WebElementActionNames.IS_COMPONENT_DISPLAYED_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.actions.WebElementActionNames.IS_COMPONENT_PRESENT_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.actions.WebElementActionNames.IS_DISPLAYED_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.actions.WebElementActionNames.IS_ENABLED_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.actions.WebElementActionNames.IS_IN_FOCUS_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.actions.WebElementActionNames.IS_PRESENT_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.actions.WebElementActionNames.IS_SELECTED_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.actions.WebElementActionNames.SCROLL_TO_METHOD;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;

@Tags(@Tag("Element"))
class WebRadioButtonElementTest  extends AbstractUiTest {

    @Test
    void webRadioButtonInitializationTest(Environment env) {
        WebPageFactory pageFactory = new WebPageFactory(new DefaultSeleniumWebPageFactoryPreferences());
        ElementsPage elementsPage = (ElementsPage) pageFactory.createWebPage(ElementsPage.class);
        elementsPage.setEnvironment(env);
        elementsPage.setWebBrowserDispatcher(mock(WebBrowserDispatcher.class));
        WebRadioButton radioButtonOne = elementsPage.radioButtonOne();
        assertNotNull(radioButtonOne.getEnvironment());
        assertNotNull(radioButtonOne.getLocatorChain());
        assertNotNull(radioButtonOne.getWebBrowserDispatcher());
        assertNotNull(radioButtonOne.getOptionalLocator(ROOT));
        // WebCheckbox
        assertNotNull(radioButtonOne.getActionImplementation(CLICK_METHOD, Void.class));
        assertNotNull(radioButtonOne.getActionImplementation(GET_LABEL_METHOD, String.class));
        assertNotNull(radioButtonOne.getActionImplementation(IS_SELECTED_METHOD, Boolean.class));
        assertNotNull(radioButtonOne.getActionImplementation(IS_ENABLED_METHOD, Boolean.class));
        // WebChildElement
        assertNotNull(radioButtonOne.getActionImplementation(GET_COLOR_METHOD, WebColor.class));
        assertNotNull(radioButtonOne.getActionImplementation(GET_DIMENSIONS_METHOD, Dimensions.class));
        assertNotNull(radioButtonOne.getActionImplementation(GET_LOCATION_METHOD, Location.class));
        assertNotNull(radioButtonOne.getActionImplementation(GET_PROPERTY_VALUE_METHOD, String.class));
        assertNotNull(radioButtonOne.getActionImplementation(GET_SCREENSHOT_METHOD, Screenshot.class));
        assertNotNull(radioButtonOne.getActionImplementation(HOVER_TO_METHOD, Void.class));
        assertNotNull(radioButtonOne.getActionImplementation(IS_COMPONENT_DISPLAYED_METHOD, Boolean.class));
        assertNotNull(radioButtonOne.getActionImplementation(IS_COMPONENT_PRESENT_METHOD, Boolean.class));
        assertNotNull(radioButtonOne.getActionImplementation(IS_DISPLAYED_METHOD, Boolean.class));
        assertNotNull(radioButtonOne.getActionImplementation(IS_IN_FOCUS_METHOD, Boolean.class));
        assertNotNull(radioButtonOne.getActionImplementation(IS_ON_THE_SCREEN_METHOD, Boolean.class));
        assertNotNull(radioButtonOne.getActionImplementation(IS_PRESENT_METHOD, Boolean.class));
        assertNotNull(radioButtonOne.getActionImplementation(SCROLL_TO_METHOD, Void.class));
        // Identifier
        WebElementIdentifier elementIdentifier = radioButtonOne.getElementIdentifier();
        assertEquals("radioButtonOne", elementIdentifier.getElementMethod().getName());
        assertEquals("radioButtonOne", elementIdentifier.getLastUsedName());
        assertTrue(elementIdentifier.containsName("RadioButton one"));
        assertFalse(elementIdentifier.isNameDeprecated("RadioButton one"));
        assertEquals(1, elementIdentifier.names().size());
        WebRadioButton radioButtonOneElement = elementsPage.getElementRegistry()
                .getRequiredElementByPath("RadioButton one", WebRadioButton.class);
        assertNotNull(radioButtonOneElement);
        assertEquals("RadioButton one", elementIdentifier.getLastUsedName());
        assertNotNull(radioButtonOne.toString());
        // Смотрим описание элемента
        System.out.println(radioButtonOne.toString());
    }

    @Test
    void webRadioButtonPositiveTest(Environment env, ValueService value) {
        WebPageContext context = initWebPageContext(env, value);
        context.getPage(HomePage.class).leftMenu()
                .select("Elements");
        ElementsPage elementsPage = context.getPage(ElementsPage.class);
        WebTextBlock radioButtonText = elementsPage.radioButtonText()
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
                .should(haveDimensions(Dimensions.of(176.3d, 24.0d).setInaccuracy(0.2d)))
                .should(haveLocation(Location.relative(345.0d, 713.4d).setInaccuracy(0.2d)))
                .should(haveColor(LABEL, "color", WebColor.of(33, 37, 41, 1.0d)))
                .hoverTo(true) // 280 ms
                .should(haveLabel("Label 1"))
                .should(notHaveLabel(value.stringContains("Label 2")))
                .should(havePropertyValue("name","RadioButton 1"))
                .click()
                .should(beInFocus())
                .should(beSelected());
        radioButtonText
                .should(haveText(value.stringEquals("Label 1")));
        assertTrue(radioButtonOne.isPresent());
        assertTrue(radioButtonOne.isDisplayed());
        assertTrue(radioButtonOne.isInFocus());
        assertEquals(Dimensions.of(176.3d, 24.0d).setInaccuracy(0.2d), radioButtonOne.getDimensions(ROOT));
        assertEquals(Location.absolute(345.0d, 713.4d).setInaccuracy(0.2d), radioButtonOne.getLocation(ROOT));
        assertEquals(WebColor.of(33, 37, 41, 1.0d), radioButtonOne.getColor(LABEL, "color"));
        Screenshot screenshot = radioButtonOne.getScreenshot(ROOT); // 400 ms
        assertNotNull(screenshot);
        assertTrue(value.intGreaterThan(3500).check(screenshot.getRaw().length));
        radioButtonText.should(haveText(value.stringContainsAll("Label 1")));
        assertEquals(Point.of(88.1d, 12d).setInaccuracy(0.2d), radioButtonOne.getDimensions(ROOT).getCenter());
    }

    @Test
    void webRadioButtonNegativeTest(Environment env, ValueService value) {
        WebPageContext context = initWebPageContext(env, value);
        context.getPage(HomePage.class).leftMenu()
                .select(value.stringEquals("Elements"));
        ElementsPage elementsPage = context.getPage(ElementsPage.class);
        WebRadioButton radioButtonOne = elementsPage.radioButtonOne();
        // Выполнение этого метода показывает завершение загрузки страницы
        radioButtonOne
                .should(beDisplayed());
        // Для негативных сценариев меняем время ожидания, чтобы не ждать по 5 секунд проброса ошибки вне враппера
        env.getEnvironmentConfiguration().getTimeouts()
                .setTimeout(CheckTimeout.class, Duration.ofMillis(100L));
        assertThrows(WebElementIsPresentAssertionError.class,
                () -> radioButtonOne.should(notBePresent()));
        assertThrows(WebElementIsDisplayedAssertionError.class,
                () -> radioButtonOne.should(notBeDisplayed()));
        assertThrows(WebElementNotInFocusAssertionError.class,
                () -> radioButtonOne.should(beInFocus()));
        assertThrows(WebElementIsEnabledAssertionError.class,
                () -> radioButtonOne.should(beDisabled()));
        assertThrows(WebElementNotSelectedAssertionError.class,
                () -> radioButtonOne.should(beSelected()));
        Dimensions elementDimensions = Dimensions.of(176.3d, 26.0d).setInaccuracy(0.2d);
        assertThrows(WebElementDimensionsAssertionError.class,
                () -> radioButtonOne.should(haveDimensions(elementDimensions)));
        Location elementLocation = Location.relative(345.0d, 713.4d).setInaccuracy(0.2d);
        assertThrows(WebElementLocationAssertionError.class,
                () -> radioButtonOne.should(haveLocation(elementLocation.offset(12d, 10d))));
        assertThrows(WebElementLocationAssertionError.class,
                () -> radioButtonOne.should(notHaveLocation(elementLocation)));
        WebColor elementColor = WebColor.of(33, 37, 41, 1.0d);
        assertThrows(WebElementColorAssertionError.class,
                () -> radioButtonOne.should(notHaveColor(LABEL, "color", elementColor)));
        assertThrows(WebElementLabelValueAssertionError.class,
                () -> radioButtonOne.should(notHaveLabel("Label 1")));
        assertThrows(WebElementLabelValueAssertionError.class,
                () -> radioButtonOne.should(haveLabel("Label unknown")));
        radioButtonOne
                .click();
        assertThrows(WebElementIsSelectedAssertionError.class,
                () -> radioButtonOne.should(notBeSelected()));
        assertThrows(WebElementInFocusAssertionError.class,
                () -> radioButtonOne.should(notBeInFocus()));
        assertThrows(WebElementIsDisabledAssertionError.class,
                () -> elementsPage.checkboxThree().should(beEnabled()));
    }

}