package io.perfeccionista.framework.pagefactory.elements.components;

import io.perfeccionista.framework.pagefactory.elements.actions.MappedElementAction;
import io.perfeccionista.framework.pagefactory.elements.locators.LocatorHolder;
import io.perfeccionista.framework.plugin.AssertMethodType;

import java.util.Optional;

import static io.perfeccionista.framework.pagefactory.elements.methods.AvailableElementMethods.COMPONENT_SHOULD_BE_DISPLAYED_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.AvailableElementMethods.COMPONENT_SHOULD_BE_PRESENT_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.AvailableElementMethods.COMPONENT_SHOULD_NOT_BE_DISPLAYED_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.AvailableElementMethods.COMPONENT_SHOULD_NOT_BE_PRESENT_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.AvailableElementMethods.IS_COMPONENT_DISPLAYED_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.AvailableElementMethods.IS_COMPONENT_PRESENT_METHOD;

/**
 * TODO JavaDoc
 */
public interface ElementComponentAvailable {

    Optional<LocatorHolder> getComponent(String componentName);

    @MappedElementAction(IS_COMPONENT_DISPLAYED_METHOD)
    boolean isComponentDisplayed(String componentName);

    @MappedElementAction(IS_COMPONENT_PRESENT_METHOD)
    boolean isComponentPresent(String componentName);

    @AssertMethodType
    @MappedElementAction(COMPONENT_SHOULD_BE_DISPLAYED_METHOD)
    ElementComponentAvailable componentShouldBeDisplayed(String componentName);

    @AssertMethodType
    @MappedElementAction(COMPONENT_SHOULD_NOT_BE_DISPLAYED_METHOD)
    ElementComponentAvailable componentShouldNotBeDisplayed(String componentName);

    @AssertMethodType
    @MappedElementAction(COMPONENT_SHOULD_BE_PRESENT_METHOD)
    ElementComponentAvailable componentShouldBePresent(String componentName);

    @AssertMethodType
    @MappedElementAction(COMPONENT_SHOULD_NOT_BE_PRESENT_METHOD)
    ElementComponentAvailable componentShouldNotBePresent(String componentName);

}
