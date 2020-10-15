package io.perfeccionista.framework.pagefactory.elements.methods;

import io.perfeccionista.framework.matcher.actions.IsOnTheScreenAvailableMatcher;
import io.perfeccionista.framework.pagefactory.elements.actions.base.WebMappedElementAction;
import io.perfeccionista.framework.pagefactory.elements.base.WebChildElementBase;
import io.perfeccionista.framework.plugin.AssertMethodType;
import org.jetbrains.annotations.NotNull;

import static io.perfeccionista.framework.pagefactory.elements.actions.WebElementActionNames.IS_ON_THE_SCREEN_METHOD;

public interface IsOnTheScreenAvailable extends WebChildElementBase {

    @WebMappedElementAction(IS_ON_THE_SCREEN_METHOD)
    boolean isOnTheScreen();

    @AssertMethodType
    IsOnTheScreenAvailable should(@NotNull IsOnTheScreenAvailableMatcher matcher);

}
