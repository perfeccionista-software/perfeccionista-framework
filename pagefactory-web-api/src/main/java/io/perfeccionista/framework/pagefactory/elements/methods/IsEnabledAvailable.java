package io.perfeccionista.framework.pagefactory.elements.methods;

import io.perfeccionista.framework.matcher.actions.IsEnabledAvailableMatcher;
import io.perfeccionista.framework.pagefactory.elements.base.WebChildElement;
import io.perfeccionista.framework.plugin.AssertMethodType;
import io.perfeccionista.framework.pagefactory.elements.actions.base.WebMappedElementAction;
import org.jetbrains.annotations.NotNull;

import static io.perfeccionista.framework.pagefactory.elements.actions.WebElementActionNames.IS_ENABLED_METHOD;

public interface IsEnabledAvailable extends WebChildElement {

    @WebMappedElementAction(IS_ENABLED_METHOD)
    boolean isEnabled();

    @AssertMethodType
    IsEnabledAvailable should(@NotNull IsEnabledAvailableMatcher matcher);

}
