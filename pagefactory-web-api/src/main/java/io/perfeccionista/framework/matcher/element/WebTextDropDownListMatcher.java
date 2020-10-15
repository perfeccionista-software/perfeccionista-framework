package io.perfeccionista.framework.matcher.element;

import io.perfeccionista.framework.matcher.PerfeccionistaMatcher;
import io.perfeccionista.framework.pagefactory.elements.WebTextDropDownList;
import org.jetbrains.annotations.NotNull;

public interface WebTextDropDownListMatcher extends PerfeccionistaMatcher<WebTextDropDownList> {

    @Override
    void check(@NotNull WebTextDropDownList actual);

}
