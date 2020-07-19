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
import io.perfeccionista.framework.pagefactory.filter.table.WebTableFilter;
import io.perfeccionista.framework.pagefactory.pageobjects.HomePage;
import io.perfeccionista.framework.pagefactory.pageobjects.TablePage;
import io.perfeccionista.framework.pagefactory.pageobjects.TablePage.CheckboxWebMappedBlock;
import io.perfeccionista.framework.pagefactory.pageobjects.TablePage.CountryNumberWebMappedBlock;
import io.perfeccionista.framework.pagefactory.pageobjects.TablePage.FullNameWebMappedBlock;
import io.perfeccionista.framework.pagefactory.pageobjects.TablePage.PopulationWebMappedBlock;
import io.perfeccionista.framework.pagefactory.pageobjects.TablePage.ShortNameWebMappedBlock;
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
import static io.perfeccionista.framework.pagefactory.filter.WebConditions.componentDisplayed;
import static io.perfeccionista.framework.pagefactory.filter.WebConditions.componentPresent;
import static io.perfeccionista.framework.pagefactory.filter.WebConditions.containsLabel;
import static io.perfeccionista.framework.pagefactory.filter.WebConditions.containsProperty;
import static io.perfeccionista.framework.pagefactory.filter.WebConditions.containsText;
import static io.perfeccionista.framework.pagefactory.filter.WebConditions.disabled;
import static io.perfeccionista.framework.pagefactory.filter.WebConditions.displayed;
import static io.perfeccionista.framework.pagefactory.filter.WebConditions.enabled;
import static io.perfeccionista.framework.pagefactory.filter.WebConditions.present;
import static io.perfeccionista.framework.pagefactory.filter.WebConditions.rowIndex;
import static io.perfeccionista.framework.pagefactory.filter.WebConditions.selected;
import static io.perfeccionista.framework.pagefactory.filter.WebFilters.emptyTableFilter;
import static io.perfeccionista.framework.pagefactory.filter.WebFilters.with;
import static io.perfeccionista.framework.pagefactory.filter.WebFilters.without;
import static io.perfeccionista.framework.pagefactory.pageobjects.TablePage.CHECKBOX;
import static io.perfeccionista.framework.pagefactory.pageobjects.TablePage.FULL_NAME;
import static io.perfeccionista.framework.pagefactory.pageobjects.TablePage.NUMBER;
import static io.perfeccionista.framework.pagefactory.pageobjects.TablePage.POPULATION;
import static io.perfeccionista.framework.pagefactory.pageobjects.TablePage.SHORT_NAME;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;

class WebTableElementTest extends AbstractUiTest {

    @Test
    void webTableInitializationTest(Environment env, ValueService value) {
        WebPageFactory pageFactory = new WebPageFactory(new DefaultSeleniumWebElementsConfiguration());
        TablePage tablePage = (TablePage) pageFactory.createWebPage(TablePage.class);
        tablePage.setEnvironment(env);
        tablePage.setWebBrowserDispatcher(mock(WebBrowserDispatcher.class));
        WebTable table = tablePage.table();
        assertNotNull(table.getEnvironment());
        assertNotNull(table.getLocator(ROOT));
        assertNotNull(table.getLocatorChain());
        assertEquals(tablePage, table.getParent());
        WebBrowserDispatcher webBrowserDispatcher = table.getWebBrowserDispatcher();
        assertNotNull(webBrowserDispatcher);
        // WebButton
        assertNotNull(table.getActionImplementation(SCROLL_TO_ELEMENT_METHOD, Void.class));
        assertNotNull(table.getActionImplementation(SIZE_METHOD, Integer.class));
        assertNotNull(table.getActionImplementation(SHOULD_HAVE_SIZE_METHOD, Void.class));
        // WebChildElement
        assertNotNull(table.getActionImplementation(COMPONENT_SHOULD_BE_DISPLAYED_METHOD, Void.class));
        assertNotNull(table.getActionImplementation(COMPONENT_SHOULD_BE_PRESENT_METHOD, Void.class));
        assertNotNull(table.getActionImplementation(COMPONENT_SHOULD_NOT_BE_DISPLAYED_METHOD, Void.class));
        assertNotNull(table.getActionImplementation(COMPONENT_SHOULD_NOT_BE_PRESENT_METHOD, Void.class));
        assertNotNull(table.getActionImplementation(GET_COLOR_METHOD, WebElementColor.class));
        assertNotNull(table.getActionImplementation(GET_DIMENSIONS_METHOD, Dimensions.class));
        assertNotNull(table.getActionImplementation(GET_LOCATION_METHOD, Location.class));
        assertNotNull(table.getActionImplementation(GET_PROPERTY_VALUE_METHOD, String.class));
        assertNotNull(table.getActionImplementation(GET_SCREENSHOT_METHOD, Screenshot.class));
        assertNotNull(table.getActionImplementation(HOVER_TO_METHOD, Void.class));
        assertNotNull(table.getActionImplementation(IS_COMPONENT_DISPLAYED_METHOD, Boolean.class));
        assertNotNull(table.getActionImplementation(IS_COMPONENT_PRESENT_METHOD, Boolean.class));
        assertNotNull(table.getActionImplementation(IS_DISPLAYED_METHOD, Boolean.class));
        assertNotNull(table.getActionImplementation(IS_IN_FOCUS_METHOD, Boolean.class));
        assertNotNull(table.getActionImplementation(IS_PRESENT_METHOD, Boolean.class));
        assertNotNull(table.getActionImplementation(SCROLL_TO_METHOD, Void.class));
        assertNotNull(table.getActionImplementation(SHOULD_BE_DISPLAYED_METHOD, Void.class));
        assertNotNull(table.getActionImplementation(SHOULD_BE_IN_FOCUS_METHOD, Void.class));
        assertNotNull(table.getActionImplementation(SHOULD_BE_PRESENT_METHOD, Void.class));
        assertNotNull(table.getActionImplementation(SHOULD_HAVE_COLOR_METHOD, Void.class));
        assertNotNull(table.getActionImplementation(SHOULD_HAVE_DIMENSIONS_METHOD, Void.class));
        assertNotNull(table.getActionImplementation(SHOULD_HAVE_LOCATION_METHOD, Void.class));
        assertNotNull(table.getActionImplementation(SHOULD_HAVE_PROPERTY_NUMBER_METHOD, Void.class));
        assertNotNull(table.getActionImplementation(SHOULD_HAVE_PROPERTY_VALUE_METHOD, Void.class));
        assertNotNull(table.getActionImplementation(SHOULD_LOOKS_LIKE_METHOD, Void.class));
        assertNotNull(table.getActionImplementation(SHOULD_NOT_BE_DISPLAYED_METHOD, Void.class));
        assertNotNull(table.getActionImplementation(SHOULD_NOT_BE_IN_FOCUS_METHOD, Void.class));
        assertNotNull(table.getActionImplementation(SHOULD_NOT_BE_PRESENT_METHOD, Void.class));
        assertNotNull(table.getActionImplementation(SHOULD_NOT_HAVE_COLOR_METHOD, Void.class));
        assertNotNull(table.getActionImplementation(SHOULD_NOT_HAVE_DIMENSIONS_METHOD, Void.class));
        assertNotNull(table.getActionImplementation(SHOULD_NOT_HAVE_LOCATION_METHOD, Void.class));
        assertNotNull(table.getActionImplementation(SHOULD_NOT_HAVE_PROPERTY_NUMBER_METHOD, Void.class));
        assertNotNull(table.getActionImplementation(SHOULD_NOT_HAVE_PROPERTY_VALUE_METHOD, Void.class));
        assertNotNull(table.getActionImplementation(SHOULD_NOT_LOOKS_LIKE_METHOD, Void.class));
        // Identifier
        WebElementIdentifier elementIdentifier = table.getElementIdentifier();
        assertEquals("table", elementIdentifier.getElementMethod().getName());
        assertEquals("table", elementIdentifier.getLastUsedName());
        assertTrue(elementIdentifier.containsName("Table of countries"));
        assertFalse(elementIdentifier.isNameDeprecated("Table of countries"));
        assertEquals(1, elementIdentifier.names().size());
        WebTable tableElement = tablePage.getElementRegistry().getElementByPath("Table of countries", WebTable.class)
                .orElseThrow(() -> new ElementNotDeclaredException(ELEMENT_NOT_DECLARED.getMessage("Table of countries")));
        assertNotNull(tableElement);
        assertEquals("Table of countries", elementIdentifier.getLastUsedName());
        assertNotNull(table.toString());
        // Смотрим описание элемента
        System.out.println( table.toString() );
    }

    @Test
    void webTablePositiveTest(Environment env, ValueService value) {
        WebPageContext context = initWebPageContext(env, value);
        context.getPage(HomePage.class)
                .leftMenu()
                .select(value.stringEquals("Table Element"));
        TablePage tablePage = context.getPage(TablePage.class);
        WebTable table = tablePage.table();
        table.shouldBePresent()
                .shouldBeDisplayed()
                .shouldNotBeInFocus()
                .scrollTo()
                .hoverTo(true)
                .componentShouldHaveDimensions(ROOT, Dimensions.of(795.0d, 10447.0d).setInaccuracy(0.2d))
                .componentShouldHaveLocation(ROOT, Location.relative(345d, -4472d).setInaccuracy(0.2d))
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
        assertTrue(table.isPresent());
        assertTrue(table.isDisplayed());
        assertFalse(table.isInFocus());
        assertEquals(Dimensions.of(795.0d, 10447.0d).setInaccuracy(0.2d), table.getDimensions(ROOT));
        assertEquals(Location.absolute(345d, 173d).setInaccuracy(0.2d), table.getLocation(ROOT));
        assertEquals(WebElementColor.of(222, 226, 230, 1.0d), table.getColor(ROOT, "border-color"));
        assertEquals(195, table.size());
    }

    @Test
    void webTableNegativeTest(Environment env, ValueService value) {
        WebPageContext context = initWebPageContext(env, value);
        context.getPage(HomePage.class)
                .leftMenu()
                .select(value.stringEquals("Table Element"));
        TablePage tablePage = context.getPage(TablePage.class);
        WebTable table = tablePage.table();
        // Выполнение этого метода показывает завершение загрузки страницы
        table.shouldBeDisplayed();
        // Для негативных сценариев меняем время ожидания, чтобы не ждать по 5 секунд проброса ошибки вне враппера
        env.getEnvironmentConfiguration().getTimeouts().setTimeout(CheckTimeout.class, Duration.ofMillis(100L));
        assertThrows(ElementIsPresentException.class, table::shouldNotBePresent);
        assertThrows(ElementIsDisplayedException.class, table::shouldNotBeDisplayed);
        assertThrows(ElementNotInFocusException.class, table::shouldBeInFocus);
        Dimensions elementDimensions = Dimensions.of(795.0d, 10447.0d).setInaccuracy(0.2d);
        assertThrows(ElementDimensionsException.class, () -> table.componentShouldNotHaveDimensions(ROOT, elementDimensions));
        Location elementLocation = Location.of(345d, 173d, 345d, 173d).setInaccuracy(0.2d);
        assertThrows(ElementLocationException.class, () -> table.componentShouldHaveLocation(ROOT, elementLocation.offset(15d, 1d)));
        assertThrows(ElementLocationException.class, () -> table.componentShouldNotHaveLocation(ROOT, elementLocation));
        WebElementColor elementColor = WebElementColor.of(222, 226, 230, 1.0d);
        assertThrows(ElementColorException.class, () -> table.componentShouldNotHaveColor(ROOT, "border-color", elementColor));
        StringValue correctPropertyValue = value.stringEquals("World map picture");
        assertThrows(ElementPropertyNotDeclaredException.class, () -> table.shouldNotHavePropertyValue("unknown property", correctPropertyValue));
        NumberValue<Integer> expectedSize = value.intEquals(196);
        assertThrows(ElementSizeException.class, () -> table.shouldHaveSize(expectedSize));
    }

    @Test
    void webTableFilterEmptyConditionTest(Environment env, ValueService value) {
        WebPageContext context = initWebPageContext(env, value);
        context.getPage(HomePage.class)
                .leftMenu()
                .select(value.stringEquals("Table Element"));
        TablePage tablePage = context.getPage(TablePage.class);
        WebTable table = tablePage.table()
                .shouldBeDisplayed();

        table.filter(emptyTableFilter())
                .shouldHaveSize(value.intEquals(195));
    }

    @Test
    void webTableFilterRowIndexConditionTest(Environment env, ValueService value) {
        WebPageContext context = initWebPageContext(env, value);
        context.getPage(HomePage.class)
                .leftMenu()
                .select(value.stringEquals("Table Element"));
        TablePage tablePage = context.getPage(TablePage.class);
        WebTable table = tablePage.table()
                .shouldBeDisplayed();

        table.filter(with(rowIndex(value.intGreaterThanOrEqual(100))))
                .shouldHaveSize(value.intEquals(95));
        table.filter(without(rowIndex(value.intGreaterThanOrEqual(100))))
                .shouldHaveSize(value.intEquals(100));
    }


    // TODO: То же самое для поиска элемента по имени
    @Test
    void webTableFilterElementTextConditionTest(Environment env, ValueService value) {
        WebPageContext context = initWebPageContext(env, value);
        context.getPage(HomePage.class)
                .leftMenu()
                .select(value.stringEquals("Table Element"));
        TablePage tablePage = context.getPage(TablePage.class);
        WebTable table = tablePage.table()
                .shouldBeDisplayed();

        table.filter(with(containsText(SHORT_NAME, from(ShortNameWebMappedBlock.class).shortName(), value.stringEquals("Финляндия"))))
                .shouldHaveSize(value.intEquals(1));
        table.filter(with(containsText(SHORT_NAME, from(ShortNameWebMappedBlock.class).shortName(), value.stringStartsWith("М"))))
                .shouldHaveSize(value.intEquals(17));
        table.filter(with(containsText(SHORT_NAME, from(ShortNameWebMappedBlock.class).shortName(), value.stringEquals("Финляндия")).inverse()))
                .shouldHaveSize(value.intEquals(194));
        table.filter(with(containsText(SHORT_NAME, from(ShortNameWebMappedBlock.class).shortName(), value.stringStartsWith("М")).inverse()))
                .shouldHaveSize(value.intEquals(178));

        table.filter(without(containsText(SHORT_NAME, from(ShortNameWebMappedBlock.class).shortName(), value.stringEquals("Финляндия"))))
                .shouldHaveSize(value.intEquals(194));
        table.filter(without(containsText(SHORT_NAME, from(ShortNameWebMappedBlock.class).shortName(), value.stringStartsWith("М"))))
                .shouldHaveSize(value.intEquals(178));
        table.filter(without(containsText(SHORT_NAME, from(ShortNameWebMappedBlock.class).shortName(), value.stringEquals("Финляндия")).inverse()))
                .shouldHaveSize(value.intEquals(1));
        table.filter(without(containsText(SHORT_NAME, from(ShortNameWebMappedBlock.class).shortName(), value.stringStartsWith("М")).inverse()))
                .shouldHaveSize(value.intEquals(17));

        table.filter(with(containsText(NUMBER, from(CountryNumberWebMappedBlock.class).number(), value.intEquals(77))))
                .shouldHaveSize(value.intEquals(1));
        table.filter(with(containsText(NUMBER, from(CountryNumberWebMappedBlock.class).number(), value.intGreaterThanOrEqual(124))))
                .shouldHaveSize(value.intEquals(72));
        table.filter(with(containsText(NUMBER, from(CountryNumberWebMappedBlock.class).number(), value.intEquals(77)).inverse()))
                .shouldHaveSize(value.intEquals(194));
        table.filter(with(containsText(NUMBER, from(CountryNumberWebMappedBlock.class).number(), value.intGreaterThanOrEqual(124)).inverse()))
                .shouldHaveSize(value.intEquals(123));

        table.filter(without(containsText(NUMBER, from(CountryNumberWebMappedBlock.class).number(), value.intEquals(77))))
                .shouldHaveSize(value.intEquals(194));
        table.filter(without(containsText(NUMBER, from(CountryNumberWebMappedBlock.class).number(), value.intGreaterThanOrEqual(124))))
                .shouldHaveSize(value.intEquals(123));
        table.filter(without(containsText(NUMBER, from(CountryNumberWebMappedBlock.class).number(), value.intEquals(77)).inverse()))
                .shouldHaveSize(value.intEquals(1));
        table.filter(without(containsText(NUMBER, from(CountryNumberWebMappedBlock.class).number(), value.intGreaterThanOrEqual(124)).inverse()))
                .shouldHaveSize(value.intEquals(72));
    }

    // TODO: То же самое для поиска элемента по имени
    @Test
    void webTableFilterElementSelectedConditionTest(Environment env, ValueService value) {
        WebPageContext context = initWebPageContext(env, value);
        context.getPage(HomePage.class)
                .leftMenu()
                .select(value.stringEquals("Table Element"));
        TablePage tablePage = context.getPage(TablePage.class);
        WebTable table = tablePage.table()
                .shouldBeDisplayed();

        table.filter(with(selected(CHECKBOX, from(CheckboxWebMappedBlock.class).checkbox())))
                .shouldHaveSize(value.intEquals(6));
        table.filter(with(selected(CHECKBOX, from(CheckboxWebMappedBlock.class).checkbox()).inverse()))
                .shouldHaveSize(value.intEquals(189));

        table.filter(without(selected(CHECKBOX, from(CheckboxWebMappedBlock.class).checkbox())))
                .shouldHaveSize(value.intEquals(189));
        table.filter(without(selected(CHECKBOX, from(CheckboxWebMappedBlock.class).checkbox()).inverse()))
                .shouldHaveSize(value.intEquals(6));
    }

    // TODO: То же самое для поиска элемента по имени
    @Test
    void webTableFilterElementPropertyConditionTest(Environment env, ValueService value) {
        WebPageContext context = initWebPageContext(env, value);
        context.getPage(HomePage.class)
                .leftMenu()
                .select(value.stringEquals("Table Element"));
        TablePage tablePage = context.getPage(TablePage.class);
        WebTable table = tablePage.table()
                .shouldBeDisplayed();

        table.filter(with(containsProperty(FULL_NAME, from(FullNameWebMappedBlock.class).fullName(), "prompt", value.stringEquals("Финляндская Республика"))))
                .shouldHaveSize(value.intEquals(1));
        table.filter(with(containsProperty(FULL_NAME, from(FullNameWebMappedBlock.class).fullName(), "prompt", value.stringStartsWith("М"))))
                .shouldHaveSize(value.intEquals(5));
        table.filter(with(containsProperty(FULL_NAME, from(FullNameWebMappedBlock.class).fullName(), "prompt", value.stringEquals("Финляндская Республика")).inverse()))
                .shouldHaveSize(value.intEquals(194));
        table.filter(with(containsProperty(FULL_NAME, from(FullNameWebMappedBlock.class).fullName(), "prompt", value.stringStartsWith("М")).inverse()))
                .shouldHaveSize(value.intEquals(190));

        table.filter(without(containsProperty(FULL_NAME, from(FullNameWebMappedBlock.class).fullName(), "prompt", value.stringEquals("Финляндская Республика"))))
                .shouldHaveSize(value.intEquals(194));
        table.filter(without(containsProperty(FULL_NAME, from(FullNameWebMappedBlock.class).fullName(), "prompt", value.stringStartsWith("М"))))
                .shouldHaveSize(value.intEquals(190));
        table.filter(without(containsProperty(FULL_NAME, from(FullNameWebMappedBlock.class).fullName(), "prompt", value.stringEquals("Финляндская Республика")).inverse()))
                .shouldHaveSize(value.intEquals(1));
        table.filter(without(containsProperty(FULL_NAME, from(FullNameWebMappedBlock.class).fullName(), "prompt", value.stringStartsWith("М")).inverse()))
                .shouldHaveSize(value.intEquals(5));
    }

    // TODO: То же самое для поиска элемента по имени
    @Test
    void webTableFilterElementPresentConditionTest(Environment env, ValueService value) {
        WebPageContext context = initWebPageContext(env, value);
        context.getPage(HomePage.class)
                .leftMenu()
                .select(value.stringEquals("Table Element"));
        TablePage tablePage = context.getPage(TablePage.class);
        WebTable table = tablePage.table()
                .shouldBeDisplayed();

        table.filter(with(present(SHORT_NAME, from(ShortNameWebMappedBlock.class).shortName())))
                .shouldHaveSize(value.intEquals(193));
        table.filter(with(present(SHORT_NAME, from(ShortNameWebMappedBlock.class).shortName()).inverse()))
                .shouldHaveSize(value.intEquals(2));

        table.filter(without(present(SHORT_NAME, from(ShortNameWebMappedBlock.class).shortName())))
                .shouldHaveSize(value.intEquals(2));
        table.filter(without(present(SHORT_NAME, from(ShortNameWebMappedBlock.class).shortName()).inverse()))
                .shouldHaveSize(value.intEquals(193));
    }

    // TODO: То же самое для поиска элемента по имени
    @Test
    void webTableFilterElementLabelConditionTest(Environment env, ValueService value) {
        WebPageContext context = initWebPageContext(env, value);
        context.getPage(HomePage.class)
                .leftMenu()
                .select(value.stringEquals("Table Element"));
        TablePage tablePage = context.getPage(TablePage.class);
        WebTable table = tablePage.table()
                .shouldBeDisplayed();

        table.filter(with(containsLabel(CHECKBOX, from(CheckboxWebMappedBlock.class).checkbox(), value.stringEquals("86"))))
                .shouldHaveSize(value.intEquals(1));
        table.filter(with(containsLabel(CHECKBOX, from(CheckboxWebMappedBlock.class).checkbox(), value.stringStartsWith("15"))))
                .shouldHaveSize(value.intEquals(11));
        table.filter(with(containsLabel(CHECKBOX, from(CheckboxWebMappedBlock.class).checkbox(), value.stringEquals("86")).inverse()))
                .shouldHaveSize(value.intEquals(194));
        table.filter(with(containsLabel(CHECKBOX, from(CheckboxWebMappedBlock.class).checkbox(), value.stringStartsWith("15")).inverse()))
                .shouldHaveSize(value.intEquals(184));

        table.filter(without(containsLabel(CHECKBOX, from(CheckboxWebMappedBlock.class).checkbox(), value.stringEquals("86"))))
                .shouldHaveSize(value.intEquals(194));
        table.filter(without(containsLabel(CHECKBOX, from(CheckboxWebMappedBlock.class).checkbox(), value.stringStartsWith("15"))))
                .shouldHaveSize(value.intEquals(184));
        table.filter(without(containsLabel(CHECKBOX, from(CheckboxWebMappedBlock.class).checkbox(), value.stringEquals("86")).inverse()))
                .shouldHaveSize(value.intEquals(1));
        table.filter(without(containsLabel(CHECKBOX, from(CheckboxWebMappedBlock.class).checkbox(), value.stringStartsWith("15")).inverse()))
                .shouldHaveSize(value.intEquals(11));

        table.filter(with(containsLabel(CHECKBOX, from(CheckboxWebMappedBlock.class).checkbox(), value.intEquals(77))))
                .shouldHaveSize(value.intEquals(1));
        table.filter(with(containsLabel(CHECKBOX, from(CheckboxWebMappedBlock.class).checkbox(), value.intGreaterThanOrEqual(124))))
                .shouldHaveSize(value.intEquals(72));
        table.filter(with(containsLabel(CHECKBOX, from(CheckboxWebMappedBlock.class).checkbox(), value.intEquals(77)).inverse()))
                .shouldHaveSize(value.intEquals(194));
        table.filter(with(containsLabel(CHECKBOX, from(CheckboxWebMappedBlock.class).checkbox(), value.intGreaterThanOrEqual(124)).inverse()))
                .shouldHaveSize(value.intEquals(123));

        table.filter(without(containsLabel(CHECKBOX, from(CheckboxWebMappedBlock.class).checkbox(), value.intEquals(77))))
                .shouldHaveSize(value.intEquals(194));
        table.filter(without(containsLabel(CHECKBOX, from(CheckboxWebMappedBlock.class).checkbox(), value.intGreaterThanOrEqual(124))))
                .shouldHaveSize(value.intEquals(123));
        table.filter(without(containsLabel(CHECKBOX, from(CheckboxWebMappedBlock.class).checkbox(), value.intEquals(77)).inverse()))
                .shouldHaveSize(value.intEquals(1));
        table.filter(without(containsLabel(CHECKBOX, from(CheckboxWebMappedBlock.class).checkbox(), value.intGreaterThanOrEqual(124)).inverse()))
                .shouldHaveSize(value.intEquals(72));
    }

    // TODO: То же самое для поиска элемента по имени
    @Test
    void webTableFilterElementEnabledConditionTest(Environment env, ValueService value) {
        WebPageContext context = initWebPageContext(env, value);
        context.getPage(HomePage.class)
                .leftMenu()
                .select(value.stringEquals("Table Element"));
        TablePage tablePage = context.getPage(TablePage.class);
        WebTable table = tablePage.table()
                .shouldBeDisplayed();

        table.filter(with(enabled(CHECKBOX, from(CheckboxWebMappedBlock.class).checkbox())))
                .shouldHaveSize(value.intEquals(189));
        table.filter(with(disabled(CHECKBOX, from(CheckboxWebMappedBlock.class).checkbox())))
                .shouldHaveSize(value.intEquals(6));

        table.filter(without(enabled(CHECKBOX, from(CheckboxWebMappedBlock.class).checkbox())))
                .shouldHaveSize(value.intEquals(6));
        table.filter(without(disabled(CHECKBOX, from(CheckboxWebMappedBlock.class).checkbox())))
                .shouldHaveSize(value.intEquals(189));
    }

    // TODO: То же самое для поиска элемента по имени
    @Test
    void webTableFilterElementDisplayedConditionTest(Environment env, ValueService value) {
        WebPageContext context = initWebPageContext(env, value);
        context.getPage(HomePage.class)
                .leftMenu()
                .select(value.stringEquals("Table Element"));
        TablePage tablePage = context.getPage(TablePage.class);
        WebTable table = tablePage.table()
                .shouldBeDisplayed();

        // Для элементов, которых нет в DOM
        table.filter(with(displayed(SHORT_NAME, from(ShortNameWebMappedBlock.class).shortName())))
                .shouldHaveSize(value.intEquals(193));
        table.filter(with(displayed(SHORT_NAME, from(ShortNameWebMappedBlock.class).shortName()).inverse()))
                .shouldHaveSize(value.intEquals(2));

        table.filter(without(displayed(SHORT_NAME, from(ShortNameWebMappedBlock.class).shortName())))
                .shouldHaveSize(value.intEquals(2));
        table.filter(without(displayed(SHORT_NAME, from(ShortNameWebMappedBlock.class).shortName()).inverse()))
                .shouldHaveSize(value.intEquals(193));

        // Для элементов, которые есть в DOM, но не отображаются
        table.filter(with(displayed(POPULATION, from(PopulationWebMappedBlock.class).populationUnit())))
                .shouldHaveSize(value.intEquals(186));
        table.filter(with(displayed(POPULATION, from(PopulationWebMappedBlock.class).populationUnit()).inverse()))
                .shouldHaveSize(value.intEquals(9));

        table.filter(without(displayed(POPULATION, from(PopulationWebMappedBlock.class).populationUnit())))
                .shouldHaveSize(value.intEquals(9));
        table.filter(without(displayed(POPULATION, from(PopulationWebMappedBlock.class).populationUnit()).inverse()))
                .shouldHaveSize(value.intEquals(186));
    }

    // TODO: То же самое для поиска элемента по имени
    @Test
    void webTableFilterElementComponentPresentConditionTest(Environment env, ValueService value) {
        WebPageContext context = initWebPageContext(env, value);
        context.getPage(HomePage.class)
                .leftMenu()
                .select(value.stringEquals("Table Element"));
        TablePage tablePage = context.getPage(TablePage.class);
        WebTable table = tablePage.table()
                .shouldBeDisplayed();

        table.filter(with(componentPresent(SHORT_NAME, from(ShortNameWebMappedBlock.class).shortName(), "Self")))
                .shouldHaveSize(value.intEquals(193));
        table.filter(with(componentPresent(SHORT_NAME, from(ShortNameWebMappedBlock.class).shortName(), "Self").inverse()))
                .shouldHaveSize(value.intEquals(2));

        table.filter(without(componentPresent(SHORT_NAME, from(ShortNameWebMappedBlock.class).shortName(), "Self")))
                .shouldHaveSize(value.intEquals(2));
        table.filter(without(componentPresent(SHORT_NAME, from(ShortNameWebMappedBlock.class).shortName(), "Self").inverse()))
                .shouldHaveSize(value.intEquals(193));

        WebTableFilter filterResult = table
                .filter(with(componentPresent(SHORT_NAME, from(ShortNameWebMappedBlock.class).shortName(), "Unknown")));
        NumberValue<Integer> expectedValue = value.intEquals(200);
        assertThrows(LocatorNotDeclaredException.class, () -> filterResult.shouldHaveSize(expectedValue));
    }

    // TODO: То же самое для поиска элемента по имени
    @Test
    void webTableFilterElementComponentDisplayedConditionTest(Environment env, ValueService value) {
        WebPageContext context = initWebPageContext(env, value);
        context.getPage(HomePage.class)
                .leftMenu()
                .select(value.stringEquals("Table Element"));
        TablePage tablePage = context.getPage(TablePage.class);
        WebTable table = tablePage.table()
                .shouldBeDisplayed();

        table.filter(with(componentDisplayed(POPULATION, from(PopulationWebMappedBlock.class).populationUnit(), "Self")))
                .shouldHaveSize(value.intEquals(186));
        table.filter(with(componentDisplayed(POPULATION, from(PopulationWebMappedBlock.class).populationUnit(), "Self").inverse()))
                .shouldHaveSize(value.intEquals(9));

        table.filter(without(componentDisplayed(POPULATION, from(PopulationWebMappedBlock.class).populationUnit(), "Self")))
                .shouldHaveSize(value.intEquals(9));
        table.filter(without(componentDisplayed(POPULATION, from(PopulationWebMappedBlock.class).populationUnit(), "Self").inverse()))
                .shouldHaveSize(value.intEquals(186));

        WebTableFilter filterResult = table
                .filter(with(componentDisplayed(SHORT_NAME, from(ShortNameWebMappedBlock.class).shortName(), "Unknown")));
        NumberValue<Integer> expectedValue = value.intEquals(200);
        assertThrows(LocatorNotDeclaredException.class, () -> filterResult.shouldHaveSize(expectedValue));
    }

    // Extractors

    @Test
    void webTableExtractorsTest(Environment env, ValueService value) {
        WebPageContext context = initWebPageContext(env, value);
        context.getPage(HomePage.class)
                .leftMenu()
                .select(value.stringEquals("Table Element"));
        TablePage tablePage = context.getPage(TablePage.class);
        WebTable table = tablePage.table()
                .shouldBeDisplayed();




    }

}
