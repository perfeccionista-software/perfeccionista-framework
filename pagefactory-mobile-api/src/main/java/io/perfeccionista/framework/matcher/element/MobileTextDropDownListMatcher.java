package io.perfeccionista.framework.matcher.element;

import io.perfeccionista.framework.matcher.PerfeccionistaMatcher;
import io.perfeccionista.framework.pagefactory.elements.MobileTextDropDownList;
import org.jetbrains.annotations.NotNull;

public interface MobileTextDropDownListMatcher extends PerfeccionistaMatcher<MobileTextDropDownList> {

    @Override
    void check(@NotNull MobileTextDropDownList actual);

}
