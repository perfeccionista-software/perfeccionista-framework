package io.perfeccionista.framework.pagefactory.pageobjects;

import io.perfeccionista.framework.name.Name;
import io.perfeccionista.framework.pagefactory.elements.WebTextList;
import io.perfeccionista.framework.pagefactory.elements.locators.WebListBlockLocator;
import io.perfeccionista.framework.pagefactory.elements.locators.WebLocator;

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
    @WebListBlockLocator(xpath = "self::node()//div[@itemid = 'text-list-item']")
    WebTextList textList();

}
