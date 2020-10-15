package io.perfeccionista.framework.matcher.actions;


import io.perfeccionista.framework.matcher.PerfeccionistaMatcher;
import io.perfeccionista.framework.pagefactory.elements.methods.GetColorAvailable;
import org.jetbrains.annotations.NotNull;

public interface GetColorAvailableMatcher extends PerfeccionistaMatcher<GetColorAvailable> {

    @Override
    void check(@NotNull GetColorAvailable element);

}
