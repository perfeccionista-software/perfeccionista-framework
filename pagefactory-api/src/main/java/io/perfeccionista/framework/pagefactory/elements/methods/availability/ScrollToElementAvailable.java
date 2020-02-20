package io.perfeccionista.framework.pagefactory.elements.methods.availability;

import io.perfeccionista.framework.pagefactory.elements.Block;
import io.perfeccionista.framework.pagefactory.elements.methods.ElementMethodAvailable;
import io.perfeccionista.framework.pagefactory.itemfilter.SingleItemFilter;
import io.perfeccionista.framework.pagefactory.operations.OperationResult;

public interface ScrollToElementAvailable extends ElementMethodAvailable {

    <T extends Block> OperationResult<Void> scrollToElement(SingleItemFilter<T> filter);

}
