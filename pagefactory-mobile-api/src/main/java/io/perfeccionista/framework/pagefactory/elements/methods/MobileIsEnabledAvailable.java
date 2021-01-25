package io.perfeccionista.framework.pagefactory.elements.methods;

import io.perfeccionista.framework.matcher.method.MobileIsEnabledAvailableMatcher;
import io.perfeccionista.framework.pagefactory.elements.actions.MobileMappedElementAction;
import io.perfeccionista.framework.pagefactory.elements.base.MobileChildElement;
import io.perfeccionista.framework.plugin.AssertMethodType;
import org.jetbrains.annotations.NotNull;

import static io.perfeccionista.framework.pagefactory.elements.ElementActionNames.IS_ENABLED_METHOD;

public interface MobileIsEnabledAvailable extends MobileChildElement {

    @MobileMappedElementAction(IS_ENABLED_METHOD)
    boolean isEnabled();

    @AssertMethodType
    MobileIsEnabledAvailable should(@NotNull MobileIsEnabledAvailableMatcher matcher);

}
