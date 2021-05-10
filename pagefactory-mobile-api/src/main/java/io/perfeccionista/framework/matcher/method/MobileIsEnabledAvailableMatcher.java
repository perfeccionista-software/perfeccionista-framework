package io.perfeccionista.framework.matcher.method;

import io.perfeccionista.framework.matcher.PerfeccionistaMatcher;
import io.perfeccionista.framework.pagefactory.elements.methods.MobileIsEnabledAvailable;
import org.jetbrains.annotations.NotNull;

public interface MobileIsEnabledAvailableMatcher extends PerfeccionistaMatcher<MobileIsEnabledAvailable> {

    @Override
    void check(@NotNull MobileIsEnabledAvailable element);

}
