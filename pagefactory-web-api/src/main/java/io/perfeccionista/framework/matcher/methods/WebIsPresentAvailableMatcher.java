package io.perfeccionista.framework.matcher.methods;

import io.perfeccionista.framework.matcher.PerfeccionistaMatcher;
import io.perfeccionista.framework.pagefactory.elements.methods.WebIsPresentAvailable;
import org.jetbrains.annotations.NotNull;

public interface WebIsPresentAvailableMatcher extends PerfeccionistaMatcher<WebIsPresentAvailable> {

    @Override
    void check(@NotNull WebIsPresentAvailable element);
}
