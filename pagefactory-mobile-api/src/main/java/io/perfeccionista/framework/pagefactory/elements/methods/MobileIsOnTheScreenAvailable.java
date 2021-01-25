package io.perfeccionista.framework.pagefactory.elements.methods;

import io.perfeccionista.framework.matcher.method.MobileIsOnTheScreenAvailableMatcher;
import io.perfeccionista.framework.pagefactory.elements.actions.MobileMappedElementAction;
import io.perfeccionista.framework.pagefactory.elements.base.MobileChildElementBase;
import io.perfeccionista.framework.plugin.AssertMethodType;
import org.jetbrains.annotations.NotNull;

import static io.perfeccionista.framework.pagefactory.elements.ElementActionNames.IS_ON_THE_SCREEN_METHOD;

public interface MobileIsOnTheScreenAvailable extends MobileChildElementBase {

    @MobileMappedElementAction(IS_ON_THE_SCREEN_METHOD)
    boolean isOnTheScreen();

    @AssertMethodType
    MobileIsOnTheScreenAvailable should(@NotNull MobileIsOnTheScreenAvailableMatcher matcher);

}
