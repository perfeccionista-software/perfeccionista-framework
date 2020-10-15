package io.perfeccionista.framework.matcher.element;

import io.perfeccionista.framework.matcher.PerfeccionistaMatcher;
import io.perfeccionista.framework.pagefactory.elements.WebPage;
import org.jetbrains.annotations.NotNull;

public interface WebPageMatcher extends PerfeccionistaMatcher<WebPage> {

    @Override
    void check(@NotNull WebPage actual);

}
