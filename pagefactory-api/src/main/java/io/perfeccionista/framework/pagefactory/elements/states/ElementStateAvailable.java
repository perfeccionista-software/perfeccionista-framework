package io.perfeccionista.framework.pagefactory.elements.states;

import io.perfeccionista.framework.pagefactory.elements.locators.LocatorHolder;
import io.perfeccionista.framework.pagefactory.operations.OperationResult;

import java.util.Optional;

/**
 * TODO JavaDoc
 * StateName = Locator.Component
 */
public interface ElementStateAvailable {

    OperationResult<Boolean> isStateDisplayed(String stateName);

    Optional<LocatorHolder> getState(String stateName);

}
