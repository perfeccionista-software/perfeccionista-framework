package io.perfeccionista.framework.matcher.element;

import io.perfeccionista.framework.matcher.PerfeccionistaMatcher;
import io.perfeccionista.framework.pagefactory.elements.WebTable;
import org.jetbrains.annotations.NotNull;

public interface WebTableMatcher extends PerfeccionistaMatcher<WebTable> {

    @Override
    void check(@NotNull WebTable actual);

}
