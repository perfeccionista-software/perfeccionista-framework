package io.perfeccionista.framework.pagefactory.extractor;

import io.perfeccionista.framework.pagefactory.elements.WebList;

public interface WebListBlockValueExtractor<V> extends WebValueExtractor<WebList, V> {

    WebListBlockValueExtractor<V> setHash(String hash);

}
