package io.perfeccionista.framework.pagefactory.elements.methods;

import io.perfeccionista.framework.pagefactory.elements.base.WebChildElement;
import io.perfeccionista.framework.pagefactory.elements.options.ClickOptions;
import io.perfeccionista.framework.pagefactory.elements.options.ContextClickOptions;
import io.perfeccionista.framework.pagefactory.elements.options.DoubleClickOptions;
import io.perfeccionista.framework.plugin.ActionMethodType;
import io.perfeccionista.framework.pagefactory.elements.actions.WebMappedElementAction;
import org.jetbrains.annotations.NotNull;

import static io.perfeccionista.framework.pagefactory.elements.ElementActionNames.CLICK_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.ElementActionNames.CONTEXT_CLICK_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.ElementActionNames.DOUBLE_CLICK_METHOD;

public interface WebClickAvailable<T extends WebClickAvailable> extends WebChildElement {

    @ActionMethodType
    @WebMappedElementAction(CLICK_METHOD)
    T click();

    @ActionMethodType
    @WebMappedElementAction(CLICK_METHOD)
    T click(@NotNull ClickOptions options);

    @ActionMethodType
    @WebMappedElementAction(DOUBLE_CLICK_METHOD)
    T doubleClick();

    @ActionMethodType
    @WebMappedElementAction(DOUBLE_CLICK_METHOD)
    T doubleClick(@NotNull DoubleClickOptions options);

    @ActionMethodType
    @WebMappedElementAction(CONTEXT_CLICK_METHOD)
    T contextClick();

    @ActionMethodType
    @WebMappedElementAction(CONTEXT_CLICK_METHOD)
    T contextClick(@NotNull ContextClickOptions options);

}
