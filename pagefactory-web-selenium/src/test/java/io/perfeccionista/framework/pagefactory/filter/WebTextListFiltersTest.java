package io.perfeccionista.framework.pagefactory.filter;

import io.perfeccionista.framework.AbstractWebSeleniumParallelTest;
import io.perfeccionista.framework.pagefactory.dispatcher.context.WebPageContext;
import io.perfeccionista.framework.pagefactory.elements.WebTextList;
import io.perfeccionista.framework.pagefactory.pageobjects.HomePage;
import io.perfeccionista.framework.pagefactory.pageobjects.TextListElementsPage;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static io.perfeccionista.framework.Web.*;
import static io.perfeccionista.framework.value.Values.intGreaterThanOrEqual;
import static io.perfeccionista.framework.value.Values.stringEquals;
import static io.perfeccionista.framework.value.Values.stringStartsWith;

@Tag("WebElement") @Tag("WebTextList")
class WebTextListFiltersTest extends AbstractWebSeleniumParallelTest {

    @Test
    void webTextListFilterEmptyConditionTest() {
        WebPageContext context = initWebPageContext();
        context.getPage(HomePage.class).leftMenu()
                .select(stringEquals("Text List Elements"));

        TextListElementsPage textListElementsPage = context.getPage(TextListElementsPage.class);
        WebTextList textList = textListElementsPage.textList()
                .should(beDisplayed());

        textList.filter(emptyWebTextListFilter())
                .should(haveSize(195));
    }

    @Test
    void webTextListFilterRowIndexConditionTest() {
        WebPageContext context = initWebPageContext();
        context.getPage(HomePage.class).leftMenu()
                .select(stringEquals("Text List Elements"));

        TextListElementsPage textListElementsPage = context.getPage(TextListElementsPage.class);
        WebTextList textList = textListElementsPage.textList()
                .should(beDisplayed());

        textList.filter(with(textBlockIndex(intGreaterThanOrEqual(100))))
                .should(haveSize(95));
        textList.filter(without(textBlockIndex(intGreaterThanOrEqual(100))))
                .should(haveSize(100));
    }

    @Test
    void webTextListFilterElementTextConditionTest() {
        WebPageContext context = initWebPageContext();
        context.getPage(HomePage.class).leftMenu()
                .select(stringEquals("Text List Elements"));

        TextListElementsPage textListElementsPage = context.getPage(TextListElementsPage.class);
        WebTextList textList = textListElementsPage.textList()
                .should(beDisplayed());

        // By Element
        textList.filter(with(containsTextBlock("Финляндия")))
                .should(haveSize(1));
        textList.filter(with(containsTextBlock(stringStartsWith("М"))))
                .should(haveSize(17));
        textList.filter(with(notContainTextBlock(stringEquals("Финляндия"))))
                .should(haveSize(194));
        textList.filter(with(notContainTextBlock(stringStartsWith("М"))))
                .should(haveSize(178));

        textList.filter(without(containsTextBlock("Финляндия")))
                .should(haveSize(194));
        textList.filter(without(containsTextBlock(stringStartsWith("М"))))
                .should(haveSize(178));
        textList.filter(without(notContainTextBlock("Финляндия")))
                .should(haveSize(1));
        textList.filter(without(notContainTextBlock(stringStartsWith("М"))))
                .should(haveSize(17));
    }

}
