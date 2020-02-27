package io.perfeccionista.framework.pagefactory.itemextractor.js;

import io.perfeccionista.framework.pagefactory.elements.web.WebRadioGroup;

public interface JsWebRadioButtonValueExtractor<V> extends WebValueExtractor<WebRadioGroup, V> {

    JsWebRadioButtonValueExtractor<V> setHash(String hash);

}
