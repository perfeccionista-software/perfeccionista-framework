package io.perfeccionista.framework.pagefactory.elements.methods;

import io.perfeccionista.framework.matcher.actions.GetLabelAvailableMatcher;
import io.perfeccionista.framework.pagefactory.elements.base.WebChildElementBase;
import io.perfeccionista.framework.plugin.AssertMethodType;
import io.perfeccionista.framework.pagefactory.elements.actions.base.WebMappedElementAction;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import static io.perfeccionista.framework.pagefactory.elements.actions.WebElementActionNames.GET_LABEL_METHOD;

public interface GetLabelAvailable extends WebChildElementBase {

    @WebMappedElementAction(GET_LABEL_METHOD)
    @Nullable String getLabel();

    @AssertMethodType
    GetLabelAvailable should(@NotNull GetLabelAvailableMatcher matcher);

}
