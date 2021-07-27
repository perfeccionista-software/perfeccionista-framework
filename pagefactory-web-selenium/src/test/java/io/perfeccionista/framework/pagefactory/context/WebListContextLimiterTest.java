package io.perfeccionista.framework.pagefactory.context;

import io.perfeccionista.framework.AbstractWebSeleniumParallelTest;
import io.perfeccionista.framework.pagefactory.dispatcher.context.WebPageContext;
import io.perfeccionista.framework.pagefactory.pageobjects.ContextListElementsPage;
import io.perfeccionista.framework.pagefactory.pageobjects.HomePage;
import io.perfeccionista.framework.pagefactory.pageobjects.ListElementsPage;
import io.perfeccionista.framework.pagefactory.pageobjects.blocks.list.ContinentBlock;
import io.perfeccionista.framework.pagefactory.pageobjects.blocks.list.CountryBlock;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static io.perfeccionista.framework.Web.*;
import static io.perfeccionista.framework.pagefactory.elements.WebBlock.frame;
import static io.perfeccionista.framework.value.Values.stringStartsWith;

// TODO: Попробовать разные сценарии выборки (по индексу, отображаению элемента и т.п.)
@Tag("Context") @Tag("WebList")
class WebListContextLimiterTest extends AbstractWebSeleniumParallelTest {

    @Test
    void webListSingleLimiterPositiveTest() {
        WebPageContext context = initWebPageContext();

        context.getPage(HomePage.class).leftMenu()
                .select("List Elements");

        ListElementsPage listElementsPage = context.getPage(ListElementsPage.class);

        context.usePage(ListElementsPage.class)

                .execute((ListElementsPage page) -> {
                    page.webList()
                            .should(beDisplayed());
                })

                .execute((CountryBlock contextBlock) -> {
                    contextBlock.shortName()
                            .scrollTo()
                            .should(haveText("Финляндия"));

                }, selectWebListBlock(listElementsPage.webList(), block -> with(containsText(block.shortName(), "Финляндия"))))

                .execute((CountryBlock contextBlock) -> {
                    contextBlock.shortName()
                            .scrollTo()
                            .should(haveText(stringStartsWith("М")));
                }, selectWebListBlocks(listElementsPage.webList(), block -> with(containsText("Country name", stringStartsWith("М"))), 17));
    }

    @Test
    void webListDoubleLimiterPositiveTest() {
        WebPageContext context = initWebPageContext();

        context.getPage(HomePage.class).leftMenu()
                .select("Elements (List Context)");

        ContextListElementsPage listElementsPage = context.getPage(ContextListElementsPage.class);

        context.usePage(ContextListElementsPage.class)

                .execute((ContextListElementsPage page) -> {
                    page.continentsList()
                            .should(beDisplayed());
                })

                .execute((CountryBlock contextBlock) -> {
                    contextBlock.shortName()
                            .scrollTo()
                            .should(haveText("Финляндия"));
                }, selectWebListBlock(listElementsPage.continentsList(), block -> with(containsText(block.continentName(), "Eurasia"))),
                selectWebListBlock(frame(ContinentBlock.class).countriesList(), with(containsText(frame(CountryBlock.class).shortName(), "Финляндия"))))

                .execute((CountryBlock contextBlock) -> {
                    contextBlock.shortName()
                            .scrollTo()
                            .should(haveText(stringStartsWith("М")));
                }, selectWebListBlock(listElementsPage.continentsList(), block -> with(containsText("Continent name", "Eurasia"))),
                selectWebListBlocks("List of countries", with(containsText("Country name", stringStartsWith("М"))), 7));
    }

}
