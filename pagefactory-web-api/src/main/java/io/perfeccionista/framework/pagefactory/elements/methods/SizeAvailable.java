package io.perfeccionista.framework.pagefactory.elements.methods;

import io.perfeccionista.framework.pagefactory.elements.base.WebChildElement;
import io.perfeccionista.framework.pagefactory.elements.base.WebLocatorChainAvailable;
import io.perfeccionista.framework.plugin.AssertMethodType;
import io.perfeccionista.framework.pagefactory.elements.actions.MappedElementAction;
import io.perfeccionista.framework.value.number.NumberValue;

import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.SHOULD_HAVE_SIZE_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.SIZE_METHOD;

public interface SizeAvailable extends WebLocatorChainAvailable {

    @MappedElementAction(SIZE_METHOD)
    int size();

    @AssertMethodType
    @MappedElementAction(SHOULD_HAVE_SIZE_METHOD)
    WebChildElement shouldHaveSize(NumberValue<Integer> expectedSize);

}
