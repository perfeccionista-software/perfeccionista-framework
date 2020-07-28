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
import io.perfeccionista.framework.exceptions.ElementNotPresentException;
import io.perfeccionista.framework.exceptions.ElementTextValueException;
import io.perfeccionista.framework.invocation.timeouts.CheckTimeout;
import io.perfeccionista.framework.name.WebElementIdentifier;
import io.perfeccionista.framework.pagefactory.AbstractUiTest;
import io.perfeccionista.framework.pagefactory.DefaultSeleniumWebElementsConfiguration;
import io.perfeccionista.framework.pagefactory.browser.WebBrowserDispatcher;
import io.perfeccionista.framework.pagefactory.browser.context.WebPageContext;
import io.perfeccionista.framework.pagefactory.elements.methods.Dimensions;
import io.perfeccionista.framework.pagefactory.elements.methods.Location;
import io.perfeccionista.framework.pagefactory.elements.methods.Point;
import io.perfeccionista.framework.pagefactory.factory.WebPageFactory;
import io.perfeccionista.framework.pagefactory.pageobjects.ElementsPage;
import io.perfeccionista.framework.pagefactory.pageobjects.HomePage;
import io.perfeccionista.framework.pagefactory.screenshots.Screenshot;
import io.perfeccionista.framework.plugin.WebElementColor;
import io.perfeccionista.framework.value.ValueService;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
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

@Tags(@Tag("Element"))
class WebButtonElementTest extends AbstractUiTest {

    @Test
    void webButtonInitializationTest(Environment env) {
        WebPageFactory pageFactory = new WebPageFactory(new DefaultSeleniumWebElementsConfiguration());
        ElementsPage elementsPage = (ElementsPage) pageFactory.createWebPage(ElementsPage.class);
        elementsPage.setEnvironment(env);
        elementsPage.setWebBrowserDispatcher(mock(WebBrowserDispatcher.class));
        WebButton simpleButton = elementsPage.simpleButton();
        assertNotNull(simpleButton.getEnvironment());
        assertNotNull(simpleButton.getLocator(ROOT));
        assertNotNull(simpleButton.getLocatorChain());
        assertEquals(elementsPage, simpleButton.getParent());
        WebBrowserDispatcher webBrowserDispatcher = simpleButton.getWebBrowserDispatcher();
        assertNotNull(webBrowserDispatcher);
        // WebButton
        assertNotNull(simpleButton.getActionImplementation(CLICK_METHOD, Void.class));
        assertNotNull(simpleButton.getActionImplementation(GET_TEXT_METHOD, String.class));
        assertNotNull(simpleButton.getActionImplementation(SHOULD_HAVE_TEXT_METHOD, Void.class));
        assertNotNull(simpleButton.getActionImplementation(SHOULD_HAVE_NUMBER_METHOD, Void.class));
        assertNotNull(simpleButton.getActionImplementation(SHOULD_NOT_HAVE_TEXT_METHOD, Void.class));
        assertNotNull(simpleButton.getActionImplementation(SHOULD_NOT_HAVE_NUMBER_METHOD, Void.class));
        // WebChildElement
        assertNotNull(simpleButton.getActionImplementation(COMPONENT_SHOULD_BE_DISPLAYED_METHOD, Void.class));
        assertNotNull(simpleButton.getActionImplementation(COMPONENT_SHOULD_BE_PRESENT_METHOD, Void.class));
        assertNotNull(simpleButton.getActionImplementation(COMPONENT_SHOULD_NOT_BE_DISPLAYED_METHOD, Void.class));
        assertNotNull(simpleButton.getActionImplementation(COMPONENT_SHOULD_NOT_BE_PRESENT_METHOD, Void.class));
        assertNotNull(simpleButton.getActionImplementation(GET_COLOR_METHOD, WebElementColor.class));
        assertNotNull(simpleButton.getActionImplementation(GET_DIMENSIONS_METHOD, Dimensions.class));
        assertNotNull(simpleButton.getActionImplementation(GET_LOCATION_METHOD, Location.class));
        assertNotNull(simpleButton.getActionImplementation(GET_PROPERTY_VALUE_METHOD, String.class));
        assertNotNull(simpleButton.getActionImplementation(GET_SCREENSHOT_METHOD, Screenshot.class));
        assertNotNull(simpleButton.getActionImplementation(HOVER_TO_METHOD, Void.class));
        assertNotNull(simpleButton.getActionImplementation(IS_COMPONENT_DISPLAYED_METHOD, Boolean.class));
        assertNotNull(simpleButton.getActionImplementation(IS_COMPONENT_PRESENT_METHOD, Boolean.class));
        assertNotNull(simpleButton.getActionImplementation(IS_DISPLAYED_METHOD, Boolean.class));
        assertNotNull(simpleButton.getActionImplementation(IS_IN_FOCUS_METHOD, Boolean.class));
        assertNotNull(simpleButton.getActionImplementation(IS_PRESENT_METHOD, Boolean.class));
        assertNotNull(simpleButton.getActionImplementation(SCROLL_TO_METHOD, Void.class));
        assertNotNull(simpleButton.getActionImplementation(SHOULD_BE_DISPLAYED_METHOD, Void.class));
        assertNotNull(simpleButton.getActionImplementation(SHOULD_BE_IN_FOCUS_METHOD, Void.class));
        assertNotNull(simpleButton.getActionImplementation(SHOULD_BE_PRESENT_METHOD, Void.class));
        assertNotNull(simpleButton.getActionImplementation(SHOULD_HAVE_COLOR_METHOD, Void.class));
        assertNotNull(simpleButton.getActionImplementation(SHOULD_HAVE_DIMENSIONS_METHOD, Void.class));
        assertNotNull(simpleButton.getActionImplementation(SHOULD_HAVE_LOCATION_METHOD, Void.class));
        assertNotNull(simpleButton.getActionImplementation(SHOULD_HAVE_PROPERTY_NUMBER_METHOD, Void.class));
        assertNotNull(simpleButton.getActionImplementation(SHOULD_HAVE_PROPERTY_VALUE_METHOD, Void.class));
        assertNotNull(simpleButton.getActionImplementation(SHOULD_LOOKS_LIKE_METHOD, Void.class));
        assertNotNull(simpleButton.getActionImplementation(SHOULD_NOT_BE_DISPLAYED_METHOD, Void.class));
        assertNotNull(simpleButton.getActionImplementation(SHOULD_NOT_BE_IN_FOCUS_METHOD, Void.class));
        assertNotNull(simpleButton.getActionImplementation(SHOULD_NOT_BE_PRESENT_METHOD, Void.class));
        assertNotNull(simpleButton.getActionImplementation(SHOULD_NOT_HAVE_COLOR_METHOD, Void.class));
        assertNotNull(simpleButton.getActionImplementation(SHOULD_NOT_HAVE_DIMENSIONS_METHOD, Void.class));
        assertNotNull(simpleButton.getActionImplementation(SHOULD_NOT_HAVE_LOCATION_METHOD, Void.class));
        assertNotNull(simpleButton.getActionImplementation(SHOULD_NOT_HAVE_PROPERTY_NUMBER_METHOD, Void.class));
        assertNotNull(simpleButton.getActionImplementation(SHOULD_NOT_HAVE_PROPERTY_VALUE_METHOD, Void.class));
        assertNotNull(simpleButton.getActionImplementation(SHOULD_NOT_LOOKS_LIKE_METHOD, Void.class));
        // Identifier
        WebElementIdentifier elementIdentifier = simpleButton.getElementIdentifier();
        assertEquals("simpleButton", elementIdentifier.getElementMethod().getName());
        assertEquals("simpleButton", elementIdentifier.getLastUsedName());
        assertTrue(elementIdentifier.containsName("Simple button"));
        assertFalse(elementIdentifier.isNameDeprecated("Simple button"));
        assertEquals(1, elementIdentifier.names().size());
        WebButton simpleButtonElement = elementsPage.getElementRegistry().getElementByPath("Simple button", WebButton.class)
                .orElseThrow(() -> new ElementNotDeclaredException(ELEMENT_NOT_DECLARED.getMessage("Simple button")));
        assertNotNull(simpleButtonElement);
        assertEquals("Simple button", elementIdentifier.getLastUsedName());
        assertNotNull(simpleButton.toString());
        // Смотрим описание элемента
        System.out.println( simpleButton.toString() );
    }

    /**
     * Case: Проверяем что присутствует левое меню
     *  Проверяем текст заголовка
     *  Проверяем основной текст
     * @param env - Экземпляр окружения теста
     * @param value - Экземпляр сервиса значений
     */
    @Test
    void webButtonPositiveTest(Environment env, ValueService value) {
        WebPageContext context = initWebPageContext(env, value);
        context.getPage(HomePage.class)
                .leftMenu()
                .select(value.stringEquals("Elements"));
        ElementsPage elementsPage = context.getPage(ElementsPage.class);
        WebButton simpleButton = elementsPage.simpleButton();
        // Check Simple button
        simpleButton.shouldBePresent()
                .shouldBeDisplayed()
                .shouldNotBeInFocus()
                .scrollTo()
                .componentShouldHaveDimensions(ROOT, Dimensions.of(127.6d, 38.0d).setInaccuracy(0.2d))
                .componentShouldHaveLocation(ROOT, Location.relative(369.3d, 314.3d).setInaccuracy(0.2d))
                .componentShouldHaveLocation(ROOT, Location.center(472.3d, 333.4d).setInaccuracy(0.2d))
                .componentShouldHaveColor(ROOT, "background-color", WebElementColor.of(0, 123, 255, 1.0d))
                .hoverTo(true)
                .componentShouldHaveColor(ROOT, "background-color", WebElementColor.of(0, 105, 217, 1.0d))
                .shouldHaveText(value.stringEquals("Simple Button"))
                .shouldHaveText(value.stringContains("Button"))
                .shouldNotHaveText(value.stringContains("link"))
                .should(simpleButtonElement -> {
                    if (simpleButtonElement.isDisplayed()) {
                        return;
                    }
                    throw new ElementNotDisplayedException(ELEMENT_NOT_DISPLAYED.getMessage(simpleButtonElement.getElementIdentifier().getLastUsedName()))
                            .setType(ASSERT)
                            .addAttachmentEntry(JsonAttachmentEntry.of("Element", simpleButtonElement.toJson()));
                });
        assertTrue(simpleButton.isPresent());
        assertTrue(simpleButton.isDisplayed());
        assertFalse(simpleButton.isInFocus());
        assertEquals(Dimensions.of(127.6d, 38.0d).setInaccuracy(0.2d), simpleButton.getDimensions(ROOT));
        assertEquals(Location.absolute(369.3d, 314.3d).setInaccuracy(0.2d), simpleButton.getLocation(ROOT));
        assertEquals(WebElementColor.of(0, 105, 217, 1.0d), simpleButton.getColor(ROOT, "background-color"));
        Screenshot screenshot = simpleButton.getScreenshot(ROOT);
        assertNotNull(screenshot);
        assertTrue(value.intGreaterThan(4500).check(screenshot.getRaw().length));
        // Check Simple button text
        WebTextBlock simpleButtonText = elementsPage.simpleButtonText();
        simpleButtonText.shouldNotBePresent()
                .shouldNotBeDisplayed();
        assertFalse(simpleButtonText.isPresent());
        assertFalse(simpleButtonText.isDisplayed());
        assertEquals(Point.of(63.8d, 19d).setInaccuracy(0.2d), simpleButton.getDimensions(ROOT).getCenter());
        // Simple button click
        simpleButton.click()
                .shouldBeInFocus();
        // Check Simple button text
        simpleButtonText.shouldBePresent()
                .shouldBeDisplayed()
                .shouldNotBeInFocus();
    }

    @Test
    void webButtonNegativeTest(Environment env, ValueService value) {
        WebPageContext context = initWebPageContext(env, value);
        context.getPage(HomePage.class)
                .leftMenu()
                .select(value.stringEquals("Elements"));
        ElementsPage elementsPage = context.getPage(ElementsPage.class);
        WebButton simpleButton = elementsPage.simpleButton();
        // Выполнение этого метода показывает завершение загрузки страницы
        simpleButton.shouldBeDisplayed();
        // Для негативных сценариев меняем время ожидания, чтобы не ждать по 5 секунд проброса ошибки вне враппера
        env.getEnvironmentConfiguration().getTimeouts().setTimeout(CheckTimeout.class, Duration.ofMillis(100L));
        assertThrows(ElementIsPresentException.class, simpleButton::shouldNotBePresent);
        assertThrows(ElementIsDisplayedException.class, simpleButton::shouldNotBeDisplayed);
        assertThrows(ElementNotInFocusException.class, simpleButton::shouldBeInFocus);
        Dimensions elementDimensions = Dimensions.of(127.6d, 38.0d).setInaccuracy(0.2d);
        assertThrows(ElementDimensionsException.class, () -> simpleButton.componentShouldNotHaveDimensions(ROOT, elementDimensions));
        Location elementLocation = Location.relative(369.3d, 314.3d).setInaccuracy(0.2d);
        assertThrows(ElementLocationException.class, () -> simpleButton.componentShouldHaveLocation(ROOT, elementLocation.offset(12d, 10d)));
        assertThrows(ElementLocationException.class, () -> simpleButton.componentShouldNotHaveLocation(ROOT, elementLocation));
        WebElementColor elementColor = WebElementColor.of(0, 123, 255, 1.0d);
        assertThrows(ElementColorException.class, () -> simpleButton.componentShouldNotHaveColor(ROOT, "background-color", elementColor));
        assertThrows(ElementTextValueException.class, () -> simpleButton.shouldNotHaveText(value.stringEquals("Simple Button")));
        assertThrows(ElementTextValueException.class, () -> simpleButton.shouldHaveText(value.stringEquals("Simple Link")));
        WebTextBlock simpleButtonText = elementsPage.simpleButtonText();
        assertThrows(ElementNotPresentException.class, simpleButtonText::shouldBePresent);
        assertThrows(ElementNotDisplayedException.class, simpleButtonText::shouldBeDisplayed);
        simpleButton.click();
        assertThrows(ElementInFocusException.class, simpleButton::shouldNotBeInFocus);
        assertThrows(ElementIsPresentException.class, simpleButtonText::shouldNotBePresent);
        assertThrows(ElementIsDisplayedException.class, simpleButtonText::shouldNotBeDisplayed);
    }

    @Test
    void webButtonWithHoverTest(Environment env, ValueService value) {
        WebPageContext context = initWebPageContext(env, value);
        context.getPage(HomePage.class)
                .leftMenu()
                .select(value.stringEquals("Elements"));
        ElementsPage elementsPage = context.getPage(ElementsPage.class);
        elementsPage.buttonForHover()
                .shouldBeDisplayed();
        elementsPage.visibleLink()
                .shouldNotBePresent()
                .shouldNotBeDisplayed();
        elementsPage.visibleLinkText()
                .shouldBeDisplayed()
                .shouldHaveText(value.stringEmpty());
        elementsPage.buttonForHover()
                .hoverTo(true);
        elementsPage.visibleLink()
                .shouldBePresent()
                .shouldBeDisplayed()
                .shouldHaveText(value.stringEquals("Visible Link"))
                .hoverTo(false)
                .click();
        elementsPage.visibleLinkText()
                .shouldBeDisplayed()
                .shouldHaveText(value.stringEquals("Visible Link clicked"));
    }

    @Test
    void webButtonForSpinnerTest(Environment env, ValueService value) {
        WebPageContext context = initWebPageContext(env, value);
        context.getPage(HomePage.class)
                .leftMenu()
                .select(value.stringEquals("Elements"));
        ElementsPage elementsPage = context.getPage(ElementsPage.class);
        elementsPage.buttonWithSpinner()
                .shouldBeDisplayed();
        elementsPage.spinner()
                .shouldBePresent()
                .shouldNotBeDisplayed();
        elementsPage.buttonWithSpinnerText()
                .shouldBePresent()
                .shouldNotBeDisplayed();
        elementsPage.buttonWithSpinner()
                .click();
        elementsPage.spinner()
                .shouldBeDisplayed();
        elementsPage.buttonWithSpinnerText()
                .shouldBeDisplayed()
                .shouldHaveText(value.stringEquals("Spinner Button clicked"));
    }




}

