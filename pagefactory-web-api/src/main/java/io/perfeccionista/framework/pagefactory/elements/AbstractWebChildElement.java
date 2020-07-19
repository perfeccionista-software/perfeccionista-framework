package io.perfeccionista.framework.pagefactory.elements;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.perfeccionista.framework.asserts.WebAssertCondition;
import io.perfeccionista.framework.exceptions.ElementActionNotDeclaredException;
import io.perfeccionista.framework.exceptions.ElementInteractionNotDeclaredException;
import io.perfeccionista.framework.exceptions.ElementPropertyNotDeclaredException;
import io.perfeccionista.framework.invocation.runner.InvocationName;
import io.perfeccionista.framework.name.WebElementIdentifier;
import io.perfeccionista.framework.pagefactory.elements.actions.WebElementActionImplementation;
import io.perfeccionista.framework.pagefactory.elements.actions.WebElementActionRegistry;
import io.perfeccionista.framework.pagefactory.elements.base.WebChildElement;
import io.perfeccionista.framework.pagefactory.elements.interactions.WebElementInteractionImplementation;
import io.perfeccionista.framework.pagefactory.elements.interactions.WebElementInteractionRegistry;
import io.perfeccionista.framework.pagefactory.elements.locators.WebLocatorChain;
import io.perfeccionista.framework.pagefactory.elements.locators.WebLocatorHolder;
import io.perfeccionista.framework.pagefactory.elements.methods.Dimensions;
import io.perfeccionista.framework.pagefactory.elements.methods.Location;
import io.perfeccionista.framework.pagefactory.elements.properties.WebElementPropertyHolder;
import io.perfeccionista.framework.pagefactory.elements.properties.WebElementPropertyRegistry;
import io.perfeccionista.framework.pagefactory.screenshots.Screenshot;
import io.perfeccionista.framework.plugin.Color;
import io.perfeccionista.framework.value.number.NumberValue;
import io.perfeccionista.framework.value.string.StringValue;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

import static io.perfeccionista.framework.exceptions.messages.PageFactoryMessages.ELEMENT_ACTION_NOT_DECLARED;
import static io.perfeccionista.framework.exceptions.messages.PageFactoryMessages.ELEMENT_INTERACTION_NOT_DECLARED;
import static io.perfeccionista.framework.exceptions.messages.PageFactoryMessages.ELEMENT_PROPERTY_NOT_DECLARED;
import static io.perfeccionista.framework.invocation.wrappers.CheckActionWrapper.runCheck;
import static io.perfeccionista.framework.pagefactory.elements.components.WebComponents.DISPLAYED;
import static io.perfeccionista.framework.pagefactory.elements.components.WebComponents.PRESENTED;
import static io.perfeccionista.framework.pagefactory.elements.components.WebComponents.ROOT;
import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.COMPONENT_SHOULD_BE_DISPLAYED_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.COMPONENT_SHOULD_BE_PRESENT_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.COMPONENT_SHOULD_NOT_BE_DISPLAYED_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.COMPONENT_SHOULD_NOT_BE_PRESENT_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.EXECUTE_ACTION;
import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.EXECUTE_INTERACTION;
import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.GET_COLOR_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.GET_DIMENSIONS_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.GET_LOCATION_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.GET_PROPERTY_VALUE_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.GET_SCREENSHOT_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.HOVER_TO_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.IS_COMPONENT_DISPLAYED_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.IS_COMPONENT_PRESENT_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.IS_DISPLAYED_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.IS_IN_FOCUS_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.IS_PRESENT_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.SCROLL_TO_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.SHOULD_BE_DISPLAYED_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.SHOULD_BE_IN_FOCUS_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.SHOULD_BE_PRESENT_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.SHOULD_HAVE_COLOR_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.SHOULD_HAVE_DIMENSIONS_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.SHOULD_HAVE_LOCATION_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.SHOULD_HAVE_PROPERTY_NUMBER_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.SHOULD_HAVE_PROPERTY_VALUE_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.SHOULD_LOOKS_LIKE_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.SHOULD_NOT_BE_DISPLAYED_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.SHOULD_NOT_BE_IN_FOCUS_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.SHOULD_NOT_BE_PRESENT_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.SHOULD_NOT_HAVE_COLOR_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.SHOULD_NOT_HAVE_DIMENSIONS_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.SHOULD_NOT_HAVE_LOCATION_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.SHOULD_NOT_HAVE_PROPERTY_NUMBER_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.SHOULD_NOT_HAVE_PROPERTY_VALUE_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.SHOULD_NOT_LOOKS_LIKE_METHOD;
import static io.perfeccionista.framework.utils.JsonUtils.createObjectNode;

// TODO: В конфигурации сделать возможность устанавливать настройки элемента параметром
//  config.put(WebButton.class, new WebButtonImpl().withElementAction(SCROLL_TO_METHOD, JsScrollToWithDelay.class)
// TODO: Привести во всех имплементациях элементов и методов component -> componentName
// TODO: Привести к WebChildElement возвращаемые типы и учесть это в обработчике прокси.
public class AbstractWebChildElement extends AbstractBasicWebElement implements WebChildElement {

    protected WebElementInteractionRegistry interactionRegistry;
    protected WebElementPropertyRegistry propertyRegistry;
    protected WebElementActionRegistry actionRegistry;
    protected WebElementIdentifier elementIdentifier;

    public @NotNull WebLocatorChain getLocatorChainTo(@NotNull String locatorName) {
        if (ROOT.equals(locatorName)) {
            return getLocatorChain();
        }
        Optional<WebLocatorHolder> optionalLocator = locatorRegistry.getOptionalLocator(locatorName);
        if (optionalLocator.isPresent()) {
            return getLocatorChain().addLocator(optionalLocator.get());
        }
        return getLocatorChain();
    }

    public @NotNull WebLocatorChain getLocatorChain() {
        return parent.getLocatorChain().addLocator(locatorRegistry.getLocator(ROOT));
    }

    @Override
    public @NotNull WebElementIdentifier getElementIdentifier() {
        return elementIdentifier;
    }

    @Override
    public Optional<WebElementPropertyHolder> getProperty(String propertyName) {
        return propertyRegistry.getProperty(propertyName);
    }

    @Override
    public @NotNull <T> WebElementActionImplementation<T> getActionImplementation(@NotNull String actionName, @NotNull Class<T> returnType) {
        return actionRegistry.getAction(actionName, returnType)
                .orElseThrow(() -> new ElementActionNotDeclaredException(ELEMENT_ACTION_NOT_DECLARED.getMessage(actionName)));
    }

    @Override
    public @NotNull WebElementInteractionImplementation getInteractionImplementation(@NotNull String interactionName) {
        return interactionRegistry.getInteraction(interactionName)
                .orElseThrow(() -> new ElementInteractionNotDeclaredException(ELEMENT_INTERACTION_NOT_DECLARED.getMessage(interactionName)));
    }

    // Actions

    @Override
    public WebChildElement executeAction(@NotNull String name, Object... args) {
        runCheck(getEnvironment(), InvocationName.of(EXECUTE_ACTION, this, name, args), () -> {
            getActionImplementation(name, Void.class)
                    .execute(this, args);
        });
        return this;
    }

    @Override
    public WebChildElement executeInteraction(@NotNull String name, @NotNull WebChildElement other, Object... args) {
        runCheck(getEnvironment(), InvocationName.of(EXECUTE_INTERACTION, this, name, other, args), () -> {
            getInteractionImplementation(name)
                    .execute(this, other, args);
        });
        return this;
    }

    // Asserts

    @Override
    public WebChildElement should(WebAssertCondition assertCondition) {
        return should(assertCondition, InvocationName.empty());
    }

    @Override
    public WebChildElement should(WebAssertCondition assertCondition, InvocationName invocationName) {
        runCheck(getEnvironment(), invocationName, () -> assertCondition.accept(this));
        return this;
    }

    // Color

    @Override
    public @NotNull Color getColor(@NotNull String componentName, @NotNull String cssProperty) {
        return runCheck(getEnvironment(), InvocationName.of(GET_COLOR_METHOD, this, componentName, cssProperty),
                () -> getActionImplementation(GET_COLOR_METHOD, Color.class).execute(this, componentName, cssProperty));
    }

    @Override
    public WebChildElement componentShouldHaveColor(@NotNull String componentName, @NotNull String cssProperty, @NotNull Color expectedColor) {
        runCheck(getEnvironment(), InvocationName.of(SHOULD_HAVE_COLOR_METHOD, this, componentName, cssProperty, expectedColor),
                () -> {
                    Color actualColor = getActionImplementation(GET_COLOR_METHOD, Color.class)
                            .execute(this, componentName, cssProperty);
                    getActionImplementation(SHOULD_HAVE_COLOR_METHOD, Void.class)
                            .execute(this, actualColor, expectedColor, componentName);
                });
        return this;
    }

    @Override
    public WebChildElement componentShouldNotHaveColor(@NotNull String componentName, @NotNull String cssProperty, @NotNull Color expectedColor) {
        runCheck(getEnvironment(), InvocationName.of(SHOULD_NOT_HAVE_COLOR_METHOD, this, componentName, cssProperty, expectedColor),
                () -> {
                    Color actualColor = getActionImplementation(GET_COLOR_METHOD, Color.class)
                            .execute(this, componentName, cssProperty);
                    getActionImplementation(SHOULD_NOT_HAVE_COLOR_METHOD, Void.class)
                            .execute(this, actualColor, expectedColor, componentName);
                });
        return this;
    }

    // Dimensions

    @Override
    public @NotNull Dimensions getDimensions(@NotNull String componentName) {
        return runCheck(getEnvironment(), InvocationName.of(GET_DIMENSIONS_METHOD, this, componentName),
                () -> getActionImplementation(GET_DIMENSIONS_METHOD, Dimensions.class).execute(this, componentName));
    }

    @Override
    public WebChildElement componentShouldHaveDimensions(@NotNull String componentName, @NotNull Dimensions expectedDimensions) {
        runCheck(getEnvironment(), InvocationName.of(SHOULD_HAVE_DIMENSIONS_METHOD, this, componentName, expectedDimensions),
                () -> {
                    Dimensions actualDimensions = getActionImplementation(GET_DIMENSIONS_METHOD, Dimensions.class)
                            .execute(this, componentName);
                    getActionImplementation(SHOULD_HAVE_DIMENSIONS_METHOD, Void.class)
                            .execute(this, actualDimensions, expectedDimensions, componentName);
                });
        return this;
    }

    @Override
    public WebChildElement componentShouldNotHaveDimensions(@NotNull String componentName, @NotNull Dimensions expectedDimensions) {
        runCheck(getEnvironment(), InvocationName.of(SHOULD_NOT_HAVE_DIMENSIONS_METHOD, this, componentName, expectedDimensions),
                () -> {
                    Dimensions actualDimensions = getActionImplementation(GET_DIMENSIONS_METHOD, Dimensions.class)
                            .execute(this, componentName);
                    getActionImplementation(SHOULD_NOT_HAVE_DIMENSIONS_METHOD, Void.class)
                            .execute(this, actualDimensions, expectedDimensions, componentName);
                });
        return this;
    }

    // Location

    @Override
    public @NotNull Location getLocation(@NotNull String componentName) {
        return runCheck(getEnvironment(), InvocationName.of(GET_LOCATION_METHOD, this, componentName),
                () -> getActionImplementation(GET_LOCATION_METHOD, Location.class).execute(this, componentName));
    }

    @Override
    public WebChildElement componentShouldHaveLocation(@NotNull String componentName, @NotNull Location expectedLocation) {
        runCheck(getEnvironment(), InvocationName.of(SHOULD_HAVE_LOCATION_METHOD, this, componentName, expectedLocation),
                () -> {
                    Location actualLocation = getActionImplementation(GET_LOCATION_METHOD, Location.class)
                            .execute(this, componentName);
                    getActionImplementation(SHOULD_HAVE_LOCATION_METHOD, Void.class)
                            .execute(this, actualLocation, expectedLocation, componentName);
                });
        return this;
    }

    @Override
    public WebChildElement componentShouldNotHaveLocation(@NotNull String componentName, @NotNull Location expectedLocation) {
        runCheck(getEnvironment(), InvocationName.of(SHOULD_NOT_HAVE_LOCATION_METHOD, this, componentName, expectedLocation),
                () -> {
                    Location actualLocation = getActionImplementation(GET_LOCATION_METHOD, Location.class)
                            .execute(this, componentName);
                    getActionImplementation(SHOULD_NOT_HAVE_LOCATION_METHOD, Void.class)
                            .execute(this, actualLocation, expectedLocation, componentName);
                });
        return this;
    }

    // Screenshot

    @Override
    public @NotNull Screenshot getScreenshot(@NotNull String componentName) {
        return runCheck(getEnvironment(), InvocationName.of(GET_SCREENSHOT_METHOD, this, componentName),
                () -> getActionImplementation(GET_SCREENSHOT_METHOD, Screenshot.class).execute(this, componentName));
    }

    @Override
    public WebChildElement componentShouldLooksLike(@NotNull String componentName, @NotNull Screenshot expectedScreenshot) {
        runCheck(getEnvironment(), InvocationName.of(SHOULD_LOOKS_LIKE_METHOD, this, componentName, expectedScreenshot),
                () -> {
                    Screenshot actualScreenshot = getActionImplementation(GET_SCREENSHOT_METHOD, Screenshot.class)
                            .execute(this, componentName);
                    getActionImplementation(SHOULD_LOOKS_LIKE_METHOD, Void.class)
                            .execute(this, actualScreenshot, expectedScreenshot, componentName);
                });
        return this;
    }

    @Override
    public WebChildElement componentShouldNotLooksLike(@NotNull String componentName, @NotNull Screenshot expectedScreenshot) {
        runCheck(getEnvironment(), InvocationName.of(SHOULD_NOT_LOOKS_LIKE_METHOD, this, componentName, expectedScreenshot),
                () -> {
                    Screenshot actualScreenshot = getActionImplementation(GET_SCREENSHOT_METHOD, Screenshot.class)
                            .execute(this, componentName);
                    getActionImplementation(SHOULD_NOT_LOOKS_LIKE_METHOD, Void.class)
                            .execute(this, actualScreenshot, expectedScreenshot, componentName);
                });
        return this;
    }

    // HoverTo

    @Override
    public WebChildElement hoverTo(boolean withOutOfBounds) {
        runCheck(getEnvironment(), InvocationName.of(HOVER_TO_METHOD, this, withOutOfBounds),
                () -> getActionImplementation(HOVER_TO_METHOD, Void.class).execute(this, withOutOfBounds));
        return this;
    }

    // IsDisplayed

    @Override
    public boolean isDisplayed() {
        return runCheck(getEnvironment(), InvocationName.of(IS_DISPLAYED_METHOD, this),
                () -> getActionImplementation(IS_DISPLAYED_METHOD, Boolean.class).execute(this, DISPLAYED));
    }

    @Override
    public WebChildElement shouldBeDisplayed() {
        runCheck(getEnvironment(), InvocationName.of(SHOULD_BE_DISPLAYED_METHOD, this),
                () -> getActionImplementation(SHOULD_BE_DISPLAYED_METHOD, Boolean.class).execute(this, DISPLAYED));
        return this;
    }

    @Override
    public WebChildElement shouldNotBeDisplayed() {
        runCheck(getEnvironment(), InvocationName.of(SHOULD_NOT_BE_DISPLAYED_METHOD, this),
                () -> getActionImplementation(SHOULD_NOT_BE_DISPLAYED_METHOD, Boolean.class).execute(this, DISPLAYED));
        return this;
    }

    // IsInFocus

    @Override
    public boolean isInFocus() {
        return runCheck(getEnvironment(), InvocationName.of(IS_IN_FOCUS_METHOD, this),
                () -> getActionImplementation(IS_IN_FOCUS_METHOD, Boolean.class).execute(this));
    }

    @Override
    public WebChildElement shouldBeInFocus() {
        runCheck(getEnvironment(), InvocationName.of(SHOULD_BE_IN_FOCUS_METHOD, this),
                () -> {
                    boolean inFocus = getActionImplementation(IS_IN_FOCUS_METHOD, Boolean.class)
                            .execute(this);
                    getActionImplementation(SHOULD_BE_IN_FOCUS_METHOD, Void.class)
                            .execute(this, inFocus);
                });
        return this;
    }

    @Override
    public WebChildElement shouldNotBeInFocus() {
        runCheck(getEnvironment(), InvocationName.of(SHOULD_NOT_BE_IN_FOCUS_METHOD, this),
                () -> {
                    boolean inFocus = getActionImplementation(IS_IN_FOCUS_METHOD, Boolean.class)
                            .execute(this);
                    getActionImplementation(SHOULD_NOT_BE_IN_FOCUS_METHOD, Void.class)
                            .execute(this, inFocus);
                });
        return this;
    }

    // IsPresent

    @Override
    public boolean isPresent() {
        return runCheck(getEnvironment(), InvocationName.of(IS_PRESENT_METHOD, this),
                () -> getActionImplementation(IS_PRESENT_METHOD, Boolean.class).execute(this, PRESENTED));
    }

    @Override
    public WebChildElement shouldBePresent() {
        runCheck(getEnvironment(), InvocationName.of(SHOULD_BE_PRESENT_METHOD, this),
                () -> getActionImplementation(SHOULD_BE_PRESENT_METHOD, Void.class).execute(this, PRESENTED));
        return this;
    }

    @Override
    public WebChildElement shouldNotBePresent() {
        runCheck(getEnvironment(), InvocationName.of(SHOULD_NOT_BE_PRESENT_METHOD, this),
                () -> getActionImplementation(SHOULD_NOT_BE_PRESENT_METHOD, Void.class).execute(this, PRESENTED));
        return this;
    }

    // ScrollTo

    @Override
    public WebChildElement scrollTo() {
        runCheck(getEnvironment(), InvocationName.of(SCROLL_TO_METHOD, this),
                () -> getActionImplementation(SCROLL_TO_METHOD, Void.class).execute(this));
        return this;
    }

    // Component

    @Override
    public boolean isComponentPresent(@NotNull String componentName) {
        return runCheck(getEnvironment(), InvocationName.of(IS_COMPONENT_PRESENT_METHOD, this, componentName),
                () -> getActionImplementation(IS_COMPONENT_PRESENT_METHOD, Boolean.class).execute(this, componentName));
    }

    @Override
    public boolean isComponentDisplayed(@NotNull String componentName) {
        return runCheck(getEnvironment(), InvocationName.of(IS_COMPONENT_DISPLAYED_METHOD, this, componentName),
                () -> getActionImplementation(IS_COMPONENT_DISPLAYED_METHOD, Boolean.class).execute(this, componentName));
    }

    @Override
    public WebChildElement componentShouldBePresent(@NotNull String componentName) {
        runCheck(getEnvironment(), InvocationName.of(COMPONENT_SHOULD_BE_PRESENT_METHOD, this, componentName),
                () -> getActionImplementation(COMPONENT_SHOULD_BE_PRESENT_METHOD, Void.class).execute(this, componentName));
        return this;
    }

    @Override
    public WebChildElement componentShouldNotBePresent(@NotNull String componentName) {
        runCheck(getEnvironment(), InvocationName.of(COMPONENT_SHOULD_NOT_BE_PRESENT_METHOD, this, componentName),
                () -> getActionImplementation(COMPONENT_SHOULD_NOT_BE_PRESENT_METHOD, Void.class).execute(this, componentName));
        return this;
    }

    @Override
    public WebChildElement componentShouldBeDisplayed(@NotNull String componentName) {
        runCheck(getEnvironment(), InvocationName.of(COMPONENT_SHOULD_BE_DISPLAYED_METHOD, this, componentName),
                () -> getActionImplementation(COMPONENT_SHOULD_BE_DISPLAYED_METHOD, Void.class).execute(this, componentName));
        return this;
    }

    @Override
    public WebChildElement componentShouldNotBeDisplayed(@NotNull String componentName) {
        runCheck(getEnvironment(), InvocationName.of(COMPONENT_SHOULD_NOT_BE_DISPLAYED_METHOD, this, componentName),
                () -> getActionImplementation(COMPONENT_SHOULD_NOT_BE_DISPLAYED_METHOD, Void.class).execute(this, componentName));
        return this;
    }

    // Property

    @Override
    public @Nullable String getPropertyValue(@NotNull String propertyName) {
        WebElementPropertyHolder propertyHolder = getProperty(propertyName)
                .orElseThrow(() -> new ElementPropertyNotDeclaredException(ELEMENT_PROPERTY_NOT_DECLARED.getMessage(propertyName)));
        return runCheck(getEnvironment(), InvocationName.of(GET_PROPERTY_VALUE_METHOD, this, propertyName),
                () -> getActionImplementation(GET_PROPERTY_VALUE_METHOD, String.class).execute(this, propertyHolder));
    }

    @Override
    public WebChildElement shouldHavePropertyValue(@NotNull String propertyName, @NotNull StringValue expectedValue) {
        WebElementPropertyHolder propertyHolder = getProperty(propertyName)
                .orElseThrow(() -> new ElementPropertyNotDeclaredException(ELEMENT_PROPERTY_NOT_DECLARED.getMessage(propertyName)));
        runCheck(getEnvironment(), InvocationName.of(SHOULD_HAVE_PROPERTY_VALUE_METHOD, this, propertyName, expectedValue),
                () -> {
                    String actualPropertyValue = getActionImplementation(GET_PROPERTY_VALUE_METHOD, String.class)
                            .execute(this, propertyHolder);
                    getActionImplementation(SHOULD_HAVE_PROPERTY_VALUE_METHOD, Void.class)
                            .execute(this, actualPropertyValue, expectedValue, propertyName);
                });
        return this;
    }

    @Override
    public WebChildElement shouldHavePropertyValue(@NotNull String propertyName, @NotNull NumberValue<?> expectedValue) {
        WebElementPropertyHolder propertyHolder = getProperty(propertyName)
                .orElseThrow(() -> new ElementPropertyNotDeclaredException(ELEMENT_PROPERTY_NOT_DECLARED.getMessage(propertyName)));
        runCheck(getEnvironment(), InvocationName.of(SHOULD_HAVE_PROPERTY_NUMBER_METHOD, this, propertyName, expectedValue),
                () -> {
                    String actualPropertyValue = getActionImplementation(GET_PROPERTY_VALUE_METHOD, String.class)
                            .execute(this, propertyHolder);
                    getActionImplementation(SHOULD_HAVE_PROPERTY_NUMBER_METHOD, Void.class)
                            .execute(this, actualPropertyValue, expectedValue, propertyName);
                });
        return this;
    }

    @Override
    public WebChildElement shouldNotHavePropertyValue(@NotNull String propertyName, @NotNull StringValue expectedValue) {
        WebElementPropertyHolder propertyHolder = getProperty(propertyName)
                .orElseThrow(() -> new ElementPropertyNotDeclaredException(ELEMENT_PROPERTY_NOT_DECLARED.getMessage(propertyName)));
        runCheck(getEnvironment(), InvocationName.of(SHOULD_NOT_HAVE_PROPERTY_VALUE_METHOD, this, propertyName, expectedValue),
                () -> {
                    String actualPropertyValue = getActionImplementation(GET_PROPERTY_VALUE_METHOD, String.class)
                            .execute(this, propertyHolder);
                    getActionImplementation(SHOULD_NOT_HAVE_PROPERTY_VALUE_METHOD, Void.class)
                            .execute(this, actualPropertyValue, expectedValue, propertyName);
                });
        return this;
    }

    @Override
    public WebChildElement shouldNotHavePropertyValue(@NotNull String propertyName, @NotNull NumberValue<?> expectedValue) {
        WebElementPropertyHolder propertyHolder = getProperty(propertyName)
                .orElseThrow(() -> new ElementPropertyNotDeclaredException(ELEMENT_PROPERTY_NOT_DECLARED.getMessage(propertyName)));
        runCheck(getEnvironment(), InvocationName.of(SHOULD_NOT_HAVE_PROPERTY_NUMBER_METHOD, this, propertyName, expectedValue),
                () -> {
                    String actualPropertyValue = getActionImplementation(GET_PROPERTY_VALUE_METHOD, String.class)
                            .execute(this, propertyHolder);
                    getActionImplementation(SHOULD_NOT_HAVE_PROPERTY_NUMBER_METHOD, Void.class)
                            .execute(this, actualPropertyValue, expectedValue, propertyName);
                });
        return this;
    }

    @Override
    public JsonNode toJson() {
        ObjectNode rootNode = createObjectNode();
        rootNode.set("elementIdentifier", this.elementIdentifier.toJson());
        rootNode.put("elementClass", this.getClass().getCanonicalName())
                .put("parent", this.elementIdentifier.getElementMethod().getDeclaringClass().getCanonicalName());
        rootNode.set("locators", this.locatorRegistry.toJson());
        rootNode.set("properties", this.propertyRegistry.toJson());
        rootNode.set("actions", this.actionRegistry.toJson());
        rootNode.set("interactions", this.interactionRegistry.toJson());
        return rootNode;
    }

    @Override
    public String toString() {
        return toJson().toPrettyString();
    }

}
