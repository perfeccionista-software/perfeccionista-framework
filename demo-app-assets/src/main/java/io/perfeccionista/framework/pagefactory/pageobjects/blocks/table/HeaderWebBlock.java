package io.perfeccionista.framework.pagefactory.pageobjects.blocks.table;

import io.perfeccionista.framework.name.Name;
import io.perfeccionista.framework.pagefactory.elements.WebBlock;
import io.perfeccionista.framework.pagefactory.elements.WebLink;
import io.perfeccionista.framework.pagefactory.elements.selectors.WebSelector;

public interface HeaderWebBlock extends WebBlock<HeaderWebBlock> {

    @Name("Title")
    @Name("Заголовок")
    @WebSelector(selfNode = true)
    @WebSelector(component = "Not sorted", xpath = "self::node()[@aria-sort = 'none']")
    @WebSelector(component = "Sorted by asc", xpath = "self::node()[@aria-sort = 'ascending']")
    @WebSelector(component = "Sorted by desc", xpath = "self::node()[@aria-sort = 'descending']")
    WebLink columnTitle();

}
