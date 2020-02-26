package io.perfeccionista.framework.pagefactory.elements;

import io.perfeccionista.framework.pagefactory.elements.methods.availability.ScrollToElementAvailable;
import io.perfeccionista.framework.pagefactory.elements.methods.availability.SizeAvailable;
import io.perfeccionista.framework.pagefactory.itemfilter.Filter;
import io.perfeccionista.framework.pagefactory.itemfilter.MultipleResult;
import io.perfeccionista.framework.pagefactory.operations.OperationResult;

public interface SimpleUnorderedList<F extends Filter<?, ?>> extends SizeAvailable, ScrollToElementAvailable<F> {

    OperationResult<MultipleResult<String>> getValues();

    OperationResult<MultipleResult<String>> getValues(F filter);

}
