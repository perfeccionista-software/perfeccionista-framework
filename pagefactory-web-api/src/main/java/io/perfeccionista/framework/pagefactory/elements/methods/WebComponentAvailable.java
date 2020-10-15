package io.perfeccionista.framework.pagefactory.elements.methods;

import io.perfeccionista.framework.matcher.actions.WebComponentAvailableMatcher;
import io.perfeccionista.framework.pagefactory.elements.actions.base.WebMappedElementAction;
import io.perfeccionista.framework.pagefactory.elements.base.WebChildElementBase;
import io.perfeccionista.framework.plugin.AssertMethodType;
import org.jetbrains.annotations.NotNull;

import static io.perfeccionista.framework.pagefactory.elements.actions.WebElementActionNames.IS_COMPONENT_DISPLAYED_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.actions.WebElementActionNames.IS_COMPONENT_PRESENT_METHOD;

/**
 * TODO JavaDoc
 */
public interface WebComponentAvailable extends WebChildElementBase {

    @WebMappedElementAction(IS_COMPONENT_PRESENT_METHOD)
    boolean isComponentPresent(@NotNull String componentName);

    @WebMappedElementAction(IS_COMPONENT_DISPLAYED_METHOD)
    boolean isComponentDisplayed(@NotNull String componentName);

    @AssertMethodType
    WebComponentAvailable should(@NotNull WebComponentAvailableMatcher matcher);

}