package io.perfeccionista.framework.pagefactory.extractor;

import io.perfeccionista.framework.AbstractWebSeleniumParallelTest;
import io.perfeccionista.framework.pagefactory.dispatcher.context.WebPageContext;
import io.perfeccionista.framework.pagefactory.elements.WebTable;
import io.perfeccionista.framework.pagefactory.pageobjects.HomePage;
import io.perfeccionista.framework.pagefactory.pageobjects.TablePage;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static io.perfeccionista.framework.Web.*;

@Tag("Element") @Tag("WebTable")
class WebTableExtractorsTest extends AbstractWebSeleniumParallelTest {

    @Test
    void webTableExtractorsTest() {
        WebPageContext context = initWebPageContext();
        context.getPage(HomePage.class).leftMenu()
                .select("Table Element");

        TablePage tablePage = context.getPage(TablePage.class);
        WebTable table = tablePage.table()
                .should(beDisplayed());

    }

}
