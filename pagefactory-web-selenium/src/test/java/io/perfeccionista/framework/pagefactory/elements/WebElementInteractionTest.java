package io.perfeccionista.framework.pagefactory.elements;

import io.perfeccionista.framework.Environment;
import io.perfeccionista.framework.pagefactory.AbstractUiTest;
import io.perfeccionista.framework.pagefactory.browser.context.WebPageContext;
import io.perfeccionista.framework.pagefactory.pageobjects.ElementsPage;
import io.perfeccionista.framework.pagefactory.pageobjects.HomePage;
import io.perfeccionista.framework.value.ValueService;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static io.perfeccionista.framework.matcher.WebElementAssertions.beDisplayed;
import static io.perfeccionista.framework.matcher.WebElementAssertions.haveText;

@Tag("Element")
class WebElementInteractionTest extends AbstractUiTest {

    @Test
    void webElementInteractionTest(Environment env, ValueService value) {
        WebPageContext context = initWebPageContext(env, value);
        context.getPage(HomePage.class)
                .leftMenu()
                .select("Elements");

        ElementsPage elementsPage = context.getPage(ElementsPage.class);
        elementsPage.sourceBlock()
                .should(beDisplayed())
                .scrollTo();
        elementsPage.targetBlock()
                .should(beDisplayed());
        elementsPage.dragAndDropText()
                .should(beDisplayed())
                .should(haveText(value.stringEmpty()));
        elementsPage.sourceBlock()
                .executeInteraction("Drag and Drop", elementsPage.targetBlock());
        elementsPage.dragAndDropText()
                .should(haveText("Source moved"));
    }

}
