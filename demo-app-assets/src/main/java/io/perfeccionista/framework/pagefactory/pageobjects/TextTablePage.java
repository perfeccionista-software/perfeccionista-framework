package io.perfeccionista.framework.pagefactory.pageobjects;

import io.perfeccionista.framework.name.Name;
import io.perfeccionista.framework.pagefactory.elements.WebBlock;
import io.perfeccionista.framework.pagefactory.elements.WebTable;
import io.perfeccionista.framework.pagefactory.elements.WebText;
import io.perfeccionista.framework.pagefactory.elements.selectors.WebSelector;
import io.perfeccionista.framework.pagefactory.elements.selectors.WebItemSelector;
import io.perfeccionista.framework.pagefactory.operation.handler.JsCheckIsDisplayed;

import static io.perfeccionista.framework.pagefactory.elements.ElementComponents.TABLE_HEADER;

@Name("Page with simple table of countries")
@Name("Страница с простой таблицей стран")
public interface TextTablePage extends AbstractWebPage {

    @Name("Simple table of countries")
    @Name("Простая таблица стран")
    @WebSelector(tagName = "table", invokeOnCall = JsCheckIsDisplayed.class)
    @WebSelector(component = TABLE_HEADER, xpath = ".//thead/tr")
    @WebItemSelector(xpath = ".//tbody/tr")
    WebTable<TextTableHeader, TextTableRow> textTable();

    interface TextTableHeader extends WebBlock<TextTableHeader> {

        @WebSelector(xpath = ".//th[1]")
        WebText number();

        @WebSelector(xpath = ".//th[2]")
        WebText shortName();

        @WebSelector(xpath = ".//th[3]")
        WebText fullName();

        @WebSelector(xpath = ".//th[4]")
        WebText population();

    }

    interface TextTableRow extends WebBlock<TextTableRow> {

        @WebSelector(xpath = ".//td[1]")
        WebText number();

        @WebSelector(xpath = ".//td[2]")
        WebText shortName();

        @WebSelector(xpath = ".//td[3]")
        WebText fullName();

        @WebSelector(xpath = ".//td[4]")
        WebText population();

    }

}
