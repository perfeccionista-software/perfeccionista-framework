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
import static org.junit.jupiter.api.Assertions.assertThrows;

// TODO: Сделать тесты на несколько условий и на несколько фильтров
@Tag("WebElement") @Tag("WebList")
class WebListFiltersTest extends AbstractWebSeleniumParallelTest {

    @Test
    void webListFilterEmptyConditionTest() {
        WebPageContext context = initWebPageContext();
        context.getPage(HomePage.class).leftMenu()
                .select("List Elements");

        ListElementsPage listElementsPage = context.getPage(ListElementsPage.class);
        WebList<CountryBlock> list = listElementsPage.webList()
                .should(beDisplayed());

        list.should(haveSize(195));

        list.filter(emptyWebListFilter())
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

        list.filterByCondition(blockIndex(intGreaterThanOrEqual(100)))
                .should(haveSize(intEquals(95)));
        list.filter(without(blockIndex(intGreaterThanOrEqual(100))))
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
        list.filterByCondition(block -> containsText(block.shortName(), "Финляндия"))
                .should(haveSize(1));
        list.filterByCondition(block -> containsText(block.shortName(), stringEquals("Финляндия")))
                .should(haveSize(1));
        list.filterByCondition(block -> containsText(block.shortName(), stringStartsWith("М")))
                .should(haveSize(17));
        list.filterByCondition(block -> notContainText(block.shortName(), "Финляндия"))
                .should(haveSize(194));
        list.filterByCondition(block -> notContainText(block.shortName(), stringEquals("Финляндия")))
                .should(haveSize(194));
        list.filterByCondition(block -> notContainText(block.shortName(), stringStartsWith("М")))
                .should(haveSize(178));

        list.filter(block -> without(containsText(block.shortName(), stringEquals("Финляндия"))))
                .should(haveSize(194));
        list.filter(block -> without(containsText(block.shortName(), stringStartsWith("М"))))
                .should(haveSize(178));
        list.filter(block -> without(notContainText(block.shortName(), stringEquals("Финляндия"))))
                .should(haveSize(1));
        list.filter(block -> without(notContainText(block.shortName(), stringStartsWith("М"))))
                .should(haveSize(17));

        list.filterByCondition(block -> containsText(block.number(), intEquals(77)))
                .should(haveSize(1));
        list.filterByCondition(block -> containsText(block.number(), intGreaterThanOrEqual(124)))
                .should(haveSize(72));
        list.filterByCondition(block -> notContainText(block.number(), intEquals(77)))
                .should(haveSize(194));
        list.filterByCondition(block -> notContainText(block.number(), intGreaterThanOrEqual(124)))
                .should(haveSize(123));

        list.filter(block -> without(containsText(block.number(), intEquals(77))))
                .should(haveSize(194));
        list.filter(block -> without(containsText(block.number(), intGreaterThanOrEqual(124))))
                .should(haveSize(123));
        list.filter(block -> without(notContainText(block.number(), intEquals(77))))
                .should(haveSize(1));
        list.filter(block -> without(notContainText(block.number(), intGreaterThanOrEqual(124))))
                .should(haveSize(72));

        // TODO: Добавить проверки со стрингой в остальные тесты
        // By Element name
        list.filterByCondition(containsText("Country name", "Финляндия"))
                .should(haveSize(1));
        list.filterByCondition(containsText("Country name", stringEquals("Финляндия")))
                .should(haveSize(1));
        list.filterByCondition(containsText("Country name", stringStartsWith("М")))
                .should(haveSize(17));
        list.filterByCondition(notContainText("Country name", "Финляндия"))
                .should(haveSize(194));
        list.filterByCondition(notContainText("Country name", stringEquals("Финляндия")))
                .should(haveSize(194));
        list.filterByCondition(notContainText("Country name", stringStartsWith("М")))
                .should(haveSize(178));

        list.filter(without(containsText("Country name", stringEquals("Финляндия"))))
                .should(haveSize(194));
        list.filter(without(containsText("Country name", stringStartsWith("М"))))
                .should(haveSize(178));
        list.filter(without(notContainText("Country name", stringEquals("Финляндия"))))
                .should(haveSize(1));
        list.filter(without(notContainText("Country name", stringStartsWith("М"))))
                .should(haveSize(17));

        list.filterByCondition(containsText("Number", intEquals(77)))
                .should(haveSize(1));
        list.filterByCondition(containsText("Number", intGreaterThanOrEqual(124)))
                .should(haveSize(72));
        list.filterByCondition(notContainText("Number", intEquals(77)))
                .should(haveSize(194));
        list.filterByCondition(notContainText("Number", intGreaterThanOrEqual(124)))
                .should(haveSize(123));

        list.filter(without(containsText("Number", intEquals(77))))
                .should(haveSize(194));
        list.filter(without(containsText("Number", intGreaterThanOrEqual(124))))
                .should(haveSize(123));
        list.filter(without(notContainText("Number", intEquals(77))))
                .should(haveSize(1));
        list.filter(without(notContainText("Number", intGreaterThanOrEqual(124))))
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
        list.filterByCondition(block -> containsProperty(block.fullName(), "prompt", "Финляндская Республика"))
                .should(haveSize(1));
        list.filterByCondition(block -> containsProperty(block.fullName(), "prompt", stringStartsWith("М")))
                .should(haveSize(5));
        list.filterByCondition(block -> notContainProperty(block.fullName(), "prompt", stringEquals("Финляндская Республика")))
                .should(haveSize(194));
        list.filterByCondition(block -> notContainProperty(block.fullName(), "prompt", stringStartsWith("М")))
                .should(haveSize(190));

        list.filter(block -> without(containsProperty(block.fullName(), "prompt", "Финляндская Республика")))
                .should(haveSize(194));
        list.filter(block -> without(containsProperty(block.fullName(), "prompt", stringStartsWith("М"))))
                .should(haveSize(190));
        list.filter(block -> without(notContainProperty(block.fullName(), "prompt", stringEquals("Финляндская Республика"))))
                .should(haveSize(1));
        list.filter(block -> without(notContainProperty(block.fullName(), "prompt", stringStartsWith("М"))))
                .should(haveSize(5));

        // By Element name
        list.filterByCondition(containsProperty("Full country name", "prompt", "Финляндская Республика"))
                .should(haveSize(1));
        list.filterByCondition(containsProperty("Full country name", "prompt", stringStartsWith("М")))
                .should(haveSize(5));
        list.filterByCondition(notContainProperty("Full country name", "prompt", stringEquals("Финляндская Республика")))
                .should(haveSize(194));
        list.filterByCondition(notContainProperty("Full country name", "prompt", stringStartsWith("М")))
                .should(haveSize(190));

        list.filter(without(containsProperty("Full country name", "prompt", "Финляндская Республика")))
                .should(haveSize(194));
        list.filter(without(containsProperty("Full country name", "prompt", stringStartsWith("М"))))
                .should(haveSize(190));
        list.filter(without(notContainProperty("Full country name", "prompt", stringEquals("Финляндская Республика"))))
                .should(haveSize(1));
        list.filter(without(notContainProperty("Full country name", "prompt", stringStartsWith("М"))))
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
        list.filterByCondition(block -> containsLabel(block.checkbox(), stringEquals("86")))
                .should(haveSize(1));
        list.filterByCondition(block -> containsLabel(block.checkbox(), stringStartsWith("15")))
                .should(haveSize(11));
        list.filterByCondition(block -> notContainLabel(block.checkbox(), stringEquals("86")))
                .should(haveSize(194));
        list.filterByCondition(block -> notContainLabel(block.checkbox(), stringStartsWith("15")))
                .should(haveSize(184));

        list.filter(block -> without(containsLabel(block.checkbox(), stringEquals("86"))))
                .should(haveSize(194));
        list.filter(block -> without(containsLabel(block.checkbox(), stringStartsWith("15"))))
                .should(haveSize(184));
        list.filter(block -> without(notContainLabel(block.checkbox(), stringEquals("86"))))
                .should(haveSize(1));
        list.filter(block -> without(notContainLabel(block.checkbox(), stringStartsWith("15"))))
                .should(haveSize(11));

        list.filterByCondition(block -> containsLabel(block.checkbox(), intEquals(77)))
                .should(haveSize(1));
        list.filterByCondition(block -> containsLabel(block.checkbox(), intGreaterThanOrEqual(124)))
                .should(haveSize(72));
        list.filterByCondition(block -> notContainLabel(block.checkbox(), intEquals(77)))
                .should(haveSize(194));
        list.filterByCondition(block -> notContainLabel(block.checkbox(), intGreaterThanOrEqual(124)))
                .should(haveSize(123));

        list.filter(block -> without(containsLabel(block.checkbox(), intEquals(77))))
                .should(haveSize(194));
        list.filter(block -> without(containsLabel(block.checkbox(), intGreaterThanOrEqual(124))))
                .should(haveSize(123));
        list.filter(block -> without(notContainLabel(block.checkbox(), intEquals(77))))
                .should(haveSize(1));
        list.filter(block -> without(notContainLabel(block.checkbox(), intGreaterThanOrEqual(124))))
                .should(haveSize(72));

        // By Element name
        list.filterByCondition(containsLabel("Select", stringEquals("86")))
                .should(haveSize(1));
        list.filterByCondition(containsLabel("Select", stringStartsWith("15")))
                .should(haveSize(11));
        list.filterByCondition(notContainLabel("Select", stringEquals("86")))
                .should(haveSize(194));
        list.filterByCondition(notContainLabel("Select", stringStartsWith("15")))
                .should(haveSize(184));

        list.filter(without(containsLabel("Select", stringEquals("86"))))
                .should(haveSize(194));
        list.filter(without(containsLabel("Select", stringStartsWith("15"))))
                .should(haveSize(184));
        list.filter(without(notContainLabel("Select", stringEquals("86"))))
                .should(haveSize(1));
        list.filter(without(notContainLabel("Select", stringStartsWith("15"))))
                .should(haveSize(11));

        list.filterByCondition(containsLabel("Select", intEquals(77)))
                .should(haveSize(1));
        list.filterByCondition(containsLabel("Select", intGreaterThanOrEqual(124)))
                .should(haveSize(72));
        list.filterByCondition(notContainLabel("Select", intEquals(77)))
                .should(haveSize(194));
        list.filterByCondition(notContainLabel("Select", intGreaterThanOrEqual(124)))
                .should(haveSize(123));

        list.filter(without(containsLabel("Select", intEquals(77))))
                .should(haveSize(194));
        list.filter(without(containsLabel("Select", intGreaterThanOrEqual(124))))
                .should(haveSize(123));
        list.filter(without(notContainLabel("Select", intEquals(77))))
                .should(haveSize(1));
        list.filter(without(notContainLabel("Select", intGreaterThanOrEqual(124))))
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
        list.filterByCondition(block -> enabled(block.checkbox()))
                .should(haveSize(189));
        list.filterByCondition(block -> disabled(block.checkbox()))
                .should(haveSize(6));

        list.filter(block -> without(enabled(block.checkbox())))
                .should(haveSize(6));
        list.filter(block -> without(disabled(block.checkbox())))
                .should(haveSize(189));

        // By Element name
        list.filterByCondition(enabled("Select"))
                .should(haveSize(189));
        list.filterByCondition(disabled("Select"))
                .should(haveSize(6));

        list.filter(without(enabled("Select")))
                .should(haveSize(6));
        list.filter(without(disabled("Select")))
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
        list.filterByCondition(block -> selected(block.checkbox()))
                .should(haveSize(6));
        list.filterByCondition(block -> notSelected(block.checkbox()))
                .should(haveSize(189));

        list.filter(block -> without(selected(block.checkbox())))
                .should(haveSize(189));
        list.filter(block -> without(notSelected(block.checkbox())))
                .should(haveSize(6));

        // By Element name
        list.filterByCondition(selected("Select"))
                .should(haveSize(6));
        list.filterByCondition(notSelected("Select"))
                .should(haveSize(189));

        list.filter(without(selected("Select")))
                .should(haveSize(189));
        list.filter(without(notSelected("Select")))
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
        list.filterByCondition(block -> present(block.shortName()))
                .should(haveSize(193));
        list.filterByCondition(block -> notPresent(block.shortName()))
                .should(haveSize(2));

        list.filter(block -> without(present(block.shortName())))
                .should(haveSize(2));
        list.filter(block -> without(notPresent(block.shortName())))
                .should(haveSize(193));

        // By Element name
        list.filterByCondition(present("Country name"))
                .should(haveSize(193));
        list.filterByCondition(notPresent("Country name"))
                .should(haveSize(2));

        list.filter(without(present("Country name")))
                .should(haveSize(2));
        list.filter(without(notPresent("Country name")))
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
        list.filterByCondition(block -> displayed(block.shortName()))
                .should(haveSize(193));
        list.filterByCondition(block -> notDisplayed(block.shortName()))
                .should(haveSize(2));

        list.filter(block -> without(displayed(block.shortName())))
                .should(haveSize(2));
        list.filter(block -> without(notDisplayed(block.shortName())))
                .should(haveSize(193));

        // Для элементов, которые есть в DOM, но не отображаются
        list.filterByCondition(block -> displayed(block.populationUnit()))
                .should(haveSize(186));
        list.filterByCondition(block -> notDisplayed(block.populationUnit()))
                .should(haveSize(9));

        list.filter(block -> without(displayed(block.populationUnit())))
                .should(haveSize(9));
        list.filter(block -> without(notDisplayed(block.populationUnit())))
                .should(haveSize(186));

        // By Element name
        // Для элементов, которых нет в DOM
        list.filterByCondition(displayed("Country name"))
                .should(haveSize(193));
        list.filterByCondition(notDisplayed("Country name"))
                .should(haveSize(2));

        list.filter(without(displayed("Country name")))
                .should(haveSize(2));
        list.filter(without(notDisplayed("Country name")))
                .should(haveSize(193));

        // Для элементов, которые есть в DOM, но не отображаются
        list.filterByCondition(displayed("Population unit"))
                .should(haveSize(186));
        list.filterByCondition(notDisplayed("Population unit"))
                .should(haveSize(9));

        list.filter(without(displayed("Population unit")))
                .should(haveSize(9));
        list.filter(without(notDisplayed("Population unit")))
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
        list.filterByCondition(block -> componentPresent(block.shortName(), "Self"))
                .should(haveSize(193));
        list.filterByCondition(block -> componentNotPresent(block.shortName(), "Self"))
                .should(haveSize(2));

        list.filter(block -> without(componentPresent(block.shortName(), "Self")))
                .should(haveSize(2));
        list.filter(block -> without(componentNotPresent(block.shortName(), "Self")))
                .should(haveSize(193));

        // By Element name
        list.filterByCondition(componentPresent("Country name", "Self"))
                .should(haveSize(193));
        list.filterByCondition(componentNotPresent("Country name", "Self"))
                .should(haveSize(2));

        list.filter(without(componentPresent("Country name", "Self")))
                .should(haveSize(2));
        list.filter(without(componentNotPresent("Country name", "Self")))
                .should(haveSize(193));

        WebListFilter<CountryBlock> filter = list.filter(block -> with(componentPresent(block.shortName(), "Unknown")));
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
        list.filterByCondition(block -> componentDisplayed(block.populationUnit(), "Self"))
                .should(haveSize(186));
        list.filterByCondition(block -> componentNotDisplayed(block.populationUnit(), "Self"))
                .should(haveSize(9));

        list.filter(block -> without(componentDisplayed(block.populationUnit(), "Self")))
                .should(haveSize(9));
        list.filter(block -> without(componentNotDisplayed(block.populationUnit(), "Self")))
                .should(haveSize(186));

        // By Element name
        list.filterByCondition(componentDisplayed("Population unit", "Self"))
                .should(haveSize(186));
        list.filterByCondition(componentNotDisplayed("Population unit", "Self"))
                .should(haveSize(9));

        list.filter(without(componentDisplayed("Population unit", "Self")))
                .should(haveSize(9));
        list.filter(without(componentNotDisplayed("Population unit", "Self")))
                .should(haveSize(186));

        WebListFilter<CountryBlock> filter = list.filter(block -> with(componentDisplayed(block.shortName(), "Unknown")));
        assertThrows(LocatorNotFoundException.class, () -> filter.should(haveSize(200)));
    }

}
