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
import io.perfeccionista.framework.pagefactory.browser.context.WebPageContext;
import io.perfeccionista.framework.measurements.Dimensions;
import io.perfeccionista.framework.measurements.Location;
import io.perfeccionista.framework.measurements.Point;
import io.perfeccionista.framework.pagefactory.factory.WebPageFactory;
import io.perfeccionista.framework.pagefactory.pageobjects.HomePage;
import io.perfeccionista.framework.pagefactory.pageobjects.ListElementsPage;
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
import static io.perfeccionista.framework.pagefactory.elements.actions.WebElementActionNames.SCROLL_TO_ELEMENT_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.actions.WebElementActionNames.SCROLL_TO_METHOD;
import static io.perfeccionista.framework.pagefactory.extractor.WebExtractors.block;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;

@Tag("Element") @Tag("WebList")
class WebListElementTest extends AbstractUiTest {

    @Test
    void webListInitializationTest(Environment env) {
        WebPageFactory pageFactory = new WebPageFactory(new DefaultSeleniumWebPageFactoryPreferences());
        ListElementsPage listElementsPage = (ListElementsPage) pageFactory.createWebPage(ListElementsPage.class);
        listElementsPage.setEnvironment(env);
        listElementsPage.setWebBrowserDispatcher(mock(WebBrowserDispatcher.class));
        WebList list = listElementsPage.webList();
        assertNotNull(list.getEnvironment());
        assertNotNull(list.getLocatorChain());
        assertNotNull(list.getWebBrowserDispatcher());
        assertNotNull(list.getOptionalLocator(ROOT));
        // WebChildElement
        assertNotNull(list.getActionImplementation(GET_COLOR_METHOD, WebColor.class));
        assertNotNull(list.getActionImplementation(GET_DIMENSIONS_METHOD, Dimensions.class));
        assertNotNull(list.getActionImplementation(GET_LOCATION_METHOD, Location.class));
        assertNotNull(list.getActionImplementation(GET_PROPERTY_VALUE_METHOD, String.class));
        assertNotNull(list.getActionImplementation(GET_SCREENSHOT_METHOD, Screenshot.class));
        assertNotNull(list.getActionImplementation(HOVER_TO_METHOD, Void.class));
        assertNotNull(list.getActionImplementation(IS_COMPONENT_DISPLAYED_METHOD, Boolean.class));
        assertNotNull(list.getActionImplementation(IS_COMPONENT_PRESENT_METHOD, Boolean.class));
        assertNotNull(list.getActionImplementation(IS_DISPLAYED_METHOD, Boolean.class));
        assertNotNull(list.getActionImplementation(IS_IN_FOCUS_METHOD, Boolean.class));
        assertNotNull(list.getActionImplementation(IS_ON_THE_SCREEN_METHOD, Boolean.class));
        assertNotNull(list.getActionImplementation(IS_PRESENT_METHOD, Boolean.class));
        assertNotNull(list.getActionImplementation(SCROLL_TO_METHOD, Void.class));
        // Identifier
        WebElementIdentifier elementIdentifier = list.getElementIdentifier();
        assertEquals("webList", elementIdentifier.getElementMethod().getName());
        assertEquals("webList", elementIdentifier.getLastUsedName());
        assertTrue(elementIdentifier.containsName("List of countries"));
        assertFalse(elementIdentifier.isNameDeprecated("List of countries"));
        assertEquals(1, elementIdentifier.names().size());
        WebList listElement = listElementsPage.getElementRegistry()
                .getRequiredElementByPath("List of countries", WebList.class);
        assertNotNull(listElement);
        assertEquals("List of countries", elementIdentifier.getLastUsedName());
        assertNotNull(list.toString());
        // Смотрим описание элемента
        System.out.println( list.toString() );
    }

    @Test
    void webListPositiveTest(Environment env, ValueService value) {
        WebPageContext context = initWebPageContext(env, value);
        context.getPage(HomePage.class)
                .leftMenu()
                .select("List Elements");
        ListElementsPage listElementsPage = context.getPage(ListElementsPage.class);
        WebList list = listElementsPage.webList();
        list.should(bePresent())
                .should(beDisplayed())
                .should(notBeInFocus())
                .scrollTo()
                .hoverTo(true)
                .should(haveDimensions(Dimensions.of(795.0d, 10295.0d).setInaccuracy(0.2d)))
                .should(haveLocation(Location.relative(345d, -4709d).setInaccuracy(0.2d)))
                .should(haveColor("border-color", WebColor.of(33, 37, 41, 1.0d)))
                .should(haveSize(195));
        assertTrue(list.isPresent());
        assertTrue(list.isDisplayed());
        assertFalse(list.isInFocus());
        assertEquals(Dimensions.of(795.0d, 10295.0d).setInaccuracy(0.2d), list.getDimensions(ROOT));
        assertEquals(Location.absolute(345d, 335d).setInaccuracy(0.2d), list.getLocation(ROOT));
        assertEquals(WebColor.of(33, 37, 41, 1.0d), list.getColor(ROOT, "border-color"));
        assertEquals(195, list.extractAll(block()).getSize());
        assertEquals(Point.of(397.5d, 5147.5d).setInaccuracy(0.2d), list.getDimensions(ROOT).getCenter());
    }

    @Test
    void webListNegativeTest(Environment env, ValueService value) {
        WebPageContext context = initWebPageContext(env, value);
        context.getPage(HomePage.class).leftMenu()
                .select("List Elements");
        ListElementsPage listElementsPage = context.getPage(ListElementsPage.class);
        WebList list = listElementsPage.webList();
        // Выполнение этого метода показывает завершение загрузки страницы
        list.should(beDisplayed());
        // Для негативных сценариев меняем время ожидания, чтобы не ждать по 5 секунд проброса ошибки вне враппера
        env.getEnvironmentConfiguration().getTimeouts()
                .setTimeout(CheckTimeout.class, Duration.ofMillis(100L));
        assertThrows(WebElementIsPresentAssertionError.class,
                () -> list.should(notBePresent()));
        assertThrows(WebElementIsDisplayedAssertionError.class,
                () -> list.should(notBeDisplayed()));
        assertThrows(WebElementNotInFocusAssertionError.class,
                () -> list.should(beInFocus()));
        Dimensions elementDimensions = Dimensions.of(795.0d, 10295.0d).setInaccuracy(0.2d);
        assertThrows(WebElementDimensionsAssertionError.class,
                () -> list.should(notHaveDimensions(elementDimensions)));
        Location elementLocation = Location.relative(345d, 335d).setInaccuracy(0.2d);
        assertThrows(WebElementLocationAssertionError.class,
                () -> list.should(haveLocation(elementLocation.offset(15d, 1d))));
        assertThrows(WebElementLocationAssertionError.class,
                () -> list.should(notHaveLocation(elementLocation)));
        WebColor elementColor = WebColor.of(33, 37, 41, 1.0d);
        assertThrows(WebElementColorAssertionError.class,
                () -> list.should(notHaveColor("border-color", elementColor)));
        assertThrows(WebElementPropertyNotFoundException.class,
                () -> list.should(notHavePropertyValue("unknown property", "Some value")));
        assertThrows(WebElementSizeAssertionError.class,
                () -> list.should(haveSize(196)));
    }

}
