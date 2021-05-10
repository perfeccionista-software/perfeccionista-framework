package io.perfeccionista.framework.pagefactory.filter;

import io.perfeccionista.framework.AbstractWebSeleniumParallelTest;
import io.perfeccionista.framework.pagefactory.dispatcher.context.WebPageContext;
import io.perfeccionista.framework.pagefactory.elements.WebTextTable;
import io.perfeccionista.framework.pagefactory.pageobjects.HomePage;
import io.perfeccionista.framework.pagefactory.pageobjects.TextTablePage;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static io.perfeccionista.framework.Web.*;
import static io.perfeccionista.framework.pagefactory.pageobjects.TablePage.NUMBER;
import static io.perfeccionista.framework.pagefactory.pageobjects.TablePage.SHORT_COUNTRY_NAME;
import static io.perfeccionista.framework.value.Values.intEquals;
import static io.perfeccionista.framework.value.Values.intGreaterThanOrEqual;
import static io.perfeccionista.framework.value.Values.stringEquals;
import static io.perfeccionista.framework.value.Values.stringStartsWith;

@Tag("WebElement") @Tag("WebTextTable")
class WebTextTableFiltersTest extends AbstractWebSeleniumParallelTest {

    @Test
    void webTextTableFilterEmptyConditionTest() {
        WebPageContext context = initWebPageContext();
        context.getPage(HomePage.class).leftMenu()
                .select("Text Table Element");

        TextTablePage textTablePage = context.getPage(TextTablePage.class);
        WebTextTable textTable = textTablePage.textTable()
                .should(beDisplayed());

        textTable.filter(emptyWebTextTableFilter())
                .should(haveSize(195));
    }

    @Test
    void webTableFilterRowIndexConditionTest() {
        WebPageContext context = initWebPageContext();
        context.getPage(HomePage.class).leftMenu()
                .select("Text Table Element");

        TextTablePage textTablePage = context.getPage(TextTablePage.class);
        WebTextTable textTable = textTablePage.textTable()
                .should(beDisplayed());

        textTable.filter(with(textRowIndex(intGreaterThanOrEqual(100))))
                .should(haveSize(95));
        textTable.filter(without(textRowIndex(intGreaterThanOrEqual(100))))
                .should(haveSize(100));
    }

    @Test
    void webTableFilterElementTextConditionTest() {
        WebPageContext context = initWebPageContext();
        context.getPage(HomePage.class).leftMenu()
                .select(stringEquals("Text Table Element"));

        TextTablePage textTablePage = context.getPage(TextTablePage.class);
        WebTextTable textTable = textTablePage.textTable()
                .should(beDisplayed());

        // By Element
        textTable.filter(with(containsTextCell(SHORT_COUNTRY_NAME, "Финляндия")))
                .should(haveSize(1));
        textTable.filter(with(containsTextCell(SHORT_COUNTRY_NAME, stringStartsWith("М"))))
                .should(haveSize(17));
        textTable.filter(with(notContainTextCell(SHORT_COUNTRY_NAME, stringEquals("Финляндия"))))
                .should(haveSize(194));
        textTable.filter(with(notContainTextCell(SHORT_COUNTRY_NAME, stringStartsWith("М"))))
                .should(haveSize(178));

        textTable.filter(without(containsTextCell(SHORT_COUNTRY_NAME, "Финляндия")))
                .should(haveSize(194));
        textTable.filter(without(containsTextCell(SHORT_COUNTRY_NAME, stringStartsWith("М"))))
                .should(haveSize(178));
        textTable.filter(without(notContainTextCell(SHORT_COUNTRY_NAME, stringEquals("Финляндия"))))
                .should(haveSize(1));
        textTable.filter(without(notContainTextCell(SHORT_COUNTRY_NAME, stringStartsWith("М"))))
                .should(haveSize(17));

        textTable.filter(with(containsTextCell(NUMBER, intEquals(77))))
                .should(haveSize(1));
        textTable.filter(with(containsTextCell(NUMBER, intGreaterThanOrEqual(124))))
                .should(haveSize(72));
        textTable.filter(with(notContainTextCell(NUMBER, intEquals(77))))
                .should(haveSize(194));
        textTable.filter(with(notContainTextCell(NUMBER, intGreaterThanOrEqual(124))))
                .should(haveSize(123));

        textTable.filter(without(containsTextCell(NUMBER, intEquals(77))))
                .should(haveSize(194));
        textTable.filter(without(containsTextCell(NUMBER, intGreaterThanOrEqual(124))))
                .should(haveSize(123));
        textTable.filter(without(notContainTextCell(NUMBER, intEquals(77))))
                .should(haveSize(1));
        textTable.filter(without(notContainTextCell(NUMBER, intGreaterThanOrEqual(124))))
                .should(haveSize(72));
    }

}
