package io.perfeccionista.framework.pagefactory.elements;

import io.perfeccionista.framework.asserts.WebAssertCondition;
import io.perfeccionista.framework.invocation.runner.InvocationName;
import io.perfeccionista.framework.pagefactory.elements.base.WebChildElement;
import io.perfeccionista.framework.pagefactory.elements.base.WebParentElement;
import io.perfeccionista.framework.pagefactory.elements.methods.Dimensions;
import io.perfeccionista.framework.pagefactory.elements.methods.Location;
import io.perfeccionista.framework.pagefactory.screenshots.Screenshot;
import io.perfeccionista.framework.plugin.Color;
import io.perfeccionista.framework.value.number.NumberValue;
import io.perfeccionista.framework.value.string.StringValue;
import org.jetbrains.annotations.NotNull;

public interface WebBlock extends WebChildElement, WebParentElement {

    // Actions

    @Override
    WebBlock executeAction(@NotNull String name, Object... args);

    @Override
    WebBlock executeInteraction(@NotNull String name, @NotNull WebChildElement other, Object... args);

    // Asserts

    @Override
    WebBlock should(WebAssertCondition assertCondition);

    @Override
    WebBlock should(WebAssertCondition assertCondition, InvocationName invocationName);

    // Get Color

    @Override
    WebBlock componentShouldHaveColor(@NotNull String componentName, @NotNull String cssProperty, @NotNull Color expectedColor);

    @Override
    WebBlock componentShouldNotHaveColor(@NotNull String componentName, @NotNull String cssProperty, @NotNull Color expectedColor);

    // Get Dimensions

    @Override
    WebBlock componentShouldHaveDimensions(@NotNull String componentName, @NotNull Dimensions expectedDimensions);

    @Override
    WebBlock componentShouldNotHaveDimensions(@NotNull String componentName, @NotNull Dimensions expectedDimensions);

    // Get Location

    @Override
    WebBlock componentShouldHaveLocation(@NotNull String componentName, @NotNull Location expectedLocation);

    @Override
    WebBlock componentShouldNotHaveLocation(@NotNull String componentName, @NotNull Location expectedLocation);

    // Get Screenshot

    @Override
    WebBlock componentShouldLooksLike(@NotNull String componentName, @NotNull Screenshot expectedScreenshot);

    @Override
    WebBlock componentShouldNotLooksLike(@NotNull String componentName, @NotNull Screenshot expectedScreenshot);

    // HoverTo

    @Override
    WebBlock hoverTo(boolean withOutOfBounds);

    // IsDisplayed

    @Override
    WebBlock shouldBeDisplayed();

    @Override
    WebBlock shouldNotBeDisplayed();

    // IsInFocus

    @Override
    WebBlock shouldBeInFocus();

    @Override
    WebBlock shouldNotBeInFocus();

    // IsPresent

    @Override
    WebBlock shouldBePresent();

    @Override
    WebBlock shouldNotBePresent();

    // ScrollTo

    @Override
    WebBlock scrollTo();

    // WebComponents

    @Override
    WebBlock componentShouldBePresent(@NotNull String componentName);

    @Override
    WebBlock componentShouldNotBePresent(@NotNull String componentName);

    @Override
    WebBlock componentShouldBeDisplayed(@NotNull String componentName);

    @Override
    WebBlock componentShouldNotBeDisplayed(@NotNull String componentName);

    // WebProperties

    @Override
    WebBlock shouldHavePropertyValue(@NotNull String propertyName, @NotNull StringValue expectedValue);

    @Override
    WebBlock shouldHavePropertyValue(@NotNull String propertyName, @NotNull NumberValue<?> expectedValue);

    @Override
    WebBlock shouldNotHavePropertyValue(@NotNull String propertyName, @NotNull StringValue expectedValue);

    @Override
    WebBlock shouldNotHavePropertyValue(@NotNull String propertyName, @NotNull NumberValue<?> expectedValue);

}
