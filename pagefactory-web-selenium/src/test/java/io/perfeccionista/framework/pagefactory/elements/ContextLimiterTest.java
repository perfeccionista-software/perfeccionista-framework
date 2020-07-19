package io.perfeccionista.framework.pagefactory.elements;

import io.perfeccionista.framework.Environment;
import io.perfeccionista.framework.pagefactory.AbstractUiTest;
import io.perfeccionista.framework.pagefactory.browser.context.WebPageContext;
import io.perfeccionista.framework.pagefactory.elements.context.WebListBlockContextLimiter;
import io.perfeccionista.framework.pagefactory.elements.context.WebTableCellContextLimiter;
import io.perfeccionista.framework.pagefactory.elements.context.WebTableRowContextLimiter;
import io.perfeccionista.framework.pagefactory.filter.WebConditions;
import io.perfeccionista.framework.pagefactory.filter.list.WebListFilterBuilder;
import io.perfeccionista.framework.pagefactory.filter.table.WebTableFilterBuilder;
import io.perfeccionista.framework.pagefactory.pageobjects.HomePage;
import io.perfeccionista.framework.pagefactory.pageobjects.ListElementsPage;
import io.perfeccionista.framework.pagefactory.pageobjects.ListElementsPage.CountryBlock;
import io.perfeccionista.framework.pagefactory.pageobjects.TablePage;
import io.perfeccionista.framework.pagefactory.pageobjects.TablePage.ShortNameWebMappedBlock;
import io.perfeccionista.framework.value.ValueService;
import org.junit.jupiter.api.Test;

import static io.perfeccionista.framework.pagefactory.elements.WebMappedBlock.from;
import static io.perfeccionista.framework.pagefactory.filter.WebConditions.blockIndex;
import static io.perfeccionista.framework.pagefactory.filter.WebConditions.rowIndex;
import static io.perfeccionista.framework.pagefactory.filter.WebFilters.with;

public class ContextLimiterTest extends AbstractUiTest {

    @Test
    void webBlockContextLimiterTest(Environment env, ValueService value) {
        WebPageContext pc = initWebPageContext(env, value);

//        HomePage defaultContext = pc.getSearchContext(HomePage.class);
//        defaultContext.contentTitle().shouldBePresent();
//        defaultContext.content().shouldBePresent();
//        defaultContext.flags().shouldBePresent();
//
//        pc.setLimiter(new WebBlockContextLimiter<>(pc.getPage(HomePage.class).flags()));
//        FlagsBlock contextBlock = pc.getSearchContext(FlagsBlock.class);
//        contextBlock.italyFlag().shouldBeDisplayed();
//        contextBlock.russianFlag().shouldBeDisplayed();
//        contextBlock.spainFlag().shouldBeDisplayed();

        pc.removeLimiters();
    }

    @Test
    void webListBlockContextLimiterByElementTest(Environment env, ValueService value) {
        WebPageContext pc = initWebPageContext(env, value);
        HomePage homePage = pc.getPage(HomePage.class);
        homePage.leftMenu()
                .select(value.stringEquals("UL Elements"));

        ListElementsPage listElementsPage = pc.getPage(ListElementsPage.class);
        WebList webList = listElementsPage.webList();

        // Один блок
        WebListFilterBuilder webListSingleBlockFilter = with(WebConditions.containsText(from(CountryBlock.class).shortName(), value.stringEqualsIgnoreCase("Spain")));
        CountryBlock singleBlockContext = pc.usePage(ListElementsPage.class)
                .setLimiter(new WebListBlockContextLimiter<>(webList, webListSingleBlockFilter))
                .getSearchContext(CountryBlock.class);
        singleBlockContext.checkbox().shouldBeDisplayed();
        singleBlockContext.fullName().shouldBeDisplayed();
        singleBlockContext.number().shouldBeDisplayed();
        singleBlockContext.shortName().shouldBeDisplayed();
        pc.removeLimiters();

        // Несколько блоков
        WebListFilterBuilder webListMultipleBlocksFilter = with(blockIndex(value.intLessThan(50)));
        pc.usePage(ListElementsPage.class)
                .setLimiter(new WebListBlockContextLimiter<>(webList, webListMultipleBlocksFilter))
                .getSearchContexts(CountryBlock.class)
                .forEachOrdered(countryNameBlock -> {
                    countryNameBlock.checkbox().click();
                    countryNameBlock.fullName().shouldBeDisplayed();
                    countryNameBlock.number().shouldBeDisplayed();
                    countryNameBlock.shortName().shouldBeDisplayed();
                });
        pc.removeLimiters();
    }

    @Test
    void webListBlockContextLimiterByElementNameTest(Environment env, ValueService value) {
        WebPageContext pc = initWebPageContext(env, value);
        HomePage homePage = pc.getPage(HomePage.class);
        homePage.leftMenu()
                .select(value.stringEquals("UL Elements"));

        pc.usePage(ListElementsPage.class);

        // Один блок
        WebListFilterBuilder webListSingleBlockFilter = with(WebConditions.containsText("Short name", value.stringEqualsIgnoreCase("Spain")));
        pc.setLimiter(new WebListBlockContextLimiter<>("list of countries", webListSingleBlockFilter));
//        pc.getSearchContext().getElementByPath("Checkbox", WebCheckbox.class).shouldBeDisplayed();
//        pc.getSearchContext().getElementByPath("Full name", WebTextBlock.class).shouldBeDisplayed();
//        pc.getSearchContext().getElementByPath("Number", WebTextBlock.class).shouldBeDisplayed();
//        pc.getSearchContext().getElementByPath("Short name", WebLink.class).shouldBeDisplayed();
        pc.removeLimiters();

        // Несколько блоков
        WebListFilterBuilder webListMultipleBlocksFilter = with(blockIndex(value.intLessThan(50)));
        pc.setLimiter(new WebListBlockContextLimiter<>("list of countries", webListMultipleBlocksFilter));
//        pc.getSearchContexts().forEachOrdered(block -> block.getElementByPath("Checkbox", WebCheckbox.class).click());
//        pc.getSearchContexts().forEachOrdered(block -> block.getElementByPath("Full name", WebTextBlock.class).shouldBeDisplayed());
//        pc.getSearchContexts().forEachOrdered(block -> block.getElementByPath("Number", WebTextBlock.class).shouldBeDisplayed());
//        pc.getSearchContexts().forEachOrdered(block -> block.getElementByPath("Short name", WebLink.class).shouldBeDisplayed());
        pc.removeLimiters();
    }

    @Test
    void webTableCellContextLimiterByElementTest(Environment env, ValueService value) {
        WebPageContext pc = initWebPageContext(env, value);
        HomePage homePage = pc.getPage(HomePage.class);
        homePage.leftMenu()
                .select(value.stringEquals("Table"));

        TablePage tablePage = pc.getPage(TablePage.class);
        WebTable webTable = tablePage.table();

        // Один блок
        WebTableFilterBuilder webTableSingleCellFilter = with(WebConditions.containsText(TablePage.SHORT_NAME, from(ShortNameWebMappedBlock.class).shortName(), value.stringEqualsIgnoreCase("Spain")));

        ShortNameWebMappedBlock singleCellContext = pc.usePage(TablePage.class)
                .setLimiter(new WebTableCellContextLimiter<>(webTable, TablePage.SHORT_NAME, webTableSingleCellFilter))
                .getSearchContext(ShortNameWebMappedBlock.class);
        singleCellContext.shortName().shouldBeDisplayed();
        singleCellContext.shortName().shouldHaveText(value.stringEqualsIgnoreCase("Spain"));
        pc.removeLimiters();

        // Несколько блоков
        WebTableFilterBuilder webTableMultipleCellsFilter = with(rowIndex(value.intLessThan(50)));
        pc.usePage(TablePage.class)
                .setLimiter(new WebTableCellContextLimiter<>(webTable, TablePage.SHORT_NAME, webTableMultipleCellsFilter))
                .getSearchContexts(ShortNameWebMappedBlock.class)
                .forEachOrdered(countryNameCell -> {
                    countryNameCell.shortName().shouldBeDisplayed();
                });
        pc.removeLimiters();
    }

    @Test
    void webTableCellContextLimiterByElementNameTest(Environment env, ValueService value) {
        WebPageContext pc = initWebPageContext(env, value);
        HomePage homePage = pc.getPage(HomePage.class);
        homePage.leftMenu()
                .select(value.stringEquals("Table"));

        pc.usePage(TablePage.class);

        // Один блок
        WebTableFilterBuilder webTableSingleCellFilter = with(WebConditions.containsText(TablePage.SHORT_NAME, "link Short name", value.stringEqualsIgnoreCase("Spain")));
        pc.setLimiter(new WebTableCellContextLimiter<>("table of countries", TablePage.SHORT_NAME, webTableSingleCellFilter));
//        pc.getSearchContext().getElementByPath("link Short name", WebLink.class).shouldBeDisplayed();
//        pc.getSearchContext().getElementByPath("link Short name", WebLink.class).shouldHaveText(value.stringEqualsIgnoreCase("Spain"));
        pc.removeLimiters();

        // Несколько блоков
        WebTableFilterBuilder webTableMultipleCellsFilter = with(rowIndex(value.intLessThan(50)));
        pc.setLimiter(new WebTableCellContextLimiter<>("table of countries", TablePage.SHORT_NAME, webTableMultipleCellsFilter));
//        pc.getSearchContexts().forEachOrdered(cell -> cell.getElementByPath("link Short name", WebLink.class).shouldBeDisplayed());
        pc.removeLimiters();
    }

    @Test
    void webTableRowContextLimiterByElementNameTest(Environment env, ValueService value) {
        WebPageContext pc = initWebPageContext(env, value);
        HomePage homePage = pc.getPage(HomePage.class);
        homePage.leftMenu()
                .select(value.stringEquals("Table"));

        pc.usePage(TablePage.class);

        // Один блок
        WebTableFilterBuilder webTableSingleCellFilter = with(WebConditions.containsText(TablePage.SHORT_NAME, "link Short name", value.stringEqualsIgnoreCase("Spain")));
        pc.setLimiter(new WebTableRowContextLimiter("table of countries", webTableSingleCellFilter));
//        pc.getSearchContext().getElementByPath(TablePage.CHECKBOX + " -> checkbox Select", WebCheckbox.class).shouldBeDisplayed();
//        pc.getSearchContext().getElementByPath(TablePage.SHORT_NAME + " -> link Short name", WebLink.class).shouldBeDisplayed();
//        pc.getSearchContext().getElementByPath(TablePage.NUMBER + " -> text", WebLink.class).shouldBeDisplayed();
//        pc.getSearchContext().getElementByPath(TablePage.FULL_NAME + " -> text Full Name", WebLink.class).shouldBeDisplayed();
        pc.removeLimiters();

        // Несколько блоков
        WebTableFilterBuilder webTableMultipleCellsFilter = with(rowIndex(value.intLessThan(50)));
        pc.setLimiter(new WebTableRowContextLimiter("table of countries", webTableMultipleCellsFilter));
//        pc.getSearchContexts().forEachOrdered(cell -> cell.getElementByPath(TablePage.CHECKBOX + " -> checkbox Select", WebCheckbox.class).shouldBeDisplayed());
//        pc.getSearchContexts().forEachOrdered(cell -> cell.getElementByPath(TablePage.SHORT_NAME + " -> link Short name", WebLink.class).shouldBeDisplayed());
//        pc.getSearchContexts().forEachOrdered(cell -> cell.getElementByPath(TablePage.NUMBER + " -> text", WebLink.class).shouldBeDisplayed());
//        pc.getSearchContexts().forEachOrdered(cell -> cell.getElementByPath(TablePage.FULL_NAME + " -> text Full Name", WebLink.class).shouldBeDisplayed());
        pc.removeLimiters();
    }

}