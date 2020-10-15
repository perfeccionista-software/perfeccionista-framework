package io.perfeccionista.framework.matcher.element;

import io.perfeccionista.framework.matcher.PerfeccionistaMatcher;
import io.perfeccionista.framework.pagefactory.elements.WebTextBlock;
import org.jetbrains.annotations.NotNull;

public interface WebTextBlockMatcher extends PerfeccionistaMatcher<WebTextBlock> {

    @Override
    void check(@NotNull WebTextBlock element);

}