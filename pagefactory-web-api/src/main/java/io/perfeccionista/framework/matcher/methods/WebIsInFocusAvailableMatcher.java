package io.perfeccionista.framework.matcher.methods;

import io.perfeccionista.framework.matcher.PerfeccionistaMatcher;
import io.perfeccionista.framework.pagefactory.elements.methods.WebIsInFocusAvailable;
import org.jetbrains.annotations.NotNull;

public interface WebIsInFocusAvailableMatcher extends PerfeccionistaMatcher<WebIsInFocusAvailable> {

    @Override
    void check(@NotNull WebIsInFocusAvailable element);

}
