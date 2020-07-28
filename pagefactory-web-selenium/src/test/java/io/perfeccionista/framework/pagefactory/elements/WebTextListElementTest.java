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
import io.perfeccionista.framework.pagefactory.pageobjects.HomePage;
import io.perfeccionista.framework.pagefactory.pageobjects.TextListElementsPage;
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
import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.CLICK_TO_ELEMENT_METHOD;
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
import static io.perfeccionista.framework.pagefactory.filter.WebConditions.containsTextBlock;
import static io.perfeccionista.framework.pagefactory.filter.WebConditions.textBlockIndex;
import static io.perfeccionista.framework.pagefactory.filter.WebFilters.emptyTextListFilter;
import static io.perfeccionista.framework.pagefactory.filter.WebFilters.with;
import static io.perfeccionista.framework.pagefactory.filter.WebFilters.without;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;

@Tags(@Tag("Element"))
public class WebTextListElementTest extends AbstractUiTest {

    @Test
    void webTableInitializationTest(Environment env) {
        WebPageFactory pageFactory = new WebPageFactory(new DefaultSeleniumWebElementsConfiguration());
        TextListElementsPage textListPage = (TextListElementsPage) pageFactory.createWebPage(TextListElementsPage.class);
        textListPage.setEnvironment(env);
        textListPage.setWebBrowserDispatcher(mock(WebBrowserDispatcher.class));
        WebTextList textList = textListPage.textList();
        assertNotNull(textList.getEnvironment());
        assertNotNull(textList.getLocator(ROOT));
        assertNotNull(textList.getLocatorChain());
        assertEquals(textListPage, textList.getParent());
        WebBrowserDispatcher webBrowserDispatcher = textList.getWebBrowserDispatcher();
        assertNotNull(webBrowserDispatcher);
        // WebButton
        assertNotNull(textList.getActionImplementation(CLICK_TO_ELEMENT_METHOD, Void.class));
        assertNotNull(textList.getActionImplementation(SCROLL_TO_ELEMENT_METHOD, Void.class));
        assertNotNull(textList.getActionImplementation(SIZE_METHOD, Integer.class));
        assertNotNull(textList.getActionImplementation(SHOULD_HAVE_SIZE_METHOD, Void.class));
        // WebChildElement
        assertNotNull(textList.getActionImplementation(COMPONENT_SHOULD_BE_DISPLAYED_METHOD, Void.class));
        assertNotNull(textList.getActionImplementation(COMPONENT_SHOULD_BE_PRESENT_METHOD, Void.class));
        assertNotNull(textList.getActionImplementation(COMPONENT_SHOULD_NOT_BE_DISPLAYED_METHOD, Void.class));
        assertNotNull(textList.getActionImplementation(COMPONENT_SHOULD_NOT_BE_PRESENT_METHOD, Void.class));
        assertNotNull(textList.getActionImplementation(GET_COLOR_METHOD, WebElementColor.class));
        assertNotNull(textList.getActionImplementation(GET_DIMENSIONS_METHOD, Dimensions.class));
        assertNotNull(textList.getActionImplementation(GET_LOCATION_METHOD, Location.class));
        assertNotNull(textList.getActionImplementation(GET_PROPERTY_VALUE_METHOD, String.class));
        assertNotNull(textList.getActionImplementation(GET_SCREENSHOT_METHOD, Screenshot.class));
        assertNotNull(textList.getActionImplementation(HOVER_TO_METHOD, Void.class));
        assertNotNull(textList.getActionImplementation(IS_COMPONENT_DISPLAYED_METHOD, Boolean.class));
        assertNotNull(textList.getActionImplementation(IS_COMPONENT_PRESENT_METHOD, Boolean.class));
        assertNotNull(textList.getActionImplementation(IS_DISPLAYED_METHOD, Boolean.class));
        assertNotNull(textList.getActionImplementation(IS_IN_FOCUS_METHOD, Boolean.class));
        assertNotNull(textList.getActionImplementation(IS_PRESENT_METHOD, Boolean.class));
        assertNotNull(textList.getActionImplementation(SCROLL_TO_METHOD, Void.class));
        assertNotNull(textList.getActionImplementation(SHOULD_BE_DISPLAYED_METHOD, Void.class));
        assertNotNull(textList.getActionImplementation(SHOULD_BE_IN_FOCUS_METHOD, Void.class));
        assertNotNull(textList.getActionImplementation(SHOULD_BE_PRESENT_METHOD, Void.class));
        assertNotNull(textList.getActionImplementation(SHOULD_HAVE_COLOR_METHOD, Void.class));
        assertNotNull(textList.getActionImplementation(SHOULD_HAVE_DIMENSIONS_METHOD, Void.class));
        assertNotNull(textList.getActionImplementation(SHOULD_HAVE_LOCATION_METHOD, Void.class));
        assertNotNull(textList.getActionImplementation(SHOULD_HAVE_PROPERTY_NUMBER_METHOD, Void.class));
        assertNotNull(textList.getActionImplementation(SHOULD_HAVE_PROPERTY_VALUE_METHOD, Void.class));
        assertNotNull(textList.getActionImplementation(SHOULD_LOOKS_LIKE_METHOD, Void.class));
        assertNotNull(textList.getActionImplementation(SHOULD_NOT_BE_DISPLAYED_METHOD, Void.class));
        assertNotNull(textList.getActionImplementation(SHOULD_NOT_BE_IN_FOCUS_METHOD, Void.class));
        assertNotNull(textList.getActionImplementation(SHOULD_NOT_BE_PRESENT_METHOD, Void.class));
        assertNotNull(textList.getActionImplementation(SHOULD_NOT_HAVE_COLOR_METHOD, Void.class));
        assertNotNull(textList.getActionImplementation(SHOULD_NOT_HAVE_DIMENSIONS_METHOD, Void.class));
        assertNotNull(textList.getActionImplementation(SHOULD_NOT_HAVE_LOCATION_METHOD, Void.class));
        assertNotNull(textList.getActionImplementation(SHOULD_NOT_HAVE_PROPERTY_NUMBER_METHOD, Void.class));
        assertNotNull(textList.getActionImplementation(SHOULD_NOT_HAVE_PROPERTY_VALUE_METHOD, Void.class));
        assertNotNull(textList.getActionImplementation(SHOULD_NOT_LOOKS_LIKE_METHOD, Void.class));
        // Identifier
        WebElementIdentifier elementIdentifier = textList.getElementIdentifier();
        assertEquals("textList", elementIdentifier.getElementMethod().getName());
        assertEquals("textList", elementIdentifier.getLastUsedName());
        assertTrue(elementIdentifier.containsName("Text list of countries"));
        assertFalse(elementIdentifier.isNameDeprecated("Text list of countries"));
        assertEquals(1, elementIdentifier.names().size());
        WebTextList textListElement = textListPage.getElementRegistry().getElementByPath("Text list of countries", WebTextList.class)
                .orElseThrow(() -> new ElementNotDeclaredException(ELEMENT_NOT_DECLARED.getMessage("Text list of countries")));
        assertNotNull(textListElement);
        assertEquals("Text list of countries", elementIdentifier.getLastUsedName());
        assertNotNull(textList.toString());
        // Смотрим описание элемента
        System.out.println( textList.toString() );
    }

    @Test
    void webTextListPositiveTest(Environment env, ValueService value) {
        WebPageContext context = initWebPageContext(env, value);
        context.getPage(HomePage.class)
                .leftMenu()
                .select(value.stringEquals("Text List Elements"));
        TextListElementsPage textListElementsPage = context.getPage(TextListElementsPage.class);
        WebTextList textList = textListElementsPage.textList();
        textList.shouldBePresent()
                .shouldBeDisplayed()
                .shouldNotBeInFocus()
                .scrollTo()
                .hoverTo(true)
                .componentShouldHaveDimensions(ROOT, Dimensions.of(795.0d, 9508.0d).setInaccuracy(0.2d))
                .componentShouldHaveLocation(ROOT, Location.relative(345d, -4315d).setInaccuracy(0.2d))
                .componentShouldHaveColor(ROOT, "border-color", WebElementColor.of(33, 37, 41, 1.0d))
                .shouldHaveSize(value.intEquals(195))
                .should(worldMapImage -> {
                    if (worldMapImage.isDisplayed()) {
                        return;
                    }
                    throw new ElementNotDisplayedException(ELEMENT_NOT_DISPLAYED.getMessage(worldMapImage.getElementIdentifier().getLastUsedName()))
                            .setType(ASSERT)
                            .addAttachmentEntry(JsonAttachmentEntry.of("Element", worldMapImage.toJson()));
                });
        assertTrue(textList.isPresent());
        assertTrue(textList.isDisplayed());
        assertFalse(textList.isInFocus());
        assertEquals(Dimensions.of(795.0d, 9508.0d).setInaccuracy(0.2d), textList.getDimensions(ROOT));
        assertEquals(Location.absolute(345d, 281d).setInaccuracy(0.2d), textList.getLocation(ROOT));
        assertEquals(WebElementColor.of(33, 37, 41, 1.0d), textList.getColor(ROOT, "border-color"));
        assertEquals(195, textList.size());
        assertEquals(Point.of(397.5d, 4754d).setInaccuracy(0.2d), textList.getDimensions(ROOT).getCenter());
    }

    @Test
    void webTextListNegativeTest(Environment env, ValueService value) {
        WebPageContext context = initWebPageContext(env, value);
        context.getPage(HomePage.class)
                .leftMenu()
                .select(value.stringEquals("Text List Elements"));
        TextListElementsPage textListElementsPage = context.getPage(TextListElementsPage.class);
        WebTextList textList = textListElementsPage.textList();
        // Выполнение этого метода показывает завершение загрузки страницы
        textList.shouldBeDisplayed();
        // Для негативных сценариев меняем время ожидания, чтобы не ждать по 5 секунд проброса ошибки вне враппера
        env.getEnvironmentConfiguration().getTimeouts().setTimeout(CheckTimeout.class, Duration.ofMillis(100L));
        assertThrows(ElementIsPresentException.class, textList::shouldNotBePresent);
        assertThrows(ElementIsDisplayedException.class, textList::shouldNotBeDisplayed);
        assertThrows(ElementNotInFocusException.class, textList::shouldBeInFocus);
        Dimensions elementDimensions = Dimensions.of(795.0d, 9508.0d).setInaccuracy(0.2d);
        assertThrows(ElementDimensionsException.class, () -> textList.componentShouldNotHaveDimensions(ROOT, elementDimensions));
        Location elementLocation = Location.relative(345d, 281d).setInaccuracy(0.2d);
        assertThrows(ElementLocationException.class, () -> textList.componentShouldHaveLocation(ROOT, elementLocation.offset(15d, 1d)));
        assertThrows(ElementLocationException.class, () -> textList.componentShouldNotHaveLocation(ROOT, elementLocation));
        WebElementColor elementColor = WebElementColor.of(33, 37, 41, 1.0d);
        assertThrows(ElementColorException.class, () -> textList.componentShouldNotHaveColor(ROOT, "border-color", elementColor));
        StringValue correctPropertyValue = value.stringEquals("Some value");
        assertThrows(ElementPropertyNotDeclaredException.class, () -> textList.shouldNotHavePropertyValue("unknown property", correctPropertyValue));
        NumberValue<Integer> expectedSize = value.intEquals(196);
        assertThrows(ElementSizeException.class, () -> textList.shouldHaveSize(expectedSize));
    }

    @Test
    void webTextListFilterEmptyConditionTest(Environment env, ValueService value) {
        WebPageContext context = initWebPageContext(env, value);
        context.getPage(HomePage.class)
                .leftMenu()
                .select(value.stringEquals("Text List Elements"));
        TextListElementsPage textListElementsPage = context.getPage(TextListElementsPage.class);
        WebTextList textList = textListElementsPage.textList()
                .shouldBeDisplayed();

        textList.filter(emptyTextListFilter())
                .shouldHaveSize(value.intEquals(195));
    }

    @Test
    void webTextListFilterRowIndexConditionTest(Environment env, ValueService value) {
        WebPageContext context = initWebPageContext(env, value);
        context.getPage(HomePage.class)
                .leftMenu()
                .select(value.stringEquals("Text List Elements"));
        TextListElementsPage textListElementsPage = context.getPage(TextListElementsPage.class);
        WebTextList textList = textListElementsPage.textList()
                .shouldBeDisplayed();

        textList.filter(with(textBlockIndex(value.intGreaterThanOrEqual(100))))
                .shouldHaveSize(value.intEquals(95));
        textList.filter(without(textBlockIndex(value.intGreaterThanOrEqual(100))))
                .shouldHaveSize(value.intEquals(100));
    }

    @Test
    void webTextListFilterElementTextConditionTest(Environment env, ValueService value) {
        WebPageContext context = initWebPageContext(env, value);
        context.getPage(HomePage.class)
                .leftMenu()
                .select(value.stringEquals("Text List Elements"));
        TextListElementsPage textListElementsPage = context.getPage(TextListElementsPage.class);
        WebTextList textList = textListElementsPage.textList()
                .shouldBeDisplayed();

        // By Element
        textList.filter(with(containsTextBlock(value.stringEquals("Финляндия"))))
                .shouldHaveSize(value.intEquals(1));
        textList.filter(with(containsTextBlock(value.stringStartsWith("М"))))
                .shouldHaveSize(value.intEquals(17));
        textList.filter(with(containsTextBlock(value.stringEquals("Финляндия")).inverse()))
                .shouldHaveSize(value.intEquals(194));
        textList.filter(with(containsTextBlock(value.stringStartsWith("М")).inverse()))
                .shouldHaveSize(value.intEquals(178));

        textList.filter(without(containsTextBlock(value.stringEquals("Финляндия"))))
                .shouldHaveSize(value.intEquals(194));
        textList.filter(without(containsTextBlock(value.stringStartsWith("М"))))
                .shouldHaveSize(value.intEquals(178));
        textList.filter(without(containsTextBlock(value.stringEquals("Финляндия")).inverse()))
                .shouldHaveSize(value.intEquals(1));
        textList.filter(without(containsTextBlock(value.stringStartsWith("М")).inverse()))
                .shouldHaveSize(value.intEquals(17));
    }

}
