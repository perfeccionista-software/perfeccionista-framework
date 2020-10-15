package io.perfeccionista.framework.pagefactory.elements.methods;

import io.perfeccionista.framework.matcher.actions.IsSelectedAvailableMatcher;
import io.perfeccionista.framework.pagefactory.elements.base.WebChildElementBase;
import io.perfeccionista.framework.plugin.AssertMethodType;
import io.perfeccionista.framework.pagefactory.elements.actions.base.WebMappedElementAction;
import org.jetbrains.annotations.NotNull;

import static io.perfeccionista.framework.pagefactory.elements.actions.WebElementActionNames.IS_SELECTED_METHOD;

public interface IsSelectedAvailable extends WebChildElementBase {

    @WebMappedElementAction(IS_SELECTED_METHOD)
    boolean isSelected();

    @AssertMethodType
    IsSelectedAvailable should(@NotNull IsSelectedAvailableMatcher matcher);

}
