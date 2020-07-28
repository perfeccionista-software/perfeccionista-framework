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
class WebElementActionTest extends AbstractUiTest {

    @Test
    void webElementDoubleClickText(Environment env, ValueService value) {
        WebPageContext context = initWebPageContext(env, value);
        context.getPage(HomePage.class)
                .leftMenu()
                .select(value.stringEquals("Elements"));
        ElementsPage elementsPage = context.getPage(ElementsPage.class);
        WebButton doubleClickButton = elementsPage.doubleClickButton()
                .shouldBeDisplayed()
                .scrollTo();
        WebTextBlock doubleClickText = elementsPage.doubleClickText()
                .shouldBeDisplayed()
                .shouldHaveText(value.stringEmpty());
        doubleClickButton.click();
        doubleClickText.shouldHaveText(value.stringEmpty());
        doubleClickButton.executeAction("Double click");
        doubleClickText.shouldHaveText(value.stringEquals("Double click done"));
    }

}
