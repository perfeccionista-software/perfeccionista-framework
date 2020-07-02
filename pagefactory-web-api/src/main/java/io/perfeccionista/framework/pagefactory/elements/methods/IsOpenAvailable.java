package io.perfeccionista.framework.pagefactory.elements.methods;

import io.perfeccionista.framework.pagefactory.elements.base.WebChildElement;
import io.perfeccionista.framework.pagefactory.elements.base.WebLocatorChainAvailable;
import io.perfeccionista.framework.plugin.AssertMethodType;
import io.perfeccionista.framework.pagefactory.elements.actions.MappedElementAction;

import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.IS_OPEN_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.SHOULD_BE_CLOSED_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.SHOULD_BE_OPEN_METHOD;

public interface IsOpenAvailable extends WebLocatorChainAvailable {

    @MappedElementAction(IS_OPEN_METHOD)
    boolean isOpen();

    @AssertMethodType
    @MappedElementAction(SHOULD_BE_OPEN_METHOD)
    WebChildElement shouldBeOpen();

    @AssertMethodType
    @MappedElementAction(SHOULD_BE_CLOSED_METHOD)
    WebChildElement shouldBeClosed();

}
