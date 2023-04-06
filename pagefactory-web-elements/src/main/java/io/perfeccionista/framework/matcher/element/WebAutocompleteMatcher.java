package io.perfeccionista.framework.matcher.element;

import io.perfeccionista.framework.matcher.PerfeccionistaMatcher;
import io.perfeccionista.framework.pagefactory.elements.WebAutocomplete;
import org.jetbrains.annotations.NotNull;

public interface WebAutocompleteMatcher extends PerfeccionistaMatcher<WebAutocomplete> {

    @Override
    void check(@NotNull WebAutocomplete actual);

}
