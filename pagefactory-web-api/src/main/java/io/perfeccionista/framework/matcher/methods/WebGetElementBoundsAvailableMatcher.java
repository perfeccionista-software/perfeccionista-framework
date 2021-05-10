package io.perfeccionista.framework.matcher.methods;

import io.perfeccionista.framework.matcher.PerfeccionistaMatcher;
import io.perfeccionista.framework.pagefactory.elements.methods.WebGetElementBoundsAvailable;
import org.jetbrains.annotations.NotNull;

public interface WebGetElementBoundsAvailableMatcher extends PerfeccionistaMatcher<WebGetElementBoundsAvailable> {

    @Override
    void check(@NotNull WebGetElementBoundsAvailable element);

}
