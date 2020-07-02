package io.perfeccionista.framework.pagefactory.pageobjects;

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
import io.perfeccionista.framework.pagefactory.pageobjects.extractors.HrefAttributeWebElementPropertyExtractor;

public interface ListElementsPage extends AbstractWebPage {

    @UseWebMappedBlock(CountryNameBlock.class)
//    @WebLocator(invokeOnCall = {IsDisplayedFunctionInvoke.class})
    WebDropDownList dropDownList();

    @UseWebMappedBlock(CountryNameBlock.class)
//    @WebLocator(invokeOnCall = {IsDisplayedFunctionInvoke.class})
    WebAutocomplete autocomplete();

    @Name("list of countries")
    @UseWebMappedBlock(CountryNameBlock.class)
//    @WebLocator(invokeOnCall = {IsDisplayedFunctionInvoke.class})
    WebList webList();

    interface CountryNameBlock extends WebMappedBlock {

        @Name("Checkbox")
//        @WebLocator(invokeOnCall = {IsDisplayedFunctionInvoke.class})
        WebCheckbox checkbox();

        @Name("Number")
//        @WebLocator(invokeOnCall = {IsDisplayedFunctionInvoke.class})
        WebTextBlock number();

        @Name("Short name")
//        @WebLocator(invokeOnCall = {IsDisplayedFunctionInvoke.class})
        @WebElementProperty(name = "Wiki link", extractor = HrefAttributeWebElementPropertyExtractor.class)
        WebLink shortName();

        @Name("Full name")
        @WebLocator(component = "SNG", css = ".sng-country")
//        @WebLocator(invokeOnCall = {IsDisplayedFunctionInvoke.class})
        WebTextBlock fullName();

    }

}
