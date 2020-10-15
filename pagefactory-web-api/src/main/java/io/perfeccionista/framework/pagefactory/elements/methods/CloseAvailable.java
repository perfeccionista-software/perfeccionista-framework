package io.perfeccionista.framework.pagefactory.elements.methods;

import io.perfeccionista.framework.pagefactory.elements.base.WebChildElementBase;
import io.perfeccionista.framework.plugin.ActionMethodType;
import io.perfeccionista.framework.pagefactory.elements.actions.base.WebMappedElementAction;

import static io.perfeccionista.framework.pagefactory.elements.actions.WebElementActionNames.CLOSE_METHOD;

public interface CloseAvailable extends WebChildElementBase {

    @ActionMethodType
    @WebMappedElementAction(CLOSE_METHOD)
    CloseAvailable close();

}
