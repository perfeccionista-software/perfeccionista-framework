package io.perfeccionista.framework.pagefactory.elements.methods;

import io.perfeccionista.framework.matcher.actions.GetColorAvailableMatcher;
import io.perfeccionista.framework.pagefactory.elements.base.WebChildElementBase;
import io.perfeccionista.framework.plugin.AssertMethodType;
import io.perfeccionista.framework.pagefactory.elements.actions.base.WebMappedElementAction;
import io.perfeccionista.framework.color.Color;
import org.jetbrains.annotations.NotNull;

import static io.perfeccionista.framework.pagefactory.elements.actions.WebElementActionNames.GET_COLOR_METHOD;

public interface GetColorAvailable extends WebChildElementBase {

    @WebMappedElementAction(GET_COLOR_METHOD)
    @NotNull Color getColor(@NotNull String componentName, @NotNull String cssProperty);

    @AssertMethodType
    GetColorAvailable should(@NotNull GetColorAvailableMatcher matcher);

}
