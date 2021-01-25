package io.perfeccionista.framework.pagefactory.elements.methods;

import io.perfeccionista.framework.matcher.method.MobileIsSelectedAvailableMatcher;
import io.perfeccionista.framework.pagefactory.elements.actions.MobileMappedElementAction;
import io.perfeccionista.framework.pagefactory.elements.base.MobileChildElement;
import io.perfeccionista.framework.plugin.AssertMethodType;
import org.jetbrains.annotations.NotNull;

import static io.perfeccionista.framework.pagefactory.elements.ElementActionNames.IS_SELECTED_METHOD;

public interface MobileIsSelectedAvailable extends MobileChildElement {

    @MobileMappedElementAction(IS_SELECTED_METHOD)
    boolean isSelected();

    @AssertMethodType
    MobileIsSelectedAvailable should(@NotNull MobileIsSelectedAvailableMatcher matcher);

}
