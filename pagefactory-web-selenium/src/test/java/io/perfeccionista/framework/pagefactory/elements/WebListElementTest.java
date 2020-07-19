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
import io.perfeccionista.framework.exceptions.LocatorNotDeclaredException;
import io.perfeccionista.framework.invocation.timeouts.CheckTimeout;
import io.perfeccionista.framework.name.WebElementIdentifier;
import io.perfeccionista.framework.pagefactory.AbstractUiTest;
import io.perfeccionista.framework.pagefactory.DefaultSeleniumWebElementsConfiguration;
import io.perfeccionista.framework.pagefactory.browser.WebBrowserDispatcher;
import io.perfeccionista.framework.pagefactory.browser.context.WebPageContext;
import io.perfeccionista.framework.pagefactory.elements.methods.Dimensions;
import io.perfeccionista.framework.pagefactory.elements.methods.Location;
import io.perfeccionista.framework.pagefactory.factory.WebPageFactory;
import io.perfeccionista.framework.pagefactory.filter.list.WebListFilter;
import io.perfeccionista.framework.pagefactory.pageobjects.HomePage;
import io.perfeccionista.framework.pagefactory.pageobjects.ListElementsPage;
import io.perfeccionista.framework.pagefactory.pageobjects.ListElementsPage.CountryBlock;
import io.perfeccionista.framework.pagefactory.screenshots.Screenshot;
import io.perfeccionista.framework.plugin.WebElementColor;
import io.perfeccionista.framework.value.ValueService;
import io.perfeccionista.framework.value.number.NumberValue;
import io.perfeccionista.framework.value.string.StringValue;
import org.junit.jupiter.api.Test;

import java.time.Duration;

import static io.perfeccionista.framework.exceptions.base.ExceptionType.ASSERT;
import static io.perfeccionista.framework.exceptions.messages.PageFactoryMessages.ELEMENT_NOT_DECLARED;
import static io.perfeccionista.framework.exceptions.messages.PageFactoryMessages.ELEMENT_NOT_DISPLAYED;
import static io.perfeccionista.framework.pagefactory.elements.WebMappedBlock.from;
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
import static io.perfeccionista.framework.pagefactory.filter.WebConditions.blockIndex;
import static io.perfeccionista.framework.pagefactory.filter.WebConditions.componentDisplayed;
import static io.perfeccionista.framework.pagefactory.filter.WebConditions.componentPresent;
import static io.perfeccionista.framework.pagefactory.filter.WebConditions.containsLabel;
import static io.perfeccionista.framework.pagefactory.filter.WebConditions.containsProperty;
import static io.perfeccionista.framework.pagefactory.filter.WebConditions.containsText;
import static io.perfeccionista.framework.pagefactory.filter.WebConditions.disabled;
import static io.perfeccionista.framework.pagefactory.filter.WebConditions.displayed;
import static io.perfeccionista.framework.pagefactory.filter.WebConditions.enabled;
import static io.perfeccionista.framework.pagefactory.filter.WebConditions.present;
import static io.perfeccionista.framework.pagefactory.filter.WebConditions.selected;
import static io.perfeccionista.framework.pagefactory.filter.WebFilters.emptyListFilter;
import static io.perfeccionista.framework.pagefactory.filter.WebFilters.with;
import static io.perfeccionista.framework.pagefactory.filter.WebFilters.without;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;

class WebListElementTest extends AbstractUiTest {

    @Test
    void webListInitializationTest(Environment env, ValueService value) {
        WebPageFactory pageFactory = new WebPageFactory(new DefaultSeleniumWebElementsConfiguration());
        ListElementsPage listElementsPage = (ListElementsPage) pageFactory.createWebPage(ListElementsPage.class);
        listElementsPage.setEnvironment(env);
        listElementsPage.setWebBrowserDispatcher(mock(WebBrowserDispatcher.class));
        WebList list = listElementsPage.webList();
        assertNotNull(list.getEnvironment());
        assertNotNull(list.getLocator(ROOT));
        assertNotNull(list.getLocatorChain());
        assertEquals(listElementsPage, list.getParent());
        WebBrowserDispatcher webBrowserDispatcher = list.getWebBrowserDispatcher();
        assertNotNull(webBrowserDispatcher);
        // WebButton
        assertNotNull(list.getActionImplementation(SCROLL_TO_ELEMENT_METHOD, Void.class));
        assertNotNull(list.getActionImplementation(SIZE_METHOD, Integer.class));
        assertNotNull(list.getActionImplementation(SHOULD_HAVE_SIZE_METHOD, Void.class));
        // WebChildElement
        assertNotNull(list.getActionImplementation(COMPONENT_SHOULD_BE_DISPLAYED_METHOD, Void.class));
        assertNotNull(list.getActionImplementation(COMPONENT_SHOULD_BE_PRESENT_METHOD, Void.class));
        assertNotNull(list.getActionImplementation(COMPONENT_SHOULD_NOT_BE_DISPLAYED_METHOD, Void.class));
        assertNotNull(list.getActionImplementation(COMPONENT_SHOULD_NOT_BE_PRESENT_METHOD, Void.class));
        assertNotNull(list.getActionImplementation(GET_COLOR_METHOD, WebElementColor.class));
        assertNotNull(list.getActionImplementation(GET_DIMENSIONS_METHOD, Dimensions.class));
        assertNotNull(list.getActionImplementation(GET_LOCATION_METHOD, Location.class));
        assertNotNull(list.getActionImplementation(GET_PROPERTY_VALUE_METHOD, String.class));
        assertNotNull(list.getActionImplementation(GET_SCREENSHOT_METHOD, Screenshot.class));
        assertNotNull(list.getActionImplementation(HOVER_TO_METHOD, Void.class));
        assertNotNull(list.getActionImplementation(IS_COMPONENT_DISPLAYED_METHOD, Boolean.class));
        assertNotNull(list.getActionImplementation(IS_COMPONENT_PRESENT_METHOD, Boolean.class));
        assertNotNull(list.getActionImplementation(IS_DISPLAYED_METHOD, Boolean.class));
        assertNotNull(list.getActionImplementation(IS_IN_FOCUS_METHOD, Boolean.class));
        assertNotNull(list.getActionImplementation(IS_PRESENT_METHOD, Boolean.class));
        assertNotNull(list.getActionImplementation(SCROLL_TO_METHOD, Void.class));
        assertNotNull(list.getActionImplementation(SHOULD_BE_DISPLAYED_METHOD, Void.class));
        assertNotNull(list.getActionImplementation(SHOULD_BE_IN_FOCUS_METHOD, Void.class));
        assertNotNull(list.getActionImplementation(SHOULD_BE_PRESENT_METHOD, Void.class));
        assertNotNull(list.getActionImplementation(SHOULD_HAVE_COLOR_METHOD, Void.class));
        assertNotNull(list.getActionImplementation(SHOULD_HAVE_DIMENSIONS_METHOD, Void.class));
        assertNotNull(list.getActionImplementation(SHOULD_HAVE_LOCATION_METHOD, Void.class));
        assertNotNull(list.getActionImplementation(SHOULD_HAVE_PROPERTY_NUMBER_METHOD, Void.class));
        assertNotNull(list.getActionImplementation(SHOULD_HAVE_PROPERTY_VALUE_METHOD, Void.class));
        assertNotNull(list.getActionImplementation(SHOULD_LOOKS_LIKE_METHOD, Void.class));
        assertNotNull(list.getActionImplementation(SHOULD_NOT_BE_DISPLAYED_METHOD, Void.class));
        assertNotNull(list.getActionImplementation(SHOULD_NOT_BE_IN_FOCUS_METHOD, Void.class));
        assertNotNull(list.getActionImplementation(SHOULD_NOT_BE_PRESENT_METHOD, Void.class));
        assertNotNull(list.getActionImplementation(SHOULD_NOT_HAVE_COLOR_METHOD, Void.class));
        assertNotNull(list.getActionImplementation(SHOULD_NOT_HAVE_DIMENSIONS_METHOD, Void.class));
        assertNotNull(list.getActionImplementation(SHOULD_NOT_HAVE_LOCATION_METHOD, Void.class));
        assertNotNull(list.getActionImplementation(SHOULD_NOT_HAVE_PROPERTY_NUMBER_METHOD, Void.class));
        assertNotNull(list.getActionImplementation(SHOULD_NOT_HAVE_PROPERTY_VALUE_METHOD, Void.class));
        assertNotNull(list.getActionImplementation(SHOULD_NOT_LOOKS_LIKE_METHOD, Void.class));
        // Identifier
        WebElementIdentifier elementIdentifier = list.getElementIdentifier();
        assertEquals("webList", elementIdentifier.getElementMethod().getName());
        assertEquals("webList", elementIdentifier.getLastUsedName());
        assertTrue(elementIdentifier.containsName("List of countries"));
        assertFalse(elementIdentifier.isNameDeprecated("List of countries"));
        assertEquals(1, elementIdentifier.names().size());
        WebList listElement = listElementsPage.getElementRegistry().getElementByPath("List of countries", WebList.class)
                .orElseThrow(() -> new ElementNotDeclaredException(ELEMENT_NOT_DECLARED.getMessage("List of countries")));
        assertNotNull(listElement);
        assertEquals("List of countries", elementIdentifier.getLastUsedName());
        assertNotNull(list.toString());
        // Смотрим описание элемента
        System.out.println( list.toString() );
    }

    @Test
    void webListPositiveTest(Environment env, ValueService value) {
        WebPageContext context = initWebPageContext(env, value);
        context.getPage(HomePage.class)
                .leftMenu()
                .select(value.stringEquals("List Elements"));
        ListElementsPage listElementsPage = context.getPage(ListElementsPage.class);
        WebList list = listElementsPage.webList();
        list.shouldBePresent()
                .shouldBeDisplayed()
                .shouldNotBeInFocus()
                .scrollTo()
                .hoverTo(true)
                .componentShouldHaveDimensions(ROOT, Dimensions.of(795.0d, 10295.0d).setInaccuracy(0.2d))
                .componentShouldHaveLocation(ROOT, Location.relative(345d, -4396d).setInaccuracy(0.2d))
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
        assertTrue(list.isPresent());
        assertTrue(list.isDisplayed());
        assertFalse(list.isInFocus());
        assertEquals(Dimensions.of(795.0d, 10295.0d).setInaccuracy(0.2d), list.getDimensions(ROOT));
        assertEquals(Location.absolute(345d, 335d).setInaccuracy(0.2d), list.getLocation(ROOT));
        assertEquals(WebElementColor.of(33, 37, 41, 1.0d), list.getColor(ROOT, "border-color"));
        assertEquals(195, list.size());
    }

    @Test
    void webListNegativeTest(Environment env, ValueService value) {
        WebPageContext context = initWebPageContext(env, value);
        context.getPage(HomePage.class)
                .leftMenu()
                .select(value.stringEquals("List Elements"));
        ListElementsPage listElementsPage = context.getPage(ListElementsPage.class);
        WebList list = listElementsPage.webList();
        // Выполнение этого метода показывает завершение загрузки страницы
        list.shouldBeDisplayed();
        // Для негативных сценариев меняем время ожидания, чтобы не ждать по 5 секунд проброса ошибки вне враппера
        env.getEnvironmentConfiguration().getTimeouts().setTimeout(CheckTimeout.class, Duration.ofMillis(100L));
        assertThrows(ElementIsPresentException.class, list::shouldNotBePresent);
        assertThrows(ElementIsDisplayedException.class, list::shouldNotBeDisplayed);
        assertThrows(ElementNotInFocusException.class, list::shouldBeInFocus);
        Dimensions elementDimensions = Dimensions.of(795.0d, 10295.0d).setInaccuracy(0.2d);
        assertThrows(ElementDimensionsException.class, () -> list.componentShouldNotHaveDimensions(ROOT, elementDimensions));
        Location elementLocation = Location.of(345d, 335d, 345d, 335d).setInaccuracy(0.2d);
        assertThrows(ElementLocationException.class, () -> list.componentShouldHaveLocation(ROOT, elementLocation.offset(15d, 1d)));
        assertThrows(ElementLocationException.class, () -> list.componentShouldNotHaveLocation(ROOT, elementLocation));
        WebElementColor elementColor = WebElementColor.of(33, 37, 41, 1.0d);
        assertThrows(ElementColorException.class, () -> list.componentShouldNotHaveColor(ROOT, "border-color", elementColor));
        StringValue correctPropertyValue = value.stringEquals("World map picture");
        assertThrows(ElementPropertyNotDeclaredException.class, () -> list.shouldNotHavePropertyValue("unknown property", correctPropertyValue));
        NumberValue<Integer> expectedSize = value.intEquals(196);
        assertThrows(ElementSizeException.class, () -> list.shouldHaveSize(expectedSize));
    }

    @Test
    void webListFilterEmptyConditionTest(Environment env, ValueService value) {
        WebPageContext context = initWebPageContext(env, value);
        context.getPage(HomePage.class)
                .leftMenu()
                .select(value.stringEquals("List Elements"));
        ListElementsPage listElementsPage = context.getPage(ListElementsPage.class);
        WebList list = listElementsPage.webList()
                .shouldBeDisplayed();

        list.filter(emptyListFilter())
                .shouldHaveSize(value.intEquals(195));
    }

    @Test
    void webListFilterRowIndexConditionTest(Environment env, ValueService value) {
        WebPageContext context = initWebPageContext(env, value);
        context.getPage(HomePage.class)
                .leftMenu()
                .select(value.stringEquals("List Elements"));
        ListElementsPage listElementsPage = context.getPage(ListElementsPage.class);
        WebList list = listElementsPage.webList()
                .shouldBeDisplayed();

        list.filter(with(blockIndex(value.intGreaterThanOrEqual(100))))
                .shouldHaveSize(value.intEquals(95));
        list.filter(without(blockIndex(value.intGreaterThanOrEqual(100))))
                .shouldHaveSize(value.intEquals(100));
    }

    // TODO: То же самое для поиска элемента по имени
    @Test
    void webListFilterElementTextConditionTest(Environment env, ValueService value) {
        WebPageContext context = initWebPageContext(env, value);
        context.getPage(HomePage.class)
                .leftMenu()
                .select(value.stringEquals("List Elements"));
        ListElementsPage listElementsPage = context.getPage(ListElementsPage.class);
        WebList list = listElementsPage.webList()
                .shouldBeDisplayed();

        list.filter(with(containsText(from(CountryBlock.class).shortName(), value.stringEquals("Финляндия"))))
                .shouldHaveSize(value.intEquals(1));
        list.filter(with(containsText(from(CountryBlock.class).shortName(), value.stringStartsWith("М"))))
                .shouldHaveSize(value.intEquals(17));
        list.filter(with(containsText(from(CountryBlock.class).shortName(), value.stringEquals("Финляндия")).inverse()))
                .shouldHaveSize(value.intEquals(194));
        list.filter(with(containsText(from(CountryBlock.class).shortName(), value.stringStartsWith("М")).inverse()))
                .shouldHaveSize(value.intEquals(178));

        list.filter(without(containsText(from(CountryBlock.class).shortName(), value.stringEquals("Финляндия"))))
                .shouldHaveSize(value.intEquals(194));
        list.filter(without(containsText(from(CountryBlock.class).shortName(), value.stringStartsWith("М"))))
                .shouldHaveSize(value.intEquals(178));
        list.filter(without(containsText(from(CountryBlock.class).shortName(), value.stringEquals("Финляндия")).inverse()))
                .shouldHaveSize(value.intEquals(1));
        list.filter(without(containsText(from(CountryBlock.class).shortName(), value.stringStartsWith("М")).inverse()))
                .shouldHaveSize(value.intEquals(17));

        list.filter(with(containsText(from(CountryBlock.class).number(), value.intEquals(77))))
                .shouldHaveSize(value.intEquals(1));
        list.filter(with(containsText(from(CountryBlock.class).number(), value.intGreaterThanOrEqual(124))))
                .shouldHaveSize(value.intEquals(72));
        list.filter(with(containsText(from(CountryBlock.class).number(), value.intEquals(77)).inverse()))
                .shouldHaveSize(value.intEquals(194));
        list.filter(with(containsText(from(CountryBlock.class).number(), value.intGreaterThanOrEqual(124)).inverse()))
                .shouldHaveSize(value.intEquals(123));

        list.filter(without(containsText(from(CountryBlock.class).number(), value.intEquals(77))))
                .shouldHaveSize(value.intEquals(194));
        list.filter(without(containsText(from(CountryBlock.class).number(), value.intGreaterThanOrEqual(124))))
                .shouldHaveSize(value.intEquals(123));
        list.filter(without(containsText(from(CountryBlock.class).number(), value.intEquals(77)).inverse()))
                .shouldHaveSize(value.intEquals(1));
        list.filter(without(containsText(from(CountryBlock.class).number(), value.intGreaterThanOrEqual(124)).inverse()))
                .shouldHaveSize(value.intEquals(72));
    }

    // TODO: То же самое для поиска элемента по имени
    @Test
    void webListFilterElementSelectedConditionTest(Environment env, ValueService value) {
        WebPageContext context = initWebPageContext(env, value);
        context.getPage(HomePage.class)
                .leftMenu()
                .select(value.stringEquals("List Elements"));
        ListElementsPage listElementsPage = context.getPage(ListElementsPage.class);
        WebList list = listElementsPage.webList()
                .shouldBeDisplayed();

        list.filter(with(selected(from(CountryBlock.class).checkbox())))
                .shouldHaveSize(value.intEquals(6));
        list.filter(with(selected(from(CountryBlock.class).checkbox()).inverse()))
                .shouldHaveSize(value.intEquals(189));

        list.filter(without(selected(from(CountryBlock.class).checkbox())))
                .shouldHaveSize(value.intEquals(189));
        list.filter(without(selected(from(CountryBlock.class).checkbox()).inverse()))
                .shouldHaveSize(value.intEquals(6));
    }

    // TODO: То же самое для поиска элемента по имени
    @Test
    void webListFilterElementPropertyConditionTest(Environment env, ValueService value) {
        WebPageContext context = initWebPageContext(env, value);
        context.getPage(HomePage.class)
                .leftMenu()
                .select(value.stringEquals("List Elements"));
        ListElementsPage listElementsPage = context.getPage(ListElementsPage.class);
        WebList list = listElementsPage.webList()
                .shouldBeDisplayed();

        list.filter(with(containsProperty(from(CountryBlock.class).fullName(), "prompt", value.stringEquals("Финляндская Республика"))))
                .shouldHaveSize(value.intEquals(1));
        list.filter(with(containsProperty(from(CountryBlock.class).fullName(), "prompt", value.stringStartsWith("М"))))
                .shouldHaveSize(value.intEquals(5));
        list.filter(with(containsProperty(from(CountryBlock.class).fullName(), "prompt", value.stringEquals("Финляндская Республика")).inverse()))
                .shouldHaveSize(value.intEquals(194));
        list.filter(with(containsProperty(from(CountryBlock.class).fullName(), "prompt", value.stringStartsWith("М")).inverse()))
                .shouldHaveSize(value.intEquals(190));

        list.filter(without(containsProperty(from(CountryBlock.class).fullName(), "prompt", value.stringEquals("Финляндская Республика"))))
                .shouldHaveSize(value.intEquals(194));
        list.filter(without(containsProperty(from(CountryBlock.class).fullName(), "prompt", value.stringStartsWith("М"))))
                .shouldHaveSize(value.intEquals(190));
        list.filter(without(containsProperty(from(CountryBlock.class).fullName(), "prompt", value.stringEquals("Финляндская Республика")).inverse()))
                .shouldHaveSize(value.intEquals(1));
        list.filter(without(containsProperty(from(CountryBlock.class).fullName(), "prompt", value.stringStartsWith("М")).inverse()))
                .shouldHaveSize(value.intEquals(5));
    }

    // TODO: То же самое для поиска элемента по имени
    @Test
    void webListFilterElementPresentConditionTest(Environment env, ValueService value) {
        WebPageContext context = initWebPageContext(env, value);
        context.getPage(HomePage.class)
                .leftMenu()
                .select(value.stringEquals("List Elements"));
        ListElementsPage listElementsPage = context.getPage(ListElementsPage.class);
        WebList list = listElementsPage.webList()
                .shouldBeDisplayed();

        list.filter(with(present(from(CountryBlock.class).shortName())))
                .shouldHaveSize(value.intEquals(193));
        list.filter(with(present(from(CountryBlock.class).shortName()).inverse()))
                .shouldHaveSize(value.intEquals(2));

        list.filter(without(present(from(CountryBlock.class).shortName())))
                .shouldHaveSize(value.intEquals(2));
        list.filter(without(present(from(CountryBlock.class).shortName()).inverse()))
                .shouldHaveSize(value.intEquals(193));
    }

    // TODO: То же самое для поиска элемента по имени
    @Test
    void webListFilterElementLabelConditionTest(Environment env, ValueService value) {
        WebPageContext context = initWebPageContext(env, value);
        context.getPage(HomePage.class)
                .leftMenu()
                .select(value.stringEquals("List Elements"));
        ListElementsPage listElementsPage = context.getPage(ListElementsPage.class);
        WebList list = listElementsPage.webList()
                .shouldBeDisplayed();

        list.filter(with(containsLabel(from(CountryBlock.class).checkbox(), value.stringEquals("86"))))
                .shouldHaveSize(value.intEquals(1));
        list.filter(with(containsLabel(from(CountryBlock.class).checkbox(), value.stringStartsWith("15"))))
                .shouldHaveSize(value.intEquals(11));
        list.filter(with(containsLabel(from(CountryBlock.class).checkbox(), value.stringEquals("86")).inverse()))
                .shouldHaveSize(value.intEquals(194));
        list.filter(with(containsLabel(from(CountryBlock.class).checkbox(), value.stringStartsWith("15")).inverse()))
                .shouldHaveSize(value.intEquals(184));

        list.filter(without(containsLabel(from(CountryBlock.class).checkbox(), value.stringEquals("86"))))
                .shouldHaveSize(value.intEquals(194));
        list.filter(without(containsLabel(from(CountryBlock.class).checkbox(), value.stringStartsWith("15"))))
                .shouldHaveSize(value.intEquals(184));
        list.filter(without(containsLabel(from(CountryBlock.class).checkbox(), value.stringEquals("86")).inverse()))
                .shouldHaveSize(value.intEquals(1));
        list.filter(without(containsLabel(from(CountryBlock.class).checkbox(), value.stringStartsWith("15")).inverse()))
                .shouldHaveSize(value.intEquals(11));

        list.filter(with(containsLabel(from(CountryBlock.class).checkbox(), value.intEquals(77))))
                .shouldHaveSize(value.intEquals(1));
        list.filter(with(containsLabel(from(CountryBlock.class).checkbox(), value.intGreaterThanOrEqual(124))))
                .shouldHaveSize(value.intEquals(72));
        list.filter(with(containsLabel(from(CountryBlock.class).checkbox(), value.intEquals(77)).inverse()))
                .shouldHaveSize(value.intEquals(194));
        list.filter(with(containsLabel(from(CountryBlock.class).checkbox(), value.intGreaterThanOrEqual(124)).inverse()))
                .shouldHaveSize(value.intEquals(123));

        list.filter(without(containsLabel(from(CountryBlock.class).checkbox(), value.intEquals(77))))
                .shouldHaveSize(value.intEquals(194));
        list.filter(without(containsLabel(from(CountryBlock.class).checkbox(), value.intGreaterThanOrEqual(124))))
                .shouldHaveSize(value.intEquals(123));
        list.filter(without(containsLabel(from(CountryBlock.class).checkbox(), value.intEquals(77)).inverse()))
                .shouldHaveSize(value.intEquals(1));
        list.filter(without(containsLabel(from(CountryBlock.class).checkbox(), value.intGreaterThanOrEqual(124)).inverse()))
                .shouldHaveSize(value.intEquals(72));
    }

    // TODO: То же самое для поиска элемента по имени
    @Test
    void webListFilterElementEnabledConditionTest(Environment env, ValueService value) {
        WebPageContext context = initWebPageContext(env, value);
        context.getPage(HomePage.class)
                .leftMenu()
                .select(value.stringEquals("List Elements"));
        ListElementsPage listElementsPage = context.getPage(ListElementsPage.class);
        WebList list = listElementsPage.webList()
                .shouldBeDisplayed();

        list.filter(with(enabled(from(CountryBlock.class).checkbox())))
                .shouldHaveSize(value.intEquals(189));
        list.filter(with(disabled(from(CountryBlock.class).checkbox())))
                .shouldHaveSize(value.intEquals(6));

        list.filter(without(enabled(from(CountryBlock.class).checkbox())))
                .shouldHaveSize(value.intEquals(6));
        list.filter(without(disabled(from(CountryBlock.class).checkbox())))
                .shouldHaveSize(value.intEquals(189));
    }

    // TODO: То же самое для поиска элемента по имени
    @Test
    void webListFilterElementDisplayedConditionTest(Environment env, ValueService value) {
        WebPageContext context = initWebPageContext(env, value);
        context.getPage(HomePage.class)
                .leftMenu()
                .select(value.stringEquals("List Elements"));
        ListElementsPage listElementsPage = context.getPage(ListElementsPage.class);
        WebList list = listElementsPage.webList()
                .shouldBeDisplayed();

        // Для элементов, которых нет в DOM
        list.filter(with(displayed(from(CountryBlock.class).shortName())))
                .shouldHaveSize(value.intEquals(193));
        list.filter(with(displayed(from(CountryBlock.class).shortName()).inverse()))
                .shouldHaveSize(value.intEquals(2));

        list.filter(without(displayed(from(CountryBlock.class).shortName())))
                .shouldHaveSize(value.intEquals(2));
        list.filter(without(displayed(from(CountryBlock.class).shortName()).inverse()))
                .shouldHaveSize(value.intEquals(193));

        // Для элементов, которые есть в DOM, но не отображаются
        list.filter(with(displayed(from(CountryBlock.class).populationUnit())))
                .shouldHaveSize(value.intEquals(186));
        list.filter(with(displayed(from(CountryBlock.class).populationUnit()).inverse()))
                .shouldHaveSize(value.intEquals(9));

        list.filter(without(displayed(from(CountryBlock.class).populationUnit())))
                .shouldHaveSize(value.intEquals(9));
        list.filter(without(displayed(from(CountryBlock.class).populationUnit()).inverse()))
                .shouldHaveSize(value.intEquals(186));
    }

    // TODO: То же самое для поиска элемента по имени
    @Test
    void webListFilterElementComponentPresentConditionTest(Environment env, ValueService value) {
        WebPageContext context = initWebPageContext(env, value);
        context.getPage(HomePage.class)
                .leftMenu()
                .select(value.stringEquals("List Elements"));
        ListElementsPage listElementsPage = context.getPage(ListElementsPage.class);
        WebList list = listElementsPage.webList()
                .shouldBeDisplayed();

        list.filter(with(componentPresent(from(CountryBlock.class).shortName(), "Self")))
                .shouldHaveSize(value.intEquals(193));
        list.filter(with(componentPresent(from(CountryBlock.class).shortName(), "Self").inverse()))
                .shouldHaveSize(value.intEquals(2));

        list.filter(without(componentPresent(from(CountryBlock.class).shortName(), "Self")))
                .shouldHaveSize(value.intEquals(2));
        list.filter(without(componentPresent(from(CountryBlock.class).shortName(), "Self").inverse()))
                .shouldHaveSize(value.intEquals(193));

        WebListFilter filter = list
                .filter(with(componentPresent(from(CountryBlock.class).shortName(), "Unknown")));
        NumberValue<Integer> expectedValue = value.intEquals(200);
        assertThrows(LocatorNotDeclaredException.class, () -> filter.shouldHaveSize(expectedValue));
    }

    // TODO: То же самое для поиска элемента по имени
    @Test
    void webListFilterElementComponentDisplayedConditionTest(Environment env, ValueService value) {
        WebPageContext context = initWebPageContext(env, value);
        context.getPage(HomePage.class)
                .leftMenu()
                .select(value.stringEquals("List Elements"));
        ListElementsPage listElementsPage = context.getPage(ListElementsPage.class);
        WebList list = listElementsPage.webList()
                .shouldBeDisplayed();

        list.filter(with(componentDisplayed(from(CountryBlock.class).populationUnit(), "Self")))
                .shouldHaveSize(value.intEquals(186));
        list.filter(with(componentDisplayed(from(CountryBlock.class).populationUnit(), "Self").inverse()))
                .shouldHaveSize(value.intEquals(9));

        list.filter(without(componentDisplayed(from(CountryBlock.class).populationUnit(), "Self")))
                .shouldHaveSize(value.intEquals(9));
        list.filter(without(componentDisplayed(from(CountryBlock.class).populationUnit(), "Self").inverse()))
                .shouldHaveSize(value.intEquals(186));

        WebListFilter filter = list
                .filter(with(componentDisplayed(from(CountryBlock.class).shortName(), "Unknown")));
        NumberValue<Integer> expectedValue = value.intEquals(200);
        assertThrows(LocatorNotDeclaredException.class, () -> filter.shouldHaveSize(expectedValue));
    }





    // Extractors

    @Test
    void webListExtractorsTest(Environment env, ValueService value) {
        WebPageContext context = initWebPageContext(env, value);
        context.getPage(HomePage.class)
                .leftMenu()
                .select(value.stringEquals("List Elements"));
        ListElementsPage listElementsPage = context.getPage(ListElementsPage.class);
        WebList list = listElementsPage.webList()
                .shouldBeDisplayed();




    }


}
