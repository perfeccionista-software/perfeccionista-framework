package io.perfeccionista.framework.pagefactory.elements;

import io.perfeccionista.framework.asserts.WebAssertCondition;
import io.perfeccionista.framework.invocation.runner.InvocationName;
import io.perfeccionista.framework.pagefactory.elements.base.WebChildElement;
import io.perfeccionista.framework.pagefactory.elements.methods.Dimensions;
import io.perfeccionista.framework.pagefactory.elements.methods.GetTextAvailable;
import io.perfeccionista.framework.pagefactory.elements.methods.Location;
import io.perfeccionista.framework.pagefactory.screenshots.Screenshot;
import io.perfeccionista.framework.plugin.Color;
import io.perfeccionista.framework.value.number.NumberValue;
import io.perfeccionista.framework.value.string.StringValue;
import org.jetbrains.annotations.NotNull;

public interface WebTextBlock extends WebChildElement,
        GetTextAvailable {

    // Actions

    @Override
    WebTextBlock executeAction(@NotNull String name, Object... args);

    @Override
    WebTextBlock executeInteraction(@NotNull String name, @NotNull WebChildElement other, Object... args);

    // Asserts

    @Override
    WebTextBlock should(WebAssertCondition assertCondition);

    @Override
    WebTextBlock should(WebAssertCondition assertCondition, InvocationName invocationName);

    // Get Color

    @Override
    WebTextBlock componentShouldHaveColor(@NotNull String componentName, @NotNull String cssProperty, @NotNull Color expectedColor);

    @Override
    WebTextBlock componentShouldNotHaveColor(@NotNull String componentName, @NotNull String cssProperty, @NotNull Color expectedColor);

    // Get Dimensions

    @Override
    WebTextBlock componentShouldHaveDimensions(@NotNull String componentName, @NotNull Dimensions expectedDimensions);

    @Override
    WebTextBlock componentShouldNotHaveDimensions(@NotNull String componentName, @NotNull Dimensions expectedDimensions);

    // Get Location

    @Override
    WebTextBlock componentShouldHaveLocation(@NotNull String componentName, @NotNull Location expectedLocation);

    @Override
    WebTextBlock componentShouldNotHaveLocation(@NotNull String componentName, @NotNull Location expectedLocation);

    // Get Screenshot

    @Override
    WebTextBlock componentShouldLooksLike(@NotNull String componentName, @NotNull Screenshot expectedScreenshot);

    @Override
    WebTextBlock componentShouldNotLooksLike(@NotNull String componentName, @NotNull Screenshot expectedScreenshot);

    // Get Text

    @Override
    WebTextBlock shouldHaveText(@NotNull StringValue expectedValue);

    @Override
    WebTextBlock shouldHaveText(@NotNull NumberValue<?> expectedValue);

    @Override
    WebTextBlock shouldNotHaveText(@NotNull StringValue expectedValue);

    @Override
    WebTextBlock shouldNotHaveText(@NotNull NumberValue<?> expectedValue);

    // HoverTo

    @Override
    WebTextBlock hoverTo(boolean withOutOfBounds);

    // IsDisplayed

    @Override
    WebTextBlock shouldBeDisplayed();

    @Override
    WebTextBlock shouldNotBeDisplayed();

    // IsInFocus

    @Override
    WebTextBlock shouldBeInFocus();

    @Override
    WebTextBlock shouldNotBeInFocus();

    // IsPresent

    @Override
    WebTextBlock shouldBePresent();

    @Override
    WebTextBlock shouldNotBePresent();

    // ScrollTo

    @Override
    WebTextBlock scrollTo();

    // WebComponent

    @Override
    WebTextBlock componentShouldBePresent(@NotNull String componentName);

    @Override
    WebTextBlock componentShouldNotBePresent(@NotNull String componentName);

    @Override
    WebTextBlock componentShouldBeDisplayed(@NotNull String componentName);

    @Override
    WebTextBlock componentShouldNotBeDisplayed(@NotNull String componentName);

    // WebProperties

    @Override
    WebTextBlock shouldHavePropertyValue(@NotNull String propertyName, @NotNull StringValue expectedValue);

    @Override
    WebTextBlock shouldHavePropertyValue(@NotNull String propertyName, @NotNull NumberValue<?> expectedValue);

    @Override
    WebTextBlock shouldNotHavePropertyValue(@NotNull String propertyName, @NotNull StringValue expectedValue);

    @Override
    WebTextBlock shouldNotHavePropertyValue(@NotNull String propertyName, @NotNull NumberValue<?> expectedValue);

}
