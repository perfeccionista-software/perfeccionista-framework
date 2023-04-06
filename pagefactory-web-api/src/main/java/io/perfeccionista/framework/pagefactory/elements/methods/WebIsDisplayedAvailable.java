package io.perfeccionista.framework.pagefactory.elements.methods;

import io.perfeccionista.framework.pagefactory.elements.base.WebChildElementBase;
import io.perfeccionista.framework.pagefactory.elements.actions.WebMappedElementAction;
import org.jetbrains.annotations.NotNull;

import static io.perfeccionista.framework.pagefactory.elements.ElementActionNames.IS_DISPLAYED_METHOD;

public interface WebIsDisplayedAvailable extends WebChildElementBase {

    @WebMappedElementAction(IS_DISPLAYED_METHOD)
    boolean isDisplayed();

    @WebMappedElementAction(IS_DISPLAYED_METHOD)
    boolean isDisplayed(@NotNull String componentName);

}
