package io.perfeccionista.framework.pagefactory.elements.methods;

import io.perfeccionista.framework.matcher.method.MobileIsPresentAvailableMatcher;
import io.perfeccionista.framework.pagefactory.elements.actions.MobileMappedElementAction;
import io.perfeccionista.framework.pagefactory.elements.base.MobileChildElementBase;
import io.perfeccionista.framework.plugin.AssertMethodType;
import org.jetbrains.annotations.NotNull;

import static io.perfeccionista.framework.pagefactory.elements.ElementActionNames.IS_PRESENT_METHOD;

public interface MobileIsPresentAvailable extends MobileChildElementBase {

    @MobileMappedElementAction(IS_PRESENT_METHOD)
    boolean isPresent();

    @AssertMethodType
    MobileIsPresentAvailable should(@NotNull MobileIsPresentAvailableMatcher matcher);

}
