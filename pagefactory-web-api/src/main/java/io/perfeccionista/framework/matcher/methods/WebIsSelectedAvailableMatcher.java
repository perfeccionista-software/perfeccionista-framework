package io.perfeccionista.framework.matcher.methods;

import io.perfeccionista.framework.matcher.PerfeccionistaMatcher;
import io.perfeccionista.framework.pagefactory.elements.methods.WebIsSelectedAvailable;
import org.jetbrains.annotations.NotNull;

public interface WebIsSelectedAvailableMatcher extends PerfeccionistaMatcher<WebIsSelectedAvailable> {

    @Override
    void check(@NotNull WebIsSelectedAvailable element);

}
