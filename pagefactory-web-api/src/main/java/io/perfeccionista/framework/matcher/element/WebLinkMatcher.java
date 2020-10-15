package io.perfeccionista.framework.matcher.element;

import io.perfeccionista.framework.matcher.PerfeccionistaMatcher;
import io.perfeccionista.framework.pagefactory.elements.WebLink;
import org.jetbrains.annotations.NotNull;

public interface WebLinkMatcher extends PerfeccionistaMatcher<WebLink> {

    @Override
    void check(@NotNull WebLink actual);

}
