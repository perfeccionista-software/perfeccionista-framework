package io.perfeccionista.framework.pagefactory.elements.methods;

import io.perfeccionista.framework.pagefactory.elements.base.WebChildElementBase;
import io.perfeccionista.framework.pagefactory.elements.actions.WebMappedElementAction;
import org.jetbrains.annotations.NotNull;

import static io.perfeccionista.framework.pagefactory.elements.ElementActionNames.IS_IN_FOCUS_METHOD;

public interface WebIsInFocusAvailable extends WebChildElementBase {

    @WebMappedElementAction(IS_IN_FOCUS_METHOD)
    boolean isInFocus();

    @WebMappedElementAction(IS_IN_FOCUS_METHOD)
    boolean isInFocus(@NotNull String componentName);

}
