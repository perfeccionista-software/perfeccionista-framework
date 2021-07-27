package io.perfeccionista.framework.pagefactory.elements;

import io.perfeccionista.framework.Environment;
import io.perfeccionista.framework.color.Color;
import io.perfeccionista.framework.exceptions.SingleResultConversion.SingleResultConversionException;
import io.perfeccionista.framework.exceptions.WebElementColor.WebElementColorAssertionError;
import io.perfeccionista.framework.exceptions.WebElementDimensions.WebElementDimensionsAssertionError;
import io.perfeccionista.framework.exceptions.ElementIsDisplayed.ElementIsDisplayedAssertionError;
import io.perfeccionista.framework.exceptions.ElementIsPresent.ElementIsPresentAssertionError;
import io.perfeccionista.framework.exceptions.WebElementLocation.WebElementLocationAssertionError;
import io.perfeccionista.framework.exceptions.WebElementNotInFocus.WebElementNotInFocusAssertionError;
import io.perfeccionista.framework.exceptions.WebElementSize.WebElementSizeAssertionError;
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
import static io.perfeccionista.framework.pagefactory.elements.ElementActionNames.GET_COLOR_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.ElementActionNames.GET_SCREENSHOT_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.ElementActionNames.HOVER_TO_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.ElementActionNames.IS_COMPONENT_DISPLAYED_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.ElementActionNames.IS_COMPONENT_PRESENT_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.ElementActionNames.IS_DISPLAYED_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.ElementActionNames.IS_IN_FOCUS_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.ElementActionNames.IS_PRESENT_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.ElementActionNames.SCROLL_TO_METHOD;
import static io.perfeccionista.framework.value.Values.intEquals;
import static io.perfeccionista.framework.value.Values.intGreaterThan;
import static io.perfeccionista.framework.value.Values.stringEquals;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;

@Tag("WebElement") @Tag("WebRadioGroup")
class WebRadioGroupElementTest extends AbstractWebSeleniumParallelTest {

    @Test
    void webRadioGroupInitializationTest(Environment environment) {
        WebPageFactory pageFactory = new WebPageFactory(new DefaultSeleniumWebPageFactoryPreferences());
        ElementsPage elementsPage = (ElementsPage) pageFactory.createWebPage(ElementsPage.class);
        elementsPage.setEnvironment(environment);
        elementsPage.setWebBrowserDispatcher(mock(WebBrowserDispatcher.class));
        WebRadioGroup radioGroup = elementsPage.radioGroup();
        WebElementIdentifier elementIdentifier = radioGroup.getElementIdentifier();
        assertAll(
                () -> assertNotNull(radioGroup.getEnvironment()),
                () -> assertNotNull(radioGroup.getLocatorChain()),
                () -> assertNotNull(radioGroup.getWebBrowserDispatcher()),
                () -> assertNotNull(radioGroup.getOptionalLocator(ROOT)),
                // WebChildElement
                () -> assertNotNull(radioGroup.getEndpointHandler(GET_COLOR_METHOD, Color.class)),
                () -> assertNotNull(radioGroup.getEndpointHandler(GET_ELEMENT_BOUNDS_METHOD, ElementBounds.class)),
                () -> assertNotNull(radioGroup.getEndpointHandler(GET_SCREENSHOT_METHOD, Screenshot.class)),
                () -> assertNotNull(radioGroup.getEndpointHandler(HOVER_TO_METHOD, Void.class)),
                () -> assertNotNull(radioGroup.getEndpointHandler(IS_COMPONENT_DISPLAYED_METHOD, Boolean.class)),
                () -> assertNotNull(radioGroup.getEndpointHandler(IS_COMPONENT_PRESENT_METHOD, Boolean.class)),
                () -> assertNotNull(radioGroup.getEndpointHandler(IS_DISPLAYED_METHOD, Boolean.class)),
                () -> assertNotNull(radioGroup.getEndpointHandler(IS_IN_FOCUS_METHOD, Boolean.class)),
                () -> assertNotNull(radioGroup.getEndpointHandler(IS_ON_THE_SCREEN_METHOD, Boolean.class)),
                () -> assertNotNull(radioGroup.getEndpointHandler(IS_PRESENT_METHOD, Boolean.class)),
                () -> assertNotNull(radioGroup.getEndpointHandler(SCROLL_TO_METHOD, Void.class)),
                // Identifier
                () -> assertEquals("radioGroup", elementIdentifier.getElementMethod().getName()),
                () -> assertEquals("Radio group", elementIdentifier.getLastUsedName()),
                () -> assertTrue(elementIdentifier.containsName("Radio group")),
                () -> assertFalse(elementIdentifier.isNameDeprecated("Radio group")),
                () -> assertEquals(2, elementIdentifier.names().size()),
                () -> {
                    WebRadioGroup radioGroupElement = elementsPage.getElementRegistry()
                            .getRequiredElementByPath("Radio group", WebRadioGroup.class);
                    assertAll(
                            () -> assertNotNull(radioGroupElement),
                            () -> assertEquals("Radio group", elementIdentifier.getLastUsedName()),
                            () -> assertNotNull(radioGroup.toString())
                    );
                }
        );
    }

    @Test
    void webRadioGroupPositiveTest() {
        WebPageContext context = initWebPageContext();
        context.getPage(HomePage.class).leftMenu()
                .select(stringEquals("Elements"));

        ElementsPage elementsPage = context.getPage(ElementsPage.class);
        WebRadioGroup radioGroup = elementsPage.radioGroup();
        radioGroup.should(bePresent())
                .should(beDisplayed())
                .should(notBeInFocus())
                .scrollTo()
                .hoverTo(true)
                .should(haveDimensions(Dimensions2D.of(825.0d, 24.0d).setInaccuracy(0.2d)))
                .should(haveScreenLocation(Point2D.of(330d, 713.4d).setInaccuracy(0.2d)))
                .should(haveColor("border-color", Color.of(33, 37, 41, 1.0d)))
                .should(haveSize(3))
                .should(haveSize(intEquals(3)))
                .should(notHaveSize(2))
                .should(notHaveSize(intGreaterThan(3)));
        assertAll(
                () -> assertTrue(radioGroup.isPresent()),
                () -> assertTrue(radioGroup.isDisplayed()),
                () -> assertFalse(radioGroup.isInFocus()),
                () -> assertEquals(Dimensions2D.of(825.0d, 24.0d).setInaccuracy(0.2d), radioGroup.getElementBounds().getDimensions()),
                () -> assertEquals(Point2D.of(330d, 713.4d).setInaccuracy(0.2d), radioGroup.getElementBounds().getScreenLocation()),
                () -> assertEquals(Color.of(33, 37, 41, 1.0d), radioGroup.getColor("border-color")),
                () -> assertEquals(3, radioGroup.filterBuilder(emptyWebRadioButtonFilter()).extractAll(element()).getSize()),
                () -> assertEquals(Point2D.of(757.5d, 1328.4d).setInaccuracy(0.2d), radioGroup.getElementBounds().getCenter()),
                () -> assertEquals(3, radioGroup.size())
        );
    }

    @Test
    void webRadioGroupNegativeTest(Environment environment) {
        WebPageContext context = initWebPageContext();
        context.getPage(HomePage.class).leftMenu()
                .select(stringEquals("Elements"));

        ElementsPage elementsPage = context.getPage(ElementsPage.class);
        WebRadioGroup radioGroup = elementsPage.radioGroup();
        // Выполнение этого метода показывает завершение загрузки страницы
        radioGroup
                .should(beDisplayed());
        // Для негативных сценариев меняем время ожидания, чтобы не ждать по 5 секунд проброса ошибки вне враппера
        environment.getService(TimeoutsService.class)
                .setTimeout(CheckTimeout.class, Duration.ofMillis(100L));
        assertAll(
                () -> assertThrows(ElementIsPresentAssertionError.class,
                        () -> radioGroup.should(notBePresent())),
                () -> assertThrows(ElementIsDisplayedAssertionError.class,
                        () -> radioGroup.should(notBeDisplayed())),
                () -> assertThrows(WebElementNotInFocusAssertionError.class,
                        () -> radioGroup.should(beInFocus())),
                () -> {
                    Dimensions2D elementDimensions = Dimensions2D.of(825.0d, 24.0d).setInaccuracy(0.2d);
                    assertThrows(WebElementDimensionsAssertionError.class,
                            () -> radioGroup.should(notHaveDimensions(elementDimensions)));
                },
                () -> {
                    Point2D elementLocation = Point2D.of(330d, 713.4d).setInaccuracy(0.2d);
                    assertAll(
                            () -> assertThrows(WebElementLocationAssertionError.class,
                                    () -> radioGroup.should(haveScreenLocation(elementLocation.offset(15d, 1d)))),
                            () -> assertThrows(WebElementLocationAssertionError.class,
                                    () -> radioGroup.should(notHaveScreenLocation(elementLocation)))
                    );
                },
                () -> {
                    Color elementColor = Color.of(33, 37, 41, 1.0d);
                    assertThrows(WebElementColorAssertionError.class,
                            () -> radioGroup.should(notHaveColor("border-color", elementColor)));
                },
                () -> assertThrows(SingleResultConversionException.class,
                        () -> radioGroup.should(notHavePropertyValue("unknown property", stringEquals("Some value")))),
                () -> assertThrows(WebElementSizeAssertionError.class,
                        () -> radioGroup.should(haveSize(intEquals(4))))
        );
    }

}
