package io.perfeccionista.framework.pagefactory.filter;

import io.perfeccionista.framework.AbstractWebSeleniumParallelTest;
import io.perfeccionista.framework.pagefactory.dispatcher.context.WebPageContext;
import io.perfeccionista.framework.pagefactory.elements.WebRadioGroup;
import io.perfeccionista.framework.pagefactory.pageobjects.ElementsPage;
import io.perfeccionista.framework.pagefactory.pageobjects.HomePage;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static io.perfeccionista.framework.Web.*;
import static io.perfeccionista.framework.value.Values.intEquals;
import static io.perfeccionista.framework.value.Values.intGreaterThanOrEqual;
import static io.perfeccionista.framework.value.Values.stringEquals;
import static io.perfeccionista.framework.value.Values.stringStartsWith;

@Tag("WebElement") @Tag("WebRadioGroup")
class WebRadioGroupFiltersTest extends AbstractWebSeleniumParallelTest {

    @Test
    void webRadioGroupFilterEmptyConditionTest() {
        var context = initWebPageContext();
        context.getPage(HomePage.class).leftMenu()
                .select(stringEquals("Elements"));

        var elementsPage = context.getPage(ElementsPage.class);
        var radioGroup = elementsPage.radioGroup()
                .should(beDisplayed());

        radioGroup.filterBuilder(emptyWebRadioButtonFilter())
                .should(haveSize(3));
        radioGroup.filterBuilder(emptyWebRadioButtonFilter().subtract(allRadioButtons()))
                .should(haveSize(intEquals(0)));
    }

    @Test
    void webRadioGroupFilterRadioButtonIndexConditionTest() {
        WebPageContext context = initWebPageContext();
        context.getPage(HomePage.class).leftMenu()
                .select(stringEquals("Elements"));

        ElementsPage elementsPage = context.getPage(ElementsPage.class);
        WebRadioGroup radioGroup = elementsPage.radioGroup()
                .should(beDisplayed());

        radioGroup.filterBuilder(with(radioButtonIndex(intGreaterThanOrEqual(1))))
                .should(haveSize(2));
        radioGroup.filterBuilder(without(radioButtonIndex(intGreaterThanOrEqual(1))))
                .should(haveSize(intEquals(1)));
    }

    @Test
    void webRadioGroupFilterRadioButtonSelectedConditionTest() {
        WebPageContext context = initWebPageContext();
        context.getPage(HomePage.class).leftMenu()
                .select(stringEquals("Elements"));

        ElementsPage elementsPage = context.getPage(ElementsPage.class);
        WebRadioGroup radioGroup = elementsPage.radioGroup()
                .should(beDisplayed());

        // By Element
        radioGroup.filterBuilder(with(selected()))
                .should(haveSize(1));
        radioGroup.filterBuilder(with(notSelected()))
                .should(haveSize(2));

        radioGroup.filterBuilder(without(selected()))
                .should(haveSize(intEquals(2)));
        radioGroup.filterBuilder(without(notSelected()))
                .should(haveSize(intEquals(1)));
    }

    @Test
    void webRadioGroupFilterElementLabelConditionTest() {
        WebPageContext context = initWebPageContext();
        context.getPage(HomePage.class).leftMenu()
                .select(stringEquals("Elements"));

        ElementsPage elementsPage = context.getPage(ElementsPage.class);
        WebRadioGroup radioGroup = elementsPage.radioGroup()
                .should(beDisplayed());

        // By Element
        radioGroup.filterBuilder(with(containsLabel("Label 3")))
                .should(haveSize(1));
        radioGroup.filterBuilder(with(containsLabel(stringStartsWith("Label"))))
                .should(haveSize(3));
        radioGroup.filterBuilder(with(notContainLabel("Label 3")))
                .should(haveSize(2));
        radioGroup.filterBuilder(with(notContainLabel(stringStartsWith("Label"))))
                .should(haveSize(0));

        radioGroup.filterBuilder(without(containsLabel(stringEquals("Label 3"))))
                .should(haveSize(intEquals(2)));
        radioGroup.filterBuilder(without(containsLabel(stringStartsWith("Label"))))
                .should(haveSize(intEquals(0)));
        radioGroup.filterBuilder(without(notContainLabel(stringEquals("Label 3"))))
                .should(haveSize(intEquals(1)));
        radioGroup.filterBuilder(without(notContainLabel(stringStartsWith("Label"))))
                .should(haveSize(intEquals(3)));
    }

    @Test
    void webRadioGroupFilterElementEnabledConditionTest() {
        WebPageContext context = initWebPageContext();
        context.getPage(HomePage.class).leftMenu()
                .select(stringEquals("Elements"));

        ElementsPage elementsPage = context.getPage(ElementsPage.class);
        WebRadioGroup radioGroup = elementsPage.radioGroup()
                .should(beDisplayed());

        // By Element
        radioGroup.filterBuilder(with(enabled()))
                .should(haveSize(2));
        radioGroup.filterBuilder(with(disabled()))
                .should(haveSize(1));

        radioGroup.filterBuilder(without(enabled()))
                .should(haveSize(1));
        radioGroup.filterBuilder(without(disabled()))
                .should(haveSize(2));
    }

}
