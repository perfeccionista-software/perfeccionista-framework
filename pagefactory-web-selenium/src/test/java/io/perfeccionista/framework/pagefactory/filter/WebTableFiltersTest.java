package io.perfeccionista.framework.pagefactory.filter;

import io.perfeccionista.framework.Environment;
import io.perfeccionista.framework.exceptions.WebLocatorNotFound.WebLocatorNotFoundException;
import io.perfeccionista.framework.pagefactory.AbstractUiTest;
import io.perfeccionista.framework.pagefactory.context.base.WebPageContext;
import io.perfeccionista.framework.pagefactory.elements.WebTable;
import io.perfeccionista.framework.pagefactory.filter.table.WebTableFilter;
import io.perfeccionista.framework.pagefactory.pageobjects.HomePage;
import io.perfeccionista.framework.pagefactory.pageobjects.TablePage;
import io.perfeccionista.framework.pagefactory.pageobjects.blocks.table.CheckboxWebBlock;
import io.perfeccionista.framework.pagefactory.pageobjects.blocks.table.CountryNumberWebBlock;
import io.perfeccionista.framework.pagefactory.pageobjects.blocks.table.FullNameWebBlock;
import io.perfeccionista.framework.pagefactory.pageobjects.blocks.table.PopulationWebBlock;
import io.perfeccionista.framework.pagefactory.pageobjects.blocks.table.ShortNameWebBlock;
import io.perfeccionista.framework.value.ValueService;
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
import static io.perfeccionista.framework.pagefactory.filter.WebFilterConditions.notContainsLabel;
import static io.perfeccionista.framework.pagefactory.filter.WebFilterConditions.notContainsProperty;
import static io.perfeccionista.framework.pagefactory.filter.WebFilterConditions.notContainsText;
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
import static io.perfeccionista.framework.pagefactory.pageobjects.TablePage.FULL_NAME;
import static io.perfeccionista.framework.pagefactory.pageobjects.TablePage.NUMBER;
import static io.perfeccionista.framework.pagefactory.pageobjects.TablePage.POPULATION;
import static io.perfeccionista.framework.pagefactory.pageobjects.TablePage.SHORT_NAME;
import static org.junit.jupiter.api.Assertions.assertThrows;

@Tag("Element") @Tag("WebTable")
class WebTableFiltersTest extends AbstractUiTest {

    @Test
    void webTableFilterEmptyConditionTest(Environment env, ValueService value) {
        WebPageContext context = initWebPageContext(env, value);
        context.getPage(HomePage.class).leftMenu()
                .select("Table Element");
        TablePage tablePage = context.getPage(TablePage.class);
        WebTable table = tablePage.table()
                .should(beDisplayed());

        table.filter(emptyWebTableFilter())
                .should(haveSize(195));
    }

    @Test
    void webTableFilterRowIndexConditionTest(Environment env, ValueService value) {
        WebPageContext context = initWebPageContext(env, value);
        context.getPage(HomePage.class).leftMenu()
                .select("Table Element");
        TablePage tablePage = context.getPage(TablePage.class);
        WebTable table = tablePage.table()
                .should(beDisplayed());

        table.filter(with(rowIndex(value.intGreaterThanOrEqual(100))))
                .should(haveSize(95));
        table.filter(without(rowIndex(value.intGreaterThanOrEqual(100))))
                .should(haveSize(100));
    }

    @Test
    void webTableFilterElementTextConditionTest(Environment env, ValueService value) {
        WebPageContext context = initWebPageContext(env, value);
        context.getPage(HomePage.class).leftMenu()
                .select("Table Element");
        TablePage tablePage = context.getPage(TablePage.class);
        WebTable table = tablePage.table()
                .should(beDisplayed());

        // By Element
        table.filter(with(containsText(SHORT_NAME, frame(ShortNameWebBlock.class).shortName(), "Финляндия")))
                .should(haveSize(1));
        table.filter(with(containsText(SHORT_NAME, frame(ShortNameWebBlock.class).shortName(), value.stringStartsWith("М"))))
                .should(haveSize(17));
        table.filter(with(notContainsText(SHORT_NAME, frame(ShortNameWebBlock.class).shortName(), value.stringEquals("Финляндия"))))
                .should(haveSize(194));
        table.filter(with(notContainsText(SHORT_NAME, frame(ShortNameWebBlock.class).shortName(), value.stringStartsWith("М"))))
                .should(haveSize(178));

        table.filter(without(containsText(SHORT_NAME, frame(ShortNameWebBlock.class).shortName(), "Финляндия")))
                .should(haveSize(194));
        table.filter(without(containsText(SHORT_NAME, frame(ShortNameWebBlock.class).shortName(), value.stringStartsWith("М"))))
                .should(haveSize(178));
        table.filter(without(notContainsText(SHORT_NAME, frame(ShortNameWebBlock.class).shortName(), value.stringEquals("Финляндия"))))
                .should(haveSize(1));
        table.filter(without(notContainsText(SHORT_NAME, frame(ShortNameWebBlock.class).shortName(), value.stringStartsWith("М"))))
                .should(haveSize(17));

        table.filter(with(containsText(NUMBER, frame(CountryNumberWebBlock.class).number(), value.intEquals(77))))
                .should(haveSize(1));
        table.filter(with(containsText(NUMBER, frame(CountryNumberWebBlock.class).number(), value.intGreaterThanOrEqual(124))))
                .should(haveSize(72));
        table.filter(with(notContainsText(NUMBER, frame(CountryNumberWebBlock.class).number(), value.intEquals(77))))
                .should(haveSize(194));
        table.filter(with(notContainsText(NUMBER, frame(CountryNumberWebBlock.class).number(), value.intGreaterThanOrEqual(124))))
                .should(haveSize(123));

        table.filter(without(containsText(NUMBER, frame(CountryNumberWebBlock.class).number(), value.intEquals(77))))
                .should(haveSize(194));
        table.filter(without(containsText(NUMBER, frame(CountryNumberWebBlock.class).number(), value.intGreaterThanOrEqual(124))))
                .should(haveSize(123));
        table.filter(without(notContainsText(NUMBER, frame(CountryNumberWebBlock.class).number(), value.intEquals(77))))
                .should(haveSize(1));
        table.filter(without(notContainsText(NUMBER, frame(CountryNumberWebBlock.class).number(), value.intGreaterThanOrEqual(124))))
                .should(haveSize(72));

        // By Element name
        table.filter(with(containsText(SHORT_NAME, "Short name", value.stringEquals("Финляндия"))))
                .should(haveSize(1));
        table.filter(with(containsText(SHORT_NAME, "Short name", value.stringStartsWith("М"))))
                .should(haveSize(17));
        table.filter(with(notContainsText(SHORT_NAME, "Short name", value.stringEquals("Финляндия"))))
                .should(haveSize(194));
        table.filter(with(notContainsText(SHORT_NAME, "Short name", value.stringStartsWith("М"))))
                .should(haveSize(178));

        table.filter(without(containsText(SHORT_NAME, "Short name", value.stringEquals("Финляндия"))))
                .should(haveSize(194));
        table.filter(without(containsText(SHORT_NAME, "Short name", value.stringStartsWith("М"))))
                .should(haveSize(178));
        table.filter(without(notContainsText(SHORT_NAME, "Short name", value.stringEquals("Финляндия"))))
                .should(haveSize(1));
        table.filter(without(notContainsText(SHORT_NAME, "Short name", value.stringStartsWith("М"))))
                .should(haveSize(17));

        table.filter(with(containsText(NUMBER, "Number", value.intEquals(77))))
                .should(haveSize(1));
        table.filter(with(containsText(NUMBER, "Number", value.intGreaterThanOrEqual(124))))
                .should(haveSize(72));
        table.filter(with(notContainsText(NUMBER, "Number", value.intEquals(77))))
                .should(haveSize(194));
        table.filter(with(notContainsText(NUMBER, "Number", value.intGreaterThanOrEqual(124))))
                .should(haveSize(123));

        table.filter(without(containsText(NUMBER, "Number", value.intEquals(77))))
                .should(haveSize(194));
        table.filter(without(containsText(NUMBER, "Number", value.intGreaterThanOrEqual(124))))
                .should(haveSize(123));
        table.filter(without(notContainsText(NUMBER, "Number", value.intEquals(77))))
                .should(haveSize(1));
        table.filter(without(notContainsText(NUMBER, "Number", value.intGreaterThanOrEqual(124))))
                .should(haveSize(72));
    }

    @Test
    void webTableFilterElementSelectedConditionTest(Environment env, ValueService value) {
        WebPageContext context = initWebPageContext(env, value);
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
    void webTableFilterElementPropertyConditionTest(Environment env, ValueService value) {
        WebPageContext context = initWebPageContext(env, value);
        context.getPage(HomePage.class).leftMenu()
                .select("Table Element");
        TablePage tablePage = context.getPage(TablePage.class);
        WebTable table = tablePage.table()
                .should(beDisplayed());

        // By Element
        table.filter(with(containsProperty(FULL_NAME, frame(FullNameWebBlock.class).fullName(), "prompt", "Финляндская Республика")))
                .should(haveSize(1));
        table.filter(with(containsProperty(FULL_NAME, frame(FullNameWebBlock.class).fullName(), "prompt", value.stringStartsWith("М"))))
                .should(haveSize(5));
        table.filter(with(notContainsProperty(FULL_NAME, frame(FullNameWebBlock.class).fullName(), "prompt", value.stringEquals("Финляндская Республика"))))
                .should(haveSize(194));
        table.filter(with(notContainsProperty(FULL_NAME, frame(FullNameWebBlock.class).fullName(), "prompt", value.stringStartsWith("М"))))
                .should(haveSize(190));

        table.filter(without(containsProperty(FULL_NAME, frame(FullNameWebBlock.class).fullName(), "prompt", value.stringEquals("Финляндская Республика"))))
                .should(haveSize(194));
        table.filter(without(containsProperty(FULL_NAME, frame(FullNameWebBlock.class).fullName(), "prompt", value.stringStartsWith("М"))))
                .should(haveSize(190));
        table.filter(without(notContainsProperty(FULL_NAME, frame(FullNameWebBlock.class).fullName(), "prompt", value.stringEquals("Финляндская Республика"))))
                .should(haveSize(1));
        table.filter(without(notContainsProperty(FULL_NAME, frame(FullNameWebBlock.class).fullName(), "prompt", value.stringStartsWith("М"))))
                .should(haveSize(5));

        // By Element name
        table.filter(with(containsProperty(FULL_NAME, "Full name", "prompt", value.stringEquals("Финляндская Республика"))))
                .should(haveSize(1));
        table.filter(with(containsProperty(FULL_NAME, "Full name", "prompt", value.stringStartsWith("М"))))
                .should(haveSize(5));
        table.filter(with(notContainsProperty(FULL_NAME, "Full name", "prompt", value.stringEquals("Финляндская Республика"))))
                .should(haveSize(194));
        table.filter(with(notContainsProperty(FULL_NAME, "Full name", "prompt", value.stringStartsWith("М"))))
                .should(haveSize(190));

        table.filter(without(containsProperty(FULL_NAME, "Full name", "prompt", value.stringEquals("Финляндская Республика"))))
                .should(haveSize(194));
        table.filter(without(containsProperty(FULL_NAME, "Full name", "prompt", value.stringStartsWith("М"))))
                .should(haveSize(190));
        table.filter(without(notContainsProperty(FULL_NAME, "Full name", "prompt", value.stringEquals("Финляндская Республика"))))
                .should(haveSize(1));
        table.filter(without(notContainsProperty(FULL_NAME, "Full name", "prompt", value.stringStartsWith("М"))))
                .should(haveSize(5));
    }

    @Test
    void webTableFilterElementPresentConditionTest(Environment env, ValueService value) {
        WebPageContext context = initWebPageContext(env, value);
        context.getPage(HomePage.class).leftMenu()
                .select("Table Element");
        TablePage tablePage = context.getPage(TablePage.class);
        WebTable table = tablePage.table()
                .should(beDisplayed());

        // By Element
        table.filter(with(present(SHORT_NAME, frame(ShortNameWebBlock.class).shortName())))
                .should(haveSize(193));
        table.filter(with(notPresent(SHORT_NAME, frame(ShortNameWebBlock.class).shortName())))
                .should(haveSize(2));

        table.filter(without(present(SHORT_NAME, frame(ShortNameWebBlock.class).shortName())))
                .should(haveSize(2));
        table.filter(without(notPresent(SHORT_NAME, frame(ShortNameWebBlock.class).shortName())))
                .should(haveSize(193));

        // By Element name
        table.filter(with(present(SHORT_NAME, "Short name")))
                .should(haveSize(193));
        table.filter(with(notPresent(SHORT_NAME, "Short name")))
                .should(haveSize(2));

        table.filter(without(present(SHORT_NAME, "Short name")))
                .should(haveSize(2));
        table.filter(without(notPresent(SHORT_NAME, "Short name")))
                .should(haveSize(193));
    }

    @Test
    void webTableFilterElementLabelConditionTest(Environment env, ValueService value) {
        WebPageContext context = initWebPageContext(env, value);
        context.getPage(HomePage.class).leftMenu()
                .select("Table Element");
        TablePage tablePage = context.getPage(TablePage.class);
        WebTable table = tablePage.table()
                .should(beDisplayed());

        // By Element
        table.filter(with(containsLabel(CHECKBOX, frame(CheckboxWebBlock.class).checkbox(), value.stringEquals("86"))))
                .should(haveSize(1));
        table.filter(with(containsLabel(CHECKBOX, frame(CheckboxWebBlock.class).checkbox(), value.stringStartsWith("15"))))
                .should(haveSize(11));
        table.filter(with(notContainsLabel(CHECKBOX, frame(CheckboxWebBlock.class).checkbox(), value.stringEquals("86"))))
                .should(haveSize(194));
        table.filter(with(notContainsLabel(CHECKBOX, frame(CheckboxWebBlock.class).checkbox(), value.stringStartsWith("15"))))
                .should(haveSize(184));

        table.filter(without(containsLabel(CHECKBOX, frame(CheckboxWebBlock.class).checkbox(), value.stringEquals("86"))))
                .should(haveSize(194));
        table.filter(without(containsLabel(CHECKBOX, frame(CheckboxWebBlock.class).checkbox(), value.stringStartsWith("15"))))
                .should(haveSize(184));
        table.filter(without(notContainsLabel(CHECKBOX, frame(CheckboxWebBlock.class).checkbox(), value.stringEquals("86"))))
                .should(haveSize(1));
        table.filter(without(notContainsLabel(CHECKBOX, frame(CheckboxWebBlock.class).checkbox(), value.stringStartsWith("15"))))
                .should(haveSize(11));

        table.filter(with(containsLabel(CHECKBOX, frame(CheckboxWebBlock.class).checkbox(), value.intEquals(77))))
                .should(haveSize(1));
        table.filter(with(containsLabel(CHECKBOX, frame(CheckboxWebBlock.class).checkbox(), value.intGreaterThanOrEqual(124))))
                .should(haveSize(72));
        table.filter(with(notContainsLabel(CHECKBOX, frame(CheckboxWebBlock.class).checkbox(), value.intEquals(77))))
                .should(haveSize(194));
        table.filter(with(notContainsLabel(CHECKBOX, frame(CheckboxWebBlock.class).checkbox(), value.intGreaterThanOrEqual(124))))
                .should(haveSize(123));

        table.filter(without(containsLabel(CHECKBOX, frame(CheckboxWebBlock.class).checkbox(), value.intEquals(77))))
                .should(haveSize(194));
        table.filter(without(containsLabel(CHECKBOX, frame(CheckboxWebBlock.class).checkbox(), value.intGreaterThanOrEqual(124))))
                .should(haveSize(123));
        table.filter(without(notContainsLabel(CHECKBOX, frame(CheckboxWebBlock.class).checkbox(), value.intEquals(77))))
                .should(haveSize(1));
        table.filter(without(notContainsLabel(CHECKBOX, frame(CheckboxWebBlock.class).checkbox(), value.intGreaterThanOrEqual(124))))
                .should(haveSize(72));

        // By Element name
        table.filter(with(containsLabel(CHECKBOX, "Select", value.stringEquals("86"))))
                .should(haveSize(1));
        table.filter(with(containsLabel(CHECKBOX, "Select", value.stringStartsWith("15"))))
                .should(haveSize(11));
        table.filter(with(notContainsLabel(CHECKBOX, "Select", value.stringEquals("86"))))
                .should(haveSize(194));
        table.filter(with(notContainsLabel(CHECKBOX, "Select", value.stringStartsWith("15"))))
                .should(haveSize(184));

        table.filter(without(containsLabel(CHECKBOX, "Select", value.stringEquals("86"))))
                .should(haveSize(194));
        table.filter(without(containsLabel(CHECKBOX, "Select", value.stringStartsWith("15"))))
                .should(haveSize(184));
        table.filter(without(notContainsLabel(CHECKBOX, "Select", value.stringEquals("86"))))
                .should(haveSize(1));
        table.filter(without(notContainsLabel(CHECKBOX, "Select", value.stringStartsWith("15"))))
                .should(haveSize(11));

        table.filter(with(containsLabel(CHECKBOX, "Select", value.intEquals(77))))
                .should(haveSize(1));
        table.filter(with(containsLabel(CHECKBOX, "Select", value.intGreaterThanOrEqual(124))))
                .should(haveSize(72));
        table.filter(with(notContainsLabel(CHECKBOX, "Select", value.intEquals(77))))
                .should(haveSize(194));
        table.filter(with(notContainsLabel(CHECKBOX, "Select", value.intGreaterThanOrEqual(124))))
                .should(haveSize(123));

        table.filter(without(containsLabel(CHECKBOX, "Select", value.intEquals(77))))
                .should(haveSize(194));
        table.filter(without(containsLabel(CHECKBOX, "Select", value.intGreaterThanOrEqual(124))))
                .should(haveSize(123));
        table.filter(without(notContainsLabel(CHECKBOX, "Select", value.intEquals(77))))
                .should(haveSize(1));
        table.filter(without(notContainsLabel(CHECKBOX, "Select", value.intGreaterThanOrEqual(124))))
                .should(haveSize(72));
    }

    @Test
    void webTableFilterElementEnabledConditionTest(Environment env, ValueService value) {
        WebPageContext context = initWebPageContext(env, value);
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
    void webTableFilterElementDisplayedConditionTest(Environment env, ValueService value) {
        WebPageContext context = initWebPageContext(env, value);
        context.getPage(HomePage.class).leftMenu()
                .select("Table Element");
        TablePage tablePage = context.getPage(TablePage.class);
        WebTable table = tablePage.table()
                .should(beDisplayed());

        // By Element
        // Для элементов, которых нет в DOM
        table.filter(with(displayed(SHORT_NAME, frame(ShortNameWebBlock.class).shortName())))
                .should(haveSize(193));
        table.filter(with(notDisplayed(SHORT_NAME, frame(ShortNameWebBlock.class).shortName())))
                .should(haveSize(2));

        table.filter(without(displayed(SHORT_NAME, frame(ShortNameWebBlock.class).shortName())))
                .should(haveSize(2));
        table.filter(without(notDisplayed(SHORT_NAME, frame(ShortNameWebBlock.class).shortName())))
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
        table.filter(with(displayed(SHORT_NAME, "Short name")))
                .should(haveSize(193));
        table.filter(with(notDisplayed(SHORT_NAME, "Short name")))
                .should(haveSize(2));

        table.filter(without(displayed(SHORT_NAME, "Short name")))
                .should(haveSize(2));
        table.filter(without(notDisplayed(SHORT_NAME, "Short name")))
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
    void webTableFilterElementComponentPresentConditionTest(Environment env, ValueService value) {
        WebPageContext context = initWebPageContext(env, value);
        context.getPage(HomePage.class).leftMenu()
                .select("Table Element");
        TablePage tablePage = context.getPage(TablePage.class);
        WebTable table = tablePage.table()
                .should(beDisplayed());

        // By Element
        table.filter(with(componentPresent(SHORT_NAME, frame(ShortNameWebBlock.class).shortName(), "Self")))
                .should(haveSize(193));
        table.filter(with(componentNotPresent(SHORT_NAME, frame(ShortNameWebBlock.class).shortName(), "Self")))
                .should(haveSize(2));

        table.filter(without(componentPresent(SHORT_NAME, frame(ShortNameWebBlock.class).shortName(), "Self")))
                .should(haveSize(2));
        table.filter(without(componentNotPresent(SHORT_NAME, frame(ShortNameWebBlock.class).shortName(), "Self")))
                .should(haveSize(193));

        // By Element name
        table.filter(with(componentPresent(SHORT_NAME, "Short name", "Self")))
                .should(haveSize(193));
        table.filter(with(componentNotPresent(SHORT_NAME, "Short name", "Self")))
                .should(haveSize(2));

        table.filter(without(componentPresent(SHORT_NAME, "Short name", "Self")))
                .should(haveSize(2));
        table.filter(without(componentNotPresent(SHORT_NAME, "Short name", "Self")))
                .should(haveSize(193));

        WebTableFilter filter = table
                .filter(with(componentPresent(SHORT_NAME, frame(ShortNameWebBlock.class).shortName(), "Unknown")));
        assertThrows(WebLocatorNotFoundException.class, () -> filter.should(haveSize(200)));
    }

    @Test
    void webTableFilterElementComponentDisplayedConditionTest(Environment env, ValueService value) {
        WebPageContext context = initWebPageContext(env, value);
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
                .filter(with(componentDisplayed(SHORT_NAME, frame(ShortNameWebBlock.class).shortName(), "Unknown")));
        assertThrows(WebLocatorNotFoundException.class, () -> filter.should(haveSize(200)));
    }

}
