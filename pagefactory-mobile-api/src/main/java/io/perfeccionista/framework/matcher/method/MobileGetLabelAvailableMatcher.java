package io.perfeccionista.framework.matcher.method;

import io.perfeccionista.framework.matcher.PerfeccionistaMatcher;
import io.perfeccionista.framework.pagefactory.elements.methods.MobileGetLabelAvailable;
import org.jetbrains.annotations.NotNull;

public interface MobileGetLabelAvailableMatcher extends PerfeccionistaMatcher<MobileGetLabelAvailable> {

    @Override
    void check(@NotNull MobileGetLabelAvailable element);

}
