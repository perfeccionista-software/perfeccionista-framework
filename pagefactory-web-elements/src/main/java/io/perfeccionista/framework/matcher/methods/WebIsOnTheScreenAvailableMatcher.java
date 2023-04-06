package io.perfeccionista.framework.matcher.methods;

import io.perfeccionista.framework.matcher.PerfeccionistaMatcher;
import io.perfeccionista.framework.pagefactory.elements.methods.WebIsOnTheScreenAvailable;
import org.jetbrains.annotations.NotNull;

public interface WebIsOnTheScreenAvailableMatcher extends PerfeccionistaMatcher<WebIsOnTheScreenAvailable> {

    @Override
    void check(@NotNull WebIsOnTheScreenAvailable element);

}
