package io.perfeccionista.framework.matcher.method;

import io.perfeccionista.framework.matcher.PerfeccionistaMatcher;
import io.perfeccionista.framework.pagefactory.elements.methods.MobileIsSelectedAvailable;
import org.jetbrains.annotations.NotNull;

public interface MobileIsSelectedAvailableMatcher extends PerfeccionistaMatcher<MobileIsSelectedAvailable> {

    @Override
    void check(@NotNull MobileIsSelectedAvailable element);

}
