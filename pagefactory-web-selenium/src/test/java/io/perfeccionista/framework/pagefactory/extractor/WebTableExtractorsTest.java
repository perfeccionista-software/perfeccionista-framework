package io.perfeccionista.framework.pagefactory.extractor;

import io.perfeccionista.framework.Environment;
import io.perfeccionista.framework.pagefactory.AbstractUiTest;
import io.perfeccionista.framework.pagefactory.context.base.WebPageContext;
import io.perfeccionista.framework.pagefactory.elements.WebTable;
import io.perfeccionista.framework.pagefactory.pageobjects.HomePage;
import io.perfeccionista.framework.pagefactory.pageobjects.TablePage;
import io.perfeccionista.framework.value.ValueService;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static io.perfeccionista.framework.matcher.WebElementAssertions.beDisplayed;

@Tag("Element") @Tag("WebTable")
class WebTableExtractorsTest extends AbstractUiTest {

    @Test
    void webTableExtractorsTest(Environment env, ValueService value) {
        WebPageContext context = initWebPageContext(env, value);
        context.getPage(HomePage.class).leftMenu()
                .select("Table Element");
        TablePage tablePage = context.getPage(TablePage.class);
        WebTable table = tablePage.table()
                .should(beDisplayed());

    }

}
