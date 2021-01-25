package io.perfeccionista.framework.pagefactory.elements.methods;

import io.perfeccionista.framework.matcher.method.MobileElementStateAvailableMatcher;
import io.perfeccionista.framework.pagefactory.elements.actions.MobileMappedElementAction;
import io.perfeccionista.framework.pagefactory.elements.base.MobileChildElementBase;
import io.perfeccionista.framework.pagefactory.elements.states.base.MobileElementStateHolder;
import io.perfeccionista.framework.plugin.AssertMethodType;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;

import static io.perfeccionista.framework.pagefactory.elements.ElementActionNames.HAS_STATE_METHOD;

public interface MobileElementStateAvailable extends MobileChildElementBase {

    Optional<MobileElementStateHolder> getState(String stateName);

    @MobileMappedElementAction(HAS_STATE_METHOD)
    boolean hasState(@NotNull String stateName);

    @AssertMethodType
    MobileChildElementBase should(@NotNull MobileElementStateAvailableMatcher matcher);

}
