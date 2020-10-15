package io.perfeccionista.framework.matcher.actions;

import io.perfeccionista.framework.matcher.PerfeccionistaMatcher;
import io.perfeccionista.framework.pagefactory.elements.methods.GetLocationAvailable;
import org.jetbrains.annotations.NotNull;

public interface GetLocationAvailableMatcher extends PerfeccionistaMatcher<GetLocationAvailable> {

    @Override
    void check(@NotNull GetLocationAvailable element);

}
