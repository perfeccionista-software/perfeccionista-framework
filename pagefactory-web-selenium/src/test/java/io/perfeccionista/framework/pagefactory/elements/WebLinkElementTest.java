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

@Tag("Element") @Tag("WebLink")
class WebLinkElementTest extends AbstractUiTest {

    @Test
    void webLinkInitializationTest(Environment env) {
        WebPageFactory pageFactory = new WebPageFactory(new DefaultSeleniumWebPageFactoryPreferences());
        ElementsPage elementsPage = (ElementsPage) pageFactory.createWebPage(ElementsPage.class);
        elementsPage.setEnvironment(env);
        elementsPage.setWebBrowserDispatcher(mock(WebBrowserDispatcher.class));
        WebLink simpleLink = elementsPage.simpleLink();
        assertNotNull(simpleLink.getEnvironment());
        assertNotNull(simpleLink.getLocatorChain());
        assertNotNull(simpleLink.getWebBrowserDispatcher());
        assertNotNull(simpleLink.getOptionalLocator(ROOT));
        // WebButton
        assertNotNull(simpleLink.getActionImplementation(CLICK_METHOD, Void.class));
        assertNotNull(simpleLink.getActionImplementation(GET_TEXT_METHOD, String.class));
        // WebChildElement
        assertNotNull(simpleLink.getActionImplementation(GET_COLOR_METHOD, WebColor.class));
        assertNotNull(simpleLink.getActionImplementation(GET_DIMENSIONS_METHOD, Dimensions.class));
        assertNotNull(simpleLink.getActionImplementation(GET_LOCATION_METHOD, Location.class));
        assertNotNull(simpleLink.getActionImplementation(GET_PROPERTY_VALUE_METHOD, String.class));
        assertNotNull(simpleLink.getActionImplementation(GET_SCREENSHOT_METHOD, Screenshot.class));
        assertNotNull(simpleLink.getActionImplementation(HOVER_TO_METHOD, Void.class));
        assertNotNull(simpleLink.getActionImplementation(IS_COMPONENT_DISPLAYED_METHOD, Boolean.class));
        assertNotNull(simpleLink.getActionImplementation(IS_COMPONENT_PRESENT_METHOD, Boolean.class));
        assertNotNull(simpleLink.getActionImplementation(IS_DISPLAYED_METHOD, Boolean.class));
        assertNotNull(simpleLink.getActionImplementation(IS_IN_FOCUS_METHOD, Boolean.class));
        assertNotNull(simpleLink.getActionImplementation(IS_ON_THE_SCREEN_METHOD, Boolean.class));
        assertNotNull(simpleLink.getActionImplementation(IS_PRESENT_METHOD, Boolean.class));
        assertNotNull(simpleLink.getActionImplementation(SCROLL_TO_METHOD, Void.class));
        // Identifier
        WebElementIdentifier elementIdentifier = simpleLink.getElementIdentifier();
        assertEquals("simpleLink", elementIdentifier.getElementMethod().getName());
        assertEquals("simpleLink", elementIdentifier.getLastUsedName());
        assertTrue(elementIdentifier.containsName("Simple link"));
        assertFalse(elementIdentifier.isNameDeprecated("Simple link"));
        assertEquals(1, elementIdentifier.names().size());
        WebLink simpleLinkElement = elementsPage.getElementRegistry()
                .getRequiredElementByPath("Simple link", WebLink.class);
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
        context.getPage(HomePage.class).leftMenu()
                .select("Elements");
        ElementsPage elementsPage = context.getPage(ElementsPage.class);
        WebLink simpleLink = elementsPage.simpleLink();
        // Check Simple button
        simpleLink
                .should(bePresent())
                .should(beDisplayed())
                .should(notBeInFocus())
                .scrollTo()
                .should(haveDimensions(Dimensions.of(81.8d, 22.0d).setInaccuracy(0.2d)))
                .should(haveLocation(Location.relative(392.2d, 478.4d).setInaccuracy(0.2d)))
                .should(haveColor("color", WebColor.of(0, 123, 255, 1.0d)))
                .hoverTo(true)
                .should(haveColor("color", WebColor.of(0, 86, 179, 1.0d)))
                .should(haveText("Simple Link"))
                .should(haveText(value.stringContains("Link")))
                .should(notHaveText(value.stringContains("button")));
        assertTrue(simpleLink.isPresent());
        assertTrue(simpleLink.isDisplayed());
        assertFalse(simpleLink.isInFocus());
        assertEquals(Dimensions.of(81.8d, 22.0d).setInaccuracy(0.2d), simpleLink.getDimensions(ROOT));
        assertEquals(Location.absolute(392.2d, 478.4d).setInaccuracy(0.2d), simpleLink.getLocation(ROOT));
        assertEquals(WebColor.of(0, 86, 179, 1.0d), simpleLink.getColor(ROOT, "color"));
        Screenshot screenshot = simpleLink.getScreenshot(ROOT);
        assertNotNull(screenshot);
        assertTrue(value.intGreaterThan(2500).check(screenshot.getRaw().length));
        // Check Simple button text
        WebTextBlock simpleLinkText = elementsPage.simpleLinkText();
        simpleLinkText
                .should(bePresent())
                .should(notBeDisplayed());
        assertTrue(simpleLinkText.isPresent());
        assertFalse(simpleLinkText.isDisplayed());
        // Simple button click
        simpleLink
                .click()
                .should(beInFocus());
        // Check Simple button text
        simpleLinkText
                .should(bePresent())
                .should(beDisplayed())
                .should(notBeInFocus());
        assertEquals(Point.of(40.9d, 11d).setInaccuracy(0.2d), simpleLink.getDimensions(ROOT).getCenter());
    }

    @Test
    void webLinkNegativeTest(Environment env, ValueService value) {
        WebPageContext context = initWebPageContext(env, value);
        context.getPage(HomePage.class).leftMenu()
                .select("Elements");
        ElementsPage elementsPage = context.getPage(ElementsPage.class);
        WebLink simpleLink = elementsPage.simpleLink();
        // Выполнение этого метода показывает завершение загрузки страницы
        simpleLink
                .should(beDisplayed());
        // Для негативных сценариев меняем время ожидания, чтобы не ждать по 5 секунд проброса ошибки вне враппера
        env.getEnvironmentConfiguration().getTimeouts()
                .setTimeout(CheckTimeout.class, Duration.ofMillis(100L));
        assertThrows(WebElementIsPresentAssertionError.class,
                () -> simpleLink.should(notBePresent()));
        assertThrows(WebElementIsDisplayedAssertionError.class,
                () -> simpleLink.should(notBeDisplayed()));
        assertThrows(WebElementNotInFocusAssertionError.class,
                () -> simpleLink.should(beInFocus()));
        Dimensions elementDimensions = Dimensions.of(81.8d, 22.0d).setInaccuracy(0.2d);
        assertThrows(WebElementDimensionsAssertionError.class,
                () -> simpleLink.should(notHaveDimensions(elementDimensions)));
        Location elementLocation = Location.relative(392.2d, 478.4d).setInaccuracy(0.2d);
        assertThrows(WebElementLocationAssertionError.class,
                () -> simpleLink.should(haveLocation(elementLocation.offset(54d, -4d))));
        assertThrows(WebElementLocationAssertionError.class,
                () -> simpleLink.should(notHaveLocation(elementLocation)));
        WebColor elementColor = WebColor.of(0, 123, 255, 1.0d);
        assertThrows(WebElementColorAssertionError.class,
                () -> simpleLink.should(notHaveColor("color", elementColor)));
        assertThrows(WebElementTextValueAssertionError.class,
                () -> simpleLink.should(notHaveText("Simple Link")));
        assertThrows(WebElementTextValueAssertionError.class,
                () -> simpleLink.should(haveText("Simple Button")));
        WebTextBlock simpleLinkText = elementsPage.simpleLinkText();
        assertThrows(WebElementIsPresentAssertionError.class,
                () -> simpleLinkText.should(notBePresent()));
        assertThrows(WebElementNotDisplayedAssertionError.class,
                () -> simpleLinkText.should(beDisplayed()));
        simpleLink.click();
        assertThrows(WebElementInFocusAssertionError.class,
                () -> simpleLink.should(notBeInFocus()));
        assertThrows(WebElementIsPresentAssertionError.class,
                () -> simpleLinkText.should(notBePresent()));
        assertThrows(WebElementIsDisplayedAssertionError.class,
                () -> simpleLinkText.should(notBeDisplayed()));
    }

}