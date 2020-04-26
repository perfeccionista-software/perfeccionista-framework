package io.perfeccionista.framework.pagefactory.web.pageobjects;

import io.perfeccionista.framework.name.Name;
import io.perfeccionista.framework.pagefactory.elements.WebAutocomplete;
import io.perfeccionista.framework.pagefactory.elements.WebCheckbox;
import io.perfeccionista.framework.pagefactory.elements.WebDropDownList;
import io.perfeccionista.framework.pagefactory.elements.WebLink;
import io.perfeccionista.framework.pagefactory.elements.WebMappedBlock;
import io.perfeccionista.framework.pagefactory.elements.WebTextBlock;
import io.perfeccionista.framework.pagefactory.elements.WebList;
import io.perfeccionista.framework.pagefactory.elements.locators.WebLocator;
import io.perfeccionista.framework.pagefactory.elements.mapping.UseWebMappedBlock;
import io.perfeccionista.framework.pagefactory.elements.properties.WebElementProperty;
import io.perfeccionista.framework.pagefactory.js.checks.IsDisplayedAction;
import io.perfeccionista.framework.pagefactory.web.pageobjects.extractors.HrefAttributeElementPropertyExtractor;

public interface ListElementsPage extends AbstractWebPage {

    @UseWebMappedBlock(CountryNameBlock.class)
    @WebLocator(executeOnCall = {IsDisplayedAction.class})
    WebDropDownList dropDownList();

    @UseWebMappedBlock(CountryNameBlock.class)
    @WebLocator(executeOnCall = {IsDisplayedAction.class})
    WebAutocomplete autocomplete();

    @Name("list of countries")
    @UseWebMappedBlock(CountryNameBlock.class)
    @WebLocator(executeOnCall = {IsDisplayedAction.class})
    WebList webList();

    interface CountryNameBlock extends WebMappedBlock {

        @Name("Checkbox")
        @WebLocator(executeOnCall = {IsDisplayedAction.class})
        WebCheckbox checkbox();

        @Name("Number")
        @WebLocator(executeOnCall = {IsDisplayedAction.class})
        WebTextBlock number();

        @Name("Short name")
        @WebLocator(executeOnCall = {IsDisplayedAction.class})
        @WebElementProperty(name = "Wiki link", extractor = HrefAttributeElementPropertyExtractor.class)
        WebLink shortName();

        @Name("Full name")
        @WebLocator(component = "SNG", css = ".sng-country")
        @WebLocator(executeOnCall = {IsDisplayedAction.class})
        WebTextBlock fullName();

    }

}
