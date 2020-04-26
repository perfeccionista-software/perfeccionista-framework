package io.perfeccionista.framework.pagefactory.web;

import io.perfeccionista.framework.Environment;
import io.perfeccionista.framework.extension.PerfeccionistaExtension;
import io.perfeccionista.framework.pagefactory.browser.context.WebPageContext;
import io.perfeccionista.framework.pagefactory.elements.WebCheckbox;
import io.perfeccionista.framework.pagefactory.elements.WebLink;
import io.perfeccionista.framework.pagefactory.elements.WebList;
import io.perfeccionista.framework.pagefactory.elements.WebTable;
import io.perfeccionista.framework.pagefactory.elements.WebTextBlock;
import io.perfeccionista.framework.pagefactory.elements.context.WebBlockContextLimiter;
import io.perfeccionista.framework.pagefactory.elements.context.WebListBlockContextLimiter;
import io.perfeccionista.framework.pagefactory.elements.context.WebTableCellContextLimiter;
import io.perfeccionista.framework.pagefactory.elements.context.WebTableRowContextLimiter;
import io.perfeccionista.framework.pagefactory.filter.WebConditions;
import io.perfeccionista.framework.pagefactory.filter.list.WebListFilter;
import io.perfeccionista.framework.pagefactory.filter.table.WebTableFilter;
import io.perfeccionista.framework.pagefactory.web.pageobjects.HomePage;
import io.perfeccionista.framework.pagefactory.web.pageobjects.HomePage.FlagsBlock;
import io.perfeccionista.framework.pagefactory.web.pageobjects.ListElementsPage;
import io.perfeccionista.framework.pagefactory.web.pageobjects.ListElementsPage.CountryNameBlock;
import io.perfeccionista.framework.pagefactory.web.pageobjects.TablePage;
import io.perfeccionista.framework.pagefactory.web.pageobjects.TablePage.LinkWebMappedBlock;
import io.perfeccionista.framework.value.ValueService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static io.perfeccionista.framework.pagefactory.elements.mapping.WebMappingUtils.blockMock;
import static io.perfeccionista.framework.pagefactory.filter.WebConditions.blockIndex;
import static io.perfeccionista.framework.pagefactory.filter.WebConditions.rowIndex;
import static io.perfeccionista.framework.pagefactory.filter.WebConditions.containsText;
import static io.perfeccionista.framework.pagefactory.filter.WebFilters.with;

@ExtendWith(PerfeccionistaExtension.class)
public class ContextLimiterTest extends AbstractElementTest {

    @Test
    void webBlockContextLimiterTest(Environment env, ValueService value) {
        WebPageContext pc = initWebPageContext(env, value);

        HomePage defaultContext = pc.getSearchContext(HomePage.class);
        defaultContext.contentTitle().shouldBePresent();
        defaultContext.content().shouldBePresent();
        defaultContext.flags().shouldBePresent();

        pc.setLimiter(new WebBlockContextLimiter<>(pc.getPage(HomePage.class).flags()));
        FlagsBlock contextBlock = pc.getSearchContext(FlagsBlock.class);
        contextBlock.italyFlag().shouldBeDisplayed();
        contextBlock.russianFlag().shouldBeDisplayed();
        contextBlock.spainFlag().shouldBeDisplayed();

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
        WebListFilter webListSingleBlockFilter = with(WebConditions.containsText(blockMock(CountryNameBlock.class).shortName(), value.stringEqualsIgnoreCase("Spain")));
        CountryNameBlock singleBlockContext = pc.usePage(ListElementsPage.class)
                .setLimiter(new WebListBlockContextLimiter<>(webList, webListSingleBlockFilter))
                .getSearchContext(CountryNameBlock.class);
        singleBlockContext.checkbox().shouldBeDisplayed();
        singleBlockContext.fullName().shouldBeDisplayed();
        singleBlockContext.number().shouldBeDisplayed();
        singleBlockContext.shortName().shouldBeDisplayed();
        pc.removeLimiters();

        // Несколько блоков
        WebListFilter webListMultipleBlocksFilter = with(blockIndex(value.intLessThan(50)));
        pc.usePage(ListElementsPage.class)
                .setLimiter(new WebListBlockContextLimiter<>(webList, webListMultipleBlocksFilter))
                .getSearchContexts(CountryNameBlock.class)
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
        WebListFilter webListSingleBlockFilter = with(WebConditions.containsText("Short name", value.stringEqualsIgnoreCase("Spain")));
        pc.setLimiter(new WebListBlockContextLimiter<>("list of countries", webListSingleBlockFilter));
        pc.getSearchContext().getElementByPath("Checkbox", WebCheckbox.class).shouldBeDisplayed();
        pc.getSearchContext().getElementByPath("Full name", WebTextBlock.class).shouldBeDisplayed();
        pc.getSearchContext().getElementByPath("Number", WebTextBlock.class).shouldBeDisplayed();
        pc.getSearchContext().getElementByPath("Short name", WebLink.class).shouldBeDisplayed();
        pc.removeLimiters();

        // Несколько блоков
        WebListFilter webListMultipleBlocksFilter = with(blockIndex(value.intLessThan(50)));
        pc.setLimiter(new WebListBlockContextLimiter<>("list of countries", webListMultipleBlocksFilter));
        pc.getSearchContexts().forEachOrdered(block -> block.getElementByPath("Checkbox", WebCheckbox.class).click());
        pc.getSearchContexts().forEachOrdered(block -> block.getElementByPath("Full name", WebTextBlock.class).shouldBeDisplayed());
        pc.getSearchContexts().forEachOrdered(block -> block.getElementByPath("Number", WebTextBlock.class).shouldBeDisplayed());
        pc.getSearchContexts().forEachOrdered(block -> block.getElementByPath("Short name", WebLink.class).shouldBeDisplayed());
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
        WebTableFilter webTableSingleCellFilter = with(WebConditions.containsText(TablePage.SHORT_NAME, blockMock(LinkWebMappedBlock.class).shortName(), value.stringEqualsIgnoreCase("Spain")));

        LinkWebMappedBlock singleCellContext = pc.usePage(TablePage.class)
                .setLimiter(new WebTableCellContextLimiter<>(webTable, TablePage.SHORT_NAME, webTableSingleCellFilter))
                .getSearchContext(LinkWebMappedBlock.class);
        singleCellContext.shortName().shouldBeDisplayed();
        singleCellContext.shortName().shouldHaveText(value.stringEqualsIgnoreCase("Spain"));
        pc.removeLimiters();

        // Несколько блоков
        WebTableFilter webTableMultipleCellsFilter = with(rowIndex(value.intLessThan(50)));
        pc.usePage(TablePage.class)
                .setLimiter(new WebTableCellContextLimiter<>(webTable, TablePage.SHORT_NAME, webTableMultipleCellsFilter))
                .getSearchContexts(LinkWebMappedBlock.class)
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
        WebTableFilter webTableSingleCellFilter = with(WebConditions.containsText(TablePage.SHORT_NAME, "link Short name", value.stringEqualsIgnoreCase("Spain")));
        pc.setLimiter(new WebTableCellContextLimiter<>("table of countries", TablePage.SHORT_NAME, webTableSingleCellFilter));
        pc.getSearchContext().getElementByPath("link Short name", WebLink.class).shouldBeDisplayed();
        pc.getSearchContext().getElementByPath("link Short name", WebLink.class).shouldHaveText(value.stringEqualsIgnoreCase("Spain"));
        pc.removeLimiters();

        // Несколько блоков
        WebTableFilter webTableMultipleCellsFilter = with(rowIndex(value.intLessThan(50)));
        pc.setLimiter(new WebTableCellContextLimiter<>("table of countries", TablePage.SHORT_NAME, webTableMultipleCellsFilter));
        pc.getSearchContexts().forEachOrdered(cell -> cell.getElementByPath("link Short name", WebLink.class).shouldBeDisplayed());
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
        WebTableFilter webTableSingleCellFilter = with(WebConditions.containsText(TablePage.SHORT_NAME, "link Short name", value.stringEqualsIgnoreCase("Spain")));
        pc.setLimiter(new WebTableRowContextLimiter("table of countries", webTableSingleCellFilter));
        pc.getSearchContext().getElementByPath(TablePage.CHECKBOX + " -> checkbox Select", WebCheckbox.class).shouldBeDisplayed();
        pc.getSearchContext().getElementByPath(TablePage.SHORT_NAME + " -> link Short name", WebLink.class).shouldBeDisplayed();
        pc.getSearchContext().getElementByPath(TablePage.NUMBER + " -> text", WebLink.class).shouldBeDisplayed();
        pc.getSearchContext().getElementByPath(TablePage.FULL_NAME + " -> text Full Name", WebLink.class).shouldBeDisplayed();
        pc.removeLimiters();

        // Несколько блоков
        WebTableFilter webTableMultipleCellsFilter = with(rowIndex(value.intLessThan(50)));
        pc.setLimiter(new WebTableRowContextLimiter("table of countries", webTableMultipleCellsFilter));
        pc.getSearchContexts().forEachOrdered(cell -> cell.getElementByPath(TablePage.CHECKBOX + " -> checkbox Select", WebCheckbox.class).shouldBeDisplayed());
        pc.getSearchContexts().forEachOrdered(cell -> cell.getElementByPath(TablePage.SHORT_NAME + " -> link Short name", WebLink.class).shouldBeDisplayed());
        pc.getSearchContexts().forEachOrdered(cell -> cell.getElementByPath(TablePage.NUMBER + " -> text", WebLink.class).shouldBeDisplayed());
        pc.getSearchContexts().forEachOrdered(cell -> cell.getElementByPath(TablePage.FULL_NAME + " -> text Full Name", WebLink.class).shouldBeDisplayed());
        pc.removeLimiters();
    }

}