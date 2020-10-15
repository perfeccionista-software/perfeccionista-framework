package io.perfeccionista.framework.matcher.actions;

import io.perfeccionista.framework.matcher.PerfeccionistaMatcher;
import io.perfeccionista.framework.pagefactory.elements.methods.GetLabelAvailable;
import org.jetbrains.annotations.NotNull;

public interface GetLabelAvailableMatcher extends PerfeccionistaMatcher<GetLabelAvailable> {

    @Override
    void check(@NotNull GetLabelAvailable element);

}
