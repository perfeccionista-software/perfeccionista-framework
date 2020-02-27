package io.perfeccionista.framework.pagefactory.itemextractor.js;

import io.perfeccionista.framework.pagefactory.elements.web.WebChildElement;
import io.perfeccionista.framework.pagefactory.itemextractor.ValueExtractor;

import java.util.Collection;

public interface WebValueExtractor<T extends WebChildElement, V> extends ValueExtractor<T, Collection<Integer>, V> {
}
