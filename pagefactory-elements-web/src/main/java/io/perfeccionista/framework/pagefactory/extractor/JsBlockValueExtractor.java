package io.perfeccionista.framework.pagefactory.extractor;

import io.perfeccionista.framework.pagefactory.elements.WebUnorderedList;

public interface JsBlockValueExtractor<V> extends WebValueExtractor<WebUnorderedList, V> {

    JsBlockValueExtractor<V> setHash(String hash);

}
