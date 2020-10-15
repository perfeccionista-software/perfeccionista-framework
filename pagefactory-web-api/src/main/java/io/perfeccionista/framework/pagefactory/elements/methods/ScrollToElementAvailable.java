package io.perfeccionista.framework.pagefactory.elements.methods;

import io.perfeccionista.framework.pagefactory.elements.base.WebChildElement;
import io.perfeccionista.framework.pagefactory.elements.base.WebChildElementBase;
import io.perfeccionista.framework.pagefactory.filter.WebFilterBuilder;
import io.perfeccionista.framework.plugin.ActionMethodType;
import io.perfeccionista.framework.pagefactory.elements.actions.base.WebMappedElementAction;
import org.jetbrains.annotations.NotNull;

import static io.perfeccionista.framework.pagefactory.elements.actions.WebElementActionNames.SCROLL_TO_ELEMENT_METHOD;

@Deprecated
public interface ScrollToElementAvailable<F extends WebFilterBuilder<?, ?>> extends WebChildElementBase {

    @ActionMethodType
    @WebMappedElementAction(SCROLL_TO_ELEMENT_METHOD)
    WebChildElement scrollToElement(@NotNull F filter);

}
