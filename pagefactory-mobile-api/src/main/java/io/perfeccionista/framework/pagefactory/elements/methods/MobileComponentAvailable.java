package io.perfeccionista.framework.pagefactory.elements.methods;

import io.perfeccionista.framework.matcher.method.MobileComponentAvailableMatcher;
import io.perfeccionista.framework.pagefactory.elements.actions.MobileMappedElementAction;
import io.perfeccionista.framework.pagefactory.elements.base.MobileChildElementBase;
import io.perfeccionista.framework.plugin.AssertMethodType;
import org.jetbrains.annotations.NotNull;

import static io.perfeccionista.framework.pagefactory.elements.ElementActionNames.IS_COMPONENT_DISPLAYED_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.ElementActionNames.IS_COMPONENT_PRESENT_METHOD;

/**
 * TODO JavaDoc
 */
public interface MobileComponentAvailable extends MobileChildElementBase {

    @MobileMappedElementAction(IS_COMPONENT_PRESENT_METHOD)
    boolean isComponentPresent(@NotNull String componentName);

    @MobileMappedElementAction(IS_COMPONENT_DISPLAYED_METHOD)
    boolean isComponentDisplayed(@NotNull String componentName);

    @AssertMethodType
    MobileComponentAvailable should(@NotNull MobileComponentAvailableMatcher matcher);

}
