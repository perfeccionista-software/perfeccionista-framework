package io.perfeccionista.framework.matcher.element;

import io.perfeccionista.framework.matcher.PerfeccionistaMatcher;
import io.perfeccionista.framework.pagefactory.elements.MobileTextList;
import org.jetbrains.annotations.NotNull;

public interface MobileTextListMatcher extends PerfeccionistaMatcher<MobileTextList> {

    @Override
    void check(@NotNull MobileTextList element);

}
