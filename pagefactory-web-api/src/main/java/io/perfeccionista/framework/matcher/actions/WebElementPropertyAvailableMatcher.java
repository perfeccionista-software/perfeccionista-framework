package io.perfeccionista.framework.matcher.actions;

import io.perfeccionista.framework.matcher.PerfeccionistaMatcher;
import io.perfeccionista.framework.pagefactory.elements.methods.WebElementPropertyAvailable;
import org.jetbrains.annotations.NotNull;

public interface WebElementPropertyAvailableMatcher extends PerfeccionistaMatcher<WebElementPropertyAvailable> {

    @Override
    void check(@NotNull WebElementPropertyAvailable element);

}
