package io.perfeccionista.framework.pagefactory.elements;

import io.perfeccionista.framework.Environment;
import io.perfeccionista.framework.pagefactory.AbstractUiTest;
import io.perfeccionista.framework.pagefactory.context.base.WebPageContext;
import io.perfeccionista.framework.pagefactory.pageobjects.ElementsPage;
import io.perfeccionista.framework.pagefactory.pageobjects.HomePage;
import io.perfeccionista.framework.value.ValueService;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static io.perfeccionista.framework.matcher.WebElementAssertions.beDisplayed;
import static io.perfeccionista.framework.matcher.WebElementAssertions.haveText;

@Tag("Element")
class WebElementActionTest extends AbstractUiTest {

    @Test
    void webElementDoubleClickText(Environment env, ValueService value) {
        WebPageContext context = initWebPageContext(env, value);
        context.getPage(HomePage.class)
                .leftMenu()
                .select("Elements");

        ElementsPage elementsPage = context.getPage(ElementsPage.class);
        elementsPage.doubleClickButton()
                .should(beDisplayed())
                .scrollTo();
        elementsPage.doubleClickText()
                .should(beDisplayed())
                .should(haveText(value.stringEmpty()));
        elementsPage.doubleClickButton()
                .click();
        elementsPage.doubleClickText()
                .should(haveText(value.stringEmpty()));
        elementsPage.doubleClickButton()
                .executeAction("Double click");
        elementsPage.doubleClickText()
                .should(haveText("Double click done"));
    }

}
