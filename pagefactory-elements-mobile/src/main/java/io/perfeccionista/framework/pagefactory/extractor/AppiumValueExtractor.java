package io.perfeccionista.framework.pagefactory.extractor;

import io.appium.java_client.MobileElement;
import io.perfeccionista.framework.pagefactory.elements.MobileChildElement;
import io.perfeccionista.framework.pagefactory.filter.MultipleResult;
import io.perfeccionista.framework.pagefactory.filter.SingleResult;

public interface AppiumValueExtractor<T extends MobileChildElement, V> extends ValueExtractor<T, SingleResult<MobileElement>, MultipleResult<MobileElement>, V> {
}
