package io.perfeccionista.framework.matcher.element;

import io.perfeccionista.framework.matcher.PerfeccionistaMatcher;
import io.perfeccionista.framework.pagefactory.elements.WebDropDownList;
import org.jetbrains.annotations.NotNull;

public interface WebDropDownListMatcher extends PerfeccionistaMatcher<WebDropDownList> {

    @Override
    void check(@NotNull WebDropDownList actual);

}
