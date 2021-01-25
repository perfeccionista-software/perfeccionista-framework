package io.perfeccionista.framework.pagefactory.context;

import io.perfeccionista.framework.AbstractWebSeleniumParallelTest;
import io.perfeccionista.framework.pagefactory.dispatcher.context.WebPageContext;
import io.perfeccionista.framework.pagefactory.elements.WebTableRow;
import io.perfeccionista.framework.pagefactory.pageobjects.ContextTableElementsPage;
import io.perfeccionista.framework.pagefactory.pageobjects.HomePage;
import io.perfeccionista.framework.pagefactory.pageobjects.TablePage;
import io.perfeccionista.framework.pagefactory.pageobjects.blocks.table.ContinentNameWebBlock;
import io.perfeccionista.framework.pagefactory.pageobjects.blocks.table.CountryNameWebBlock;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static io.perfeccionista.framework.Web.*;
import static io.perfeccionista.framework.pagefactory.elements.WebBlock.frame;
import static io.perfeccionista.framework.pagefactory.pageobjects.ContextTableElementsPage.CONTINENT;
import static io.perfeccionista.framework.pagefactory.pageobjects.TablePage.SHORT_COUNTRY_NAME;
import static io.perfeccionista.framework.value.Values.stringStartsWith;

// TODO: Попробовать разные сценарии выборки (по индексу, отображаению элемента и т.п.)
@Tag("Context") @Tag("WebTable")
class WebTableRowContextLimiterTest extends AbstractWebSeleniumParallelTest {

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

                .execute((WebTableRow tableRow) -> {
                    tableRow.getCell(SHORT_COUNTRY_NAME, CountryNameWebBlock.class).shortName()
                            .scrollTo()
                            .should(haveText("Финляндия"));
                }, selectWebTableRow("Table of countries", with(containsText(SHORT_COUNTRY_NAME, frame(CountryNameWebBlock.class).shortName(), "Финляндия"))))

                .execute((WebTableRow tableRow) -> {
                    tableRow.getCell(SHORT_COUNTRY_NAME, CountryNameWebBlock.class).shortName()
                            .scrollTo()
                            .should(haveText(stringStartsWith("М")));
                }, selectWebTableRows("Table of countries", with(containsText(SHORT_COUNTRY_NAME, frame(CountryNameWebBlock.class).shortName(), stringStartsWith("М"))), 17));
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

                .execute((WebTableRow tableRow) -> {
                    tableRow.getCell(SHORT_COUNTRY_NAME, CountryNameWebBlock.class).shortName()
                            .scrollTo()
                            .should(haveText("Финляндия"));
                }, selectWebTableRow("Table of continents", with(containsText(CONTINENT, frame(ContinentNameWebBlock.class).continentName(), "Eurasia"))),
                selectWebTableRow("Countries -> Table of countries", with(containsText(SHORT_COUNTRY_NAME, frame(CountryNameWebBlock.class).shortName(), "Финляндия"))))

                .execute((WebTableRow tableRow) -> {
                    tableRow.getCell(SHORT_COUNTRY_NAME, CountryNameWebBlock.class).shortName()
                            .scrollTo()
                            .should(haveText(stringStartsWith("М")));
                }, selectWebTableRow("Table of continents", with(containsText(CONTINENT, frame(ContinentNameWebBlock.class).continentName(), "Eurasia"))),
                selectWebTableRows("Countries -> Table of countries", with(containsText(SHORT_COUNTRY_NAME, frame(CountryNameWebBlock.class).shortName(), stringStartsWith("М"))), 7));
    }

}
