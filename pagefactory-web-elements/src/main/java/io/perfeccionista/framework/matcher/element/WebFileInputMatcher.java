package io.perfeccionista.framework.matcher.element;

import io.perfeccionista.framework.matcher.PerfeccionistaMatcher;
import io.perfeccionista.framework.pagefactory.elements.WebFileInput;
import org.jetbrains.annotations.NotNull;

public interface WebFileInputMatcher extends PerfeccionistaMatcher<WebFileInput> {

    @Override
    void check(@NotNull WebFileInput actual);

}
