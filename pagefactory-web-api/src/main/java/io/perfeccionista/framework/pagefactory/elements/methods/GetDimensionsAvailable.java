package io.perfeccionista.framework.pagefactory.elements.methods;

import io.perfeccionista.framework.pagefactory.elements.base.WebChildElement;
import io.perfeccionista.framework.pagefactory.elements.base.WebLocatorChainAvailable;
import io.perfeccionista.framework.plugin.AssertMethodType;
import io.perfeccionista.framework.pagefactory.elements.actions.MappedElementAction;
import org.jetbrains.annotations.NotNull;

import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.GET_DIMENSIONS_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.SHOULD_HAVE_DIMENSIONS_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.SHOULD_NOT_HAVE_DIMENSIONS_METHOD;

public interface GetDimensionsAvailable extends WebLocatorChainAvailable {

    @MappedElementAction(GET_DIMENSIONS_METHOD)
    @NotNull Dimensions getDimensions(@NotNull String componentName);

    @AssertMethodType
    @MappedElementAction(SHOULD_HAVE_DIMENSIONS_METHOD)
    WebChildElement componentShouldHaveDimensions(@NotNull String componentName, @NotNull Dimensions expectedDimensions);

    @AssertMethodType
    @MappedElementAction(SHOULD_NOT_HAVE_DIMENSIONS_METHOD)
    WebChildElement componentShouldNotHaveDimensions(@NotNull String componentName, @NotNull Dimensions expectedDimensions);

}