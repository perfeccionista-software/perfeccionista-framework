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


public interface WebLink extends WebChildElement,
        ClickAvailable, GetTextAvailable {

    // Actions

    @Override
    WebLink executeAction(@NotNull String name, Object... args);

    @Override
    WebLink executeInteraction(@NotNull String name, @NotNull WebChildElement other, Object... args);

    // Asserts

    @Override
    WebLink should(WebAssertCondition assertCondition);

    @Override
    WebLink should(WebAssertCondition assertCondition, InvocationName invocationName);

    // Click

    @Override
    WebLink click();

    // Get Color

    @Override
    WebLink componentShouldHaveColor(@NotNull String componentName, @NotNull String cssProperty, @NotNull Color expectedColor);

    @Override
    WebLink componentShouldNotHaveColor(@NotNull String componentName, @NotNull String cssProperty, @NotNull Color expectedColor);

    // Get Dimensions

    @Override
    WebLink componentShouldHaveDimensions(@NotNull String componentName, @NotNull Dimensions expectedDimensions);

    @Override
    WebLink componentShouldNotHaveDimensions(@NotNull String componentName, @NotNull Dimensions expectedDimensions);

    // Get Location

    @Override
    WebLink componentShouldHaveLocation(@NotNull String componentName, @NotNull Location expectedLocation);

    @Override
    WebLink componentShouldNotHaveLocation(@NotNull String componentName, @NotNull Location expectedLocation);

    // Get Screenshot

    @Override
    WebLink componentShouldLooksLike(@NotNull String componentName, @NotNull Screenshot expectedScreenshot);

    @Override
    WebLink componentShouldNotLooksLike(@NotNull String componentName, @NotNull Screenshot expectedScreenshot);

    // Get Text

    @Override
    WebLink shouldHaveText(@NotNull StringValue expectedValue);

    @Override
    WebLink shouldHaveText(@NotNull NumberValue<?> expectedValue);

    @Override
    WebLink shouldNotHaveText(@NotNull StringValue expectedValue);

    @Override
    WebLink shouldNotHaveText(@NotNull NumberValue<?> expectedValue);

    // HoverTo

    @Override
    WebLink hoverTo(boolean withOutOfBounds);

    // IsDisplayed

    @Override
    WebLink shouldBeDisplayed();

    @Override
    WebLink shouldNotBeDisplayed();

    // IsInFocus

    @Override
    WebLink shouldBeInFocus();

    @Override
    WebLink shouldNotBeInFocus();

    // IsPresent

    @Override
    WebLink shouldBePresent();

    @Override
    WebLink shouldNotBePresent();

    // ScrollTo

    @Override
    WebLink scrollTo();

    // WebComponents

    @Override
    WebLink componentShouldBePresent(@NotNull String componentName);

    @Override
    WebLink componentShouldNotBePresent(@NotNull String componentName);

    @Override
    WebLink componentShouldBeDisplayed(@NotNull String componentName);

    @Override
    WebLink componentShouldNotBeDisplayed(@NotNull String componentName);

    // WebProperties

    @Override
    WebLink shouldHavePropertyValue(@NotNull String propertyName, @NotNull StringValue expectedValue);

    @Override
    WebLink shouldHavePropertyValue(@NotNull String propertyName, @NotNull NumberValue<?> expectedValue);

    @Override
    WebLink shouldNotHavePropertyValue(@NotNull String propertyName, @NotNull StringValue expectedValue);

    @Override
    WebLink shouldNotHavePropertyValue(@NotNull String propertyName, @NotNull NumberValue<?> expectedValue);

}