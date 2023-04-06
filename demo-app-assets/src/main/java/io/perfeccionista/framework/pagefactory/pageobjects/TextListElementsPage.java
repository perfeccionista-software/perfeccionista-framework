package io.perfeccionista.framework.pagefactory.pageobjects;

import io.perfeccionista.framework.name.Name;
import io.perfeccionista.framework.pagefactory.elements.WebList;
import io.perfeccionista.framework.pagefactory.elements.WebNode;
import io.perfeccionista.framework.pagefactory.elements.selectors.WebItemSelector;
import io.perfeccionista.framework.pagefactory.elements.selectors.WebSelector;

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
    @WebSelector(id = "text-list")
    @WebItemSelector(xpath = "self::node()//div[@itemid = 'text-list-item']")
    WebList<WebNode> textList();

}
