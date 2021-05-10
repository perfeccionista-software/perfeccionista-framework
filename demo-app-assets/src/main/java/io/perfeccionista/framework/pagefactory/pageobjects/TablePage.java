package io.perfeccionista.framework.pagefactory.pageobjects;

import io.perfeccionista.framework.name.Name;
import io.perfeccionista.framework.pagefactory.elements.WebTable;
import io.perfeccionista.framework.pagefactory.elements.locators.WebLocator;
import io.perfeccionista.framework.pagefactory.elements.mapping.UseMappedWebTableColumn;
import io.perfeccionista.framework.pagefactory.elements.mapping.UseMappedWebTableColumns;
import io.perfeccionista.framework.pagefactory.operation.handler.JsCheckIsDisplayed;
import io.perfeccionista.framework.pagefactory.pageobjects.blocks.table.CheckboxWebBlock;
import io.perfeccionista.framework.pagefactory.pageobjects.blocks.table.CountryNumberWebBlock;
import io.perfeccionista.framework.pagefactory.pageobjects.blocks.table.FullCountryNameWebBlock;
import io.perfeccionista.framework.pagefactory.pageobjects.blocks.table.HeaderWebBlock;
import io.perfeccionista.framework.pagefactory.pageobjects.blocks.table.PopulationWebBlock;
import io.perfeccionista.framework.pagefactory.pageobjects.blocks.table.CountryNameWebBlock;

@Name("Page with table of countries")
@Name("Страница с таблицей стран")
public interface TablePage extends AbstractWebPage {

    String CHECKBOX = "Checkbox";
    String NUMBER = "Number";
    String SHORT_COUNTRY_NAME = "Short country name";
    String FULL_COUNTRY_NAME = "Full country name";
    String POPULATION = "Population";

    @Name("Table of countries")
    @Name("Таблица стран")
    @WebLocator(tagName = "table", invokeOnCall = JsCheckIsDisplayed.class)
    @UseMappedWebTableColumns({
            @UseMappedWebTableColumn(name = CHECKBOX,
                    headerClass = HeaderWebBlock.class, headerLocator = @WebLocator(xpath = ".//th[1]"),
                    bodyClass = CheckboxWebBlock.class, bodyLocator = @WebLocator(xpath = ".//td[1]")),
            @UseMappedWebTableColumn(name = NUMBER,
                    headerClass = HeaderWebBlock.class, headerLocator = @WebLocator(xpath = ".//th[2]"),
                    bodyClass = CountryNumberWebBlock.class, bodyLocator = @WebLocator(xpath = ".//td[2]")),
            @UseMappedWebTableColumn(name = SHORT_COUNTRY_NAME,
                    headerClass = HeaderWebBlock.class, headerLocator = @WebLocator(xpath = ".//th[3]"),
                    bodyClass = CountryNameWebBlock.class, bodyLocator = @WebLocator(xpath = ".//td[3]")),
            @UseMappedWebTableColumn(name = FULL_COUNTRY_NAME,
                    headerClass = HeaderWebBlock.class, headerLocator = @WebLocator(xpath = ".//th[4]"),
                    bodyClass = FullCountryNameWebBlock.class, bodyLocator = @WebLocator(xpath = ".//td[4]")),
            @UseMappedWebTableColumn(name = POPULATION,
                    headerClass = HeaderWebBlock.class, headerLocator = @WebLocator(xpath = ".//th[5]"),
                    bodyClass = PopulationWebBlock.class, bodyLocator = @WebLocator(xpath = ".//td[5]"))
    })
    WebTable table();

}
