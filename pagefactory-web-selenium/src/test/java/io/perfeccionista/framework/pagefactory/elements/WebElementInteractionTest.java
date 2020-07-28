package io.perfeccionista.framework.pagefactory.elements;

import io.perfeccionista.framework.Environment;
import io.perfeccionista.framework.pagefactory.AbstractUiTest;
import io.perfeccionista.framework.pagefactory.browser.context.WebPageContext;
import io.perfeccionista.framework.pagefactory.pageobjects.ElementsPage;
import io.perfeccionista.framework.pagefactory.pageobjects.HomePage;
import io.perfeccionista.framework.value.ValueService;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;

@Tags(@Tag("Element"))
class WebElementInteractionTest extends AbstractUiTest {

    @Test
    void webElementInteractionTest(Environment env, ValueService value) {
        WebPageContext context = initWebPageContext(env, value);
        context.getPage(HomePage.class)
                .leftMenu()
                .select(value.stringEquals("Elements"));
        ElementsPage elementsPage = context.getPage(ElementsPage.class);
        WebTextBlock sourceBlock = elementsPage.sourceBlock()
                .shouldBeDisplayed()
                .scrollTo();
        WebTextBlock targetBlock = elementsPage.targetBlock()
                .shouldBeDisplayed();
        WebTextBlock dragAndDropText = elementsPage.dragAndDropText()
                .shouldBeDisplayed()
                .shouldHaveText(value.stringEmpty());
        sourceBlock.executeInteraction("Drag and Drop", targetBlock);
        dragAndDropText.shouldHaveText(value.stringEquals("Source moved"));
    }

}
