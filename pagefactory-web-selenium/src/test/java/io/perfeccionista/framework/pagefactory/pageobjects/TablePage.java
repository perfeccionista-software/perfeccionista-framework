package io.perfeccionista.framework.pagefactory.pageobjects;

import io.perfeccionista.framework.name.Name;
import io.perfeccionista.framework.pagefactory.elements.WebTable;
import io.perfeccionista.framework.pagefactory.elements.locators.WebLocator;
import io.perfeccionista.framework.pagefactory.elements.mapping.UseMappedWebTableColumn;
import io.perfeccionista.framework.pagefactory.elements.mapping.UseMappedWebTableColumns;
import io.perfeccionista.framework.pagefactory.jsfunction.CheckIsDisplayed;
import io.perfeccionista.framework.pagefactory.pageobjects.blocks.table.CheckboxWebBlock;
import io.perfeccionista.framework.pagefactory.pageobjects.blocks.table.CountryNumberWebBlock;
import io.perfeccionista.framework.pagefactory.pageobjects.blocks.table.FullNameWebBlock;
import io.perfeccionista.framework.pagefactory.pageobjects.blocks.table.HeaderWebBlock;
import io.perfeccionista.framework.pagefactory.pageobjects.blocks.table.PopulationWebBlock;
import io.perfeccionista.framework.pagefactory.pageobjects.blocks.table.ShortNameWebBlock;

public interface TablePage extends AbstractWebPage {

    String CHECKBOX = "Checkbox";
    String NUMBER = "Number";
    String SHORT_NAME = "Short name";
    String FULL_NAME = "Full name";
    String POPULATION = "Population";

    @Name("Table of countries")
    @WebLocator(tagName = "table", invokeOnCall = {CheckIsDisplayed.class})
    @UseMappedWebTableColumns({
            @UseMappedWebTableColumn(name = CHECKBOX,
                    headerClass = HeaderWebBlock.class, headerLocator = @WebLocator(xpath = ".//th[1]"),
                    bodyClass = CheckboxWebBlock.class, bodyLocator = @WebLocator(xpath = ".//td[1]")),
            @UseMappedWebTableColumn(name = NUMBER,
                    headerClass = HeaderWebBlock.class, headerLocator = @WebLocator(xpath = ".//th[2]"),
                    bodyClass = CountryNumberWebBlock.class, bodyLocator = @WebLocator(xpath = ".//td[2]")),
            @UseMappedWebTableColumn(name = SHORT_NAME,
                    headerClass = HeaderWebBlock.class, headerLocator = @WebLocator(xpath = ".//th[3]"),
                    bodyClass = ShortNameWebBlock.class, bodyLocator = @WebLocator(xpath = ".//td[3]")),
            @UseMappedWebTableColumn(name = FULL_NAME,
                    headerClass = HeaderWebBlock.class, headerLocator = @WebLocator(xpath = ".//th[4]"),
                    bodyClass = FullNameWebBlock.class, bodyLocator = @WebLocator(xpath = ".//td[4]")),
            @UseMappedWebTableColumn(name = POPULATION,
                    headerClass = HeaderWebBlock.class, headerLocator = @WebLocator(xpath = ".//th[5]"),
                    bodyClass = PopulationWebBlock.class, bodyLocator = @WebLocator(xpath = ".//td[5]"))
    })
    WebTable table();

}
