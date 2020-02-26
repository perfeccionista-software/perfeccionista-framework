package io.perfeccionista.framework.pagefactory.itemextractor;

import io.perfeccionista.framework.pagefactory.elements.web.WebRadioGroup;

public interface JsWebRadioButtonValueExtractor<V> extends WebValueExtractor<WebRadioGroup, V> {

    JsWebRadioButtonValueExtractor<V> setHash(String hash);

}
