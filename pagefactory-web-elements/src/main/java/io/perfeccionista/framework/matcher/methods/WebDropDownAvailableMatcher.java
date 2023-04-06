package io.perfeccionista.framework.matcher.methods;

import io.perfeccionista.framework.matcher.PerfeccionistaMatcher;
import io.perfeccionista.framework.pagefactory.elements.methods.WebDropDownAvailable;
import org.jetbrains.annotations.NotNull;

public interface WebDropDownAvailableMatcher extends PerfeccionistaMatcher<WebDropDownAvailable> {

    @Override
    void check(@NotNull WebDropDownAvailable element);

}
