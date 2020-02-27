package io.perfeccionista.framework.pagefactory.itemextractor.appium;

import io.perfeccionista.framework.pagefactory.elements.mobile.MobileRadioGroup;

public interface AppiumRadioButtonValueExtractor<V> extends AppiumValueExtractor<MobileRadioGroup, V> {

    AppiumRadioButtonValueExtractor<V> setHash(String hash);

}
