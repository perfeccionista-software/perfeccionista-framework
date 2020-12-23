package io.perfeccionista.framework.pagefactory.pageobjects;

import io.perfeccionista.framework.name.Name;
import io.perfeccionista.framework.pagefactory.elements.WebTextTable;
import io.perfeccionista.framework.pagefactory.elements.locators.WebLocator;
import io.perfeccionista.framework.pagefactory.elements.mapping.UseMappedWebTextTableColumn;
import io.perfeccionista.framework.pagefactory.elements.mapping.UseMappedWebTextTableColumns;
import io.perfeccionista.framework.pagefactory.jsfunction.CheckIsDisplayed;

@Name("Page with simple table of countries")
@Name("Страница с простой таблицей стран")
public interface TextTablePage extends AbstractWebPage {

    String NUMBER = "Number";
    String SHORT_COUNTRY_NAME = "Short country name";
    String FULL_COUNTRY_NAME = "Full country name";
    String POPULATION = "Population";

    @Name("Simple table of countries")
    @Name("Простая таблица стран")
    @WebLocator(tagName = "table", invokeOnCall = {CheckIsDisplayed.class})
    @UseMappedWebTextTableColumns({
            @UseMappedWebTextTableColumn(name = NUMBER,
                    headerLocator = @WebLocator(xpath = ".//th[1]"),
                    bodyLocator = @WebLocator(xpath = ".//td[1]")),
            @UseMappedWebTextTableColumn(name = SHORT_COUNTRY_NAME,
                    headerLocator = @WebLocator(xpath = ".//th[2]"),
                    bodyLocator = @WebLocator(xpath = ".//td[2]")),
            @UseMappedWebTextTableColumn(name = FULL_COUNTRY_NAME,
                    headerLocator = @WebLocator(xpath = ".//th[3]"),
                    bodyLocator = @WebLocator(xpath = ".//td[3]")),
            @UseMappedWebTextTableColumn(name = POPULATION,
                    headerLocator = @WebLocator(xpath = ".//th[4]"),
                    bodyLocator = @WebLocator(xpath = ".//td[4]"))
    })
    WebTextTable textTable();

}