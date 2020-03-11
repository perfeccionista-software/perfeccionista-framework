package io.perfeccionista.framework.pagefactory.extractor;

import io.perfeccionista.framework.pagefactory.elements.MobileUnorderedList;

public interface AppiumBlockValueExtractor<V> extends AppiumValueExtractor<MobileUnorderedList, V> {

    AppiumBlockValueExtractor<V> setHash(String hash);

}
