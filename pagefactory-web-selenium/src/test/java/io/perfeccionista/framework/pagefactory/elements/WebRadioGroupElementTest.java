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
import io.perfeccionista.framework.exceptions.ElementPropertyNotDeclaredException;
import io.perfeccionista.framework.exceptions.ElementSizeException;
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
import io.perfeccionista.framework.value.number.NumberValue;
import io.perfeccionista.framework.value.string.StringValue;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;

import java.time.Duration;

import static io.perfeccionista.framework.exceptions.base.ExceptionType.ASSERT;
import static io.perfeccionista.framework.exceptions.messages.PageFactoryMessages.ELEMENT_NOT_DECLARED;
import static io.perfeccionista.framework.exceptions.messages.PageFactoryMessages.ELEMENT_NOT_DISPLAYED;
import static io.perfeccionista.framework.pagefactory.elements.components.WebComponents.ROOT;
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
import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.SCROLL_TO_ELEMENT_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.SCROLL_TO_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.SHOULD_BE_DISPLAYED_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.SHOULD_BE_IN_FOCUS_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.SHOULD_BE_PRESENT_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.SHOULD_HAVE_COLOR_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.SHOULD_HAVE_DIMENSIONS_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.SHOULD_HAVE_LOCATION_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.SHOULD_HAVE_PROPERTY_NUMBER_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.SHOULD_HAVE_PROPERTY_VALUE_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.SHOULD_HAVE_SIZE_METHOD;
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
import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.SIZE_METHOD;
import static io.perfeccionista.framework.pagefactory.filter.WebConditions.containsLabel;
import static io.perfeccionista.framework.pagefactory.filter.WebConditions.disabled;
import static io.perfeccionista.framework.pagefactory.filter.WebConditions.enabled;
import static io.perfeccionista.framework.pagefactory.filter.WebConditions.radioButtonIndex;
import static io.perfeccionista.framework.pagefactory.filter.WebConditions.selected;
import static io.perfeccionista.framework.pagefactory.filter.WebFilters.emptyRadioButtonFilter;
import static io.perfeccionista.framework.pagefactory.filter.WebFilters.with;
import static io.perfeccionista.framework.pagefactory.filter.WebFilters.without;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;

@Tags(@Tag("Element"))
class WebRadioGroupElementTest extends AbstractUiTest {

    @Test
    void webRadioGroupInitializationTest(Environment env) {
        WebPageFactory pageFactory = new WebPageFactory(new DefaultSeleniumWebElementsConfiguration());
        ElementsPage elementsPage = (ElementsPage) pageFactory.createWebPage(ElementsPage.class);
        elementsPage.setEnvironment(env);
        elementsPage.setWebBrowserDispatcher(mock(WebBrowserDispatcher.class));
        WebRadioGroup radioGroup = elementsPage.radioGroup();
        assertNotNull(radioGroup.getEnvironment());
        assertNotNull(radioGroup.getLocator(ROOT));
        assertNotNull(radioGroup.getLocatorChain());
        assertEquals(elementsPage, radioGroup.getParent());
        WebBrowserDispatcher webBrowserDispatcher = radioGroup.getWebBrowserDispatcher();
        assertNotNull(webBrowserDispatcher);
        // WebButton
        assertNotNull(radioGroup.getActionImplementation(SCROLL_TO_ELEMENT_METHOD, Void.class));
        assertNotNull(radioGroup.getActionImplementation(SIZE_METHOD, Integer.class));
        assertNotNull(radioGroup.getActionImplementation(SHOULD_HAVE_SIZE_METHOD, Void.class));
        // WebChildElement
        assertNotNull(radioGroup.getActionImplementation(COMPONENT_SHOULD_BE_DISPLAYED_METHOD, Void.class));
        assertNotNull(radioGroup.getActionImplementation(COMPONENT_SHOULD_BE_PRESENT_METHOD, Void.class));
        assertNotNull(radioGroup.getActionImplementation(COMPONENT_SHOULD_NOT_BE_DISPLAYED_METHOD, Void.class));
        assertNotNull(radioGroup.getActionImplementation(COMPONENT_SHOULD_NOT_BE_PRESENT_METHOD, Void.class));
        assertNotNull(radioGroup.getActionImplementation(GET_COLOR_METHOD, WebElementColor.class));
        assertNotNull(radioGroup.getActionImplementation(GET_DIMENSIONS_METHOD, Dimensions.class));
        assertNotNull(radioGroup.getActionImplementation(GET_LOCATION_METHOD, Location.class));
        assertNotNull(radioGroup.getActionImplementation(GET_PROPERTY_VALUE_METHOD, String.class));
        assertNotNull(radioGroup.getActionImplementation(GET_SCREENSHOT_METHOD, Screenshot.class));
        assertNotNull(radioGroup.getActionImplementation(HOVER_TO_METHOD, Void.class));
        assertNotNull(radioGroup.getActionImplementation(IS_COMPONENT_DISPLAYED_METHOD, Boolean.class));
        assertNotNull(radioGroup.getActionImplementation(IS_COMPONENT_PRESENT_METHOD, Boolean.class));
        assertNotNull(radioGroup.getActionImplementation(IS_DISPLAYED_METHOD, Boolean.class));
        assertNotNull(radioGroup.getActionImplementation(IS_IN_FOCUS_METHOD, Boolean.class));
        assertNotNull(radioGroup.getActionImplementation(IS_PRESENT_METHOD, Boolean.class));
        assertNotNull(radioGroup.getActionImplementation(SCROLL_TO_METHOD, Void.class));
        assertNotNull(radioGroup.getActionImplementation(SHOULD_BE_DISPLAYED_METHOD, Void.class));
        assertNotNull(radioGroup.getActionImplementation(SHOULD_BE_IN_FOCUS_METHOD, Void.class));
        assertNotNull(radioGroup.getActionImplementation(SHOULD_BE_PRESENT_METHOD, Void.class));
        assertNotNull(radioGroup.getActionImplementation(SHOULD_HAVE_COLOR_METHOD, Void.class));
        assertNotNull(radioGroup.getActionImplementation(SHOULD_HAVE_DIMENSIONS_METHOD, Void.class));
        assertNotNull(radioGroup.getActionImplementation(SHOULD_HAVE_LOCATION_METHOD, Void.class));
        assertNotNull(radioGroup.getActionImplementation(SHOULD_HAVE_PROPERTY_NUMBER_METHOD, Void.class));
        assertNotNull(radioGroup.getActionImplementation(SHOULD_HAVE_PROPERTY_VALUE_METHOD, Void.class));
        assertNotNull(radioGroup.getActionImplementation(SHOULD_LOOKS_LIKE_METHOD, Void.class));
        assertNotNull(radioGroup.getActionImplementation(SHOULD_NOT_BE_DISPLAYED_METHOD, Void.class));
        assertNotNull(radioGroup.getActionImplementation(SHOULD_NOT_BE_IN_FOCUS_METHOD, Void.class));
        assertNotNull(radioGroup.getActionImplementation(SHOULD_NOT_BE_PRESENT_METHOD, Void.class));
        assertNotNull(radioGroup.getActionImplementation(SHOULD_NOT_HAVE_COLOR_METHOD, Void.class));
        assertNotNull(radioGroup.getActionImplementation(SHOULD_NOT_HAVE_DIMENSIONS_METHOD, Void.class));
        assertNotNull(radioGroup.getActionImplementation(SHOULD_NOT_HAVE_LOCATION_METHOD, Void.class));
        assertNotNull(radioGroup.getActionImplementation(SHOULD_NOT_HAVE_PROPERTY_NUMBER_METHOD, Void.class));
        assertNotNull(radioGroup.getActionImplementation(SHOULD_NOT_HAVE_PROPERTY_VALUE_METHOD, Void.class));
        assertNotNull(radioGroup.getActionImplementation(SHOULD_NOT_LOOKS_LIKE_METHOD, Void.class));
        // Identifier
        WebElementIdentifier elementIdentifier = radioGroup.getElementIdentifier();
        assertEquals("radioGroup", elementIdentifier.getElementMethod().getName());
        assertEquals("radioGroup", elementIdentifier.getLastUsedName());
        assertTrue(elementIdentifier.containsName("Radio group"));
        assertFalse(elementIdentifier.isNameDeprecated("Radio group"));
        assertEquals(1, elementIdentifier.names().size());
        WebRadioGroup radioGroupElement = elementsPage.getElementRegistry().getElementByPath("Radio group", WebRadioGroup.class)
                .orElseThrow(() -> new ElementNotDeclaredException(ELEMENT_NOT_DECLARED.getMessage("Radio group")));
        assertNotNull(radioGroupElement);
        assertEquals("Radio group", elementIdentifier.getLastUsedName());
        assertNotNull(radioGroup.toString());
        // Смотрим описание элемента
        System.out.println( radioGroup.toString() );
    }

    @Test
    void webRadioGroupPositiveTest(Environment env, ValueService value) {
        WebPageContext context = initWebPageContext(env, value);
        context.getPage(HomePage.class)
                .leftMenu()
                .select(value.stringEquals("Elements"));
        ElementsPage elementsPage = context.getPage(ElementsPage.class);
        WebRadioGroup radioGroup = elementsPage.radioGroup();
        radioGroup.shouldBePresent()
                .shouldBeDisplayed()
                .shouldNotBeInFocus()
                .scrollTo()
                .hoverTo(true)
                .componentShouldHaveDimensions(ROOT, Dimensions.of(825.0d, 24.0d).setInaccuracy(0.2d))
                .componentShouldHaveLocation(ROOT, Location.relative(330d, 713.4d).setInaccuracy(0.2d))
                .componentShouldHaveColor(ROOT, "border-color", WebElementColor.of(33, 37, 41, 1.0d))
                .shouldHaveSize(value.intEquals(3))
                .should(worldMapImage -> {
                    if (worldMapImage.isDisplayed()) {
                        return;
                    }
                    throw new ElementNotDisplayedException(ELEMENT_NOT_DISPLAYED.getMessage(worldMapImage.getElementIdentifier().getLastUsedName()))
                            .setType(ASSERT)
                            .addAttachmentEntry(JsonAttachmentEntry.of("Element", worldMapImage.toJson()));
                });
        assertTrue(radioGroup.isPresent());
        assertTrue(radioGroup.isDisplayed());
        assertFalse(radioGroup.isInFocus());
        assertEquals(Dimensions.of(825.0d, 24.0d).setInaccuracy(0.2d), radioGroup.getDimensions(ROOT));
        assertEquals(Location.absolute(330d, 713.4d).setInaccuracy(0.2d), radioGroup.getLocation(ROOT));
        assertEquals(WebElementColor.of(33, 37, 41, 1.0d), radioGroup.getColor(ROOT, "border-color"));
        assertEquals(3, radioGroup.size());
        assertEquals(Point.of(412.5d, 12d).setInaccuracy(0.2d), radioGroup.getDimensions(ROOT).getCenter());
    }

    @Test
    void webRadioGroupNegativeTest(Environment env, ValueService value) {
        WebPageContext context = initWebPageContext(env, value);
        context.getPage(HomePage.class)
                .leftMenu()
                .select(value.stringEquals("Elements"));
        ElementsPage elementsPage = context.getPage(ElementsPage.class);
        WebRadioGroup radioGroup = elementsPage.radioGroup();
        // Выполнение этого метода показывает завершение загрузки страницы
        radioGroup.shouldBeDisplayed();
        // Для негативных сценариев меняем время ожидания, чтобы не ждать по 5 секунд проброса ошибки вне враппера
        env.getEnvironmentConfiguration().getTimeouts().setTimeout(CheckTimeout.class, Duration.ofMillis(100L));
        assertThrows(ElementIsPresentException.class, radioGroup::shouldNotBePresent);
        assertThrows(ElementIsDisplayedException.class, radioGroup::shouldNotBeDisplayed);
        assertThrows(ElementNotInFocusException.class, radioGroup::shouldBeInFocus);
        Dimensions elementDimensions = Dimensions.of(825.0d, 24.0d).setInaccuracy(0.2d);
        assertThrows(ElementDimensionsException.class, () -> radioGroup.componentShouldNotHaveDimensions(ROOT, elementDimensions));
        Location elementLocation = Location.relative(330d, 713.4d).setInaccuracy(0.2d);
        assertThrows(ElementLocationException.class, () -> radioGroup.componentShouldHaveLocation(ROOT, elementLocation.offset(15d, 1d)));
        assertThrows(ElementLocationException.class, () -> radioGroup.componentShouldNotHaveLocation(ROOT, elementLocation));
        WebElementColor elementColor = WebElementColor.of(33, 37, 41, 1.0d);
        assertThrows(ElementColorException.class, () -> radioGroup.componentShouldNotHaveColor(ROOT, "border-color", elementColor));
        StringValue correctPropertyValue = value.stringEquals("Some value");
        assertThrows(ElementPropertyNotDeclaredException.class, () -> radioGroup.shouldNotHavePropertyValue("unknown property", correctPropertyValue));
        NumberValue<Integer> expectedSize = value.intEquals(4);
        assertThrows(ElementSizeException.class, () -> radioGroup.shouldHaveSize(expectedSize));
    }

    @Test
    void webRadioGroupFilterEmptyConditionTest(Environment env, ValueService value) {
        WebPageContext context = initWebPageContext(env, value);
        context.getPage(HomePage.class)
                .leftMenu()
                .select(value.stringEquals("Elements"));
        ElementsPage elementsPage = context.getPage(ElementsPage.class);
        WebRadioGroup radioGroup = elementsPage.radioGroup()
                .shouldBeDisplayed();

        radioGroup.filter(emptyRadioButtonFilter())
                .shouldHaveSize(value.intEquals(3));
    }

    @Test
    void webRadioGroupFilterRadioButtonIndexConditionTest(Environment env, ValueService value) {
        WebPageContext context = initWebPageContext(env, value);
        context.getPage(HomePage.class)
                .leftMenu()
                .select(value.stringEquals("Elements"));
        ElementsPage elementsPage = context.getPage(ElementsPage.class);
        WebRadioGroup radioGroup = elementsPage.radioGroup()
                .shouldBeDisplayed();

        radioGroup.filter(with(radioButtonIndex(value.intGreaterThanOrEqual(1))))
                .shouldHaveSize(value.intEquals(2));
        radioGroup.filter(without(radioButtonIndex(value.intGreaterThanOrEqual(1))))
                .shouldHaveSize(value.intEquals(1));
    }

    @Test
    void webRadioGroupFilterRadioButtonSelectedConditionTest(Environment env, ValueService value) {
        WebPageContext context = initWebPageContext(env, value);
        context.getPage(HomePage.class)
                .leftMenu()
                .select(value.stringEquals("Elements"));
        ElementsPage elementsPage = context.getPage(ElementsPage.class);
        WebRadioGroup radioGroup = elementsPage.radioGroup()
                .shouldBeDisplayed();

        // By Element
        radioGroup.filter(with(selected()))
                .shouldHaveSize(value.intEquals(1));
        radioGroup.filter(with(selected().inverse()))
                .shouldHaveSize(value.intEquals(2));

        radioGroup.filter(without(selected()))
                .shouldHaveSize(value.intEquals(2));
        radioGroup.filter(without(selected().inverse()))
                .shouldHaveSize(value.intEquals(1));
    }

    @Test
    void webRadioGroupFilterElementLabelConditionTest(Environment env, ValueService value) {
        WebPageContext context = initWebPageContext(env, value);
        context.getPage(HomePage.class)
                .leftMenu()
                .select(value.stringEquals("Elements"));
        ElementsPage elementsPage = context.getPage(ElementsPage.class);
        WebRadioGroup radioGroup = elementsPage.radioGroup()
                .shouldBeDisplayed();

        // By Element
        radioGroup.filter(with(containsLabel(value.stringEquals("Label 3"))))
                .shouldHaveSize(value.intEquals(1));
        radioGroup.filter(with(containsLabel(value.stringStartsWith("Label"))))
                .shouldHaveSize(value.intEquals(3));
        radioGroup.filter(with(containsLabel(value.stringEquals("Label 3")).inverse()))
                .shouldHaveSize(value.intEquals(2));
        radioGroup.filter(with(containsLabel(value.stringStartsWith("Label")).inverse()))
                .shouldHaveSize(value.intEquals(0));

        radioGroup.filter(without(containsLabel(value.stringEquals("Label 3"))))
                .shouldHaveSize(value.intEquals(2));
        radioGroup.filter(without(containsLabel(value.stringStartsWith("Label"))))
                .shouldHaveSize(value.intEquals(0));
        radioGroup.filter(without(containsLabel(value.stringEquals("Label 3")).inverse()))
                .shouldHaveSize(value.intEquals(1));
        radioGroup.filter(without(containsLabel(value.stringStartsWith("Label")).inverse()))
                .shouldHaveSize(value.intEquals(3));
    }

    @Test
    void webRadioGroupFilterElementEnabledConditionTest(Environment env, ValueService value) {
        WebPageContext context = initWebPageContext(env, value);
        context.getPage(HomePage.class)
                .leftMenu()
                .select(value.stringEquals("Elements"));
        ElementsPage elementsPage = context.getPage(ElementsPage.class);
        WebRadioGroup radioGroup = elementsPage.radioGroup()
                .shouldBeDisplayed();

        // By Element
        radioGroup.filter(with(enabled()))
                .shouldHaveSize(value.intEquals(2));
        radioGroup.filter(with(disabled()))
                .shouldHaveSize(value.intEquals(1));

        radioGroup.filter(without(enabled()))
                .shouldHaveSize(value.intEquals(1));
        radioGroup.filter(without(disabled()))
                .shouldHaveSize(value.intEquals(2));
    }

}
