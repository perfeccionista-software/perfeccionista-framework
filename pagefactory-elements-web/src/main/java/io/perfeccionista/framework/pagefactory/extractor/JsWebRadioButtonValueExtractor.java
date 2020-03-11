package io.perfeccionista.framework.pagefactory.extractor;

import io.perfeccionista.framework.pagefactory.elements.WebRadioGroup;

public interface JsWebRadioButtonValueExtractor<V> extends WebValueExtractor<WebRadioGroup, V> {

    JsWebRadioButtonValueExtractor<V> setHash(String hash);

}
