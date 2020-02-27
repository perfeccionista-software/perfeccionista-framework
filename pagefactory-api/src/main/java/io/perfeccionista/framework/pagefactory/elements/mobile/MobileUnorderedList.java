package io.perfeccionista.framework.pagefactory.elements.mobile;

import io.perfeccionista.framework.pagefactory.elements.methods.availability.SizeAvailable;
import io.perfeccionista.framework.pagefactory.elements.methods.availability.SwipeToElementAvailable;
import io.perfeccionista.framework.pagefactory.itemextractor.appium.AppiumBlockValueExtractor;
import io.perfeccionista.framework.pagefactory.itemfilter.MultipleResult;
import io.perfeccionista.framework.pagefactory.itemfilter.appium.AppiumBlockFilter;
import io.perfeccionista.framework.pagefactory.operations.OperationResult;

public interface MobileUnorderedList extends MobileChildElement,
        SwipeToElementAvailable<AppiumBlockFilter>, SizeAvailable {

    <V> OperationResult<MultipleResult<V>> getValues(AppiumBlockValueExtractor<V> extractor);

    <V> OperationResult<MultipleResult<V>> getValues(AppiumBlockValueExtractor<V> extractor, AppiumBlockFilter filter);

}
