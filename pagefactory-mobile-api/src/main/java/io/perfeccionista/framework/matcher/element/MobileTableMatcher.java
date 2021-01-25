package io.perfeccionista.framework.matcher.element;

import io.perfeccionista.framework.matcher.PerfeccionistaMatcher;
import io.perfeccionista.framework.pagefactory.elements.MobileTable;
import org.jetbrains.annotations.NotNull;

public interface MobileTableMatcher extends PerfeccionistaMatcher<MobileTable> {

    @Override
    void check(@NotNull MobileTable actual);

}
