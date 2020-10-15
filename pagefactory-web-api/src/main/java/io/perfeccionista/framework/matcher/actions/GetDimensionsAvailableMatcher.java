package io.perfeccionista.framework.matcher.actions;


import io.perfeccionista.framework.matcher.PerfeccionistaMatcher;
import io.perfeccionista.framework.pagefactory.elements.methods.GetDimensionsAvailable;
import org.jetbrains.annotations.NotNull;

public interface GetDimensionsAvailableMatcher extends PerfeccionistaMatcher<GetDimensionsAvailable> {

    @Override
    void check(@NotNull GetDimensionsAvailable element);

}
