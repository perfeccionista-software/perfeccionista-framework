package io.perfeccionista.framework.matcher.methods;

import io.perfeccionista.framework.matcher.PerfeccionistaMatcher;
import io.perfeccionista.framework.pagefactory.elements.methods.WebGetLabelAvailable;
import org.jetbrains.annotations.NotNull;

public interface WebGetLabelAvailableMatcher extends PerfeccionistaMatcher<WebGetLabelAvailable> {

    @Override
    void check(@NotNull WebGetLabelAvailable element);

}
