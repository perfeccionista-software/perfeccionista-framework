package io.perfeccionista.framework.pagefactory.pageobjects;

import io.perfeccionista.framework.name.Name;
import io.perfeccionista.framework.pagefactory.elements.WebList;
import io.perfeccionista.framework.pagefactory.elements.locators.WebLocator;
import io.perfeccionista.framework.pagefactory.elements.mapping.UseMappedWebBlock;
import io.perfeccionista.framework.pagefactory.pageobjects.blocks.list.CountryBlock;

import static io.perfeccionista.framework.pagefactory.elements.ElementComponents.LI;

@Name("Page with list of countries")
@Name("Страница со списком стран")
public interface ListElementsPage extends AbstractWebPage {

//    @UseWebMappedBlock(CountryBlock.class)
////    @WebLocator(invokeOnCall = {IsDisplayedFunctionInvoke.class})
//    WebDropDownList dropDownList();
//
//    @UseWebMappedBlock(CountryBlock.class)
////    @WebLocator(invokeOnCall = {IsDisplayedFunctionInvoke.class})
//    WebAutocomplete autocomplete();

    @Name("List of countries")
    @Name("Список стран")
    @WebLocator(id = "countries-list")
    @WebLocator(component = LI, xpath = ".//div[@itemid = 'countries-list-item']", single = false)
    @UseMappedWebBlock(CountryBlock.class)
    WebList<CountryBlock> webList();

}
