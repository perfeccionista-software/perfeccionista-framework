package io.perfeccionista.framework.pagefactory.elements;

import io.perfeccionista.framework.pagefactory.elements.methods.availability.SizeAvailable;
import io.perfeccionista.framework.pagefactory.elements.methods.availability.SwipeToElementAvailable;
import io.perfeccionista.framework.pagefactory.extractor.AppiumRadioButtonValueExtractor;
import io.perfeccionista.framework.pagefactory.filter.MultipleResult;
import io.perfeccionista.framework.pagefactory.filter.AppiumRadioButtonFilter;
import io.perfeccionista.framework.pagefactory.operations.OperationResult;

public interface MobileRadioGroup extends MobileChildElement,
        SwipeToElementAvailable<AppiumRadioButtonFilter>, SizeAvailable {

    <V> OperationResult<MultipleResult<V>> getValues(AppiumRadioButtonValueExtractor<V> extractor);

    <V> OperationResult<MultipleResult<V>> getValues(AppiumRadioButtonValueExtractor<V> extractor, AppiumRadioButtonFilter filter);

}
