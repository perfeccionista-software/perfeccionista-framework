package io.perfeccionista.framework.matcher.actions;

import io.perfeccionista.framework.matcher.PerfeccionistaMatcher;
import io.perfeccionista.framework.pagefactory.elements.methods.IsEnabledAvailable;
import org.jetbrains.annotations.NotNull;

public interface IsEnabledAvailableMatcher extends PerfeccionistaMatcher<IsEnabledAvailable> {

    @Override
    void check(@NotNull IsEnabledAvailable element);

}
