package io.perfeccionista.framework.pagefactory.filter;

import io.perfeccionista.framework.Environment;
import io.perfeccionista.framework.pagefactory.AbstractUiTest;
import io.perfeccionista.framework.pagefactory.context.base.WebPageContext;
import io.perfeccionista.framework.pagefactory.elements.WebTextList;
import io.perfeccionista.framework.pagefactory.pageobjects.HomePage;
import io.perfeccionista.framework.pagefactory.pageobjects.TextListElementsPage;
import io.perfeccionista.framework.value.ValueService;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static io.perfeccionista.framework.matcher.WebElementAssertions.beDisplayed;
import static io.perfeccionista.framework.matcher.WebMultipleResultAssertions.haveSize;
import static io.perfeccionista.framework.pagefactory.filter.WebFilterConditions.containsTextBlock;
import static io.perfeccionista.framework.pagefactory.filter.WebFilterConditions.notContainsTextBlock;
import static io.perfeccionista.framework.pagefactory.filter.WebFilterConditions.textBlockIndex;
import static io.perfeccionista.framework.pagefactory.filter.WebFilters.emptyWebTextListFilter;
import static io.perfeccionista.framework.pagefactory.filter.WebFilters.with;
import static io.perfeccionista.framework.pagefactory.filter.WebFilters.without;

@Tag("Element") @Tag("WebTextList")
class WebTextListFiltersTest extends AbstractUiTest {

    @Test
    void webTextListFilterEmptyConditionTest(Environment env, ValueService value) {
        WebPageContext context = initWebPageContext(env, value);
        context.getPage(HomePage.class).leftMenu()
                .select(value.stringEquals("Text List Elements"));
        TextListElementsPage textListElementsPage = context.getPage(TextListElementsPage.class);
        WebTextList textList = textListElementsPage.textList()
                .should(beDisplayed());

        textList.filter(emptyWebTextListFilter())
                .should(haveSize(195));
    }

    @Test
    void webTextListFilterRowIndexConditionTest(Environment env, ValueService value) {
        WebPageContext context = initWebPageContext(env, value);
        context.getPage(HomePage.class).leftMenu()
                .select(value.stringEquals("Text List Elements"));
        TextListElementsPage textListElementsPage = context.getPage(TextListElementsPage.class);
        WebTextList textList = textListElementsPage.textList()
                .should(beDisplayed());

        textList.filter(with(textBlockIndex(value.intGreaterThanOrEqual(100))))
                .should(haveSize(95));
        textList.filter(without(textBlockIndex(value.intGreaterThanOrEqual(100))))
                .should(haveSize(100));
    }

    @Test
    void webTextListFilterElementTextConditionTest(Environment env, ValueService value) {
        WebPageContext context = initWebPageContext(env, value);
        context.getPage(HomePage.class).leftMenu()
                .select(value.stringEquals("Text List Elements"));
        TextListElementsPage textListElementsPage = context.getPage(TextListElementsPage.class);
        WebTextList textList = textListElementsPage.textList()
                .should(beDisplayed());

        // By Element
        textList.filter(with(containsTextBlock("Финляндия")))
                .should(haveSize(1));
        textList.filter(with(containsTextBlock(value.stringStartsWith("М"))))
                .should(haveSize(17));
        textList.filter(with(notContainsTextBlock(value.stringEquals("Финляндия"))))
                .should(haveSize(194));
        textList.filter(with(notContainsTextBlock(value.stringStartsWith("М"))))
                .should(haveSize(178));

        textList.filter(without(containsTextBlock("Финляндия")))
                .should(haveSize(194));
        textList.filter(without(containsTextBlock(value.stringStartsWith("М"))))
                .should(haveSize(178));
        textList.filter(without(notContainsTextBlock("Финляндия")))
                .should(haveSize(1));
        textList.filter(without(notContainsTextBlock(value.stringStartsWith("М"))))
                .should(haveSize(17));
    }

}
