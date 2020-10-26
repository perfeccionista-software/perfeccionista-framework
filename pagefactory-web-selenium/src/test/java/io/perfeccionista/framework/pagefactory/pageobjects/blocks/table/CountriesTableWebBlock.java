package io.perfeccionista.framework.pagefactory.pageobjects.blocks.table;

import io.perfeccionista.framework.name.Name;
import io.perfeccionista.framework.pagefactory.elements.WebBlock;
import io.perfeccionista.framework.pagefactory.elements.WebTable;
import io.perfeccionista.framework.pagefactory.elements.locators.WebLocator;
import io.perfeccionista.framework.pagefactory.elements.mapping.UseMappedWebTableColumn;
import io.perfeccionista.framework.pagefactory.elements.mapping.UseMappedWebTableColumns;
import io.perfeccionista.framework.pagefactory.jsfunction.CheckIsDisplayed;

import static io.perfeccionista.framework.pagefactory.elements.components.WebComponents.TBODY_ROW;
import static io.perfeccionista.framework.pagefactory.elements.components.WebComponents.THEAD_ROW;

public interface CountriesTableWebBlock extends WebBlock {

    String CHECKBOX = "Checkbox";
    String SHORT_NAME = "Short name";
    String FULL_NAME = "Full name";
    String POPULATION = "Population";

    @Name("Table of countries")
    @WebLocator(xpath = ".//table[@itemid = 'countries-table']", strictSearch = false)
    @WebLocator(component = THEAD_ROW, xpath = "./thead/tr")
    @WebLocator(component = TBODY_ROW, xpath = "./tbody/tr", single = false)
    @UseMappedWebTableColumns({
            @UseMappedWebTableColumn(name = CHECKBOX,
                    headerClass = HeaderWebBlock.class, headerLocator = @WebLocator(xpath = "./th[1]"),
                    bodyClass = CheckboxWebBlock.class, bodyLocator = @WebLocator(xpath = "./td[1]")),
            @UseMappedWebTableColumn(name = SHORT_NAME,
                    headerClass = HeaderWebBlock.class, headerLocator = @WebLocator(xpath = "./th[2]"),
                    bodyClass = ShortNameWebBlock.class, bodyLocator = @WebLocator(xpath = "./td[2]")),
            @UseMappedWebTableColumn(name = FULL_NAME,
                    headerClass = HeaderWebBlock.class, headerLocator = @WebLocator(xpath = "./th[3]"),
                    bodyClass = FullNameWebBlock.class, bodyLocator = @WebLocator(xpath = "./td[3]")),
            @UseMappedWebTableColumn(name = POPULATION,
                    headerClass = HeaderWebBlock.class, headerLocator = @WebLocator(xpath = "./th[4]"),
                    bodyClass = PopulationWebBlock.class, bodyLocator = @WebLocator(xpath = "./td[4]"))
    })
    WebTable countriesTable();

}
