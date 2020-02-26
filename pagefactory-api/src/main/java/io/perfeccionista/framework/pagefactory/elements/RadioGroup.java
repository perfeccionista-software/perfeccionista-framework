package io.perfeccionista.framework.pagefactory.elements;

import io.perfeccionista.framework.pagefactory.elements.methods.availability.ScrollToElementAvailable;
import io.perfeccionista.framework.pagefactory.elements.methods.availability.SizeAvailable;
import io.perfeccionista.framework.pagefactory.itemextractor.JsWebRadioButtonValueExtractor;
import io.perfeccionista.framework.pagefactory.itemfilter.Filter;
import io.perfeccionista.framework.pagefactory.itemfilter.MultipleResult;
import io.perfeccionista.framework.pagefactory.operations.OperationResult;

public interface RadioGroup<F extends Filter<?, ?>> extends SizeAvailable, ScrollToElementAvailable<F> {

    <V> OperationResult<MultipleResult<V>> getValues(JsWebRadioButtonValueExtractor<V> extractor);

    <V> OperationResult<MultipleResult<V>> getValues(JsWebRadioButtonValueExtractor<V> extractor, F filter);

}
