package io.perfeccionista.framework.pagefactory.elements.methods;

import io.perfeccionista.framework.matcher.actions.GetDimensionsAvailableMatcher;
import io.perfeccionista.framework.measurements.Dimensions;
import io.perfeccionista.framework.pagefactory.elements.base.WebChildElementBase;
import io.perfeccionista.framework.plugin.AssertMethodType;
import io.perfeccionista.framework.pagefactory.elements.actions.base.WebMappedElementAction;
import org.jetbrains.annotations.NotNull;

import static io.perfeccionista.framework.pagefactory.elements.actions.WebElementActionNames.GET_DIMENSIONS_METHOD;

public interface GetDimensionsAvailable extends WebChildElementBase {

    @WebMappedElementAction(GET_DIMENSIONS_METHOD)
    @NotNull Dimensions getDimensions(@NotNull String componentName);

    @AssertMethodType
    GetDimensionsAvailable should(@NotNull GetDimensionsAvailableMatcher matcher);

}