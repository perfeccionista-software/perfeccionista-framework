package io.perfeccionista.framework.pagefactory.context;

import io.perfeccionista.framework.Environment;
import io.perfeccionista.framework.pagefactory.AbstractUiTest;
import io.perfeccionista.framework.pagefactory.context.base.WebPageContext;
import io.perfeccionista.framework.pagefactory.pageobjects.ContextListElementsPage;
import io.perfeccionista.framework.pagefactory.pageobjects.HomePage;
import io.perfeccionista.framework.pagefactory.pageobjects.ListElementsPage;
import io.perfeccionista.framework.pagefactory.pageobjects.blocks.list.ContinentBlock;
import io.perfeccionista.framework.pagefactory.pageobjects.blocks.list.CountryBlock;
import io.perfeccionista.framework.value.ValueService;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static io.perfeccionista.framework.matcher.WebElementAssertions.beDisplayed;
import static io.perfeccionista.framework.matcher.WebElementAssertions.haveText;
import static io.perfeccionista.framework.pagefactory.context.WebContextLimiters.selectWebListBlock;
import static io.perfeccionista.framework.pagefactory.context.WebContextLimiters.selectWebListBlocks;
import static io.perfeccionista.framework.pagefactory.elements.WebBlock.frame;
import static io.perfeccionista.framework.pagefactory.filter.WebFilterConditions.containsText;
import static io.perfeccionista.framework.pagefactory.filter.WebFilters.with;
import static io.perfeccionista.framework.value.Values.stringStartsWith;

// TODO: Попробовать разные сценарии выборки (по индексу, отображаению элемента и т.п.)
@Tag("Context") @Tag("WebList")
class WebListBlockContextLimiterTest extends AbstractUiTest {

    @Test
    void webListSingleLimiterPositiveTest(Environment env, ValueService value) {
        WebPageContext context = initWebPageContext(env, value);

        context.getPage(HomePage.class).leftMenu()
                .select("List Elements");

        context.usePage(ListElementsPage.class)

                .execute((ListElementsPage page) -> {
                    page.webList()
                            .should(beDisplayed());
                })

                .execute((CountryBlock contextBlock) -> {
                    contextBlock.shortName()
                            .scrollTo()
                            .should(haveText("Финляндия"));

                }, selectWebListBlock("List of countries", with(containsText(frame(CountryBlock.class).shortName(), "Финляндия"))))

                .execute((CountryBlock contextBlock) -> {
                    contextBlock.shortName()
                            .scrollTo()
                            .should(haveText(stringStartsWith("М")));
                }, selectWebListBlocks("List of countries", with(containsText("Country name", stringStartsWith("М"))), 17));
    }

    @Test
    void webListDoubleLimiterPositiveTest(Environment env, ValueService value) {
        WebPageContext context = initWebPageContext(env, value);

        context.getPage(HomePage.class).leftMenu()
                .select("Elements (List Context)");

        context.usePage(ContextListElementsPage.class)

                .execute((ContextListElementsPage page) -> {
                    page.continentsList()
                            .should(beDisplayed());
                })

                .execute((CountryBlock contextBlock) -> {
                    contextBlock.shortName()
                            .scrollTo()
                            .should(haveText("Финляндия"));
                }, selectWebListBlock("List of continents", with(containsText(frame(ContinentBlock.class).continentName(), "Eurasia"))),
                selectWebListBlock(frame(ContinentBlock.class).countriesList(), with(containsText(frame(CountryBlock.class).shortName(), "Финляндия"))))

                .execute((CountryBlock contextBlock) -> {
                    contextBlock.shortName()
                            .scrollTo()
                            .should(haveText(stringStartsWith("М")));
                }, selectWebListBlock("List of continents", with(containsText("Continent name", "Eurasia"))),
                selectWebListBlocks("List of countries", with(containsText("Country name", stringStartsWith("М"))), 7));
    }

}
