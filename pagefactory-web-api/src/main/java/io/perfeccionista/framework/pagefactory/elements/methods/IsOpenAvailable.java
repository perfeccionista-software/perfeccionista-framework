package io.perfeccionista.framework.pagefactory.elements.methods;

import io.perfeccionista.framework.matcher.actions.IsOpenAvailableMatcher;
import io.perfeccionista.framework.pagefactory.elements.base.WebChildElement;
import io.perfeccionista.framework.plugin.AssertMethodType;
import io.perfeccionista.framework.pagefactory.elements.actions.base.WebMappedElementAction;
import org.jetbrains.annotations.NotNull;

import static io.perfeccionista.framework.pagefactory.elements.actions.WebElementActionNames.IS_OPEN_METHOD;

public interface IsOpenAvailable extends WebChildElement {

    @WebMappedElementAction(IS_OPEN_METHOD)
    boolean isOpen();

    @AssertMethodType
    IsOpenAvailable should(@NotNull IsOpenAvailableMatcher matcher);

}
