package io.perfeccionista.framework.pagefactory.elements;

import io.perfeccionista.framework.Environment;
import io.perfeccionista.framework.exceptions.WebElementColor.WebElementColorAssertionError;
import io.perfeccionista.framework.exceptions.WebElementDimensions.WebElementDimensionsAssertionError;
import io.perfeccionista.framework.exceptions.WebElementInFocus.WebElementInFocusAssertionError;
import io.perfeccionista.framework.exceptions.WebElementIsDisplayed.WebElementIsDisplayedAssertionError;
import io.perfeccionista.framework.exceptions.WebElementIsPresent.WebElementIsPresentAssertionError;
import io.perfeccionista.framework.exceptions.WebElementLocation.WebElementLocationAssertionError;
import io.perfeccionista.framework.exceptions.WebElementNotDisplayed.WebElementNotDisplayedAssertionError;
import io.perfeccionista.framework.exceptions.WebElementNotInFocus.WebElementNotInFocusAssertionError;
import io.perfeccionista.framework.exceptions.WebElementNotPresent.WebElementNotPresentAssertionError;
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
import io.perfeccionista.framework.pagefactory.pageobjects.ElementsPage;
import io.perfeccionista.framework.pagefactory.pageobjects.HomePage;
import io.perfeccionista.framework.screenshots.Screenshot;
import io.perfeccionista.framework.color.WebColor;
import io.perfeccionista.framework.value.ValueService;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.time.Duration;

import static io.perfeccionista.framework.matcher.WebElementAssertions.beDisplayed;
import static io.perfeccionista.framework.matcher.WebElementAssertions.beInFocus;
import static io.perfeccionista.framework.matcher.WebElementAssertions.bePresent;
import static io.perfeccionista.framework.matcher.WebElementAssertions.haveColor;
import static io.perfeccionista.framework.matcher.WebElementAssertions.haveDimensions;
import static io.perfeccionista.framework.matcher.WebElementAssertions.haveLocation;
import static io.perfeccionista.framework.matcher.WebElementAssertions.haveText;
import static io.perfeccionista.framework.matcher.WebElementAssertions.notBeDisplayed;
import static io.perfeccionista.framework.matcher.WebElementAssertions.notBeInFocus;
import static io.perfeccionista.framework.matcher.WebElementAssertions.notBePresent;
import static io.perfeccionista.framework.matcher.WebElementAssertions.notHaveColor;
import static io.perfeccionista.framework.matcher.WebElementAssertions.notHaveDimensions;
import static io.perfeccionista.framework.matcher.WebElementAssertions.notHaveLocation;
import static io.perfeccionista.framework.matcher.WebElementAssertions.notHaveText;
import static io.perfeccionista.framework.pagefactory.elements.actions.WebElementActionNames.IS_ON_THE_SCREEN_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.components.WebComponents.ROOT;
import static io.perfeccionista.framework.pagefactory.elements.actions.WebElementActionNames.CLICK_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.actions.WebElementActionNames.GET_COLOR_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.actions.WebElementActionNames.GET_DIMENSIONS_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.actions.WebElementActionNames.GET_LOCATION_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.actions.WebElementActionNames.GET_PROPERTY_VALUE_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.actions.WebElementActionNames.GET_SCREENSHOT_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.actions.WebElementActionNames.GET_TEXT_METHOD;
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

@Tag("WebElement") @Tag("WebButton")
class WebButtonElementTest extends AbstractUiTest {

    @Test
    void webButtonInitializationTest(Environment env) {
        WebPageFactory pageFactory = new WebPageFactory(new DefaultSeleniumWebPageFactoryPreferences());
        ElementsPage elementsPage = (ElementsPage) pageFactory.createWebPage(ElementsPage.class);
        elementsPage.setEnvironment(env);
        elementsPage.setWebBrowserDispatcher(mock(WebBrowserDispatcher.class));
        WebButton simpleButton = elementsPage.simpleButton();
        assertNotNull(simpleButton.getEnvironment());
        assertNotNull(simpleButton.getLocatorChain());
        assertNotNull(simpleButton.getWebBrowserDispatcher());
        assertNotNull(simpleButton.getRequiredLocator(ROOT));
        // WebButton
        assertNotNull(simpleButton.getActionImplementation(CLICK_METHOD, Void.class));
        assertNotNull(simpleButton.getActionImplementation(GET_TEXT_METHOD, String.class));
        // WebChildElement
        assertNotNull(simpleButton.getActionImplementation(GET_COLOR_METHOD, WebColor.class));
        assertNotNull(simpleButton.getActionImplementation(GET_DIMENSIONS_METHOD, Dimensions.class));
        assertNotNull(simpleButton.getActionImplementation(GET_LOCATION_METHOD, Location.class));
        assertNotNull(simpleButton.getActionImplementation(GET_PROPERTY_VALUE_METHOD, String.class));
        assertNotNull(simpleButton.getActionImplementation(GET_SCREENSHOT_METHOD, Screenshot.class));
        assertNotNull(simpleButton.getActionImplementation(HOVER_TO_METHOD, Void.class));
        assertNotNull(simpleButton.getActionImplementation(IS_COMPONENT_DISPLAYED_METHOD, Boolean.class));
        assertNotNull(simpleButton.getActionImplementation(IS_COMPONENT_PRESENT_METHOD, Boolean.class));
        assertNotNull(simpleButton.getActionImplementation(IS_DISPLAYED_METHOD, Boolean.class));
        assertNotNull(simpleButton.getActionImplementation(IS_IN_FOCUS_METHOD, Boolean.class));
        assertNotNull(simpleButton.getActionImplementation(IS_ON_THE_SCREEN_METHOD, Boolean.class));
        assertNotNull(simpleButton.getActionImplementation(IS_PRESENT_METHOD, Boolean.class));
        assertNotNull(simpleButton.getActionImplementation(SCROLL_TO_METHOD, Void.class));
        // Identifier
        WebElementIdentifier elementIdentifier = simpleButton.getElementIdentifier();
        assertEquals("simpleButton", elementIdentifier.getElementMethod().getName());
        assertEquals("simpleButton", elementIdentifier.getLastUsedName());
        assertTrue(elementIdentifier.containsName("Simple button"));
        assertFalse(elementIdentifier.isNameDeprecated("Simple button"));
        assertEquals(1, elementIdentifier.names().size());
        WebButton simpleButtonElement = elementsPage.getElementRegistry()
                .getRequiredElementByPath("Simple button", WebButton.class);
        assertNotNull(simpleButtonElement);
        assertEquals("Simple button", elementIdentifier.getLastUsedName());
        assertNotNull(simpleButton.toString());
        // Смотрим описание элемента
        System.out.println(simpleButton.toString());
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
        context.getPage(HomePage.class).leftMenu()
                .select(value.stringEquals("Elements"));
        ElementsPage elementsPage = context.getPage(ElementsPage.class);
        WebButton simpleButton = elementsPage.simpleButton();
        // Check Simple button
        simpleButton
                .should(bePresent())
                .should(beDisplayed())
                .should(notBeInFocus())
                .scrollTo()
                .should(haveDimensions(Dimensions.of(127.6d, 38.0d).setInaccuracy(0.2d)))
                .should(haveLocation(Location.relative(369.3d, 314.3d).setInaccuracy(0.2d)))
                .should(haveLocation(Location.center(472.3d, 333.4d).setInaccuracy(0.2d)))
                .should(haveColor("background-color", WebColor.of(0, 123, 255, 1.0d)))
                .hoverTo(true)
                .should(haveColor( "background-color", WebColor.of(0, 105, 217, 1.0d)))
                .should(haveText("Simple Button"))
                .should(haveText(value.stringContains("Button")))
                .should(notHaveText(value.stringContains("link")));
        assertTrue(simpleButton.isPresent());
        assertTrue(simpleButton.isDisplayed());
        assertFalse(simpleButton.isInFocus());
        assertEquals(Dimensions.of(127.6d, 38.0d).setInaccuracy(0.2d), simpleButton.getDimensions(ROOT));
        assertEquals(Location.absolute(369.3d, 314.3d).setInaccuracy(0.2d), simpleButton.getLocation(ROOT));
        assertEquals(WebColor.of(0, 105, 217, 1.0d), simpleButton.getColor(ROOT, "background-color"));
        Screenshot screenshot = simpleButton.getScreenshot(ROOT);
        assertNotNull(screenshot);
        assertTrue(value.intGreaterThan(4500).check(screenshot.getRaw().length));
        // Check Simple button text
        WebTextBlock simpleButtonText = elementsPage.simpleButtonText();
        simpleButtonText
                .should(notBePresent())
                .should(notBeDisplayed());
        assertFalse(simpleButtonText.isPresent());
        assertFalse(simpleButtonText.isDisplayed());
        assertEquals(Point.of(63.8d, 19d).setInaccuracy(0.2d), simpleButton.getDimensions(ROOT).getCenter());
        // Simple button click
        simpleButton
                .click()
                .should(beInFocus());
        // Check Simple button text
        simpleButtonText
                .should(bePresent())
                .should(beDisplayed())
                .should(notBeInFocus());
    }

    @Test
    void webButtonNegativeTest(Environment env, ValueService value) {
        WebPageContext context = initWebPageContext(env, value);
        context.getPage(HomePage.class).leftMenu()
                .select("Elements");
        ElementsPage elementsPage = context.getPage(ElementsPage.class);
        WebButton simpleButton = elementsPage.simpleButton();
        // Выполнение этого метода показывает завершение загрузки страницы
        simpleButton.should(beDisplayed());
        // Для негативных сценариев меняем время ожидания, чтобы не ждать по 5 секунд проброса ошибки вне враппера
        env.getEnvironmentConfiguration().getTimeouts()
                .setTimeout(CheckTimeout.class, Duration.ofMillis(100L));
        assertThrows(WebElementIsPresentAssertionError.class,
                () -> simpleButton.should(notBePresent()));
        assertThrows(WebElementIsDisplayedAssertionError.class,
                () -> simpleButton.should(notBeDisplayed()));
        assertThrows(WebElementNotInFocusAssertionError.class,
                () -> simpleButton.should(beInFocus()));
        Dimensions elementDimensions = Dimensions.of(127.6d, 38.0d).setInaccuracy(0.2d);
        assertThrows(WebElementDimensionsAssertionError.class,
                () -> simpleButton.should(notHaveDimensions(elementDimensions)));
        Location elementLocation = Location.relative(369.3d, 314.3d).setInaccuracy(0.2d);
        assertThrows(WebElementLocationAssertionError.class,
                () -> simpleButton.should(haveLocation(elementLocation.offset(12d, 10d))));
        assertThrows(WebElementLocationAssertionError.class,
                () -> simpleButton.should(notHaveLocation(elementLocation)));
        WebColor elementColor = WebColor.of(0, 123, 255, 1.0d);
        assertThrows(WebElementColorAssertionError.class,
                () -> simpleButton.should(notHaveColor("background-color", elementColor)));
        assertThrows(WebElementTextValueAssertionError.class,
                () -> simpleButton.should(notHaveText("Simple Button")));
        assertThrows(WebElementTextValueAssertionError.class,
                () -> simpleButton.should(haveText("Simple Link")));
        WebTextBlock simpleButtonText = elementsPage.simpleButtonText();
        assertThrows(WebElementNotPresentAssertionError.class,
                () -> simpleButtonText.should(bePresent()));
        assertThrows(WebElementNotDisplayedAssertionError.class,
                () -> simpleButtonText.should(beDisplayed()));
        simpleButton.click();
        assertThrows(WebElementInFocusAssertionError.class,
                () -> simpleButton.should(notBeInFocus()));
        assertThrows(WebElementIsPresentAssertionError.class,
                () -> simpleButtonText.should(notBePresent()));
        assertThrows(WebElementIsDisplayedAssertionError.class,
                () -> simpleButtonText.should(notBeDisplayed()));
    }

    @Test
    void webButtonWithHoverTest(Environment env, ValueService value) {
        WebPageContext context = initWebPageContext(env, value);
        context.getPage(HomePage.class).leftMenu()
                .select("Elements");
        ElementsPage elementsPage = context.getPage(ElementsPage.class);
        elementsPage.buttonForHover()
                .should(beDisplayed());
        elementsPage.visibleLink()
                .should(notBePresent())
                .should(notBeDisplayed());
        elementsPage.visibleLinkText()
                .should(beDisplayed())
                .should(haveText(value.stringEmpty()));
        elementsPage.buttonForHover()
                .hoverTo(true);
        elementsPage.visibleLink()
                .should(bePresent())
                .should(beDisplayed())
                .should(haveText("Visible Link"))
                .hoverTo(false)
                .click();
        elementsPage.visibleLinkText()
                .should(beDisplayed())
                .should(haveText("Visible Link clicked"));
    }

    @Test
    void webButtonForSpinnerTest(Environment env, ValueService value) {
        WebPageContext context = initWebPageContext(env, value);
        context.getPage(HomePage.class).leftMenu()
                .select("Elements");
        ElementsPage elementsPage = context.getPage(ElementsPage.class);
        elementsPage.buttonWithSpinner()
                .should(beDisplayed());
        elementsPage.spinner()
                .should(bePresent())
                .should(notBeDisplayed());
        elementsPage.buttonWithSpinnerText()
                .should(bePresent())
                .should(notBeDisplayed());
        elementsPage.buttonWithSpinner()
                .click();
        elementsPage.spinner()
                .should(beDisplayed());
        elementsPage.buttonWithSpinnerText()
                .should(beDisplayed())
                .should(haveText("Spinner Button clicked"));
    }

}

