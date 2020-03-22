package io.perfeccionista.framework.pagefactory.extractor;

import io.perfeccionista.framework.pagefactory.elements.WebChildElement;
import io.perfeccionista.framework.pagefactory.filter.MultipleResult;
import io.perfeccionista.framework.pagefactory.filter.SingleResult;

public interface WebValueExtractor<T extends WebChildElement, V> extends ValueExtractor<T, SingleResult<Integer>, MultipleResult<Integer>, V> {
}
