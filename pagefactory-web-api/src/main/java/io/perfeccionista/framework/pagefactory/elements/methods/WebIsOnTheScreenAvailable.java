package io.perfeccionista.framework.pagefactory.elements.methods;

import io.perfeccionista.framework.matcher.methods.WebIsOnTheScreenAvailableMatcher;
import io.perfeccionista.framework.pagefactory.elements.actions.WebMappedElementAction;
import io.perfeccionista.framework.pagefactory.elements.base.WebChildElementBase;
import io.perfeccionista.framework.plugin.AssertMethodType;
import org.jetbrains.annotations.NotNull;

import static io.perfeccionista.framework.pagefactory.elements.ElementActionNames.IS_ON_THE_SCREEN_METHOD;

public interface WebIsOnTheScreenAvailable extends WebChildElementBase {

    @WebMappedElementAction(IS_ON_THE_SCREEN_METHOD)
    boolean isOnTheScreen();

    @AssertMethodType
    WebIsOnTheScreenAvailable should(@NotNull WebIsOnTheScreenAvailableMatcher matcher);

}
