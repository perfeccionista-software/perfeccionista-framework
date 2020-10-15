package io.perfeccionista.framework.matcher.actions;

import io.perfeccionista.framework.matcher.PerfeccionistaMatcher;
import io.perfeccionista.framework.pagefactory.elements.methods.IsOpenAvailable;
import org.jetbrains.annotations.NotNull;

public interface IsOpenAvailableMatcher extends PerfeccionistaMatcher<IsOpenAvailable> {

    @Override
    void check(@NotNull IsOpenAvailable element);
}
