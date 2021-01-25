package io.perfeccionista.framework.matcher.method;

import io.perfeccionista.framework.matcher.PerfeccionistaMatcher;
import io.perfeccionista.framework.pagefactory.elements.methods.MobileDropDownAvailable;
import org.jetbrains.annotations.NotNull;

public interface MobileIsOpenAvailableMatcher extends PerfeccionistaMatcher<MobileDropDownAvailable> {

    @Override
    void check(@NotNull MobileDropDownAvailable element);
}
