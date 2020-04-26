package io.perfeccionista.framework.pagefactory.elements.methods;

import io.perfeccionista.framework.plugin.ActionMethodType;
import io.perfeccionista.framework.pagefactory.elements.actions.MappedElementAction;
import io.perfeccionista.framework.pagefactory.elements.base.Element;

import static io.perfeccionista.framework.pagefactory.elements.methods.AvailableElementMethods.OPEN_METHOD;

public interface OpenAvailable extends Element {

    @ActionMethodType
    @MappedElementAction(OPEN_METHOD)
    OpenAvailable open();

}
