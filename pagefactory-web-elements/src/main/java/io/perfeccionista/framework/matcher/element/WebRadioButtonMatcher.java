package io.perfeccionista.framework.matcher.element;

import io.perfeccionista.framework.matcher.PerfeccionistaMatcher;
import io.perfeccionista.framework.pagefactory.elements.WebRadioButton;
import org.jetbrains.annotations.NotNull;

public interface WebRadioButtonMatcher extends PerfeccionistaMatcher<WebRadioButton> {

    @Override
    void check(@NotNull WebRadioButton actual);

}
