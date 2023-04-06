package io.perfeccionista.framework.pagefactory.elements;

import io.perfeccionista.framework.matcher.element.WebRadioButtonMatcher;
import io.perfeccionista.framework.pagefactory.elements.base.WebChildElement;
import io.perfeccionista.framework.pagefactory.elements.methods.WebClickAvailable;
import io.perfeccionista.framework.pagefactory.elements.methods.WebGetTextAvailable;
import io.perfeccionista.framework.pagefactory.elements.methods.WebIsEnabledAvailable;
import io.perfeccionista.framework.pagefactory.elements.methods.WebIsSelectedAvailable;
import org.jetbrains.annotations.NotNull;

public interface WebRadioButton extends WebChildElement,
        WebClickAvailable, WebIsSelectedAvailable, WebIsEnabledAvailable, WebGetTextAvailable {

    // Actions
    @Override
    WebRadioButton executeAction(@NotNull String name, Object... args);

    // Asserts
    WebRadioButton should(@NotNull WebRadioButtonMatcher matcher);

    // Click
    @Override
    WebRadioButton click();

}
