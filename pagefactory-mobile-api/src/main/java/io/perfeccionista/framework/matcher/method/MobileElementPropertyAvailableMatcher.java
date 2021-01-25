package io.perfeccionista.framework.matcher.method;

import io.perfeccionista.framework.matcher.PerfeccionistaMatcher;
import io.perfeccionista.framework.pagefactory.elements.methods.MobileElementPropertyAvailable;
import org.jetbrains.annotations.NotNull;

public interface MobileElementPropertyAvailableMatcher extends PerfeccionistaMatcher<MobileElementPropertyAvailable> {

    @Override
    void check(@NotNull MobileElementPropertyAvailable element);

}
