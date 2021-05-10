package io.perfeccionista.framework.matcher.method;

import io.perfeccionista.framework.matcher.PerfeccionistaMatcher;
import io.perfeccionista.framework.pagefactory.elements.methods.MobileIsInFocusAvailable;
import org.jetbrains.annotations.NotNull;

public interface MobileIsInFocusAvailableMatcher extends PerfeccionistaMatcher<MobileIsInFocusAvailable> {

    @Override
    void check(@NotNull MobileIsInFocusAvailable element);

}

