package io.perfeccionista.framework.pagefactory.elements.states;

import io.perfeccionista.framework.pagefactory.operations.OperationResult;

import java.util.Optional;

/**
 * TODO JavaDoc
 */
public interface ElementStateAvailable {

    OperationResult<Boolean> isStateDisplayed(String stateName);

    Optional<ElementStateHolder> getState(String stateName);

}
