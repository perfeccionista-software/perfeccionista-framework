package io.perfeccionista.framework.pagefactory.extractor;

import io.perfeccionista.framework.AbstractWebSeleniumParallelTest;
import io.perfeccionista.framework.pagefactory.dispatcher.context.WebPageContext;
import io.perfeccionista.framework.pagefactory.elements.WebList;
import io.perfeccionista.framework.pagefactory.pageobjects.HomePage;
import io.perfeccionista.framework.pagefactory.pageobjects.ListElementsPage;
import io.perfeccionista.framework.pagefactory.pageobjects.blocks.list.CountryBlock;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static io.perfeccionista.framework.Web.*;

@Tag("Element") @Tag("WebList")
class WebListExtractorsTest extends AbstractWebSeleniumParallelTest {

    @Test
    void webListBlockIndexExtractorsTest() {
        WebPageContext context = initWebPageContext();
        context.getPage(HomePage.class).leftMenu()
                .select("List Elements");

        ListElementsPage listElementsPage = context.getPage(ListElementsPage.class);
        WebList<CountryBlock> list = listElementsPage.webList()
                .should(beDisplayed());

        list.extractAll(blockIndex())
                .should(haveSize(195))
                .should(haveIndex(77))
                .should(haveResult(124))
                .should(beSorted(Integer::compareTo));
    }





}
