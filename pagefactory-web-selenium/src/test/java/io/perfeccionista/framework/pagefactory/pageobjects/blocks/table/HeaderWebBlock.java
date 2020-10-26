package io.perfeccionista.framework.pagefactory.pageobjects.blocks.table;

import io.perfeccionista.framework.name.Name;
import io.perfeccionista.framework.pagefactory.elements.WebBlock;
import io.perfeccionista.framework.pagefactory.elements.WebLink;
import io.perfeccionista.framework.pagefactory.elements.locators.WebLocator;

public interface HeaderWebBlock extends WebBlock {

    @Name("Title")
    @WebLocator(xpath = "self::node()")
    @WebLocator(component = "Not sorted", xpath = "self::node()[@aria-sort = 'none']")
    @WebLocator(component = "Sorted by asc", xpath = "self::node()[@aria-sort = 'ascending']")
    @WebLocator(component = "Sorted by desc", xpath = "self::node()[@aria-sort = 'descending']")
    WebLink columnTitle();

}
