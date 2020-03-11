package io.perfeccionista.framework.pagefactory.elements;

import io.perfeccionista.framework.pagefactory.elements.methods.availability.ScrollToElementAvailable;
import io.perfeccionista.framework.pagefactory.elements.methods.availability.SizeAvailable;
import io.perfeccionista.framework.pagefactory.extractor.JsBlockValueExtractor;
import io.perfeccionista.framework.pagefactory.filter.MultipleResult;
import io.perfeccionista.framework.pagefactory.filter.JsBlockFilter;
import io.perfeccionista.framework.pagefactory.operations.OperationResult;

public interface WebUnorderedList extends WebChildElement,
        ScrollToElementAvailable<JsBlockFilter>, SizeAvailable {

    <V> OperationResult<MultipleResult<V>> getValues(JsBlockValueExtractor<V> extractor);

    <V> OperationResult<MultipleResult<V>> getValues(JsBlockValueExtractor<V> extractor, JsBlockFilter filter);

}
