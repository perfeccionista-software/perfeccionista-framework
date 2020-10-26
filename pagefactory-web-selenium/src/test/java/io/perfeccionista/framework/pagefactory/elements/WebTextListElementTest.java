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
import io.perfeccionista.framework.pagefactory.pageobjects.HomePage;
import io.perfeccionista.framework.pagefactory.pageobjects.TextListElementsPage;
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
import static io.perfeccionista.framework.pagefactory.elements.actions.WebElementActionNames.SCROLL_TO_METHOD;
import static io.perfeccionista.framework.pagefactory.filter.WebFilters.emptyWebTextListFilter;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;

@Tag("Element") @Tag("WebTextList")
class WebTextListElementTest extends AbstractUiTest {

    @Test
    void webTableInitializationTest(Environment env) {
        WebPageFactory pageFactory = new WebPageFactory(new DefaultSeleniumWebPageFactoryPreferences());
        TextListElementsPage textListPage = (TextListElementsPage) pageFactory.createWebPage(TextListElementsPage.class);
        textListPage.setEnvironment(env);
        textListPage.setWebBrowserDispatcher(mock(WebBrowserDispatcher.class));
        WebTextList textList = textListPage.textList();
        assertNotNull(textList.getEnvironment());
        assertNotNull(textList.getLocatorChain());
        assertNotNull(textList.getWebBrowserDispatcher());
        assertNotNull(textList.getOptionalLocator(ROOT));
        // WebChildElement
        assertNotNull(textList.getActionImplementation(GET_COLOR_METHOD, WebColor.class));
        assertNotNull(textList.getActionImplementation(GET_DIMENSIONS_METHOD, Dimensions.class));
        assertNotNull(textList.getActionImplementation(GET_LOCATION_METHOD, Location.class));
        assertNotNull(textList.getActionImplementation(GET_PROPERTY_VALUE_METHOD, String.class));
        assertNotNull(textList.getActionImplementation(GET_SCREENSHOT_METHOD, Screenshot.class));
        assertNotNull(textList.getActionImplementation(HOVER_TO_METHOD, Void.class));
        assertNotNull(textList.getActionImplementation(IS_COMPONENT_DISPLAYED_METHOD, Boolean.class));
        assertNotNull(textList.getActionImplementation(IS_COMPONENT_PRESENT_METHOD, Boolean.class));
        assertNotNull(textList.getActionImplementation(IS_DISPLAYED_METHOD, Boolean.class));
        assertNotNull(textList.getActionImplementation(IS_IN_FOCUS_METHOD, Boolean.class));
        assertNotNull(textList.getActionImplementation(IS_ON_THE_SCREEN_METHOD, Boolean.class));
        assertNotNull(textList.getActionImplementation(IS_PRESENT_METHOD, Boolean.class));
        assertNotNull(textList.getActionImplementation(SCROLL_TO_METHOD, Void.class));
        // Identifier
        WebElementIdentifier elementIdentifier = textList.getElementIdentifier();
        assertEquals("textList", elementIdentifier.getElementMethod().getName());
        assertEquals("textList", elementIdentifier.getLastUsedName());
        assertTrue(elementIdentifier.containsName("Text list of countries"));
        assertFalse(elementIdentifier.isNameDeprecated("Text list of countries"));
        assertEquals(1, elementIdentifier.names().size());
        WebTextList textListElement = textListPage.getElementRegistry()
                .getRequiredElementByPath("Text list of countries", WebTextList.class);
        assertNotNull(textListElement);
        assertEquals("Text list of countries", elementIdentifier.getLastUsedName());
        assertNotNull(textList.toString());
        // Смотрим описание элемента
        System.out.println( textList.toString() );
    }

    @Test
    void webTextListPositiveTest(Environment env, ValueService value) {
        WebPageContext context = initWebPageContext(env, value);
        context.getPage(HomePage.class).leftMenu()
                .select(value.stringEquals("Text List Elements"));
        TextListElementsPage textListElementsPage = context.getPage(TextListElementsPage.class);
        WebTextList textList = textListElementsPage.textList();
        textList.should(bePresent())
                .should(beDisplayed())
                .should(notBeInFocus())
                .scrollTo()
                .hoverTo(true)
                .should(haveDimensions(Dimensions.of(795.0d, 9508.0d).setInaccuracy(0.2d)))
                .should(haveLocation(Location.relative(345d, -4315d).setInaccuracy(0.2d)))
                .should(haveColor("border-color", WebColor.of(33, 37, 41, 1.0d)))
                .should(haveSize(195));
        assertTrue(textList.isPresent());
        assertTrue(textList.isDisplayed());
        assertFalse(textList.isInFocus());
        assertEquals(Dimensions.of(795.0d, 9508.0d).setInaccuracy(0.2d), textList.getDimensions(ROOT));
        assertEquals(Location.absolute(345d, 281d).setInaccuracy(0.2d), textList.getLocation(ROOT));
        assertEquals(WebColor.of(33, 37, 41, 1.0d), textList.getColor(ROOT, "border-color"));
        assertEquals(195, textList.filter(emptyWebTextListFilter()).extractAll().getSize());
        assertEquals(Point.of(397.5d, 4754d).setInaccuracy(0.2d), textList.getDimensions(ROOT).getCenter());
    }

    @Test
    void webTextListNegativeTest(Environment env, ValueService value) {
        WebPageContext context = initWebPageContext(env, value);
        context.getPage(HomePage.class).leftMenu()
                .select(value.stringEquals("Text List Elements"));
        TextListElementsPage textListElementsPage = context.getPage(TextListElementsPage.class);
        WebTextList textList = textListElementsPage.textList();
        // Выполнение этого метода показывает завершение загрузки страницы
        textList
                .should(beDisplayed());
        // Для негативных сценариев меняем время ожидания, чтобы не ждать по 5 секунд проброса ошибки вне враппера
        env.getEnvironmentConfiguration().getTimeouts()
                .setTimeout(CheckTimeout.class, Duration.ofMillis(100L));
        assertThrows(WebElementIsPresentAssertionError.class,
                () -> textList.should(notBePresent()));
        assertThrows(WebElementIsDisplayedAssertionError.class,
                () -> textList.should(notBeDisplayed()));
        assertThrows(WebElementNotInFocusAssertionError.class,
                () -> textList.should(beInFocus()));
        Dimensions elementDimensions = Dimensions.of(795.0d, 9508.0d).setInaccuracy(0.2d);
        assertThrows(WebElementDimensionsAssertionError.class,
                () -> textList.should(notHaveDimensions(elementDimensions)));
        Location elementLocation = Location.relative(345d, 281d).setInaccuracy(0.2d);
        assertThrows(WebElementLocationAssertionError.class,
                () -> textList.should(haveLocation(elementLocation.offset(15d, 1d))));
        assertThrows(WebElementLocationAssertionError.class,
                () -> textList.should(notHaveLocation(elementLocation)));
        WebColor elementColor = WebColor.of(33, 37, 41, 1.0d);
        assertThrows(WebElementColorAssertionError.class,
                () -> textList.should(notHaveColor("border-color", elementColor)));
        assertThrows(WebElementPropertyNotFoundException.class,
                () -> textList.should(notHavePropertyValue("unknown property", "Some value")));
        assertThrows(WebElementSizeAssertionError.class,
                () -> textList.should(haveSize(196)));
    }

}
