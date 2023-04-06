package io.perfeccionista.framework.matcher.element;

import io.perfeccionista.framework.matcher.PerfeccionistaMatcher;
import io.perfeccionista.framework.pagefactory.elements.WebImage;
import org.jetbrains.annotations.NotNull;

public interface WebImageMatcher extends PerfeccionistaMatcher<WebImage> {

    @Override
    void check(@NotNull WebImage actual);

}
