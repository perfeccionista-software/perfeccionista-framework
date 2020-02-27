package io.perfeccionista.framework.pagefactory.itemextractor.js;

import io.perfeccionista.framework.pagefactory.elements.web.WebUnorderedList;

public interface JsBlockValueExtractor<V> extends WebValueExtractor<WebUnorderedList, V> {

    JsBlockValueExtractor<V> setHash(String hash);

}
