package io.perfeccionista.framework.pagefactory.elements;

import io.perfeccionista.framework.asserts.WebAssertCondition;
import io.perfeccionista.framework.invocation.runner.InvocationName;
import io.perfeccionista.framework.pagefactory.elements.actions.JsGetColor;
import io.perfeccionista.framework.pagefactory.elements.actions.JsGetDimensions;
import io.perfeccionista.framework.pagefactory.elements.actions.JsGetIsDisplayed;
import io.perfeccionista.framework.pagefactory.elements.actions.JsGetIsInFocus;
import io.perfeccionista.framework.pagefactory.elements.actions.JsGetIsPresent;
import io.perfeccionista.framework.pagefactory.elements.actions.JsGetLabel;
import io.perfeccionista.framework.pagefactory.elements.actions.JsGetLocation;
import io.perfeccionista.framework.pagefactory.elements.actions.JsGetPropertyValue;
import io.perfeccionista.framework.pagefactory.elements.actions.JsGetScreenshot;
import io.perfeccionista.framework.pagefactory.elements.actions.JsScrollTo;
import io.perfeccionista.framework.pagefactory.elements.actions.SeleniumClick;
import io.perfeccionista.framework.pagefactory.elements.actions.SeleniumHoverTo;
import io.perfeccionista.framework.pagefactory.elements.actions.SeleniumIsEnabled;
import io.perfeccionista.framework.pagefactory.elements.actions.SeleniumIsSelected;
import io.perfeccionista.framework.pagefactory.elements.actions.WebElementAction;
import io.perfeccionista.framework.pagefactory.elements.asserts.AssertShouldBeDisabled;
import io.perfeccionista.framework.pagefactory.elements.asserts.AssertShouldBeEnabled;
import io.perfeccionista.framework.pagefactory.elements.asserts.AssertShouldBeInFocus;
import io.perfeccionista.framework.pagefactory.elements.asserts.AssertShouldBeSelected;
import io.perfeccionista.framework.pagefactory.elements.asserts.AssertShouldHaveColor;
import io.perfeccionista.framework.pagefactory.elements.asserts.AssertShouldHaveLabelNumber;
import io.perfeccionista.framework.pagefactory.elements.asserts.AssertShouldHaveLabelText;
import io.perfeccionista.framework.pagefactory.elements.asserts.AssertShouldNotBeInFocus;
import io.perfeccionista.framework.pagefactory.elements.asserts.AssertShouldNotBeSelected;
import io.perfeccionista.framework.pagefactory.elements.asserts.AssertShouldNotHaveColor;
import io.perfeccionista.framework.pagefactory.elements.asserts.AssertShouldNotHaveLabelNumber;
import io.perfeccionista.framework.pagefactory.elements.asserts.AssertShouldNotHaveLabelText;
import io.perfeccionista.framework.pagefactory.elements.asserts.JsAssertShouldBeDisplayed;
import io.perfeccionista.framework.pagefactory.elements.asserts.JsAssertShouldBePresent;
import io.perfeccionista.framework.pagefactory.elements.asserts.AssertShouldHaveDimensions;
import io.perfeccionista.framework.pagefactory.elements.asserts.AssertShouldHaveLocation;
import io.perfeccionista.framework.pagefactory.elements.asserts.AssertShouldHavePropertyNumber;
import io.perfeccionista.framework.pagefactory.elements.asserts.AssertShouldHavePropertyValue;
import io.perfeccionista.framework.pagefactory.elements.asserts.AssertShouldLooksLike;
import io.perfeccionista.framework.pagefactory.elements.asserts.JsAssertShouldNotBeDisplayed;
import io.perfeccionista.framework.pagefactory.elements.asserts.JsAssertShouldNotBePresent;
import io.perfeccionista.framework.pagefactory.elements.asserts.AssertShouldNotHaveDimensions;
import io.perfeccionista.framework.pagefactory.elements.asserts.AssertShouldNotHaveLocation;
import io.perfeccionista.framework.pagefactory.elements.asserts.AssertShouldNotHavePropertyNumber;
import io.perfeccionista.framework.pagefactory.elements.asserts.AssertShouldNotHavePropertyValue;
import io.perfeccionista.framework.pagefactory.elements.asserts.AssertShouldNotLooksLike;
import io.perfeccionista.framework.pagefactory.elements.base.WebParentInfo;
import io.perfeccionista.framework.pagefactory.elements.base.WebChildElement;
import io.perfeccionista.framework.pagefactory.elements.locators.WebLocatorChain;
import io.perfeccionista.framework.pagefactory.elements.locators.WebLocatorHolder;
import io.perfeccionista.framework.pagefactory.elements.methods.Dimensions;
import io.perfeccionista.framework.pagefactory.elements.methods.Location;
import io.perfeccionista.framework.pagefactory.screenshots.Screenshot;
import io.perfeccionista.framework.plugin.Color;
import io.perfeccionista.framework.value.number.NumberValue;
import io.perfeccionista.framework.value.string.StringValue;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

import static io.perfeccionista.framework.invocation.wrappers.CheckActionWrapper.runCheck;
import static io.perfeccionista.framework.pagefactory.elements.components.WebComponents.ROOT;
import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.CLICK_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.COMPONENT_SHOULD_BE_DISPLAYED_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.COMPONENT_SHOULD_BE_PRESENT_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.COMPONENT_SHOULD_NOT_BE_DISPLAYED_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.COMPONENT_SHOULD_NOT_BE_PRESENT_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.GET_COLOR_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.GET_DIMENSIONS_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.GET_LABEL_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.GET_LOCATION_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.GET_PROPERTY_VALUE_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.GET_SCREENSHOT_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.HOVER_TO_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.IS_COMPONENT_DISPLAYED_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.IS_COMPONENT_PRESENT_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.IS_DISPLAYED_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.IS_ENABLED_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.IS_IN_FOCUS_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.IS_PRESENT_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.IS_SELECTED_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.SCROLL_TO_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.SHOULD_BE_DISABLED_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.SHOULD_BE_DISPLAYED_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.SHOULD_BE_ENABLED_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.SHOULD_BE_IN_FOCUS_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.SHOULD_BE_PRESENT_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.SHOULD_BE_SELECTED_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.SHOULD_HAVE_COLOR_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.SHOULD_HAVE_DIMENSIONS_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.SHOULD_HAVE_LOCATION_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.SHOULD_HAVE_NUMBER_LABEL_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.SHOULD_HAVE_PROPERTY_NUMBER_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.SHOULD_HAVE_PROPERTY_VALUE_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.SHOULD_HAVE_TEXT_LABEL_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.SHOULD_LOOKS_LIKE_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.SHOULD_NOT_BE_DISPLAYED_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.SHOULD_NOT_BE_IN_FOCUS_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.SHOULD_NOT_BE_PRESENT_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.SHOULD_NOT_BE_SELECTED_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.SHOULD_NOT_HAVE_COLOR_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.SHOULD_NOT_HAVE_DIMENSIONS_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.SHOULD_NOT_HAVE_LOCATION_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.SHOULD_NOT_HAVE_NUMBER_LABEL_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.SHOULD_NOT_HAVE_PROPERTY_NUMBER_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.SHOULD_NOT_HAVE_PROPERTY_VALUE_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.SHOULD_NOT_HAVE_TEXT_LABEL_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.SHOULD_NOT_LOOKS_LIKE_METHOD;


// WebRadioGroup
@WebElementAction(name = CLICK_METHOD, implementation = SeleniumClick.class)
@WebElementAction(name = IS_SELECTED_METHOD, implementation = SeleniumIsSelected.class)
@WebElementAction(name = SHOULD_BE_SELECTED_METHOD, implementation = AssertShouldBeSelected.class)
@WebElementAction(name = SHOULD_NOT_BE_SELECTED_METHOD, implementation = AssertShouldNotBeSelected.class)
@WebElementAction(name = IS_ENABLED_METHOD, implementation = SeleniumIsEnabled.class)
@WebElementAction(name = SHOULD_BE_ENABLED_METHOD, implementation = AssertShouldBeEnabled.class)
@WebElementAction(name = SHOULD_BE_DISABLED_METHOD, implementation = AssertShouldBeDisabled.class)
@WebElementAction(name = GET_LABEL_METHOD, implementation = JsGetLabel.class)
@WebElementAction(name = SHOULD_HAVE_TEXT_LABEL_METHOD, implementation = AssertShouldHaveLabelText.class)
@WebElementAction(name = SHOULD_HAVE_NUMBER_LABEL_METHOD, implementation = AssertShouldHaveLabelNumber.class)
@WebElementAction(name = SHOULD_NOT_HAVE_TEXT_LABEL_METHOD, implementation = AssertShouldNotHaveLabelText.class)
@WebElementAction(name = SHOULD_NOT_HAVE_NUMBER_LABEL_METHOD, implementation = AssertShouldNotHaveLabelNumber.class)
// WebChildElement
@WebElementAction(name = COMPONENT_SHOULD_BE_DISPLAYED_METHOD, implementation = JsAssertShouldBeDisplayed.class)
@WebElementAction(name = COMPONENT_SHOULD_BE_PRESENT_METHOD, implementation = JsAssertShouldBePresent.class)
@WebElementAction(name = COMPONENT_SHOULD_NOT_BE_DISPLAYED_METHOD, implementation = JsAssertShouldNotBeDisplayed.class)
@WebElementAction(name = COMPONENT_SHOULD_NOT_BE_PRESENT_METHOD, implementation = JsAssertShouldNotBePresent.class)
@WebElementAction(name = GET_COLOR_METHOD, implementation = JsGetColor.class)
@WebElementAction(name = GET_DIMENSIONS_METHOD, implementation = JsGetDimensions.class)
@WebElementAction(name = GET_LOCATION_METHOD, implementation = JsGetLocation.class)
@WebElementAction(name = GET_PROPERTY_VALUE_METHOD, implementation = JsGetPropertyValue.class)
@WebElementAction(name = GET_SCREENSHOT_METHOD, implementation = JsGetScreenshot.class)
@WebElementAction(name = HOVER_TO_METHOD, implementation = SeleniumHoverTo.class)
@WebElementAction(name = IS_COMPONENT_DISPLAYED_METHOD, implementation = JsGetIsDisplayed.class)
@WebElementAction(name = IS_COMPONENT_PRESENT_METHOD, implementation = JsGetIsPresent.class)
@WebElementAction(name = IS_DISPLAYED_METHOD, implementation = JsGetIsDisplayed.class)
@WebElementAction(name = IS_IN_FOCUS_METHOD, implementation = JsGetIsInFocus.class)
@WebElementAction(name = IS_PRESENT_METHOD, implementation = JsGetIsPresent.class)
@WebElementAction(name = SCROLL_TO_METHOD, implementation = JsScrollTo.class)
@WebElementAction(name = SHOULD_BE_DISPLAYED_METHOD, implementation = JsAssertShouldBeDisplayed.class)
@WebElementAction(name = SHOULD_BE_IN_FOCUS_METHOD, implementation = AssertShouldBeInFocus.class)
@WebElementAction(name = SHOULD_BE_PRESENT_METHOD, implementation = JsAssertShouldBePresent.class)
@WebElementAction(name = SHOULD_HAVE_COLOR_METHOD, implementation = AssertShouldHaveColor.class)
@WebElementAction(name = SHOULD_HAVE_DIMENSIONS_METHOD, implementation = AssertShouldHaveDimensions.class)
@WebElementAction(name = SHOULD_HAVE_LOCATION_METHOD, implementation = AssertShouldHaveLocation.class)
@WebElementAction(name = SHOULD_HAVE_PROPERTY_NUMBER_METHOD, implementation = AssertShouldHavePropertyNumber.class)
@WebElementAction(name = SHOULD_HAVE_PROPERTY_VALUE_METHOD, implementation = AssertShouldHavePropertyValue.class)
@WebElementAction(name = SHOULD_LOOKS_LIKE_METHOD, implementation = AssertShouldLooksLike.class)
@WebElementAction(name = SHOULD_NOT_BE_DISPLAYED_METHOD, implementation = JsAssertShouldNotBeDisplayed.class)
@WebElementAction(name = SHOULD_NOT_BE_IN_FOCUS_METHOD, implementation = AssertShouldNotBeInFocus.class)
@WebElementAction(name = SHOULD_NOT_BE_PRESENT_METHOD, implementation = JsAssertShouldNotBePresent.class)
@WebElementAction(name = SHOULD_NOT_HAVE_COLOR_METHOD, implementation = AssertShouldNotHaveColor.class)
@WebElementAction(name = SHOULD_NOT_HAVE_DIMENSIONS_METHOD, implementation = AssertShouldNotHaveDimensions.class)
@WebElementAction(name = SHOULD_NOT_HAVE_LOCATION_METHOD, implementation = AssertShouldNotHaveLocation.class)
@WebElementAction(name = SHOULD_NOT_HAVE_PROPERTY_NUMBER_METHOD, implementation = AssertShouldNotHavePropertyNumber.class)
@WebElementAction(name = SHOULD_NOT_HAVE_PROPERTY_VALUE_METHOD, implementation = AssertShouldNotHavePropertyValue.class)
@WebElementAction(name = SHOULD_NOT_LOOKS_LIKE_METHOD, implementation = AssertShouldNotLooksLike.class)
public class WebRadioButtonSeleniumImpl extends AbstractWebChildElement implements WebRadioButton {

    protected WebParentInfo<WebRadioGroup> parentInfo = null;

    @Override
    public @Nullable WebParentInfo<WebRadioGroup> getParentInfo() {
        return parentInfo;
    }

    @Override
    public @NotNull WebLocatorChain getLocatorChainTo(@NotNull String locatorName) {
        Optional<WebLocatorHolder> optionalLocator = locatorRegistry.getOptionalLocator(locatorName);
        if (optionalLocator.isPresent()) {
            return getLocatorChain().addLocator(optionalLocator.get());
        }
        return getLocatorChain();
    }

    @Override
    public @NotNull WebLocatorChain getLocatorChain() {
        // Если WebRadioButton инициализируется на странице, как обычный элемент, а не внутри RadioGroup
        if (parentInfo == null) {
            return parent.getLocatorChain().addLocator(locatorRegistry.getLocator(ROOT));
        }
        WebLocatorChain locatorChain = parentInfo.getParent().getLocatorChain();
        locatorChain.getLastLocator().setExpectedHash(parentInfo.getParentHash());
        locatorChain.addLocators(parentInfo.getParentLocators());
        return locatorChain;
    }

    // Actions

    @Override
    public WebRadioButton executeAction(@NotNull String name, Object... args) {
        super.executeAction(name, args);
        return this;
    }

    @Override
    public WebRadioButton executeInteraction(@NotNull String name, @NotNull WebChildElement other, Object... args) {
        super.executeInteraction(name, other, args);
        return this;
    }

    // Asserts

    @Override
    public WebRadioButton should(WebAssertCondition assertCondition) {
        super.should(assertCondition);
        return this;
    }

    @Override
    public WebRadioButton should(WebAssertCondition assertCondition, InvocationName invocationName) {
        super.should(assertCondition, invocationName);
        return this;
    }

    // Click

    @Override
    public WebRadioButton click() {
        runCheck(getEnvironment(), InvocationName.of(CLICK_METHOD, this),
                () -> getActionImplementation(CLICK_METHOD, Void.class).execute(this));
        return this;
    }

    // Get Color

    @Override
    public WebRadioButton componentShouldHaveColor(@NotNull String componentName, @NotNull String cssProperty, @NotNull Color expectedColor) {
        super.componentShouldHaveColor(componentName, cssProperty, expectedColor);
        return this;
    }

    @Override
    public WebRadioButton componentShouldNotHaveColor(@NotNull String componentName, @NotNull String cssProperty, @NotNull Color expectedColor) {
        super.componentShouldNotHaveColor(componentName, cssProperty, expectedColor);
        return this;
    }

    // Get Dimensions

    @Override
    public WebRadioButton componentShouldHaveDimensions(@NotNull String componentName, @NotNull Dimensions expectedDimensions) {
        super.componentShouldHaveDimensions(componentName, expectedDimensions);
        return this;
    }

    @Override
    public WebRadioButton componentShouldNotHaveDimensions(@NotNull String componentName, @NotNull Dimensions expectedDimensions) {
        super.componentShouldNotHaveDimensions(componentName, expectedDimensions);
        return this;
    }

    // Get Label

    @Override
    public @Nullable String getLabel() {
        return runCheck(getEnvironment(), InvocationName.of(GET_LABEL_METHOD, this),
                () -> getActionImplementation(GET_LABEL_METHOD, String.class).execute(this));
    }

    @Override
    public WebRadioButton shouldHaveLabel(@NotNull StringValue expectedValue) {
        runCheck(getEnvironment(), InvocationName.of(SHOULD_HAVE_TEXT_LABEL_METHOD, this, expectedValue),
                () -> {
                    String actualLabel = getActionImplementation(GET_LABEL_METHOD, String.class)
                            .execute(this);
                    getActionImplementation(SHOULD_HAVE_TEXT_LABEL_METHOD, Void.class)
                            .execute(this, actualLabel, expectedValue);
                });
        return this;
    }

    @Override
    public WebRadioButton shouldHaveLabel(@NotNull NumberValue<?> expectedValue) {
        runCheck(getEnvironment(), InvocationName.of(SHOULD_HAVE_NUMBER_LABEL_METHOD, this, expectedValue),
                () -> {
                    String actualLabel = getActionImplementation(GET_LABEL_METHOD, String.class)
                            .execute(this);
                    getActionImplementation(SHOULD_HAVE_NUMBER_LABEL_METHOD, Void.class)
                            .execute(this, actualLabel, expectedValue);
                });
        return this;
    }

    @Override
    public WebRadioButton shouldNotHaveLabel(@NotNull StringValue expectedValue) {
        runCheck(getEnvironment(), InvocationName.of(SHOULD_NOT_HAVE_TEXT_LABEL_METHOD, this, expectedValue),
                () -> {
                    String actualLabel = getActionImplementation(GET_LABEL_METHOD, String.class)
                            .execute(this);
                    getActionImplementation(SHOULD_NOT_HAVE_TEXT_LABEL_METHOD, Void.class)
                            .execute(this, actualLabel, expectedValue);
                });
        return this;
    }

    @Override
    public WebRadioButton shouldNotHaveLabel(@NotNull NumberValue<?> expectedValue) {
        runCheck(getEnvironment(), InvocationName.of(SHOULD_NOT_HAVE_NUMBER_LABEL_METHOD, this, expectedValue),
                () -> {
                    String actualLabel = getActionImplementation(GET_LABEL_METHOD, String.class)
                            .execute(this);
                    getActionImplementation(SHOULD_NOT_HAVE_NUMBER_LABEL_METHOD, Void.class)
                            .execute(this, actualLabel, expectedValue);
                });
        return this;
    }

    // Get Location

    @Override
    public WebRadioButton componentShouldHaveLocation(@NotNull String componentName, @NotNull Location expectedLocation) {
        super.componentShouldHaveLocation(componentName, expectedLocation);
        return this;
    }

    @Override
    public WebRadioButton componentShouldNotHaveLocation(@NotNull String componentName, @NotNull Location expectedLocation) {
        super.componentShouldNotHaveLocation(componentName, expectedLocation);
        return this;
    }

    // Get Screenshot

    @Override
    public WebRadioButton componentShouldLooksLike(@NotNull String componentName, @NotNull Screenshot expectedScreenshot) {
        super.componentShouldLooksLike(componentName, expectedScreenshot);
        return this;
    }

    @Override
    public WebRadioButton componentShouldNotLooksLike(@NotNull String componentName, @NotNull Screenshot expectedScreenshot) {
        super.componentShouldNotLooksLike(componentName, expectedScreenshot);
        return this;
    }

    // HoverTo

    @Override
    public WebRadioButton hoverTo(boolean withOutOfBounds) {
        super.hoverTo(withOutOfBounds);
        return this;
    }

    // IsDisplayed

    @Override
    public WebRadioButton shouldBeDisplayed() {
        super.shouldBeDisplayed();
        return this;
    }

    @Override
    public WebRadioButton shouldNotBeDisplayed() {
        super.shouldNotBeDisplayed();
        return this;
    }

    // IsEnabled

    @Override
    public boolean isEnabled() {
        return runCheck(getEnvironment(), InvocationName.of(IS_ENABLED_METHOD, this),
                () -> getActionImplementation(IS_ENABLED_METHOD, Boolean.class).execute(this));
    }

    @Override
    public WebRadioButton shouldBeEnabled() {
        runCheck(getEnvironment(), InvocationName.of(SHOULD_BE_ENABLED_METHOD, this),
                () -> {
                    boolean enabled = getActionImplementation(IS_ENABLED_METHOD, Boolean.class)
                            .execute(this);
                    getActionImplementation(SHOULD_BE_ENABLED_METHOD, Void.class)
                            .execute(this, enabled);
                });
        return this;
    }

    @Override
    public WebRadioButton shouldBeDisabled() {
        runCheck(getEnvironment(), InvocationName.of(SHOULD_BE_DISABLED_METHOD, this),
                () -> {
                    boolean enabled = getActionImplementation(IS_ENABLED_METHOD, Boolean.class)
                            .execute(this);
                    getActionImplementation(SHOULD_BE_DISABLED_METHOD, Void.class)
                            .execute(this, enabled);
                });
        return this;
    }

    // IsInFocus

    @Override
    public WebRadioButton shouldBeInFocus() {
        super.shouldBeInFocus();
        return this;
    }

    @Override
    public WebRadioButton shouldNotBeInFocus() {
        super.shouldNotBeInFocus();
        return this;
    }

    // IsPresent

    @Override
    public WebRadioButton shouldBePresent() {
        super.shouldBePresent();
        return this;
    }

    @Override
    public WebRadioButton shouldNotBePresent() {
        super.shouldNotBePresent();
        return this;
    }

    // IsSelected

    @Override
    public boolean isSelected() {
        return runCheck(getEnvironment(), InvocationName.of(IS_SELECTED_METHOD, this),
                () -> getActionImplementation(IS_SELECTED_METHOD, Boolean.class).execute(this));
    }

    @Override
    public WebRadioButton shouldBeSelected() {
        runCheck(getEnvironment(), InvocationName.of(SHOULD_BE_SELECTED_METHOD, this),
                () -> {
                    boolean selected = getActionImplementation(IS_SELECTED_METHOD, Boolean.class)
                            .execute(this);
                    getActionImplementation(SHOULD_BE_SELECTED_METHOD, Void.class)
                            .execute(this, selected);
                });
        return this;
    }

    @Override
    public WebRadioButton shouldNotBeSelected() {
        runCheck(getEnvironment(), InvocationName.of(SHOULD_NOT_BE_SELECTED_METHOD, this),
                () -> {
                    boolean selected = getActionImplementation(IS_SELECTED_METHOD, Boolean.class)
                            .execute(this);
                    getActionImplementation(SHOULD_NOT_BE_SELECTED_METHOD, Void.class)
                            .execute(this, selected);
                });
        return this;
    }

    // ScrollTo

    @Override
    public WebRadioButton scrollTo() {
        super.scrollTo();
        return this;
    }

    // WebComponents

    @Override
    public WebRadioButton componentShouldBePresent(@NotNull String componentName) {
        super.componentShouldBePresent(componentName);
        return this;
    }

    @Override
    public WebRadioButton componentShouldNotBePresent(@NotNull String componentName) {
        super.componentShouldNotBePresent(componentName);
        return this;
    }

    @Override
    public WebRadioButton componentShouldBeDisplayed(@NotNull String componentName) {
        super.componentShouldBeDisplayed(componentName);
        return this;
    }

    @Override
    public WebRadioButton componentShouldNotBeDisplayed(@NotNull String componentName) {
        super.componentShouldNotBeDisplayed(componentName);
        return this;
    }

    // WebProperties

    @Override
    public WebRadioButton shouldHavePropertyValue(@NotNull String propertyName, @NotNull StringValue expectedValue) {
        super.shouldHavePropertyValue(propertyName, expectedValue);
        return this;
    }

    @Override
    public WebRadioButton shouldHavePropertyValue(@NotNull String propertyName, @NotNull NumberValue<?> expectedValue) {
        super.shouldHavePropertyValue(propertyName, expectedValue);
        return this;
    }

    @Override
    public WebRadioButton shouldNotHavePropertyValue(@NotNull String propertyName, @NotNull StringValue expectedValue) {
        super.shouldNotHavePropertyValue(propertyName, expectedValue);
        return this;
    }

    @Override
    public WebRadioButton shouldNotHavePropertyValue(@NotNull String propertyName, @NotNull NumberValue<?> expectedValue) {
        super.shouldNotHavePropertyValue(propertyName, expectedValue);
        return this;
    }

}
