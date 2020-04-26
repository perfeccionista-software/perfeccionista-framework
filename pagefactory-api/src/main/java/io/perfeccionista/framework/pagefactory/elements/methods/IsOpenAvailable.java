package io.perfeccionista.framework.pagefactory.elements.methods;

import io.perfeccionista.framework.plugin.AssertMethodType;
import io.perfeccionista.framework.pagefactory.elements.actions.MappedElementAction;
import io.perfeccionista.framework.pagefactory.elements.base.Element;

import static io.perfeccionista.framework.pagefactory.elements.methods.AvailableElementMethods.IS_OPEN_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.AvailableElementMethods.SHOULD_BE_CLOSED_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.AvailableElementMethods.SHOULD_BE_OPEN_METHOD;

public interface IsOpenAvailable extends Element {

    @MappedElementAction(IS_OPEN_METHOD)
    boolean isOpen();

    @AssertMethodType
    @MappedElementAction(SHOULD_BE_OPEN_METHOD)
    IsOpenAvailable shouldBeOpen();

    @AssertMethodType
    @MappedElementAction(SHOULD_BE_CLOSED_METHOD)
    IsOpenAvailable shouldBeClosed();

}
