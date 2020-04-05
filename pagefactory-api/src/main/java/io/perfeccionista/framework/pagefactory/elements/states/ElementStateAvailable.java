package io.perfeccionista.framework.pagefactory.elements.states;

import io.perfeccionista.framework.pagefactory.elements.locators.LocatorHolder;
import io.perfeccionista.framework.pagefactory.operations.OperationResult;

import java.util.Optional;

/**
 * TODO JavaDoc
 * StateName = Locator.Component
 */
public interface ElementStateAvailable {

    Optional<LocatorHolder> getState(String stateName);

    boolean isStateDisplayed(String stateName);

    ElementStateAvailable stateShouldBeDisplayed(String stateName);

}
