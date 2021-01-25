package io.perfeccionista.framework.matcher.method;


import io.perfeccionista.framework.matcher.PerfeccionistaMatcher;
import io.perfeccionista.framework.pagefactory.elements.methods.MobileGetColorAvailable;
import org.jetbrains.annotations.NotNull;

public interface MobileGetColorAvailableMatcher extends PerfeccionistaMatcher<MobileGetColorAvailable> {

    @Override
    void check(@NotNull MobileGetColorAvailable element);

}
