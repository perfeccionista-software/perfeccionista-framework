package io.perfeccionista.framework.pagefactory.elements.web;

import io.perfeccionista.framework.pagefactory.elements.methods.availability.ScrollToElementAvailable;
import io.perfeccionista.framework.pagefactory.elements.methods.availability.SizeAvailable;
import io.perfeccionista.framework.pagefactory.itemextractor.js.JsBlockValueExtractor;
import io.perfeccionista.framework.pagefactory.itemfilter.MultipleResult;
import io.perfeccionista.framework.pagefactory.itemfilter.js.JsBlockFilter;
import io.perfeccionista.framework.pagefactory.operations.OperationResult;

public interface WebUnorderedList extends WebChildElement,
        ScrollToElementAvailable<JsBlockFilter>, SizeAvailable {

    <V> OperationResult<MultipleResult<V>> getValues(JsBlockValueExtractor<V> extractor);

    <V> OperationResult<MultipleResult<V>> getValues(JsBlockValueExtractor<V> extractor, JsBlockFilter filter);

}
