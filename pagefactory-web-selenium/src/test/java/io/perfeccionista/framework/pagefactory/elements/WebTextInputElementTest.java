package io.perfeccionista.framework.pagefactory.elements;

import io.perfeccionista.framework.Environment;
import io.perfeccionista.framework.exceptions.WebElementColor.WebElementColorAssertionError;
import io.perfeccionista.framework.exceptions.WebElementDimensions.WebElementDimensionsAssertionError;
import io.perfeccionista.framework.exceptions.WebElementInFocus.WebElementInFocusAssertionError;
import io.perfeccionista.framework.exceptions.WebElementIsDisabled.WebElementIsDisabledAssertionError;
import io.perfeccionista.framework.exceptions.WebElementIsDisabled.WebElementIsDisabledException;
import io.perfeccionista.framework.exceptions.WebElementIsDisplayed.WebElementIsDisplayedAssertionError;
import io.perfeccionista.framework.exceptions.WebElementIsEnabled.WebElementIsEnabledAssertionError;
import io.perfeccionista.framework.exceptions.WebElementIsPresent.WebElementIsPresentAssertionError;
import io.perfeccionista.framework.exceptions.WebElementLocation.WebElementLocationAssertionError;
import io.perfeccionista.framework.exceptions.WebElementNotInFocus.WebElementNotInFocusAssertionError;
import io.perfeccionista.framework.exceptions.WebElementTextValue.WebElementTextValueAssertionError;
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
import io.perfeccionista.framework.pagefactory.keys.KeysEventChain;
import io.perfeccionista.framework.pagefactory.pageobjects.ElementsPage;
import io.perfeccionista.framework.pagefactory.pageobjects.HomePage;
import io.perfeccionista.framework.screenshots.Screenshot;
import io.perfeccionista.framework.color.WebColor;
import io.perfeccionista.framework.value.ValueService;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.time.Duration;

import static io.perfeccionista.framework.matcher.WebElementAssertions.beDisabled;
import static io.perfeccionista.framework.matcher.WebElementAssertions.beDisplayed;
import static io.perfeccionista.framework.matcher.WebElementAssertions.beEnabled;
import static io.perfeccionista.framework.matcher.WebElementAssertions.beInFocus;
import static io.perfeccionista.framework.matcher.WebElementAssertions.bePresent;
import static io.perfeccionista.framework.matcher.WebElementAssertions.componentBeDisplayed;
import static io.perfeccionista.framework.matcher.WebElementAssertions.haveColor;
import static io.perfeccionista.framework.matcher.WebElementAssertions.haveDimensions;
import static io.perfeccionista.framework.matcher.WebElementAssertions.haveLocation;
import static io.perfeccionista.framework.matcher.WebElementAssertions.havePropertyValue;
import static io.perfeccionista.framework.matcher.WebElementAssertions.haveText;
import static io.perfeccionista.framework.matcher.WebElementAssertions.notBeDisplayed;
import static io.perfeccionista.framework.matcher.WebElementAssertions.notBeInFocus;
import static io.perfeccionista.framework.matcher.WebElementAssertions.notBePresent;
import static io.perfeccionista.framework.matcher.WebElementAssertions.notHaveColor;
import static io.perfeccionista.framework.matcher.WebElementAssertions.notHavePropertyValue;
import static io.perfeccionista.framework.matcher.WebElementAssertions.notHaveText;
import static io.perfeccionista.framework.pagefactory.elements.actions.WebElementActionNames.IS_ON_THE_SCREEN_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.components.WebComponents.ROOT;
import static io.perfeccionista.framework.pagefactory.elements.actions.WebElementActionNames.CLEAR_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.actions.WebElementActionNames.CLICK_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.actions.WebElementActionNames.GET_COLOR_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.actions.WebElementActionNames.GET_DIMENSIONS_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.actions.WebElementActionNames.GET_LABEL_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.actions.WebElementActionNames.GET_LOCATION_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.actions.WebElementActionNames.GET_PROPERTY_VALUE_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.actions.WebElementActionNames.GET_SCREENSHOT_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.actions.WebElementActionNames.GET_TEXT_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.actions.WebElementActionNames.HOVER_TO_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.actions.WebElementActionNames.IS_COMPONENT_DISPLAYED_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.actions.WebElementActionNames.IS_COMPONENT_PRESENT_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.actions.WebElementActionNames.IS_DISPLAYED_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.actions.WebElementActionNames.IS_ENABLED_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.actions.WebElementActionNames.IS_IN_FOCUS_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.actions.WebElementActionNames.IS_PRESENT_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.actions.WebElementActionNames.SCROLL_TO_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.actions.WebElementActionNames.SEND_KEYS_METHOD;
import static io.perfeccionista.framework.pagefactory.keys.Keys.ENTER;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;

@Tag("WebElement") @Tag("WebTextInput")
class WebTextInputElementTest extends AbstractUiTest {

    @Test
    void webTextInputInitializationTest(Environment env) {
        WebPageFactory pageFactory = new WebPageFactory(new DefaultSeleniumWebPageFactoryPreferences());
        ElementsPage elementsPage = (ElementsPage) pageFactory.createWebPage(ElementsPage.class);
        elementsPage.setEnvironment(env);
        elementsPage.setWebBrowserDispatcher(mock(WebBrowserDispatcher.class));
        WebTextInput simpleInput = elementsPage.simpleInput();
        assertNotNull(simpleInput.getEnvironment());
        assertNotNull(simpleInput.getLocatorChain());
        assertNotNull(simpleInput.getWebBrowserDispatcher());
        assertNotNull(simpleInput.getOptionalLocator(ROOT));
        // WebTextInput
        assertNotNull(simpleInput.getActionImplementation(CLICK_METHOD, Void.class));
        assertNotNull(simpleInput.getActionImplementation(CLEAR_METHOD, Void.class));
        assertNotNull(simpleInput.getActionImplementation(SEND_KEYS_METHOD, Void.class));
        assertNotNull(simpleInput.getActionImplementation(IS_ENABLED_METHOD, Boolean.class));
        assertNotNull(simpleInput.getActionImplementation(GET_TEXT_METHOD, String.class));
        assertNotNull(simpleInput.getActionImplementation(GET_LABEL_METHOD, String.class));
        // WebChildElement
        assertNotNull(simpleInput.getActionImplementation(GET_COLOR_METHOD, WebColor.class));
        assertNotNull(simpleInput.getActionImplementation(GET_DIMENSIONS_METHOD, Dimensions.class));
        assertNotNull(simpleInput.getActionImplementation(GET_LOCATION_METHOD, Location.class));
        assertNotNull(simpleInput.getActionImplementation(GET_PROPERTY_VALUE_METHOD, String.class));
        assertNotNull(simpleInput.getActionImplementation(GET_SCREENSHOT_METHOD, Screenshot.class));
        assertNotNull(simpleInput.getActionImplementation(HOVER_TO_METHOD, Void.class));
        assertNotNull(simpleInput.getActionImplementation(IS_COMPONENT_DISPLAYED_METHOD, Boolean.class));
        assertNotNull(simpleInput.getActionImplementation(IS_COMPONENT_PRESENT_METHOD, Boolean.class));
        assertNotNull(simpleInput.getActionImplementation(IS_DISPLAYED_METHOD, Boolean.class));
        assertNotNull(simpleInput.getActionImplementation(IS_IN_FOCUS_METHOD, Boolean.class));
        assertNotNull(simpleInput.getActionImplementation(IS_ON_THE_SCREEN_METHOD, Boolean.class));
        assertNotNull(simpleInput.getActionImplementation(IS_PRESENT_METHOD, Boolean.class));
        assertNotNull(simpleInput.getActionImplementation(SCROLL_TO_METHOD, Void.class));
        // Identifier
        WebElementIdentifier elementIdentifier = simpleInput.getElementIdentifier();
        assertEquals("simpleInput", elementIdentifier.getElementMethod().getName());
        assertEquals("simpleInput", elementIdentifier.getLastUsedName());
        assertTrue(elementIdentifier.containsName("Simple input"));
        assertFalse(elementIdentifier.isNameDeprecated("Simple input"));
        assertEquals(1, elementIdentifier.names().size());
        WebTextInput simpleInputElement = elementsPage.getElementRegistry()
                .getRequiredElementByPath("Simple input", WebTextInput.class);
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

        context.getPage(HomePage.class).leftMenu()
                .select(value.stringEquals("Elements"));

        ElementsPage elementsPage = context.getPage(ElementsPage.class);
        WebButton simpleInputButton = elementsPage.simpleInputButton()
                .should(bePresent())
                .should(beDisplayed())
                .should(componentBeDisplayed("Red"))
                .should(haveText("Disable Input"));
        WebTextBlock simpleInputText = elementsPage.simpleInputText()
                .should(bePresent())
                .should(beDisplayed())
                .should(haveText(value.stringEmpty()));
        // Check Simple input
        WebTextInput simpleInput = elementsPage.simpleInput()
                .should(bePresent())
                .should(beDisplayed())
                .should(notBeInFocus())
                .should(beEnabled())
                .scrollTo()
                .should(haveDimensions(Dimensions.of(382.5d, 38.0d).setInaccuracy(0.2d)))
                .should(haveLocation(Location.relative(551.3d, 517.4d).setInaccuracy(0.2d)))
                .should(haveColor("border-color", WebColor.of(206, 212, 218, 1.0d)))
                .hoverTo(true)
                .click()
                .should(haveColor("border-color", WebColor.of(128, 189, 255, 1.0d)))
                .should(haveText(value.stringEmpty()))
                .should(notHaveText(value.stringContains("input")))
                .should(havePropertyValue("placeholder", "Enter text"))
                .should(notHavePropertyValue("placeholder", value.stringContains("Name")))
                .sendKeys("Let's test text input")
                .should(haveText("Let's test text input"));
        simpleInputText
                .should(beDisplayed())
                .should(haveText("Let's test text input"));
        simpleInput
                .clear()
                .should(haveText(value.stringEmpty()));
        simpleInputText
                .should(haveText(value.stringEmpty()));
        simpleInputButton
                .click()
                .should(beInFocus())
                .should(componentBeDisplayed("Green"))
                .should(haveText("Enable Input"));
        simpleInput
                .should(beDisabled())
                .should(haveText(value.stringEmpty()));
        assertTrue(simpleInput.isPresent());
        assertTrue(simpleInput.isDisplayed());
        assertFalse(simpleInput.isInFocus());
        assertFalse(simpleInput.isEnabled());
        assertEquals(Dimensions.of(382.5d, 38.0d).setInaccuracy(0.2d), simpleInput.getDimensions(ROOT));
        assertEquals(Location.absolute(551.3d, 517.4d).setInaccuracy(0.2d), simpleInput.getLocation(ROOT));
        assertEquals(WebColor.of(206, 212, 218, 1.0d), simpleInput.getColor(ROOT, "border-color"));
        Screenshot screenshot = simpleInput.getScreenshot(ROOT);
        assertNotNull(screenshot);
        assertTrue(value.intGreaterThan(4500).check(screenshot.getRaw().length));
        assertEquals(Point.of(191.3d, 19d).setInaccuracy(0.2d), simpleInput.getDimensions(ROOT).getCenter());
    }

    @Test
    void webTextInputNegativeTest(Environment env, ValueService value) {
        WebPageContext context = initWebPageContext(env, value);
        context.getPage(HomePage.class).leftMenu()
                .select(value.stringEquals("Elements"));
        ElementsPage elementsPage = context.getPage(ElementsPage.class);
        WebTextInput simpleInput = elementsPage.simpleInput();
        // Выполнение этого метода показывает завершение загрузки страницы
        simpleInput
                .should(beDisplayed());
        // Для негативных сценариев меняем время ожидания, чтобы не ждать по 5 секунд проброса ошибки вне враппера
        env.getEnvironmentConfiguration().getTimeouts()
                .setTimeout(CheckTimeout.class, Duration.ofMillis(100L));
        assertThrows(WebElementIsPresentAssertionError.class,
                () -> simpleInput.should(notBePresent()));
        assertThrows(WebElementIsDisplayedAssertionError.class,
                () -> simpleInput.should(notBeDisplayed()));
        assertThrows(WebElementNotInFocusAssertionError.class,
                () -> simpleInput.should(beInFocus()));
        assertThrows(WebElementIsEnabledAssertionError.class,
                () -> simpleInput.should(beDisabled()));
        Dimensions elementDimensions = Dimensions.of(382.5d, 48.0d).setInaccuracy(0.2d);
        assertThrows(WebElementDimensionsAssertionError.class,
                () -> simpleInput.should(haveDimensions(elementDimensions)));
        Location elementLocation = Location.relative(551.3d, 517.4d).setInaccuracy(0.2d).offset(10d, 10d);
        assertThrows(WebElementLocationAssertionError.class,
                () -> simpleInput.should(haveLocation(elementLocation)));
        WebColor elementColor = WebColor.of(206, 212, 218, 1.0d);
        assertThrows(WebElementColorAssertionError.class,
                () -> simpleInput.should(notHaveColor("border-color", elementColor)));
        assertThrows(WebElementTextValueAssertionError.class,
                () -> simpleInput.should(notHaveText(value.stringEmpty())));
        assertThrows(WebElementTextValueAssertionError.class,
                () -> simpleInput.should(haveText("TextInput text")));
        elementsPage.simpleInputButton()
                .should(componentBeDisplayed("Red"))
                .click()
                .should(componentBeDisplayed("Green"));
        assertThrows(WebElementIsDisabledAssertionError.class,
                () -> simpleInput.should(beEnabled()));
        assertThrows(WebElementIsDisabledException.class,
                () -> simpleInput.sendKeys("Let's test disabled input"));
        elementsPage.simpleInputButton()
                .click();
        simpleInput
                .should(beEnabled())
                .click();
        assertThrows(WebElementInFocusAssertionError.class,
                () -> simpleInput.should(notBeInFocus()));
    }

    @Test
    void webTextAreaPositiveTest(Environment env, ValueService value) {
        WebPageContext context = initWebPageContext(env, value);
        context.getPage(HomePage.class).leftMenu()
                .select(value.stringEquals("Elements"));
        ElementsPage elementsPage = context.getPage(ElementsPage.class);
        WebButton areaInputButton = elementsPage.areaInputButton()
                .should(bePresent())
                .should(beDisplayed())
                .should(componentBeDisplayed("Red"))
                .should(haveText("Disable Area"));
        WebTextBlock areaInputText = elementsPage.areaInputText()
                .should(bePresent())
                .should(beDisplayed())
                .should(haveText(value.stringEmpty()));
        // Check Area input
        WebTextInput areaInput = elementsPage.areaInput()
                .should(bePresent())
                .should(beDisplayed())
                .should(notBeInFocus())
                .should(beEnabled())
                .scrollTo()
                .should(haveDimensions(Dimensions.of(382.5d, 86.0d).setInaccuracy(0.2d)))
                .should(haveLocation(Location.relative(551.3d, 571.4d).setInaccuracy(0.2d)))
                .should(haveColor("border-color", WebColor.of(206, 212, 218, 1.0d)))
                .hoverTo(true)
                .click()
                .should(haveColor("border-color", WebColor.of(128, 189, 255, 1.0d)))
                .should(haveText(value.stringEmpty()))
                .should(notHaveText(value.stringContains("input")))
                .should(havePropertyValue("placeholder", "Enter text"))
                .should(notHavePropertyValue("placeholder", value.stringContains("Name")))
                .sendKeys(KeysEventChain.builder()
                        .addText("Let's test").setDelay(Duration.ofMillis(500))
                        .keyPress(ENTER)
                        .addText("multistring")
                        .keyPress(ENTER)
                        .addText("text input"))
                .should(haveText("Let's test\nmultistring\ntext input"));
        areaInputText
                .should(beDisplayed())
                .should(haveText("Let's test multistring text input"));
        areaInput
                .clear()
                .should(haveText(value.stringEmpty()));
        areaInputText
                .should(haveText(value.stringEmpty()));
        areaInputButton
                .click()
                .should(beInFocus())
                .should(componentBeDisplayed("Green"))
                .should(haveText("Enable Area"));
        areaInput
                .should(beDisabled())
                .should(haveText(value.stringEmpty()));
    }

}
