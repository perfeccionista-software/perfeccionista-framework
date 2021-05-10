package io.perfeccionista.framework.pagefactory.elements.methods;

import io.perfeccionista.framework.matcher.method.MobileGetLabelAvailableMatcher;
import io.perfeccionista.framework.pagefactory.elements.actions.MobileMappedElementAction;
import io.perfeccionista.framework.pagefactory.elements.base.MobileChildElement;
import io.perfeccionista.framework.plugin.AssertMethodType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import static io.perfeccionista.framework.pagefactory.elements.ElementActionNames.GET_LABEL_METHOD;

public interface MobileGetLabelAvailable extends MobileChildElement {

    @MobileMappedElementAction(GET_LABEL_METHOD)
    @Nullable String getLabel();

    @AssertMethodType
    MobileGetLabelAvailable should(@NotNull MobileGetLabelAvailableMatcher matcher);

}
