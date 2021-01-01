package io.perfeccionista.framework.pagefactory.context;

import io.perfeccionista.framework.AbstractWebSeleniumParallelTest;
import io.perfeccionista.framework.pagefactory.context.base.WebPageContext;
import io.perfeccionista.framework.pagefactory.pageobjects.ContextTableElementsPage;
import io.perfeccionista.framework.pagefactory.pageobjects.HomePage;
import io.perfeccionista.framework.pagefactory.pageobjects.TablePage;
import io.perfeccionista.framework.pagefactory.pageobjects.blocks.table.ContinentNameWebBlock;
import io.perfeccionista.framework.pagefactory.pageobjects.blocks.table.CountryNameWebBlock;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static io.perfeccionista.framework.matcher.WebElementAssertions.beDisplayed;
import static io.perfeccionista.framework.matcher.WebElementAssertions.haveText;
import static io.perfeccionista.framework.pagefactory.context.WebContextLimiters.selectWebTableCell;
import static io.perfeccionista.framework.pagefactory.context.WebContextLimiters.selectWebTableCells;
import static io.perfeccionista.framework.pagefactory.elements.WebBlock.frame;
import static io.perfeccionista.framework.pagefactory.filter.WebFilterConditions.containsText;
import static io.perfeccionista.framework.pagefactory.filter.WebFilters.with;
import static io.perfeccionista.framework.pagefactory.pageobjects.ContextTableElementsPage.CONTINENT;
import static io.perfeccionista.framework.pagefactory.pageobjects.TablePage.SHORT_COUNTRY_NAME;
import static io.perfeccionista.framework.value.Values.stringStartsWith;

// TODO: Попробовать разные сценарии выборки (по индексу, отображаению элемента и т.п.)
@Tag("Context") @Tag("WebTable")
class WebTableCellContextLimiterTest extends AbstractWebSeleniumParallelTest {

    @Test
    void webTableRowSingleLimiterPositiveTest() {
        WebPageContext context = initWebPageContext();

        context.getPage(HomePage.class).leftMenu()
                .select("Table Element");

        context.usePage(TablePage.class)

                .execute((TablePage page) -> {
                    page.table()
                            .should(beDisplayed());
                })

                .execute((CountryNameWebBlock tableCell) -> {
                    tableCell.shortName()
                            .scrollTo()
                            .should(haveText("Финляндия"));
                }, selectWebTableCell("Table of countries", "Short country name", with(containsText(SHORT_COUNTRY_NAME, frame(CountryNameWebBlock.class).shortName(), "Финляндия"))))

                .execute((CountryNameWebBlock tableCell) -> {
                    tableCell.shortName()
                            .scrollTo()
                            .should(haveText(stringStartsWith("М")));
                }, selectWebTableCells("Table of countries", "Short country name", with(containsText(SHORT_COUNTRY_NAME, frame(CountryNameWebBlock.class).shortName(), stringStartsWith("М"))), 17));
    }

    @Test
    void webListDoubleLimiterPositiveTest() {
        WebPageContext context = initWebPageContext();

        context.getPage(HomePage.class).leftMenu()
                .select("Elements (Table Context)");

        context.usePage(ContextTableElementsPage.class)

                .execute((ContextTableElementsPage page) -> {
                    page.continentsTable()
                            .should(beDisplayed());
                })

                .execute((CountryNameWebBlock tableCell) -> {
                    tableCell.shortName()
                            .scrollTo()
                            .should(haveText("Финляндия"));
                }, selectWebTableCell("Table of continents", "Countries", with(containsText(CONTINENT, frame(ContinentNameWebBlock.class).continentName(), "Eurasia"))),
                selectWebTableCell("Table of countries", "Short country name", with(containsText(SHORT_COUNTRY_NAME, frame(CountryNameWebBlock.class).shortName(), "Финляндия"))))

                .execute((CountryNameWebBlock tableCell) -> {
                    tableCell.shortName()
                            .scrollTo()
                            .should(haveText(stringStartsWith("М")));
                }, selectWebTableCell("Table of continents", "Countries", with(containsText(CONTINENT, frame(ContinentNameWebBlock.class).continentName(), "Eurasia"))),
                selectWebTableCells("Table of countries", "Short country name", with(containsText(SHORT_COUNTRY_NAME, frame(CountryNameWebBlock.class).shortName(), stringStartsWith("М"))), 7));
    }

}
