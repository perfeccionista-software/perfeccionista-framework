package io.perfeccionista.framework.pagefactory.pageobjects.elements;

import io.perfeccionista.framework.name.Name;
import io.perfeccionista.framework.pagefactory.elements.WebBlock;
import io.perfeccionista.framework.pagefactory.elements.WebLink;
import io.perfeccionista.framework.pagefactory.elements.WebList;
import io.perfeccionista.framework.pagefactory.elements.selectors.WebItemSelector;
import io.perfeccionista.framework.pagefactory.elements.selectors.WebSelector;
import io.perfeccionista.framework.pagefactory.pageobjects.elements.LeftMenu.LeftMenuItemBlock;
import io.perfeccionista.framework.value.string.StringValue;
import org.jetbrains.annotations.NotNull;

import static io.perfeccionista.framework.Web.*;
import static io.perfeccionista.framework.pagefactory.elements.WebBlock.frame;

@WebItemSelector(css = ".list-group-item")
public interface LeftMenu extends WebList<LeftMenuItemBlock> {

    default void select(@NotNull StringValue expectedValue) {
        this.filterBuilder(block -> with(containsText(block.item(), expectedValue)))
                .forSingle(leftMenuItemBlock -> leftMenuItemBlock.item().click());
    }

    default void select(@NotNull String expectedText) {
        this.filterBuilder(block -> with(containsText(frame(LeftMenuItemBlock.class).item(), expectedText)))
                .forSingle(leftMenuItemBlock -> leftMenuItemBlock.item().click());
    }

    interface LeftMenuItemBlock extends WebBlock<LeftMenuItemBlock> {

        @Name("link Menu item")
        @Name("ссылка Пункт меню")
        @WebSelector(tagName = "a")
        WebLink item();

    }

}
