package io.perfeccionista.framework.matcher.methods;

import io.perfeccionista.framework.matcher.PerfeccionistaMatcher;
import io.perfeccionista.framework.pagefactory.elements.methods.WebGetScreenshotAvailable;
import org.jetbrains.annotations.NotNull;

public interface WebGetScreenshotAvailableMatcher extends PerfeccionistaMatcher<WebGetScreenshotAvailable> {

    @Override
    void check(@NotNull WebGetScreenshotAvailable element);

}
