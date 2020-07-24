package io.perfeccionista.framework.pagefactory.pageobjects;

import io.perfeccionista.framework.name.Name;
import io.perfeccionista.framework.pagefactory.elements.WebTextTable;
import io.perfeccionista.framework.pagefactory.elements.locators.WebLocator;
import io.perfeccionista.framework.pagefactory.elements.mapping.UseWebMappedTableColumn;
import io.perfeccionista.framework.pagefactory.elements.mapping.UseWebMappedTableColumns;
import io.perfeccionista.framework.pagefactory.jsfunction.CheckIsDisplayed;

public interface TextTablePage extends AbstractWebPage {

    String NUMBER = "Number";
    String SHORT_NAME = "Short name";
    String FULL_NAME = "Full name";
    String POPULATION = "Population";

    @Name("Text table of countries")
    @WebLocator(tagName = "table", invokeOnCall = {CheckIsDisplayed.class})
    @UseWebMappedTableColumns({
            @UseWebMappedTableColumn(name = NUMBER,
                    headerLocator = @WebLocator(xpath = ".//th[1]"),
                    bodyLocator = @WebLocator(xpath = ".//td[1]")),
            @UseWebMappedTableColumn(name = SHORT_NAME,
                    headerLocator = @WebLocator(xpath = ".//th[2]"),
                    bodyLocator = @WebLocator(xpath = ".//td[2]")),
            @UseWebMappedTableColumn(name = FULL_NAME,
                    headerLocator = @WebLocator(xpath = ".//th[3]"),
                    bodyLocator = @WebLocator(xpath = ".//td[3]")),
            @UseWebMappedTableColumn(name = POPULATION,
                    headerLocator = @WebLocator(xpath = ".//th[4]"),
                    bodyLocator = @WebLocator(xpath = ".//td[4]"))
    })
    WebTextTable textTable();

}
