package io.perfeccionista.framework.pagefactory.itemextractor.appium;

import io.perfeccionista.framework.pagefactory.elements.mobile.MobileChildElement;
import io.perfeccionista.framework.pagefactory.itemextractor.ValueExtractor;

import java.util.Collection;

public interface AppiumValueExtractor<T extends MobileChildElement, V> extends ValueExtractor<T, Collection<Integer>, V> {
}
