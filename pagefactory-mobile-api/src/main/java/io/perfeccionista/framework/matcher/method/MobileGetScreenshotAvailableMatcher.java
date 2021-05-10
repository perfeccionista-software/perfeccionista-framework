package io.perfeccionista.framework.matcher.method;

import io.perfeccionista.framework.matcher.PerfeccionistaMatcher;
import io.perfeccionista.framework.pagefactory.elements.methods.MobileGetScreenshotAvailable;
import org.jetbrains.annotations.NotNull;

public interface MobileGetScreenshotAvailableMatcher extends PerfeccionistaMatcher<MobileGetScreenshotAvailable> {

    @Override
    void check(@NotNull MobileGetScreenshotAvailable element);

}
