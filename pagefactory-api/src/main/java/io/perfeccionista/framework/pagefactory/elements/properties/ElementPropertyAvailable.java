package io.perfeccionista.framework.pagefactory.elements.properties;

import io.perfeccionista.framework.value.string.StringValue;

import java.util.Optional;

/**
 * TODO JavaDoc
 */
public interface ElementPropertyAvailable<T extends ElementPropertyHolder> {

    Optional<T> getProperty(String propertyName);

    String getPropertyValue(String propertyName);

    ElementPropertyAvailable<T> shouldHavePropertyValue(String propertyValue, StringValue stringValue);

}
