package io.perfeccionista.framework.pagefactory.web.pageobjects;

import io.perfeccionista.framework.name.Name;
import io.perfeccionista.framework.pagefactory.elements.WebTextTable;
import io.perfeccionista.framework.pagefactory.elements.locators.WebLocator;
import io.perfeccionista.framework.pagefactory.elements.mapping.UseWebMappedTableColumn;
import io.perfeccionista.framework.pagefactory.elements.mapping.UseWebMappedTableColumns;

public interface SimpleTablePage extends AbstractWebPage {
    String NUMBER = "Number";
    String SHORT_NAME = "Short name";
    String FULL_NAME = "Full name";

    @Name("таблица Стран")
//    @WebLocator(css = ".simple-table", invokeOnCall = {IsDisplayedFunctionInvoke.class})
    @UseWebMappedTableColumns({
            @UseWebMappedTableColumn(name = NUMBER,
                    headerLocator = @WebLocator(xpath = ".//thead//th[1]"),
                    bodyLocator = @WebLocator(xpath = ".//tbody//td[1]"),
                    footerLocator = @WebLocator(xpath = ".//tfoot//td[1]")),
            @UseWebMappedTableColumn(name = SHORT_NAME,
                    headerLocator = @WebLocator(xpath = ".//thead//th[2]"),
                    bodyLocator = @WebLocator(xpath = ".//tbody//td[2]"),
                    footerLocator = @WebLocator(xpath = ".//tfoot//td[2]")),
            @UseWebMappedTableColumn(name = FULL_NAME,
                    headerLocator = @WebLocator(xpath = ".//thead//th[3]"),
                    bodyLocator = @WebLocator(xpath = ".//tbody//td[3]"),
                    footerLocator = @WebLocator(xpath = ".//tfoot//td[3]"))
    })
    WebTextTable simpleTable();

}
