package io.perfeccionista.framework.pagefactory.elements;

import io.perfeccionista.framework.pagefactory.elements.methods.availability.ScrollToElementAvailable;
import io.perfeccionista.framework.pagefactory.elements.methods.availability.SizeAvailable;
import io.perfeccionista.framework.pagefactory.itemfilter.Filter;
import io.perfeccionista.framework.pagefactory.itemfilter.MultipleResult;
import io.perfeccionista.framework.pagefactory.operations.OperationResult;

public interface SimpleTable<F extends Filter<?, ?>> extends SizeAvailable, ScrollToElementAvailable<F> {

    OperationResult<String> getHeaderValue(String columnName);

    OperationResult<MultipleResult<String>> getValues(String columnName);

    OperationResult<MultipleResult<String>> getValues(String columnName, F filter);

    OperationResult<String> getFooterValue(String columnName);

}
