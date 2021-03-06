package io.perfeccionista.framework.pagefactory.elements;

import io.perfeccionista.framework.AbstractWebSeleniumParallelTest;
import io.perfeccionista.framework.pagefactory.dispatcher.context.WebPageContext;
import io.perfeccionista.framework.pagefactory.pageobjects.ElementsPage;
import io.perfeccionista.framework.pagefactory.pageobjects.HomePage;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static io.perfeccionista.framework.Web.*;
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
                .executeAction("Drag and Drop", elementsPage.targetBlock().getElementBounds().getCenter());
        elementsPage.dragAndDropText()
                .should(haveText("Source moved"));
    }

}
