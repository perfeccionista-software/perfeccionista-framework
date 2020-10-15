package io.perfeccionista.framework.matcher.actions;

import io.perfeccionista.framework.matcher.PerfeccionistaMatcher;
import io.perfeccionista.framework.pagefactory.elements.methods.IsPresentAvailable;
import org.jetbrains.annotations.NotNull;

public interface IsPresentAvailableMatcher extends PerfeccionistaMatcher<IsPresentAvailable> {

    @Override
    void check(@NotNull IsPresentAvailable element);
}
