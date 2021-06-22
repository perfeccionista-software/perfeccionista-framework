package io.perfeccionista.framework.pagefactory.elements;

import io.perfeccionista.framework.Environment;
import io.perfeccionista.framework.color.Color;
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
import io.perfeccionista.framework.pagefactory.pageobjects.TextTablePage;
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
import static io.perfeccionista.framework.pagefactory.pageobjects.TablePage.SHORT_COUNTRY_NAME;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;

@Tag("WebElement") @Tag("WebTextTable")
class WebTextTableElementTest extends AbstractWebSeleniumParallelTest {

    @Test
    void webTableInitializationTest(Environment environment) {
        WebPageFactory pageFactory = new WebPageFactory(new DefaultSeleniumWebPageFactoryPreferences());
        TextTablePage textTablePage = (TextTablePage) pageFactory.createWebPage(TextTablePage.class);
        textTablePage.setEnvironment(environment);
        textTablePage.setWebBrowserDispatcher(mock(WebBrowserDispatcher.class));
        WebTextTable textTable = textTablePage.textTable();
        WebElementIdentifier elementIdentifier = textTable.getElementIdentifier();
        assertAll(
                () -> assertNotNull(textTable.getEnvironment()),
                () -> assertNotNull(textTable.getLocatorChain()),
                () -> assertNotNull(textTable.getWebBrowserDispatcher()),
                () -> assertNotNull(textTable.getOptionalLocator(ROOT)),
                // WebChildElement
                () -> assertNotNull(textTable.getEndpointHandler(GET_COLOR_METHOD, Color.class)),
                () -> assertNotNull(textTable.getEndpointHandler(GET_ELEMENT_BOUNDS_METHOD, ElementBounds.class)),
                () -> assertNotNull(textTable.getEndpointHandler(GET_SCREENSHOT_METHOD, Screenshot.class)),
                () -> assertNotNull(textTable.getEndpointHandler(HOVER_TO_METHOD, Void.class)),
                () -> assertNotNull(textTable.getEndpointHandler(IS_COMPONENT_DISPLAYED_METHOD, Boolean.class)),
                () -> assertNotNull(textTable.getEndpointHandler(IS_COMPONENT_PRESENT_METHOD, Boolean.class)),
                () -> assertNotNull(textTable.getEndpointHandler(IS_DISPLAYED_METHOD, Boolean.class)),
                () -> assertNotNull(textTable.getEndpointHandler(IS_IN_FOCUS_METHOD, Boolean.class)),
                () -> assertNotNull(textTable.getEndpointHandler(IS_ON_THE_SCREEN_METHOD, Boolean.class)),
                () -> assertNotNull(textTable.getEndpointHandler(IS_PRESENT_METHOD, Boolean.class)),
                () -> assertNotNull(textTable.getEndpointHandler(SCROLL_TO_METHOD, Void.class)),
                // Identifier
                () -> assertEquals("textTable", elementIdentifier.getElementMethod().getName()),
                () -> assertEquals("textTable", elementIdentifier.getLastUsedName()),
                () -> assertTrue(elementIdentifier.containsName("Simple table of countries")),
                () -> assertFalse(elementIdentifier.isNameDeprecated("Simple table of countries")),
                () -> assertEquals(2, elementIdentifier.names().size()),
                () -> {
                    WebTextTable textTableByName = textTablePage.getElementRegistry()
                            .getRequiredElementByPath("Simple table of countries", WebTextTable.class);
                    assertAll(
                            () -> assertNotNull(textTableByName),
                            () -> assertEquals("Simple table of countries", elementIdentifier.getLastUsedName()),
                            () -> assertNotNull(textTableByName.toString())
                    );
                }
        );
    }

    @Test
    void webTextTablePositiveTest() {
        WebPageContext context = initWebPageContext();
        context.getPage(HomePage.class).leftMenu()
                .select("Text Table Element");

        TextTablePage textTablePage = context.getPage(TextTablePage.class);
        WebTextTable textTable = textTablePage.textTable();
        textTable.should(bePresent())
                .should(beDisplayed())
                .should(notBeInFocus())
                .scrollTo()
                .hoverTo(true)
                .should(haveDimensions(Dimensions2D.of(795.0d, 10014.0d).setInaccuracy(0.2d)))
                .should(haveScreenLocation(Point2D.of(345d, -4568d).setInaccuracy(0.2d)))
                .should(haveColor("border-color", Color.of(222, 226, 230, 1.0d)))
                .should(haveSize(195));
        assertAll(
                () -> assertTrue(textTable.isPresent()),
                () -> assertTrue(textTable.isDisplayed()),
                () -> assertFalse(textTable.isInFocus()),
                () -> assertEquals(Dimensions2D.of(795.0d, 10014.0d).setInaccuracy(0.2d), textTable.getElementBounds().getDimensions()),
                () -> assertEquals(Point2D.of(345d, 173d).setInaccuracy(0.2d), textTable.getElementBounds().getAbsoluteLocation()),
                () -> assertEquals(Color.of(222, 226, 230, 1.0d), textTable.getColor("border-color")),
                () -> assertEquals(195, textTable.filter(emptyWebTextTableFilter()).extractRows(SHORT_COUNTRY_NAME).getSize()),
                () -> assertEquals(Point2D.of(757.5d, 439d).setInaccuracy(0.2d), textTable.getElementBounds().getCenter())
        );
    }

    @Test
    void webTextTableNegativeTest(Environment environment) {
        WebPageContext context = initWebPageContext();
        context.getPage(HomePage.class).leftMenu()
                .select("Text Table Element");

        TextTablePage textTablePage = context.getPage(TextTablePage.class);
        WebTextTable textTable = textTablePage.textTable();
        // Выполнение этого метода показывает завершение загрузки страницы
        textTable
                .should(beDisplayed());
        // Для негативных сценариев меняем время ожидания, чтобы не ждать по 5 секунд проброса ошибки вне враппера
        environment.getService(TimeoutsService.class)
                .setTimeout(CheckTimeout.class, Duration.ofMillis(100L));
        assertAll(
                () -> assertThrows(ElementIsPresentAssertionError.class,
                        () -> textTable.should(notBePresent())),
                () -> assertThrows(ElementIsDisplayedAssertionError.class,
                        () -> textTable.should(notBeDisplayed())),
                () -> assertThrows(WebElementNotInFocusAssertionError.class,
                        () -> textTable.should(beInFocus())),
                () -> {
                    Dimensions2D elementDimensions = Dimensions2D.of(795.0d, 10024.0d).setInaccuracy(0.2d);
                    assertThrows(WebElementDimensionsAssertionError.class,
                            () -> textTable.should(haveDimensions(elementDimensions)));
                },
                () -> {
                    Point2D elementLocation = Point2D.of(345d, 173d).setInaccuracy(0.2d);
                    assertAll(
                            () -> assertThrows(WebElementLocationAssertionError.class,
                                    () -> textTable.should(haveScreenLocation(elementLocation.offset(15d, 1d)))),
                            () -> assertThrows(WebElementLocationAssertionError.class,
                                    () -> textTable.should(notHaveScreenLocation(elementLocation)))
                    );
                },
                () -> {
                    Color elementColor = Color.of(222, 226, 230, 1.0d);
                    assertThrows(WebElementColorAssertionError.class,
                            () -> textTable.should(notHaveColor("border-color", elementColor)));
                },
                () -> assertThrows(WebElementSizeAssertionError.class,
                        () -> textTable.should(haveSize(196)))
        );
    }

}
