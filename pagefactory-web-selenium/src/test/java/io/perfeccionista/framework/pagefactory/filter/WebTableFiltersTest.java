package io.perfeccionista.framework.pagefactory.filter;

import io.perfeccionista.framework.exceptions.WebLocatorNotFound.WebLocatorNotFoundException;
import io.perfeccionista.framework.AbstractWebSeleniumParallelTest;
import io.perfeccionista.framework.pagefactory.context.base.WebPageContext;
import io.perfeccionista.framework.pagefactory.elements.WebTable;
import io.perfeccionista.framework.pagefactory.filter.table.WebTableFilter;
import io.perfeccionista.framework.pagefactory.pageobjects.HomePage;
import io.perfeccionista.framework.pagefactory.pageobjects.TablePage;
import io.perfeccionista.framework.pagefactory.pageobjects.blocks.table.CheckboxWebBlock;
import io.perfeccionista.framework.pagefactory.pageobjects.blocks.table.CountryNumberWebBlock;
import io.perfeccionista.framework.pagefactory.pageobjects.blocks.table.FullCountryNameWebBlock;
import io.perfeccionista.framework.pagefactory.pageobjects.blocks.table.PopulationWebBlock;
import io.perfeccionista.framework.pagefactory.pageobjects.blocks.table.CountryNameWebBlock;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static io.perfeccionista.framework.matcher.WebElementAssertions.beDisplayed;
import static io.perfeccionista.framework.matcher.WebMultipleResultAssertions.haveSize;
import static io.perfeccionista.framework.pagefactory.elements.WebBlock.frame;
import static io.perfeccionista.framework.pagefactory.filter.WebFilterConditions.componentDisplayed;
import static io.perfeccionista.framework.pagefactory.filter.WebFilterConditions.componentNotDisplayed;
import static io.perfeccionista.framework.pagefactory.filter.WebFilterConditions.componentNotPresent;
import static io.perfeccionista.framework.pagefactory.filter.WebFilterConditions.componentPresent;
import static io.perfeccionista.framework.pagefactory.filter.WebFilterConditions.containsLabel;
import static io.perfeccionista.framework.pagefactory.filter.WebFilterConditions.containsProperty;
import static io.perfeccionista.framework.pagefactory.filter.WebFilterConditions.containsText;
import static io.perfeccionista.framework.pagefactory.filter.WebFilterConditions.disabled;
import static io.perfeccionista.framework.pagefactory.filter.WebFilterConditions.displayed;
import static io.perfeccionista.framework.pagefactory.filter.WebFilterConditions.enabled;
import static io.perfeccionista.framework.pagefactory.filter.WebFilterConditions.notContainLabel;
import static io.perfeccionista.framework.pagefactory.filter.WebFilterConditions.notContainProperty;
import static io.perfeccionista.framework.pagefactory.filter.WebFilterConditions.notContainText;
import static io.perfeccionista.framework.pagefactory.filter.WebFilterConditions.notDisplayed;
import static io.perfeccionista.framework.pagefactory.filter.WebFilterConditions.notPresent;
import static io.perfeccionista.framework.pagefactory.filter.WebFilterConditions.notSelected;
import static io.perfeccionista.framework.pagefactory.filter.WebFilterConditions.present;
import static io.perfeccionista.framework.pagefactory.filter.WebFilterConditions.rowIndex;
import static io.perfeccionista.framework.pagefactory.filter.WebFilterConditions.selected;
import static io.perfeccionista.framework.pagefactory.filter.WebFilters.emptyWebTableFilter;
import static io.perfeccionista.framework.pagefactory.filter.WebFilters.with;
import static io.perfeccionista.framework.pagefactory.filter.WebFilters.without;
import static io.perfeccionista.framework.pagefactory.pageobjects.TablePage.CHECKBOX;
import static io.perfeccionista.framework.pagefactory.pageobjects.TablePage.FULL_COUNTRY_NAME;
import static io.perfeccionista.framework.pagefactory.pageobjects.TablePage.NUMBER;
import static io.perfeccionista.framework.pagefactory.pageobjects.TablePage.POPULATION;
import static io.perfeccionista.framework.pagefactory.pageobjects.TablePage.SHORT_COUNTRY_NAME;
import static io.perfeccionista.framework.value.Values.intEquals;
import static io.perfeccionista.framework.value.Values.intGreaterThanOrEqual;
import static io.perfeccionista.framework.value.Values.stringEquals;
import static io.perfeccionista.framework.value.Values.stringStartsWith;
import static org.junit.jupiter.api.Assertions.assertThrows;

@Tag("WebElement") @Tag("WebTable")
class WebTableFiltersTest extends AbstractWebSeleniumParallelTest {

    @Test
    void webTableFilterEmptyConditionTest() {
        WebPageContext context = initWebPageContext();
        context.getPage(HomePage.class).leftMenu()
                .select("Table Element");

        TablePage tablePage = context.getPage(TablePage.class);
        WebTable table = tablePage.table()
                .should(beDisplayed());

        table.filter(emptyWebTableFilter())
                .should(haveSize(195));
    }

    @Test
    void webTableFilterRowIndexConditionTest() {
        WebPageContext context = initWebPageContext();
        context.getPage(HomePage.class).leftMenu()
                .select("Table Element");

        TablePage tablePage = context.getPage(TablePage.class);
        WebTable table = tablePage.table()
                .should(beDisplayed());

        table.filter(with(rowIndex(intGreaterThanOrEqual(100))))
                .should(haveSize(95));
        table.filter(without(rowIndex(intGreaterThanOrEqual(100))))
                .should(haveSize(100));
    }

    @Test
    void webTableFilterElementTextConditionTest() {
        WebPageContext context = initWebPageContext();
        context.getPage(HomePage.class).leftMenu()
                .select("Table Element");

        TablePage tablePage = context.getPage(TablePage.class);
        WebTable table = tablePage.table()
                .should(beDisplayed());

        // By Element
        table.filter(with(containsText(SHORT_COUNTRY_NAME, frame(CountryNameWebBlock.class).shortName(), "Финляндия")))
                .should(haveSize(1));
        table.filter(with(containsText(SHORT_COUNTRY_NAME, frame(CountryNameWebBlock.class).shortName(), stringStartsWith("М"))))
                .should(haveSize(17));
        table.filter(with(notContainText(SHORT_COUNTRY_NAME, frame(CountryNameWebBlock.class).shortName(), stringEquals("Финляндия"))))
                .should(haveSize(194));
        table.filter(with(notContainText(SHORT_COUNTRY_NAME, frame(CountryNameWebBlock.class).shortName(), stringStartsWith("М"))))
                .should(haveSize(178));

        table.filter(without(containsText(SHORT_COUNTRY_NAME, frame(CountryNameWebBlock.class).shortName(), "Финляндия")))
                .should(haveSize(194));
        table.filter(without(containsText(SHORT_COUNTRY_NAME, frame(CountryNameWebBlock.class).shortName(), stringStartsWith("М"))))
                .should(haveSize(178));
        table.filter(without(notContainText(SHORT_COUNTRY_NAME, frame(CountryNameWebBlock.class).shortName(), stringEquals("Финляндия"))))
                .should(haveSize(1));
        table.filter(without(notContainText(SHORT_COUNTRY_NAME, frame(CountryNameWebBlock.class).shortName(), stringStartsWith("М"))))
                .should(haveSize(17));

        table.filter(with(containsText(NUMBER, frame(CountryNumberWebBlock.class).number(), intEquals(77))))
                .should(haveSize(1));
        table.filter(with(containsText(NUMBER, frame(CountryNumberWebBlock.class).number(), intGreaterThanOrEqual(124))))
                .should(haveSize(72));
        table.filter(with(notContainText(NUMBER, frame(CountryNumberWebBlock.class).number(), intEquals(77))))
                .should(haveSize(194));
        table.filter(with(notContainText(NUMBER, frame(CountryNumberWebBlock.class).number(), intGreaterThanOrEqual(124))))
                .should(haveSize(123));

        table.filter(without(containsText(NUMBER, frame(CountryNumberWebBlock.class).number(), intEquals(77))))
                .should(haveSize(194));
        table.filter(without(containsText(NUMBER, frame(CountryNumberWebBlock.class).number(), intGreaterThanOrEqual(124))))
                .should(haveSize(123));
        table.filter(without(notContainText(NUMBER, frame(CountryNumberWebBlock.class).number(), intEquals(77))))
                .should(haveSize(1));
        table.filter(without(notContainText(NUMBER, frame(CountryNumberWebBlock.class).number(), intGreaterThanOrEqual(124))))
                .should(haveSize(72));

        // By Element name
        table.filter(with(containsText(SHORT_COUNTRY_NAME, "Country name", stringEquals("Финляндия"))))
                .should(haveSize(1));
        table.filter(with(containsText(SHORT_COUNTRY_NAME, "Country name", stringStartsWith("М"))))
                .should(haveSize(17));
        table.filter(with(notContainText(SHORT_COUNTRY_NAME, "Country name", stringEquals("Финляндия"))))
                .should(haveSize(194));
        table.filter(with(notContainText(SHORT_COUNTRY_NAME, "Country name", stringStartsWith("М"))))
                .should(haveSize(178));

        table.filter(without(containsText(SHORT_COUNTRY_NAME, "Country name", stringEquals("Финляндия"))))
                .should(haveSize(194));
        table.filter(without(containsText(SHORT_COUNTRY_NAME, "Country name", stringStartsWith("М"))))
                .should(haveSize(178));
        table.filter(without(notContainText(SHORT_COUNTRY_NAME, "Country name", stringEquals("Финляндия"))))
                .should(haveSize(1));
        table.filter(without(notContainText(SHORT_COUNTRY_NAME, "Country name", stringStartsWith("М"))))
                .should(haveSize(17));

        table.filter(with(containsText(NUMBER, "Number", intEquals(77))))
                .should(haveSize(1));
        table.filter(with(containsText(NUMBER, "Number", intGreaterThanOrEqual(124))))
                .should(haveSize(72));
        table.filter(with(notContainText(NUMBER, "Number", intEquals(77))))
                .should(haveSize(194));
        table.filter(with(notContainText(NUMBER, "Number", intGreaterThanOrEqual(124))))
                .should(haveSize(123));

        table.filter(without(containsText(NUMBER, "Number", intEquals(77))))
                .should(haveSize(194));
        table.filter(without(containsText(NUMBER, "Number", intGreaterThanOrEqual(124))))
                .should(haveSize(123));
        table.filter(without(notContainText(NUMBER, "Number", intEquals(77))))
                .should(haveSize(1));
        table.filter(without(notContainText(NUMBER, "Number", intGreaterThanOrEqual(124))))
                .should(haveSize(72));
    }

    @Test
    void webTableFilterElementPropertyConditionTest() {
        WebPageContext context = initWebPageContext();
        context.getPage(HomePage.class).leftMenu()
                .select("Table Element");

        TablePage tablePage = context.getPage(TablePage.class);
        WebTable table = tablePage.table()
                .should(beDisplayed());

        // By Element
        table.filter(with(containsProperty(FULL_COUNTRY_NAME, frame(FullCountryNameWebBlock.class).fullName(), "prompt", "Финляндская Республика")))
                .should(haveSize(1));
        table.filter(with(containsProperty(FULL_COUNTRY_NAME, frame(FullCountryNameWebBlock.class).fullName(), "prompt", stringStartsWith("М"))))
                .should(haveSize(5));
        table.filter(with(notContainProperty(FULL_COUNTRY_NAME, frame(FullCountryNameWebBlock.class).fullName(), "prompt", stringEquals("Финляндская Республика"))))
                .should(haveSize(194));
        table.filter(with(notContainProperty(FULL_COUNTRY_NAME, frame(FullCountryNameWebBlock.class).fullName(), "prompt", stringStartsWith("М"))))
                .should(haveSize(190));

        table.filter(without(containsProperty(FULL_COUNTRY_NAME, frame(FullCountryNameWebBlock.class).fullName(), "prompt", stringEquals("Финляндская Республика"))))
                .should(haveSize(194));
        table.filter(without(containsProperty(FULL_COUNTRY_NAME, frame(FullCountryNameWebBlock.class).fullName(), "prompt", stringStartsWith("М"))))
                .should(haveSize(190));
        table.filter(without(notContainProperty(FULL_COUNTRY_NAME, frame(FullCountryNameWebBlock.class).fullName(), "prompt", stringEquals("Финляндская Республика"))))
                .should(haveSize(1));
        table.filter(without(notContainProperty(FULL_COUNTRY_NAME, frame(FullCountryNameWebBlock.class).fullName(), "prompt", stringStartsWith("М"))))
                .should(haveSize(5));

        // By Element name
        table.filter(with(containsProperty(FULL_COUNTRY_NAME, "Full country name", "prompt", stringEquals("Финляндская Республика"))))
                .should(haveSize(1));
        table.filter(with(containsProperty(FULL_COUNTRY_NAME, "Full country name", "prompt", stringStartsWith("М"))))
                .should(haveSize(5));
        table.filter(with(notContainProperty(FULL_COUNTRY_NAME, "Full country name", "prompt", stringEquals("Финляндская Республика"))))
                .should(haveSize(194));
        table.filter(with(notContainProperty(FULL_COUNTRY_NAME, "Full country name", "prompt", stringStartsWith("М"))))
                .should(haveSize(190));

        table.filter(without(containsProperty(FULL_COUNTRY_NAME, "Full country name", "prompt", stringEquals("Финляндская Республика"))))
                .should(haveSize(194));
        table.filter(without(containsProperty(FULL_COUNTRY_NAME, "Full country name", "prompt", stringStartsWith("М"))))
                .should(haveSize(190));
        table.filter(without(notContainProperty(FULL_COUNTRY_NAME, "Full country name", "prompt", stringEquals("Финляндская Республика"))))
                .should(haveSize(1));
        table.filter(without(notContainProperty(FULL_COUNTRY_NAME, "Full country name", "prompt", stringStartsWith("М"))))
                .should(haveSize(5));
    }

    @Test
    void webTableFilterElementLabelConditionTest() {
        WebPageContext context = initWebPageContext();
        context.getPage(HomePage.class).leftMenu()
                .select("Table Element");

        TablePage tablePage = context.getPage(TablePage.class);
        WebTable table = tablePage.table()
                .should(beDisplayed());

        // By Element
        table.filter(with(containsLabel(CHECKBOX, frame(CheckboxWebBlock.class).checkbox(), stringEquals("86"))))
                .should(haveSize(1));
        table.filter(with(containsLabel(CHECKBOX, frame(CheckboxWebBlock.class).checkbox(), stringStartsWith("15"))))
                .should(haveSize(11));
        table.filter(with(notContainLabel(CHECKBOX, frame(CheckboxWebBlock.class).checkbox(), stringEquals("86"))))
                .should(haveSize(194));
        table.filter(with(notContainLabel(CHECKBOX, frame(CheckboxWebBlock.class).checkbox(), stringStartsWith("15"))))
                .should(haveSize(184));

        table.filter(without(containsLabel(CHECKBOX, frame(CheckboxWebBlock.class).checkbox(), stringEquals("86"))))
                .should(haveSize(194));
        table.filter(without(containsLabel(CHECKBOX, frame(CheckboxWebBlock.class).checkbox(), stringStartsWith("15"))))
                .should(haveSize(184));
        table.filter(without(notContainLabel(CHECKBOX, frame(CheckboxWebBlock.class).checkbox(), stringEquals("86"))))
                .should(haveSize(1));
        table.filter(without(notContainLabel(CHECKBOX, frame(CheckboxWebBlock.class).checkbox(), stringStartsWith("15"))))
                .should(haveSize(11));

        table.filter(with(containsLabel(CHECKBOX, frame(CheckboxWebBlock.class).checkbox(), intEquals(77))))
                .should(haveSize(1));
        table.filter(with(containsLabel(CHECKBOX, frame(CheckboxWebBlock.class).checkbox(), intGreaterThanOrEqual(124))))
                .should(haveSize(72));
        table.filter(with(notContainLabel(CHECKBOX, frame(CheckboxWebBlock.class).checkbox(), intEquals(77))))
                .should(haveSize(194));
        table.filter(with(notContainLabel(CHECKBOX, frame(CheckboxWebBlock.class).checkbox(), intGreaterThanOrEqual(124))))
                .should(haveSize(123));

        table.filter(without(containsLabel(CHECKBOX, frame(CheckboxWebBlock.class).checkbox(), intEquals(77))))
                .should(haveSize(194));
        table.filter(without(containsLabel(CHECKBOX, frame(CheckboxWebBlock.class).checkbox(), intGreaterThanOrEqual(124))))
                .should(haveSize(123));
        table.filter(without(notContainLabel(CHECKBOX, frame(CheckboxWebBlock.class).checkbox(), intEquals(77))))
                .should(haveSize(1));
        table.filter(without(notContainLabel(CHECKBOX, frame(CheckboxWebBlock.class).checkbox(), intGreaterThanOrEqual(124))))
                .should(haveSize(72));

        // By Element name
        table.filter(with(containsLabel(CHECKBOX, "Select", stringEquals("86"))))
                .should(haveSize(1));
        table.filter(with(containsLabel(CHECKBOX, "Select", stringStartsWith("15"))))
                .should(haveSize(11));
        table.filter(with(notContainLabel(CHECKBOX, "Select", stringEquals("86"))))
                .should(haveSize(194));
        table.filter(with(notContainLabel(CHECKBOX, "Select", stringStartsWith("15"))))
                .should(haveSize(184));

        table.filter(without(containsLabel(CHECKBOX, "Select", stringEquals("86"))))
                .should(haveSize(194));
        table.filter(without(containsLabel(CHECKBOX, "Select", stringStartsWith("15"))))
                .should(haveSize(184));
        table.filter(without(notContainLabel(CHECKBOX, "Select", stringEquals("86"))))
                .should(haveSize(1));
        table.filter(without(notContainLabel(CHECKBOX, "Select", stringStartsWith("15"))))
                .should(haveSize(11));

        table.filter(with(containsLabel(CHECKBOX, "Select", intEquals(77))))
                .should(haveSize(1));
        table.filter(with(containsLabel(CHECKBOX, "Select", intGreaterThanOrEqual(124))))
                .should(haveSize(72));
        table.filter(with(notContainLabel(CHECKBOX, "Select", intEquals(77))))
                .should(haveSize(194));
        table.filter(with(notContainLabel(CHECKBOX, "Select", intGreaterThanOrEqual(124))))
                .should(haveSize(123));

        table.filter(without(containsLabel(CHECKBOX, "Select", intEquals(77))))
                .should(haveSize(194));
        table.filter(without(containsLabel(CHECKBOX, "Select", intGreaterThanOrEqual(124))))
                .should(haveSize(123));
        table.filter(without(notContainLabel(CHECKBOX, "Select", intEquals(77))))
                .should(haveSize(1));
        table.filter(without(notContainLabel(CHECKBOX, "Select", intGreaterThanOrEqual(124))))
                .should(haveSize(72));
    }

    @Test
    void webTableFilterElementEnabledConditionTest() {
        WebPageContext context = initWebPageContext();
        context.getPage(HomePage.class).leftMenu()
                .select("Table Element");

        TablePage tablePage = context.getPage(TablePage.class);
        WebTable table = tablePage.table()
                .should(beDisplayed());

        // By Element
        table.filter(with(enabled(CHECKBOX, frame(CheckboxWebBlock.class).checkbox())))
                .should(haveSize(189));
        table.filter(with(disabled(CHECKBOX, frame(CheckboxWebBlock.class).checkbox())))
                .should(haveSize(6));

        table.filter(without(enabled(CHECKBOX, frame(CheckboxWebBlock.class).checkbox())))
                .should(haveSize(6));
        table.filter(without(disabled(CHECKBOX, frame(CheckboxWebBlock.class).checkbox())))
                .should(haveSize(189));

        // By Element name
        table.filter(with(enabled(CHECKBOX, "Select")))
                .should(haveSize(189));
        table.filter(with(disabled(CHECKBOX, "Select")))
                .should(haveSize(6));

        table.filter(without(enabled(CHECKBOX, "Select")))
                .should(haveSize(6));
        table.filter(without(disabled(CHECKBOX, "Select")))
                .should(haveSize(189));
    }

    @Test
    void webTableFilterElementSelectedConditionTest() {
        WebPageContext context = initWebPageContext();
        context.getPage(HomePage.class).leftMenu()
                .select("Table Element");

        TablePage tablePage = context.getPage(TablePage.class);
        WebTable table = tablePage.table()
                .should(beDisplayed());

        // By Element
        table.filter(with(selected(CHECKBOX, frame(CheckboxWebBlock.class).checkbox())))
                .should(haveSize(6));
        table.filter(with(notSelected(CHECKBOX, frame(CheckboxWebBlock.class).checkbox())))
                .should(haveSize(189));

        table.filter(without(selected(CHECKBOX, frame(CheckboxWebBlock.class).checkbox())))
                .should(haveSize(189));
        table.filter(without(notSelected(CHECKBOX, frame(CheckboxWebBlock.class).checkbox())))
                .should(haveSize(6));

        // By Element name
        table.filter(with(selected(CHECKBOX, "Select")))
                .should(haveSize(6));
        table.filter(with(notSelected(CHECKBOX, "Select")))
                .should(haveSize(189));

        table.filter(without(selected(CHECKBOX, "Select")))
                .should(haveSize(189));
        table.filter(without(notSelected(CHECKBOX, "Select")))
                .should(haveSize(6));
    }

    @Test
    void webTableFilterElementPresentConditionTest() {
        WebPageContext context = initWebPageContext();
        context.getPage(HomePage.class).leftMenu()
                .select("Table Element");

        TablePage tablePage = context.getPage(TablePage.class);
        WebTable table = tablePage.table()
                .should(beDisplayed());

        // By Element
        table.filter(with(present(SHORT_COUNTRY_NAME, frame(CountryNameWebBlock.class).shortName())))
                .should(haveSize(193));
        table.filter(with(notPresent(SHORT_COUNTRY_NAME, frame(CountryNameWebBlock.class).shortName())))
                .should(haveSize(2));

        table.filter(without(present(SHORT_COUNTRY_NAME, frame(CountryNameWebBlock.class).shortName())))
                .should(haveSize(2));
        table.filter(without(notPresent(SHORT_COUNTRY_NAME, frame(CountryNameWebBlock.class).shortName())))
                .should(haveSize(193));

        // By Element name
        table.filter(with(present(SHORT_COUNTRY_NAME, "Country name")))
                .should(haveSize(193));
        table.filter(with(notPresent(SHORT_COUNTRY_NAME, "Country name")))
                .should(haveSize(2));

        table.filter(without(present(SHORT_COUNTRY_NAME, "Country name")))
                .should(haveSize(2));
        table.filter(without(notPresent(SHORT_COUNTRY_NAME, "Country name")))
                .should(haveSize(193));
    }

    @Test
    void webTableFilterElementDisplayedConditionTest() {
        WebPageContext context = initWebPageContext();
        context.getPage(HomePage.class).leftMenu()
                .select("Table Element");

        TablePage tablePage = context.getPage(TablePage.class);
        WebTable table = tablePage.table()
                .should(beDisplayed());

        // By Element
        // Для элементов, которых нет в DOM
        table.filter(with(displayed(SHORT_COUNTRY_NAME, frame(CountryNameWebBlock.class).shortName())))
                .should(haveSize(193));
        table.filter(with(notDisplayed(SHORT_COUNTRY_NAME, frame(CountryNameWebBlock.class).shortName())))
                .should(haveSize(2));

        table.filter(without(displayed(SHORT_COUNTRY_NAME, frame(CountryNameWebBlock.class).shortName())))
                .should(haveSize(2));
        table.filter(without(notDisplayed(SHORT_COUNTRY_NAME, frame(CountryNameWebBlock.class).shortName())))
                .should(haveSize(193));

        // Для элементов, которые есть в DOM, но не отображаются
        table.filter(with(displayed(POPULATION, frame(PopulationWebBlock.class).populationUnit())))
                .should(haveSize(186));
        table.filter(with(notDisplayed(POPULATION, frame(PopulationWebBlock.class).populationUnit())))
                .should(haveSize(9));

        table.filter(without(displayed(POPULATION, frame(PopulationWebBlock.class).populationUnit())))
                .should(haveSize(9));
        table.filter(without(notDisplayed(POPULATION, frame(PopulationWebBlock.class).populationUnit())))
                .should(haveSize(186));

        // By Element name
        // Для элементов, которых нет в DOM
        table.filter(with(displayed(SHORT_COUNTRY_NAME, "Country name")))
                .should(haveSize(193));
        table.filter(with(notDisplayed(SHORT_COUNTRY_NAME, "Country name")))
                .should(haveSize(2));

        table.filter(without(displayed(SHORT_COUNTRY_NAME, "Country name")))
                .should(haveSize(2));
        table.filter(without(notDisplayed(SHORT_COUNTRY_NAME, "Country name")))
                .should(haveSize(193));

        // Для элементов, которые есть в DOM, но не отображаются
        table.filter(with(displayed(POPULATION, "Population unit")))
                .should(haveSize(186));
        table.filter(with(notDisplayed(POPULATION, "Population unit")))
                .should(haveSize(9));

        table.filter(without(displayed(POPULATION, "Population unit")))
                .should(haveSize(9));
        table.filter(without(notDisplayed(POPULATION, "Population unit")))
                .should(haveSize(186));
    }

    @Test
    void webTableFilterElementComponentPresentConditionTest() {
        WebPageContext context = initWebPageContext();
        context.getPage(HomePage.class).leftMenu()
                .select("Table Element");

        TablePage tablePage = context.getPage(TablePage.class);
        WebTable table = tablePage.table()
                .should(beDisplayed());

        // By Element
        table.filter(with(componentPresent(SHORT_COUNTRY_NAME, frame(CountryNameWebBlock.class).shortName(), "Self")))
                .should(haveSize(193));
        table.filter(with(componentNotPresent(SHORT_COUNTRY_NAME, frame(CountryNameWebBlock.class).shortName(), "Self")))
                .should(haveSize(2));

        table.filter(without(componentPresent(SHORT_COUNTRY_NAME, frame(CountryNameWebBlock.class).shortName(), "Self")))
                .should(haveSize(2));
        table.filter(without(componentNotPresent(SHORT_COUNTRY_NAME, frame(CountryNameWebBlock.class).shortName(), "Self")))
                .should(haveSize(193));

        // By Element name
        table.filter(with(componentPresent(SHORT_COUNTRY_NAME, "Country name", "Self")))
                .should(haveSize(193));
        table.filter(with(componentNotPresent(SHORT_COUNTRY_NAME, "Country name", "Self")))
                .should(haveSize(2));

        table.filter(without(componentPresent(SHORT_COUNTRY_NAME, "Country name", "Self")))
                .should(haveSize(2));
        table.filter(without(componentNotPresent(SHORT_COUNTRY_NAME, "Country name", "Self")))
                .should(haveSize(193));

        WebTableFilter filter = table
                .filter(with(componentPresent(SHORT_COUNTRY_NAME, frame(CountryNameWebBlock.class).shortName(), "Unknown")));
        assertThrows(WebLocatorNotFoundException.class, () -> filter.should(haveSize(200)));
    }

    @Test
    void webTableFilterElementComponentDisplayedConditionTest() {
        WebPageContext context = initWebPageContext();
        context.getPage(HomePage.class).leftMenu()
                .select("Table Element");

        TablePage tablePage = context.getPage(TablePage.class);
        WebTable table = tablePage.table()
                .should(beDisplayed());

        // By Element
        table.filter(with(componentDisplayed(POPULATION, frame(PopulationWebBlock.class).populationUnit(), "Self")))
                .should(haveSize(186));
        table.filter(with(componentNotDisplayed(POPULATION, frame(PopulationWebBlock.class).populationUnit(), "Self")))
                .should(haveSize(9));

        table.filter(without(componentDisplayed(POPULATION, frame(PopulationWebBlock.class).populationUnit(), "Self")))
                .should(haveSize(9));
        table.filter(without(componentNotDisplayed(POPULATION, frame(PopulationWebBlock.class).populationUnit(), "Self")))
                .should(haveSize(186));

        // By Element name
        table.filter(with(componentDisplayed(POPULATION, "Population unit", "Self")))
                .should(haveSize(186));
        table.filter(with(componentNotDisplayed(POPULATION, "Population unit", "Self")))
                .should(haveSize(9));

        table.filter(without(componentDisplayed(POPULATION, "Population unit", "Self")))
                .should(haveSize(9));
        table.filter(without(componentNotDisplayed(POPULATION, "Population unit", "Self")))
                .should(haveSize(186));

        WebTableFilter filter = table
                .filter(with(componentDisplayed(SHORT_COUNTRY_NAME, frame(CountryNameWebBlock.class).shortName(), "Unknown")));
        assertThrows(WebLocatorNotFoundException.class, () -> filter.should(haveSize(200)));
    }

}
