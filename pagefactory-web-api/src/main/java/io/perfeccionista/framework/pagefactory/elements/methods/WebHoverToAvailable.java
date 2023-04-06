package io.perfeccionista.framework.pagefactory.elements.methods;

import io.perfeccionista.framework.pagefactory.elements.base.WebChildElement;
import io.perfeccionista.framework.pagefactory.elements.base.WebChildElementBase;
import io.perfeccionista.framework.pagefactory.elements.options.HoverOptions;
import io.perfeccionista.framework.plugin.ActionMethodType;
import io.perfeccionista.framework.pagefactory.elements.actions.WebMappedElementAction;
import org.jetbrains.annotations.NotNull;

import static io.perfeccionista.framework.pagefactory.elements.ElementActionNames.HOVER_TO_METHOD;

public interface WebHoverToAvailable extends WebChildElementBase {

    @ActionMethodType
    @WebMappedElementAction(HOVER_TO_METHOD)
    WebChildElement hoverTo();

    @ActionMethodType
    @WebMappedElementAction(HOVER_TO_METHOD)
    WebChildElement hoverTo(@NotNull HoverOptions options);

}
