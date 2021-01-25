package io.perfeccionista.framework.matcher.element;

import io.perfeccionista.framework.matcher.PerfeccionistaMatcher;
import io.perfeccionista.framework.pagefactory.elements.WebText;
import org.jetbrains.annotations.NotNull;

public interface WebTextMatcher extends PerfeccionistaMatcher<WebText> {

    @Override
    void check(@NotNull WebText element);

}
