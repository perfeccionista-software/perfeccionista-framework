package io.perfeccionista.framework.matcher.method;

import io.perfeccionista.framework.matcher.PerfeccionistaMatcher;
import io.perfeccionista.framework.pagefactory.elements.methods.MobileIsDisplayedAvailable;
import org.jetbrains.annotations.NotNull;

public interface MobileIsDisplayedAvailableMatcher extends PerfeccionistaMatcher<MobileIsDisplayedAvailable> {

    @Override
    void check(@NotNull MobileIsDisplayedAvailable element);

}
