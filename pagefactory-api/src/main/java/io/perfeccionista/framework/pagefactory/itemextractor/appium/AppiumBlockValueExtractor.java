package io.perfeccionista.framework.pagefactory.itemextractor.appium;

import io.perfeccionista.framework.pagefactory.elements.mobile.MobileUnorderedList;

public interface AppiumBlockValueExtractor<V> extends AppiumValueExtractor<MobileUnorderedList, V> {

    AppiumBlockValueExtractor<V> setHash(String hash);

}
