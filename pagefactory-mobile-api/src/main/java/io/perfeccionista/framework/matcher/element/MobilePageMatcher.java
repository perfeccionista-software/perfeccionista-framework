package io.perfeccionista.framework.matcher.element;

import io.perfeccionista.framework.matcher.PerfeccionistaMatcher;
import io.perfeccionista.framework.pagefactory.elements.MobilePage;
import org.jetbrains.annotations.NotNull;

public interface MobilePageMatcher extends PerfeccionistaMatcher<MobilePage> {

    @Override
    void check(@NotNull MobilePage actual);

}
