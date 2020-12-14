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
import io.perfeccionista.framework.pagefactory.pageobjects.TablePage;
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
import static io.perfeccionista.framework.pagefactory.extractor.WebExtractors.row;
import static io.perfeccionista.framework.pagefactory.filter.WebFilters.emptyWebTableFilter;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;

@Tag("WebElement") @Tag("WebTable")
class WebTableElementTest extends AbstractUiTest {

    @Test
    void webTableInitializationTest(Environment env) {
        WebPageFactory pageFactory = new WebPageFactory(new DefaultSeleniumWebPageFactoryPreferences());
        TablePage tablePage = (TablePage) pageFactory.createWebPage(TablePage.class);
        tablePage.setEnvironment(env);
        tablePage.setWebBrowserDispatcher(mock(WebBrowserDispatcher.class));
        WebTable table = tablePage.table();
        assertNotNull(table.getEnvironment());
        assertNotNull(table.getLocatorChain());
        assertNotNull(table.getWebBrowserDispatcher());
        assertNotNull(table.getOptionalLocator(ROOT));
        WebBrowserDispatcher webBrowserDispatcher = table.getWebBrowserDispatcher();
        // WebChildElement
        assertNotNull(table.getActionImplementation(GET_COLOR_METHOD, WebColor.class));
        assertNotNull(table.getActionImplementation(GET_DIMENSIONS_METHOD, Dimensions.class));
        assertNotNull(table.getActionImplementation(GET_LOCATION_METHOD, Location.class));
        assertNotNull(table.getActionImplementation(GET_PROPERTY_VALUE_METHOD, String.class));
        assertNotNull(table.getActionImplementation(GET_SCREENSHOT_METHOD, Screenshot.class));
        assertNotNull(table.getActionImplementation(HOVER_TO_METHOD, Void.class));
        assertNotNull(table.getActionImplementation(IS_COMPONENT_DISPLAYED_METHOD, Boolean.class));
        assertNotNull(table.getActionImplementation(IS_COMPONENT_PRESENT_METHOD, Boolean.class));
        assertNotNull(table.getActionImplementation(IS_DISPLAYED_METHOD, Boolean.class));
        assertNotNull(table.getActionImplementation(IS_IN_FOCUS_METHOD, Boolean.class));
        assertNotNull(table.getActionImplementation(IS_ON_THE_SCREEN_METHOD, Boolean.class));
        assertNotNull(table.getActionImplementation(IS_PRESENT_METHOD, Boolean.class));
        assertNotNull(table.getActionImplementation(SCROLL_TO_METHOD, Void.class));
        // Identifier
        WebElementIdentifier elementIdentifier = table.getElementIdentifier();
        assertEquals("table", elementIdentifier.getElementMethod().getName());
        assertEquals("table", elementIdentifier.getLastUsedName());
        assertTrue(elementIdentifier.containsName("Table of countries"));
        assertFalse(elementIdentifier.isNameDeprecated("Table of countries"));
        assertEquals(1, elementIdentifier.names().size());
        WebTable tableElement = tablePage.getElementRegistry()
                .getRequiredElementByPath("Table of countries", WebTable.class);
        assertNotNull(tableElement);
        assertEquals("Table of countries", elementIdentifier.getLastUsedName());
        assertNotNull(table.toString());
        // Смотрим описание элемента
        System.out.println( table.toString() );
    }

    @Test
    void webTablePositiveTest(Environment env, ValueService value) {
        WebPageContext context = initWebPageContext(env, value);
        context.getPage(HomePage.class).leftMenu()
                .select("Table Element");
        TablePage tablePage = context.getPage(TablePage.class);
        WebTable table = tablePage.table();
        table.should(bePresent())
                .should(beDisplayed())
                .should(notBeInFocus())
                .scrollTo()
                .hoverTo(true)
                .should(haveDimensions(Dimensions.of(795.0d, 10447.0d).setInaccuracy(0.2d)))
                .should(haveLocation(Location.relative(345d, -4785d).setInaccuracy(0.2d)))
                .should(haveColor("border-color", WebColor.of(222, 226, 230, 1.0d)))
                .should(haveSize(195));
        assertTrue(table.isPresent());
        assertTrue(table.isDisplayed());
        assertFalse(table.isInFocus());
        assertEquals(Dimensions.of(795.0d, 10447.0d).setInaccuracy(0.2d), table.getDimensions(ROOT));
        assertEquals(Location.absolute(345d, 173d).setInaccuracy(0.2d), table.getLocation(ROOT));
        assertEquals(WebColor.of(222, 226, 230, 1.0d), table.getColor(ROOT, "border-color"));
        assertEquals(195, table.filter(emptyWebTableFilter()).extractRows(row()).getSize());
        assertEquals(Point.of(397.5d, 5223.5d).setInaccuracy(0.2d), table.getDimensions(ROOT).getCenter());
    }

    @Test
    void webTableNegativeTest(Environment env, ValueService value) {
        WebPageContext context = initWebPageContext(env, value);
        context.getPage(HomePage.class).leftMenu()
                .select("Table Element");
        TablePage tablePage = context.getPage(TablePage.class);
        WebTable table = tablePage.table();
        // Выполнение этого метода показывает завершение загрузки страницы
        table.should(beDisplayed());
        // Для негативных сценариев меняем время ожидания, чтобы не ждать по 5 секунд проброса ошибки вне враппера
        env.getEnvironmentConfiguration().getTimeouts()
                .setTimeout(CheckTimeout.class, Duration.ofMillis(100L));
        assertThrows(WebElementIsPresentAssertionError.class,
                () -> table.should(notBePresent()));
        assertThrows(WebElementIsDisplayedAssertionError.class,
                () -> table.should(notBeDisplayed()));
        assertThrows(WebElementNotInFocusAssertionError.class,
                () -> table.should(beInFocus()));
        Dimensions elementDimensions = Dimensions.of(795.0d, 10447.0d).setInaccuracy(0.2d);
        assertThrows(WebElementDimensionsAssertionError.class,
                () -> table.should(notHaveDimensions(elementDimensions)));
        Location elementLocation = Location.relative(345d, 173d).setInaccuracy(0.2d);
        assertThrows(WebElementLocationAssertionError.class,
                () -> table.should(haveLocation(elementLocation.offset(15d, 1d))));
        assertThrows(WebElementLocationAssertionError.class,
                () -> table.should(notHaveLocation(elementLocation)));
        WebColor elementColor = WebColor.of(222, 226, 230, 1.0d);
        assertThrows(WebElementColorAssertionError.class,
                () -> table.should(notHaveColor("border-color", elementColor)));
        assertThrows(WebElementPropertyNotFoundException.class,
                () -> table.should(notHavePropertyValue("unknown property", "Some value")));
        assertThrows(WebElementSizeAssertionError.class,
                () -> table.should(haveSize(196)));
    }

}
