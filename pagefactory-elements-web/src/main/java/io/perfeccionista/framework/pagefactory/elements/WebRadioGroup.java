package io.perfeccionista.framework.pagefactory.elements;

import io.perfeccionista.framework.pagefactory.elements.methods.availability.ScrollToElementAvailable;
import io.perfeccionista.framework.pagefactory.elements.methods.availability.SizeAvailable;
import io.perfeccionista.framework.pagefactory.extractor.JsWebRadioButtonValueExtractor;
import io.perfeccionista.framework.pagefactory.filter.MultipleResult;
import io.perfeccionista.framework.pagefactory.filter.JsRadioButtonFilter;
import io.perfeccionista.framework.pagefactory.operations.OperationResult;

public interface WebRadioGroup extends WebChildElement,
        ScrollToElementAvailable<JsRadioButtonFilter>, SizeAvailable {

    <V> OperationResult<MultipleResult<V>> getValues(JsWebRadioButtonValueExtractor<V> extractor);

    <V> OperationResult<MultipleResult<V>> getValues(JsWebRadioButtonValueExtractor<V> extractor, JsRadioButtonFilter filter);

}
