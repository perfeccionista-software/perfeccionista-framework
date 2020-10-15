package io.perfeccionista.framework.matcher.element;

import io.perfeccionista.framework.matcher.PerfeccionistaMatcher;
import io.perfeccionista.framework.pagefactory.elements.WebBlock;
import org.jetbrains.annotations.NotNull;

public interface WebBlockMatcher extends PerfeccionistaMatcher<WebBlock> {

    @Override
    void check(@NotNull WebBlock actual);

}
