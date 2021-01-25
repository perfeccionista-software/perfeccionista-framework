package io.perfeccionista.framework.matcher.method;

import io.perfeccionista.framework.matcher.PerfeccionistaMatcher;
import io.perfeccionista.framework.pagefactory.elements.methods.MobileGetElementBoundsAvailable;
import org.jetbrains.annotations.NotNull;

public interface MobileGetElementBoundsAvailableMatcher extends PerfeccionistaMatcher<MobileGetElementBoundsAvailable> {

    @Override
    void check(@NotNull MobileGetElementBoundsAvailable element);

}
