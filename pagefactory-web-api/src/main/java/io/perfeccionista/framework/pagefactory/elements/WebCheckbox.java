package io.perfeccionista.framework.pagefactory.elements;

import io.perfeccionista.framework.asserts.WebAssertCondition;
import io.perfeccionista.framework.invocation.runner.InvocationName;
import io.perfeccionista.framework.pagefactory.elements.base.WebChildElement;
import io.perfeccionista.framework.pagefactory.elements.locators.WebLocator;
import io.perfeccionista.framework.pagefactory.elements.methods.ClickAvailable;
import io.perfeccionista.framework.pagefactory.elements.methods.Dimensions;
import io.perfeccionista.framework.pagefactory.elements.methods.GetLabelAvailable;
import io.perfeccionista.framework.pagefactory.elements.methods.IsEnabledAvailable;
import io.perfeccionista.framework.pagefactory.elements.methods.IsSelectedAvailable;
import io.perfeccionista.framework.pagefactory.elements.methods.Location;
import io.perfeccionista.framework.pagefactory.screenshots.Screenshot;
import io.perfeccionista.framework.plugin.Color;
import io.perfeccionista.framework.value.number.NumberValue;
import io.perfeccionista.framework.value.string.StringValue;
import org.jetbrains.annotations.NotNull;

import static io.perfeccionista.framework.pagefactory.elements.components.WebComponents.ENABLED;
import static io.perfeccionista.framework.pagefactory.elements.components.WebComponents.LABEL;
import static io.perfeccionista.framework.pagefactory.elements.components.WebComponents.SELECTED;

@WebLocator(component = LABEL, xpath = "self::node()//label")
@WebLocator(component = SELECTED, xpath = "self::node()//input[@type = 'checkbox']")
@WebLocator(component = ENABLED, xpath = "self::node()//input[@type = 'checkbox']")
public interface WebCheckbox extends WebChildElement,
        ClickAvailable, IsSelectedAvailable, IsEnabledAvailable, GetLabelAvailable {

    // Actions

    @Override
    WebCheckbox executeAction(@NotNull String name, Object... args);

    @Override
    WebCheckbox executeInteraction(@NotNull String name, @NotNull WebChildElement other, Object... args);

    // Asserts

    @Override
    WebCheckbox should(WebAssertCondition assertCondition);

    @Override
    WebCheckbox should(WebAssertCondition assertCondition, InvocationName invocationName);

    // Click

    @Override
    WebCheckbox click();

    // Get Color

    @Override
    WebCheckbox componentShouldHaveColor(@NotNull String componentName, @NotNull String cssProperty, @NotNull Color expectedColor);

    @Override
    WebCheckbox componentShouldNotHaveColor(@NotNull String componentName, @NotNull String cssProperty, @NotNull Color expectedColor);

    // Get Dimensions

    @Override
    WebCheckbox componentShouldHaveDimensions(@NotNull String componentName, @NotNull Dimensions expectedDimensions);

    @Override
    WebCheckbox componentShouldNotHaveDimensions(@NotNull String componentName, @NotNull Dimensions expectedDimensions);

    // Get Label

    @Override
    WebCheckbox shouldHaveLabel(@NotNull StringValue expectedValue);

    @Override
    WebCheckbox shouldHaveLabel(@NotNull NumberValue<?> expectedValue);

    @Override
    WebCheckbox shouldNotHaveLabel(@NotNull StringValue expectedValue);

    @Override
    WebCheckbox shouldNotHaveLabel(@NotNull NumberValue<?> expectedValue);

    // Get Location

    @Override
    WebCheckbox componentShouldHaveLocation(@NotNull String componentName, @NotNull Location expectedLocation);

    @Override
    WebCheckbox componentShouldNotHaveLocation(@NotNull String componentName, @NotNull Location expectedLocation);

    // Get Screenshot

    @Override
    WebCheckbox componentShouldLooksLike(@NotNull String componentName, @NotNull Screenshot expectedScreenshot);

    @Override
    WebCheckbox componentShouldNotLooksLike(@NotNull String componentName, @NotNull Screenshot expectedScreenshot);

    // HoverTo

    @Override
    WebCheckbox hoverTo(boolean withOutOfBounds);

    // IsDisplayed

    @Override
    WebCheckbox shouldBeDisplayed();

    @Override
    WebCheckbox shouldNotBeDisplayed();

    // IsEnabled

    @Override
    WebCheckbox shouldBeEnabled();

    @Override
    WebCheckbox shouldBeDisabled();

    // IsInFocus

    @Override
    WebCheckbox shouldBeInFocus();

    @Override
    WebCheckbox shouldNotBeInFocus();

    // IsPresent

    @Override
    WebCheckbox shouldBePresent();

    @Override
    WebCheckbox shouldNotBePresent();

    // IsSelected

    @Override
    WebCheckbox shouldBeSelected();

    @Override
    WebCheckbox shouldNotBeSelected();

    // ScrollTo

    @Override
    WebCheckbox scrollTo();

    // WebComponents

    @Override
    WebCheckbox componentShouldBePresent(@NotNull String componentName);

    @Override
    WebCheckbox componentShouldNotBePresent(@NotNull String componentName);

    @Override
    WebCheckbox componentShouldBeDisplayed(@NotNull String componentName);

    @Override
    WebCheckbox componentShouldNotBeDisplayed(@NotNull String componentName);

    // WebProperties

    @Override
    WebCheckbox shouldHavePropertyValue(@NotNull String propertyName, @NotNull StringValue expectedValue);

    @Override
    WebCheckbox shouldHavePropertyValue(@NotNull String propertyName, @NotNull NumberValue<?> expectedValue);

    @Override
    WebCheckbox shouldNotHavePropertyValue(@NotNull String propertyName, @NotNull StringValue expectedValue);

    @Override
    WebCheckbox shouldNotHavePropertyValue(@NotNull String propertyName, @NotNull NumberValue<?> expectedValue);

}
