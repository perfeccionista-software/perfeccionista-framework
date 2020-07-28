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
import io.perfeccionista.framework.pagefactory.pageobjects.TextTablePage;
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
import static io.perfeccionista.framework.pagefactory.filter.WebConditions.containsTextCell;
import static io.perfeccionista.framework.pagefactory.filter.WebConditions.textRowIndex;
import static io.perfeccionista.framework.pagefactory.filter.WebFilters.emptyTextTableFilter;
import static io.perfeccionista.framework.pagefactory.filter.WebFilters.with;
import static io.perfeccionista.framework.pagefactory.filter.WebFilters.without;
import static io.perfeccionista.framework.pagefactory.pageobjects.TablePage.NUMBER;
import static io.perfeccionista.framework.pagefactory.pageobjects.TablePage.SHORT_NAME;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;

@Tags(@Tag("Element"))
class WebTextTableElementTest extends AbstractUiTest {

    @Test
    void webTableInitializationTest(Environment env) {
        WebPageFactory pageFactory = new WebPageFactory(new DefaultSeleniumWebElementsConfiguration());
        TextTablePage textTablePage = (TextTablePage) pageFactory.createWebPage(TextTablePage.class);
        textTablePage.setEnvironment(env);
        textTablePage.setWebBrowserDispatcher(mock(WebBrowserDispatcher.class));
        WebTextTable textTable = textTablePage.textTable();
        assertNotNull(textTable.getEnvironment());
        assertNotNull(textTable.getLocator(ROOT));
        assertNotNull(textTable.getLocatorChain());
        assertEquals(textTablePage, textTable.getParent());
        WebBrowserDispatcher webBrowserDispatcher = textTable.getWebBrowserDispatcher();
        assertNotNull(webBrowserDispatcher);
        // WebButton
        assertNotNull(textTable.getActionImplementation(SCROLL_TO_ELEMENT_METHOD, Void.class));
        assertNotNull(textTable.getActionImplementation(SIZE_METHOD, Integer.class));
        assertNotNull(textTable.getActionImplementation(SHOULD_HAVE_SIZE_METHOD, Void.class));
        // WebChildElement
        assertNotNull(textTable.getActionImplementation(COMPONENT_SHOULD_BE_DISPLAYED_METHOD, Void.class));
        assertNotNull(textTable.getActionImplementation(COMPONENT_SHOULD_BE_PRESENT_METHOD, Void.class));
        assertNotNull(textTable.getActionImplementation(COMPONENT_SHOULD_NOT_BE_DISPLAYED_METHOD, Void.class));
        assertNotNull(textTable.getActionImplementation(COMPONENT_SHOULD_NOT_BE_PRESENT_METHOD, Void.class));
        assertNotNull(textTable.getActionImplementation(GET_COLOR_METHOD, WebElementColor.class));
        assertNotNull(textTable.getActionImplementation(GET_DIMENSIONS_METHOD, Dimensions.class));
        assertNotNull(textTable.getActionImplementation(GET_LOCATION_METHOD, Location.class));
        assertNotNull(textTable.getActionImplementation(GET_PROPERTY_VALUE_METHOD, String.class));
        assertNotNull(textTable.getActionImplementation(GET_SCREENSHOT_METHOD, Screenshot.class));
        assertNotNull(textTable.getActionImplementation(HOVER_TO_METHOD, Void.class));
        assertNotNull(textTable.getActionImplementation(IS_COMPONENT_DISPLAYED_METHOD, Boolean.class));
        assertNotNull(textTable.getActionImplementation(IS_COMPONENT_PRESENT_METHOD, Boolean.class));
        assertNotNull(textTable.getActionImplementation(IS_DISPLAYED_METHOD, Boolean.class));
        assertNotNull(textTable.getActionImplementation(IS_IN_FOCUS_METHOD, Boolean.class));
        assertNotNull(textTable.getActionImplementation(IS_PRESENT_METHOD, Boolean.class));
        assertNotNull(textTable.getActionImplementation(SCROLL_TO_METHOD, Void.class));
        assertNotNull(textTable.getActionImplementation(SHOULD_BE_DISPLAYED_METHOD, Void.class));
        assertNotNull(textTable.getActionImplementation(SHOULD_BE_IN_FOCUS_METHOD, Void.class));
        assertNotNull(textTable.getActionImplementation(SHOULD_BE_PRESENT_METHOD, Void.class));
        assertNotNull(textTable.getActionImplementation(SHOULD_HAVE_COLOR_METHOD, Void.class));
        assertNotNull(textTable.getActionImplementation(SHOULD_HAVE_DIMENSIONS_METHOD, Void.class));
        assertNotNull(textTable.getActionImplementation(SHOULD_HAVE_LOCATION_METHOD, Void.class));
        assertNotNull(textTable.getActionImplementation(SHOULD_HAVE_PROPERTY_NUMBER_METHOD, Void.class));
        assertNotNull(textTable.getActionImplementation(SHOULD_HAVE_PROPERTY_VALUE_METHOD, Void.class));
        assertNotNull(textTable.getActionImplementation(SHOULD_LOOKS_LIKE_METHOD, Void.class));
        assertNotNull(textTable.getActionImplementation(SHOULD_NOT_BE_DISPLAYED_METHOD, Void.class));
        assertNotNull(textTable.getActionImplementation(SHOULD_NOT_BE_IN_FOCUS_METHOD, Void.class));
        assertNotNull(textTable.getActionImplementation(SHOULD_NOT_BE_PRESENT_METHOD, Void.class));
        assertNotNull(textTable.getActionImplementation(SHOULD_NOT_HAVE_COLOR_METHOD, Void.class));
        assertNotNull(textTable.getActionImplementation(SHOULD_NOT_HAVE_DIMENSIONS_METHOD, Void.class));
        assertNotNull(textTable.getActionImplementation(SHOULD_NOT_HAVE_LOCATION_METHOD, Void.class));
        assertNotNull(textTable.getActionImplementation(SHOULD_NOT_HAVE_PROPERTY_NUMBER_METHOD, Void.class));
        assertNotNull(textTable.getActionImplementation(SHOULD_NOT_HAVE_PROPERTY_VALUE_METHOD, Void.class));
        assertNotNull(textTable.getActionImplementation(SHOULD_NOT_LOOKS_LIKE_METHOD, Void.class));
        // Identifier
        WebElementIdentifier elementIdentifier = textTable.getElementIdentifier();
        assertEquals("textTable", elementIdentifier.getElementMethod().getName());
        assertEquals("textTable", elementIdentifier.getLastUsedName());
        assertTrue(elementIdentifier.containsName("Text table of countries"));
        assertFalse(elementIdentifier.isNameDeprecated("Text table of countries"));
        assertEquals(1, elementIdentifier.names().size());
        WebTextTable textTableElement = textTablePage.getElementRegistry().getElementByPath("Text table of countries", WebTextTable.class)
                .orElseThrow(() -> new ElementNotDeclaredException(ELEMENT_NOT_DECLARED.getMessage("Text table of countries")));
        assertNotNull(textTableElement);
        assertEquals("Text table of countries", elementIdentifier.getLastUsedName());
        assertNotNull(textTable.toString());
        // Смотрим описание элемента
        System.out.println( textTable.toString() );
    }

    @Test
    void webTextTablePositiveTest(Environment env, ValueService value) {
        WebPageContext context = initWebPageContext(env, value);
        context.getPage(HomePage.class)
                .leftMenu()
                .select(value.stringEquals("Text Table Element"));
        TextTablePage textTablePage = context.getPage(TextTablePage.class);
        WebTextTable textTable = textTablePage.textTable();
        textTable.shouldBePresent()
                .shouldBeDisplayed()
                .shouldNotBeInFocus()
                .scrollTo()
                .hoverTo(true)
                .componentShouldHaveDimensions(ROOT, Dimensions.of(795.0d, 10014.0d).setInaccuracy(0.2d))
                .componentShouldHaveLocation(ROOT, Location.relative(345d, -4568d).setInaccuracy(0.2d))
                .componentShouldHaveColor(ROOT, "border-color", WebElementColor.of(222, 226, 230, 1.0d))
                .shouldHaveSize(value.intEquals(195))
                .should(worldMapImage -> {
                    if (worldMapImage.isDisplayed()) {
                        return;
                    }
                    throw new ElementNotDisplayedException(ELEMENT_NOT_DISPLAYED.getMessage(worldMapImage.getElementIdentifier().getLastUsedName()))
                            .setType(ASSERT)
                            .addAttachmentEntry(JsonAttachmentEntry.of("Element", worldMapImage.toJson()));
                });
        assertTrue(textTable.isPresent());
        assertTrue(textTable.isDisplayed());
        assertFalse(textTable.isInFocus());
        assertEquals(Dimensions.of(795.0d, 10014.0d).setInaccuracy(0.2d), textTable.getDimensions(ROOT));
        assertEquals(Location.absolute(345d, 173d).setInaccuracy(0.2d), textTable.getLocation(ROOT));
        assertEquals(WebElementColor.of(222, 226, 230, 1.0d), textTable.getColor(ROOT, "border-color"));
        assertEquals(195, textTable.size());
        assertEquals(Point.of(397.5d, 5007d).setInaccuracy(0.2d), textTable.getDimensions(ROOT).getCenter());
    }

    @Test
    void webTextTableNegativeTest(Environment env, ValueService value) {
        WebPageContext context = initWebPageContext(env, value);
        context.getPage(HomePage.class)
                .leftMenu()
                .select(value.stringEquals("Text Table Element"));
        TextTablePage textTablePage = context.getPage(TextTablePage.class);
        WebTextTable textTable = textTablePage.textTable();
        // Выполнение этого метода показывает завершение загрузки страницы
        textTable.shouldBeDisplayed();
        // Для негативных сценариев меняем время ожидания, чтобы не ждать по 5 секунд проброса ошибки вне враппера
        env.getEnvironmentConfiguration().getTimeouts().setTimeout(CheckTimeout.class, Duration.ofMillis(100L));
        assertThrows(ElementIsPresentException.class, textTable::shouldNotBePresent);
        assertThrows(ElementIsDisplayedException.class, textTable::shouldNotBeDisplayed);
        assertThrows(ElementNotInFocusException.class, textTable::shouldBeInFocus);
        Dimensions elementDimensions = Dimensions.of(795.0d, 10014.0d).setInaccuracy(0.2d);
        assertThrows(ElementDimensionsException.class, () -> textTable.componentShouldNotHaveDimensions(ROOT, elementDimensions));
        Location elementLocation = Location.relative(345d, 173d).setInaccuracy(0.2d);
        assertThrows(ElementLocationException.class, () -> textTable.componentShouldHaveLocation(ROOT, elementLocation.offset(15d, 1d)));
        assertThrows(ElementLocationException.class, () -> textTable.componentShouldNotHaveLocation(ROOT, elementLocation));
        WebElementColor elementColor = WebElementColor.of(222, 226, 230, 1.0d);
        assertThrows(ElementColorException.class, () -> textTable.componentShouldNotHaveColor(ROOT, "border-color", elementColor));
        StringValue correctPropertyValue = value.stringEquals("Some value");
        assertThrows(ElementPropertyNotDeclaredException.class, () -> textTable.shouldNotHavePropertyValue("unknown property", correctPropertyValue));
        NumberValue<Integer> expectedSize = value.intEquals(196);
        assertThrows(ElementSizeException.class, () -> textTable.shouldHaveSize(expectedSize));
    }

    @Test
    void webTextTableFilterEmptyConditionTest(Environment env, ValueService value) {
        WebPageContext context = initWebPageContext(env, value);
        context.getPage(HomePage.class)
                .leftMenu()
                .select(value.stringEquals("Text Table Element"));
        TextTablePage textTablePage = context.getPage(TextTablePage.class);
        WebTextTable textTable = textTablePage.textTable()
                .shouldBeDisplayed();

        textTable.filter(emptyTextTableFilter())
                .shouldHaveSize(value.intEquals(195));
    }

    @Test
    void webTableFilterRowIndexConditionTest(Environment env, ValueService value) {
        WebPageContext context = initWebPageContext(env, value);
        context.getPage(HomePage.class)
                .leftMenu()
                .select(value.stringEquals("Text Table Element"));
        TextTablePage textTablePage = context.getPage(TextTablePage.class);
        WebTextTable textTable = textTablePage.textTable()
                .shouldBeDisplayed();

        textTable.filter(with(textRowIndex(value.intGreaterThanOrEqual(100))))
                .shouldHaveSize(value.intEquals(95));
        textTable.filter(without(textRowIndex(value.intGreaterThanOrEqual(100))))
                .shouldHaveSize(value.intEquals(100));
    }

    @Test
    void webTableFilterElementTextConditionTest(Environment env, ValueService value) {
        WebPageContext context = initWebPageContext(env, value);
        context.getPage(HomePage.class)
                .leftMenu()
                .select(value.stringEquals("Text Table Element"));
        TextTablePage textTablePage = context.getPage(TextTablePage.class);
        WebTextTable textTable = textTablePage.textTable()
                .shouldBeDisplayed();

        // By Element
        textTable.filter(with(containsTextCell(SHORT_NAME, value.stringEquals("Финляндия"))))
                .shouldHaveSize(value.intEquals(1));
        textTable.filter(with(containsTextCell(SHORT_NAME, value.stringStartsWith("М"))))
                .shouldHaveSize(value.intEquals(17));
        textTable.filter(with(containsTextCell(SHORT_NAME, value.stringEquals("Финляндия")).inverse()))
                .shouldHaveSize(value.intEquals(194));
        textTable.filter(with(containsTextCell(SHORT_NAME, value.stringStartsWith("М")).inverse()))
                .shouldHaveSize(value.intEquals(178));

        textTable.filter(without(containsTextCell(SHORT_NAME, value.stringEquals("Финляндия"))))
                .shouldHaveSize(value.intEquals(194));
        textTable.filter(without(containsTextCell(SHORT_NAME, value.stringStartsWith("М"))))
                .shouldHaveSize(value.intEquals(178));
        textTable.filter(without(containsTextCell(SHORT_NAME, value.stringEquals("Финляндия")).inverse()))
                .shouldHaveSize(value.intEquals(1));
        textTable.filter(without(containsTextCell(SHORT_NAME, value.stringStartsWith("М")).inverse()))
                .shouldHaveSize(value.intEquals(17));

        textTable.filter(with(containsTextCell(NUMBER, value.intEquals(77))))
                .shouldHaveSize(value.intEquals(1));
        textTable.filter(with(containsTextCell(NUMBER, value.intGreaterThanOrEqual(124))))
                .shouldHaveSize(value.intEquals(72));
        textTable.filter(with(containsTextCell(NUMBER, value.intEquals(77)).inverse()))
                .shouldHaveSize(value.intEquals(194));
        textTable.filter(with(containsTextCell(NUMBER, value.intGreaterThanOrEqual(124)).inverse()))
                .shouldHaveSize(value.intEquals(123));

        textTable.filter(without(containsTextCell(NUMBER, value.intEquals(77))))
                .shouldHaveSize(value.intEquals(194));
        textTable.filter(without(containsTextCell(NUMBER, value.intGreaterThanOrEqual(124))))
                .shouldHaveSize(value.intEquals(123));
        textTable.filter(without(containsTextCell(NUMBER, value.intEquals(77)).inverse()))
                .shouldHaveSize(value.intEquals(1));
        textTable.filter(without(containsTextCell(NUMBER, value.intGreaterThanOrEqual(124)).inverse()))
                .shouldHaveSize(value.intEquals(72));
    }

}
