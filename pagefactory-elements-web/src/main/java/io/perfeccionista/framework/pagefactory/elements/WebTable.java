package io.perfeccionista.framework.pagefactory.elements;

import io.perfeccionista.framework.pagefactory.elements.mapping.WebColumnMapper;
import io.perfeccionista.framework.pagefactory.elements.methods.availability.ScrollToElementAvailable;
import io.perfeccionista.framework.pagefactory.elements.methods.availability.SizeAvailable;
import io.perfeccionista.framework.pagefactory.extractor.JsTableRowValueExtractor;
import io.perfeccionista.framework.pagefactory.filter.MultipleResult;
import io.perfeccionista.framework.pagefactory.filter.JsTableRowFilter;
import io.perfeccionista.framework.pagefactory.operations.OperationResult;

import java.util.Optional;

public interface WebTable extends WebChildElement,
        ScrollToElementAvailable<JsTableRowFilter>, SizeAvailable {

    Optional<WebColumnMapper> getColumnMapper(String columnName);

    // TODO: Параметризовать экстракторы
    <V> OperationResult<V> getHeaderValue(JsTableRowValueExtractor<V> extractor);

    <V> OperationResult<MultipleResult<V>> getValues(JsTableRowValueExtractor<V> extractor);

    <V> OperationResult<MultipleResult<V>> getValues(JsTableRowValueExtractor<V> extractor, JsTableRowFilter filter);

    <V> OperationResult<V> getFooterValue(JsTableRowValueExtractor<V> extractor);

}