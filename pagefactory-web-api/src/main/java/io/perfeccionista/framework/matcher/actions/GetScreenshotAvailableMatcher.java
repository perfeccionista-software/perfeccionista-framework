package io.perfeccionista.framework.matcher.actions;

import io.perfeccionista.framework.matcher.PerfeccionistaMatcher;
import io.perfeccionista.framework.pagefactory.elements.methods.GetScreenshotAvailable;
import org.jetbrains.annotations.NotNull;

public interface GetScreenshotAvailableMatcher extends PerfeccionistaMatcher<GetScreenshotAvailable> {

    @Override
    void check(@NotNull GetScreenshotAvailable element);

}
