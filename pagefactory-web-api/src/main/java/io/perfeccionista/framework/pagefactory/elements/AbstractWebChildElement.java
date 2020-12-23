package io.perfeccionista.framework.pagefactory.elements;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.perfeccionista.framework.exceptions.WebElementInteractionNotFound;
import io.perfeccionista.framework.exceptions.WebElementPropertyNotFound;
import io.perfeccionista.framework.matcher.actions.GetColorAvailableMatcher;
import io.perfeccionista.framework.matcher.actions.GetDimensionsAvailableMatcher;
import io.perfeccionista.framework.matcher.actions.GetLocationAvailableMatcher;
import io.perfeccionista.framework.matcher.actions.GetScreenshotAvailableMatcher;
import io.perfeccionista.framework.matcher.actions.IsDisplayedAvailableMatcher;
import io.perfeccionista.framework.matcher.actions.IsInFocusAvailableMatcher;
import io.perfeccionista.framework.matcher.actions.IsOnTheScreenAvailableMatcher;
import io.perfeccionista.framework.matcher.actions.IsPresentAvailableMatcher;
import io.perfeccionista.framework.matcher.element.WebChildElementMatcher;
import io.perfeccionista.framework.matcher.actions.WebComponentAvailableMatcher;
import io.perfeccionista.framework.matcher.actions.WebElementPropertyAvailableMatcher;
import io.perfeccionista.framework.pagefactory.elements.base.WebChildElement;
import io.perfeccionista.framework.measurements.Dimensions;
import io.perfeccionista.framework.measurements.Location;
import io.perfeccionista.framework.pagefactory.elements.interactions.base.WebElementInteractionImplementation;
import io.perfeccionista.framework.pagefactory.elements.interactions.base.WebElementInteractionRegistry;
import io.perfeccionista.framework.pagefactory.elements.properties.base.WebElementPropertyHolder;
import io.perfeccionista.framework.pagefactory.elements.properties.base.WebElementPropertyRegistry;
import io.perfeccionista.framework.screenshots.Screenshot;
import io.perfeccionista.framework.color.Color;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

import static io.perfeccionista.framework.exceptions.messages.PageFactoryWebApiMessages.ELEMENT_INTERACTION_NOT_FOUND;
import static io.perfeccionista.framework.exceptions.messages.PageFactoryWebApiMessages.ELEMENT_PROPERTY_NOT_FOUND;
import static io.perfeccionista.framework.invocation.runner.InvocationName.actionInvocation;
import static io.perfeccionista.framework.invocation.runner.InvocationName.getterInvocation;
import static io.perfeccionista.framework.invocation.wrappers.CheckInvocationWrapper.runCheck;
import static io.perfeccionista.framework.pagefactory.elements.components.WebComponents.DISPLAYED;
import static io.perfeccionista.framework.pagefactory.elements.components.WebComponents.PRESENTED;
import static io.perfeccionista.framework.pagefactory.elements.actions.WebElementActionNames.EXECUTE_INTERACTION;
import static io.perfeccionista.framework.pagefactory.elements.actions.WebElementActionNames.GET_COLOR_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.actions.WebElementActionNames.GET_DIMENSIONS_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.actions.WebElementActionNames.GET_LOCATION_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.actions.WebElementActionNames.GET_PROPERTY_VALUE_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.actions.WebElementActionNames.GET_SCREENSHOT_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.actions.WebElementActionNames.HOVER_TO_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.actions.WebElementActionNames.IS_COMPONENT_DISPLAYED_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.actions.WebElementActionNames.IS_COMPONENT_PRESENT_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.actions.WebElementActionNames.IS_DISPLAYED_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.actions.WebElementActionNames.IS_IN_FOCUS_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.actions.WebElementActionNames.IS_ON_THE_SCREEN_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.actions.WebElementActionNames.IS_PRESENT_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.actions.WebElementActionNames.SCROLL_TO_METHOD;

// TODO: В конфигурации сделать возможность устанавливать настройки элемента параметром
//  config.put(WebButton.class, new WebButtonImpl().withElementAction(SCROLL_TO_METHOD, JsScrollToWithDelay.class)
// TODO: Привести во всех имплементациях элементов и методов component -> componentName
// TODO: Привести к WebChildElement возвращаемые типы и учесть это в обработчике прокси.
public class AbstractWebChildElement extends AbstractWebChildElementBase implements WebChildElement {

    protected WebElementPropertyRegistry propertyRegistry;
    protected WebElementInteractionRegistry interactionRegistry;

    // Actions

    @Override
    public @NotNull WebElementInteractionImplementation getInteractionImplementation(@NotNull String interactionName) {
        return interactionRegistry.getInteraction(interactionName)
                .orElseThrow(() -> WebElementInteractionNotFound.exception(ELEMENT_INTERACTION_NOT_FOUND.getMessage(interactionName)));
    }

    public WebChildElement executeInteraction(@NotNull String name, @NotNull WebChildElement other, Object... args) {
        runCheck(getEnvironment(), actionInvocation(EXECUTE_INTERACTION, this, name, other, args), () -> {
            getInteractionImplementation(name)
                    .execute(this, other, args);
        });
        return this;
    }

    @Override
    public WebChildElement executeAction(@NotNull String name, Object... args) {
        super.executeAction(name, args);
        return this;
    }

    // Asserts

    @Override
    public WebChildElement should(@NotNull WebChildElementMatcher matcher) {
        matcher.check(this);
        return this;
    }

    @Override
    public WebChildElement should(@NotNull GetColorAvailableMatcher matcher) {
        matcher.check(this);
        return this;
    }

    @Override
    public WebChildElement should(@NotNull GetDimensionsAvailableMatcher matcher) {
        matcher.check(this);
        return this;
    }

    @Override
    public WebChildElement should(@NotNull GetLocationAvailableMatcher matcher) {
        matcher.check(this);
        return this;
    }

    @Override
    public WebChildElement should(@NotNull GetScreenshotAvailableMatcher matcher) {
        matcher.check(this);
        return this;
    }

    @Override
    public WebChildElement should(@NotNull IsDisplayedAvailableMatcher matcher) {
        matcher.check(this);
        return this;
    }

    @Override
    public WebChildElement should(@NotNull IsInFocusAvailableMatcher matcher) {
        matcher.check(this);
        return this;
    }

    @Override
    public WebChildElement should(@NotNull IsOnTheScreenAvailableMatcher matcher) {
        matcher.check(this);
        return this;
    }

    @Override
    public WebChildElement should(@NotNull IsPresentAvailableMatcher matcher) {
        matcher.check(this);
        return this;
    }

    @Override
    public WebChildElement should(@NotNull WebComponentAvailableMatcher matcher) {
        matcher.check(this);
        return this;
    }

    @Override
    public WebChildElement should(@NotNull WebElementPropertyAvailableMatcher matcher) {
        matcher.check(this);
        return this;
    }

    // Get Color

    @Override
    public @NotNull Color getColor(@NotNull String componentName, @NotNull String cssProperty) {
        return runCheck(getEnvironment(), getterInvocation(GET_COLOR_METHOD, this, componentName, cssProperty),
                () -> getActionImplementation(GET_COLOR_METHOD, Color.class).execute(this, componentName, cssProperty));
    }

    // Get Dimensions

    @Override
    public @NotNull Dimensions getDimensions(@NotNull String componentName) {
        return runCheck(getEnvironment(), getterInvocation(GET_DIMENSIONS_METHOD, this, componentName),
                () -> getActionImplementation(GET_DIMENSIONS_METHOD, Dimensions.class).execute(this, componentName));
    }

    // Get Location

    @Override
    public @NotNull Location getLocation(@NotNull String componentName) {
        return runCheck(getEnvironment(), getterInvocation(GET_LOCATION_METHOD, this, componentName),
                () -> getActionImplementation(GET_LOCATION_METHOD, Location.class).execute(this, componentName));
    }

    // Get Screenshot

    @Override
    public @NotNull Screenshot getScreenshot(@NotNull String componentName) {
        return runCheck(getEnvironment(), getterInvocation(GET_SCREENSHOT_METHOD, this, componentName),
                () -> getActionImplementation(GET_SCREENSHOT_METHOD, Screenshot.class).execute(this, componentName));
    }

    // HoverTo

    @Override
    public WebChildElement hoverTo(boolean withOutOfBounds) {
        runCheck(getEnvironment(), actionInvocation(HOVER_TO_METHOD, this, withOutOfBounds),
                () -> getActionImplementation(HOVER_TO_METHOD, Void.class).execute(this, withOutOfBounds));
        return this;
    }

    // IsDisplayed

    @Override
    public boolean isDisplayed() {
        return runCheck(getEnvironment(), getterInvocation(IS_DISPLAYED_METHOD, this),
                () -> getActionImplementation(IS_DISPLAYED_METHOD, Boolean.class).execute(this, DISPLAYED));
    }

    // IsInFocus

    @Override
    public boolean isInFocus() {
        return runCheck(getEnvironment(), getterInvocation(IS_IN_FOCUS_METHOD, this),
                () -> getActionImplementation(IS_IN_FOCUS_METHOD, Boolean.class).execute(this));
    }

    // IsOnTheScreen

    @Override
    public boolean isOnTheScreen() {
        return runCheck(getEnvironment(), getterInvocation(IS_ON_THE_SCREEN_METHOD, this),
                () -> getActionImplementation(IS_ON_THE_SCREEN_METHOD, Boolean.class).execute(this));
    }

    // IsPresent

    @Override
    public boolean isPresent() {
        return runCheck(getEnvironment(), getterInvocation(IS_PRESENT_METHOD, this),
                () -> getActionImplementation(IS_PRESENT_METHOD, Boolean.class).execute(this, PRESENTED));
    }

    // ScrollTo

    @Override
    public WebChildElement scrollTo() {
        runCheck(getEnvironment(), actionInvocation(SCROLL_TO_METHOD, this),
                () -> getActionImplementation(SCROLL_TO_METHOD, Void.class).execute(this));
        return this;
    }

    // WebComponent

    @Override
    public boolean isComponentPresent(@NotNull String componentName) {
        return runCheck(getEnvironment(), getterInvocation(IS_COMPONENT_PRESENT_METHOD, this, componentName),
                () -> getActionImplementation(IS_COMPONENT_PRESENT_METHOD, Boolean.class).execute(this, componentName));
    }

    @Override
    public boolean isComponentDisplayed(@NotNull String componentName) {
        return runCheck(getEnvironment(), getterInvocation(IS_COMPONENT_DISPLAYED_METHOD, this, componentName),
                () -> getActionImplementation(IS_COMPONENT_DISPLAYED_METHOD, Boolean.class).execute(this, componentName));
    }

    // WebProperty

    @Override
    public Optional<WebElementPropertyHolder> getProperty(String propertyName) {
        return propertyRegistry.getProperty(propertyName);
    }

    @Override
    public @Nullable String getPropertyValue(@NotNull String propertyName) {
        WebElementPropertyHolder propertyHolder = getProperty(propertyName)
                .orElseThrow(() -> WebElementPropertyNotFound.exception(ELEMENT_PROPERTY_NOT_FOUND.getMessage(propertyName)));
        return runCheck(getEnvironment(), getterInvocation(GET_PROPERTY_VALUE_METHOD, this, propertyName),
                () -> getActionImplementation(GET_PROPERTY_VALUE_METHOD, String.class).execute(this, propertyHolder));
    }

    @Override
    public @NotNull JsonNode toJson() {
        ObjectNode rootNode = (ObjectNode) super.toJson();
        rootNode.set("interactions", this.interactionRegistry.toJson());
        rootNode.set("properties", this.propertyRegistry.toJson());
        return rootNode;
    }

}
