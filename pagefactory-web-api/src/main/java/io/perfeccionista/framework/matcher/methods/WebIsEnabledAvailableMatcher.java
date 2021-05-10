package io.perfeccionista.framework.matcher.methods;

import io.perfeccionista.framework.matcher.PerfeccionistaMatcher;
import io.perfeccionista.framework.pagefactory.elements.methods.WebIsEnabledAvailable;
import org.jetbrains.annotations.NotNull;

public interface WebIsEnabledAvailableMatcher extends PerfeccionistaMatcher<WebIsEnabledAvailable> {

    @Override
    void check(@NotNull WebIsEnabledAvailable element);

}
