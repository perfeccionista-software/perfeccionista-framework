package io.perfeccionista.framework.pagefactory.pageobjects.elements;

import io.perfeccionista.framework.name.Name;
import io.perfeccionista.framework.pagefactory.elements.WebBlock;
import io.perfeccionista.framework.pagefactory.elements.WebLink;
import io.perfeccionista.framework.pagefactory.elements.WebList;
import io.perfeccionista.framework.pagefactory.elements.locators.WebLocator;
import io.perfeccionista.framework.pagefactory.elements.mapping.UseMappedWebBlock;
import io.perfeccionista.framework.pagefactory.pageobjects.elements.LeftMenu.LeftMenuItemBlock;
import io.perfeccionista.framework.value.string.StringValue;
import org.jetbrains.annotations.NotNull;

import static io.perfeccionista.framework.matcher.WebMultipleResultAssertions.haveNotNullResults;
import static io.perfeccionista.framework.pagefactory.elements.WebBlock.frame;
import static io.perfeccionista.framework.pagefactory.extractor.WebExtractors.element;
import static io.perfeccionista.framework.pagefactory.filter.WebFilterConditions.containsText;
import static io.perfeccionista.framework.pagefactory.filter.WebFilters.with;

@WebLocator(component = "LI", css = ".list-group-item", single = false)
@UseMappedWebBlock(LeftMenuItemBlock.class)
public interface LeftMenu extends WebList {

    default void select(@NotNull StringValue expectedValue) {
        this.filter(with(containsText(frame(LeftMenuItemBlock.class).item(), expectedValue)))
                .extractOne(element(frame(LeftMenuItemBlock.class).item()))
                .should(haveNotNullResults()).getValue()
                .click();
    }

    default void select(@NotNull String expectedText) {
        this.filter(with(containsText(frame(LeftMenuItemBlock.class).item(), expectedText)))
                .extractOne(element(frame(LeftMenuItemBlock.class).item()))
                .should(haveNotNullResults()).getValue()
                .click();
    }

    interface LeftMenuItemBlock extends WebBlock {

        @Name("ссылка")
        @WebLocator(tagName = "a")
        WebLink item();

    }

}
