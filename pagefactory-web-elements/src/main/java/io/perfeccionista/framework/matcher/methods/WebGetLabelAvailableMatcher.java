package io.perfeccionista.framework.matcher.methods;

import io.perfeccionista.framework.matcher.PerfeccionistaMatcher;
import io.perfeccionista.framework.pagefactory.elements.methods.WebGetTextAvailable;
import org.jetbrains.annotations.NotNull;

public interface WebGetLabelAvailableMatcher extends PerfeccionistaMatcher<WebGetTextAvailable> {

    @Override
    void check(@NotNull WebGetTextAvailable element);

}
