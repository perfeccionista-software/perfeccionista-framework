package io.perfeccionista.framework.matcher.element;

import io.perfeccionista.framework.matcher.PerfeccionistaMatcher;
import io.perfeccionista.framework.pagefactory.elements.MobileImage;
import org.jetbrains.annotations.NotNull;

public interface MobileImageMatcher extends PerfeccionistaMatcher<MobileImage> {

    @Override
    void check(@NotNull MobileImage actual);

}
