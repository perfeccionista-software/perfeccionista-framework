package io.perfeccionista.framework.matcher.element;

import io.perfeccionista.framework.matcher.PerfeccionistaMatcher;
import io.perfeccionista.framework.pagefactory.elements.MobileTextInput;
import org.jetbrains.annotations.NotNull;

public interface MobileTextInputMatcher extends PerfeccionistaMatcher<MobileTextInput> {

    @Override
    void check(@NotNull MobileTextInput element);

}

