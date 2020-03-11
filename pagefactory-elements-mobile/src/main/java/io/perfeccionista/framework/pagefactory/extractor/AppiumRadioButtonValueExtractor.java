package io.perfeccionista.framework.pagefactory.extractor;

import io.perfeccionista.framework.pagefactory.elements.MobileRadioGroup;

public interface AppiumRadioButtonValueExtractor<V> extends AppiumValueExtractor<MobileRadioGroup, V> {

    AppiumRadioButtonValueExtractor<V> setHash(String hash);

}
