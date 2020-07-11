package io.perfeccionista.framework.pagefactory.elements;

import io.perfeccionista.framework.asserts.WebAssertCondition;
import io.perfeccionista.framework.invocation.runner.InvocationName;
import io.perfeccionista.framework.pagefactory.elements.base.WebChildElement;
import io.perfeccionista.framework.pagefactory.elements.methods.ClickAvailable;
import io.perfeccionista.framework.pagefactory.elements.methods.Dimensions;
import io.perfeccionista.framework.pagefactory.elements.methods.GetTextAvailable;
import io.perfeccionista.framework.pagefactory.elements.methods.Location;
import io.perfeccionista.framework.pagefactory.screenshots.Screenshot;
import io.perfeccionista.framework.plugin.Color;
import io.perfeccionista.framework.value.number.NumberValue;
import io.perfeccionista.framework.value.string.StringValue;
import org.jetbrains.annotations.NotNull;

public interface WebButton extends WebChildElement,
        ClickAvailable, GetTextAvailable {

    // Actions

    @Override
    WebButton executeAction(@NotNull String name, Object... args);

    @Override
    WebButton executeInteraction(@NotNull String name, @NotNull WebChildElement other, Object... args);

    // Asserts

    @Override
    WebButton should(WebAssertCondition assertCondition);

    @Override
    WebButton should(WebAssertCondition assertCondition, InvocationName invocationName);

    // Click

    @Override
    WebButton click();

    // Get Color

    @Override
    WebButton componentShouldHaveColor(@NotNull String componentName, @NotNull String cssProperty, @NotNull Color expectedColor);

    @Override
    WebButton componentShouldNotHaveColor(@NotNull String componentName, @NotNull String cssProperty, @NotNull Color expectedColor);

    // Get Dimensions

    @Override
    WebButton componentShouldHaveDimensions(@NotNull String componentName, @NotNull Dimensions expectedDimensions);

    @Override
    WebButton componentShouldNotHaveDimensions(@NotNull String componentName, @NotNull Dimensions expectedDimensions);

    // Get Location

    @Override
    WebButton componentShouldHaveLocation(@NotNull String componentName, @NotNull Location expectedLocation);

    @Override
    WebButton componentShouldNotHaveLocation(@NotNull String componentName, @NotNull Location expectedLocation);

    // Get Screenshot

    @Override
    WebButton componentShouldLooksLike(@NotNull String componentName, @NotNull Screenshot expectedScreenshot);

    @Override
    WebButton componentShouldNotLooksLike(@NotNull String componentName, @NotNull Screenshot expectedScreenshot);

    // Get Text

    @Override
    WebButton shouldHaveText(@NotNull StringValue expectedValue);

    @Override
    WebButton shouldHaveText(@NotNull NumberValue<?> expectedValue);

    @Override
    WebButton shouldNotHaveText(@NotNull StringValue expectedValue);

    @Override
    WebButton shouldNotHaveText(@NotNull NumberValue<?> expectedValue);

    // HoverTo

    @Override
    WebButton hoverTo(boolean withOutOfBounds);

    // IsDisplayed

    @Override
    WebButton shouldBeDisplayed();

    @Override
    WebButton shouldNotBeDisplayed();

    // IsInFocus

    @Override
    WebButton shouldBeInFocus();

    @Override
    WebButton shouldNotBeInFocus();

    // IsPresent

    @Override
    WebButton shouldBePresent();

    @Override
    WebButton shouldNotBePresent();

    // ScrollTo

    @Override
    WebButton scrollTo();

    // WebComponents

    @Override
    WebButton componentShouldBePresent(@NotNull String componentName);

    @Override
    WebButton componentShouldNotBePresent(@NotNull String componentName);

    @Override
    WebButton componentShouldBeDisplayed(@NotNull String componentName);

    @Override
    WebButton componentShouldNotBeDisplayed(@NotNull String componentName);

    // WebProperties

    @Override
    WebButton shouldHavePropertyValue(@NotNull String propertyName, @NotNull StringValue expectedValue);

    @Override
    WebButton shouldHavePropertyValue(@NotNull String propertyName, @NotNull NumberValue<?> expectedValue);

    @Override
    WebButton shouldNotHavePropertyValue(@NotNull String propertyName, @NotNull StringValue expectedValue);

    @Override
    WebButton shouldNotHavePropertyValue(@NotNull String propertyName, @NotNull NumberValue<?> expectedValue);

}
