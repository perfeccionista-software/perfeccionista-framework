package io.perfeccionista.framework.pagefactory.elements;

import io.perfeccionista.framework.Environment;
import io.perfeccionista.framework.color.Color;
import io.perfeccionista.framework.exceptions.ElementPropertyNotFound.ElementPropertyNotFoundException;
import io.perfeccionista.framework.exceptions.WebElementColor.WebElementColorAssertionError;
import io.perfeccionista.framework.exceptions.WebElementDimensions.WebElementDimensionsAssertionError;
import io.perfeccionista.framework.exceptions.ElementIsDisplayed.ElementIsDisplayedAssertionError;
import io.perfeccionista.framework.exceptions.ElementIsPresent.ElementIsPresentAssertionError;
import io.perfeccionista.framework.exceptions.WebElementLocation.WebElementLocationAssertionError;
import io.perfeccionista.framework.exceptions.WebElementNotInFocus.WebElementNotInFocusAssertionError;
import io.perfeccionista.framework.exceptions.WebElementSize.WebElementSizeAssertionError;
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
import io.perfeccionista.framework.pagefactory.pageobjects.HomePage;
import io.perfeccionista.framework.pagefactory.pageobjects.TablePage;
import io.perfeccionista.framework.screenshots.Screenshot;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.time.Duration;

import static io.perfeccionista.framework.Web.*;
import static io.perfeccionista.framework.pagefactory.elements.ElementActionNames.GET_ELEMENT_BOUNDS_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.ElementActionNames.IS_ON_THE_SCREEN_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.ElementComponents.ROOT;
import static io.perfeccionista.framework.pagefactory.elements.ElementActionNames.GET_COLOR_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.ElementActionNames.GET_SCREENSHOT_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.ElementActionNames.HOVER_TO_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.ElementActionNames.IS_COMPONENT_DISPLAYED_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.ElementActionNames.IS_COMPONENT_PRESENT_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.ElementActionNames.IS_DISPLAYED_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.ElementActionNames.IS_IN_FOCUS_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.ElementActionNames.IS_PRESENT_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.ElementActionNames.SCROLL_TO_METHOD;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;

@Tag("WebElement") @Tag("WebTable")
class WebTableElementTest extends AbstractWebSeleniumParallelTest {

    @Test
    void webTableInitializationTest(Environment environment) {
        WebPageFactory pageFactory = new WebPageFactory(new DefaultSeleniumWebPageFactoryPreferences());
        TablePage tablePage = (TablePage) pageFactory.createWebPage(TablePage.class);
        tablePage.setEnvironment(environment);
        tablePage.setWebBrowserDispatcher(mock(WebBrowserDispatcher.class));
        WebTable table = tablePage.table();
        WebElementIdentifier elementIdentifier = table.getElementIdentifier();
        assertAll(
                () -> assertNotNull(table.getEnvironment()),
                () -> assertNotNull(table.getLocatorChain()),
                () -> assertNotNull(table.getWebBrowserDispatcher()),
                () -> assertNotNull(table.getOptionalLocator(ROOT)),
                // WebChildElement
                () -> assertNotNull(table.getEndpointHandler(GET_COLOR_METHOD, Color.class)),
                () -> assertNotNull(table.getEndpointHandler(GET_ELEMENT_BOUNDS_METHOD, ElementBounds.class)),
                () -> assertNotNull(table.getEndpointHandler(GET_SCREENSHOT_METHOD, Screenshot.class)),
                () -> assertNotNull(table.getEndpointHandler(HOVER_TO_METHOD, Void.class)),
                () -> assertNotNull(table.getEndpointHandler(IS_COMPONENT_DISPLAYED_METHOD, Boolean.class)),
                () -> assertNotNull(table.getEndpointHandler(IS_COMPONENT_PRESENT_METHOD, Boolean.class)),
                () -> assertNotNull(table.getEndpointHandler(IS_DISPLAYED_METHOD, Boolean.class)),
                () -> assertNotNull(table.getEndpointHandler(IS_IN_FOCUS_METHOD, Boolean.class)),
                () -> assertNotNull(table.getEndpointHandler(IS_ON_THE_SCREEN_METHOD, Boolean.class)),
                () -> assertNotNull(table.getEndpointHandler(IS_PRESENT_METHOD, Boolean.class)),
                () -> assertNotNull(table.getEndpointHandler(SCROLL_TO_METHOD, Void.class)),
                // Identifier
                () -> assertEquals("table", elementIdentifier.getElementMethod().getName()),
                () -> assertEquals("table", elementIdentifier.getLastUsedName()),
                () -> assertTrue(elementIdentifier.containsName("Table of countries")),
                () -> assertFalse(elementIdentifier.isNameDeprecated("Table of countries")),
                () -> assertEquals(2, elementIdentifier.names().size()),
                () -> {
                    WebTable tableByName = tablePage.getElementRegistry()
                            .getRequiredElementByPath("Table of countries", WebTable.class);
                    assertAll(
                            () -> assertNotNull(tableByName),
                            () -> assertEquals("Table of countries", elementIdentifier.getLastUsedName()),
                            () -> assertNotNull(tableByName.toString())
                    );
                }
        );
    }

    @Test
    void webTablePositiveTest() {
        WebPageContext context = initWebPageContext();
        context.getPage(HomePage.class).leftMenu()
                .select("Table Element");

        TablePage tablePage = context.getPage(TablePage.class);
        WebTable table = tablePage.table();
        table.should(bePresent())
                .should(beDisplayed())
                .should(notBeInFocus())
                .scrollTo()
                .hoverTo(true)
                .should(haveDimensions(Dimensions2D.of(795.0d, 10447.0d).setInaccuracy(0.2d)))
                .should(haveScreenLocation(Point2D.of(345d, -4785d).setInaccuracy(0.2d)))
                .should(haveColor("border-color", Color.of(222, 226, 230, 1.0d)))
                .should(haveSize(195));
        assertAll(
                () -> assertTrue(table.isPresent()),
                () -> assertTrue(table.isDisplayed()),
                () -> assertFalse(table.isInFocus()),
                () -> assertEquals(Dimensions2D.of(795.0d, 10447.0d).setInaccuracy(0.2d), table.getElementBounds().getDimensions()),
                () -> assertEquals(Point2D.of(345d, 173d).setInaccuracy(0.2d), table.getElementBounds().getAbsoluteLocation()),
                () -> assertEquals(Color.of(222, 226, 230, 1.0d), table.getColor(ROOT, "border-color")),
                () -> assertEquals(195, table.filter(emptyWebTableFilter()).extractRows(row()).getSize()),
                () -> assertEquals(Point2D.of(757.5d, 438.5d).setInaccuracy(0.2d), table.getElementBounds().getCenter())
        );
    }

    @Test
    void webTableNegativeTest(Environment environment) {
        WebPageContext context = initWebPageContext();
        context.getPage(HomePage.class).leftMenu()
                .select("Table Element");

        TablePage tablePage = context.getPage(TablePage.class);
        WebTable table = tablePage.table();
        // Выполнение этого метода показывает завершение загрузки страницы
        table.should(beDisplayed());
        // Для негативных сценариев меняем время ожидания, чтобы не ждать по 5 секунд проброса ошибки вне враппера
        environment.getService(TimeoutsService.class)
                .setTimeout(CheckTimeout.class, Duration.ofMillis(100L));
        assertAll(
                () -> assertThrows(ElementIsPresentAssertionError.class,
                        () -> table.should(notBePresent())),
                () -> assertThrows(ElementIsDisplayedAssertionError.class,
                        () -> table.should(notBeDisplayed())),
                () -> assertThrows(WebElementNotInFocusAssertionError.class,
                        () -> table.should(beInFocus())),
                () -> {
                    Dimensions2D elementDimensions = Dimensions2D.of(795.0d, 10447.0d).setInaccuracy(0.2d);
                    assertThrows(WebElementDimensionsAssertionError.class,
                            () -> table.should(notHaveDimensions(elementDimensions)));
                },
                () -> {
                    Point2D elementLocation = Point2D.of(345d, 173d).setInaccuracy(0.2d);
                    assertAll(
                            () -> assertThrows(WebElementLocationAssertionError.class,
                                    () -> table.should(haveScreenLocation(elementLocation.offset(15d, 1d)))),
                            () -> assertThrows(WebElementLocationAssertionError.class,
                                    () -> table.should(notHaveScreenLocation(elementLocation)))
                    );
                },
                () -> {
                    Color elementColor = Color.of(222, 226, 230, 1.0d);
                    assertThrows(WebElementColorAssertionError.class,
                            () -> table.should(notHaveColor("border-color", elementColor)));
                },
                () -> assertThrows(WebElementSizeAssertionError.class,
                        () -> table.should(haveSize(196)))
        );
    }

}
