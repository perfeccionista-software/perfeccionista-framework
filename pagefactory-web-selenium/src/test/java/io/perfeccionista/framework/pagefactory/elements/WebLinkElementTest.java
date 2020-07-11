package io.perfeccionista.framework.pagefactory.elements;

import io.perfeccionista.framework.Environment;
import io.perfeccionista.framework.attachment.JsonAttachmentEntry;
import io.perfeccionista.framework.exceptions.ElementColorException;
import io.perfeccionista.framework.exceptions.ElementDimensionsException;
import io.perfeccionista.framework.exceptions.ElementInFocusException;
import io.perfeccionista.framework.exceptions.ElementIsDisplayedException;
import io.perfeccionista.framework.exceptions.ElementIsPresentException;
import io.perfeccionista.framework.exceptions.ElementLocationException;
import io.perfeccionista.framework.exceptions.ElementNotDeclaredException;
import io.perfeccionista.framework.exceptions.ElementNotDisplayedException;
import io.perfeccionista.framework.exceptions.ElementNotInFocusException;
import io.perfeccionista.framework.exceptions.ElementTextValueException;
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
import org.junit.jupiter.api.Test;

import java.time.Duration;

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
import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.GET_TEXT_METHOD;
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
import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.SHOULD_HAVE_NUMBER_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.SHOULD_HAVE_PROPERTY_NUMBER_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.SHOULD_HAVE_PROPERTY_VALUE_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.SHOULD_HAVE_TEXT_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.SHOULD_LOOKS_LIKE_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.SHOULD_NOT_BE_DISPLAYED_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.SHOULD_NOT_BE_IN_FOCUS_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.SHOULD_NOT_BE_PRESENT_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.SHOULD_NOT_HAVE_COLOR_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.SHOULD_NOT_HAVE_DIMENSIONS_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.SHOULD_NOT_HAVE_LOCATION_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.SHOULD_NOT_HAVE_NUMBER_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.SHOULD_NOT_HAVE_PROPERTY_NUMBER_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.SHOULD_NOT_HAVE_PROPERTY_VALUE_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.SHOULD_NOT_HAVE_TEXT_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.SHOULD_NOT_LOOKS_LIKE_METHOD;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;

class WebLinkElementTest extends AbstractUiTest {

    @Test
    void webLinkInitializationTest(Environment env) {
        WebPageFactory pageFactory = new WebPageFactory(new DefaultSeleniumWebElementsConfiguration());
        ElementsPage elementsPage = (ElementsPage) pageFactory.createWebPage(ElementsPage.class);
        elementsPage.setEnvironment(env);
        elementsPage.setWebBrowserDispatcher(mock(WebBrowserDispatcher.class));
        WebLink simpleLink = elementsPage.simpleLink();
        assertNotNull(simpleLink.getEnvironment());
        assertNotNull(simpleLink.getLocator(ROOT));
        assertNotNull(simpleLink.getLocatorChain());
        assertEquals(elementsPage, simpleLink.getParent());
        WebBrowserDispatcher webBrowserDispatcher = simpleLink.getWebBrowserDispatcher();
        assertNotNull(webBrowserDispatcher);
        // WebButton
        assertNotNull(simpleLink.getActionImplementation(CLICK_METHOD, Void.class));
        assertNotNull(simpleLink.getActionImplementation(GET_TEXT_METHOD, String.class));
        assertNotNull(simpleLink.getActionImplementation(SHOULD_HAVE_TEXT_METHOD, Void.class));
        assertNotNull(simpleLink.getActionImplementation(SHOULD_HAVE_NUMBER_METHOD, Void.class));
        assertNotNull(simpleLink.getActionImplementation(SHOULD_NOT_HAVE_TEXT_METHOD, Void.class));
        assertNotNull(simpleLink.getActionImplementation(SHOULD_NOT_HAVE_NUMBER_METHOD, Void.class));
        // WebChildElement
        assertNotNull(simpleLink.getActionImplementation(COMPONENT_SHOULD_BE_DISPLAYED_METHOD, Void.class));
        assertNotNull(simpleLink.getActionImplementation(COMPONENT_SHOULD_BE_PRESENT_METHOD, Void.class));
        assertNotNull(simpleLink.getActionImplementation(COMPONENT_SHOULD_NOT_BE_DISPLAYED_METHOD, Void.class));
        assertNotNull(simpleLink.getActionImplementation(COMPONENT_SHOULD_NOT_BE_PRESENT_METHOD, Void.class));
        assertNotNull(simpleLink.getActionImplementation(GET_COLOR_METHOD, WebElementColor.class));
        assertNotNull(simpleLink.getActionImplementation(GET_DIMENSIONS_METHOD, Dimensions.class));
        assertNotNull(simpleLink.getActionImplementation(GET_LOCATION_METHOD, Location.class));
        assertNotNull(simpleLink.getActionImplementation(GET_PROPERTY_VALUE_METHOD, String.class));
        assertNotNull(simpleLink.getActionImplementation(GET_SCREENSHOT_METHOD, Screenshot.class));
        assertNotNull(simpleLink.getActionImplementation(HOVER_TO_METHOD, Void.class));
        assertNotNull(simpleLink.getActionImplementation(IS_COMPONENT_DISPLAYED_METHOD, Boolean.class));
        assertNotNull(simpleLink.getActionImplementation(IS_COMPONENT_PRESENT_METHOD, Boolean.class));
        assertNotNull(simpleLink.getActionImplementation(IS_DISPLAYED_METHOD, Boolean.class));
        assertNotNull(simpleLink.getActionImplementation(IS_IN_FOCUS_METHOD, Boolean.class));
        assertNotNull(simpleLink.getActionImplementation(IS_PRESENT_METHOD, Boolean.class));
        assertNotNull(simpleLink.getActionImplementation(SCROLL_TO_METHOD, Void.class));
        assertNotNull(simpleLink.getActionImplementation(SHOULD_BE_DISPLAYED_METHOD, Void.class));
        assertNotNull(simpleLink.getActionImplementation(SHOULD_BE_IN_FOCUS_METHOD, Void.class));
        assertNotNull(simpleLink.getActionImplementation(SHOULD_BE_PRESENT_METHOD, Void.class));
        assertNotNull(simpleLink.getActionImplementation(SHOULD_HAVE_COLOR_METHOD, Void.class));
        assertNotNull(simpleLink.getActionImplementation(SHOULD_HAVE_DIMENSIONS_METHOD, Void.class));
        assertNotNull(simpleLink.getActionImplementation(SHOULD_HAVE_LOCATION_METHOD, Void.class));
        assertNotNull(simpleLink.getActionImplementation(SHOULD_HAVE_PROPERTY_NUMBER_METHOD, Void.class));
        assertNotNull(simpleLink.getActionImplementation(SHOULD_HAVE_PROPERTY_VALUE_METHOD, Void.class));
        assertNotNull(simpleLink.getActionImplementation(SHOULD_LOOKS_LIKE_METHOD, Void.class));
        assertNotNull(simpleLink.getActionImplementation(SHOULD_NOT_BE_DISPLAYED_METHOD, Void.class));
        assertNotNull(simpleLink.getActionImplementation(SHOULD_NOT_BE_IN_FOCUS_METHOD, Void.class));
        assertNotNull(simpleLink.getActionImplementation(SHOULD_NOT_BE_PRESENT_METHOD, Void.class));
        assertNotNull(simpleLink.getActionImplementation(SHOULD_NOT_HAVE_COLOR_METHOD, Void.class));
        assertNotNull(simpleLink.getActionImplementation(SHOULD_NOT_HAVE_DIMENSIONS_METHOD, Void.class));
        assertNotNull(simpleLink.getActionImplementation(SHOULD_NOT_HAVE_LOCATION_METHOD, Void.class));
        assertNotNull(simpleLink.getActionImplementation(SHOULD_NOT_HAVE_PROPERTY_NUMBER_METHOD, Void.class));
        assertNotNull(simpleLink.getActionImplementation(SHOULD_NOT_HAVE_PROPERTY_VALUE_METHOD, Void.class));
        assertNotNull(simpleLink.getActionImplementation(SHOULD_NOT_LOOKS_LIKE_METHOD, Void.class));
        // Identifier
        WebElementIdentifier elementIdentifier = simpleLink.getElementIdentifier();
        assertEquals("simpleLink", elementIdentifier.getElementMethod().getName());
        assertEquals("simpleLink", elementIdentifier.getLastUsedName());
        assertTrue(elementIdentifier.containsName("Simple link"));
        assertFalse(elementIdentifier.isNameDeprecated("Simple link"));
        assertEquals(1, elementIdentifier.names().size());
        WebLink simpleLinkElement = elementsPage.getElementRegistry().getElementByPath("Simple link", WebLink.class)
                .orElseThrow(() -> new ElementNotDeclaredException(ELEMENT_NOT_DECLARED.getMessage("Simple link")));
        assertNotNull(simpleLinkElement);
        assertEquals("Simple link", elementIdentifier.getLastUsedName());
        assertNotNull(simpleLink.toString());
        // Смотрим описание элемента
        System.out.println( simpleLink.toString() );
    }

    /**
     * Case: Проверяем что присутствует левое меню
     *  Проверяем текст заголовка
     *  Проверяем основной текст
     * @param env - Экземпляр окружения теста
     * @param value - Экземпляр сервиса значений
     */
    @Test
    void webLinkPositiveTest(Environment env, ValueService value) {
        WebPageContext context = initWebPageContext(env, value);
        context.getPage(HomePage.class)
                .leftMenu()
                .select(value.stringEquals("Elements"));
        ElementsPage elementsPage = context.getPage(ElementsPage.class);
        WebLink simpleLink = elementsPage.simpleLink();
        // Check Simple button
        simpleLink.shouldBePresent()
                .shouldBeDisplayed()
                .shouldNotBeInFocus()
                .scrollTo()
                .componentShouldHaveDimensions(ROOT, Dimensions.of(81.8d, 22.0d).setInaccuracy(0.2d))
                .componentShouldHaveLocation(ROOT, Location.relative(392.2d, 478.4d).setInaccuracy(0.2d))
                .componentShouldHaveColor(ROOT, "color", WebElementColor.of(0, 123, 255, 1.0d))
                .hoverTo(true)
                .componentShouldHaveColor(ROOT, "color", WebElementColor.of(0, 86, 179, 1.0d))
                .shouldHaveText(value.stringEquals("Simple Link"))
                .shouldHaveText(value.stringContains("Link"))
                .shouldNotHaveText(value.stringContains("button"))
                .should(simpleLinkElement -> {
                    if (simpleLinkElement.isDisplayed()) {
                        return;
                    }
                    throw new ElementNotDisplayedException(ELEMENT_NOT_DISPLAYED.getMessage(simpleLinkElement.getElementIdentifier().getLastUsedName()))
                            .setType(ASSERT)
                            .addAttachmentEntry(JsonAttachmentEntry.of("Element", simpleLinkElement.toJson()));
                });
        assertTrue(simpleLink.isPresent());
        assertTrue(simpleLink.isDisplayed());
        assertFalse(simpleLink.isInFocus());
        assertEquals(Dimensions.of(81.8d, 22.0d).setInaccuracy(0.2d), simpleLink.getDimensions(ROOT));
        assertEquals(Location.absolute(392.2d, 478.4d).setInaccuracy(0.2d), simpleLink.getLocation(ROOT));
        assertEquals(WebElementColor.of(0, 86, 179, 1.0d), simpleLink.getColor(ROOT, "color"));
        Screenshot screenshot = simpleLink.getScreenshot(ROOT);
        assertNotNull(screenshot);
        assertTrue(value.intGreaterThan(2500).check(screenshot.getRaw().length));
        // Check Simple button text
        WebTextBlock simpleLinkText = elementsPage.simpleLinkText();
        simpleLinkText.shouldBePresent()
                .shouldNotBeDisplayed();
        assertTrue(simpleLinkText.isPresent());
        assertFalse(simpleLinkText.isDisplayed());
        // Simple button click
        simpleLink.click()
                .shouldBeInFocus();
        // Check Simple button text
        simpleLinkText.shouldBePresent()
                .shouldBeDisplayed()
                .shouldNotBeInFocus();
    }

    @Test
    void webLinkNegativeTest(Environment env, ValueService value) {
        WebPageContext context = initWebPageContext(env, value);
        context.getPage(HomePage.class)
                .leftMenu()
                .select(value.stringEquals("Elements"));
        ElementsPage elementsPage = context.getPage(ElementsPage.class);
        WebLink simpleLink = elementsPage.simpleLink();
        // Выполнение этого метода показывает завершение загрузки страницы
        simpleLink.shouldBeDisplayed();
        // Для негативных сценариев меняем время ожидания, чтобы не ждать по 5 секунд проброса ошибки вне враппера
        env.getEnvironmentConfiguration().getTimeouts().setTimeout(CheckTimeout.class, Duration.ofMillis(100L));
        assertThrows(ElementIsPresentException.class, simpleLink::shouldNotBePresent);
        assertThrows(ElementIsDisplayedException.class, simpleLink::shouldNotBeDisplayed);
        assertThrows(ElementNotInFocusException.class, simpleLink::shouldBeInFocus);
        Dimensions elementDimensions = Dimensions.of(81.8d, 22.0d).setInaccuracy(0.2d);
        assertThrows(ElementDimensionsException.class, () -> simpleLink.componentShouldNotHaveDimensions(ROOT, elementDimensions));
        Location elementLocation = Location.of(392.2d, 478.4d, 392.2d, 478.4d).setInaccuracy(0.2d);
        assertThrows(ElementLocationException.class, () -> simpleLink.componentShouldHaveLocation(ROOT, elementLocation.offset(54d, -4d)));
        assertThrows(ElementLocationException.class, () -> simpleLink.componentShouldNotHaveLocation(ROOT, elementLocation));
        WebElementColor elementColor = WebElementColor.of(0, 123, 255, 1.0d);
        assertThrows(ElementColorException.class, () -> simpleLink.componentShouldNotHaveColor(ROOT, "color", elementColor));
        assertThrows(ElementTextValueException.class, () -> simpleLink.shouldNotHaveText(value.stringEquals("Simple Link")));
        assertThrows(ElementTextValueException.class, () -> simpleLink.shouldHaveText(value.stringEquals("Simple Button")));
        WebTextBlock simpleLinkText = elementsPage.simpleLinkText();
        assertThrows(ElementIsPresentException.class, simpleLinkText::shouldNotBePresent);
        assertThrows(ElementNotDisplayedException.class, simpleLinkText::shouldBeDisplayed);
        simpleLink.click();
        assertThrows(ElementInFocusException.class, simpleLink::shouldNotBeInFocus);
        assertThrows(ElementIsPresentException.class, simpleLinkText::shouldNotBePresent);
        assertThrows(ElementIsDisplayedException.class, simpleLinkText::shouldNotBeDisplayed);
    }

}
