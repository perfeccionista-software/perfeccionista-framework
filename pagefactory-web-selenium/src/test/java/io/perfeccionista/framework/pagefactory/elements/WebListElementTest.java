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
import io.perfeccionista.framework.invocation.timeouts.type.RepeatInvocationTimeout;
import io.perfeccionista.framework.name.WebElementIdentifier;
import io.perfeccionista.framework.AbstractWebSeleniumParallelTest;
import io.perfeccionista.framework.pagefactory.elements.preferences.DefaultSeleniumWebPageFactoryPreferences;
import io.perfeccionista.framework.pagefactory.dispatcher.WebBrowserDispatcher;
import io.perfeccionista.framework.pagefactory.dispatcher.context.WebPageContext;
import io.perfeccionista.framework.measurements.Dimensions2D;
import io.perfeccionista.framework.measurements.Point2D;
import io.perfeccionista.framework.pagefactory.factory.WebPageFactory;
import io.perfeccionista.framework.pagefactory.pageobjects.HomePage;
import io.perfeccionista.framework.pagefactory.pageobjects.ListElementsPage;
import io.perfeccionista.framework.pagefactory.pageobjects.blocks.list.CountryBlock;
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

@Tag("WebElement") @Tag("WebList")
class WebListElementTest extends AbstractWebSeleniumParallelTest {

    @Test
    void webListInitializationTest(Environment environment) {
        WebPageFactory pageFactory = new WebPageFactory(new DefaultSeleniumWebPageFactoryPreferences());
        ListElementsPage listElementsPage = (ListElementsPage) pageFactory.createWebPage(ListElementsPage.class);
        listElementsPage.setEnvironment(environment);
        listElementsPage.setWebBrowserDispatcher(mock(WebBrowserDispatcher.class));
        WebList<CountryBlock> list = listElementsPage.webList();
        WebElementIdentifier elementIdentifier = list.getElementIdentifier();
        assertAll(
                () -> assertNotNull(list.getEnvironment()),
                () -> assertNotNull(list.getSelectorChain()),
                () -> assertNotNull(list.getWebBrowserDispatcher()),
                () -> assertNotNull(list.getOptionalSelector(ROOT)),
                // WebChildElement
                () -> assertNotNull(list.getEndpointHandler(GET_COLOR_METHOD, Color.class)),
                () -> assertNotNull(list.getEndpointHandler(GET_ELEMENT_BOUNDS_METHOD, ElementBounds.class)),
                () -> assertNotNull(list.getEndpointHandler(GET_SCREENSHOT_METHOD, Screenshot.class)),
                () -> assertNotNull(list.getEndpointHandler(HOVER_TO_METHOD, Void.class)),
                () -> assertNotNull(list.getEndpointHandler(IS_COMPONENT_DISPLAYED_METHOD, Boolean.class)),
                () -> assertNotNull(list.getEndpointHandler(IS_COMPONENT_PRESENT_METHOD, Boolean.class)),
                () -> assertNotNull(list.getEndpointHandler(IS_DISPLAYED_METHOD, Boolean.class)),
                () -> assertNotNull(list.getEndpointHandler(IS_IN_FOCUS_METHOD, Boolean.class)),
                () -> assertNotNull(list.getEndpointHandler(IS_ON_THE_SCREEN_METHOD, Boolean.class)),
                () -> assertNotNull(list.getEndpointHandler(IS_PRESENT_METHOD, Boolean.class)),
                () -> assertNotNull(list.getEndpointHandler(SCROLL_TO_METHOD, Void.class)),
                // Identifier
                () -> assertEquals("webList", elementIdentifier.getElementMethod().getName()),
                () -> assertEquals("List of countries", elementIdentifier.getLastUsedName()),
                () -> assertTrue(elementIdentifier.containsName("List of countries")),
                () -> assertFalse(elementIdentifier.isNameDeprecated("List of countries")),
                () -> assertEquals(2, elementIdentifier.names().size()),
                () -> {
                    WebList<CountryBlock> listByName = listElementsPage.getElementRegistry()
                            .getRequiredElementByPath("List of countries", WebList.class);
                    assertAll(
                            () -> assertNotNull(listByName),
                            () -> assertEquals("List of countries", elementIdentifier.getLastUsedName()),
                            () -> assertNotNull(listByName.toString())
                    );
                }
        );
    }

    @Test
    void webListPositiveTest() {
        WebPageContext context = initWebPageContext();
        context.getPage(HomePage.class).leftMenu()
                .select("List Elements");

        ListElementsPage listElementsPage = context.getPage(ListElementsPage.class);
        WebList<CountryBlock> list = listElementsPage.webList();
        list.should(bePresent())
                .should(beDisplayed())
                .should(notBeInFocus())
                .scrollTo()
                .hoverTo(true)
                .should(haveDimensions(Dimensions2D.of(795.0d, 10295.0d).setInaccuracy(0.2d)))
                .should(haveScreenLocation(Point2D.of(345d, -4709d).setInaccuracy(0.2d)))
                .should(haveColor("border-color", Color.of(33, 37, 41, 1.0d)))
                .should(haveSize(195));
        assertAll(
                () -> assertTrue(list.isPresent()),
                () -> assertTrue(list.isDisplayed()),
                () -> assertFalse(list.isInFocus()),
                () -> assertEquals(Dimensions2D.of(795.0d, 10295.0d).setInaccuracy(0.2d), list.getElementBounds().getDimensions()),
                () -> assertEquals(Point2D.of(345d, 227d).setInaccuracy(0.2d), list.getElementBounds().getAbsoluteLocation()),
                () -> assertEquals(Color.of(33, 37, 41, 1.0d), list.getColor("border-color")),
                () -> assertEquals(195, list.extractAll(block()).getSize()),
                () -> assertEquals(Point2D.of(757.5d, 438.5d).setInaccuracy(0.2d), list.getElementBounds().getCenter()),
                () -> assertEquals(195, list.size())
        );
    }

    @Test
    void webListNegativeTest(Environment environment) {
        WebPageContext context = initWebPageContext();
        context.getPage(HomePage.class).leftMenu()
                .select("List Elements");

        ListElementsPage listElementsPage = context.getPage(ListElementsPage.class);
        WebList<CountryBlock> list = listElementsPage.webList();

        list.forFirst(countryBlock -> {
            countryBlock.hoverTo().
        })


        // Выполнение этого метода показывает завершение загрузки страницы
        list.should(beDisplayed());
        // Для негативных сценариев меняем время ожидания, чтобы не ждать по 5 секунд проброса ошибки вне враппера
        environment.getService(TimeoutsService.class)
                .setTimeout(RepeatInvocationTimeout.class, Duration.ofMillis(100L));
        assertAll(
                () -> assertThrows(ElementIsPresentAssertionError.class,
                        () -> list.should(notBePresent())),
                () -> assertThrows(ElementIsDisplayedAssertionError.class,
                        () -> list.should(notBeDisplayed())),
                () -> assertThrows(WebElementNotInFocusAssertionError.class,
                        () -> list.should(beInFocus())),
                () -> {
                    Dimensions2D elementDimensions = Dimensions2D.of(795.0d, 10295.0d).setInaccuracy(0.2d);
                    assertThrows(WebElementDimensionsAssertionError.class,
                            () -> list.should(notHaveDimensions(elementDimensions)));
                },
                () -> {
                    Point2D elementLocation = Point2D.of(345d, 227d).setInaccuracy(0.2d);
                    assertAll(
                            () -> assertThrows(WebElementLocationAssertionError.class,
                                    () -> list.should(haveScreenLocation(elementLocation.offset(15d, 1d)))),
                            () -> assertThrows(WebElementLocationAssertionError.class,
                                    () -> list.should(notHaveScreenLocation(elementLocation)))
                    );
                },
                () -> {
                    Color elementColor = Color.of(33, 37, 41, 1.0d);
                    assertThrows(WebElementColorAssertionError.class,
                            () -> list.should(notHaveColor("border-color", elementColor)));
                },
                () -> assertThrows(WebElementSizeAssertionError.class,
                        () -> list.should(haveSize(196)))
        );
    }

}
