package io.perfeccionista.framework.matcher.element;

import io.perfeccionista.framework.matcher.PerfeccionistaMatcher;
import io.perfeccionista.framework.pagefactory.elements.WebTextList;
import org.jetbrains.annotations.NotNull;

public interface WebTextListMatcher extends PerfeccionistaMatcher<WebTextList> {

    @Override
    void check(@NotNull WebTextList element);

}
