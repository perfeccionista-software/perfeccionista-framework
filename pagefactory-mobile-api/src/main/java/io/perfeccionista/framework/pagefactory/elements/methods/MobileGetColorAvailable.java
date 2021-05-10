package io.perfeccionista.framework.pagefactory.elements.methods;

import io.perfeccionista.framework.color.Color;
import io.perfeccionista.framework.matcher.method.MobileGetColorAvailableMatcher;
import io.perfeccionista.framework.pagefactory.elements.actions.MobileMappedElementAction;
import io.perfeccionista.framework.pagefactory.elements.base.MobileChildElementBase;
import io.perfeccionista.framework.plugin.AssertMethodType;
import org.jetbrains.annotations.NotNull;

import static io.perfeccionista.framework.pagefactory.elements.ElementActionNames.GET_COLOR_METHOD;

public interface MobileGetColorAvailable extends MobileChildElementBase {

    @MobileMappedElementAction(GET_COLOR_METHOD)
    @NotNull Color getColor(@NotNull String property);

    @MobileMappedElementAction(GET_COLOR_METHOD)
    @NotNull Color getColor(@NotNull String componentName, @NotNull String property);

    @AssertMethodType
    MobileGetColorAvailable should(@NotNull MobileGetColorAvailableMatcher matcher);

}
