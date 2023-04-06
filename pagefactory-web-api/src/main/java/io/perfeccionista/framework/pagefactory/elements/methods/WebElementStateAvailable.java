package io.perfeccionista.framework.pagefactory.elements.methods;

import io.perfeccionista.framework.pagefactory.elements.actions.WebMappedElementAction;
import io.perfeccionista.framework.pagefactory.elements.base.WebChildElementBase;
import io.perfeccionista.framework.pagefactory.elements.states.base.WebElementStateHolder;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;

import static io.perfeccionista.framework.pagefactory.elements.ElementActionNames.HAS_STATE_METHOD;

public interface WebElementStateAvailable extends WebChildElementBase {

    Optional<WebElementStateHolder> getStateHolder(String stateName);

    @WebMappedElementAction(HAS_STATE_METHOD)
    boolean hasState(@NotNull String stateName);

}
