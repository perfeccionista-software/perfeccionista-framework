package io.perfeccionista.framework.pagefactory.elements.methods;

import io.perfeccionista.framework.plugin.AssertMethodType;
import io.perfeccionista.framework.pagefactory.elements.actions.MappedElementAction;
import io.perfeccionista.framework.pagefactory.elements.base.Element;
import io.perfeccionista.framework.value.Value;

import static io.perfeccionista.framework.pagefactory.elements.methods.AvailableElementMethods.SHOULD_HAVE_SIZE_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.AvailableElementMethods.SIZE_METHOD;

public interface SizeAvailable extends Element {

    @MappedElementAction(SIZE_METHOD)
    int size();

    @AssertMethodType
    @MappedElementAction(SHOULD_HAVE_SIZE_METHOD)
    SizeAvailable shouldHaveSize(Value<Integer> integerValue);

}
