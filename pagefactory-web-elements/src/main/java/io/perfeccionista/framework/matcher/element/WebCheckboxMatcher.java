package io.perfeccionista.framework.matcher.element;

import io.perfeccionista.framework.matcher.PerfeccionistaMatcher;
import io.perfeccionista.framework.pagefactory.elements.WebCheckbox;
import org.jetbrains.annotations.NotNull;

public interface WebCheckboxMatcher extends PerfeccionistaMatcher<WebCheckbox> {

    @Override
    void check(@NotNull WebCheckbox actual);

}
