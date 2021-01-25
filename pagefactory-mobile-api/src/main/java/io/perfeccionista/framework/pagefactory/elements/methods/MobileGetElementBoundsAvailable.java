package io.perfeccionista.framework.pagefactory.elements.methods;

import io.perfeccionista.framework.matcher.method.MobileGetElementBoundsAvailableMatcher;
import io.perfeccionista.framework.pagefactory.elements.ElementBounds;
import io.perfeccionista.framework.pagefactory.elements.actions.MobileMappedElementAction;
import io.perfeccionista.framework.pagefactory.elements.base.MobileChildElementBase;
import io.perfeccionista.framework.plugin.AssertMethodType;
import org.jetbrains.annotations.NotNull;

import static io.perfeccionista.framework.pagefactory.elements.ElementActionNames.GET_ELEMENT_BOUNDS_METHOD;

public interface MobileGetElementBoundsAvailable extends MobileChildElementBase {

    @MobileMappedElementAction(GET_ELEMENT_BOUNDS_METHOD)
    @NotNull ElementBounds getElementBounds();

    @MobileMappedElementAction(GET_ELEMENT_BOUNDS_METHOD)
    @NotNull ElementBounds getElementBounds(@NotNull String componentName);

    @AssertMethodType
    MobileGetElementBoundsAvailable should(@NotNull MobileGetElementBoundsAvailableMatcher matcher);

}

