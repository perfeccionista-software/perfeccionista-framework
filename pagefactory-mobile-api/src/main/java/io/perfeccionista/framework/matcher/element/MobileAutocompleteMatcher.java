package io.perfeccionista.framework.matcher.element;

import io.perfeccionista.framework.matcher.PerfeccionistaMatcher;
import io.perfeccionista.framework.pagefactory.elements.MobileAutocomplete;
import org.jetbrains.annotations.NotNull;

public interface MobileAutocompleteMatcher extends PerfeccionistaMatcher<MobileAutocomplete> {

    @Override
    void check(@NotNull MobileAutocomplete actual);

}
