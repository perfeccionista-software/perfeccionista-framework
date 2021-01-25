package io.perfeccionista.framework.matcher.method;

import io.perfeccionista.framework.matcher.PerfeccionistaMatcher;
import io.perfeccionista.framework.pagefactory.elements.methods.MobileGetTextAvailable;
import org.jetbrains.annotations.NotNull;

public interface MobileGetTextAvailableMatcher extends PerfeccionistaMatcher<MobileGetTextAvailable> {

    @Override
    void check(@NotNull MobileGetTextAvailable element);

}
