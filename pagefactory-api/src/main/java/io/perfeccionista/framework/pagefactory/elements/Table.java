package io.perfeccionista.framework.pagefactory.elements;

import io.perfeccionista.framework.pagefactory.elements.methods.availability.ScrollToElementAvailable;
import io.perfeccionista.framework.pagefactory.elements.methods.availability.SizeAvailable;
import io.perfeccionista.framework.pagefactory.itemextractor.JsWebTableRowValueExtractor;
import io.perfeccionista.framework.pagefactory.itemfilter.Filter;
import io.perfeccionista.framework.pagefactory.itemfilter.MultipleResult;
import io.perfeccionista.framework.pagefactory.operations.OperationResult;

public interface Table<F extends Filter<?, ?>> extends SizeAvailable, ScrollToElementAvailable<F> {

    // TODO: Параметризовать экстракторы
    <V> OperationResult<V> getHeaderValue(JsWebTableRowValueExtractor<V> extractor);

    <V> OperationResult<MultipleResult<V>> getValues(JsWebTableRowValueExtractor<V> extractor);

    <V> OperationResult<MultipleResult<V>> getValues(JsWebTableRowValueExtractor<V> extractor, F filter);

    <V> OperationResult<V> getFooterValue(JsWebTableRowValueExtractor<V> extractor);

}
