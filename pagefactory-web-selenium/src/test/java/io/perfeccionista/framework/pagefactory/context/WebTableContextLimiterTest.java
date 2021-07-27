package io.perfeccionista.framework.pagefactory.context;

import io.perfeccionista.framework.AbstractWebSeleniumParallelTest;
import io.perfeccionista.framework.pagefactory.dispatcher.context.WebPageContext;
import io.perfeccionista.framework.pagefactory.pageobjects.ContextTableElementsPage;
import io.perfeccionista.framework.pagefactory.pageobjects.HomePage;
import io.perfeccionista.framework.pagefactory.pageobjects.TablePage;
import io.perfeccionista.framework.pagefactory.pageobjects.TablePage.TableRow;
import io.perfeccionista.framework.pagefactory.pageobjects.blocks.table.CountriesTableWebBlock.CountriesTableRow;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static io.perfeccionista.framework.Web.*;
import static io.perfeccionista.framework.pagefactory.elements.WebBlock.frame;
import static io.perfeccionista.framework.value.Values.stringStartsWith;

// TODO: Попробовать разные сценарии выборки (по индексу, отображаению элемента и т.п.)
@Tag("Context") @Tag("WebTable")
class WebTableContextLimiterTest extends AbstractWebSeleniumParallelTest {

    @Test
    void webTableRowSingleLimiterPositiveTest() {
        WebPageContext context = initWebPageContext();

        context.getPage(HomePage.class).leftMenu()
                .select("Table Element");

        TablePage tablePage = context.getPage(TablePage.class);

        context.usePage(TablePage.class)

                .execute((TablePage page) -> {
                    page.table()
                            .should(beDisplayed());
                })

                .execute((TableRow tableRow) -> {
                    tableRow.shortNameColumn().shortName()
                            .scrollTo()
                            .should(haveText("Финляндия"));
                }, selectWebListBlock(tablePage.table(), row -> with(containsText(row.shortNameColumn().shortName(), "Финляндия"))))

                .execute((TableRow tableRow) -> {
                    tableRow.shortNameColumn().shortName()
                            .scrollTo()
                            .should(haveText(stringStartsWith("М")));
                }, selectWebListBlocks(tablePage.table(), row -> with(containsText(row.shortNameColumn().shortName(), stringStartsWith("М"))), 17));
    }

    @Test
    void webListDoubleLimiterPositiveTest() {
        WebPageContext context = initWebPageContext();

        context.getPage(HomePage.class).leftMenu()
                .select("Elements (Table Context)");

        ContextTableElementsPage tablePage = context.getPage(ContextTableElementsPage.class);

        context.usePage(ContextTableElementsPage.class)

                .execute((ContextTableElementsPage page) -> {
                    page.continentsTable()
                            .should(beDisplayed());
                })

                .execute((CountriesTableRow tableRow) -> {
                    tableRow.shortNameColumn().shortName()
                            .scrollTo()
                            .should(haveText("Финляндия"));
                }, selectWebListBlock(tablePage.continentsTable(), row -> with(containsText(row.continentColumn().continentName(), "Eurasia"))),
                        selectWebListBlock("column Countries -> Table of countries", with(containsText(frame(CountriesTableRow.class).shortNameColumn().shortName(), "Финляндия"))))

                .execute((CountriesTableRow tableRow) -> {
                    tableRow.shortNameColumn().shortName()
                            .scrollTo()
                            .should(haveText(stringStartsWith("М")));
                }, selectWebListBlock(tablePage.continentsTable(), row -> with(containsText(row.continentColumn().continentName(), "Eurasia"))),
                        selectWebListBlocks("column Countries -> Table of countries", with(containsText(frame(CountriesTableRow.class).shortNameColumn().shortName(), stringStartsWith("М"))), 7));
    }

}
