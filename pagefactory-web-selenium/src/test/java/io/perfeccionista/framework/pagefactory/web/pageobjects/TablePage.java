package io.perfeccionista.framework.pagefactory.web.pageobjects;

import io.perfeccionista.framework.name.Name;
import io.perfeccionista.framework.pagefactory.elements.WebButton;
import io.perfeccionista.framework.pagefactory.elements.WebCheckbox;
import io.perfeccionista.framework.pagefactory.elements.WebLink;
import io.perfeccionista.framework.pagefactory.elements.WebMappedBlock;
import io.perfeccionista.framework.pagefactory.elements.WebTable;
import io.perfeccionista.framework.pagefactory.elements.WebTextBlock;
import io.perfeccionista.framework.pagefactory.elements.locators.WebLocator;
import io.perfeccionista.framework.pagefactory.elements.mapping.UseWebMappedTableColumn;
import io.perfeccionista.framework.pagefactory.elements.mapping.UseWebMappedTableColumns;
import io.perfeccionista.framework.pagefactory.elements.properties.WebElementProperty;
import io.perfeccionista.framework.pagefactory.web.pageobjects.extractors.HrefAttributeWebElementPropertyExtractor;

public interface TablePage extends AbstractWebPage {

    String CHECKBOX = "Checkbox";
    String NUMBER = "Number";
    String SHORT_NAME = "Short name";
    String FULL_NAME = "Full name";

    @Name("table of countries")
//    @WebLocator(css = ".table", invokeOnCall = {IsDisplayedFunctionInvoke.class})
    @UseWebMappedTableColumns({
            @UseWebMappedTableColumn(name = CHECKBOX,
                    headerClass = CheckboxWebMappedBlock.class, headerLocator = @WebLocator(xpath = ".//thead//th[1]"),
                    bodyClass = CheckboxWebMappedBlock.class, bodyLocator = @WebLocator(xpath = ".//tbody//td[1]")),
            @UseWebMappedTableColumn(name = NUMBER,
                    headerClass = HeaderWebMappedBlock.class, headerLocator = @WebLocator(xpath = ".//thead//th[2]"),
                    bodyClass = SimpleStringWebMappedBlock.class, bodyLocator = @WebLocator(xpath = ".//tbody//td[2]"),
                    footerClass = SimpleStringWebMappedBlock.class, footerLocator = @WebLocator(xpath = ".//tfoot//td[2]")),
            @UseWebMappedTableColumn(name = SHORT_NAME,
                    headerClass = HeaderWebMappedBlock.class, headerLocator = @WebLocator(xpath = ".//thead//th[3]"),
                    bodyClass = LinkWebMappedBlock.class, bodyLocator = @WebLocator(xpath = ".//tbody//td[3]")),
            @UseWebMappedTableColumn(name = FULL_NAME,
                    headerClass = HeaderWebMappedBlock.class, headerLocator = @WebLocator(xpath = ".//thead//th[4]"),
                    bodyClass = FullNameWebMappedBlock.class, bodyLocator = @WebLocator(xpath = ".//tbody//td[4]"))
    })
    WebTable table();

    // Blocks

    interface HeaderWebMappedBlock extends WebMappedBlock {

        @WebLocator(css = ".column-title")
        WebLink columnTitle();

        @WebLocator(css = ".column-sort-button")
        @WebLocator(component = "Not sorted", css = ".column-sort-button")
        @WebLocator(component = "Sorted by asc", css = ".column-sort-button-asc")
        @WebLocator(component = "Sorted by desc", css = ".column-sort-button-desc")
        WebButton sortButton();

    }

    interface SimpleStringWebMappedBlock extends WebMappedBlock {

        @Name("text")
        @WebLocator(css = ".cell-value")
        WebTextBlock text();

    }

    interface FullNameWebMappedBlock extends WebMappedBlock {

        @Name("text Full Name")
        @WebLocator(component = "SNG", css = ".cell-value-sng")
        @WebLocator(css = ".cell-value")
        WebTextBlock fullName();

    }

    interface CheckboxWebMappedBlock extends WebMappedBlock {

        @Name("checkbox Select")
        @WebLocator(css = ".row-select")
        WebCheckbox checkbox();

    }

    interface LinkWebMappedBlock extends WebMappedBlock {

        @Name("link Short name")
        @WebLocator(css = ".short-name-link")
        @WebElementProperty(name = "Wiki link", extractor = HrefAttributeWebElementPropertyExtractor.class)
        WebLink shortName();

    }

}
