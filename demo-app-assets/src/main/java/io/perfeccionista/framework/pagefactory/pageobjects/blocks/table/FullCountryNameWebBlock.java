package io.perfeccionista.framework.pagefactory.pageobjects.blocks.table;

import io.perfeccionista.framework.name.Name;
import io.perfeccionista.framework.pagefactory.elements.WebBlock;
import io.perfeccionista.framework.pagefactory.elements.WebText;
import io.perfeccionista.framework.pagefactory.elements.locators.WebLocator;
import io.perfeccionista.framework.pagefactory.elements.properties.base.WebElementProperty;

public interface FullCountryNameWebBlock extends WebBlock {

    @Name("Full country name")
    @Name("Полное название государства")
    @WebLocator(xpath = "self::node()//span[@itemid = 'country-full-name']", strictSearch = false)
    @WebLocator(component = "SNG", xpath = "self::node()//span[@itemid = 'sng']")
    @WebElementProperty(name = "prompt", params = "title")
    WebText fullName();

}
