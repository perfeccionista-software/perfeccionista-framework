package io.perfeccionista.framework.pagefactory.elements;

import io.perfeccionista.framework.pagefactory.elements.base.ChildElement;
import io.perfeccionista.framework.pagefactory.elements.methods.availability.ScrollToElementAvailable;
import io.perfeccionista.framework.pagefactory.elements.methods.availability.SizeAvailable;
import io.perfeccionista.framework.pagefactory.itemextractor.IndexedItems;
import io.perfeccionista.framework.pagefactory.itemfilter.MultipleTableRowSimpleFilter;
import io.perfeccionista.framework.pagefactory.operations.OperationResult;

public interface SimpleTable extends ChildElement,
        SizeAvailable, ScrollToElementAvailable {

    OperationResult<String> getHeaderValue(String columnName);

    OperationResult<IndexedItems<String>> getValues(String columnName);

    OperationResult<IndexedItems<String>> getValues(String columnName, MultipleTableRowSimpleFilter filter);

    OperationResult<String> getFooterValue(String columnName);

}
