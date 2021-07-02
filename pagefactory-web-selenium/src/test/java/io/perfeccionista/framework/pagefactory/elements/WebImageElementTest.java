package io.perfeccionista.framework.pagefactory.elements;

import io.perfeccionista.framework.Environment;
import io.perfeccionista.framework.color.Color;
import io.perfeccionista.framework.exceptions.WebElementColor.WebElementColorAssertionError;
import io.perfeccionista.framework.exceptions.WebElementDimensions.WebElementDimensionsAssertionError;
import io.perfeccionista.framework.exceptions.ElementIsDisplayed.ElementIsDisplayedAssertionError;
import io.perfeccionista.framework.exceptions.ElementIsPresent.ElementIsPresentAssertionError;
import io.perfeccionista.framework.exceptions.WebElementLocation.WebElementLocationAssertionError;
import io.perfeccionista.framework.exceptions.WebElementNotInFocus.WebElementNotInFocusAssertionError;
import io.perfeccionista.framework.exceptions.WebElementPropertyValue.WebElementPropertyValueAssertionError;
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
import static io.perfeccionista.framework.pagefactory.elements.ElementComponents.ROOT;
import static io.perfeccionista.framework.pagefactory.elements.ElementActionNames.CLICK_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.ElementActionNames.GET_COLOR_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.ElementActionNames.GET_SCREENSHOT_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.ElementActionNames.HOVER_TO_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.ElementActionNames.IS_COMPONENT_DISPLAYED_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.ElementActionNames.IS_COMPONENT_PRESENT_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.ElementActionNames.IS_DISPLAYED_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.ElementActionNames.IS_IN_FOCUS_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.ElementActionNames.IS_PRESENT_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.ElementActionNames.SCROLL_TO_METHOD;
import static io.perfeccionista.framework.value.Values.intGreaterThan;
import static io.perfeccionista.framework.value.Values.stringContains;
import static io.perfeccionista.framework.value.Values.stringContainsAll;
import static io.perfeccionista.framework.value.Values.stringEmpty;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;

@Tag("WebElement") @Tag("WebImage")
class WebImageElementTest extends AbstractWebSeleniumParallelTest {

    @Test
    void webImageInitializationTest(Environment environment) {
        WebPageFactory pageFactory = new WebPageFactory(new DefaultSeleniumWebPageFactoryPreferences());
        ElementsPage elementsPage = (ElementsPage) pageFactory.createWebPage(ElementsPage.class);
        elementsPage.setEnvironment(environment);
        elementsPage.setWebBrowserDispatcher(mock(WebBrowserDispatcher.class));
        WebImage worldMap = elementsPage.worldMap();
        WebElementIdentifier elementIdentifier = worldMap.getElementIdentifier();
        assertAll(
                () -> assertNotNull(worldMap.getEnvironment()),
                () -> assertNotNull(worldMap.getLocatorChain()),
                () -> assertNotNull(worldMap.getWebBrowserDispatcher()),
                () -> assertNotNull(worldMap.getOptionalLocator(ROOT)),
                // WebImage
                () -> assertNotNull(worldMap.getEndpointHandler(CLICK_METHOD, Void.class)),
                // WebChildElement
                () -> assertNotNull(worldMap.getEndpointHandler(GET_COLOR_METHOD, Color.class)),
                () -> assertNotNull(worldMap.getEndpointHandler(GET_ELEMENT_BOUNDS_METHOD, ElementBounds.class)),
                () -> assertNotNull(worldMap.getEndpointHandler(GET_SCREENSHOT_METHOD, Screenshot.class)),
                () -> assertNotNull(worldMap.getEndpointHandler(HOVER_TO_METHOD, Void.class)),
                () -> assertNotNull(worldMap.getEndpointHandler(IS_COMPONENT_DISPLAYED_METHOD, Boolean.class)),
                () -> assertNotNull(worldMap.getEndpointHandler(IS_COMPONENT_PRESENT_METHOD, Boolean.class)),
                () -> assertNotNull(worldMap.getEndpointHandler(IS_DISPLAYED_METHOD, Boolean.class)),
                () -> assertNotNull(worldMap.getEndpointHandler(IS_IN_FOCUS_METHOD, Boolean.class)),
                () -> assertNotNull(worldMap.getEndpointHandler(IS_ON_THE_SCREEN_METHOD, Boolean.class)),
                () -> assertNotNull(worldMap.getEndpointHandler(IS_PRESENT_METHOD, Boolean.class)),
                () -> assertNotNull(worldMap.getEndpointHandler(SCROLL_TO_METHOD, Void.class)),
                // Identifier
                () -> assertEquals("worldMap", elementIdentifier.getElementMethod().getName()),
                () -> assertEquals("worldMap", elementIdentifier.getLastUsedName()),
                () -> assertTrue(elementIdentifier.containsName("World map")),
                () -> assertFalse(elementIdentifier.isNameDeprecated("World map")),
                () -> assertEquals(2, elementIdentifier.names().size()),
                () -> {
                    WebImage worldMapByName = elementsPage.getElementRegistry()
                            .getRequiredElementByPath("World map", WebImage.class);
                    assertAll(
                            () -> assertNotNull(worldMapByName),
                            () -> assertEquals("World map", elementIdentifier.getLastUsedName()),
                            () -> assertNotNull(worldMapByName.toString())
                    );
                }
        );
    }

    /**
     * Case: Проверяем что присутствует левое меню
     *  Проверяем текст заголовка
     *  Проверяем основной текст
     */
    @Test
    void webImagePositiveTest() {
        WebPageContext context = initWebPageContext();
        context.getPage(HomePage.class).leftMenu()
                .select("Elements");

        ElementsPage elementsPage = context.getPage(ElementsPage.class);
        WebImage worldMap = elementsPage.worldMap();
        worldMap.should(bePresent())
                .should(beDisplayed())
                .should(notBeInFocus())
                .scrollTo()
                .hoverTo(true)
                .click()
                .should(componentBePresent("Image border"))
                .should(componentBeDisplayed("Image border"))
                .should(haveDimensions(Dimensions2D.of(176.3d, 125.4d).setInaccuracy(0.2d)))
                .should(haveScreenLocation(Point2D.of(345d, 173d).setInaccuracy(0.2d)))
                .should(haveColor("border-color", Color.of(222, 226, 230, 1.0d)))
                .should(havePropertyValue("prompt", "World map picture"))
                .should(havePropertyValue("source", stringContains("src/static/world_map.jpeg")))
                .should(havePropertyValue("source", stringContainsAll("src", "static", "world", "jpeg")))
                .should(notHavePropertyValue("prompt", stringContains("image")))
                .should(notHavePropertyValue("source", stringEmpty()));
        assertAll(
                () -> assertTrue(worldMap.isPresent()),
                () -> assertTrue(worldMap.isDisplayed()),
                () -> assertFalse(worldMap.isInFocus()),
                () -> assertTrue(worldMap.isComponentPresent("Image border")),
                () -> assertTrue(worldMap.isComponentDisplayed("Image border")),
                () -> assertEquals(Dimensions2D.of(176.3d, 125.4d).setInaccuracy(0.2d), worldMap.getElementBounds().getDimensions()),
                () -> assertEquals(Point2D.of(345d, 173d).setInaccuracy(0.2d), worldMap.getElementBounds().getAbsoluteLocation()),
                () -> assertEquals(Color.of(222, 226, 230, 1.0d), worldMap.getColor("border-color")),
                () -> {
                    Screenshot screenshot = worldMap.getScreenshot();
                    assertAll(
                            () -> assertNotNull(screenshot),
                            () -> assertTrue(intGreaterThan(10000).check(screenshot.getRaw().length))
                    );
                },
                () -> assertEquals("World map picture", worldMap.getPropertyValue("prompt")),
                () -> assertEquals(Point2D.of(448.0d, 235.7d).setInaccuracy(0.2d), worldMap.getElementBounds().getCenter())
        );
    }

    @Test
    void webImageNegativeTest(Environment environment) {
        WebPageContext context = initWebPageContext();
        context.getPage(HomePage.class).leftMenu()
                .select("Elements");

        ElementsPage elementsPage = context.getPage(ElementsPage.class);
        WebImage worldMap = elementsPage.worldMap();
        // Выполнение этого метода показывает завершение загрузки страницы
        worldMap.should(beDisplayed());
        // Для негативных сценариев меняем время ожидания, чтобы не ждать по 5 секунд проброса ошибки вне враппера
        environment.getService(TimeoutsService.class)
                .setTimeout(CheckTimeout.class, Duration.ofMillis(100L));
        assertAll(
                () -> assertThrows(ElementIsPresentAssertionError.class,
                        () -> worldMap.should(notBePresent())),
                () -> assertThrows(ElementIsDisplayedAssertionError.class,
                        () -> worldMap.should(notBeDisplayed())),
                () -> assertThrows(WebElementNotInFocusAssertionError.class,
                        () -> worldMap.should(beInFocus())),
                () -> assertThrows(ElementIsPresentAssertionError.class,
                        () -> worldMap.should(componentNotBePresent("Image border"))),
                () -> assertThrows(ElementIsDisplayedAssertionError.class,
                        () -> worldMap.should(componentNotBeDisplayed("Image border"))),
                () -> {
                    Dimensions2D elementDimensions = Dimensions2D.of(176.3d, 125.4d).setInaccuracy(0.2d);
                    assertThrows(WebElementDimensionsAssertionError.class,
                            () -> worldMap.should(notHaveDimensions(elementDimensions)));
                },
                () -> {
                    Point2D elementLocation = Point2D.of(345d, 173d).setInaccuracy(0.2d);
                    assertAll(
                            () -> assertThrows(WebElementLocationAssertionError.class,
                                    () -> worldMap.should(haveScreenLocation(elementLocation.offset(15d, 1d)))),
                            () -> assertThrows(WebElementLocationAssertionError.class,
                                    () -> worldMap.should(notHaveScreenLocation(elementLocation)))
                    );
                },
                () -> {
                    Color elementColor = Color.of(222, 226, 230, 1.0d);
                    assertThrows(WebElementColorAssertionError.class,
                            () -> worldMap.should(notHaveColor("border-color", elementColor)));
                },
                () -> assertThrows(WebElementPropertyValueAssertionError.class,
                        () -> worldMap.should(notHavePropertyValue("prompt", "World map picture"))),
                () -> assertThrows(WebElementPropertyValueAssertionError.class,
                        () -> worldMap.should(havePropertyValue("prompt", "image")))
        );
    }

}
