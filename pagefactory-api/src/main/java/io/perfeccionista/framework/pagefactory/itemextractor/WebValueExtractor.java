package io.perfeccionista.framework.pagefactory.itemextractor;

import io.perfeccionista.framework.pagefactory.elements.web.WebChildElement;

import java.util.Collection;

public interface WebValueExtractor<T extends WebChildElement, V> extends ValueExtractor<T, Collection<Integer>, V> {
}
