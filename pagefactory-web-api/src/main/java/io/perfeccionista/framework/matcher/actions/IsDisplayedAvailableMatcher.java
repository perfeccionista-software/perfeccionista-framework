package io.perfeccionista.framework.matcher.actions;

import io.perfeccionista.framework.matcher.PerfeccionistaMatcher;
import io.perfeccionista.framework.pagefactory.elements.methods.IsDisplayedAvailable;
import org.jetbrains.annotations.NotNull;

public interface IsDisplayedAvailableMatcher extends PerfeccionistaMatcher<IsDisplayedAvailable> {

    @Override
    void check(@NotNull IsDisplayedAvailable element);

}
