package io.perfeccionista.framework.pagefactory.elements.mobile;

import io.perfeccionista.framework.pagefactory.elements.mapping.MobileColumnMapper;
import io.perfeccionista.framework.pagefactory.elements.methods.availability.SizeAvailable;
import io.perfeccionista.framework.pagefactory.elements.methods.availability.SwipeToElementAvailable;
import io.perfeccionista.framework.pagefactory.itemextractor.appium.AppiumTableRowValueExtractor;
import io.perfeccionista.framework.pagefactory.itemfilter.MultipleResult;
import io.perfeccionista.framework.pagefactory.itemfilter.appium.AppiumTableRowFilter;
import io.perfeccionista.framework.pagefactory.operations.OperationResult;

import java.util.Optional;

public interface MobileTable extends MobileChildElement,
        SwipeToElementAvailable<AppiumTableRowFilter>, SizeAvailable {

    Optional<MobileColumnMapper> getColumnMapper(String columnName);

    // TODO: Параметризовать экстракторы
    <V> OperationResult<V> getHeaderValue(AppiumTableRowValueExtractor<V> extractor);

    <V> OperationResult<MultipleResult<V>> getValues(AppiumTableRowValueExtractor<V> extractor);

    <V> OperationResult<MultipleResult<V>> getValues(AppiumTableRowValueExtractor<V> extractor, AppiumTableRowFilter filter);

    <V> OperationResult<V> getFooterValue(AppiumTableRowValueExtractor<V> extractor);

}
