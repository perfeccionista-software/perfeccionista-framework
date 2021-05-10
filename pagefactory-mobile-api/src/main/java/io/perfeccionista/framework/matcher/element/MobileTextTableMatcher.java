package io.perfeccionista.framework.matcher.element;

import io.perfeccionista.framework.matcher.PerfeccionistaMatcher;
import io.perfeccionista.framework.pagefactory.elements.MobileTextTable;
import org.jetbrains.annotations.NotNull;

public interface MobileTextTableMatcher extends PerfeccionistaMatcher<MobileTextTable> {

    @Override
    void check(@NotNull MobileTextTable element);

}
