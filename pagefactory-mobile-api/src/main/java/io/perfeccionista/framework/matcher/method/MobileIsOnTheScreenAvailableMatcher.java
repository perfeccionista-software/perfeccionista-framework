package io.perfeccionista.framework.matcher.method;

import io.perfeccionista.framework.matcher.PerfeccionistaMatcher;
import io.perfeccionista.framework.pagefactory.elements.methods.MobileIsOnTheScreenAvailable;
import org.jetbrains.annotations.NotNull;

public interface MobileIsOnTheScreenAvailableMatcher extends PerfeccionistaMatcher<MobileIsOnTheScreenAvailable> {

    @Override
    void check(@NotNull MobileIsOnTheScreenAvailable element);

}
