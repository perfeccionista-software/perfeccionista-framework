package io.perfeccionista.framework.pagefactory.context;

import io.perfeccionista.framework.Environment;
import io.perfeccionista.framework.pagefactory.AbstractUiTest;
import io.perfeccionista.framework.pagefactory.context.base.WebPageContext;
import io.perfeccionista.framework.pagefactory.elements.WebTableRow;
import io.perfeccionista.framework.pagefactory.pageobjects.ContextTableElementsPage;
import io.perfeccionista.framework.pagefactory.pageobjects.HomePage;
import io.perfeccionista.framework.pagefactory.pageobjects.TablePage;
import io.perfeccionista.framework.pagefactory.pageobjects.blocks.table.ContinentNameWebBlock;
import io.perfeccionista.framework.pagefactory.pageobjects.blocks.table.CountryNameWebBlock;
import io.perfeccionista.framework.value.ValueService;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static io.perfeccionista.framework.matcher.WebElementAssertions.beDisplayed;
import static io.perfeccionista.framework.matcher.WebElementAssertions.haveText;
import static io.perfeccionista.framework.pagefactory.context.WebContextLimiters.selectWebTableRow;
import static io.perfeccionista.framework.pagefactory.context.WebContextLimiters.selectWebTableRows;
import static io.perfeccionista.framework.pagefactory.elements.WebBlock.frame;
import static io.perfeccionista.framework.pagefactory.filter.WebFilterConditions.containsText;
import static io.perfeccionista.framework.pagefactory.filter.WebFilters.with;
import static io.perfeccionista.framework.pagefactory.pageobjects.ContextTableElementsPage.CONTINENT;
import static io.perfeccionista.framework.pagefactory.pageobjects.TablePage.SHORT_COUNTRY_NAME;
import static io.perfeccionista.framework.value.Values.stringStartsWith;

// TODO: Попробовать разные сценарии выборки (по индексу, отображаению элемента и т.п.)
@Tag("Context") @Tag("WebTable")
public class WebTableRowContextLimiterTest extends AbstractUiTest {

    @Test
    void webTableRowSingleLimiterPositiveTest(Environment env, ValueService value) {
        WebPageContext context = initWebPageContext(env, value);

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
    void webListDoubleLimiterPositiveTest(Environment env, ValueService value) {
        WebPageContext context = initWebPageContext(env, value);

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
