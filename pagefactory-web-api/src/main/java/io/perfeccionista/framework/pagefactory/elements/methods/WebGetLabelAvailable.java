package io.perfeccionista.framework.pagefactory.elements.methods;

import io.perfeccionista.framework.matcher.methods.WebGetLabelAvailableMatcher;
import io.perfeccionista.framework.pagefactory.elements.base.WebChildElement;
import io.perfeccionista.framework.plugin.AssertMethodType;
import io.perfeccionista.framework.pagefactory.elements.actions.WebMappedElementAction;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import static io.perfeccionista.framework.pagefactory.elements.ElementActionNames.GET_LABEL_METHOD;

public interface WebGetLabelAvailable extends WebChildElement {

    @WebMappedElementAction(GET_LABEL_METHOD)
    @Nullable String getLabel();

    @AssertMethodType
    WebGetLabelAvailable should(@NotNull WebGetLabelAvailableMatcher matcher);

}
