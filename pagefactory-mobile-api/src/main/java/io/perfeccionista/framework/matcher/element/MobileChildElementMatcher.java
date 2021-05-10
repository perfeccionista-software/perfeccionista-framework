package io.perfeccionista.framework.matcher.element;

import io.perfeccionista.framework.matcher.PerfeccionistaMatcher;
import io.perfeccionista.framework.pagefactory.elements.base.MobileChildElement;
import org.jetbrains.annotations.NotNull;

public interface MobileChildElementMatcher extends PerfeccionistaMatcher<MobileChildElement> {

    @Override
    void check(@NotNull MobileChildElement element);

}
