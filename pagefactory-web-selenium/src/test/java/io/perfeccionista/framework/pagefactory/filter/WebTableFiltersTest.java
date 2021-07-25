package io.perfeccionista.framework.pagefactory.filter;

import io.perfeccionista.framework.AbstractWebSeleniumParallelTest;
import io.perfeccionista.framework.exceptions.LocatorNotFound.LocatorNotFoundException;
import io.perfeccionista.framework.pagefactory.dispatcher.context.WebPageContext;
import io.perfeccionista.framework.pagefactory.elements.WebTable;
import io.perfeccionista.framework.pagefactory.filter.block.WebBlockFilter;
import io.perfeccionista.framework.pagefactory.pageobjects.HomePage;
import io.perfeccionista.framework.pagefactory.pageobjects.TablePage;
import io.perfeccionista.framework.pagefactory.pageobjects.TablePage.TableHeader;
import io.perfeccionista.framework.pagefactory.pageobjects.TablePage.TableRow;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static io.perfeccionista.framework.Web.*;
import static io.perfeccionista.framework.pagefactory.pageobjects.TablePage.CHECKBOX;
import static io.perfeccionista.framework.pagefactory.pageobjects.TablePage.FULL_COUNTRY_NAME;
import static io.perfeccionista.framework.pagefactory.pageobjects.TablePage.NUMBER;
import static io.perfeccionista.framework.pagefactory.pageobjects.TablePage.POPULATION;
import static io.perfeccionista.framework.pagefactory.pageobjects.TablePage.SHORT_COUNTRY_NAME;
import static io.perfeccionista.framework.value.Values.intEquals;
import static io.perfeccionista.framework.value.Values.intGreaterThanOrEqual;
import static io.perfeccionista.framework.value.Values.stringEquals;
import static io.perfeccionista.framework.value.Values.stringStartsWith;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@Tag("WebElement") @Tag("WebTable")
class WebTableFiltersTest extends AbstractWebSeleniumParallelTest {

    @Test
    void webTableFilterSizeTest() {
        WebPageContext context = initWebPageContext();
        context.getPage(HomePage.class).leftMenu()
                .select("Table Element");

        TablePage tablePage = context.getPage(TablePage.class);
        WebTable<TableHeader, TableRow> table = tablePage.table()
                .should(beDisplayed());

        assertEquals(95, table.filter(index(intGreaterThanOrEqual(100))).size());
        assertEquals(100, table.filterBuilder(without(index(intGreaterThanOrEqual(100)))).size());
    }

    @Test
    void webTableFilterEmptyConditionTest() {
        WebPageContext context = initWebPageContext();
        context.getPage(HomePage.class).leftMenu()
                .select("Table Element");

        TablePage tablePage = context.getPage(TablePage.class);
        WebTable<TableHeader, TableRow> table = tablePage.table()
                .should(beDisplayed());

        table.filterBuilder(emptyWebBlockFilter())
                .should(haveSize(195));
    }

    @Test
    void webTableFilterRowIndexConditionTest() {
        WebPageContext context = initWebPageContext();
        context.getPage(HomePage.class).leftMenu()
                .select("Table Element");

        TablePage tablePage = context.getPage(TablePage.class);
        WebTable<TableHeader, TableRow> table = tablePage.table()
                .should(beDisplayed());

        table.filter(index(intGreaterThanOrEqual(100)))
                .should(haveSize(95));
        table.filterBuilder(without(index(intGreaterThanOrEqual(100))))
                .should(haveSize(100));
    }

    @Test
    void webTableFilterElementTextConditionTest() {
        WebPageContext context = initWebPageContext();
        context.getPage(HomePage.class).leftMenu()
                .select("Table Element");

        TablePage tablePage = context.getPage(TablePage.class);
        WebTable<TableHeader, TableRow> table = tablePage.table()
                .should(beDisplayed());

        // By Element
        table.filter(row -> containsText(row.shortNameColumn().shortName(), "Финляндия"))
                .should(haveSize(1));
        table.filter(row -> containsText(row.shortNameColumn().shortName(), stringStartsWith("М")))
                .should(haveSize(17));
        table.filter(row -> notContainText(row.shortNameColumn().shortName(), stringEquals("Финляндия")))
                .should(haveSize(194));
        table.filter(row -> notContainText(row.shortNameColumn().shortName(), stringStartsWith("М")))
                .should(haveSize(178));

        table.filterBuilder(row -> without(containsText(row.shortNameColumn().shortName(), "Финляндия")))
                .should(haveSize(194));
        table.filterBuilder(row -> without(containsText(row.shortNameColumn().shortName(), stringStartsWith("М"))))
                .should(haveSize(178));
        table.filterBuilder(row -> without(notContainText(row.shortNameColumn().shortName(), stringEquals("Финляндия"))))
                .should(haveSize(1));
        table.filterBuilder(row -> without(notContainText(row.shortNameColumn().shortName(), stringStartsWith("М"))))
                .should(haveSize(17));

        table.filter(row -> containsText(row.numberColumn().number(), intEquals(77)))
                .should(haveSize(1));
        table.filter(row -> containsText(row.numberColumn().number(), intGreaterThanOrEqual(124)))
                .should(haveSize(72));
        table.filter(row -> notContainText(row.numberColumn().number(), intEquals(77)))
                .should(haveSize(194));
        table.filter(row -> notContainText(row.numberColumn().number(), intGreaterThanOrEqual(124)))
                .should(haveSize(123));

        table.filterBuilder(row -> without(containsText(row.numberColumn().number(), intEquals(77))))
                .should(haveSize(194));
        table.filterBuilder(row -> without(containsText(row.numberColumn().number(), intGreaterThanOrEqual(124))))
                .should(haveSize(123));
        table.filterBuilder(row -> without(notContainText(row.numberColumn().number(), intEquals(77))))
                .should(haveSize(1));
        table.filterBuilder(row -> without(notContainText(row.numberColumn().number(), intGreaterThanOrEqual(124))))
                .should(haveSize(72));

        // By Element name
        table.filter(row -> containsText(SHORT_COUNTRY_NAME + " -> Country name", stringEquals("Финляндия")))
                .should(haveSize(1));
        table.filter(row -> containsText(SHORT_COUNTRY_NAME + " -> Country name", stringStartsWith("М")))
                .should(haveSize(17));
        table.filter(row -> notContainText(SHORT_COUNTRY_NAME + " -> Country name", stringEquals("Финляндия")))
                .should(haveSize(194));
        table.filter(row -> notContainText(SHORT_COUNTRY_NAME + " -> Country name", stringStartsWith("М")))
                .should(haveSize(178));

        table.filterBuilder(row -> without(containsText(SHORT_COUNTRY_NAME + " -> Country name", stringEquals("Финляндия"))))
                .should(haveSize(194));
        table.filterBuilder(row -> without(containsText(SHORT_COUNTRY_NAME + " -> Country name", stringStartsWith("М"))))
                .should(haveSize(178));
        table.filterBuilder(row -> without(notContainText(SHORT_COUNTRY_NAME + " -> Country name", stringEquals("Финляндия"))))
                .should(haveSize(1));
        table.filterBuilder(row -> without(notContainText(SHORT_COUNTRY_NAME + " -> Country name", stringStartsWith("М"))))
                .should(haveSize(17));

        table.filter(row -> containsText(NUMBER + " -> Number", intEquals(77)))
                .should(haveSize(1));
        table.filter(row -> containsText(NUMBER + " -> Number", intGreaterThanOrEqual(124)))
                .should(haveSize(72));
        table.filter(row -> notContainText(NUMBER + " -> Number", intEquals(77)))
                .should(haveSize(194));
        table.filter(row -> notContainText(NUMBER + " -> Number", intGreaterThanOrEqual(124)))
                .should(haveSize(123));

        table.filterBuilder(row -> without(containsText(NUMBER + " -> Number", intEquals(77))))
                .should(haveSize(194));
        table.filterBuilder(row -> without(containsText(NUMBER + " -> Number", intGreaterThanOrEqual(124))))
                .should(haveSize(123));
        table.filterBuilder(row -> without(notContainText(NUMBER + " -> Number", intEquals(77))))
                .should(haveSize(1));
        table.filterBuilder(row -> without(notContainText(NUMBER + " -> Number", intGreaterThanOrEqual(124))))
                .should(haveSize(72));
    }

    @Test
    void webTableFilterElementPropertyConditionTest() {
        WebPageContext context = initWebPageContext();
        context.getPage(HomePage.class).leftMenu()
                .select("Table Element");

        TablePage tablePage = context.getPage(TablePage.class);
        WebTable<TableHeader, TableRow> table = tablePage.table()
                .should(beDisplayed());

        // By Element
        table.filter(row -> containsProperty(row.fullNameColumn().fullName(), "prompt", "Финляндская Республика"))
                .should(haveSize(1));
        table.filter(row -> containsProperty(row.fullNameColumn().fullName(), "prompt", stringStartsWith("М")))
                .should(haveSize(5));
        table.filter(row -> notContainProperty(row.fullNameColumn().fullName(), "prompt", stringEquals("Финляндская Республика")))
                .should(haveSize(194));
        table.filter(row -> notContainProperty(row.fullNameColumn().fullName(), "prompt", stringStartsWith("М")))
                .should(haveSize(190));

        table.filterBuilder(row -> without(containsProperty(row.fullNameColumn().fullName(), "prompt", stringEquals("Финляндская Республика"))))
                .should(haveSize(194));
        table.filterBuilder(row -> without(containsProperty(row.fullNameColumn().fullName(), "prompt", stringStartsWith("М"))))
                .should(haveSize(190));
        table.filterBuilder(row -> without(notContainProperty(row.fullNameColumn().fullName(), "prompt", stringEquals("Финляндская Республика"))))
                .should(haveSize(1));
        table.filterBuilder(row -> without(notContainProperty(row.fullNameColumn().fullName(), "prompt", stringStartsWith("М"))))
                .should(haveSize(5));

        // By Element name
        table.filter(row -> containsProperty(FULL_COUNTRY_NAME + " -> Full country name", "prompt", stringEquals("Финляндская Республика")))
                .should(haveSize(1));
        table.filter(row -> containsProperty(FULL_COUNTRY_NAME + " -> Full country name", "prompt", stringStartsWith("М")))
                .should(haveSize(5));
        table.filter(row -> notContainProperty(FULL_COUNTRY_NAME + " -> Full country name", "prompt", stringEquals("Финляндская Республика")))
                .should(haveSize(194));
        table.filter(row -> notContainProperty(FULL_COUNTRY_NAME + " -> Full country name", "prompt", stringStartsWith("М")))
                .should(haveSize(190));

        table.filterBuilder(row -> without(containsProperty(FULL_COUNTRY_NAME + " -> Full country name", "prompt", stringEquals("Финляндская Республика"))))
                .should(haveSize(194));
        table.filterBuilder(row -> without(containsProperty(FULL_COUNTRY_NAME + " -> Full country name", "prompt", stringStartsWith("М"))))
                .should(haveSize(190));
        table.filterBuilder(row -> without(notContainProperty(FULL_COUNTRY_NAME + " -> Full country name", "prompt", stringEquals("Финляндская Республика"))))
                .should(haveSize(1));
        table.filterBuilder(row -> without(notContainProperty(FULL_COUNTRY_NAME + " -> Full country name", "prompt", stringStartsWith("М"))))
                .should(haveSize(5));
    }

    @Test
    void webTableFilterElementLabelConditionTest() {
        WebPageContext context = initWebPageContext();
        context.getPage(HomePage.class).leftMenu()
                .select("Table Element");

        TablePage tablePage = context.getPage(TablePage.class);
        WebTable<TableHeader, TableRow> table = tablePage.table()
                .should(beDisplayed());

        // By Element
        table.filter(row -> containsLabel(row.checkboxColumn().checkbox(), stringEquals("86")))
                .should(haveSize(1));
        table.filter(row -> containsLabel(row.checkboxColumn().checkbox(), stringStartsWith("15")))
                .should(haveSize(11));
        table.filter(row -> notContainLabel(row.checkboxColumn().checkbox(), stringEquals("86")))
                .should(haveSize(194));
        table.filter(row -> notContainLabel(row.checkboxColumn().checkbox(), stringStartsWith("15")))
                .should(haveSize(184));

        table.filterBuilder(row -> without(containsLabel(row.checkboxColumn().checkbox(), stringEquals("86"))))
                .should(haveSize(194));
        table.filterBuilder(row -> without(containsLabel(row.checkboxColumn().checkbox(), stringStartsWith("15"))))
                .should(haveSize(184));
        table.filterBuilder(row -> without(notContainLabel(row.checkboxColumn().checkbox(), stringEquals("86"))))
                .should(haveSize(1));
        table.filterBuilder(row -> without(notContainLabel(row.checkboxColumn().checkbox(), stringStartsWith("15"))))
                .should(haveSize(11));

        table.filter(row -> containsLabel(row.checkboxColumn().checkbox(), intEquals(77)))
                .should(haveSize(1));
        table.filter(row -> containsLabel(row.checkboxColumn().checkbox(), intGreaterThanOrEqual(124)))
                .should(haveSize(72));
        table.filter(row -> notContainLabel(row.checkboxColumn().checkbox(), intEquals(77)))
                .should(haveSize(194));
        table.filter(row -> notContainLabel(row.checkboxColumn().checkbox(), intGreaterThanOrEqual(124)))
                .should(haveSize(123));

        table.filterBuilder(row -> without(containsLabel(row.checkboxColumn().checkbox(), intEquals(77))))
                .should(haveSize(194));
        table.filterBuilder(row -> without(containsLabel(row.checkboxColumn().checkbox(), intGreaterThanOrEqual(124))))
                .should(haveSize(123));
        table.filterBuilder(row -> without(notContainLabel(row.checkboxColumn().checkbox(), intEquals(77))))
                .should(haveSize(1));
        table.filterBuilder(row -> without(notContainLabel(row.checkboxColumn().checkbox(), intGreaterThanOrEqual(124))))
                .should(haveSize(72));

        // By Element name
        table.filter(row -> containsLabel(CHECKBOX + " -> Select", stringEquals("86")))
                .should(haveSize(1));
        table.filter(row -> containsLabel(CHECKBOX + " -> Select", stringStartsWith("15")))
                .should(haveSize(11));
        table.filter(row -> notContainLabel(CHECKBOX + " -> Select", stringEquals("86")))
                .should(haveSize(194));
        table.filter(row -> notContainLabel(CHECKBOX + " -> Select", stringStartsWith("15")))
                .should(haveSize(184));

        table.filterBuilder(row -> without(containsLabel(CHECKBOX + " -> Select", stringEquals("86"))))
                .should(haveSize(194));
        table.filterBuilder(row -> without(containsLabel(CHECKBOX + " -> Select", stringStartsWith("15"))))
                .should(haveSize(184));
        table.filterBuilder(row -> without(notContainLabel(CHECKBOX + " -> Select", stringEquals("86"))))
                .should(haveSize(1));
        table.filterBuilder(row -> without(notContainLabel(CHECKBOX + " -> Select", stringStartsWith("15"))))
                .should(haveSize(11));

        table.filter(row -> containsLabel(CHECKBOX + " -> Select", intEquals(77)))
                .should(haveSize(1));
        table.filter(row -> containsLabel(CHECKBOX + " -> Select", intGreaterThanOrEqual(124)))
                .should(haveSize(72));
        table.filter(row -> notContainLabel(CHECKBOX + " -> Select", intEquals(77)))
                .should(haveSize(194));
        table.filter(row -> notContainLabel(CHECKBOX + " -> Select", intGreaterThanOrEqual(124)))
                .should(haveSize(123));

        table.filterBuilder(row -> without(containsLabel(CHECKBOX + " -> Select", intEquals(77))))
                .should(haveSize(194));
        table.filterBuilder(row -> without(containsLabel(CHECKBOX + " -> Select", intGreaterThanOrEqual(124))))
                .should(haveSize(123));
        table.filterBuilder(row -> without(notContainLabel(CHECKBOX + " -> Select", intEquals(77))))
                .should(haveSize(1));
        table.filterBuilder(row -> without(notContainLabel(CHECKBOX + " -> Select", intGreaterThanOrEqual(124))))
                .should(haveSize(72));
    }

    @Test
    void webTableFilterElementEnabledConditionTest() {
        WebPageContext context = initWebPageContext();
        context.getPage(HomePage.class).leftMenu()
                .select("Table Element");

        TablePage tablePage = context.getPage(TablePage.class);
        WebTable<TableHeader, TableRow> table = tablePage.table()
                .should(beDisplayed());

        // By Element
        table.filter(row -> enabled(row.checkboxColumn().checkbox()))
                .should(haveSize(189));
        table.filter(row -> disabled(row.checkboxColumn().checkbox()))
                .should(haveSize(6));

        table.filterBuilder(row -> without(enabled(row.checkboxColumn().checkbox())))
                .should(haveSize(6));
        table.filterBuilder(row -> without(disabled(row.checkboxColumn().checkbox())))
                .should(haveSize(189));

        // By Element name
        table.filter(row -> enabled(CHECKBOX + " -> Select"))
                .should(haveSize(189));
        table.filter(row -> disabled(CHECKBOX + " -> Select"))
                .should(haveSize(6));

        table.filterBuilder(row -> without(enabled(CHECKBOX + " -> Select")))
                .should(haveSize(6));
        table.filterBuilder(row -> without(disabled(CHECKBOX + " -> Select")))
                .should(haveSize(189));
    }

    @Test
    void webTableFilterElementSelectedConditionTest() {
        WebPageContext context = initWebPageContext();
        context.getPage(HomePage.class).leftMenu()
                .select("Table Element");

        TablePage tablePage = context.getPage(TablePage.class);
        WebTable<TableHeader, TableRow> table = tablePage.table()
                .should(beDisplayed());

        // By Element
        table.filter(row -> selected(row.checkboxColumn().checkbox()))
                .should(haveSize(6));
        table.filter(row -> notSelected(row.checkboxColumn().checkbox()))
                .should(haveSize(189));

        table.filterBuilder(row -> without(selected(row.checkboxColumn().checkbox())))
                .should(haveSize(189));
        table.filterBuilder(row -> without(notSelected(row.checkboxColumn().checkbox())))
                .should(haveSize(6));

        // By Element name
        table.filter(row -> selected(CHECKBOX + " -> Select"))
                .should(haveSize(6));
        table.filter(row -> notSelected(CHECKBOX + " -> Select"))
                .should(haveSize(189));

        table.filterBuilder(row -> without(selected(CHECKBOX + " -> Select")))
                .should(haveSize(189));
        table.filterBuilder(row -> without(notSelected(CHECKBOX + " -> Select")))
                .should(haveSize(6));
    }

    @Test
    void webTableFilterElementPresentConditionTest() {
        WebPageContext context = initWebPageContext();
        context.getPage(HomePage.class).leftMenu()
                .select("Table Element");

        TablePage tablePage = context.getPage(TablePage.class);
        WebTable<TableHeader, TableRow> table = tablePage.table()
                .should(beDisplayed());

        // By Element
        table.filter(row -> present(row.shortNameColumn().shortName()))
                .should(haveSize(193));
        table.filter(row -> notPresent(row.shortNameColumn().shortName()))
                .should(haveSize(2));

        table.filterBuilder(row -> without(present(row.shortNameColumn().shortName())))
                .should(haveSize(2));
        table.filterBuilder(row -> without(notPresent(row.shortNameColumn().shortName())))
                .should(haveSize(193));

        // By Element name
        table.filter(row -> present(SHORT_COUNTRY_NAME + " -> Country name"))
                .should(haveSize(193));
        table.filter(row -> notPresent(SHORT_COUNTRY_NAME + " -> Country name"))
                .should(haveSize(2));

        table.filterBuilder(row -> without(present(SHORT_COUNTRY_NAME + " -> Country name")))
                .should(haveSize(2));
        table.filterBuilder(row -> without(notPresent(SHORT_COUNTRY_NAME + " -> Country name")))
                .should(haveSize(193));
    }

    @Test
    void webTableFilterElementDisplayedConditionTest() {
        WebPageContext context = initWebPageContext();
        context.getPage(HomePage.class).leftMenu()
                .select("Table Element");

        TablePage tablePage = context.getPage(TablePage.class);
        WebTable<TableHeader, TableRow> table = tablePage.table()
                .should(beDisplayed());

        // By Element
        // Для элементов, которых нет в DOM
        table.filter(row -> displayed(row.shortNameColumn().shortName()))
                .should(haveSize(193));
        table.filter(row -> notDisplayed(row.shortNameColumn().shortName()))
                .should(haveSize(2));

        table.filterBuilder(row -> without(displayed(row.shortNameColumn().shortName())))
                .should(haveSize(2));
        table.filterBuilder(row -> without(notDisplayed(row.shortNameColumn().shortName())))
                .should(haveSize(193));

        // Для элементов, которые есть в DOM, но не отображаются
        table.filter(row -> displayed(row.populationColumn().populationUnit()))
                .should(haveSize(186));
        table.filter(row -> notDisplayed(row.populationColumn().populationUnit()))
                .should(haveSize(9));

        table.filterBuilder(row -> without(displayed(row.populationColumn().populationUnit())))
                .should(haveSize(9));
        table.filterBuilder(row -> without(notDisplayed(row.populationColumn().populationUnit())))
                .should(haveSize(186));

        // By Element name
        // Для элементов, которых нет в DOM
        table.filter(row -> displayed(SHORT_COUNTRY_NAME + " -> Country name"))
                .should(haveSize(193));
        table.filter(row -> notDisplayed(SHORT_COUNTRY_NAME + " -> Country name"))
                .should(haveSize(2));

        table.filterBuilder(row -> without(displayed(SHORT_COUNTRY_NAME + " -> Country name")))
                .should(haveSize(2));
        table.filterBuilder(row -> without(notDisplayed(SHORT_COUNTRY_NAME + " -> Country name")))
                .should(haveSize(193));

        // Для элементов, которые есть в DOM, но не отображаются
        table.filter(row -> displayed(POPULATION + " -> Population unit"))
                .should(haveSize(186));
        table.filter(row -> notDisplayed(POPULATION + " -> Population unit"))
                .should(haveSize(9));

        table.filterBuilder(row -> without(displayed(POPULATION + " -> Population unit")))
                .should(haveSize(9));
        table.filterBuilder(row -> without(notDisplayed(POPULATION + " -> Population unit")))
                .should(haveSize(186));
    }

    @Test
    void webTableFilterElementComponentPresentConditionTest() {
        WebPageContext context = initWebPageContext();
        context.getPage(HomePage.class).leftMenu()
                .select("Table Element");

        TablePage tablePage = context.getPage(TablePage.class);
        WebTable<TableHeader, TableRow> table = tablePage.table()
                .should(beDisplayed());

        // By Element
        table.filter(row -> componentPresent(row.shortNameColumn().shortName(), "Self"))
                .should(haveSize(193));
        table.filter(row -> componentNotPresent(row.shortNameColumn().shortName(), "Self"))
                .should(haveSize(2));

        table.filterBuilder(row -> without(componentPresent(row.shortNameColumn().shortName(), "Self")))
                .should(haveSize(2));
        table.filterBuilder(row -> without(componentNotPresent(row.shortNameColumn().shortName(), "Self")))
                .should(haveSize(193));

        // By Element name
        table.filter(row -> componentPresent(SHORT_COUNTRY_NAME + " -> Country name", "Self"))
                .should(haveSize(193));
        table.filter(row -> componentNotPresent(SHORT_COUNTRY_NAME + " -> Country name", "Self"))
                .should(haveSize(2));

        table.filterBuilder(row -> without(componentPresent(SHORT_COUNTRY_NAME + " -> Country name", "Self")))
                .should(haveSize(2));
        table.filterBuilder(row -> without(componentNotPresent(SHORT_COUNTRY_NAME + " -> Country name", "Self")))
                .should(haveSize(193));

        WebBlockFilter<TableRow> filter = table
                .filter(row -> componentPresent(row.shortNameColumn().shortName(), "Unknown"));
        assertThrows(LocatorNotFoundException.class, () -> filter.should(haveSize(200)));
    }

    @Test
    void webTableFilterElementComponentDisplayedConditionTest() {
        WebPageContext context = initWebPageContext();
        context.getPage(HomePage.class).leftMenu()
                .select("Table Element");

        TablePage tablePage = context.getPage(TablePage.class);
        WebTable<TableHeader, TableRow> table = tablePage.table()
                .should(beDisplayed());

        // By Element
        table.filter(row -> componentDisplayed(row.populationColumn().populationUnit(), "Self"))
                .should(haveSize(186));
        table.filter(row -> componentNotDisplayed(row.populationColumn().populationUnit(), "Self"))
                .should(haveSize(9));

        table.filterBuilder(row -> without(componentDisplayed(row.populationColumn().populationUnit(), "Self")))
                .should(haveSize(9));
        table.filterBuilder(row -> without(componentNotDisplayed(row.populationColumn().populationUnit(), "Self")))
                .should(haveSize(186));

        // By Element name
        table.filter(row -> componentDisplayed(POPULATION + " -> Population unit", "Self"))
                .should(haveSize(186));
        table.filter(row -> componentNotDisplayed(POPULATION + " -> Population unit", "Self"))
                .should(haveSize(9));

        table.filterBuilder(row -> without(componentDisplayed(POPULATION + " -> Population unit", "Self")))
                .should(haveSize(9));
        table.filterBuilder(row -> without(componentNotDisplayed(POPULATION + " -> Population unit", "Self")))
                .should(haveSize(186));

        WebBlockFilter<TableRow> filter = table
                .filter(row -> componentDisplayed(row.shortNameColumn().shortName(), "Unknown"));
        assertThrows(LocatorNotFoundException.class, () -> filter.should(haveSize(200)));
    }

}
