package io.perfeccionista.framework.matcher.actions;

import io.perfeccionista.framework.matcher.PerfeccionistaMatcher;
import io.perfeccionista.framework.pagefactory.elements.methods.IsInFocusAvailable;
import org.jetbrains.annotations.NotNull;

public interface IsInFocusAvailableMatcher extends PerfeccionistaMatcher<IsInFocusAvailable> {

    @Override
    void check(@NotNull IsInFocusAvailable element);

}
