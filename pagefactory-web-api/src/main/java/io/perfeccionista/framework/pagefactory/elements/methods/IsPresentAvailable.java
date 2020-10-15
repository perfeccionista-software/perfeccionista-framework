package io.perfeccionista.framework.pagefactory.elements.methods;

import io.perfeccionista.framework.matcher.actions.IsPresentAvailableMatcher;
import io.perfeccionista.framework.pagefactory.elements.base.WebChildElementBase;
import io.perfeccionista.framework.plugin.AssertMethodType;
import io.perfeccionista.framework.pagefactory.elements.actions.base.WebMappedElementAction;
import org.jetbrains.annotations.NotNull;

import static io.perfeccionista.framework.pagefactory.elements.actions.WebElementActionNames.IS_PRESENT_METHOD;

public interface IsPresentAvailable extends WebChildElementBase {

    @WebMappedElementAction(IS_PRESENT_METHOD)
    boolean isPresent();

    @AssertMethodType
    IsPresentAvailable should(@NotNull IsPresentAvailableMatcher matcher);

}
