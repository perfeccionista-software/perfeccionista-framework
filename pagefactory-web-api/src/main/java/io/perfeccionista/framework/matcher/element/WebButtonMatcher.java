package io.perfeccionista.framework.matcher.element;

import io.perfeccionista.framework.matcher.PerfeccionistaMatcher;
import io.perfeccionista.framework.pagefactory.elements.WebButton;
import org.jetbrains.annotations.NotNull;

public interface WebButtonMatcher extends PerfeccionistaMatcher<WebButton> {

    @Override
    void check(@NotNull WebButton actual);

}
