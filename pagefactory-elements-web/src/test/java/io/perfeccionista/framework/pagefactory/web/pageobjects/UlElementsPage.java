package io.perfeccionista.framework.pagefactory.web.pageobjects;

import io.perfeccionista.framework.name.Name;
import io.perfeccionista.framework.pagefactory.elements.WebAutocomplete;
import io.perfeccionista.framework.pagefactory.elements.WebCheckbox;
import io.perfeccionista.framework.pagefactory.elements.WebDropDownList;
import io.perfeccionista.framework.pagefactory.elements.WebLink;
import io.perfeccionista.framework.pagefactory.elements.WebMappedBlock;
import io.perfeccionista.framework.pagefactory.elements.WebTextBlock;
import io.perfeccionista.framework.pagefactory.elements.WebUnorderedList;
import io.perfeccionista.framework.pagefactory.elements.locators.WebLocator;
import io.perfeccionista.framework.pagefactory.elements.mapping.UseWebMappedBlock;
import io.perfeccionista.framework.pagefactory.elements.properties.WebElementProperty;
import io.perfeccionista.framework.pagefactory.js.checks.IsDisplayedCheck;
import io.perfeccionista.framework.pagefactory.web.pageobjects.extractors.HrefAttributeElementPropertyExtractor;

public interface UlElementsPage extends AbstractWebPage {

    @UseWebMappedBlock(CountryNameBlock.class)
    @WebLocator(elementChecks = {IsDisplayedCheck.class})
    WebDropDownList dropDownList();

    @UseWebMappedBlock(CountryNameBlock.class)
    @WebLocator(elementChecks = {IsDisplayedCheck.class})
    WebAutocomplete autocomplete();

    @UseWebMappedBlock(CountryNameBlock.class)
    @WebLocator(elementChecks = {IsDisplayedCheck.class})
    WebUnorderedList unorderedList();

    interface CountryNameBlock extends WebMappedBlock {

        @Name("Checkbox")
        @WebLocator(elementChecks = {IsDisplayedCheck.class})
        WebCheckbox checkbox();

        @Name("Number")
        @WebLocator(elementChecks = {IsDisplayedCheck.class})
        WebTextBlock number();

        @Name("Short name")
        @WebLocator(elementChecks = {IsDisplayedCheck.class})
        @WebElementProperty(name = "Wiki link", extractor = HrefAttributeElementPropertyExtractor.class)
        WebLink shortName();

        @Name("Full name")
        @WebLocator(component = "SNG", css = ".sng-country")
        @WebLocator(elementChecks = {IsDisplayedCheck.class})
        WebTextBlock fullName();

    }

}
