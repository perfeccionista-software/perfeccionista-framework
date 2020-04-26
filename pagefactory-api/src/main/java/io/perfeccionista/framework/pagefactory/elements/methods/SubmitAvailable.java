package io.perfeccionista.framework.pagefactory.elements.methods;

import io.perfeccionista.framework.plugin.ActionMethodType;
import io.perfeccionista.framework.pagefactory.elements.actions.MappedElementAction;
import io.perfeccionista.framework.pagefactory.elements.base.Element;

import static io.perfeccionista.framework.pagefactory.elements.methods.AvailableElementMethods.SUBMIT_METHOD;

public interface SubmitAvailable extends Element {

    @ActionMethodType
    @MappedElementAction(SUBMIT_METHOD)
    SubmitAvailable submit();

}
