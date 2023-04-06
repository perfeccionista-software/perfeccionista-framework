package io.perfeccionista.framework.pagefactory.elements.methods;

import io.perfeccionista.framework.pagefactory.elements.base.WebChildElement;
import io.perfeccionista.framework.pagefactory.elements.actions.WebMappedElementAction;
import org.jetbrains.annotations.NotNull;

import static io.perfeccionista.framework.pagefactory.elements.ElementActionNames.IS_SELECTED_METHOD;

public interface WebIsSelectedAvailable extends WebChildElement {

    @WebMappedElementAction(IS_SELECTED_METHOD)
    boolean isSelected();

    @WebMappedElementAction(IS_SELECTED_METHOD)
    boolean isSelected(@NotNull String componentName);

}
