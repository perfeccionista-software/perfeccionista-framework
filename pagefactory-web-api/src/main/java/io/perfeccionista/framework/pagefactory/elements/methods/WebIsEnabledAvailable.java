package io.perfeccionista.framework.pagefactory.elements.methods;

import io.perfeccionista.framework.matcher.methods.WebIsEnabledAvailableMatcher;
import io.perfeccionista.framework.pagefactory.elements.base.WebChildElement;
import io.perfeccionista.framework.plugin.AssertMethodType;
import io.perfeccionista.framework.pagefactory.elements.actions.WebMappedElementAction;
import org.jetbrains.annotations.NotNull;

import static io.perfeccionista.framework.pagefactory.elements.ElementActionNames.IS_ENABLED_METHOD;

public interface WebIsEnabledAvailable extends WebChildElement {

    @WebMappedElementAction(IS_ENABLED_METHOD)
    boolean isEnabled();

    @AssertMethodType
    WebIsEnabledAvailable should(@NotNull WebIsEnabledAvailableMatcher matcher);

}
