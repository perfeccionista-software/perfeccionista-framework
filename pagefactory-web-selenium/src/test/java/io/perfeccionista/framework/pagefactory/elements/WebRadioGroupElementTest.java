package io.perfeccionista.framework.pagefactory.elements;

import io.perfeccionista.framework.Environment;
import io.perfeccionista.framework.exceptions.WebElementColor.WebElementColorAssertionError;
import io.perfeccionista.framework.exceptions.WebElementDimensions.WebElementDimensionsAssertionError;
import io.perfeccionista.framework.exceptions.WebElementIsDisplayed.WebElementIsDisplayedAssertionError;
import io.perfeccionista.framework.exceptions.WebElementIsPresent.WebElementIsPresentAssertionError;
import io.perfeccionista.framework.exceptions.WebElementLocation.WebElementLocationAssertionError;
import io.perfeccionista.framework.exceptions.WebElementNotInFocus.WebElementNotInFocusAssertionError;
import io.perfeccionista.framework.exceptions.WebElementPropertyNotFound.WebElementPropertyNotFoundException;
import io.perfeccionista.framework.exceptions.WebElementSize.WebElementSizeAssertionError;
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
import org.junit.jupiter.api.Test;

import java.time.Duration;

import static io.perfeccionista.framework.matcher.WebElementAssertions.beDisplayed;
import static io.perfeccionista.framework.matcher.WebElementAssertions.beInFocus;
import static io.perfeccionista.framework.matcher.WebElementAssertions.bePresent;
import static io.perfeccionista.framework.matcher.WebElementAssertions.haveColor;
import static io.perfeccionista.framework.matcher.WebElementAssertions.haveDimensions;
import static io.perfeccionista.framework.matcher.WebElementAssertions.haveLocation;
import static io.perfeccionista.framework.matcher.WebElementAssertions.notBeDisplayed;
import static io.perfeccionista.framework.matcher.WebElementAssertions.notBeInFocus;
import static io.perfeccionista.framework.matcher.WebElementAssertions.notBePresent;
import static io.perfeccionista.framework.matcher.WebElementAssertions.notHaveColor;
import static io.perfeccionista.framework.matcher.WebElementAssertions.notHaveDimensions;
import static io.perfeccionista.framework.matcher.WebElementAssertions.notHaveLocation;
import static io.perfeccionista.framework.matcher.WebElementAssertions.notHavePropertyValue;
import static io.perfeccionista.framework.matcher.WebMultipleResultAssertions.haveSize;
import static io.perfeccionista.framework.matcher.WebMultipleResultAssertions.notHaveSize;
import static io.perfeccionista.framework.pagefactory.elements.actions.WebElementActionNames.IS_ON_THE_SCREEN_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.components.WebComponents.ROOT;
import static io.perfeccionista.framework.pagefactory.elements.actions.WebElementActionNames.GET_COLOR_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.actions.WebElementActionNames.GET_DIMENSIONS_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.actions.WebElementActionNames.GET_LOCATION_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.actions.WebElementActionNames.GET_PROPERTY_VALUE_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.actions.WebElementActionNames.GET_SCREENSHOT_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.actions.WebElementActionNames.HOVER_TO_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.actions.WebElementActionNames.IS_COMPONENT_DISPLAYED_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.actions.WebElementActionNames.IS_COMPONENT_PRESENT_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.actions.WebElementActionNames.IS_DISPLAYED_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.actions.WebElementActionNames.IS_IN_FOCUS_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.actions.WebElementActionNames.IS_PRESENT_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.actions.WebElementActionNames.SCROLL_TO_METHOD;
import static io.perfeccionista.framework.pagefactory.extractor.WebExtractors.element;
import static io.perfeccionista.framework.pagefactory.filter.WebFilters.emptyWebRadioButtonFilter;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;

@Tag("WebElement") @Tag("WebRadioGroup")
class WebRadioGroupElementTest extends AbstractUiTest {

    @Test
    void webRadioGroupInitializationTest(Environment env) {
        WebPageFactory pageFactory = new WebPageFactory(new DefaultSeleniumWebPageFactoryPreferences());
        ElementsPage elementsPage = (ElementsPage) pageFactory.createWebPage(ElementsPage.class);
        elementsPage.setEnvironment(env);
        elementsPage.setWebBrowserDispatcher(mock(WebBrowserDispatcher.class));
        WebRadioGroup radioGroup = elementsPage.radioGroup();
        assertNotNull(radioGroup.getEnvironment());
        assertNotNull(radioGroup.getLocatorChain());
        assertNotNull(radioGroup.getWebBrowserDispatcher());
        assertNotNull(radioGroup.getOptionalLocator(ROOT));
        // WebChildElement
        assertNotNull(radioGroup.getActionImplementation(GET_COLOR_METHOD, WebColor.class));
        assertNotNull(radioGroup.getActionImplementation(GET_DIMENSIONS_METHOD, Dimensions.class));
        assertNotNull(radioGroup.getActionImplementation(GET_LOCATION_METHOD, Location.class));
        assertNotNull(radioGroup.getActionImplementation(GET_PROPERTY_VALUE_METHOD, String.class));
        assertNotNull(radioGroup.getActionImplementation(GET_SCREENSHOT_METHOD, Screenshot.class));
        assertNotNull(radioGroup.getActionImplementation(HOVER_TO_METHOD, Void.class));
        assertNotNull(radioGroup.getActionImplementation(IS_COMPONENT_DISPLAYED_METHOD, Boolean.class));
        assertNotNull(radioGroup.getActionImplementation(IS_COMPONENT_PRESENT_METHOD, Boolean.class));
        assertNotNull(radioGroup.getActionImplementation(IS_DISPLAYED_METHOD, Boolean.class));
        assertNotNull(radioGroup.getActionImplementation(IS_IN_FOCUS_METHOD, Boolean.class));
        assertNotNull(radioGroup.getActionImplementation(IS_ON_THE_SCREEN_METHOD, Boolean.class));
        assertNotNull(radioGroup.getActionImplementation(IS_PRESENT_METHOD, Boolean.class));
        assertNotNull(radioGroup.getActionImplementation(SCROLL_TO_METHOD, Void.class));
        // Identifier
        WebElementIdentifier elementIdentifier = radioGroup.getElementIdentifier();
        assertEquals("radioGroup", elementIdentifier.getElementMethod().getName());
        assertEquals("radioGroup", elementIdentifier.getLastUsedName());
        assertTrue(elementIdentifier.containsName("Radio group"));
        assertFalse(elementIdentifier.isNameDeprecated("Radio group"));
        assertEquals(1, elementIdentifier.names().size());
        WebRadioGroup radioGroupElement = elementsPage.getElementRegistry()
                .getRequiredElementByPath("Radio group", WebRadioGroup.class);
        assertNotNull(radioGroupElement);
        assertEquals("Radio group", elementIdentifier.getLastUsedName());
        assertNotNull(radioGroup.toString());
        // Смотрим описание элемента
        System.out.println( radioGroup.toString() );
    }

    @Test
    void webRadioGroupPositiveTest(Environment env, ValueService value) {
        WebPageContext context = initWebPageContext(env, value);
        context.getPage(HomePage.class).leftMenu()
                .select(value.stringEquals("Elements"));
        ElementsPage elementsPage = context.getPage(ElementsPage.class);
        WebRadioGroup radioGroup = elementsPage.radioGroup();
        radioGroup.should(bePresent())
                .should(beDisplayed())
                .should(notBeInFocus())
                .scrollTo()
                .hoverTo(true)
                .should(haveDimensions(Dimensions.of(825.0d, 24.0d).setInaccuracy(0.2d)))
                .should(haveLocation(Location.relative(330d, 713.4d).setInaccuracy(0.2d)))
                .should(haveColor("border-color", WebColor.of(33, 37, 41, 1.0d)))
                .should(haveSize(3))
                .should(haveSize(value.intEquals(3)))
                .should(notHaveSize(2))
                .should(notHaveSize(value.intGreaterThan(3)));
        assertTrue(radioGroup.isPresent());
        assertTrue(radioGroup.isDisplayed());
        assertFalse(radioGroup.isInFocus());
        assertEquals(Dimensions.of(825.0d, 24.0d).setInaccuracy(0.2d), radioGroup.getDimensions(ROOT));
        assertEquals(Location.absolute(330d, 713.4d).setInaccuracy(0.2d), radioGroup.getLocation(ROOT));
        assertEquals(WebColor.of(33, 37, 41, 1.0d), radioGroup.getColor(ROOT, "border-color"));
        assertEquals(3, radioGroup.filter(emptyWebRadioButtonFilter()).extractAll(element()).getSize());
        assertEquals(Point.of(412.5d, 12d).setInaccuracy(0.2d), radioGroup.getDimensions(ROOT).getCenter());
    }

    @Test
    void webRadioGroupNegativeTest(Environment env, ValueService value) {
        WebPageContext context = initWebPageContext(env, value);
        context.getPage(HomePage.class).leftMenu()
                .select(value.stringEquals("Elements"));
        ElementsPage elementsPage = context.getPage(ElementsPage.class);
        WebRadioGroup radioGroup = elementsPage.radioGroup();
        // Выполнение этого метода показывает завершение загрузки страницы
        radioGroup
                .should(beDisplayed());
        // Для негативных сценариев меняем время ожидания, чтобы не ждать по 5 секунд проброса ошибки вне враппера
        env.getEnvironmentConfiguration().getTimeouts()
                .setTimeout(CheckTimeout.class, Duration.ofMillis(100L));
        assertThrows(WebElementIsPresentAssertionError.class,
                () -> radioGroup.should(notBePresent()));
        assertThrows(WebElementIsDisplayedAssertionError.class,
                () -> radioGroup.should(notBeDisplayed()));
        assertThrows(WebElementNotInFocusAssertionError.class,
                () -> radioGroup.should(beInFocus()));
        Dimensions elementDimensions = Dimensions.of(825.0d, 24.0d).setInaccuracy(0.2d);
        assertThrows(WebElementDimensionsAssertionError.class,
                () -> radioGroup.should(notHaveDimensions(ROOT, elementDimensions)));
        Location elementLocation = Location.relative(330d, 713.4d).setInaccuracy(0.2d);
        assertThrows(WebElementLocationAssertionError.class,
                () -> radioGroup.should(haveLocation(ROOT, elementLocation.offset(15d, 1d))));
        assertThrows(WebElementLocationAssertionError.class,
                () -> radioGroup.should(notHaveLocation(ROOT, elementLocation)));
        WebColor elementColor = WebColor.of(33, 37, 41, 1.0d);
        assertThrows(WebElementColorAssertionError.class,
                () -> radioGroup.should(notHaveColor(ROOT, "border-color", elementColor)));
        assertThrows(WebElementPropertyNotFoundException.class,
                () -> radioGroup.should(notHavePropertyValue("unknown property", value.stringEquals("Some value"))));
        assertThrows(WebElementSizeAssertionError.class,
                () -> radioGroup.should(haveSize(value.intEquals(4))));
    }

}
