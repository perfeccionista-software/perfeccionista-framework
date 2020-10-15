package io.perfeccionista.framework.pagefactory.elements.methods;

import io.perfeccionista.framework.matcher.actions.GetTextAvailableMatcher;
import io.perfeccionista.framework.pagefactory.elements.base.WebChildElementBase;
import io.perfeccionista.framework.plugin.AssertMethodType;
import io.perfeccionista.framework.pagefactory.elements.actions.base.WebMappedElementAction;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import static io.perfeccionista.framework.pagefactory.elements.actions.WebElementActionNames.GET_TEXT_METHOD;

public interface GetTextAvailable extends WebChildElementBase {

    @WebMappedElementAction(GET_TEXT_METHOD)
    @Nullable String getText();

    @AssertMethodType
    GetTextAvailable should(@NotNull GetTextAvailableMatcher matcher);

}
