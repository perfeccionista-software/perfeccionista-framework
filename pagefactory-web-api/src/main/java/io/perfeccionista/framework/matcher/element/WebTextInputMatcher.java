package io.perfeccionista.framework.matcher.element;

import io.perfeccionista.framework.matcher.PerfeccionistaMatcher;
import io.perfeccionista.framework.pagefactory.elements.WebTextInput;
import org.jetbrains.annotations.NotNull;

public interface WebTextInputMatcher extends PerfeccionistaMatcher<WebTextInput> {

    @Override
    void check(@NotNull WebTextInput element);

}

