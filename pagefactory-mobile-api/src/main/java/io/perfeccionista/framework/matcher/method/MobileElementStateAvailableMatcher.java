package io.perfeccionista.framework.matcher.method;

import io.perfeccionista.framework.matcher.PerfeccionistaMatcher;
import io.perfeccionista.framework.pagefactory.elements.methods.MobileElementStateAvailable;
import org.jetbrains.annotations.NotNull;

public interface MobileElementStateAvailableMatcher extends PerfeccionistaMatcher<MobileElementStateAvailable> {

    @Override
    void check(@NotNull MobileElementStateAvailable element);

}
