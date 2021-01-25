package io.perfeccionista.framework.matcher.element;

import io.perfeccionista.framework.matcher.PerfeccionistaMatcher;
import io.perfeccionista.framework.pagefactory.elements.MobileBlock;
import org.jetbrains.annotations.NotNull;

public interface MobileBlockMatcher extends PerfeccionistaMatcher<MobileBlock> {

    @Override
    void check(@NotNull MobileBlock actual);

}
