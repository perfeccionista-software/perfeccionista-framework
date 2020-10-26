package io.perfeccionista.framework.pagefactory.filter;

import io.perfeccionista.framework.Environment;
import io.perfeccionista.framework.pagefactory.AbstractUiTest;
import io.perfeccionista.framework.pagefactory.context.base.WebPageContext;
import io.perfeccionista.framework.pagefactory.elements.WebRadioGroup;
import io.perfeccionista.framework.pagefactory.pageobjects.ElementsPage;
import io.perfeccionista.framework.pagefactory.pageobjects.HomePage;
import io.perfeccionista.framework.value.ValueService;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static io.perfeccionista.framework.matcher.WebElementAssertions.beDisplayed;
import static io.perfeccionista.framework.matcher.WebMultipleResultAssertions.haveSize;
import static io.perfeccionista.framework.pagefactory.filter.WebFilterConditions.allRadioButtons;
import static io.perfeccionista.framework.pagefactory.filter.WebFilterConditions.containsLabel;
import static io.perfeccionista.framework.pagefactory.filter.WebFilterConditions.disabled;
import static io.perfeccionista.framework.pagefactory.filter.WebFilterConditions.enabled;
import static io.perfeccionista.framework.pagefactory.filter.WebFilterConditions.notContainsLabel;
import static io.perfeccionista.framework.pagefactory.filter.WebFilterConditions.notSelected;
import static io.perfeccionista.framework.pagefactory.filter.WebFilterConditions.radioButtonIndex;
import static io.perfeccionista.framework.pagefactory.filter.WebFilterConditions.selected;
import static io.perfeccionista.framework.pagefactory.filter.WebFilters.emptyWebRadioButtonFilter;
import static io.perfeccionista.framework.pagefactory.filter.WebFilters.with;
import static io.perfeccionista.framework.pagefactory.filter.WebFilters.without;

@Tag("Element") @Tag("WebRadioGroup")
class WebRadioGroupFiltersTest extends AbstractUiTest {

    @Test
    void webRadioGroupFilterEmptyConditionTest(Environment env, ValueService value) {
        var context = initWebPageContext(env, value);
        context.getPage(HomePage.class).leftMenu()
                .select(value.stringEquals("Elements"));
        var elementsPage = context.getPage(ElementsPage.class);
        var radioGroup = elementsPage.radioGroup()
                .should(beDisplayed());

        radioGroup.filter(emptyWebRadioButtonFilter())
                .should(haveSize(3));
        radioGroup.filter(emptyWebRadioButtonFilter().subtract(allRadioButtons()))
                .should(haveSize(value.intEquals(0)));
    }

    @Test
    void webRadioGroupFilterRadioButtonIndexConditionTest(Environment env, ValueService value) {
        WebPageContext context = initWebPageContext(env, value);
        context.getPage(HomePage.class).leftMenu()
                .select(value.stringEquals("Elements"));
        ElementsPage elementsPage = context.getPage(ElementsPage.class);
        WebRadioGroup radioGroup = elementsPage.radioGroup()
                .should(beDisplayed());

        radioGroup.filter(with(radioButtonIndex(value.intGreaterThanOrEqual(1))))
                .should(haveSize(2));
        radioGroup.filter(without(radioButtonIndex(value.intGreaterThanOrEqual(1))))
                .should(haveSize(value.intEquals(1)));
    }

    @Test
    void webRadioGroupFilterRadioButtonSelectedConditionTest(Environment env, ValueService value) {
        WebPageContext context = initWebPageContext(env, value);
        context.getPage(HomePage.class).leftMenu()
                .select(value.stringEquals("Elements"));
        ElementsPage elementsPage = context.getPage(ElementsPage.class);
        WebRadioGroup radioGroup = elementsPage.radioGroup()
                .should(beDisplayed());

        // By Element
        radioGroup.filter(with(selected()))
                .should(haveSize(1));
        radioGroup.filter(with(notSelected()))
                .should(haveSize(2));

        radioGroup.filter(without(selected()))
                .should(haveSize(value.intEquals(2)));
        radioGroup.filter(without(notSelected()))
                .should(haveSize(value.intEquals(1)));
    }

    @Test
    void webRadioGroupFilterElementLabelConditionTest(Environment env, ValueService value) {
        WebPageContext context = initWebPageContext(env, value);
        context.getPage(HomePage.class)
                .leftMenu()
                .select(value.stringEquals("Elements"));
        ElementsPage elementsPage = context.getPage(ElementsPage.class);
        WebRadioGroup radioGroup = elementsPage.radioGroup()
                .should(beDisplayed());

        // By Element
        radioGroup.filter(with(containsLabel("Label 3")))
                .should(haveSize(1));
        radioGroup.filter(with(containsLabel(value.stringStartsWith("Label"))))
                .should(haveSize(3));
        radioGroup.filter(with(notContainsLabel("Label 3")))
                .should(haveSize(2));
        radioGroup.filter(with(notContainsLabel(value.stringStartsWith("Label"))))
                .should(haveSize(0));

        radioGroup.filter(without(containsLabel(value.stringEquals("Label 3"))))
                .should(haveSize(value.intEquals(2)));
        radioGroup.filter(without(containsLabel(value.stringStartsWith("Label"))))
                .should(haveSize(value.intEquals(0)));
        radioGroup.filter(without(notContainsLabel(value.stringEquals("Label 3"))))
                .should(haveSize(value.intEquals(1)));
        radioGroup.filter(without(notContainsLabel(value.stringStartsWith("Label"))))
                .should(haveSize(value.intEquals(3)));
    }

    @Test
    void webRadioGroupFilterElementEnabledConditionTest(Environment env, ValueService value) {
        WebPageContext context = initWebPageContext(env, value);
        context.getPage(HomePage.class)
                .leftMenu()
                .select(value.stringEquals("Elements"));
        ElementsPage elementsPage = context.getPage(ElementsPage.class);
        WebRadioGroup radioGroup = elementsPage.radioGroup()
                .should(beDisplayed());

        // By Element
        radioGroup.filter(with(enabled()))
                .should(haveSize(2));
        radioGroup.filter(with(disabled()))
                .should(haveSize(1));

        radioGroup.filter(without(enabled()))
                .should(haveSize(1));
        radioGroup.filter(without(disabled()))
                .should(haveSize(2));
    }

}