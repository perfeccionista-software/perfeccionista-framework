package io.perfeccionista.framework.matcher.methods;

import io.perfeccionista.framework.matcher.PerfeccionistaMatcher;
import io.perfeccionista.framework.pagefactory.elements.methods.WebElementStateAvailable;
import org.jetbrains.annotations.NotNull;

public interface WebElementStateAvailableMatcher extends PerfeccionistaMatcher<WebElementStateAvailable> {

    @Override
    void check(@NotNull WebElementStateAvailable element);

}
