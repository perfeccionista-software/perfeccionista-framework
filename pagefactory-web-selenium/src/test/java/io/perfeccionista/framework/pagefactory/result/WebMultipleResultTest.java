package io.perfeccionista.framework.pagefactory.result;

import io.perfeccionista.framework.AbstractWebSeleniumParallelTest;
import io.perfeccionista.framework.pagefactory.context.base.WebPageContext;
import io.perfeccionista.framework.pagefactory.elements.WebList;
import io.perfeccionista.framework.pagefactory.filter.WebFilterConditions;
import io.perfeccionista.framework.pagefactory.filter.WebFilters;
import io.perfeccionista.framework.pagefactory.pageobjects.HomePage;
import io.perfeccionista.framework.pagefactory.pageobjects.ListElementsPage;
import org.junit.jupiter.api.Test;

import static io.perfeccionista.framework.matcher.WebElementAssertions.beDisplayed;
import static io.perfeccionista.framework.matcher.WebMultipleResultAssertions.haveIndex;
import static io.perfeccionista.framework.matcher.WebMultipleResultAssertions.haveResult;
import static io.perfeccionista.framework.matcher.WebMultipleResultAssertions.haveSize;
import static io.perfeccionista.framework.matcher.WebMultipleResultAssertions.notHaveIndex;
import static io.perfeccionista.framework.matcher.WebMultipleResultAssertions.notHaveSize;
import static io.perfeccionista.framework.pagefactory.extractor.WebExtractors.blockIndex;
import static io.perfeccionista.framework.pagefactory.filter.WebFilters.with;
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
        WebList list = listElementsPage.webList()
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

        list.filter(WebFilters.with(WebFilterConditions.blockIndex(intEquals(77))))
                .extractAll(blockIndex())
                .should(haveSize(1))
                .should(haveIndex(77))
                .should(haveResult(77));

        list.filter(with(WebFilterConditions.blockIndex(intEquals(77))))
                .extractOne(blockIndex())
                .should(haveIndex(77))
                .should(haveResult(77));
    }

}
