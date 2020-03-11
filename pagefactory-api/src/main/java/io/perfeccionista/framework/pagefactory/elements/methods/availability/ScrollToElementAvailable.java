package io.perfeccionista.framework.pagefactory.elements.methods.availability;

import io.perfeccionista.framework.pagefactory.elements.methods.ElementMethodAvailable;
import io.perfeccionista.framework.pagefactory.filter.Filter;
import io.perfeccionista.framework.pagefactory.operations.OperationResult;

public interface ScrollToElementAvailable<F extends Filter<?, ?>> extends ElementMethodAvailable {

    OperationResult<Void> scrollToElement(F filter);

}
