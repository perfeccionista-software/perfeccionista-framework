package io.perfeccionista.framework.pagefactory.pageobjects;

import io.perfeccionista.framework.name.Name;
import io.perfeccionista.framework.pagefactory.elements.WebTextAutocomplete;
import io.perfeccionista.framework.pagefactory.elements.WebTextDropDownList;
import io.perfeccionista.framework.pagefactory.elements.WebTextList;
import io.perfeccionista.framework.pagefactory.elements.locators.WebLocator;

import static io.perfeccionista.framework.pagefactory.elements.components.WebComponents.LI;

@Name("Page with simple list of countries")
@Name("Страница с простым списком стран")
public interface TextListElementsPage extends AbstractWebPage {

////    @WebLocator(invokeOnCall = {IsDisplayedFunctionInvoke.class})
//    WebTextDropDownList simpleDropDownList();
//
////    @WebLocator(invokeOnCall = {IsDisplayedFunctionInvoke.class})
//    WebTextAutocomplete simpleAutocomplete();

    @Name("Simple list of countries")
    @Name("Простой список стран")
    @WebLocator(id = "text-list")
    @WebLocator(component = LI, xpath = "self::node()//div[@itemid = 'text-list-item']", single = false)
    WebTextList textList();

}
