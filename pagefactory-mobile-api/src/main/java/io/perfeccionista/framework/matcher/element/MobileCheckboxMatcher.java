package io.perfeccionista.framework.matcher.element;

import io.perfeccionista.framework.matcher.PerfeccionistaMatcher;
import io.perfeccionista.framework.pagefactory.elements.MobileCheckbox;
import org.jetbrains.annotations.NotNull;

public interface MobileCheckboxMatcher extends PerfeccionistaMatcher<MobileCheckbox> {

    @Override
    void check(@NotNull MobileCheckbox actual);

}
