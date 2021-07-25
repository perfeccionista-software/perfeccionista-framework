package io.perfeccionista.framework.pagefactory.result;

import io.perfeccionista.framework.AbstractWebSeleniumParallelTest;
import io.perfeccionista.framework.Web;
import io.perfeccionista.framework.pagefactory.dispatcher.context.WebPageContext;
import io.perfeccionista.framework.pagefactory.elements.WebList;
import io.perfeccionista.framework.pagefactory.pageobjects.HomePage;
import io.perfeccionista.framework.pagefactory.pageobjects.ListElementsPage;
import io.perfeccionista.framework.pagefactory.pageobjects.blocks.list.CountryBlock;
import org.junit.jupiter.api.Test;

import static io.perfeccionista.framework.Web.*;
import static io.perfeccionista.framework.value.Values.intEquals;
import static io.perfeccionista.framework.value.Values.intGreaterThan;
import static io.perfeccionista.framework.value.Values.intNotEquals;

class WebMultipleResultTest extends AbstractWebSeleniumParallelTest {

    @Test
    void webMultipleResultTest() {
        WebPageContext context = initWebPageContext();
        context.getPage(HomePage.class).leftMenu()
                .select("List Elements");

        ListElementsPage listElementsPage = context.getPage(ListElementsPage.class);
        WebList<CountryBlock> list = listElementsPage.webList()
                .should(beDisplayed());

        list.extractAll(blockIndex())
                .should(haveSize(195))
                .should(notHaveSize(intNotEquals(195)))
                .should(haveIndex(77))
//                .should(haveIndexes(Set.of(3, 5, 8, 34, 66, 78, 94, 145, 194)))
                .should(notHaveIndex(intGreaterThan(195)))
//                .should(notHaveIndexes(Set.of(-1, 196, 200)))
                .should(haveResult(124));
//                .should(haveResults(expectedResults(56, 56)
//                        .add(98, 98)
//                        .add(127, 127))
//                .shouldHaveResults(expectedValues(56, value.intEquals(56))
//                        .add(98, value.intEquals(98))
//                        .add(127, value.intEquals(127)))
//                .shouldNotHaveResult(value.intGreaterThan(195))
//                .shouldNotHaveResults(expectedValues(3, value.intNotEquals(3))
//                        .add(78, value.intNotEquals(78))
//                        .add(188, value.intNotEquals(188)));

        list.filter(block -> Web.index(intEquals(77)))
                .extractAll(blockIndex())
                .should(haveSize(1))
                .should(haveIndex(77))
                .should(haveResult(77));

        list.filter(block -> Web.index(intEquals(77)))
                .extractOne(blockIndex())
                .should(haveIndex(77))
                .should(haveResult(77));
    }

}
