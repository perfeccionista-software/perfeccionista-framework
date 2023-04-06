package io.perfeccionista.framework.pagefactory.elements.methods;

import io.perfeccionista.framework.pagefactory.elements.base.WebChildElementBase;
import io.perfeccionista.framework.pagefactory.elements.actions.WebMappedElementAction;
import io.perfeccionista.framework.color.Color;
import org.jetbrains.annotations.NotNull;

import static io.perfeccionista.framework.pagefactory.elements.ElementActionNames.GET_COLOR_METHOD;

public interface WebGetColorAvailable extends WebChildElementBase {

    @WebMappedElementAction(GET_COLOR_METHOD)
    @NotNull Color getColor(@NotNull String cssProperty);

    @WebMappedElementAction(GET_COLOR_METHOD)
    @NotNull Color getColor(@NotNull String componentName, @NotNull String cssProperty);

}
