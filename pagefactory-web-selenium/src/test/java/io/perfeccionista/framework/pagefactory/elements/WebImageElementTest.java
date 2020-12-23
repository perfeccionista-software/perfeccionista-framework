package io.perfeccionista.framework.pagefactory.elements;

import io.perfeccionista.framework.Environment;
import io.perfeccionista.framework.color.Color;
import io.perfeccionista.framework.exceptions.WebElementColor.WebElementColorAssertionError;
import io.perfeccionista.framework.exceptions.WebElementDimensions.WebElementDimensionsAssertionError;
import io.perfeccionista.framework.exceptions.WebElementIsDisplayed.WebElementIsDisplayedAssertionError;
import io.perfeccionista.framework.exceptions.WebElementIsPresent.WebElementIsPresentAssertionError;
import io.perfeccionista.framework.exceptions.WebElementLocation.WebElementLocationAssertionError;
import io.perfeccionista.framework.exceptions.WebElementNotInFocus.WebElementNotInFocusAssertionError;
import io.perfeccionista.framework.exceptions.WebElementPropertyValue.WebElementPropertyValueAssertionError;
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
import io.perfeccionista.framework.value.ValueService;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.time.Duration;

import static io.perfeccionista.framework.matcher.WebElementAssertions.beDisplayed;
import static io.perfeccionista.framework.matcher.WebElementAssertions.beInFocus;
import static io.perfeccionista.framework.matcher.WebElementAssertions.bePresent;
import static io.perfeccionista.framework.matcher.WebElementAssertions.componentBeDisplayed;
import static io.perfeccionista.framework.matcher.WebElementAssertions.componentBePresent;
import static io.perfeccionista.framework.matcher.WebElementAssertions.componentNotBeDisplayed;
import static io.perfeccionista.framework.matcher.WebElementAssertions.componentNotBePresent;
import static io.perfeccionista.framework.matcher.WebElementAssertions.haveColor;
import static io.perfeccionista.framework.matcher.WebElementAssertions.haveDimensions;
import static io.perfeccionista.framework.matcher.WebElementAssertions.haveLocation;
import static io.perfeccionista.framework.matcher.WebElementAssertions.havePropertyValue;
import static io.perfeccionista.framework.matcher.WebElementAssertions.notBeDisplayed;
import static io.perfeccionista.framework.matcher.WebElementAssertions.notBeInFocus;
import static io.perfeccionista.framework.matcher.WebElementAssertions.notBePresent;
import static io.perfeccionista.framework.matcher.WebElementAssertions.notHaveColor;
import static io.perfeccionista.framework.matcher.WebElementAssertions.notHaveDimensions;
import static io.perfeccionista.framework.matcher.WebElementAssertions.notHaveLocation;
import static io.perfeccionista.framework.matcher.WebElementAssertions.notHavePropertyValue;
import static io.perfeccionista.framework.pagefactory.elements.actions.WebElementActionNames.IS_ON_THE_SCREEN_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.components.WebComponents.ROOT;
import static io.perfeccionista.framework.pagefactory.elements.actions.WebElementActionNames.CLICK_METHOD;
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
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;

@Tag("WebElement") @Tag("WebImage")
class WebImageElementTest extends AbstractUiTest {

    @Test
    void webImageInitializationTest(Environment env) {
        WebPageFactory pageFactory = new WebPageFactory(new DefaultSeleniumWebPageFactoryPreferences());
        ElementsPage elementsPage = (ElementsPage) pageFactory.createWebPage(ElementsPage.class);
        elementsPage.setEnvironment(env);
        elementsPage.setWebBrowserDispatcher(mock(WebBrowserDispatcher.class));
        WebImage worldMap = elementsPage.worldMap();
        assertNotNull(worldMap.getEnvironment());
        assertNotNull(worldMap.getLocatorChain());
        assertNotNull(worldMap.getWebBrowserDispatcher());
        assertNotNull(worldMap.getOptionalLocator(ROOT));
        // WebImage
        assertNotNull(worldMap.getActionImplementation(CLICK_METHOD, Void.class));
        // WebChildElement
        assertNotNull(worldMap.getActionImplementation(GET_COLOR_METHOD, Color.class));
        assertNotNull(worldMap.getActionImplementation(GET_DIMENSIONS_METHOD, Dimensions.class));
        assertNotNull(worldMap.getActionImplementation(GET_LOCATION_METHOD, Location.class));
        assertNotNull(worldMap.getActionImplementation(GET_PROPERTY_VALUE_METHOD, String.class));
        assertNotNull(worldMap.getActionImplementation(GET_SCREENSHOT_METHOD, Screenshot.class));
        assertNotNull(worldMap.getActionImplementation(HOVER_TO_METHOD, Void.class));
        assertNotNull(worldMap.getActionImplementation(IS_COMPONENT_DISPLAYED_METHOD, Boolean.class));
        assertNotNull(worldMap.getActionImplementation(IS_COMPONENT_PRESENT_METHOD, Boolean.class));
        assertNotNull(worldMap.getActionImplementation(IS_DISPLAYED_METHOD, Boolean.class));
        assertNotNull(worldMap.getActionImplementation(IS_IN_FOCUS_METHOD, Boolean.class));
        assertNotNull(worldMap.getActionImplementation(IS_ON_THE_SCREEN_METHOD, Boolean.class));
        assertNotNull(worldMap.getActionImplementation(IS_PRESENT_METHOD, Boolean.class));
        assertNotNull(worldMap.getActionImplementation(SCROLL_TO_METHOD, Void.class));
        // Identifier
        WebElementIdentifier elementIdentifier = worldMap.getElementIdentifier();
        assertEquals("worldMap", elementIdentifier.getElementMethod().getName());
        assertEquals("worldMap", elementIdentifier.getLastUsedName());
        assertTrue(elementIdentifier.containsName("World map"));
        assertFalse(elementIdentifier.isNameDeprecated("World map"));
        assertEquals(2, elementIdentifier.names().size());
        WebImage simpleImageElement = elementsPage.getElementRegistry()
                .getRequiredElementByPath("World map", WebImage.class);
        assertNotNull(simpleImageElement);
        assertEquals("World map", elementIdentifier.getLastUsedName());
        assertNotNull(worldMap.toString());
        // Смотрим описание элемента
        System.out.println( worldMap.toString() );
    }

    /**
     * Case: Проверяем что присутствует левое меню
     *  Проверяем текст заголовка
     *  Проверяем основной текст
     * @param env - Экземпляр окружения теста
     * @param value - Экземпляр сервиса значений
     */
    @Test
    void webImagePositiveTest(Environment env, ValueService value) {
        WebPageContext context = initWebPageContext(env, value);
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
                .should(haveDimensions(Dimensions.of(176.3d, 125.4d).setInaccuracy(0.2d)))
                .should(haveLocation(Location.relative(345d, 173d).setInaccuracy(0.2d)))
                .should(haveColor("border-color", Color.of(222, 226, 230, 1.0d)))
                .should(havePropertyValue("prompt", "World map picture"))
                .should(havePropertyValue("source", value.stringContains("src/static/world_map.jpeg")))
                .should(havePropertyValue("source", value.stringContainsAll("src", "static", "world", "jpeg")))
                .should(notHavePropertyValue("prompt", value.stringContains("image")))
                .should(notHavePropertyValue("source", value.stringEmpty()));
        assertTrue(worldMap.isPresent());
        assertTrue(worldMap.isDisplayed());
        assertFalse(worldMap.isInFocus());
        assertTrue(worldMap.isComponentPresent("Image border"));
        assertTrue(worldMap.isComponentDisplayed("Image border"));
        assertEquals(Dimensions.of(176.3d, 125.4d).setInaccuracy(0.2d), worldMap.getDimensions(ROOT));
        assertEquals(Location.absolute(345d, 173d).setInaccuracy(0.2d), worldMap.getLocation(ROOT));
        assertEquals(Color.of(222, 226, 230, 1.0d), worldMap.getColor(ROOT, "border-color"));
        Screenshot screenshot = worldMap.getScreenshot(ROOT);
        assertNotNull(screenshot);
        assertTrue(value.intGreaterThan(10000).check(screenshot.getRaw().length));
        assertEquals("World map picture", worldMap.getPropertyValue("prompt"));
        assertEquals(Point.of(88.3d, 62.7d).setInaccuracy(0.2d), worldMap.getDimensions(ROOT).getCenter());
    }

    @Test
    void webImageNegativeTest(Environment env, ValueService value) {
        WebPageContext context = initWebPageContext(env, value);
        context.getPage(HomePage.class).leftMenu()
                .select("Elements");
        ElementsPage elementsPage = context.getPage(ElementsPage.class);
        WebImage worldMap = elementsPage.worldMap();
        // Выполнение этого метода показывает завершение загрузки страницы
        worldMap.should(beDisplayed());
        // Для негативных сценариев меняем время ожидания, чтобы не ждать по 5 секунд проброса ошибки вне враппера
        env.getEnvironmentConfiguration().getTimeouts()
                .setTimeout(CheckTimeout.class, Duration.ofMillis(100L));
        assertThrows(WebElementIsPresentAssertionError.class,
                () -> worldMap.should(notBePresent()));
        assertThrows(WebElementIsDisplayedAssertionError.class,
                () -> worldMap.should(notBeDisplayed()));
        assertThrows(WebElementNotInFocusAssertionError.class,
                () -> worldMap.should(beInFocus()));
        assertThrows(WebElementIsPresentAssertionError.class,
                () -> worldMap.should(componentNotBePresent("Image border")));
        assertThrows(WebElementIsDisplayedAssertionError.class,
                () -> worldMap.should(componentNotBeDisplayed("Image border")));
        Dimensions elementDimensions = Dimensions.of(176.3d, 125.4d).setInaccuracy(0.2d);
        assertThrows(WebElementDimensionsAssertionError.class,
                () -> worldMap.should(notHaveDimensions(elementDimensions)));
        Location elementLocation = Location.relative(345d, 173d).setInaccuracy(0.2d);
        assertThrows(WebElementLocationAssertionError.class,
                () -> worldMap.should(haveLocation(elementLocation.offset(15d, 1d))));
        assertThrows(WebElementLocationAssertionError.class,
                () -> worldMap.should(notHaveLocation(elementLocation)));
        Color elementColor = Color.of(222, 226, 230, 1.0d);
        assertThrows(WebElementColorAssertionError.class,
                () -> worldMap.should(notHaveColor("border-color", elementColor)));
        assertThrows(WebElementPropertyValueAssertionError.class,
                () -> worldMap.should(notHavePropertyValue("prompt", "World map picture")));
        assertThrows(WebElementPropertyValueAssertionError.class,
                () -> worldMap.should(havePropertyValue("prompt", "image")));
    }

}
