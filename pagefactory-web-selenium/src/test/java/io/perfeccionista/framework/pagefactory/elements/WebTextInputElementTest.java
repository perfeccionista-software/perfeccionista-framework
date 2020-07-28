package io.perfeccionista.framework.pagefactory.elements;

import io.perfeccionista.framework.Environment;
import io.perfeccionista.framework.attachment.JsonAttachmentEntry;
import io.perfeccionista.framework.exceptions.ElementColorException;
import io.perfeccionista.framework.exceptions.ElementDimensionsException;
import io.perfeccionista.framework.exceptions.ElementInFocusException;
import io.perfeccionista.framework.exceptions.ElementIsDisabledException;
import io.perfeccionista.framework.exceptions.ElementIsDisplayedException;
import io.perfeccionista.framework.exceptions.ElementIsEnabledException;
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
import org.openqa.selenium.Keys;

import java.time.Duration;

import static io.perfeccionista.framework.exceptions.base.ExceptionType.ASSERT;
import static io.perfeccionista.framework.exceptions.messages.PageFactoryMessages.ELEMENT_NOT_DECLARED;
import static io.perfeccionista.framework.exceptions.messages.PageFactoryMessages.ELEMENT_NOT_DISPLAYED;
import static io.perfeccionista.framework.pagefactory.elements.components.WebComponents.ROOT;
import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.CLEAR_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.CLICK_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.COMPONENT_SHOULD_BE_DISPLAYED_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.COMPONENT_SHOULD_BE_PRESENT_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.COMPONENT_SHOULD_NOT_BE_DISPLAYED_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.COMPONENT_SHOULD_NOT_BE_PRESENT_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.GET_COLOR_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.GET_DIMENSIONS_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.GET_LABEL_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.GET_LOCATION_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.GET_PROPERTY_VALUE_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.GET_SCREENSHOT_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.GET_TEXT_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.HOVER_TO_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.IS_COMPONENT_DISPLAYED_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.IS_COMPONENT_PRESENT_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.IS_DISPLAYED_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.IS_ENABLED_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.IS_IN_FOCUS_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.IS_PRESENT_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.SCROLL_TO_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.SEND_KEYS_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.SHOULD_BE_DISABLED_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.SHOULD_BE_DISPLAYED_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.SHOULD_BE_ENABLED_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.SHOULD_BE_IN_FOCUS_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.SHOULD_BE_PRESENT_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.SHOULD_HAVE_COLOR_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.SHOULD_HAVE_DIMENSIONS_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.SHOULD_HAVE_LOCATION_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.SHOULD_HAVE_NUMBER_LABEL_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.SHOULD_HAVE_NUMBER_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.SHOULD_HAVE_PROPERTY_NUMBER_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.SHOULD_HAVE_PROPERTY_VALUE_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.SHOULD_HAVE_TEXT_LABEL_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.SHOULD_HAVE_TEXT_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.SHOULD_LOOKS_LIKE_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.SHOULD_NOT_BE_DISPLAYED_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.SHOULD_NOT_BE_IN_FOCUS_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.SHOULD_NOT_BE_PRESENT_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.SHOULD_NOT_HAVE_COLOR_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.SHOULD_NOT_HAVE_DIMENSIONS_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.SHOULD_NOT_HAVE_LOCATION_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.SHOULD_NOT_HAVE_NUMBER_LABEL_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.SHOULD_NOT_HAVE_NUMBER_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.SHOULD_NOT_HAVE_PROPERTY_NUMBER_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.SHOULD_NOT_HAVE_PROPERTY_VALUE_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.SHOULD_NOT_HAVE_TEXT_LABEL_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.SHOULD_NOT_HAVE_TEXT_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.SHOULD_NOT_LOOKS_LIKE_METHOD;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;

@Tags(@Tag("Element"))
class WebTextInputElementTest extends AbstractUiTest {

    @Test
    void webTextInputInitializationTest(Environment env) {
        WebPageFactory pageFactory = new WebPageFactory(new DefaultSeleniumWebElementsConfiguration());
        ElementsPage elementsPage = (ElementsPage) pageFactory.createWebPage(ElementsPage.class);
        elementsPage.setEnvironment(env);
        elementsPage.setWebBrowserDispatcher(mock(WebBrowserDispatcher.class));
        WebTextInput simpleInput = elementsPage.simpleInput();
        assertNotNull(simpleInput.getEnvironment());
        assertNotNull(simpleInput.getLocator(ROOT));
        assertNotNull(simpleInput.getLocatorChain());
        assertEquals(elementsPage, simpleInput.getParent());
        WebBrowserDispatcher webBrowserDispatcher = simpleInput.getWebBrowserDispatcher();
        assertNotNull(webBrowserDispatcher);
        // WebTextInput
        assertNotNull(simpleInput.getActionImplementation(CLICK_METHOD, Void.class));
        assertNotNull(simpleInput.getActionImplementation(CLEAR_METHOD, Void.class));
        assertNotNull(simpleInput.getActionImplementation(SEND_KEYS_METHOD, Void.class));
        assertNotNull(simpleInput.getActionImplementation(IS_ENABLED_METHOD, Boolean.class));
        assertNotNull(simpleInput.getActionImplementation(SHOULD_BE_ENABLED_METHOD, Void.class));
        assertNotNull(simpleInput.getActionImplementation(SHOULD_BE_DISABLED_METHOD, Void.class));
        assertNotNull(simpleInput.getActionImplementation(GET_TEXT_METHOD, String.class));
        assertNotNull(simpleInput.getActionImplementation(SHOULD_HAVE_TEXT_METHOD, Void.class));
        assertNotNull(simpleInput.getActionImplementation(SHOULD_HAVE_NUMBER_METHOD, Void.class));
        assertNotNull(simpleInput.getActionImplementation(SHOULD_NOT_HAVE_TEXT_METHOD, Void.class));
        assertNotNull(simpleInput.getActionImplementation(SHOULD_NOT_HAVE_NUMBER_METHOD, Void.class));
        assertNotNull(simpleInput.getActionImplementation(GET_LABEL_METHOD, String.class));
        assertNotNull(simpleInput.getActionImplementation(SHOULD_HAVE_TEXT_LABEL_METHOD, Void.class));
        assertNotNull(simpleInput.getActionImplementation(SHOULD_HAVE_NUMBER_LABEL_METHOD, Void.class));
        assertNotNull(simpleInput.getActionImplementation(SHOULD_NOT_HAVE_TEXT_LABEL_METHOD, Void.class));
        assertNotNull(simpleInput.getActionImplementation(SHOULD_NOT_HAVE_NUMBER_LABEL_METHOD, Void.class));
        // WebChildElement
        assertNotNull(simpleInput.getActionImplementation(COMPONENT_SHOULD_BE_DISPLAYED_METHOD, Void.class));
        assertNotNull(simpleInput.getActionImplementation(COMPONENT_SHOULD_BE_PRESENT_METHOD, Void.class));
        assertNotNull(simpleInput.getActionImplementation(COMPONENT_SHOULD_NOT_BE_DISPLAYED_METHOD, Void.class));
        assertNotNull(simpleInput.getActionImplementation(COMPONENT_SHOULD_NOT_BE_PRESENT_METHOD, Void.class));
        assertNotNull(simpleInput.getActionImplementation(GET_COLOR_METHOD, WebElementColor.class));
        assertNotNull(simpleInput.getActionImplementation(GET_DIMENSIONS_METHOD, Dimensions.class));
        assertNotNull(simpleInput.getActionImplementation(GET_LOCATION_METHOD, Location.class));
        assertNotNull(simpleInput.getActionImplementation(GET_PROPERTY_VALUE_METHOD, String.class));
        assertNotNull(simpleInput.getActionImplementation(GET_SCREENSHOT_METHOD, Screenshot.class));
        assertNotNull(simpleInput.getActionImplementation(HOVER_TO_METHOD, Void.class));
        assertNotNull(simpleInput.getActionImplementation(IS_COMPONENT_DISPLAYED_METHOD, Boolean.class));
        assertNotNull(simpleInput.getActionImplementation(IS_COMPONENT_PRESENT_METHOD, Boolean.class));
        assertNotNull(simpleInput.getActionImplementation(IS_DISPLAYED_METHOD, Boolean.class));
        assertNotNull(simpleInput.getActionImplementation(IS_IN_FOCUS_METHOD, Boolean.class));
        assertNotNull(simpleInput.getActionImplementation(IS_PRESENT_METHOD, Boolean.class));
        assertNotNull(simpleInput.getActionImplementation(SCROLL_TO_METHOD, Void.class));
        assertNotNull(simpleInput.getActionImplementation(SHOULD_BE_DISPLAYED_METHOD, Void.class));
        assertNotNull(simpleInput.getActionImplementation(SHOULD_BE_IN_FOCUS_METHOD, Void.class));
        assertNotNull(simpleInput.getActionImplementation(SHOULD_BE_PRESENT_METHOD, Void.class));
        assertNotNull(simpleInput.getActionImplementation(SHOULD_HAVE_COLOR_METHOD, Void.class));
        assertNotNull(simpleInput.getActionImplementation(SHOULD_HAVE_DIMENSIONS_METHOD, Void.class));
        assertNotNull(simpleInput.getActionImplementation(SHOULD_HAVE_LOCATION_METHOD, Void.class));
        assertNotNull(simpleInput.getActionImplementation(SHOULD_HAVE_PROPERTY_NUMBER_METHOD, Void.class));
        assertNotNull(simpleInput.getActionImplementation(SHOULD_HAVE_PROPERTY_VALUE_METHOD, Void.class));
        assertNotNull(simpleInput.getActionImplementation(SHOULD_LOOKS_LIKE_METHOD, Void.class));
        assertNotNull(simpleInput.getActionImplementation(SHOULD_NOT_BE_DISPLAYED_METHOD, Void.class));
        assertNotNull(simpleInput.getActionImplementation(SHOULD_NOT_BE_IN_FOCUS_METHOD, Void.class));
        assertNotNull(simpleInput.getActionImplementation(SHOULD_NOT_BE_PRESENT_METHOD, Void.class));
        assertNotNull(simpleInput.getActionImplementation(SHOULD_NOT_HAVE_COLOR_METHOD, Void.class));
        assertNotNull(simpleInput.getActionImplementation(SHOULD_NOT_HAVE_DIMENSIONS_METHOD, Void.class));
        assertNotNull(simpleInput.getActionImplementation(SHOULD_NOT_HAVE_LOCATION_METHOD, Void.class));
        assertNotNull(simpleInput.getActionImplementation(SHOULD_NOT_HAVE_PROPERTY_NUMBER_METHOD, Void.class));
        assertNotNull(simpleInput.getActionImplementation(SHOULD_NOT_HAVE_PROPERTY_VALUE_METHOD, Void.class));
        assertNotNull(simpleInput.getActionImplementation(SHOULD_NOT_LOOKS_LIKE_METHOD, Void.class));
        // Identifier
        WebElementIdentifier elementIdentifier = simpleInput.getElementIdentifier();
        assertEquals("simpleInput", elementIdentifier.getElementMethod().getName());
        assertEquals("simpleInput", elementIdentifier.getLastUsedName());
        assertTrue(elementIdentifier.containsName("Simple input"));
        assertFalse(elementIdentifier.isNameDeprecated("Simple input"));
        assertEquals(1, elementIdentifier.names().size());
        WebTextInput simpleInputElement = elementsPage.getElementRegistry().getElementByPath("Simple input", WebTextInput.class)
                .orElseThrow(() -> new ElementNotDeclaredException(ELEMENT_NOT_DECLARED.getMessage("Simple input")));
        assertNotNull(simpleInputElement);
        assertEquals("Simple input", elementIdentifier.getLastUsedName());
        assertNotNull(simpleInput.toString());
        // Смотрим описание элемента
        System.out.println( simpleInput.toString() );
    }

    /**
     * Case: Проверяем что присутствует левое меню
     *  Проверяем текст заголовка
     *  Проверяем основной текст
     * @param env - Экземпляр окружения теста
     * @param value - Экземпляр сервиса значений
     */
    @Test
    void webTextInputPositiveTest(Environment env, ValueService value) {
        WebPageContext context = initWebPageContext(env, value);

        // TODO: Вывести количество запросов и их продолжительность
        context.getPage(HomePage.class)
                .leftMenu()
                .select(value.stringEquals("Elements"));

        ElementsPage elementsPage = context.getPage(ElementsPage.class);
        WebButton simpleInputButton = elementsPage.simpleInputButton()
                .shouldBePresent()
                .shouldBeDisplayed()
                .componentShouldBeDisplayed("Red")
                .shouldHaveText(value.stringEquals("Disable Input"));
        WebTextBlock simpleInputText = elementsPage.simpleInputText()
                .shouldBePresent()
                .shouldBeDisplayed()
                .shouldHaveText(value.stringEmpty());
        // Check Simple input
        WebTextInput simpleInput = elementsPage.simpleInput()
                .shouldBePresent()
                .shouldBeDisplayed()
                .shouldNotBeInFocus()
                .shouldBeEnabled()
                .scrollTo()
                .componentShouldHaveDimensions(ROOT, Dimensions.of(382.5d, 38.0d).setInaccuracy(0.2d))
                .componentShouldHaveLocation(ROOT, Location.relative(551.3d, 517.4d).setInaccuracy(0.2d))
                .componentShouldHaveColor(ROOT, "border-color", WebElementColor.of(206, 212, 218, 1.0d))
                .hoverTo(true)
                .click()
                .componentShouldHaveColor(ROOT, "border-color", WebElementColor.of(128, 189, 255, 1.0d))
                .shouldHaveText(value.stringEmpty())
                .shouldNotHaveText(value.stringContains("input"))
                .shouldHavePropertyValue("placeholder", value.stringEquals("Enter text"))
                .shouldNotHavePropertyValue("placeholder", value.stringContains("Name"))
                .sendKeys("Let's test text input")
                .shouldHaveText(value.stringEquals("Let's test text input"));
        simpleInputText
                .shouldBeDisplayed()
                .shouldHaveText(value.stringEquals("Let's test text input"));
        simpleInput
                .clear()
                .shouldHaveText(value.stringEmpty());
        simpleInputText
                .shouldHaveText(value.stringEmpty());
        simpleInputButton
                .click()
                .shouldBeInFocus()
                .componentShouldBeDisplayed("Green")
                .shouldHaveText(value.stringEquals("Enable Input"));
        simpleInput
                .shouldBeDisabled()
                .shouldHaveText(value.stringEmpty())
                .should(simpleButtonElement -> {
                    if (simpleButtonElement.isDisplayed()) {
                        return;
                    }
                    throw new ElementNotDisplayedException(ELEMENT_NOT_DISPLAYED.getMessage(simpleButtonElement.getElementIdentifier().getLastUsedName()))
                            .setType(ASSERT)
                            .addAttachmentEntry(JsonAttachmentEntry.of("Element", simpleButtonElement.toJson()));
                });
        assertTrue(simpleInput.isPresent());
        assertTrue(simpleInput.isDisplayed());
        assertFalse(simpleInput.isInFocus());
        assertFalse(simpleInput.isEnabled());
        assertEquals(Dimensions.of(382.5d, 38.0d).setInaccuracy(0.2d), simpleInput.getDimensions(ROOT));
        assertEquals(Location.absolute(551.3d, 517.4d).setInaccuracy(0.2d), simpleInput.getLocation(ROOT));
        assertEquals(WebElementColor.of(206, 212, 218, 1.0d), simpleInput.getColor(ROOT, "border-color"));
        Screenshot screenshot = simpleInput.getScreenshot(ROOT);
        assertNotNull(screenshot);
        assertTrue(value.intGreaterThan(4500).check(screenshot.getRaw().length));
        assertEquals(Point.of(191.3d, 19d).setInaccuracy(0.2d), simpleInput.getDimensions(ROOT).getCenter());
    }

    @Test
    void webTextInputNegativeTest(Environment env, ValueService value) {
        WebPageContext context = initWebPageContext(env, value);
        context.getPage(HomePage.class)
                .leftMenu()
                .select(value.stringEquals("Elements"));
        ElementsPage elementsPage = context.getPage(ElementsPage.class);
        WebTextInput simpleInput = elementsPage.simpleInput();
        // Выполнение этого метода показывает завершение загрузки страницы
        simpleInput.shouldBeDisplayed();
        // Для негативных сценариев меняем время ожидания, чтобы не ждать по 5 секунд проброса ошибки вне враппера
        env.getEnvironmentConfiguration().getTimeouts().setTimeout(CheckTimeout.class, Duration.ofMillis(100L));
        assertThrows(ElementIsPresentException.class, simpleInput::shouldNotBePresent);
        assertThrows(ElementIsDisplayedException.class, simpleInput::shouldNotBeDisplayed);
        assertThrows(ElementNotInFocusException.class, simpleInput::shouldBeInFocus);
        assertThrows(ElementIsEnabledException.class, simpleInput::shouldBeDisabled);
        Dimensions elementDimensions = Dimensions.of(382.5d, 38.0d).setInaccuracy(0.2d);
        assertThrows(ElementDimensionsException.class, () -> simpleInput.componentShouldNotHaveDimensions(ROOT, elementDimensions));
        Location elementLocation = Location.relative(551.3d, 517.4d).setInaccuracy(0.2d);
        assertThrows(ElementLocationException.class, () -> simpleInput.componentShouldNotHaveLocation(ROOT, elementLocation));
        WebElementColor elementColor = WebElementColor.of(206, 212, 218, 1.0d);
        assertThrows(ElementColorException.class, () -> simpleInput.componentShouldNotHaveColor(ROOT, "border-color", elementColor));
        assertThrows(ElementTextValueException.class, () -> simpleInput.shouldNotHaveText(value.stringEmpty()));
        assertThrows(ElementTextValueException.class, () -> simpleInput.shouldHaveText(value.stringEquals("TextInput text")));
        elementsPage.simpleInputButton()
                .componentShouldBeDisplayed("Red")
                .click()
                .componentShouldBeDisplayed("Green");
        assertThrows(ElementIsDisabledException.class, simpleInput::shouldBeEnabled);
        assertThrows(ElementIsDisabledException.class, () -> simpleInput.sendKeys("Let's test disabled input"));
        elementsPage.simpleInputButton()
                .click();
        simpleInput
                .shouldBeEnabled()
                .click();
        assertThrows(ElementInFocusException.class, simpleInput::shouldNotBeInFocus);
    }

    @Test
    void webTextAreaPositiveTest(Environment env, ValueService value) {
        WebPageContext context = initWebPageContext(env, value);
        context.getPage(HomePage.class)
                .leftMenu()
                .select(value.stringEquals("Elements"));
        ElementsPage elementsPage = context.getPage(ElementsPage.class);
        WebButton areaInputButton = elementsPage.areaInputButton()
                .shouldBePresent()
                .shouldBeDisplayed()
                .componentShouldBeDisplayed("Red")
                .shouldHaveText(value.stringEquals("Disable Area"));
        WebTextBlock areaInputText = elementsPage.areaInputText()
                .shouldBePresent()
                .shouldBeDisplayed()
                .shouldHaveText(value.stringEmpty());
        // Check Area input
        WebTextInput areaInput = elementsPage.areaInput()
                .shouldBePresent()
                .shouldBeDisplayed()
                .shouldNotBeInFocus()
                .shouldBeEnabled()
                .scrollTo()
                .componentShouldHaveDimensions(ROOT, Dimensions.of(382.5d, 86.0d).setInaccuracy(0.2d))
                .componentShouldHaveLocation(ROOT, Location.relative(551.3d, 571.4d).setInaccuracy(0.2d))
                .componentShouldHaveColor(ROOT, "border-color", WebElementColor.of(206, 212, 218, 1.0d))
                .hoverTo(true)
                .click()
                .componentShouldHaveColor(ROOT, "border-color", WebElementColor.of(128, 189, 255, 1.0d))
                .shouldHaveText(value.stringEmpty())
                .shouldNotHaveText(value.stringContains("input"))
                .shouldHavePropertyValue("placeholder", value.stringEquals("Enter text"))
                .shouldNotHavePropertyValue("placeholder", value.stringContains("Name"))
                .sendKeys("Let's test", Keys.ENTER, "multistring", Keys.ENTER, "text input")
                .shouldHaveText(value.stringEquals("Let's test\nmultistring\ntext input"));
        areaInputText
                .shouldBeDisplayed()
                .shouldHaveText(value.stringEquals("Let's test multistring text input"));
        areaInput
                .clear()
                .shouldHaveText(value.stringEmpty());
        areaInputText
                .shouldHaveText(value.stringEmpty());
        areaInputButton
                .click()
                .shouldBeInFocus()
                .componentShouldBeDisplayed("Green")
                .shouldHaveText(value.stringEquals("Enable Area"));
        areaInput
                .shouldBeDisabled()
                .shouldHaveText(value.stringEmpty());
    }

}
