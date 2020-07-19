package io.perfeccionista.framework.pagefactory.elements;

import io.perfeccionista.framework.asserts.WebAssertCondition;
import io.perfeccionista.framework.invocation.runner.InvocationName;
import io.perfeccionista.framework.pagefactory.elements.base.WebChildElement;
import io.perfeccionista.framework.pagefactory.elements.methods.Dimensions;
import io.perfeccionista.framework.pagefactory.elements.methods.Location;
import io.perfeccionista.framework.pagefactory.filter.textlist.WebTextListFilterBuilder;
import io.perfeccionista.framework.pagefactory.screenshots.Screenshot;
import io.perfeccionista.framework.plugin.Color;
import io.perfeccionista.framework.value.number.NumberValue;
import io.perfeccionista.framework.value.string.StringValue;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import static io.perfeccionista.framework.invocation.wrappers.CheckActionWrapper.runCheck;
import static io.perfeccionista.framework.pagefactory.elements.components.WebComponents.UL;
import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.CLICK_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.CLOSE_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.GET_LABEL_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.GET_TEXT_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.IS_OPEN_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.OPEN_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.SHOULD_BE_CLOSED_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.SHOULD_BE_OPEN_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.SHOULD_HAVE_NUMBER_LABEL_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.SHOULD_HAVE_NUMBER_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.SHOULD_HAVE_TEXT_LABEL_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.SHOULD_HAVE_TEXT_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.SHOULD_NOT_HAVE_NUMBER_LABEL_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.SHOULD_NOT_HAVE_NUMBER_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.SHOULD_NOT_HAVE_TEXT_LABEL_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.SHOULD_NOT_HAVE_TEXT_METHOD;

public class WebTextDropDownListSeleniumImpl extends WebTextListSeleniumImpl implements WebTextDropDownList {

    // Actions

    @Override
    public WebTextDropDownList executeAction(@NotNull String actionName, Object... args) {
        super.executeAction(actionName, args);
        return this;
    }

    @Override
    public WebTextDropDownList executeInteraction(@NotNull String interactionName, @NotNull WebChildElement other, Object... args) {
        super.executeInteraction(interactionName, other, args);
        return this;
    }

    // Asserts

    @Override
    public WebTextDropDownList should(WebAssertCondition assertCondition) {
        super.should(assertCondition);
        return this;
    }

    @Override
    public WebTextDropDownList should(WebAssertCondition assertCondition, InvocationName invocationName) {
        super.should(assertCondition, invocationName);
        return this;
    }

    // Click

    @Override
    public WebTextDropDownList click() {
        runCheck(getEnvironment(), InvocationName.of(CLICK_METHOD, this),
                () -> getActionImplementation(CLICK_METHOD, Void.class).execute(this));
        return this;
    }

    // ClickToElement

    @Override
    public WebTextDropDownList clickToElement(@NotNull WebTextListFilterBuilder filter) {
        super.clickToElement(filter);
        return this;
    }

    // Close

    @Override
    public WebTextDropDownList close() {
        runCheck(getEnvironment(), InvocationName.of(CLOSE_METHOD, this),
                () -> getActionImplementation(CLOSE_METHOD, Void.class).execute(this));
        return this;
    }

    // Get Color

    @Override
    public WebTextDropDownList componentShouldHaveColor(@NotNull String componentName, @NotNull String cssProperty, @NotNull Color expectedColor) {
        super.componentShouldHaveColor(componentName, cssProperty, expectedColor);
        return this;
    }

    @Override
    public WebTextDropDownList componentShouldNotHaveColor(@NotNull String componentName, @NotNull String cssProperty, @NotNull Color expectedColor) {
        super.componentShouldNotHaveColor(componentName, cssProperty, expectedColor);
        return this;
    }

    // Get Dimensions

    @Override
    public WebTextDropDownList componentShouldHaveDimensions(@NotNull String componentName, @NotNull Dimensions expectedDimensions) {
        super.componentShouldHaveDimensions(componentName, expectedDimensions);
        return this;
    }

    @Override
    public WebTextDropDownList componentShouldNotHaveDimensions(@NotNull String componentName, @NotNull Dimensions expectedDimensions) {
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
    public WebTextDropDownList shouldHaveLabel(@NotNull StringValue expectedValue) {
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
    public WebTextDropDownList shouldHaveLabel(@NotNull NumberValue<?> expectedValue) {
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
    public WebTextDropDownList shouldNotHaveLabel(@NotNull StringValue expectedValue) {
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
    public WebTextDropDownList shouldNotHaveLabel(@NotNull NumberValue<?> expectedValue) {
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
    public WebTextDropDownList componentShouldHaveLocation(@NotNull String componentName, @NotNull Location expectedLocation) {
        super.componentShouldHaveLocation(componentName, expectedLocation);
        return this;
    }

    @Override
    public WebTextDropDownList componentShouldNotHaveLocation(@NotNull String componentName, @NotNull Location expectedLocation) {
        super.componentShouldNotHaveLocation(componentName, expectedLocation);
        return this;
    }

    // Get Screenshot

    @Override
    public WebTextDropDownList componentShouldLooksLike(@NotNull String componentName, @NotNull Screenshot expectedScreenshot) {
        super.componentShouldLooksLike(componentName, expectedScreenshot);
        return this;
    }

    @Override
    public WebTextDropDownList componentShouldNotLooksLike(@NotNull String componentName, @NotNull Screenshot expectedScreenshot) {
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
    public WebTextDropDownList shouldHaveText(@NotNull StringValue expectedValue) {
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
    public WebTextDropDownList shouldHaveText(@NotNull NumberValue<?> expectedValue) {
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
    public WebTextDropDownList shouldNotHaveText(@NotNull StringValue expectedValue) {
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
    public WebTextDropDownList shouldNotHaveText(@NotNull NumberValue<?> expectedValue) {
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
    public WebTextDropDownList hoverTo(boolean withOutOfBounds) {
        super.hoverTo(withOutOfBounds);
        return this;
    }

    // IsDisplayed

    @Override
    public WebTextDropDownList shouldBeDisplayed() {
        super.shouldBeDisplayed();
        return this;
    }

    @Override
    public WebTextDropDownList shouldNotBeDisplayed() {
        super.shouldNotBeDisplayed();
        return this;
    }

    // IsInFocus

    @Override
    public WebTextDropDownList shouldBeInFocus() {
        super.shouldBeInFocus();
        return this;
    }

    @Override
    public WebTextDropDownList shouldNotBeInFocus() {
        super.shouldNotBeInFocus();
        return this;
    }

    // IsOpen

    @Override
    public boolean isOpen() {
        return runCheck(getEnvironment(), InvocationName.of(IS_OPEN_METHOD, this),
                () -> getActionImplementation(IS_OPEN_METHOD, Boolean.class).execute(this));
    }

    @Override
    public WebTextDropDownList shouldBeOpen() {
        runCheck(getEnvironment(), InvocationName.of(SHOULD_BE_OPEN_METHOD, this, UL),
                () -> getActionImplementation(SHOULD_BE_OPEN_METHOD, Void.class).execute(this, UL));
        return this;
    }

    @Override
    public WebTextDropDownList shouldBeClosed() {
        runCheck(getEnvironment(), InvocationName.of(SHOULD_BE_CLOSED_METHOD, this, UL),
                () -> getActionImplementation(SHOULD_BE_CLOSED_METHOD, Void.class).execute(this, UL));
        return this;
    }

    // IsPresent

    @Override
    public WebTextDropDownList shouldBePresent() {
        super.shouldBePresent();
        return this;
    }

    @Override
    public WebTextDropDownList shouldNotBePresent() {
        super.shouldNotBePresent();
        return this;
    }

    // Open

    @Override
    public WebTextDropDownList open() {
        runCheck(getEnvironment(), InvocationName.of(OPEN_METHOD, this),
                () -> getActionImplementation(OPEN_METHOD, Void.class).execute(this));
        return this;
    }

    // ScrollTo

    @Override
    public WebTextDropDownList scrollTo() {
        super.scrollTo();
        return this;
    }

    // ScrollToElement

    @Override
    public WebTextDropDownList scrollToElement(@NotNull WebTextListFilterBuilder filter) {
        super.scrollToElement(filter);
        return this;
    }

    // Size

    @Override
    public WebTextDropDownList shouldHaveSize(@NotNull NumberValue<Integer> expectedSize) {
        super.shouldHaveSize(expectedSize);
        return this;
    }

    // WebComponents

    @Override
    public WebTextDropDownList componentShouldBePresent(@NotNull String componentName) {
        super.componentShouldBePresent(componentName);
        return this;
    }

    @Override
    public WebTextDropDownList componentShouldNotBePresent(@NotNull String componentName) {
        super.componentShouldNotBePresent(componentName);
        return this;
    }

    @Override
    public WebTextDropDownList componentShouldBeDisplayed(@NotNull String componentName) {
        super.componentShouldBeDisplayed(componentName);
        return this;
    }

    @Override
    public WebTextDropDownList componentShouldNotBeDisplayed(@NotNull String componentName) {
        super.componentShouldNotBeDisplayed(componentName);
        return this;
    }

    // WebProperties

    @Override
    public WebTextDropDownList shouldHavePropertyValue(@NotNull String propertyName, @NotNull StringValue expectedValue) {
        super.shouldHavePropertyValue(propertyName, expectedValue);
        return this;
    }

    @Override
    public WebTextDropDownList shouldHavePropertyValue(@NotNull String propertyName, @NotNull NumberValue<?> expectedValue) {
        super.shouldHavePropertyValue(propertyName, expectedValue);
        return this;
    }

    @Override
    public WebTextDropDownList shouldNotHavePropertyValue(@NotNull String propertyName, @NotNull StringValue expectedValue) {
        super.shouldNotHavePropertyValue(propertyName, expectedValue);
        return this;
    }

    @Override
    public WebTextDropDownList shouldNotHavePropertyValue(@NotNull String propertyName, @NotNull NumberValue<?> expectedValue) {
        super.shouldNotHavePropertyValue(propertyName, expectedValue);
        return this;
    }

}
