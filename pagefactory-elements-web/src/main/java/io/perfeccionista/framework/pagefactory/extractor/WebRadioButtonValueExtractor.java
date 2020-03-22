package io.perfeccionista.framework.pagefactory.extractor;

import io.perfeccionista.framework.pagefactory.elements.WebRadioGroup;

public interface WebRadioButtonValueExtractor<V> extends WebValueExtractor<WebRadioGroup, V> {

    WebRadioButtonValueExtractor<V> setHash(String hash);

}
