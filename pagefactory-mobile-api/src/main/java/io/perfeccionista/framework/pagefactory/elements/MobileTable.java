package io.perfeccionista.framework.pagefactory.elements;

import io.perfeccionista.framework.pagefactory.elements.mapping.MobileColumnMapper;
import io.perfeccionista.framework.pagefactory.elements.methods.SizeAvailable;

import java.util.Optional;

public interface MobileTable extends MobileChildElement, SizeAvailable {

    Optional<MobileColumnMapper> getColumnMapper(String columnName);

//    <V> OperationResult<V> getHeaderValue(AppiumTableRowValueExtractor<V> extractor);
//
//    <V> OperationResult<MultipleResult<V>> getValues(AppiumTableRowValueExtractor<V> extractor);
//
//    <V> OperationResult<MultipleResult<V>> getValues(AppiumTableRowValueExtractor<V> extractor, AppiumTableRowFilter filter);
//
//    <V> OperationResult<V> getFooterValue(AppiumTableRowValueExtractor<V> extractor);

}
