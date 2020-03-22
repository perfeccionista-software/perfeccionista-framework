package io.perfeccionista.framework.pagefactory.elements.properties;

import java.util.Optional;

/**
 * TODO JavaDoc
 */
public interface ElementPropertyAvailable<T extends ElementPropertyHolder> {

    String getPropertyValue(String propertyName);

    Optional<T> getProperty(String propertyName);

}
