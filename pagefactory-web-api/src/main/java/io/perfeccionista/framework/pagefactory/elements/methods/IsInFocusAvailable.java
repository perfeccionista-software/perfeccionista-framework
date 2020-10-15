package io.perfeccionista.framework.pagefactory.elements.methods;

import io.perfeccionista.framework.matcher.actions.IsInFocusAvailableMatcher;
import io.perfeccionista.framework.pagefactory.elements.base.WebChildElementBase;
import io.perfeccionista.framework.plugin.AssertMethodType;
import io.perfeccionista.framework.pagefactory.elements.actions.base.WebMappedElementAction;
import org.jetbrains.annotations.NotNull;

import static io.perfeccionista.framework.pagefactory.elements.actions.WebElementActionNames.IS_IN_FOCUS_METHOD;

public interface IsInFocusAvailable extends WebChildElementBase {

    @WebMappedElementAction(IS_IN_FOCUS_METHOD)
    boolean isInFocus();

    @AssertMethodType
    IsInFocusAvailable should(@NotNull IsInFocusAvailableMatcher matcher);

}
