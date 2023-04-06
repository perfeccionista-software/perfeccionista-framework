package io.perfeccionista.framework.pagefactory.pageobjects.blocks.table;

import io.perfeccionista.framework.name.Name;
import io.perfeccionista.framework.pagefactory.elements.WebBlock;
import io.perfeccionista.framework.pagefactory.elements.WebText;
import io.perfeccionista.framework.pagefactory.elements.selectors.WebSelector;

public interface FullCountryNameWebBlock extends WebBlock<FullCountryNameWebBlock> {

    @Name("Full country name")
    @Name("Полное название государства")
    @WebSelector(xpath = "self::node()//span[@itemid = 'country-full-name']", strictSearch = false)
    @WebSelector(component = "SNG", xpath = "self::node()//span[@itemid = 'sng']")
    WebText fullName();

}
