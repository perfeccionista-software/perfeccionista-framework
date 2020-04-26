package io.perfeccionista.framework.pagefactory.web.pageobjects.elements;

import io.perfeccionista.framework.name.Name;
import io.perfeccionista.framework.pagefactory.elements.WebLink;
import io.perfeccionista.framework.pagefactory.elements.WebMappedBlock;
import io.perfeccionista.framework.pagefactory.elements.WebList;
import io.perfeccionista.framework.pagefactory.elements.locators.WebLocator;
import io.perfeccionista.framework.pagefactory.elements.mapping.UseWebMappedBlock;
import io.perfeccionista.framework.pagefactory.filter.WebConditions;
import io.perfeccionista.framework.pagefactory.web.pageobjects.elements.LeftMenu.LeftMenuItemBlock;
import io.perfeccionista.framework.value.string.StringValue;

import static io.perfeccionista.framework.pagefactory.elements.mapping.WebMappingUtils.blockMock;
import static io.perfeccionista.framework.pagefactory.extractor.WebExtractors.*;
import static io.perfeccionista.framework.pagefactory.filter.WebConditions.containsText;
import static io.perfeccionista.framework.pagefactory.filter.WebFilters.with;

@Name("Основное меню")
@WebLocator(component = "LI", css = ".item", single = false)
@UseWebMappedBlock(LeftMenuItemBlock.class)
public interface LeftMenu extends WebList {

    default void select(StringValue itemValue) {
        this.filter(with(WebConditions.containsText(blockMock(LeftMenuItemBlock.class).item(), itemValue)))
                .extractOne(element(blockMock(LeftMenuItemBlock.class).item()))
                .shouldHaveNotNull().get()
                .click();
    }

    interface LeftMenuItemBlock extends WebMappedBlock {

        @Name("пункт Основного меню")
        @WebLocator(tagName = "a")
        WebLink item();

    }

}
