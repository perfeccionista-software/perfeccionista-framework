package io.perfeccionista.framework.matcher.method;

import io.perfeccionista.framework.matcher.PerfeccionistaMatcher;
import io.perfeccionista.framework.pagefactory.elements.methods.MobileComponentAvailable;
import org.jetbrains.annotations.NotNull;

public interface MobileComponentAvailableMatcher extends PerfeccionistaMatcher<MobileComponentAvailable> {

    @Override
    void check(@NotNull MobileComponentAvailable element);

}
