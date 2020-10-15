package io.perfeccionista.framework.matcher.actions;

import io.perfeccionista.framework.matcher.PerfeccionistaMatcher;
import io.perfeccionista.framework.pagefactory.elements.methods.WebComponentAvailable;
import org.jetbrains.annotations.NotNull;

public interface WebComponentAvailableMatcher extends PerfeccionistaMatcher<WebComponentAvailable> {

    @Override
    void check(@NotNull WebComponentAvailable element);

}
