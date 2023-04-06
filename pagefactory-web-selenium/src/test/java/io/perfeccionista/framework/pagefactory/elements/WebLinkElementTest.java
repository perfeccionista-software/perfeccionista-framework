package io.perfeccionista.framework.pagefactory.elements;

import io.perfeccionista.framework.Environment;
import io.perfeccionista.framework.color.Color;
import io.perfeccionista.framework.exceptions.WebElementColor.WebElementColorAssertionError;
import io.perfeccionista.framework.exceptions.WebElementDimensions.WebElementDimensionsAssertionError;
import io.perfeccionista.framework.exceptions.WebElementInFocus.WebElementInFocusAssertionError;
import io.perfeccionista.framework.exceptions.ElementIsDisplayed.ElementIsDisplayedAssertionError;
import io.perfeccionista.framework.exceptions.ElementIsPresent.ElementIsPresentAssertionError;
import io.perfeccionista.framework.exceptions.WebElementLocation.WebElementLocationAssertionError;
import io.perfeccionista.framework.exceptions.ElementNotDisplayed.ElementNotDisplayedAssertionError;
import io.perfeccionista.framework.exceptions.WebElementNotInFocus.WebElementNotInFocusAssertionError;
import io.perfeccionista.framework.exceptions.WebElementTextValue.WebElementTextValueAssertionError;
import io.perfeccionista.framework.invocation.timeouts.TimeoutsService;
import io.perfeccionista.framework.invocation.timeouts.type.RepeatInvocationTimeout;
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
import static io.perfeccionista.framework.pagefactory.elements.ElementActionNames.GET_TEXT_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.ElementActionNames.HOVER_TO_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.ElementActionNames.IS_COMPONENT_DISPLAYED_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.ElementActionNames.IS_COMPONENT_PRESENT_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.ElementActionNames.IS_DISPLAYED_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.ElementActionNames.IS_IN_FOCUS_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.ElementActionNames.IS_PRESENT_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.ElementActionNames.SCROLL_TO_METHOD;
import static io.perfeccionista.framework.value.Values.intGreaterThan;
import static io.perfeccionista.framework.value.Values.stringContains;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;

@Tag("WebElement") @Tag("WebLink")
class WebLinkElementTest extends AbstractWebSeleniumParallelTest {

    @Test
    void webLinkInitializationTest(Environment environment) {
        WebPageFactory pageFactory = new WebPageFactory(new DefaultSeleniumWebPageFactoryPreferences());
        ElementsPage elementsPage = (ElementsPage) pageFactory.createWebPage(ElementsPage.class);
        elementsPage.setEnvironment(environment);
        elementsPage.setWebBrowserDispatcher(mock(WebBrowserDispatcher.class));
        WebLink simpleLink = elementsPage.simpleLink();
        WebElementIdentifier elementIdentifier = simpleLink.getElementIdentifier();
        assertAll(
                () -> assertNotNull(simpleLink.getEnvironment()),
                () -> assertNotNull(simpleLink.getSelectorChain()),
                () -> assertNotNull(simpleLink.getWebBrowserDispatcher()),
                () -> assertNotNull(simpleLink.getOptionalSelector(ROOT)),
                // WebButton
                () -> assertNotNull(simpleLink.getEndpointHandler(CLICK_METHOD, Void.class)),
                () -> assertNotNull(simpleLink.getEndpointHandler(GET_TEXT_METHOD, String.class)),
                // WebChildElement
                () -> assertNotNull(simpleLink.getEndpointHandler(GET_COLOR_METHOD, Color.class)),
                () -> assertNotNull(simpleLink.getEndpointHandler(GET_ELEMENT_BOUNDS_METHOD, ElementBounds.class)),
                () -> assertNotNull(simpleLink.getEndpointHandler(GET_SCREENSHOT_METHOD, Screenshot.class)),
                () -> assertNotNull(simpleLink.getEndpointHandler(HOVER_TO_METHOD, Void.class)),
                () -> assertNotNull(simpleLink.getEndpointHandler(IS_COMPONENT_DISPLAYED_METHOD, Boolean.class)),
                () -> assertNotNull(simpleLink.getEndpointHandler(IS_COMPONENT_PRESENT_METHOD, Boolean.class)),
                () -> assertNotNull(simpleLink.getEndpointHandler(IS_DISPLAYED_METHOD, Boolean.class)),
                () -> assertNotNull(simpleLink.getEndpointHandler(IS_IN_FOCUS_METHOD, Boolean.class)),
                () -> assertNotNull(simpleLink.getEndpointHandler(IS_ON_THE_SCREEN_METHOD, Boolean.class)),
                () -> assertNotNull(simpleLink.getEndpointHandler(IS_PRESENT_METHOD, Boolean.class)),
                () -> assertNotNull(simpleLink.getEndpointHandler(SCROLL_TO_METHOD, Void.class)),
                // Identifier
                () -> assertEquals("simpleLink", elementIdentifier.getElementMethod().getName()),
                () -> assertEquals("Simple link", elementIdentifier.getLastUsedName()),
                () -> assertTrue(elementIdentifier.containsName("Simple link")),
                () -> assertFalse(elementIdentifier.isNameDeprecated("Simple link")),
                () -> assertEquals(2, elementIdentifier.names().size()),
                () -> {
                    WebLink simpleLinkByName = elementsPage.getElementRegistry()
                            .getRequiredElementByPath("Simple link", WebLink.class);
                    assertAll(
                            () -> assertNotNull(simpleLinkByName),
                            () -> assertEquals("Simple link", elementIdentifier.getLastUsedName()),
                            () -> assertNotNull(simpleLinkByName.toString())
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
    void webLinkPositiveTest() {
        WebPageContext context = initWebPageContext();
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
                .should(haveDimensions(Dimensions2D.of(81.8d, 22.0d).setInaccuracy(0.2d)))
                .should(haveScreenLocation(Point2D.of(392.2d, 478.4d).setInaccuracy(0.2d)))
                .should(haveColor("color", Color.of(0, 123, 255, 1.0d)))
                .hoverTo(true)
                .should(haveColor("color", Color.of(0, 86, 179, 1.0d)))
                .should(haveText("Simple Link"))
                .should(haveText(stringContains("Link")))
                .should(notHaveText(stringContains("button")));
        assertAll(
                () -> assertTrue(simpleLink.isPresent()),
                () -> assertTrue(simpleLink.isDisplayed()),
                () -> assertFalse(simpleLink.isInFocus()),
                () -> assertEquals(Dimensions2D.of(81.8d, 22.0d).setInaccuracy(0.2d), simpleLink.getElementBounds().getDimensions()),
                () -> assertEquals(Point2D.of(392.2d, 478.4d).setInaccuracy(0.2d), simpleLink.getElementBounds().getAbsoluteLocation()),
                () -> assertEquals(Color.of(0, 86, 179, 1.0d), simpleLink.getColor("color")),
                () -> {
                    Screenshot screenshot = simpleLink.getScreenshot();
                    assertAll(
                            () -> assertNotNull(screenshot),
                            () -> assertTrue(intGreaterThan(2500).check(screenshot.getRaw().length))
                    );
                }
        );
        // Check Simple button text
        WebText simpleLinkText = elementsPage.simpleLinkText();
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
        assertEquals(Point2D.of(495.2d, 491.4d).setInaccuracy(0.2d), simpleLink.getElementBounds().getCenter());
    }

    @Test
    void webLinkNegativeTest(Environment environment) {
        WebPageContext context = initWebPageContext();
        context.getPage(HomePage.class).leftMenu()
                .select("Elements");

        ElementsPage elementsPage = context.getPage(ElementsPage.class);
        WebLink simpleLink = elementsPage.simpleLink();
        WebText simpleLinkText = elementsPage.simpleLinkText();
        // Выполнение этого метода показывает завершение загрузки страницы
        simpleLink
                .should(beDisplayed());
        // Для негативных сценариев меняем время ожидания, чтобы не ждать по 5 секунд проброса ошибки вне враппера
        environment.getService(TimeoutsService.class)
                .setTimeout(RepeatInvocationTimeout.class, Duration.ofMillis(100L));
        assertAll(
                () -> assertThrows(ElementIsPresentAssertionError.class,
                        () -> simpleLink.should(notBePresent())),
                () -> assertThrows(ElementIsDisplayedAssertionError.class,
                        () -> simpleLink.should(notBeDisplayed())),
                () -> assertThrows(WebElementNotInFocusAssertionError.class,
                        () -> simpleLink.should(beInFocus())),
                () -> {
                    Dimensions2D elementDimensions = Dimensions2D.of(81.8d, 22.0d).setInaccuracy(0.2d);
                    assertThrows(WebElementDimensionsAssertionError.class,
                            () -> simpleLink.should(notHaveDimensions(elementDimensions)));
                },
                () -> {
                    Point2D elementLocation = Point2D.of(392.2d, 478.4d).setInaccuracy(0.2d);
                    assertAll(
                            () -> assertThrows(WebElementLocationAssertionError.class,
                                    () -> simpleLink.should(haveScreenLocation(elementLocation.offset(54d, -4d)))),
                            () -> assertThrows(WebElementLocationAssertionError.class,
                                    () -> simpleLink.should(notHaveScreenLocation(elementLocation)))
                    );
                },
                () -> {
                    Color elementColor = Color.of(0, 123, 255, 1.0d);
                    assertThrows(WebElementColorAssertionError.class,
                            () -> simpleLink.should(notHaveColor("color", elementColor)));
                },
                () -> assertThrows(WebElementTextValueAssertionError.class,
                        () -> simpleLink.should(notHaveText("Simple Link"))),
                () -> assertThrows(WebElementTextValueAssertionError.class,
                        () -> simpleLink.should(haveText("Simple Button"))),
                () -> assertThrows(ElementIsPresentAssertionError.class,
                        () -> simpleLinkText.should(notBePresent())),
                () -> assertThrows(ElementNotDisplayedAssertionError.class,
                        () -> simpleLinkText.should(beDisplayed()))
        );
        simpleLink.click();
        assertAll(
                () -> assertThrows(WebElementInFocusAssertionError.class,
                        () -> simpleLink.should(notBeInFocus())),
                () -> assertThrows(ElementIsPresentAssertionError.class,
                        () -> simpleLinkText.should(notBePresent())),
                () -> assertThrows(ElementIsDisplayedAssertionError.class,
                        () -> simpleLinkText.should(notBeDisplayed()))
        );
    }

}
