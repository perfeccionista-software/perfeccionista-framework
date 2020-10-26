package io.perfeccionista.framework.pagefactory.pageobjects.blocks.table;

import io.perfeccionista.framework.name.Name;
import io.perfeccionista.framework.pagefactory.elements.WebBlock;
import io.perfeccionista.framework.pagefactory.elements.WebTextBlock;
import io.perfeccionista.framework.pagefactory.elements.locators.WebLocator;
import io.perfeccionista.framework.pagefactory.elements.properties.base.WebElementProperty;
import io.perfeccionista.framework.pagefactory.pageobjects.extractors.TitleAttributeExtractor;

public interface FullNameWebBlock extends WebBlock {

    @Name("Full name")
    @WebLocator(xpath = "self::node()//span[@itemid = 'country-full-name']", strictSearch = false)
    @WebLocator(component = "SNG", xpath = "self::node()//span[@itemid = 'sng']")
    @WebElementProperty(name = "prompt", extractor = TitleAttributeExtractor.class)
    WebTextBlock fullName();

}
