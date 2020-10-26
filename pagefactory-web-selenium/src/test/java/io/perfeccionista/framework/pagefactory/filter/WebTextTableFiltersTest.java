package io.perfeccionista.framework.pagefactory.filter;

import io.perfeccionista.framework.Environment;
import io.perfeccionista.framework.pagefactory.AbstractUiTest;
import io.perfeccionista.framework.pagefactory.context.base.WebPageContext;
import io.perfeccionista.framework.pagefactory.elements.WebTextTable;
import io.perfeccionista.framework.pagefactory.pageobjects.HomePage;
import io.perfeccionista.framework.pagefactory.pageobjects.TextTablePage;
import io.perfeccionista.framework.value.ValueService;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static io.perfeccionista.framework.matcher.WebElementAssertions.beDisplayed;
import static io.perfeccionista.framework.matcher.WebMultipleResultAssertions.haveSize;
import static io.perfeccionista.framework.pagefactory.filter.WebFilterConditions.containsTextCell;
import static io.perfeccionista.framework.pagefactory.filter.WebFilterConditions.notContainsTextCell;
import static io.perfeccionista.framework.pagefactory.filter.WebFilterConditions.textRowIndex;
import static io.perfeccionista.framework.pagefactory.filter.WebFilters.emptyWebTextTableFilter;
import static io.perfeccionista.framework.pagefactory.filter.WebFilters.with;
import static io.perfeccionista.framework.pagefactory.filter.WebFilters.without;
import static io.perfeccionista.framework.pagefactory.pageobjects.TablePage.NUMBER;
import static io.perfeccionista.framework.pagefactory.pageobjects.TablePage.SHORT_NAME;

@Tag("Element") @Tag("WebTextTable")
class WebTextTableFiltersTest extends AbstractUiTest {

    @Test
    void webTextTableFilterEmptyConditionTest(Environment env, ValueService value) {
        WebPageContext context = initWebPageContext(env, value);
        context.getPage(HomePage.class).leftMenu()
                .select("Text Table Element");
        TextTablePage textTablePage = context.getPage(TextTablePage.class);
        WebTextTable textTable = textTablePage.textTable()
                .should(beDisplayed());

        textTable.filter(emptyWebTextTableFilter())
                .should(haveSize(195));
    }

    @Test
    void webTableFilterRowIndexConditionTest(Environment env, ValueService value) {
        WebPageContext context = initWebPageContext(env, value);
        context.getPage(HomePage.class).leftMenu()
                .select("Text Table Element");
        TextTablePage textTablePage = context.getPage(TextTablePage.class);
        WebTextTable textTable = textTablePage.textTable()
                .should(beDisplayed());

        textTable.filter(with(textRowIndex(value.intGreaterThanOrEqual(100))))
                .should(haveSize(95));
        textTable.filter(without(textRowIndex(value.intGreaterThanOrEqual(100))))
                .should(haveSize(100));
    }

    @Test
    void webTableFilterElementTextConditionTest(Environment env, ValueService value) {
        WebPageContext context = initWebPageContext(env, value);
        context.getPage(HomePage.class).leftMenu()
                .select(value.stringEquals("Text Table Element"));
        TextTablePage textTablePage = context.getPage(TextTablePage.class);
        WebTextTable textTable = textTablePage.textTable()
                .should(beDisplayed());

        // By Element
        textTable.filter(with(containsTextCell(SHORT_NAME, "Финляндия")))
                .should(haveSize(1));
        textTable.filter(with(containsTextCell(SHORT_NAME, value.stringStartsWith("М"))))
                .should(haveSize(17));
        textTable.filter(with(notContainsTextCell(SHORT_NAME, value.stringEquals("Финляндия"))))
                .should(haveSize(194));
        textTable.filter(with(notContainsTextCell(SHORT_NAME, value.stringStartsWith("М"))))
                .should(haveSize(178));

        textTable.filter(without(containsTextCell(SHORT_NAME, "Финляндия")))
                .should(haveSize(194));
        textTable.filter(without(containsTextCell(SHORT_NAME, value.stringStartsWith("М"))))
                .should(haveSize(178));
        textTable.filter(without(notContainsTextCell(SHORT_NAME, value.stringEquals("Финляндия"))))
                .should(haveSize(1));
        textTable.filter(without(notContainsTextCell(SHORT_NAME, value.stringStartsWith("М"))))
                .should(haveSize(17));

        textTable.filter(with(containsTextCell(NUMBER, value.intEquals(77))))
                .should(haveSize(1));
        textTable.filter(with(containsTextCell(NUMBER, value.intGreaterThanOrEqual(124))))
                .should(haveSize(72));
        textTable.filter(with(notContainsTextCell(NUMBER, value.intEquals(77))))
                .should(haveSize(194));
        textTable.filter(with(notContainsTextCell(NUMBER, value.intGreaterThanOrEqual(124))))
                .should(haveSize(123));

        textTable.filter(without(containsTextCell(NUMBER, value.intEquals(77))))
                .should(haveSize(194));
        textTable.filter(without(containsTextCell(NUMBER, value.intGreaterThanOrEqual(124))))
                .should(haveSize(123));
        textTable.filter(without(notContainsTextCell(NUMBER, value.intEquals(77))))
                .should(haveSize(1));
        textTable.filter(without(notContainsTextCell(NUMBER, value.intGreaterThanOrEqual(124))))
                .should(haveSize(72));
    }

}
