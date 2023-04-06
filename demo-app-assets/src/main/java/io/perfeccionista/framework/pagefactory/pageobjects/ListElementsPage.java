package io.perfeccionista.framework.pagefactory.pageobjects;

import io.perfeccionista.framework.name.Name;
import io.perfeccionista.framework.pagefactory.elements.WebList;
import io.perfeccionista.framework.pagefactory.elements.selectors.WebItemSelector;
import io.perfeccionista.framework.pagefactory.elements.selectors.WebSelector;
import io.perfeccionista.framework.pagefactory.pageobjects.blocks.list.CountryBlock;

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
    @WebSelector(id = "countries-list")
    @WebItemSelector(xpath = ".//div[@itemid = 'countries-list-item']")
    WebList<CountryBlock> webList();

}
