package io.perfeccionista.framework.pagefactory.context;

import io.perfeccionista.framework.Environment;
import io.perfeccionista.framework.pagefactory.AbstractUiTest;
import io.perfeccionista.framework.pagefactory.context.base.WebPageContext;
import io.perfeccionista.framework.pagefactory.pageobjects.ContextTableElementsPage;
import io.perfeccionista.framework.pagefactory.pageobjects.HomePage;
import io.perfeccionista.framework.pagefactory.pageobjects.TablePage;
import io.perfeccionista.framework.pagefactory.pageobjects.blocks.table.ContinentNameWebBlock;
import io.perfeccionista.framework.pagefactory.pageobjects.blocks.table.ShortNameWebBlock;
import io.perfeccionista.framework.value.ValueService;
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
import static io.perfeccionista.framework.pagefactory.pageobjects.ContextTableElementsPage.COUNTRIES;
import static io.perfeccionista.framework.pagefactory.pageobjects.TablePage.SHORT_NAME;
import static io.perfeccionista.framework.value.Values.stringStartsWith;

// TODO: Попробовать разные сценарии выборки (по индексу, отображаению элемента и т.п.)
@Tag("Context") @Tag("WebTable")
public class WebTableCellContextLimiterTest extends AbstractUiTest {

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

                .execute((ShortNameWebBlock tableCell) -> {
                    tableCell.shortName()
                            .scrollTo()
                            .should(haveText("Финляндия"));
                }, selectWebTableCell("Table of countries", SHORT_NAME, with(containsText(SHORT_NAME, frame(ShortNameWebBlock.class).shortName(), "Финляндия"))))

                .execute((ShortNameWebBlock tableCell) -> {
                    tableCell.shortName()
                            .scrollTo()
                            .should(haveText(stringStartsWith("М")));
                }, selectWebTableCells("Table of countries", SHORT_NAME, with(containsText(SHORT_NAME, frame(ShortNameWebBlock.class).shortName(), stringStartsWith("М"))), 17));
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

                .execute((ShortNameWebBlock tableCell) -> {
                    tableCell.shortName()
                            .scrollTo()
                            .should(haveText("Финляндия"));
                }, selectWebTableCell("Table of continents", COUNTRIES, with(containsText(CONTINENT, frame(ContinentNameWebBlock.class).continentName(), "Eurasia"))),
                selectWebTableCell("Table of countries", SHORT_NAME, with(containsText(SHORT_NAME, frame(ShortNameWebBlock.class).shortName(), "Финляндия"))))

                .execute((ShortNameWebBlock tableCell) -> {
                    tableCell.shortName()
                            .scrollTo()
                            .should(haveText(stringStartsWith("М")));
                }, selectWebTableCell("Table of continents", COUNTRIES, with(containsText(CONTINENT, frame(ContinentNameWebBlock.class).continentName(), "Eurasia"))),
                selectWebTableCells("Table of countries", SHORT_NAME, with(containsText(SHORT_NAME, frame(ShortNameWebBlock.class).shortName(), stringStartsWith("М"))), 7));
    }

}
