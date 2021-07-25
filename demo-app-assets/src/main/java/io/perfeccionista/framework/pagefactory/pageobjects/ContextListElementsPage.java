package io.perfeccionista.framework.pagefactory.pageobjects;

import io.perfeccionista.framework.name.Name;
import io.perfeccionista.framework.pagefactory.elements.WebList;
import io.perfeccionista.framework.pagefactory.elements.locators.WebItemLocator;
import io.perfeccionista.framework.pagefactory.elements.locators.WebLocator;
import io.perfeccionista.framework.pagefactory.pageobjects.blocks.list.ContinentBlock;

@Name("Page for List context")
@Name("Страница для контекста в списках")
public interface ContextListElementsPage extends AbstractWebPage {

    @Name("List of continents")
    @Name("Список континентов")
    @WebLocator(id = "continents-list")
    @WebItemLocator(xpath = ".//div[@itemid = 'continents-list-item']")
    WebList<ContinentBlock> continentsList();

}
