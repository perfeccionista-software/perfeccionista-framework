package io.perfeccionista.framework.pagefactory.elements.methods;

import io.perfeccionista.framework.matcher.methods.WebGetTextAvailableMatcher;
import io.perfeccionista.framework.pagefactory.elements.base.WebChildElement;
import io.perfeccionista.framework.plugin.AssertMethodType;
import io.perfeccionista.framework.pagefactory.elements.actions.WebMappedElementAction;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import static io.perfeccionista.framework.pagefactory.elements.ElementActionNames.GET_TEXT_METHOD;

public interface WebGetTextAvailable extends WebChildElement {

    @WebMappedElementAction(GET_TEXT_METHOD)
    @Nullable String getText();

    @AssertMethodType
    WebGetTextAvailable should(@NotNull WebGetTextAvailableMatcher matcher);

}
