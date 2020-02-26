package io.perfeccionista.framework.pagefactory.itemextractor;

import io.perfeccionista.framework.pagefactory.elements.web.WebUnorderedList;

public interface JsWebBlockValueExtractor<V> extends WebValueExtractor<WebUnorderedList, V> {

    JsWebBlockValueExtractor<V> setHash(String hash);

}
