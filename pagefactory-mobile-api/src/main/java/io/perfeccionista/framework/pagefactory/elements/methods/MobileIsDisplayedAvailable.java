package io.perfeccionista.framework.pagefactory.elements.methods;

import io.perfeccionista.framework.matcher.method.MobileIsDisplayedAvailableMatcher;
import io.perfeccionista.framework.pagefactory.elements.actions.MobileMappedElementAction;
import io.perfeccionista.framework.pagefactory.elements.base.MobileChildElementBase;
import io.perfeccionista.framework.plugin.AssertMethodType;
import org.jetbrains.annotations.NotNull;

import static io.perfeccionista.framework.pagefactory.elements.ElementActionNames.IS_DISPLAYED_METHOD;

public interface MobileIsDisplayedAvailable extends MobileChildElementBase {

    @MobileMappedElementAction(IS_DISPLAYED_METHOD)
    boolean isDisplayed();

    @AssertMethodType
    MobileIsDisplayedAvailable should(@NotNull MobileIsDisplayedAvailableMatcher matcher);

}
