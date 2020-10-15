package io.perfeccionista.framework.matcher.element;

import io.perfeccionista.framework.matcher.PerfeccionistaMatcher;
import io.perfeccionista.framework.pagefactory.elements.WebTextTable;
import org.jetbrains.annotations.NotNull;

public interface WebTextTableMatcher extends PerfeccionistaMatcher<WebTextTable> {

    @Override
    void check(@NotNull WebTextTable element);

}
