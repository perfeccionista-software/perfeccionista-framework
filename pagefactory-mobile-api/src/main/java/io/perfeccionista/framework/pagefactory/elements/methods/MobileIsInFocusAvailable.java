package io.perfeccionista.framework.pagefactory.elements.methods;

import io.perfeccionista.framework.matcher.method.MobileIsInFocusAvailableMatcher;
import io.perfeccionista.framework.pagefactory.elements.actions.MobileMappedElementAction;
import io.perfeccionista.framework.pagefactory.elements.base.MobileChildElementBase;
import io.perfeccionista.framework.plugin.AssertMethodType;
import org.jetbrains.annotations.NotNull;

import static io.perfeccionista.framework.pagefactory.elements.ElementActionNames.IS_IN_FOCUS_METHOD;

public interface MobileIsInFocusAvailable extends MobileChildElementBase {

    @MobileMappedElementAction(IS_IN_FOCUS_METHOD)
    boolean isInFocus();

    @AssertMethodType
    MobileIsInFocusAvailable should(@NotNull MobileIsInFocusAvailableMatcher matcher);

}
