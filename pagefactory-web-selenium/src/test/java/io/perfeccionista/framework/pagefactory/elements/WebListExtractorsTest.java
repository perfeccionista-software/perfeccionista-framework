package io.perfeccionista.framework.pagefactory.elements;

import io.perfeccionista.framework.Environment;
import io.perfeccionista.framework.pagefactory.AbstractUiTest;
import io.perfeccionista.framework.pagefactory.browser.context.WebPageContext;
import io.perfeccionista.framework.pagefactory.pageobjects.HomePage;
import io.perfeccionista.framework.pagefactory.pageobjects.ListElementsPage;
import io.perfeccionista.framework.value.ValueService;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static io.perfeccionista.framework.matcher.WebElementAssertions.beDisplayed;
import static io.perfeccionista.framework.matcher.WebMultipleResultAssertions.beSorted;
import static io.perfeccionista.framework.matcher.WebMultipleResultAssertions.haveIndex;
import static io.perfeccionista.framework.matcher.WebMultipleResultAssertions.haveResult;
import static io.perfeccionista.framework.matcher.WebMultipleResultAssertions.haveSize;
import static io.perfeccionista.framework.pagefactory.extractor.WebExtractors.blockIndex;

@Tag("Element") @Tag("WebList")
class WebListExtractorsTest extends AbstractUiTest {

    @Test
    void webListBlockIndexExtractorsTest(Environment env, ValueService value) {
        WebPageContext context = initWebPageContext(env, value);
        context.getPage(HomePage.class).leftMenu()
                .select("List Elements");
        ListElementsPage listElementsPage = context.getPage(ListElementsPage.class);
        WebList list = listElementsPage.webList()
                .should(beDisplayed());

        list.extractAll(blockIndex())
                .should(haveSize(195))
                .should(haveIndex(77))
                .should(haveResult(124))
                .should(beSorted(Integer::compareTo));
    }





}
