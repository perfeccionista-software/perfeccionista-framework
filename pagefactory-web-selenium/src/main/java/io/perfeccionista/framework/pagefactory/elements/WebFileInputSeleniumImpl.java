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
import io.perfeccionista.framework.pagefactory.elements.actions.JsGetTextFromValue;
import io.perfeccionista.framework.pagefactory.elements.actions.JsScrollTo;
import io.perfeccionista.framework.pagefactory.elements.actions.SeleniumClear;
import io.perfeccionista.framework.pagefactory.elements.actions.SeleniumHoverTo;
import io.perfeccionista.framework.pagefactory.elements.actions.SeleniumIsEnabled;
import io.perfeccionista.framework.pagefactory.elements.actions.SeleniumSendKeys;
import io.perfeccionista.framework.pagefactory.elements.actions.SeleniumSubmit;
import io.perfeccionista.framework.pagefactory.elements.actions.WebElementAction;
import io.perfeccionista.framework.pagefactory.elements.asserts.AssertShouldBeDisabled;
import io.perfeccionista.framework.pagefactory.elements.asserts.AssertShouldBeEnabled;
import io.perfeccionista.framework.pagefactory.elements.asserts.AssertShouldBeInFocus;
import io.perfeccionista.framework.pagefactory.elements.asserts.AssertShouldHaveColor;
import io.perfeccionista.framework.pagefactory.elements.asserts.AssertShouldHaveLabelNumber;
import io.perfeccionista.framework.pagefactory.elements.asserts.AssertShouldHaveLabelText;
import io.perfeccionista.framework.pagefactory.elements.asserts.AssertShouldHaveNumber;
import io.perfeccionista.framework.pagefactory.elements.asserts.AssertShouldHaveText;
import io.perfeccionista.framework.pagefactory.elements.asserts.AssertShouldNotBeInFocus;
import io.perfeccionista.framework.pagefactory.elements.asserts.AssertShouldNotHaveColor;
import io.perfeccionista.framework.pagefactory.elements.asserts.AssertShouldNotHaveLabelNumber;
import io.perfeccionista.framework.pagefactory.elements.asserts.AssertShouldNotHaveLabelText;
import io.perfeccionista.framework.pagefactory.elements.asserts.AssertShouldNotHaveNumber;
import io.perfeccionista.framework.pagefactory.elements.asserts.AssertShouldNotHaveText;
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
import io.perfeccionista.framework.pagefactory.elements.base.WebChildElement;
import io.perfeccionista.framework.pagefactory.elements.methods.Dimensions;
import io.perfeccionista.framework.pagefactory.elements.methods.Location;
import io.perfeccionista.framework.pagefactory.screenshots.Screenshot;
import io.perfeccionista.framework.plugin.Color;
import io.perfeccionista.framework.value.number.NumberValue;
import io.perfeccionista.framework.value.string.StringValue;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

import static io.perfeccionista.framework.invocation.wrappers.CheckActionWrapper.runCheck;
import static io.perfeccionista.framework.pagefactory.elements.methods.AvailableElementMethods.SUBMIT_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.CLEAR_METHOD;
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
import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.GET_TEXT_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.HOVER_TO_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.IS_COMPONENT_DISPLAYED_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.IS_COMPONENT_PRESENT_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.IS_DISPLAYED_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.IS_ENABLED_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.IS_IN_FOCUS_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.IS_PRESENT_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.SCROLL_TO_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.SEND_KEYS_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.SHOULD_BE_DISABLED_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.SHOULD_BE_DISPLAYED_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.SHOULD_BE_ENABLED_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.SHOULD_BE_IN_FOCUS_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.SHOULD_BE_PRESENT_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.SHOULD_HAVE_COLOR_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.SHOULD_HAVE_DIMENSIONS_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.SHOULD_HAVE_LOCATION_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.SHOULD_HAVE_NUMBER_LABEL_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.SHOULD_HAVE_NUMBER_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.SHOULD_HAVE_PROPERTY_NUMBER_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.SHOULD_HAVE_PROPERTY_VALUE_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.SHOULD_HAVE_TEXT_LABEL_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.SHOULD_HAVE_TEXT_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.SHOULD_LOOKS_LIKE_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.SHOULD_NOT_BE_DISPLAYED_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.SHOULD_NOT_BE_IN_FOCUS_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.SHOULD_NOT_BE_PRESENT_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.SHOULD_NOT_HAVE_COLOR_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.SHOULD_NOT_HAVE_DIMENSIONS_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.SHOULD_NOT_HAVE_LOCATION_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.SHOULD_NOT_HAVE_NUMBER_LABEL_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.SHOULD_NOT_HAVE_NUMBER_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.SHOULD_NOT_HAVE_PROPERTY_NUMBER_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.SHOULD_NOT_HAVE_PROPERTY_VALUE_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.SHOULD_NOT_HAVE_TEXT_LABEL_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.SHOULD_NOT_HAVE_TEXT_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.SHOULD_NOT_LOOKS_LIKE_METHOD;

// WebTextInput
@WebElementAction(name = CLEAR_METHOD, implementation = SeleniumClear.class)
@WebElementAction(name = SEND_KEYS_METHOD, implementation = SeleniumSendKeys.class)
@WebElementAction(name = SUBMIT_METHOD, implementation = SeleniumSubmit.class)
@WebElementAction(name = IS_ENABLED_METHOD, implementation = SeleniumIsEnabled.class)
@WebElementAction(name = SHOULD_BE_ENABLED_METHOD, implementation = AssertShouldBeEnabled.class)
@WebElementAction(name = SHOULD_BE_DISABLED_METHOD, implementation = AssertShouldBeDisabled.class)
@WebElementAction(name = GET_TEXT_METHOD, implementation = JsGetTextFromValue.class)
@WebElementAction(name = SHOULD_HAVE_TEXT_METHOD, implementation = AssertShouldHaveText.class)
@WebElementAction(name = SHOULD_HAVE_NUMBER_METHOD, implementation = AssertShouldHaveNumber.class)
@WebElementAction(name = SHOULD_NOT_HAVE_TEXT_METHOD, implementation = AssertShouldNotHaveText.class)
@WebElementAction(name = SHOULD_NOT_HAVE_NUMBER_METHOD, implementation = AssertShouldNotHaveNumber.class)
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
public class WebFileInputSeleniumImpl extends AbstractWebChildElement implements WebFileInput {

    // Actions

    @Override
    public WebFileInput executeAction(@NotNull String name, Object... args) {
        super.executeAction(name, args);
        return this;
    }

    @Override
    public WebFileInput executeInteraction(@NotNull String name, @NotNull WebChildElement other, Object... args) {
        super.executeInteraction(name, other, args);
        return this;
    }

    // Asserts

    @Override
    public WebFileInput should(WebAssertCondition assertCondition) {
        super.should(assertCondition);
        return this;
    }

    @Override
    public WebFileInput should(WebAssertCondition assertCondition, InvocationName invocationName) {
        super.should(assertCondition, invocationName);
        return this;
    }

    // Clear

    @Override
    public WebFileInput clear() {
        runCheck(getEnvironment(), InvocationName.of(CLEAR_METHOD, this),
                () -> getActionImplementation(CLEAR_METHOD, Void.class).execute(this));
        return this;
    }

    // Get Color

    @Override
    public WebFileInput componentShouldHaveColor(@NotNull String componentName, @NotNull String cssProperty, @NotNull Color expectedColor) {
        super.componentShouldHaveColor(componentName, cssProperty, expectedColor);
        return this;
    }

    @Override
    public WebFileInput componentShouldNotHaveColor(@NotNull String componentName, @NotNull String cssProperty, @NotNull Color expectedColor) {
        super.componentShouldNotHaveColor(componentName, cssProperty, expectedColor);
        return this;
    }

    // Get Dimensions

    @Override
    public WebFileInput componentShouldHaveDimensions(@NotNull String componentName, @NotNull Dimensions expectedDimensions) {
        super.componentShouldHaveDimensions(componentName, expectedDimensions);
        return this;
    }

    @Override
    public WebFileInput componentShouldNotHaveDimensions(@NotNull String componentName, @NotNull Dimensions expectedDimensions) {
        super.componentShouldNotHaveDimensions(componentName, expectedDimensions);
        return this;
    }

    // Get Label

    @Override
    public String getLabel() {
        return runCheck(getEnvironment(), InvocationName.of(GET_LABEL_METHOD, this),
                () -> getActionImplementation(GET_LABEL_METHOD, String.class).execute(this));
    }

    @Override
    public WebFileInput shouldHaveLabel(@NotNull StringValue expectedValue) {
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
    public WebFileInput shouldHaveLabel(@NotNull NumberValue<?> expectedValue) {
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
    public WebFileInput shouldNotHaveLabel(@NotNull StringValue expectedValue) {
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
    public WebFileInput shouldNotHaveLabel(@NotNull NumberValue<?> expectedValue) {
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
    public WebFileInput componentShouldHaveLocation(@NotNull String componentName, @NotNull Location expectedLocation) {
        super.componentShouldHaveLocation(componentName, expectedLocation);
        return this;
    }

    @Override
    public WebFileInput componentShouldNotHaveLocation(@NotNull String componentName, @NotNull Location expectedLocation) {
        super.componentShouldNotHaveLocation(componentName, expectedLocation);
        return this;
    }

    // Get Screenshot

    @Override
    public WebFileInput componentShouldLooksLike(@NotNull String componentName, @NotNull Screenshot expectedScreenshot) {
        super.componentShouldLooksLike(componentName, expectedScreenshot);
        return this;
    }

    @Override
    public WebFileInput componentShouldNotLooksLike(@NotNull String componentName, @NotNull Screenshot expectedScreenshot) {
        super.componentShouldNotLooksLike(componentName, expectedScreenshot);
        return this;
    }

    // Get Text

    @Override
    public @Nullable String getText() {
        return runCheck(getEnvironment(), InvocationName.of(GET_TEXT_METHOD, this),
                () -> getActionImplementation(GET_TEXT_METHOD, String.class).execute(this));
    }

    @Override
    public WebFileInput shouldHaveText(@NotNull StringValue expectedValue) {
        runCheck(getEnvironment(), InvocationName.of(SHOULD_HAVE_TEXT_METHOD, this, expectedValue),
                () -> {
                    String actualText = getActionImplementation(GET_TEXT_METHOD, String.class)
                            .execute(this);
                    getActionImplementation(SHOULD_HAVE_TEXT_METHOD, Void.class)
                            .execute(this, actualText, expectedValue);
                });
        return this;
    }

    @Override
    public WebFileInput shouldHaveText(@NotNull NumberValue<?> expectedValue) {
        runCheck(getEnvironment(), InvocationName.of(SHOULD_HAVE_NUMBER_METHOD, this, expectedValue),
                () -> {
                    String actualText = getActionImplementation(GET_TEXT_METHOD, String.class)
                            .execute(this);
                    getActionImplementation(SHOULD_HAVE_NUMBER_METHOD, Void.class)
                            .execute(this, actualText, expectedValue);
                });
        return this;
    }

    @Override
    public WebFileInput shouldNotHaveText(@NotNull StringValue expectedValue) {
        runCheck(getEnvironment(), InvocationName.of(SHOULD_NOT_HAVE_TEXT_METHOD, this, expectedValue),
                () -> {
                    String actualText = getActionImplementation(GET_TEXT_METHOD, String.class)
                            .execute(this);
                    getActionImplementation(SHOULD_NOT_HAVE_TEXT_METHOD, Void.class)
                            .execute(this, actualText, expectedValue);
                });
        return this;
    }

    @Override
    public WebFileInput shouldNotHaveText(@NotNull NumberValue<?> expectedValue) {
        runCheck(getEnvironment(), InvocationName.of(SHOULD_NOT_HAVE_NUMBER_METHOD, this, expectedValue),
                () -> {
                    String actualText = getActionImplementation(GET_TEXT_METHOD, String.class)
                            .execute(this);
                    getActionImplementation(SHOULD_NOT_HAVE_NUMBER_METHOD, Void.class)
                            .execute(this, actualText, expectedValue);
                });
        return this;
    }

    // HoverTo

    @Override
    public WebFileInput hoverTo(boolean withOutOfBounds) {
        super.hoverTo(withOutOfBounds);
        return this;
    }

    // IsDisplayed

    @Override
    public WebFileInput shouldBeDisplayed() {
        super.shouldBeDisplayed();
        return this;
    }

    @Override
    public WebFileInput shouldNotBeDisplayed() {
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
    public WebFileInput shouldBeEnabled() {
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
    public WebFileInput shouldBeDisabled() {
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
    public WebFileInput shouldBeInFocus() {
        super.shouldBeInFocus();
        return this;
    }

    @Override
    public WebFileInput shouldNotBeInFocus() {
        super.shouldNotBeInFocus();
        return this;
    }

    // IsPresent

    @Override
    public WebFileInput shouldBePresent() {
        super.shouldBePresent();
        return this;
    }

    @Override
    public WebFileInput shouldNotBePresent() {
        super.shouldNotBePresent();
        return this;
    }

    // ScrollTo

    @Override
    public WebFileInput scrollTo() {
        super.scrollTo();
        return this;
    }

    // SendKeys

    @Override
    public WebFileInput sendKeys(CharSequence... keys) {
        runCheck(getEnvironment(), InvocationName.of(SEND_KEYS_METHOD, this, keys),
                () -> getActionImplementation(SEND_KEYS_METHOD, Void.class).execute(this, List.of(keys)));
        return this;
    }

    // WebComponents

    @Override
    public WebFileInput componentShouldBePresent(@NotNull String componentName) {
        super.componentShouldBePresent(componentName);
        return this;
    }

    @Override
    public WebFileInput componentShouldNotBePresent(@NotNull String componentName) {
        super.componentShouldNotBePresent(componentName);
        return this;
    }

    @Override
    public WebFileInput componentShouldBeDisplayed(@NotNull String componentName) {
        super.componentShouldBeDisplayed(componentName);
        return this;
    }

    @Override
    public WebFileInput componentShouldNotBeDisplayed(@NotNull String componentName) {
        super.componentShouldNotBeDisplayed(componentName);
        return this;
    }

    // WebProperties

    @Override
    public WebFileInput shouldHavePropertyValue(@NotNull String propertyName, @NotNull StringValue expectedValue) {
        super.shouldHavePropertyValue(propertyName, expectedValue);
        return this;
    }

    @Override
    public WebFileInput shouldHavePropertyValue(@NotNull String propertyName, @NotNull NumberValue<?> expectedValue) {
        super.shouldHavePropertyValue(propertyName, expectedValue);
        return this;
    }

    @Override
    public WebFileInput shouldNotHavePropertyValue(@NotNull String propertyName, @NotNull StringValue expectedValue) {
        super.shouldNotHavePropertyValue(propertyName, expectedValue);
        return this;
    }

    @Override
    public WebFileInput shouldNotHavePropertyValue(@NotNull String propertyName, @NotNull NumberValue<?> expectedValue) {
        super.shouldNotHavePropertyValue(propertyName, expectedValue);
        return this;
    }

}
