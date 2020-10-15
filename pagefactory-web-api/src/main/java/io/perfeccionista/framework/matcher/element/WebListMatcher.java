package io.perfeccionista.framework.matcher.element;

import io.perfeccionista.framework.matcher.PerfeccionistaMatcher;
import io.perfeccionista.framework.pagefactory.elements.WebList;
import org.jetbrains.annotations.NotNull;

public interface WebListMatcher extends PerfeccionistaMatcher<WebList> {

    @Override
    void check(@NotNull WebList actual);

}
