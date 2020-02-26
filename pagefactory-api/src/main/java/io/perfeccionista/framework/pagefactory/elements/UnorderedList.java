package io.perfeccionista.framework.pagefactory.elements;

import io.perfeccionista.framework.pagefactory.elements.methods.availability.ScrollToElementAvailable;
import io.perfeccionista.framework.pagefactory.elements.methods.availability.SizeAvailable;
import io.perfeccionista.framework.pagefactory.itemextractor.JsWebBlockValueExtractor;
import io.perfeccionista.framework.pagefactory.itemfilter.Filter;
import io.perfeccionista.framework.pagefactory.itemfilter.MultipleResult;
import io.perfeccionista.framework.pagefactory.operations.OperationResult;

public interface UnorderedList<F extends Filter<?, ?>> extends SizeAvailable, ScrollToElementAvailable<F> {

    <V> OperationResult<MultipleResult<V>> getValues(JsWebBlockValueExtractor<V> extractor);

    <V> OperationResult<MultipleResult<V>> getValues(JsWebBlockValueExtractor<V> extractor, F filter);

}