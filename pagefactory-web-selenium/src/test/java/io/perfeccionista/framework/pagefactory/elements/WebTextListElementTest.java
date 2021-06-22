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
import io.perfeccionista.framework.pagefactory.pageobjects.TextListElementsPage;
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
import static io.perfeccionista.framework.value.Values.stringEquals;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;

@Tag("WebElement") @Tag("WebTextList")
class WebTextListElementTest extends AbstractWebSeleniumParallelTest {

    @Test
    void webTableInitializationTest(Environment environment) {
        WebPageFactory pageFactory = new WebPageFactory(new DefaultSeleniumWebPageFactoryPreferences());
        TextListElementsPage textListPage = (TextListElementsPage) pageFactory.createWebPage(TextListElementsPage.class);
        textListPage.setEnvironment(environment);
        textListPage.setWebBrowserDispatcher(mock(WebBrowserDispatcher.class));
        WebTextList textList = textListPage.textList();
        WebElementIdentifier elementIdentifier = textList.getElementIdentifier();
        assertAll(
                () -> assertNotNull(textList.getEnvironment()),
                () -> assertNotNull(textList.getLocatorChain()),
                () -> assertNotNull(textList.getWebBrowserDispatcher()),
                () -> assertNotNull(textList.getOptionalLocator(ROOT)),
                // WebChildElement
                () -> assertNotNull(textList.getEndpointHandler(GET_COLOR_METHOD, Color.class)),
                () -> assertNotNull(textList.getEndpointHandler(GET_ELEMENT_BOUNDS_METHOD, ElementBounds.class)),
                () -> assertNotNull(textList.getEndpointHandler(GET_SCREENSHOT_METHOD, Screenshot.class)),
                () -> assertNotNull(textList.getEndpointHandler(HOVER_TO_METHOD, Void.class)),
                () -> assertNotNull(textList.getEndpointHandler(IS_COMPONENT_DISPLAYED_METHOD, Boolean.class)),
                () -> assertNotNull(textList.getEndpointHandler(IS_COMPONENT_PRESENT_METHOD, Boolean.class)),
                () -> assertNotNull(textList.getEndpointHandler(IS_DISPLAYED_METHOD, Boolean.class)),
                () -> assertNotNull(textList.getEndpointHandler(IS_IN_FOCUS_METHOD, Boolean.class)),
                () -> assertNotNull(textList.getEndpointHandler(IS_ON_THE_SCREEN_METHOD, Boolean.class)),
                () -> assertNotNull(textList.getEndpointHandler(IS_PRESENT_METHOD, Boolean.class)),
                () -> assertNotNull(textList.getEndpointHandler(SCROLL_TO_METHOD, Void.class)),
                // Identifier
                () -> assertEquals("textList", elementIdentifier.getElementMethod().getName()),
                () -> assertEquals("textList", elementIdentifier.getLastUsedName()),
                () -> assertTrue(elementIdentifier.containsName("Simple list of countries")),
                () -> assertFalse(elementIdentifier.isNameDeprecated("Simple list of countries")),
                () -> assertEquals(2, elementIdentifier.names().size()),
                () -> {
                    WebTextList textListByName = textListPage.getElementRegistry()
                            .getRequiredElementByPath("Simple list of countries", WebTextList.class);
                    assertAll(
                            () -> assertNotNull(textListByName),
                            () -> assertEquals("Simple list of countries", elementIdentifier.getLastUsedName()),
                            () -> assertNotNull(textListByName.toString())
                    );
                }
        );
    }

    @Test
    void webTextListPositiveTest() {
        WebPageContext context = initWebPageContext();
        context.getPage(HomePage.class).leftMenu()
                .select(stringEquals("Text List Elements"));

        TextListElementsPage textListElementsPage = context.getPage(TextListElementsPage.class);
        WebTextList textList = textListElementsPage.textList();
        textList.should(bePresent())
                .should(beDisplayed())
                .should(notBeInFocus())
                .scrollTo()
                .hoverTo(true)
                .should(haveDimensions(Dimensions2D.of(795.0d, 9508.0d).setInaccuracy(0.2d)))
                .should(haveScreenLocation(Point2D.of(345d, -4315d).setInaccuracy(0.2d)))
                .should(haveColor("border-color", Color.of(33, 37, 41, 1.0d)))
                .should(haveSize(195));
        assertAll(
                () -> assertTrue(textList.isPresent()),
                () -> assertTrue(textList.isDisplayed()),
                () -> assertFalse(textList.isInFocus()),
                () -> assertEquals(Dimensions2D.of(795.0d, 9508.0d).setInaccuracy(0.2d), textList.getElementBounds().getDimensions()),
                () -> assertEquals(Point2D.of(345d, 281d).setInaccuracy(0.2d), textList.getElementBounds().getAbsoluteLocation()),
                () -> assertEquals(Color.of(33, 37, 41, 1.0d), textList.getColor("border-color")),
                () -> assertEquals(195, textList.filter(emptyWebTextListFilter()).extractAll().getSize()),
                () -> assertEquals(Point2D.of(757.5d, 439d).setInaccuracy(0.2d), textList.getElementBounds().getCenter())
        );
    }

    @Test
    void webTextListNegativeTest(Environment environment) {
        WebPageContext context = initWebPageContext();
        context.getPage(HomePage.class).leftMenu()
                .select(stringEquals("Text List Elements"));

        TextListElementsPage textListElementsPage = context.getPage(TextListElementsPage.class);
        WebTextList textList = textListElementsPage.textList();
        // Выполнение этого метода показывает завершение загрузки страницы
        textList
                .should(beDisplayed());
        // Для негативных сценариев меняем время ожидания, чтобы не ждать по 5 секунд проброса ошибки вне враппера
        environment.getService(TimeoutsService.class)
                .setTimeout(CheckTimeout.class, Duration.ofMillis(100L));
        assertAll(
                () -> assertThrows(ElementIsPresentAssertionError.class,
                        () -> textList.should(notBePresent())),
                () -> assertThrows(ElementIsDisplayedAssertionError.class,
                        () -> textList.should(notBeDisplayed())),
                () -> assertThrows(WebElementNotInFocusAssertionError.class,
                        () -> textList.should(beInFocus())),
                () -> {
                    Dimensions2D elementDimensions = Dimensions2D.of(795.0d, 9508.0d).setInaccuracy(0.2d);
                    assertThrows(WebElementDimensionsAssertionError.class,
                            () -> textList.should(notHaveDimensions(elementDimensions)));
                },
                () -> {
                    Point2D elementLocation = Point2D.of(345d, 281d).setInaccuracy(0.2d);
                    assertAll(
                            () -> assertThrows(WebElementLocationAssertionError.class,
                                    () -> textList.should(haveScreenLocation(elementLocation.offset(15d, 1d)))),
                            () -> assertThrows(WebElementLocationAssertionError.class,
                                    () -> textList.should(notHaveScreenLocation(elementLocation)))
                    );

                },
                () -> {
                    Color elementColor = Color.of(33, 37, 41, 1.0d);
                    assertThrows(WebElementColorAssertionError.class,
                            () -> textList.should(notHaveColor("border-color", elementColor)));
                },
                () -> assertThrows(WebElementSizeAssertionError.class,
                        () -> textList.should(haveSize(196)))
        );
    }

}
