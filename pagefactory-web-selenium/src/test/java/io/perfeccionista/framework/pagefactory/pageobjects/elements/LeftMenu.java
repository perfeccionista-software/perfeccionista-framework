package io.perfeccionista.framework.pagefactory.pageobjects.elements;

import io.perfeccionista.framework.name.Name;
import io.perfeccionista.framework.pagefactory.elements.WebLink;
import io.perfeccionista.framework.pagefactory.elements.WebMappedBlock;
import io.perfeccionista.framework.pagefactory.elements.WebList;
import io.perfeccionista.framework.pagefactory.elements.locators.WebLocator;
import io.perfeccionista.framework.pagefactory.elements.mapping.UseWebMappedBlock;
import io.perfeccionista.framework.pagefactory.pageobjects.elements.LeftMenu.LeftMenuItemBlock;
import io.perfeccionista.framework.value.string.StringValue;

import static io.perfeccionista.framework.pagefactory.elements.WebMappedBlock.from;
import static io.perfeccionista.framework.pagefactory.extractor.WebExtractors.*;
import static io.perfeccionista.framework.pagefactory.filter.WebConditions.containsText;
import static io.perfeccionista.framework.pagefactory.filter.WebFilters.with;

@WebLocator(component = "LI", css = ".list-group-item", single = false)
@UseWebMappedBlock(LeftMenuItemBlock.class)
public interface LeftMenu extends WebList {

    default void select(StringValue itemValue) {
        this.filter(with(containsText(from(LeftMenuItemBlock.class).item(), itemValue)))
                .extractOne(element(from(LeftMenuItemBlock.class).item()))
                .shouldHaveNotNull().get()
                .click();
    }

    interface LeftMenuItemBlock extends WebMappedBlock {

        @Name("ссылка")
        @WebLocator(tagName = "a")
        WebLink item();

    }

}
