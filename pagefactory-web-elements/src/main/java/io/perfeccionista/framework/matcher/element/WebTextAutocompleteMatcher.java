package io.perfeccionista.framework.matcher.element;

import io.perfeccionista.framework.matcher.PerfeccionistaMatcher;
import io.perfeccionista.framework.pagefactory.elements.WebTextAutocomplete;
import org.jetbrains.annotations.NotNull;

public interface WebTextAutocompleteMatcher extends PerfeccionistaMatcher<WebTextAutocomplete> {

    @Override
    void check(@NotNull WebTextAutocomplete actual);

}

