package io.perfeccionista.framework.pagefactory.elements.methods;

import io.perfeccionista.framework.matcher.methods.WebIsPresentAvailableMatcher;
import io.perfeccionista.framework.pagefactory.elements.base.WebChildElementBase;
import io.perfeccionista.framework.plugin.AssertMethodType;
import io.perfeccionista.framework.pagefactory.elements.actions.WebMappedElementAction;
import org.jetbrains.annotations.NotNull;

import static io.perfeccionista.framework.pagefactory.elements.ElementActionNames.IS_PRESENT_METHOD;

public interface WebIsPresentAvailable extends WebChildElementBase {

    @WebMappedElementAction(IS_PRESENT_METHOD)
    boolean isPresent();

    @AssertMethodType
    WebIsPresentAvailable should(@NotNull WebIsPresentAvailableMatcher matcher);

}
