package io.perfeccionista.framework.matcher.actions;

import io.perfeccionista.framework.matcher.PerfeccionistaMatcher;
import io.perfeccionista.framework.pagefactory.elements.methods.GetTextAvailable;
import org.jetbrains.annotations.NotNull;

public interface GetTextAvailableMatcher extends PerfeccionistaMatcher<GetTextAvailable> {

    @Override
    void check(@NotNull GetTextAvailable element);

}
