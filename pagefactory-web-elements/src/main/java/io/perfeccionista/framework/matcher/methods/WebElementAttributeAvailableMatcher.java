package io.perfeccionista.framework.matcher.methods;

import io.perfeccionista.framework.matcher.PerfeccionistaMatcher;
import io.perfeccionista.framework.pagefactory.elements.base.WebChildElement;
import org.jetbrains.annotations.NotNull;

public interface WebElementAttributeAvailableMatcher extends PerfeccionistaMatcher<WebChildElement> {

    @Override
    void check(@NotNull WebChildElement element);

}
