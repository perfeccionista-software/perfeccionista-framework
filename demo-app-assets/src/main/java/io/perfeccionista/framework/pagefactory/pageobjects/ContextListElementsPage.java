package io.perfeccionista.framework.pagefactory.pageobjects;

import io.perfeccionista.framework.name.Name;
import io.perfeccionista.framework.pagefactory.elements.WebList;
import io.perfeccionista.framework.pagefactory.elements.locators.WebLocator;
import io.perfeccionista.framework.pagefactory.elements.mapping.UseMappedWebBlock;
import io.perfeccionista.framework.pagefactory.pageobjects.blocks.list.ContinentBlock;

import static io.perfeccionista.framework.pagefactory.elements.components.WebComponents.LI;

@Name("Page for List context")
@Name("Страница для контекста в списках")
public interface ContextListElementsPage extends AbstractWebPage {

    @Name("List of continents")
    @Name("Список континентов")
    @WebLocator(id = "continents-list")
    @WebLocator(component = LI, xpath = ".//div[@itemid = 'continents-list-item']", single = false)
    @UseMappedWebBlock(ContinentBlock.class)
    WebList continentsList();

}