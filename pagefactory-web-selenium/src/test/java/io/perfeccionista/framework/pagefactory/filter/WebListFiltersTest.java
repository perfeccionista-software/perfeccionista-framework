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
import static io.perfeccionista.framework.pagefactory.elements.WebBlock.frame;
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
        WebList list = listElementsPage.webList()
                .should(beDisplayed());

        list.filter(emptyWebListFilter())
                .should(haveSize(195));
    }

    @Test
    void webListFilterRowIndexConditionTest() {
        WebPageContext context = initWebPageContext();
        context.getPage(HomePage.class).leftMenu()
                .select("List Elements");

        ListElementsPage listElementsPage = context.getPage(ListElementsPage.class);
        WebList list = listElementsPage.webList()
                .should(beDisplayed());

        list.filter(with(blockIndex(intGreaterThanOrEqual(100))))
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
        WebList list = listElementsPage.webList()
                .should(beDisplayed());

        // TODO: Добавить проверки со стрингой в остальные тесты
        // By Element
        list.filter(with(containsText(frame(CountryBlock.class).shortName(), "Финляндия")))
                .should(haveSize(1));
        list.filter(with(containsText(frame(CountryBlock.class).shortName(), stringEquals("Финляндия"))))
                .should(haveSize(1));
        list.filter(with(containsText(frame(CountryBlock.class).shortName(), stringStartsWith("М"))))
                .should(haveSize(17));
        list.filter(with(notContainText(frame(CountryBlock.class).shortName(), "Финляндия")))
                .should(haveSize(194));
        list.filter(with(notContainText(frame(CountryBlock.class).shortName(), stringEquals("Финляндия"))))
                .should(haveSize(194));
        list.filter(with(notContainText(frame(CountryBlock.class).shortName(), stringStartsWith("М"))))
                .should(haveSize(178));

        list.filter(without(containsText(frame(CountryBlock.class).shortName(), stringEquals("Финляндия"))))
                .should(haveSize(194));
        list.filter(without(containsText(frame(CountryBlock.class).shortName(), stringStartsWith("М"))))
                .should(haveSize(178));
        list.filter(without(notContainText(frame(CountryBlock.class).shortName(), stringEquals("Финляндия"))))
                .should(haveSize(1));
        list.filter(without(notContainText(frame(CountryBlock.class).shortName(), stringStartsWith("М"))))
                .should(haveSize(17));

        list.filter(with(containsText(frame(CountryBlock.class).number(), intEquals(77))))
                .should(haveSize(1));
        list.filter(with(containsText(frame(CountryBlock.class).number(), intGreaterThanOrEqual(124))))
                .should(haveSize(72));
        list.filter(with(notContainText(frame(CountryBlock.class).number(), intEquals(77))))
                .should(haveSize(194));
        list.filter(with(notContainText(frame(CountryBlock.class).number(), intGreaterThanOrEqual(124))))
                .should(haveSize(123));

        list.filter(without(containsText(frame(CountryBlock.class).number(), intEquals(77))))
                .should(haveSize(194));
        list.filter(without(containsText(frame(CountryBlock.class).number(), intGreaterThanOrEqual(124))))
                .should(haveSize(123));
        list.filter(without(notContainText(frame(CountryBlock.class).number(), intEquals(77))))
                .should(haveSize(1));
        list.filter(without(notContainText(frame(CountryBlock.class).number(), intGreaterThanOrEqual(124))))
                .should(haveSize(72));

        // TODO: Добавить проверки со стрингой в остальные тесты
        // By Element name
        list.filter(with(containsText("Country name", "Финляндия")))
                .should(haveSize(1));
        list.filter(with(containsText("Country name", stringEquals("Финляндия"))))
                .should(haveSize(1));
        list.filter(with(containsText("Country name", stringStartsWith("М"))))
                .should(haveSize(17));
        list.filter(with(notContainText("Country name", "Финляндия")))
                .should(haveSize(194));
        list.filter(with(notContainText("Country name", stringEquals("Финляндия"))))
                .should(haveSize(194));
        list.filter(with(notContainText("Country name", stringStartsWith("М"))))
                .should(haveSize(178));

        list.filter(without(containsText("Country name", stringEquals("Финляндия"))))
                .should(haveSize(194));
        list.filter(without(containsText("Country name", stringStartsWith("М"))))
                .should(haveSize(178));
        list.filter(without(notContainText("Country name", stringEquals("Финляндия"))))
                .should(haveSize(1));
        list.filter(without(notContainText("Country name", stringStartsWith("М"))))
                .should(haveSize(17));

        list.filter(with(containsText("Number", intEquals(77))))
                .should(haveSize(1));
        list.filter(with(containsText("Number", intGreaterThanOrEqual(124))))
                .should(haveSize(72));
        list.filter(with(notContainText("Number", intEquals(77))))
                .should(haveSize(194));
        list.filter(with(notContainText("Number", intGreaterThanOrEqual(124))))
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
        WebList list = listElementsPage.webList()
                .should(beDisplayed());

        // By Element
        list.filter(with(containsProperty(frame(CountryBlock.class).fullName(), "prompt", "Финляндская Республика")))
                .should(haveSize(1));
        list.filter(with(containsProperty(frame(CountryBlock.class).fullName(), "prompt", stringStartsWith("М"))))
                .should(haveSize(5));
        list.filter(with(notContainProperty(frame(CountryBlock.class).fullName(), "prompt", stringEquals("Финляндская Республика"))))
                .should(haveSize(194));
        list.filter(with(notContainProperty(frame(CountryBlock.class).fullName(), "prompt", stringStartsWith("М"))))
                .should(haveSize(190));

        list.filter(without(containsProperty(frame(CountryBlock.class).fullName(), "prompt", "Финляндская Республика")))
                .should(haveSize(194));
        list.filter(without(containsProperty(frame(CountryBlock.class).fullName(), "prompt", stringStartsWith("М"))))
                .should(haveSize(190));
        list.filter(without(notContainProperty(frame(CountryBlock.class).fullName(), "prompt", stringEquals("Финляндская Республика"))))
                .should(haveSize(1));
        list.filter(without(notContainProperty(frame(CountryBlock.class).fullName(), "prompt", stringStartsWith("М"))))
                .should(haveSize(5));

        // By Element name
        list.filter(with(containsProperty("Full country name", "prompt", "Финляндская Республика")))
                .should(haveSize(1));
        list.filter(with(containsProperty("Full country name", "prompt", stringStartsWith("М"))))
                .should(haveSize(5));
        list.filter(with(notContainProperty("Full country name", "prompt", stringEquals("Финляндская Республика"))))
                .should(haveSize(194));
        list.filter(with(notContainProperty("Full country name", "prompt", stringStartsWith("М"))))
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
        WebList list = listElementsPage.webList()
                .should(beDisplayed());

        // By Element
        list.filter(with(containsLabel(frame(CountryBlock.class).checkbox(), stringEquals("86"))))
                .should(haveSize(1));
        list.filter(with(containsLabel(frame(CountryBlock.class).checkbox(), stringStartsWith("15"))))
                .should(haveSize(11));
        list.filter(with(notContainLabel(frame(CountryBlock.class).checkbox(), stringEquals("86"))))
                .should(haveSize(194));
        list.filter(with(notContainLabel(frame(CountryBlock.class).checkbox(), stringStartsWith("15"))))
                .should(haveSize(184));

        list.filter(without(containsLabel(frame(CountryBlock.class).checkbox(), stringEquals("86"))))
                .should(haveSize(194));
        list.filter(without(containsLabel(frame(CountryBlock.class).checkbox(), stringStartsWith("15"))))
                .should(haveSize(184));
        list.filter(without(notContainLabel(frame(CountryBlock.class).checkbox(), stringEquals("86"))))
                .should(haveSize(1));
        list.filter(without(notContainLabel(frame(CountryBlock.class).checkbox(), stringStartsWith("15"))))
                .should(haveSize(11));

        list.filter(with(containsLabel(frame(CountryBlock.class).checkbox(), intEquals(77))))
                .should(haveSize(1));
        list.filter(with(containsLabel(frame(CountryBlock.class).checkbox(), intGreaterThanOrEqual(124))))
                .should(haveSize(72));
        list.filter(with(notContainLabel(frame(CountryBlock.class).checkbox(), intEquals(77))))
                .should(haveSize(194));
        list.filter(with(notContainLabel(frame(CountryBlock.class).checkbox(), intGreaterThanOrEqual(124))))
                .should(haveSize(123));

        list.filter(without(containsLabel(frame(CountryBlock.class).checkbox(), intEquals(77))))
                .should(haveSize(194));
        list.filter(without(containsLabel(frame(CountryBlock.class).checkbox(), intGreaterThanOrEqual(124))))
                .should(haveSize(123));
        list.filter(without(notContainLabel(frame(CountryBlock.class).checkbox(), intEquals(77))))
                .should(haveSize(1));
        list.filter(without(notContainLabel(frame(CountryBlock.class).checkbox(), intGreaterThanOrEqual(124))))
                .should(haveSize(72));

        // By Element name
        list.filter(with(containsLabel("Select", stringEquals("86"))))
                .should(haveSize(1));
        list.filter(with(containsLabel("Select", stringStartsWith("15"))))
                .should(haveSize(11));
        list.filter(with(notContainLabel("Select", stringEquals("86"))))
                .should(haveSize(194));
        list.filter(with(notContainLabel("Select", stringStartsWith("15"))))
                .should(haveSize(184));

        list.filter(without(containsLabel("Select", stringEquals("86"))))
                .should(haveSize(194));
        list.filter(without(containsLabel("Select", stringStartsWith("15"))))
                .should(haveSize(184));
        list.filter(without(notContainLabel("Select", stringEquals("86"))))
                .should(haveSize(1));
        list.filter(without(notContainLabel("Select", stringStartsWith("15"))))
                .should(haveSize(11));

        list.filter(with(containsLabel("Select", intEquals(77))))
                .should(haveSize(1));
        list.filter(with(containsLabel("Select", intGreaterThanOrEqual(124))))
                .should(haveSize(72));
        list.filter(with(notContainLabel("Select", intEquals(77))))
                .should(haveSize(194));
        list.filter(with(notContainLabel("Select", intGreaterThanOrEqual(124))))
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
        WebList list = listElementsPage.webList()
                .should(beDisplayed());

        // By Element
        list.filter(with(enabled(frame(CountryBlock.class).checkbox())))
                .should(haveSize(189));
        list.filter(with(disabled(frame(CountryBlock.class).checkbox())))
                .should(haveSize(6));

        list.filter(without(enabled(frame(CountryBlock.class).checkbox())))
                .should(haveSize(6));
        list.filter(without(disabled(frame(CountryBlock.class).checkbox())))
                .should(haveSize(189));

        // By Element name
        list.filter(with(enabled("Select")))
                .should(haveSize(189));
        list.filter(with(disabled("Select")))
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
        WebList list = listElementsPage.webList()
                .should(beDisplayed());

        // By Element
        list.filter(with(selected(frame(CountryBlock.class).checkbox())))
                .should(haveSize(6));
        list.filter(with(notSelected(frame(CountryBlock.class).checkbox())))
                .should(haveSize(189));

        list.filter(without(selected(frame(CountryBlock.class).checkbox())))
                .should(haveSize(189));
        list.filter(without(notSelected(frame(CountryBlock.class).checkbox())))
                .should(haveSize(6));

        // By Element name
        list.filter(with(selected("Select")))
                .should(haveSize(6));
        list.filter(with(notSelected("Select")))
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
        WebList list = listElementsPage.webList()
                .should(beDisplayed());

        // By Element
        list.filter(with(present(frame(CountryBlock.class).shortName())))
                .should(haveSize(193));
        list.filter(with(notPresent(frame(CountryBlock.class).shortName())))
                .should(haveSize(2));

        list.filter(without(present(frame(CountryBlock.class).shortName())))
                .should(haveSize(2));
        list.filter(without(notPresent(frame(CountryBlock.class).shortName())))
                .should(haveSize(193));

        // By Element name
        list.filter(with(present("Country name")))
                .should(haveSize(193));
        list.filter(with(notPresent("Country name")))
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
        WebList list = listElementsPage.webList()
                .should(beDisplayed());

        // By Element
        // Для элементов, которых нет в DOM
        list.filter(with(displayed(frame(CountryBlock.class).shortName())))
                .should(haveSize(193));
        list.filter(with(notDisplayed(frame(CountryBlock.class).shortName())))
                .should(haveSize(2));

        list.filter(without(displayed(frame(CountryBlock.class).shortName())))
                .should(haveSize(2));
        list.filter(without(notDisplayed(frame(CountryBlock.class).shortName())))
                .should(haveSize(193));

        // Для элементов, которые есть в DOM, но не отображаются
        list.filter(with(displayed(frame(CountryBlock.class).populationUnit())))
                .should(haveSize(186));
        list.filter(with(notDisplayed(frame(CountryBlock.class).populationUnit())))
                .should(haveSize(9));

        list.filter(without(displayed(frame(CountryBlock.class).populationUnit())))
                .should(haveSize(9));
        list.filter(without(notDisplayed(frame(CountryBlock.class).populationUnit())))
                .should(haveSize(186));

        // By Element name
        // Для элементов, которых нет в DOM
        list.filter(with(displayed("Country name")))
                .should(haveSize(193));
        list.filter(with(notDisplayed("Country name")))
                .should(haveSize(2));

        list.filter(without(displayed("Country name")))
                .should(haveSize(2));
        list.filter(without(notDisplayed("Country name")))
                .should(haveSize(193));

        // Для элементов, которые есть в DOM, но не отображаются
        list.filter(with(displayed("Population unit")))
                .should(haveSize(186));
        list.filter(with(notDisplayed("Population unit")))
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
        WebList list = listElementsPage.webList()
                .should(beDisplayed());

        // By Element
        list.filter(with(componentPresent(frame(CountryBlock.class).shortName(), "Self")))
                .should(haveSize(193));
        list.filter(with(componentNotPresent(frame(CountryBlock.class).shortName(), "Self")))
                .should(haveSize(2));

        list.filter(without(componentPresent(frame(CountryBlock.class).shortName(), "Self")))
                .should(haveSize(2));
        list.filter(without(componentNotPresent(frame(CountryBlock.class).shortName(), "Self")))
                .should(haveSize(193));

        // By Element name
        list.filter(with(componentPresent("Country name", "Self")))
                .should(haveSize(193));
        list.filter(with(componentNotPresent("Country name", "Self")))
                .should(haveSize(2));

        list.filter(without(componentPresent("Country name", "Self")))
                .should(haveSize(2));
        list.filter(without(componentNotPresent("Country name", "Self")))
                .should(haveSize(193));

        WebListFilter filter = list.filter(with(componentPresent(frame(CountryBlock.class).shortName(), "Unknown")));
        assertThrows(LocatorNotFoundException.class, () -> filter.should(haveSize(200)));
    }

    @Test
    void webListFilterElementComponentDisplayedConditionTest() {
        WebPageContext context = initWebPageContext();
        context.getPage(HomePage.class).leftMenu()
                .select("List Elements");

        ListElementsPage listElementsPage = context.getPage(ListElementsPage.class);
        WebList list = listElementsPage.webList()
                .should(beDisplayed());

        // By Element
        list.filter(with(componentDisplayed(frame(CountryBlock.class).populationUnit(), "Self")))
                .should(haveSize(186));
        list.filter(with(componentNotDisplayed(frame(CountryBlock.class).populationUnit(), "Self")))
                .should(haveSize(9));

        list.filter(without(componentDisplayed(frame(CountryBlock.class).populationUnit(), "Self")))
                .should(haveSize(9));
        list.filter(without(componentNotDisplayed(frame(CountryBlock.class).populationUnit(), "Self")))
                .should(haveSize(186));

        // By Element name
        list.filter(with(componentDisplayed("Population unit", "Self")))
                .should(haveSize(186));
        list.filter(with(componentNotDisplayed("Population unit", "Self")))
                .should(haveSize(9));

        list.filter(without(componentDisplayed("Population unit", "Self")))
                .should(haveSize(9));
        list.filter(without(componentNotDisplayed("Population unit", "Self")))
                .should(haveSize(186));

        WebListFilter filter = list.filter(with(componentDisplayed(frame(CountryBlock.class).shortName(), "Unknown")));
        assertThrows(LocatorNotFoundException.class, () -> filter.should(haveSize(200)));
    }

}
