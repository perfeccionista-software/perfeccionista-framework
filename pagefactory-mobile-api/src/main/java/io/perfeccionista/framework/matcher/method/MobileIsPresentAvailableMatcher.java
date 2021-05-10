package io.perfeccionista.framework.matcher.method;

import io.perfeccionista.framework.matcher.PerfeccionistaMatcher;
import io.perfeccionista.framework.pagefactory.elements.methods.MobileIsPresentAvailable;
import org.jetbrains.annotations.NotNull;

public interface MobileIsPresentAvailableMatcher extends PerfeccionistaMatcher<MobileIsPresentAvailable> {

    @Override
    void check(@NotNull MobileIsPresentAvailable element);

}
