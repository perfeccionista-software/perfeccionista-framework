package io.perfeccionista.framework.matcher.element;

import io.perfeccionista.framework.matcher.PerfeccionistaMatcher;
import io.perfeccionista.framework.pagefactory.elements.MobileButton;
import org.jetbrains.annotations.NotNull;

public interface MobileButtonMatcher extends PerfeccionistaMatcher<MobileButton> {

    @Override
    void check(@NotNull MobileButton actual);

}
