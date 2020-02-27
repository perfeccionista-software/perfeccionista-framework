package io.perfeccionista.framework.pagefactory.elements.methods.availability;

import io.perfeccionista.framework.pagefactory.elements.methods.ElementMethodAvailable;
import io.perfeccionista.framework.pagefactory.itemfilter.Filter;
import io.perfeccionista.framework.pagefactory.operations.OperationResult;

public interface SwipeToElementAvailable<F extends Filter<?, ?>> extends ElementMethodAvailable {

    OperationResult<Void> swipeToElement(F filter);

}
