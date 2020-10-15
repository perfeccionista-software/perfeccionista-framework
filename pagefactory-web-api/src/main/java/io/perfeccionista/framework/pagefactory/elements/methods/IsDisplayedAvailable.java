package io.perfeccionista.framework.pagefactory.elements.methods;

import io.perfeccionista.framework.matcher.actions.IsDisplayedAvailableMatcher;
import io.perfeccionista.framework.pagefactory.elements.base.WebChildElementBase;
import io.perfeccionista.framework.plugin.AssertMethodType;
import io.perfeccionista.framework.pagefactory.elements.actions.base.WebMappedElementAction;
import org.jetbrains.annotations.NotNull;

import static io.perfeccionista.framework.pagefactory.elements.actions.WebElementActionNames.IS_DISPLAYED_METHOD;

public interface IsDisplayedAvailable extends WebChildElementBase {

    @WebMappedElementAction(IS_DISPLAYED_METHOD)
    boolean isDisplayed();

    @AssertMethodType
    IsDisplayedAvailable should(@NotNull IsDisplayedAvailableMatcher matcher);

}
