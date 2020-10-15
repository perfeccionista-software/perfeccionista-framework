package io.perfeccionista.framework.matcher.actions;

import io.perfeccionista.framework.matcher.PerfeccionistaMatcher;
import io.perfeccionista.framework.pagefactory.elements.methods.IsSelectedAvailable;
import org.jetbrains.annotations.NotNull;

public interface IsSelectedAvailableMatcher extends PerfeccionistaMatcher<IsSelectedAvailable> {

    @Override
    void check(@NotNull IsSelectedAvailable element);

}
