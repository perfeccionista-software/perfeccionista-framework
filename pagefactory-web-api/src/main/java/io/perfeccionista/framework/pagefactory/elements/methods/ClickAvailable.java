package io.perfeccionista.framework.pagefactory.elements.methods;

import io.perfeccionista.framework.pagefactory.elements.base.WebChildElementBase;
import io.perfeccionista.framework.plugin.ActionMethodType;
import io.perfeccionista.framework.pagefactory.elements.actions.base.WebMappedElementAction;

import static io.perfeccionista.framework.pagefactory.elements.actions.WebElementActionNames.CLICK_METHOD;

public interface ClickAvailable extends WebChildElementBase {

    @ActionMethodType
    @WebMappedElementAction(CLICK_METHOD)
    ClickAvailable click();

}
