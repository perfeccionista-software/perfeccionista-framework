package io.perfeccionista.framework.matcher.element;

import io.perfeccionista.framework.matcher.PerfeccionistaMatcher;
import io.perfeccionista.framework.pagefactory.elements.MobileDropDownList;
import org.jetbrains.annotations.NotNull;

public interface MobileDropDownListMatcher extends PerfeccionistaMatcher<MobileDropDownList> {

    @Override
    void check(@NotNull MobileDropDownList actual);

}
