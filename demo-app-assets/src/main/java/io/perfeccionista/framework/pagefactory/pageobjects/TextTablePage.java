package io.perfeccionista.framework.pagefactory.pageobjects;

import io.perfeccionista.framework.name.Name;
import io.perfeccionista.framework.pagefactory.elements.DefaultWebTextBlock;
import io.perfeccionista.framework.pagefactory.elements.WebBlock;
import io.perfeccionista.framework.pagefactory.elements.WebTable;
import io.perfeccionista.framework.pagefactory.elements.locators.WebLocator;
import io.perfeccionista.framework.pagefactory.elements.locators.WebItemLocator;
import io.perfeccionista.framework.pagefactory.operation.handler.JsCheckIsDisplayed;

import static io.perfeccionista.framework.pagefactory.elements.ElementComponents.TABLE_HEADER;

@Name("Page with simple table of countries")
@Name("Страница с простой таблицей стран")
public interface TextTablePage extends AbstractWebPage {

    @Name("Simple table of countries")
    @Name("Простая таблица стран")
    @WebLocator(tagName = "table", invokeOnCall = JsCheckIsDisplayed.class)
    @WebLocator(component = TABLE_HEADER, xpath = ".//thead/tr")
    @WebItemLocator(xpath = ".//tbody/tr")
    WebTable<TextTableHeader, TextTableRow> textTable();

    interface TextTableHeader extends WebBlock {

        @WebLocator(xpath = ".//th[1]")
        DefaultWebTextBlock number();

        @WebLocator(xpath = ".//th[2]")
        DefaultWebTextBlock shortName();

        @WebLocator(xpath = ".//th[3]")
        DefaultWebTextBlock fullName();

        @WebLocator(xpath = ".//th[4]")
        DefaultWebTextBlock population();

    }

    interface TextTableRow extends WebBlock {

        @WebLocator(xpath = ".//td[1]")
        DefaultWebTextBlock number();

        @WebLocator(xpath = ".//td[2]")
        DefaultWebTextBlock shortName();

        @WebLocator(xpath = ".//td[3]")
        DefaultWebTextBlock fullName();

        @WebLocator(xpath = ".//td[4]")
        DefaultWebTextBlock population();

    }

}
