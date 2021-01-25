package io.perfeccionista.framework.matcher.element;

import io.perfeccionista.framework.matcher.PerfeccionistaMatcher;
import io.perfeccionista.framework.pagefactory.elements.MobileList;
import org.jetbrains.annotations.NotNull;

public interface MobileListMatcher extends PerfeccionistaMatcher<MobileList> {

    @Override
    void check(@NotNull MobileList actual);

}
