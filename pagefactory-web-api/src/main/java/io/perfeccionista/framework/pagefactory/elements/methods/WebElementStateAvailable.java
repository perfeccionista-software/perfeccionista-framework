package io.perfeccionista.framework.pagefactory.elements.methods;

import io.perfeccionista.framework.matcher.methods.WebElementStateAvailableMatcher;
import io.perfeccionista.framework.pagefactory.elements.actions.WebMappedElementAction;
import io.perfeccionista.framework.pagefactory.elements.base.WebChildElementBase;
import io.perfeccionista.framework.pagefactory.elements.states.base.WebElementStateHolder;
import io.perfeccionista.framework.plugin.AssertMethodType;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;

import static io.perfeccionista.framework.pagefactory.elements.ElementActionNames.HAS_STATE_METHOD;

public interface WebElementStateAvailable extends WebChildElementBase {

    Optional<WebElementStateHolder> getState(String stateName);

    @WebMappedElementAction(HAS_STATE_METHOD)
    boolean hasState(@NotNull String stateName);

    @AssertMethodType
    WebChildElementBase should(@NotNull WebElementStateAvailableMatcher matcher);

}
