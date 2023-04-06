package io.perfeccionista.framework.matcher.methods;


import io.perfeccionista.framework.matcher.PerfeccionistaMatcher;
import io.perfeccionista.framework.pagefactory.elements.methods.WebGetColorAvailable;
import org.jetbrains.annotations.NotNull;

public interface WebGetColorAvailableMatcher extends PerfeccionistaMatcher<WebGetColorAvailable> {

    @Override
    void check(@NotNull WebGetColorAvailable element);

}
