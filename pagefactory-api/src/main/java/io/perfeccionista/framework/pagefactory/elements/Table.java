package io.perfeccionista.framework.pagefactory.elements;

import io.perfeccionista.framework.pagefactory.elements.base.ChildElement;
import io.perfeccionista.framework.pagefactory.elements.methods.availability.ScrollToElementAvailable;
import io.perfeccionista.framework.pagefactory.elements.methods.availability.SizeAvailable;
import io.perfeccionista.framework.pagefactory.itemextractor.IndexedItems;
import io.perfeccionista.framework.pagefactory.itemextractor.TableRowValueExtractor;
import io.perfeccionista.framework.pagefactory.itemfilter.MultipleTableRowFilter;
import io.perfeccionista.framework.pagefactory.operations.OperationResult;

public interface Table extends ChildElement,
        SizeAvailable, ScrollToElementAvailable {

    <V> OperationResult<V> getHeaderValue(TableRowValueExtractor<V> extractor);

    <V> OperationResult<IndexedItems<V>> getValues(TableRowValueExtractor<V> extractor);

    <V> OperationResult<IndexedItems<V>> getValues(TableRowValueExtractor<V> extractor, MultipleTableRowFilter filter);

    <V> OperationResult<V> getFooterValue(TableRowValueExtractor<V> extractor);

}
