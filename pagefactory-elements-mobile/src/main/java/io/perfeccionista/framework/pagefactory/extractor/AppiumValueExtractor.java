package io.perfeccionista.framework.pagefactory.extractor;

import io.appium.java_client.MobileElement;
import io.perfeccionista.framework.pagefactory.elements.MobileChildElement;
import io.perfeccionista.framework.pagefactory.filter.MultipleResult;

public interface AppiumValueExtractor<T extends MobileChildElement, V> extends ValueExtractor<T, MultipleResult<MobileElement>, V> {
}
