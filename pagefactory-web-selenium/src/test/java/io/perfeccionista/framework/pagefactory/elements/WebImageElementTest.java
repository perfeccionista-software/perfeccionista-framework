package io.perfeccionista.framework.pagefactory.elements;

import io.perfeccionista.framework.Environment;
import io.perfeccionista.framework.attachment.JsonAttachmentEntry;
import io.perfeccionista.framework.exceptions.ElementColorException;
import io.perfeccionista.framework.exceptions.ElementDimensionsException;
import io.perfeccionista.framework.exceptions.ElementIsDisplayedException;
import io.perfeccionista.framework.exceptions.ElementIsPresentException;
import io.perfeccionista.framework.exceptions.ElementLocationException;
import io.perfeccionista.framework.exceptions.ElementNotDeclaredException;
import io.perfeccionista.framework.exceptions.ElementNotDisplayedException;
import io.perfeccionista.framework.exceptions.ElementNotInFocusException;
import io.perfeccionista.framework.exceptions.ElementPropertyValueException;
import io.perfeccionista.framework.invocation.timeouts.CheckTimeout;
import io.perfeccionista.framework.name.WebElementIdentifier;
import io.perfeccionista.framework.pagefactory.AbstractUiTest;
import io.perfeccionista.framework.pagefactory.DefaultSeleniumWebElementsConfiguration;
import io.perfeccionista.framework.pagefactory.browser.WebBrowserDispatcher;
import io.perfeccionista.framework.pagefactory.browser.context.WebPageContext;
import io.perfeccionista.framework.pagefactory.elements.methods.Dimensions;
import io.perfeccionista.framework.pagefactory.elements.methods.Location;
import io.perfeccionista.framework.pagefactory.factory.WebPageFactory;
import io.perfeccionista.framework.pagefactory.pageobjects.ElementsPage;
import io.perfeccionista.framework.pagefactory.pageobjects.HomePage;
import io.perfeccionista.framework.pagefactory.screenshots.Screenshot;
import io.perfeccionista.framework.plugin.WebElementColor;
import io.perfeccionista.framework.value.ValueService;
import io.perfeccionista.framework.value.string.StringValue;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.util.Set;

import static io.perfeccionista.framework.exceptions.base.ExceptionType.ASSERT;
import static io.perfeccionista.framework.exceptions.messages.PageFactoryMessages.ELEMENT_NOT_DECLARED;
import static io.perfeccionista.framework.exceptions.messages.PageFactoryMessages.ELEMENT_NOT_DISPLAYED;
import static io.perfeccionista.framework.pagefactory.elements.components.WebComponents.ROOT;
import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.CLICK_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.COMPONENT_SHOULD_BE_DISPLAYED_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.COMPONENT_SHOULD_BE_PRESENT_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.COMPONENT_SHOULD_NOT_BE_DISPLAYED_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.COMPONENT_SHOULD_NOT_BE_PRESENT_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.GET_COLOR_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.GET_DIMENSIONS_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.GET_LOCATION_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.GET_PROPERTY_VALUE_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.GET_SCREENSHOT_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.HOVER_TO_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.IS_COMPONENT_DISPLAYED_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.IS_COMPONENT_PRESENT_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.IS_DISPLAYED_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.IS_IN_FOCUS_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.IS_PRESENT_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.SCROLL_TO_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.SHOULD_BE_DISPLAYED_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.SHOULD_BE_IN_FOCUS_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.SHOULD_BE_PRESENT_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.SHOULD_HAVE_COLOR_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.SHOULD_HAVE_DIMENSIONS_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.SHOULD_HAVE_LOCATION_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.SHOULD_HAVE_PROPERTY_NUMBER_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.SHOULD_HAVE_PROPERTY_VALUE_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.SHOULD_LOOKS_LIKE_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.SHOULD_NOT_BE_DISPLAYED_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.SHOULD_NOT_BE_IN_FOCUS_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.SHOULD_NOT_BE_PRESENT_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.SHOULD_NOT_HAVE_COLOR_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.SHOULD_NOT_HAVE_DIMENSIONS_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.SHOULD_NOT_HAVE_LOCATION_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.SHOULD_NOT_HAVE_PROPERTY_NUMBER_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.SHOULD_NOT_HAVE_PROPERTY_VALUE_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.SHOULD_NOT_LOOKS_LIKE_METHOD;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;

class WebImageElementTest extends AbstractUiTest {

    @Test
    void webImageInitializationTest(Environment env) {
        WebPageFactory pageFactory = new WebPageFactory(new DefaultSeleniumWebElementsConfiguration());
        ElementsPage elementsPage = (ElementsPage) pageFactory.createWebPage(ElementsPage.class);
        elementsPage.setEnvironment(env);
        elementsPage.setWebBrowserDispatcher(mock(WebBrowserDispatcher.class));
        WebImage worldMap = elementsPage.worldMap();
        assertNotNull(worldMap.getEnvironment());
        assertNotNull(worldMap.getLocator(ROOT));
        assertNotNull(worldMap.getLocatorChain());
        assertEquals(elementsPage, worldMap.getParent());
        WebBrowserDispatcher webBrowserDispatcher = worldMap.getWebBrowserDispatcher();
        assertNotNull(webBrowserDispatcher);
        // WebImage
        assertNotNull(worldMap.getActionImplementation(CLICK_METHOD, Void.class));
        // WebChildElement
        assertNotNull(worldMap.getActionImplementation(COMPONENT_SHOULD_BE_DISPLAYED_METHOD, Void.class));
        assertNotNull(worldMap.getActionImplementation(COMPONENT_SHOULD_BE_PRESENT_METHOD, Void.class));
        assertNotNull(worldMap.getActionImplementation(COMPONENT_SHOULD_NOT_BE_DISPLAYED_METHOD, Void.class));
        assertNotNull(worldMap.getActionImplementation(COMPONENT_SHOULD_NOT_BE_PRESENT_METHOD, Void.class));
        assertNotNull(worldMap.getActionImplementation(GET_COLOR_METHOD, WebElementColor.class));
        assertNotNull(worldMap.getActionImplementation(GET_DIMENSIONS_METHOD, Dimensions.class));
        assertNotNull(worldMap.getActionImplementation(GET_LOCATION_METHOD, Location.class));
        assertNotNull(worldMap.getActionImplementation(GET_PROPERTY_VALUE_METHOD, String.class));
        assertNotNull(worldMap.getActionImplementation(GET_SCREENSHOT_METHOD, Screenshot.class));
        assertNotNull(worldMap.getActionImplementation(HOVER_TO_METHOD, Void.class));
        assertNotNull(worldMap.getActionImplementation(IS_COMPONENT_DISPLAYED_METHOD, Boolean.class));
        assertNotNull(worldMap.getActionImplementation(IS_COMPONENT_PRESENT_METHOD, Boolean.class));
        assertNotNull(worldMap.getActionImplementation(IS_DISPLAYED_METHOD, Boolean.class));
        assertNotNull(worldMap.getActionImplementation(IS_IN_FOCUS_METHOD, Boolean.class));
        assertNotNull(worldMap.getActionImplementation(IS_PRESENT_METHOD, Boolean.class));
        assertNotNull(worldMap.getActionImplementation(SCROLL_TO_METHOD, Void.class));
        assertNotNull(worldMap.getActionImplementation(SHOULD_BE_DISPLAYED_METHOD, Void.class));
        assertNotNull(worldMap.getActionImplementation(SHOULD_BE_IN_FOCUS_METHOD, Void.class));
        assertNotNull(worldMap.getActionImplementation(SHOULD_BE_PRESENT_METHOD, Void.class));
        assertNotNull(worldMap.getActionImplementation(SHOULD_HAVE_COLOR_METHOD, Void.class));
        assertNotNull(worldMap.getActionImplementation(SHOULD_HAVE_DIMENSIONS_METHOD, Void.class));
        assertNotNull(worldMap.getActionImplementation(SHOULD_HAVE_LOCATION_METHOD, Void.class));
        assertNotNull(worldMap.getActionImplementation(SHOULD_HAVE_PROPERTY_NUMBER_METHOD, Void.class));
        assertNotNull(worldMap.getActionImplementation(SHOULD_HAVE_PROPERTY_VALUE_METHOD, Void.class));
        assertNotNull(worldMap.getActionImplementation(SHOULD_LOOKS_LIKE_METHOD, Void.class));
        assertNotNull(worldMap.getActionImplementation(SHOULD_NOT_BE_DISPLAYED_METHOD, Void.class));
        assertNotNull(worldMap.getActionImplementation(SHOULD_NOT_BE_IN_FOCUS_METHOD, Void.class));
        assertNotNull(worldMap.getActionImplementation(SHOULD_NOT_BE_PRESENT_METHOD, Void.class));
        assertNotNull(worldMap.getActionImplementation(SHOULD_NOT_HAVE_COLOR_METHOD, Void.class));
        assertNotNull(worldMap.getActionImplementation(SHOULD_NOT_HAVE_DIMENSIONS_METHOD, Void.class));
        assertNotNull(worldMap.getActionImplementation(SHOULD_NOT_HAVE_LOCATION_METHOD, Void.class));
        assertNotNull(worldMap.getActionImplementation(SHOULD_NOT_HAVE_PROPERTY_NUMBER_METHOD, Void.class));
        assertNotNull(worldMap.getActionImplementation(SHOULD_NOT_HAVE_PROPERTY_VALUE_METHOD, Void.class));
        assertNotNull(worldMap.getActionImplementation(SHOULD_NOT_LOOKS_LIKE_METHOD, Void.class));
        // Identifier
        WebElementIdentifier elementIdentifier = worldMap.getElementIdentifier();
        assertEquals("worldMap", elementIdentifier.getElementMethod().getName());
        assertEquals("worldMap", elementIdentifier.getLastUsedName());
        assertTrue(elementIdentifier.containsName("World map"));
        assertFalse(elementIdentifier.isNameDeprecated("World map"));
        assertEquals(1, elementIdentifier.names().size());
        WebImage simpleImageElement = elementsPage.getElementRegistry().getElementByPath("World map", WebImage.class)
                .orElseThrow(() -> new ElementNotDeclaredException(ELEMENT_NOT_DECLARED.getMessage("World map")));
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
        context.getPage(HomePage.class)
                .leftMenu()
                .select(value.stringEquals("Elements"));
        ElementsPage elementsPage = context.getPage(ElementsPage.class);
        WebImage worldMap = elementsPage.worldMap();
        worldMap.shouldBePresent()
                .shouldBeDisplayed()
                .shouldNotBeInFocus()
                .scrollTo()
                .hoverTo(true)
                .click()
                .componentShouldBePresent("Image border")
                .componentShouldBeDisplayed("Image border")
                .componentShouldHaveDimensions(ROOT, Dimensions.of(176.3d, 125.4d).setInaccuracy(0.2d))
                .componentShouldHaveLocation(ROOT, Location.relative(345d, 173d).setInaccuracy(0.2d))
                .componentShouldHaveColor(ROOT, "border-color", WebElementColor.of(222, 226, 230, 1.0d))
                .shouldHavePropertyValue("prompt", value.stringEquals("World map picture"))
                .shouldHavePropertyValue("source", value.stringContains("src/static/world_map.jpeg"))
                .shouldHavePropertyValue("source", value.stringContainsAll("src", "static", "world", "jpeg"))
                .shouldNotHavePropertyValue("prompt", value.stringContains("image"))
                .shouldNotHavePropertyValue("source", value.stringEmpty())
                .should(worldMapImage -> {
                    if (worldMapImage.isDisplayed()) {
                        return;
                    }
                    throw new ElementNotDisplayedException(ELEMENT_NOT_DISPLAYED.getMessage(worldMapImage.getElementIdentifier().getLastUsedName()))
                            .setType(ASSERT)
                            .addAttachmentEntry(JsonAttachmentEntry.of("Element", worldMapImage.toJson()));
                });
        assertTrue(worldMap.isPresent());
        assertTrue(worldMap.isDisplayed());
        assertFalse(worldMap.isInFocus());
        assertTrue(worldMap.isComponentPresent("Image border"));
        assertTrue(worldMap.isComponentDisplayed("Image border"));
        assertEquals(Dimensions.of(176.3d, 125.4d).setInaccuracy(0.2d), worldMap.getDimensions(ROOT));
        assertEquals(Location.absolute(345d, 173d).setInaccuracy(0.2d), worldMap.getLocation(ROOT));
        assertEquals(WebElementColor.of(222, 226, 230, 1.0d), worldMap.getColor(ROOT, "border-color"));
        Screenshot screenshot = worldMap.getScreenshot(ROOT);
        assertNotNull(screenshot);
        assertTrue(value.intGreaterThan(10000).check(screenshot.getRaw().length));
        assertEquals("World map picture", worldMap.getPropertyValue("prompt"));
    }

    @Test
    void webImageNegativeTest(Environment env, ValueService value) {
        WebPageContext context = initWebPageContext(env, value);
        context.getPage(HomePage.class)
                .leftMenu()
                .select(value.stringEquals("Elements"));
        ElementsPage elementsPage = context.getPage(ElementsPage.class);
        WebImage worldMap = elementsPage.worldMap();
        // Выполнение этого метода показывает завершение загрузки страницы
        worldMap.shouldBeDisplayed();
        // Для негативных сценариев меняем время ожидания, чтобы не ждать по 5 секунд проброса ошибки вне враппера
        env.getEnvironmentConfiguration().getTimeouts().setTimeout(CheckTimeout.class, Duration.ofMillis(100L));
        assertThrows(ElementIsPresentException.class, worldMap::shouldNotBePresent);
        assertThrows(ElementIsDisplayedException.class, worldMap::shouldNotBeDisplayed);
        assertThrows(ElementNotInFocusException.class, worldMap::shouldBeInFocus);
        assertThrows(ElementIsPresentException.class, () -> worldMap.componentShouldNotBePresent("Image border"));
        assertThrows(ElementIsDisplayedException.class, () -> worldMap.componentShouldNotBeDisplayed("Image border"));
        Dimensions elementDimensions = Dimensions.of(176.3d, 125.4d).setInaccuracy(0.2d);
        assertThrows(ElementDimensionsException.class, () -> worldMap.componentShouldNotHaveDimensions(ROOT, elementDimensions));
        Location elementLocation = Location.of(345d, 173d, 345d, 173d).setInaccuracy(0.2d);
        assertThrows(ElementLocationException.class, () -> worldMap.componentShouldHaveLocation(ROOT, elementLocation.offset(15d, 1d)));
        assertThrows(ElementLocationException.class, () -> worldMap.componentShouldNotHaveLocation(ROOT, elementLocation));
        WebElementColor elementColor = WebElementColor.of(222, 226, 230, 1.0d);
        assertThrows(ElementColorException.class, () -> worldMap.componentShouldNotHaveColor(ROOT, "border-color", elementColor));
        StringValue correctPropertyValue = value.stringEquals("World map picture");
        assertThrows(ElementPropertyValueException.class, () -> worldMap.shouldNotHavePropertyValue("prompt", correctPropertyValue));
        StringValue incorrectPropertyValue = value.stringEquals("image");
        assertThrows(ElementPropertyValueException.class, () -> worldMap.shouldHavePropertyValue("prompt", incorrectPropertyValue));
    }

}
