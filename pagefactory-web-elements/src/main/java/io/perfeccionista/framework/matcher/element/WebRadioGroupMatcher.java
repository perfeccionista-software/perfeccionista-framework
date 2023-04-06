package io.perfeccionista.framework.matcher.element;

import io.perfeccionista.framework.matcher.PerfeccionistaMatcher;
import io.perfeccionista.framework.pagefactory.elements.WebRadioGroup;
import org.jetbrains.annotations.NotNull;

public interface WebRadioGroupMatcher extends PerfeccionistaMatcher<WebRadioGroup> {

    @Override
    void check(@NotNull WebRadioGroup element);

}
