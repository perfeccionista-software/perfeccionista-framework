package io.perfeccionista.framework.pagefactory.filter;

import io.perfeccionista.framework.AbstractWebSeleniumParallelTest;
import io.perfeccionista.framework.exceptions.LocatorNotFound.LocatorNotFoundException;
import io.perfeccionista.framework.pagefactory.dispatcher.context.WebPageContext;
import io.perfeccionista.framework.pagefactory.elements.WebList;
import io.perfeccionista.framework.pagefactory.filter.list.WebListFilter;
import io.perfeccionista.framework.pagefactory.pageobjects.HomePage;
import io.perfeccionista.framework.pagefactory.pageobjects.ListElementsPage;
import io.perfeccionista.framework.pagefactory.pageobjects.blocks.list.CountryBlock;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static io.perfeccionista.framework.Web.*;
import static io.perfeccionista.framework.value.Values.intEquals;
import static io.perfeccionista.framework.value.Values.intGreaterThanOrEqual;
import static io.perfeccionista.framework.value.Values.stringEquals;
import static io.perfeccionista.framework.value.Values.stringStartsWith;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

// TODO: Сделать тесты на несколько условий и на несколько фильтров
@Tag("WebElement") @Tag("WebList")
class WebListFiltersTest extends AbstractWebSeleniumParallelTest {

    @Test
    void webListFilterSizeTest() {
        WebPageContext context = initWebPageContext();
        context.getPage(HomePage.class).leftMenu()
                .select("List Elements");

        ListElementsPage listElementsPage = context.getPage(ListElementsPage.class);
        WebList<CountryBlock> list = listElementsPage.webList()
                .should(beDisplayed());

        assertEquals(95, list.filter(blockIndex(intGreaterThanOrEqual(100))).size());
        assertEquals(100, list.filterBuilder(without(blockIndex(intGreaterThanOrEqual(100)))).size());
    }

    @Test
    void webListFilterEmptyConditionTest() {
        WebPageContext context = initWebPageContext();
        context.getPage(HomePage.class).leftMenu()
                .select("List Elements");

        ListElementsPage listElementsPage = context.getPage(ListElementsPage.class);
        WebList<CountryBlock> list = listElementsPage.webList()
                .should(beDisplayed());

        list.should(haveSize(195));

        list.filterBuilder(emptyWebListFilter())
                .should(haveSize(195));
    }

    @Test
    void webListFilterRowIndexConditionTest() {
        WebPageContext context = initWebPageContext();
        context.getPage(HomePage.class).leftMenu()
                .select("List Elements");

        ListElementsPage listElementsPage = context.getPage(ListElementsPage.class);
        WebList<CountryBlock> list = listElementsPage.webList()
                .should(beDisplayed());

        list.filter(blockIndex(intGreaterThanOrEqual(100)))
                .should(haveSize(intEquals(95)));
        list.filterBuilder(without(blockIndex(intGreaterThanOrEqual(100))))
                .should(haveSize(intEquals(100)));
    }

    @Test
    void webListFilterElementTextConditionTest() {
        WebPageContext context = initWebPageContext();
        context.getPage(HomePage.class).leftMenu()
                .select("List Elements");

        ListElementsPage listElementsPage = context.getPage(ListElementsPage.class);
        WebList<CountryBlock> list = listElementsPage.webList()
                .should(beDisplayed());

        // TODO: Добавить проверки со стрингой в остальные тесты
        // By Element
        list.filter(block -> containsText(block.shortName(), "Финляндия"))
                .should(haveSize(1));
        list.filter(block -> containsText(block.shortName(), stringEquals("Финляндия")))
                .should(haveSize(1));
        list.filter(block -> containsText(block.shortName(), stringStartsWith("М")))
                .should(haveSize(17));
        list.filter(block -> notContainText(block.shortName(), "Финляндия"))
                .should(haveSize(194));
        list.filter(block -> notContainText(block.shortName(), stringEquals("Финляндия")))
                .should(haveSize(194));
        list.filter(block -> notContainText(block.shortName(), stringStartsWith("М")))
                .should(haveSize(178));

        list.filterBuilder(block -> without(containsText(block.shortName(), stringEquals("Финляндия"))))
                .should(haveSize(194));
        list.filterBuilder(block -> without(containsText(block.shortName(), stringStartsWith("М"))))
                .should(haveSize(178));
        list.filterBuilder(block -> without(notContainText(block.shortName(), stringEquals("Финляндия"))))
                .should(haveSize(1));
        list.filterBuilder(block -> without(notContainText(block.shortName(), stringStartsWith("М"))))
                .should(haveSize(17));

        list.filter(block -> containsText(block.number(), intEquals(77)))
                .should(haveSize(1));
        list.filter(block -> containsText(block.number(), intGreaterThanOrEqual(124)))
                .should(haveSize(72));
        list.filter(block -> notContainText(block.number(), intEquals(77)))
                .should(haveSize(194));
        list.filter(block -> notContainText(block.number(), intGreaterThanOrEqual(124)))
                .should(haveSize(123));

        list.filterBuilder(block -> without(containsText(block.number(), intEquals(77))))
                .should(haveSize(194));
        list.filterBuilder(block -> without(containsText(block.number(), intGreaterThanOrEqual(124))))
                .should(haveSize(123));
        list.filterBuilder(block -> without(notContainText(block.number(), intEquals(77))))
                .should(haveSize(1));
        list.filterBuilder(block -> without(notContainText(block.number(), intGreaterThanOrEqual(124))))
                .should(haveSize(72));

        // TODO: Добавить проверки со стрингой в остальные тесты
        // By Element name
        list.filter(containsText("Country name", "Финляндия"))
                .should(haveSize(1));
        list.filter(containsText("Country name", stringEquals("Финляндия")))
                .should(haveSize(1));
        list.filter(containsText("Country name", stringStartsWith("М")))
                .should(haveSize(17));
        list.filter(notContainText("Country name", "Финляндия"))
                .should(haveSize(194));
        list.filter(notContainText("Country name", stringEquals("Финляндия")))
                .should(haveSize(194));
        list.filter(notContainText("Country name", stringStartsWith("М")))
                .should(haveSize(178));

        list.filterBuilder(without(containsText("Country name", stringEquals("Финляндия"))))
                .should(haveSize(194));
        list.filterBuilder(without(containsText("Country name", stringStartsWith("М"))))
                .should(haveSize(178));
        list.filterBuilder(without(notContainText("Country name", stringEquals("Финляндия"))))
                .should(haveSize(1));
        list.filterBuilder(without(notContainText("Country name", stringStartsWith("М"))))
                .should(haveSize(17));

        list.filter(containsText("Number", intEquals(77)))
                .should(haveSize(1));
        list.filter(containsText("Number", intGreaterThanOrEqual(124)))
                .should(haveSize(72));
        list.filter(notContainText("Number", intEquals(77)))
                .should(haveSize(194));
        list.filter(notContainText("Number", intGreaterThanOrEqual(124)))
                .should(haveSize(123));

        list.filterBuilder(without(containsText("Number", intEquals(77))))
                .should(haveSize(194));
        list.filterBuilder(without(containsText("Number", intGreaterThanOrEqual(124))))
                .should(haveSize(123));
        list.filterBuilder(without(notContainText("Number", intEquals(77))))
                .should(haveSize(1));
        list.filterBuilder(without(notContainText("Number", intGreaterThanOrEqual(124))))
                .should(haveSize(72));
    }

    @Test
    void webListFilterElementPropertyConditionTest() {
        WebPageContext context = initWebPageContext();
        context.getPage(HomePage.class).leftMenu()
                .select("List Elements");

        ListElementsPage listElementsPage = context.getPage(ListElementsPage.class);
        WebList<CountryBlock> list = listElementsPage.webList()
                .should(beDisplayed());

        // By Element
        list.filter(block -> containsProperty(block.fullName(), "prompt", "Финляндская Республика"))
                .should(haveSize(1));
        list.filter(block -> containsProperty(block.fullName(), "prompt", stringStartsWith("М")))
                .should(haveSize(5));
        list.filter(block -> notContainProperty(block.fullName(), "prompt", stringEquals("Финляндская Республика")))
                .should(haveSize(194));
        list.filter(block -> notContainProperty(block.fullName(), "prompt", stringStartsWith("М")))
                .should(haveSize(190));

        list.filterBuilder(block -> without(containsProperty(block.fullName(), "prompt", "Финляндская Республика")))
                .should(haveSize(194));
        list.filterBuilder(block -> without(containsProperty(block.fullName(), "prompt", stringStartsWith("М"))))
                .should(haveSize(190));
        list.filterBuilder(block -> without(notContainProperty(block.fullName(), "prompt", stringEquals("Финляндская Республика"))))
                .should(haveSize(1));
        list.filterBuilder(block -> without(notContainProperty(block.fullName(), "prompt", stringStartsWith("М"))))
                .should(haveSize(5));

        // By Element name
        list.filter(containsProperty("Full country name", "prompt", "Финляндская Республика"))
                .should(haveSize(1));
        list.filter(containsProperty("Full country name", "prompt", stringStartsWith("М")))
                .should(haveSize(5));
        list.filter(notContainProperty("Full country name", "prompt", stringEquals("Финляндская Республика")))
                .should(haveSize(194));
        list.filter(notContainProperty("Full country name", "prompt", stringStartsWith("М")))
                .should(haveSize(190));

        list.filterBuilder(without(containsProperty("Full country name", "prompt", "Финляндская Республика")))
                .should(haveSize(194));
        list.filterBuilder(without(containsProperty("Full country name", "prompt", stringStartsWith("М"))))
                .should(haveSize(190));
        list.filterBuilder(without(notContainProperty("Full country name", "prompt", stringEquals("Финляндская Республика"))))
                .should(haveSize(1));
        list.filterBuilder(without(notContainProperty("Full country name", "prompt", stringStartsWith("М"))))
                .should(haveSize(5));
    }

    @Test
    void webListFilterElementLabelConditionTest() {
        WebPageContext context = initWebPageContext();
        context.getPage(HomePage.class).leftMenu()
                .select("List Elements");

        ListElementsPage listElementsPage = context.getPage(ListElementsPage.class);
        WebList<CountryBlock> list = listElementsPage.webList()
                .should(beDisplayed());

        // By Element
        list.filter(block -> containsLabel(block.checkbox(), stringEquals("86")))
                .should(haveSize(1));
        list.filter(block -> containsLabel(block.checkbox(), stringStartsWith("15")))
                .should(haveSize(11));
        list.filter(block -> notContainLabel(block.checkbox(), stringEquals("86")))
                .should(haveSize(194));
        list.filter(block -> notContainLabel(block.checkbox(), stringStartsWith("15")))
                .should(haveSize(184));

        list.filterBuilder(block -> without(containsLabel(block.checkbox(), stringEquals("86"))))
                .should(haveSize(194));
        list.filterBuilder(block -> without(containsLabel(block.checkbox(), stringStartsWith("15"))))
                .should(haveSize(184));
        list.filterBuilder(block -> without(notContainLabel(block.checkbox(), stringEquals("86"))))
                .should(haveSize(1));
        list.filterBuilder(block -> without(notContainLabel(block.checkbox(), stringStartsWith("15"))))
                .should(haveSize(11));

        list.filter(block -> containsLabel(block.checkbox(), intEquals(77)))
                .should(haveSize(1));
        list.filter(block -> containsLabel(block.checkbox(), intGreaterThanOrEqual(124)))
                .should(haveSize(72));
        list.filter(block -> notContainLabel(block.checkbox(), intEquals(77)))
                .should(haveSize(194));
        list.filter(block -> notContainLabel(block.checkbox(), intGreaterThanOrEqual(124)))
                .should(haveSize(123));

        list.filterBuilder(block -> without(containsLabel(block.checkbox(), intEquals(77))))
                .should(haveSize(194));
        list.filterBuilder(block -> without(containsLabel(block.checkbox(), intGreaterThanOrEqual(124))))
                .should(haveSize(123));
        list.filterBuilder(block -> without(notContainLabel(block.checkbox(), intEquals(77))))
                .should(haveSize(1));
        list.filterBuilder(block -> without(notContainLabel(block.checkbox(), intGreaterThanOrEqual(124))))
                .should(haveSize(72));

        // By Element name
        list.filter(containsLabel("Select", stringEquals("86")))
                .should(haveSize(1));
        list.filter(containsLabel("Select", stringStartsWith("15")))
                .should(haveSize(11));
        list.filter(notContainLabel("Select", stringEquals("86")))
                .should(haveSize(194));
        list.filter(notContainLabel("Select", stringStartsWith("15")))
                .should(haveSize(184));

        list.filterBuilder(without(containsLabel("Select", stringEquals("86"))))
                .should(haveSize(194));
        list.filterBuilder(without(containsLabel("Select", stringStartsWith("15"))))
                .should(haveSize(184));
        list.filterBuilder(without(notContainLabel("Select", stringEquals("86"))))
                .should(haveSize(1));
        list.filterBuilder(without(notContainLabel("Select", stringStartsWith("15"))))
                .should(haveSize(11));

        list.filter(containsLabel("Select", intEquals(77)))
                .should(haveSize(1));
        list.filter(containsLabel("Select", intGreaterThanOrEqual(124)))
                .should(haveSize(72));
        list.filter(notContainLabel("Select", intEquals(77)))
                .should(haveSize(194));
        list.filter(notContainLabel("Select", intGreaterThanOrEqual(124)))
                .should(haveSize(123));

        list.filterBuilder(without(containsLabel("Select", intEquals(77))))
                .should(haveSize(194));
        list.filterBuilder(without(containsLabel("Select", intGreaterThanOrEqual(124))))
                .should(haveSize(123));
        list.filterBuilder(without(notContainLabel("Select", intEquals(77))))
                .should(haveSize(1));
        list.filterBuilder(without(notContainLabel("Select", intGreaterThanOrEqual(124))))
                .should(haveSize(72));
    }

    @Test
    void webListFilterElementEnabledConditionTest() {
        WebPageContext context = initWebPageContext();
        context.getPage(HomePage.class).leftMenu()
                .select("List Elements");

        ListElementsPage listElementsPage = context.getPage(ListElementsPage.class);
        WebList<CountryBlock> list = listElementsPage.webList()
                .should(beDisplayed());

        // By Element
        list.filter(block -> enabled(block.checkbox()))
                .should(haveSize(189));
        list.filter(block -> disabled(block.checkbox()))
                .should(haveSize(6));

        list.filterBuilder(block -> without(enabled(block.checkbox())))
                .should(haveSize(6));
        list.filterBuilder(block -> without(disabled(block.checkbox())))
                .should(haveSize(189));

        // By Element name
        list.filter(enabled("Select"))
                .should(haveSize(189));
        list.filter(disabled("Select"))
                .should(haveSize(6));

        list.filterBuilder(without(enabled("Select")))
                .should(haveSize(6));
        list.filterBuilder(without(disabled("Select")))
                .should(haveSize(189));
    }

    @Test
    void webListFilterElementSelectedConditionTest() {
        WebPageContext context = initWebPageContext();
        context.getPage(HomePage.class).leftMenu()
                .select("List Elements");

        ListElementsPage listElementsPage = context.getPage(ListElementsPage.class);
        WebList<CountryBlock> list = listElementsPage.webList()
                .should(beDisplayed());

        // By Element
        list.filter(block -> selected(block.checkbox()))
                .should(haveSize(6));
        list.filter(block -> notSelected(block.checkbox()))
                .should(haveSize(189));

        list.filterBuilder(block -> without(selected(block.checkbox())))
                .should(haveSize(189));
        list.filterBuilder(block -> without(notSelected(block.checkbox())))
                .should(haveSize(6));

        // By Element name
        list.filter(selected("Select"))
                .should(haveSize(6));
        list.filter(notSelected("Select"))
                .should(haveSize(189));

        list.filterBuilder(without(selected("Select")))
                .should(haveSize(189));
        list.filterBuilder(without(notSelected("Select")))
                .should(haveSize(6));
    }

    @Test
    void webListFilterElementPresentConditionTest() {
        WebPageContext context = initWebPageContext();
        context.getPage(HomePage.class).leftMenu()
                .select("List Elements");
        ListElementsPage listElementsPage = context.getPage(ListElementsPage.class);
        WebList<CountryBlock> list = listElementsPage.webList()
                .should(beDisplayed());

        // By Element
        list.filter(block -> present(block.shortName()))
                .should(haveSize(193));
        list.filter(block -> notPresent(block.shortName()))
                .should(haveSize(2));

        list.filterBuilder(block -> without(present(block.shortName())))
                .should(haveSize(2));
        list.filterBuilder(block -> without(notPresent(block.shortName())))
                .should(haveSize(193));

        // By Element name
        list.filter(present("Country name"))
                .should(haveSize(193));
        list.filter(notPresent("Country name"))
                .should(haveSize(2));

        list.filterBuilder(without(present("Country name")))
                .should(haveSize(2));
        list.filterBuilder(without(notPresent("Country name")))
                .should(haveSize(193));
    }

    @Test
    void webListFilterElementDisplayedConditionTest() {
        WebPageContext context = initWebPageContext();
        context.getPage(HomePage.class).leftMenu()
                .select("List Elements");

        ListElementsPage listElementsPage = context.getPage(ListElementsPage.class);
        WebList<CountryBlock> list = listElementsPage.webList()
                .should(beDisplayed());

        // By Element
        // Для элементов, которых нет в DOM
        list.filter(block -> displayed(block.shortName()))
                .should(haveSize(193));
        list.filter(block -> notDisplayed(block.shortName()))
                .should(haveSize(2));

        list.filterBuilder(block -> without(displayed(block.shortName())))
                .should(haveSize(2));
        list.filterBuilder(block -> without(notDisplayed(block.shortName())))
                .should(haveSize(193));

        // Для элементов, которые есть в DOM, но не отображаются
        list.filter(block -> displayed(block.populationUnit()))
                .should(haveSize(186));
        list.filter(block -> notDisplayed(block.populationUnit()))
                .should(haveSize(9));

        list.filterBuilder(block -> without(displayed(block.populationUnit())))
                .should(haveSize(9));
        list.filterBuilder(block -> without(notDisplayed(block.populationUnit())))
                .should(haveSize(186));

        // By Element name
        // Для элементов, которых нет в DOM
        list.filter(displayed("Country name"))
                .should(haveSize(193));
        list.filter(notDisplayed("Country name"))
                .should(haveSize(2));

        list.filterBuilder(without(displayed("Country name")))
                .should(haveSize(2));
        list.filterBuilder(without(notDisplayed("Country name")))
                .should(haveSize(193));

        // Для элементов, которые есть в DOM, но не отображаются
        list.filter(displayed("Population unit"))
                .should(haveSize(186));
        list.filter(notDisplayed("Population unit"))
                .should(haveSize(9));

        list.filterBuilder(without(displayed("Population unit")))
                .should(haveSize(9));
        list.filterBuilder(without(notDisplayed("Population unit")))
                .should(haveSize(186));
    }

    @Test
    void webListFilterElementComponentPresentConditionTest() {
        WebPageContext context = initWebPageContext();
        context.getPage(HomePage.class).leftMenu()
                .select("List Elements");

        ListElementsPage listElementsPage = context.getPage(ListElementsPage.class);
        WebList<CountryBlock> list = listElementsPage.webList()
                .should(beDisplayed());

        // By Element
        list.filter(block -> componentPresent(block.shortName(), "Self"))
                .should(haveSize(193));
        list.filter(block -> componentNotPresent(block.shortName(), "Self"))
                .should(haveSize(2));

        list.filterBuilder(block -> without(componentPresent(block.shortName(), "Self")))
                .should(haveSize(2));
        list.filterBuilder(block -> without(componentNotPresent(block.shortName(), "Self")))
                .should(haveSize(193));

        // By Element name
        list.filter(componentPresent("Country name", "Self"))
                .should(haveSize(193));
        list.filter(componentNotPresent("Country name", "Self"))
                .should(haveSize(2));

        list.filterBuilder(without(componentPresent("Country name", "Self")))
                .should(haveSize(2));
        list.filterBuilder(without(componentNotPresent("Country name", "Self")))
                .should(haveSize(193));

        WebListFilter<CountryBlock> filter = list.filterBuilder(block -> with(componentPresent(block.shortName(), "Unknown")));
        assertThrows(LocatorNotFoundException.class, () -> filter.should(haveSize(200)));
    }

    @Test
    void webListFilterElementComponentDisplayedConditionTest() {
        WebPageContext context = initWebPageContext();
        context.getPage(HomePage.class).leftMenu()
                .select("List Elements");

        ListElementsPage listElementsPage = context.getPage(ListElementsPage.class);
        WebList<CountryBlock> list = listElementsPage.webList()
                .should(beDisplayed());

        // By Element
        list.filter(block -> componentDisplayed(block.populationUnit(), "Self"))
                .should(haveSize(186));
        list.filter(block -> componentNotDisplayed(block.populationUnit(), "Self"))
                .should(haveSize(9));

        list.filterBuilder(block -> without(componentDisplayed(block.populationUnit(), "Self")))
                .should(haveSize(9));
        list.filterBuilder(block -> without(componentNotDisplayed(block.populationUnit(), "Self")))
                .should(haveSize(186));

        // By Element name
        list.filter(componentDisplayed("Population unit", "Self"))
                .should(haveSize(186));
        list.filter(componentNotDisplayed("Population unit", "Self"))
                .should(haveSize(9));

        list.filterBuilder(without(componentDisplayed("Population unit", "Self")))
                .should(haveSize(9));
        list.filterBuilder(without(componentNotDisplayed("Population unit", "Self")))
                .should(haveSize(186));

        WebListFilter<CountryBlock> filter = list.filterBuilder(block -> with(componentDisplayed(block.shortName(), "Unknown")));
        assertThrows(LocatorNotFoundException.class, () -> filter.should(haveSize(200)));
    }

}
