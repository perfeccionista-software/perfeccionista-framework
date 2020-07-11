package io.perfeccionista.framework.pagefactory.elements.methods;

import io.perfeccionista.framework.pagefactory.elements.actions.MappedElementAction;
import io.perfeccionista.framework.pagefactory.elements.base.WebChildElement;
import io.perfeccionista.framework.pagefactory.elements.base.WebLocatorChainAvailable;
import io.perfeccionista.framework.pagefactory.elements.locators.WebLocatorHolder;
import io.perfeccionista.framework.plugin.AssertMethodType;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;

import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.COMPONENT_SHOULD_BE_DISPLAYED_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.COMPONENT_SHOULD_BE_PRESENT_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.COMPONENT_SHOULD_NOT_BE_DISPLAYED_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.COMPONENT_SHOULD_NOT_BE_PRESENT_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.IS_COMPONENT_DISPLAYED_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.IS_COMPONENT_PRESENT_METHOD;

/**
 * TODO JavaDoc
 */
public interface WebComponentAvailable extends WebLocatorChainAvailable {

    Optional<WebLocatorHolder> getLocator(@NotNull String componentName);

    @MappedElementAction(IS_COMPONENT_PRESENT_METHOD)
    boolean isComponentPresent(@NotNull String componentName);

    @MappedElementAction(IS_COMPONENT_DISPLAYED_METHOD)
    boolean isComponentDisplayed(@NotNull String componentName);

    @AssertMethodType
    @MappedElementAction(COMPONENT_SHOULD_BE_PRESENT_METHOD)
    WebChildElement componentShouldBePresent(@NotNull String componentName);

    @AssertMethodType
    @MappedElementAction(COMPONENT_SHOULD_NOT_BE_PRESENT_METHOD)
    WebChildElement componentShouldNotBePresent(@NotNull String componentName);

    @AssertMethodType
    @MappedElementAction(COMPONENT_SHOULD_BE_DISPLAYED_METHOD)
    WebChildElement componentShouldBeDisplayed(@NotNull String componentName);

    @AssertMethodType
    @MappedElementAction(COMPONENT_SHOULD_NOT_BE_DISPLAYED_METHOD)
    WebChildElement componentShouldNotBeDisplayed(@NotNull String componentName);

}
