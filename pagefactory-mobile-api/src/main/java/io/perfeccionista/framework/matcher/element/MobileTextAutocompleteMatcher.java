package io.perfeccionista.framework.matcher.element;

import io.perfeccionista.framework.matcher.PerfeccionistaMatcher;
import io.perfeccionista.framework.pagefactory.elements.MobileTextAutocomplete;
import org.jetbrains.annotations.NotNull;

public interface MobileTextAutocompleteMatcher extends PerfeccionistaMatcher<MobileTextAutocomplete> {

    @Override
    void check(@NotNull MobileTextAutocomplete actual);

}

