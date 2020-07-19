package io.perfeccionista.framework.pagefactory.pageobjects;

import io.perfeccionista.framework.name.Name;
import io.perfeccionista.framework.pagefactory.elements.WebCheckbox;
import io.perfeccionista.framework.pagefactory.elements.WebLink;
import io.perfeccionista.framework.pagefactory.elements.WebMappedBlock;
import io.perfeccionista.framework.pagefactory.elements.WebTable;
import io.perfeccionista.framework.pagefactory.elements.WebTextBlock;
import io.perfeccionista.framework.pagefactory.elements.locators.WebLocator;
import io.perfeccionista.framework.pagefactory.elements.mapping.UseWebMappedTableColumn;
import io.perfeccionista.framework.pagefactory.elements.mapping.UseWebMappedTableColumns;
import io.perfeccionista.framework.pagefactory.elements.properties.WebElementProperty;
import io.perfeccionista.framework.pagefactory.jsfunction.CheckIsDisplayed;
import io.perfeccionista.framework.pagefactory.pageobjects.extractors.HrefAttributeExtractor;
import io.perfeccionista.framework.pagefactory.pageobjects.extractors.TitleAttributeExtractor;

public interface TablePage extends AbstractWebPage {

    String CHECKBOX = "Checkbox";
    String NUMBER = "Number";
    String SHORT_NAME = "Short name";
    String FULL_NAME = "Full name";
    String POPULATION = "Population";

    @Name("Table of countries")
    @WebLocator(tagName = "table", invokeOnCall = {CheckIsDisplayed.class})
    @UseWebMappedTableColumns({
            @UseWebMappedTableColumn(name = CHECKBOX,
                    headerClass = HeaderWebMappedBlock.class, headerLocator = @WebLocator(xpath = ".//th[1]"),
                    bodyClass = CheckboxWebMappedBlock.class, bodyLocator = @WebLocator(xpath = ".//td[1]")),
            @UseWebMappedTableColumn(name = NUMBER,
                    headerClass = HeaderWebMappedBlock.class, headerLocator = @WebLocator(xpath = ".//th[2]"),
                    bodyClass = CountryNumberWebMappedBlock.class, bodyLocator = @WebLocator(xpath = ".//td[2]")),
            @UseWebMappedTableColumn(name = SHORT_NAME,
                    headerClass = HeaderWebMappedBlock.class, headerLocator = @WebLocator(xpath = ".//th[3]"),
                    bodyClass = ShortNameWebMappedBlock.class, bodyLocator = @WebLocator(xpath = ".//td[3]")),
            @UseWebMappedTableColumn(name = FULL_NAME,
                    headerClass = HeaderWebMappedBlock.class, headerLocator = @WebLocator(xpath = ".//th[4]"),
                    bodyClass = FullNameWebMappedBlock.class, bodyLocator = @WebLocator(xpath = ".//td[4]")),
            @UseWebMappedTableColumn(name = POPULATION,
                    headerClass = HeaderWebMappedBlock.class, headerLocator = @WebLocator(xpath = ".//th[5]"),
                    bodyClass = PopulationWebMappedBlock.class, bodyLocator = @WebLocator(xpath = ".//td[5]"))
    })
    WebTable table();

    // WebMappedBlocks

    interface HeaderWebMappedBlock extends WebMappedBlock {

        @Name("Title")
        @WebLocator(xpath = "self::node()")
        @WebLocator(component = "Not sorted", xpath = "self::node()[@aria-sort = 'none']")
        @WebLocator(component = "Sorted by asc", xpath = "self::node()[@aria-sort = 'ascending']")
        @WebLocator(component = "Sorted by desc", xpath = "self::node()[@aria-sort = 'descending']")
        WebLink columnTitle();

    }

    interface CheckboxWebMappedBlock extends WebMappedBlock {

        @Name("Select")
        @WebLocator(xpath = ".//input[@itemid = 'checkbox']/parent::node()")
        WebCheckbox checkbox();

    }

    interface CountryNumberWebMappedBlock extends WebMappedBlock {

        @Name("Text")
        @WebLocator(xpath = "self::node()")
        WebTextBlock number();

    }

    interface ShortNameWebMappedBlock extends WebMappedBlock {

        @Name("Short name")
        @WebLocator(xpath = "self::node()//a[@itemid = 'country-name']", strictSearch = false) // В некоторых ячейках этого элемента нет
        @WebLocator(component = "Self", xpath = "self::node()", strictSearch = false) // В некоторых ячейках этого элемента нет
        @WebElementProperty(name = "Wiki link", extractor = HrefAttributeExtractor.class)
        WebLink shortName();

    }

    interface FullNameWebMappedBlock extends WebMappedBlock {

        @Name("Full name")
        @WebLocator(xpath = "self::node()//span[@itemid = 'country-full-name']", strictSearch = false)
        @WebLocator(component = "SNG", xpath = "self::node()//span[@itemid = 'sng']")
        @WebElementProperty(name = "prompt", extractor = TitleAttributeExtractor.class)
        WebTextBlock fullName();

    }

    interface PopulationWebMappedBlock extends WebMappedBlock {

        @Name("Population")
        @WebLocator(xpath = "self::node()//span[@itemid = 'population-number']")
        WebTextBlock population();

        @Name("Population unit")
        @WebLocator(xpath = "self::node()//span[@itemid = 'population-unit']")
        @WebLocator(component = "Self", xpath = "self::node()", strictSearch = false)
        WebTextBlock populationUnit();

    }

}
