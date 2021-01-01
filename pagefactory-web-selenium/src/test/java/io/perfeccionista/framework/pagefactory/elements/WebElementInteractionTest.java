package io.perfeccionista.framework.pagefactory.elements;

import io.perfeccionista.framework.AbstractWebSeleniumParallelTest;
import io.perfeccionista.framework.pagefactory.context.base.WebPageContext;
import io.perfeccionista.framework.pagefactory.pageobjects.ElementsPage;
import io.perfeccionista.framework.pagefactory.pageobjects.HomePage;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static io.perfeccionista.framework.matcher.WebElementAssertions.beDisplayed;
import static io.perfeccionista.framework.matcher.WebElementAssertions.haveText;
import static io.perfeccionista.framework.value.Values.stringEmpty;

@Tag("WebElement") @Tag("WebElementInteraction")
class WebElementInteractionTest extends AbstractWebSeleniumParallelTest {

    @Test
    void webElementInteractionTest() {
        WebPageContext context = initWebPageContext();
        context.getPage(HomePage.class).leftMenu()
                .select("Elements");

        ElementsPage elementsPage = context.getPage(ElementsPage.class);
        elementsPage.sourceBlock()
                .should(beDisplayed())
                .scrollTo();
        elementsPage.targetBlock()
                .should(beDisplayed());
        elementsPage.dragAndDropText()
                .should(beDisplayed())
                .should(haveText(stringEmpty()));
        elementsPage.sourceBlock()
                .executeInteraction("Drag and Drop", elementsPage.targetBlock());
        elementsPage.dragAndDropText()
                .should(haveText("Source moved"));
    }

}
