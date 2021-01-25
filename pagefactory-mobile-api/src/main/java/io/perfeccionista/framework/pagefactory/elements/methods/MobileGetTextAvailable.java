package io.perfeccionista.framework.pagefactory.elements.methods;

import io.perfeccionista.framework.matcher.method.MobileGetTextAvailableMatcher;
import io.perfeccionista.framework.pagefactory.elements.actions.MobileMappedElementAction;
import io.perfeccionista.framework.pagefactory.elements.base.MobileChildElement;
import io.perfeccionista.framework.plugin.AssertMethodType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import static io.perfeccionista.framework.pagefactory.elements.ElementActionNames.GET_TEXT_METHOD;

public interface MobileGetTextAvailable extends MobileChildElement {

    @MobileMappedElementAction(GET_TEXT_METHOD)
    @Nullable String getText();

    @AssertMethodType
    MobileGetTextAvailable should(@NotNull MobileGetTextAvailableMatcher matcher);

}
