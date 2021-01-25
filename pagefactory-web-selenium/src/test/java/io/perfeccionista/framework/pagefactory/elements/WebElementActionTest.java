package io.perfeccionista.framework.pagefactory.elements;

import io.perfeccionista.framework.AbstractWebSeleniumParallelTest;
import io.perfeccionista.framework.pagefactory.dispatcher.context.WebPageContext;
import io.perfeccionista.framework.pagefactory.pageobjects.ElementsPage;
import io.perfeccionista.framework.pagefactory.pageobjects.HomePage;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static io.perfeccionista.framework.Web.*;
import static io.perfeccionista.framework.value.Values.stringEmpty;

@Tag("WebElement") @Tag("WebElementAction")
class WebElementActionTest extends AbstractWebSeleniumParallelTest {

    @Test
    void webElementDoubleClickText() {
        WebPageContext context = initWebPageContext();
        context.getPage(HomePage.class).leftMenu()
                .select("Elements");

        ElementsPage elementsPage = context.getPage(ElementsPage.class);
        elementsPage.doubleClickButton()
                .should(beDisplayed())
                .scrollTo();
        elementsPage.doubleClickText()
                .should(beDisplayed())
                .should(haveText(stringEmpty()));
        elementsPage.doubleClickButton()
                .click();
        elementsPage.doubleClickText()
                .should(haveText(stringEmpty()));
        elementsPage.doubleClickButton()
                .executeAction("Double click");
        elementsPage.doubleClickText()
                .should(haveText("Double click done"));
    }

}
