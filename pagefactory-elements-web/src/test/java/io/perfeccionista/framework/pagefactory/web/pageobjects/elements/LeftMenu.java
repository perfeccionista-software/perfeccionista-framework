package io.perfeccionista.framework.pagefactory.web.pageobjects.elements;

import io.perfeccionista.framework.name.Name;
import io.perfeccionista.framework.pagefactory.elements.WebLink;
import io.perfeccionista.framework.pagefactory.elements.WebMappedBlock;
import io.perfeccionista.framework.pagefactory.elements.WebUnorderedList;
import io.perfeccionista.framework.pagefactory.elements.locators.WebLocator;
import io.perfeccionista.framework.pagefactory.elements.mapping.UseWebMappedBlock;
import io.perfeccionista.framework.pagefactory.extractor.WebBlockValueExtractor;
import io.perfeccionista.framework.pagefactory.extractor.block.WebBlockElementExtractor;
import io.perfeccionista.framework.pagefactory.filter.impl.JsBlockElementTextFilter;
import io.perfeccionista.framework.pagefactory.web.pageobjects.elements.LeftMenu.LeftMenuItemBlock;
import io.perfeccionista.framework.value.string.StringValue;

@Name("Основное меню")
@WebLocator(component = "LI", css = ".item", single = false)
@UseWebMappedBlock(LeftMenuItemBlock.class)
public interface LeftMenu extends WebUnorderedList {

    default void select(StringValue itemValue) {
        WebBlockValueExtractor<WebLink> extractor = new WebBlockElementExtractor<>("пункт Основного меню", WebLink.class);
        this.getValue(extractor, new JsBlockElementTextFilter("пункт Основного меню", itemValue)).click();
    }

    interface LeftMenuItemBlock extends WebMappedBlock {

        @Name("пункт Основного меню")
        @WebLocator(tagName = "a")
        WebLink item();

    }

}
