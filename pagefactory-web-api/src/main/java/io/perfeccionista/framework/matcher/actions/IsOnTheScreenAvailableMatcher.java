package io.perfeccionista.framework.matcher.actions;

import io.perfeccionista.framework.matcher.PerfeccionistaMatcher;
import io.perfeccionista.framework.pagefactory.elements.methods.IsOnTheScreenAvailable;
import org.jetbrains.annotations.NotNull;

public interface IsOnTheScreenAvailableMatcher extends PerfeccionistaMatcher<IsOnTheScreenAvailable> {

    @Override
    void check(@NotNull IsOnTheScreenAvailable element);

}
