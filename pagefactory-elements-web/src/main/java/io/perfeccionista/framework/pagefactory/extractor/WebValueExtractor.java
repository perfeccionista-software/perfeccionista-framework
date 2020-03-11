package io.perfeccionista.framework.pagefactory.extractor;

import io.perfeccionista.framework.pagefactory.elements.WebChildElement;
import io.perfeccionista.framework.pagefactory.filter.MultipleResult;

public interface WebValueExtractor<T extends WebChildElement, V> extends ValueExtractor<T, MultipleResult<Integer>, V> {
}
