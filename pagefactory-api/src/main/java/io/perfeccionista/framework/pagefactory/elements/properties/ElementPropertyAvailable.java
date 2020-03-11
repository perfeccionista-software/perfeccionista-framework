package io.perfeccionista.framework.pagefactory.elements.properties;

import io.perfeccionista.framework.pagefactory.operations.OperationResult;

import java.util.Optional;

/**
 * TODO JavaDoc
 */
public interface ElementPropertyAvailable<T extends ElementPropertyHolder> {

    OperationResult<String> getPropertyValue(String propertyName);

    Optional<T> getProperty(String propertyName);

}
