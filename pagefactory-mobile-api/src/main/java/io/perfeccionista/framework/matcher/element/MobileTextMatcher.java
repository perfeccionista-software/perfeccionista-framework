package io.perfeccionista.framework.matcher.element;

import io.perfeccionista.framework.matcher.PerfeccionistaMatcher;
import io.perfeccionista.framework.pagefactory.elements.MobileText;
import org.jetbrains.annotations.NotNull;

public interface MobileTextMatcher extends PerfeccionistaMatcher<MobileText> {

    @Override
    void check(@NotNull MobileText element);

}
