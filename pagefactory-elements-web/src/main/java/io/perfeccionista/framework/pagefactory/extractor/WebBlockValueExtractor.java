package io.perfeccionista.framework.pagefactory.extractor;

import io.perfeccionista.framework.pagefactory.elements.WebUnorderedList;

public interface WebBlockValueExtractor<V> extends WebValueExtractor<WebUnorderedList, V> {

    WebBlockValueExtractor<V> setHash(String hash);

}
