package io.perfeccionista.framework.pagefactory.elements;

import io.perfeccionista.framework.asserts.WebAssertCondition;
import io.perfeccionista.framework.invocation.runner.InvocationName;
import io.perfeccionista.framework.pagefactory.elements.actions.JsGetIsDisplayed;
import io.perfeccionista.framework.pagefactory.elements.actions.JsGetLabel;
import io.perfeccionista.framework.pagefactory.elements.actions.JsGetText;
import io.perfeccionista.framework.pagefactory.elements.actions.SeleniumClick;
import io.perfeccionista.framework.pagefactory.elements.actions.SeleniumClose;
import io.perfeccionista.framework.pagefactory.elements.actions.SeleniumOpen;
import io.perfeccionista.framework.pagefactory.elements.actions.WebElementAction;
import io.perfeccionista.framework.pagefactory.elements.asserts.JsAssertShouldBeDisplayed;
import io.perfeccionista.framework.pagefactory.elements.asserts.AssertShouldHaveLabelNumber;
import io.perfeccionista.framework.pagefactory.elements.asserts.AssertShouldHaveLabelText;
import io.perfeccionista.framework.pagefactory.elements.asserts.AssertShouldHaveNumber;
import io.perfeccionista.framework.pagefactory.elements.asserts.AssertShouldHaveText;
import io.perfeccionista.framework.pagefactory.elements.asserts.JsAssertShouldNotBeDisplayed;
import io.perfeccionista.framework.pagefactory.elements.asserts.AssertShouldNotHaveLabelNumber;
import io.perfeccionista.framework.pagefactory.elements.asserts.AssertShouldNotHaveLabelText;
import io.perfeccionista.framework.pagefactory.elements.asserts.AssertShouldNotHaveNumber;
import io.perfeccionista.framework.pagefactory.elements.asserts.AssertShouldNotHaveText;
import io.perfeccionista.framework.pagefactory.elements.base.WebChildElement;
import io.perfeccionista.framework.pagefactory.elements.methods.Dimensions;
import io.perfeccionista.framework.pagefactory.elements.methods.Location;
import io.perfeccionista.framework.pagefactory.filter.list.WebListFilter;
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

// WebDropDownList
@WebElementAction(name = CLICK_METHOD, implementation = SeleniumClick.class)
@WebElementAction(name = CLOSE_METHOD, implementation = SeleniumClose.class)
@WebElementAction(name = GET_LABEL_METHOD, implementation = JsGetLabel.class)
@WebElementAction(name = GET_TEXT_METHOD, implementation = JsGetText.class)
@WebElementAction(name = IS_OPEN_METHOD, implementation = JsGetIsDisplayed.class)
@WebElementAction(name = OPEN_METHOD, implementation = SeleniumOpen.class)
@WebElementAction(name = SHOULD_BE_CLOSED_METHOD, implementation = JsAssertShouldNotBeDisplayed.class)
@WebElementAction(name = SHOULD_BE_OPEN_METHOD, implementation = JsAssertShouldBeDisplayed.class)
@WebElementAction(name = SHOULD_HAVE_NUMBER_LABEL_METHOD, implementation = AssertShouldHaveLabelNumber.class)
@WebElementAction(name = SHOULD_HAVE_NUMBER_METHOD, implementation = AssertShouldHaveNumber.class)
@WebElementAction(name = SHOULD_HAVE_TEXT_LABEL_METHOD, implementation = AssertShouldHaveLabelText.class)
@WebElementAction(name = SHOULD_HAVE_TEXT_METHOD, implementation = AssertShouldHaveText.class)
@WebElementAction(name = SHOULD_NOT_HAVE_NUMBER_LABEL_METHOD, implementation = AssertShouldNotHaveLabelNumber.class)
@WebElementAction(name = SHOULD_NOT_HAVE_NUMBER_METHOD, implementation = AssertShouldNotHaveNumber.class)
@WebElementAction(name = SHOULD_NOT_HAVE_TEXT_LABEL_METHOD, implementation = AssertShouldNotHaveLabelText.class)
@WebElementAction(name = SHOULD_NOT_HAVE_TEXT_METHOD, implementation = AssertShouldNotHaveText.class)
public class WebDropDownListSeleniumImpl extends WebListSeleniumImpl implements WebDropDownList {

    // Actions

    @Override
    public WebDropDownList executeAction(@NotNull String name, Object... args) {
        super.executeAction(name, args);
        return this;
    }

    @Override
    public WebDropDownList executeInteraction(@NotNull String name, @NotNull WebChildElement other, Object... args) {
        super.executeInteraction(name, other, args);
        return this;
    }

    // Asserts

    @Override
    public WebDropDownList should(WebAssertCondition assertCondition) {
        super.should(assertCondition);
        return this;
    }

    @Override
    public WebDropDownList should(WebAssertCondition assertCondition, InvocationName invocationName) {
        super.should(assertCondition, invocationName);
        return this;
    }


    // Click

    @Override
    public WebDropDownList click() {
        runCheck(getEnvironment(), InvocationName.of(CLICK_METHOD, this),
                () -> getActionImplementation(CLICK_METHOD, Void.class).execute(this));
        return this;
    }

    // ClickToElement

    @Override
    public WebDropDownList clickToElement(@NotNull WebListFilter filter) {
        super.clickToElement(filter);
        return this;
    }

    // Close

    @Override
    public WebDropDownList close() {
        runCheck(getEnvironment(), InvocationName.of(CLOSE_METHOD, this),
                () -> getActionImplementation(CLOSE_METHOD, Void.class).execute(this));
        return this;
    }

    // Get Color

    @Override
    public WebDropDownList componentShouldHaveColor(@NotNull String componentName, @NotNull String cssProperty, @NotNull Color expectedColor) {
        super.componentShouldHaveColor(componentName, cssProperty, expectedColor);
        return this;
    }

    @Override
    public WebDropDownList componentShouldNotHaveColor(@NotNull String componentName, @NotNull String cssProperty, @NotNull Color expectedColor) {
        super.componentShouldNotHaveColor(componentName, cssProperty, expectedColor);
        return this;
    }

    // Get Dimensions

    @Override
    public WebDropDownList componentShouldHaveDimensions(@NotNull String componentName, @NotNull Dimensions expectedDimensions) {
        super.componentShouldHaveDimensions(componentName, expectedDimensions);
        return this;
    }

    @Override
    public WebDropDownList componentShouldNotHaveDimensions(@NotNull String componentName, @NotNull Dimensions expectedDimensions) {
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
    public WebDropDownList shouldHaveLabel(@NotNull StringValue expectedValue) {
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
    public WebDropDownList shouldHaveLabel(@NotNull NumberValue<?> expectedValue) {
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
    public WebDropDownList shouldNotHaveLabel(@NotNull StringValue expectedValue) {
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
    public WebDropDownList shouldNotHaveLabel(@NotNull NumberValue<?> expectedValue) {
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
    public WebDropDownList componentShouldHaveLocation(@NotNull String componentName, @NotNull Location expectedLocation) {
        super.componentShouldHaveLocation(componentName, expectedLocation);
        return this;
    }

    @Override
    public WebDropDownList componentShouldNotHaveLocation(@NotNull String componentName, @NotNull Location expectedLocation) {
        super.componentShouldNotHaveLocation(componentName, expectedLocation);
        return this;
    }

    // Get Screenshot

    @Override
    public WebDropDownList componentShouldLooksLike(@NotNull String componentName, @NotNull Screenshot expectedScreenshot) {
        super.componentShouldLooksLike(componentName, expectedScreenshot);
        return this;
    }

    @Override
    public WebDropDownList componentShouldNotLooksLike(@NotNull String componentName, @NotNull Screenshot expectedScreenshot) {
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
    public WebDropDownList shouldHaveText(@NotNull StringValue expectedValue) {
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
    public WebDropDownList shouldHaveText(@NotNull NumberValue<?> expectedValue) {
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
    public WebDropDownList shouldNotHaveText(@NotNull StringValue expectedValue) {
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
    public WebDropDownList shouldNotHaveText(@NotNull NumberValue<?> expectedValue) {
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
    public WebDropDownList hoverTo(boolean withOutOfBounds) {
        super.hoverTo(withOutOfBounds);
        return this;
    }

    // IsDisplayed

    @Override
    public WebDropDownList shouldBeDisplayed() {
        super.shouldBeDisplayed();
        return this;
    }

    @Override
    public WebDropDownList shouldNotBeDisplayed() {
        super.shouldNotBeDisplayed();
        return this;
    }

    // IsInFocus

    @Override
    public WebDropDownList shouldBeInFocus() {
        super.shouldBeInFocus();
        return this;
    }

    @Override
    public WebDropDownList shouldNotBeInFocus() {
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
    public WebDropDownList shouldBeOpen() {
        runCheck(getEnvironment(), InvocationName.of(SHOULD_BE_OPEN_METHOD, this, UL),
                () -> getActionImplementation(SHOULD_BE_OPEN_METHOD, Void.class).execute(this, UL));
        return this;
    }

    @Override
    public WebDropDownList shouldBeClosed() {
        runCheck(getEnvironment(), InvocationName.of(SHOULD_BE_CLOSED_METHOD, this, UL),
                () -> getActionImplementation(SHOULD_BE_CLOSED_METHOD, Void.class).execute(this, UL));
        return this;
    }

    // IsPresent

    @Override
    public WebDropDownList shouldBePresent() {
        super.shouldBePresent();
        return this;
    }

    @Override
    public WebDropDownList shouldNotBePresent() {
        super.shouldNotBePresent();
        return this;
    }

    // Open

    @Override
    public WebDropDownList open() {
        runCheck(getEnvironment(), InvocationName.of(OPEN_METHOD, this),
                () -> getActionImplementation(OPEN_METHOD, Void.class).execute(this));
        return this;
    }

    // ScrollTo

    @Override
    public WebDropDownList scrollTo() {
        super.scrollTo();
        return this;
    }

    // ScrollToElement

    @Override
    public WebDropDownList scrollToElement(@NotNull WebListFilter filter) {
        super.scrollToElement(filter);
        return this;
    }

    // Size

    @Override
    public WebDropDownList shouldHaveSize(@NotNull NumberValue<Integer> expectedSize) {
        super.shouldHaveSize(expectedSize);
        return this;
    }

    // WebComponents

    @Override
    public WebDropDownList componentShouldBePresent(@NotNull String componentName) {
        super.componentShouldBePresent(componentName);
        return this;
    }

    @Override
    public WebDropDownList componentShouldNotBePresent(@NotNull String componentName) {
        super.componentShouldNotBePresent(componentName);
        return this;
    }

    @Override
    public WebDropDownList componentShouldBeDisplayed(@NotNull String componentName) {
        super.componentShouldBeDisplayed(componentName);
        return this;
    }

    @Override
    public WebDropDownList componentShouldNotBeDisplayed(@NotNull String componentName) {
        super.componentShouldNotBeDisplayed(componentName);
        return this;
    }

    // WebProperties

    @Override
    public WebDropDownList shouldHavePropertyValue(@NotNull String propertyName, @NotNull StringValue expectedValue) {
        super.shouldHavePropertyValue(propertyName, expectedValue);
        return this;
    }

    @Override
    public WebDropDownList shouldHavePropertyValue(@NotNull String propertyName, @NotNull NumberValue<?> expectedValue) {
        super.shouldHavePropertyValue(propertyName, expectedValue);
        return this;
    }

    @Override
    public WebDropDownList shouldNotHavePropertyValue(@NotNull String propertyName, @NotNull StringValue expectedValue) {
        super.shouldNotHavePropertyValue(propertyName, expectedValue);
        return this;
    }

    @Override
    public WebDropDownList shouldNotHavePropertyValue(@NotNull String propertyName, @NotNull NumberValue<?> expectedValue) {
        super.shouldNotHavePropertyValue(propertyName, expectedValue);
        return this;
    }

}
