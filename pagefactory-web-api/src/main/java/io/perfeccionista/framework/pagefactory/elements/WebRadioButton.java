package io.perfeccionista.framework.pagefactory.elements;

import io.perfeccionista.framework.asserts.WebAssertCondition;
import io.perfeccionista.framework.invocation.runner.InvocationName;
import io.perfeccionista.framework.pagefactory.elements.base.ParentInfoAvailable;
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
@WebLocator(component = SELECTED, xpath = "self::node()//input[@type = 'radio']")
@WebLocator(component = ENABLED, xpath = "self::node()//input[@type = 'radio']")
public interface WebRadioButton extends WebChildElement, ParentInfoAvailable,
        ClickAvailable, IsSelectedAvailable, IsEnabledAvailable, GetLabelAvailable {

    // Actions

    @Override
    WebRadioButton executeAction(@NotNull String name, Object... args);

    @Override
    WebRadioButton executeInteraction(@NotNull String name, @NotNull WebChildElement other, Object... args);

    // Asserts

    @Override
    WebRadioButton should(WebAssertCondition assertCondition);

    @Override
    WebRadioButton should(WebAssertCondition assertCondition, InvocationName invocationName);

    // Click

    @Override
    WebRadioButton click();

    // Get Color

    @Override
    WebRadioButton componentShouldHaveColor(@NotNull String componentName, @NotNull String cssProperty, @NotNull Color expectedColor);

    @Override
    WebRadioButton componentShouldNotHaveColor(@NotNull String componentName, @NotNull String cssProperty, @NotNull Color expectedColor);

    // Get Dimensions

    @Override
    WebRadioButton componentShouldHaveDimensions(@NotNull String componentName, @NotNull Dimensions expectedDimensions);

    @Override
    WebRadioButton componentShouldNotHaveDimensions(@NotNull String componentName, @NotNull Dimensions expectedDimensions);

    // Get Label

    @Override
    WebRadioButton shouldHaveLabel(@NotNull StringValue expectedValue);

    @Override
    WebRadioButton shouldHaveLabel(@NotNull NumberValue<?> expectedValue);

    @Override
    WebRadioButton shouldNotHaveLabel(@NotNull StringValue expectedValue);

    @Override
    WebRadioButton shouldNotHaveLabel(@NotNull NumberValue<?> expectedValue);

    // Get Location

    @Override
    WebRadioButton componentShouldHaveLocation(@NotNull String componentName, @NotNull Location expectedLocation);

    @Override
    WebRadioButton componentShouldNotHaveLocation(@NotNull String componentName, @NotNull Location expectedLocation);

    // Get Screenshot

    @Override
    WebRadioButton componentShouldLooksLike(@NotNull String componentName, @NotNull Screenshot expectedScreenshot);

    @Override
    WebRadioButton componentShouldNotLooksLike(@NotNull String componentName, @NotNull Screenshot expectedScreenshot);

    // HoverTo

    @Override
    WebRadioButton hoverTo(boolean withOutOfBounds);

    // IsDisplayed

    @Override
    WebRadioButton shouldBeDisplayed();

    @Override
    WebRadioButton shouldNotBeDisplayed();

    // IsEnabled

    @Override
    WebRadioButton shouldBeEnabled();

    @Override
    WebRadioButton shouldBeDisabled();

    // IsInFocus

    @Override
    WebRadioButton shouldBeInFocus();

    @Override
    WebRadioButton shouldNotBeInFocus();

    // IsPresent

    @Override
    WebRadioButton shouldBePresent();

    @Override
    WebRadioButton shouldNotBePresent();

    // IsSelected

    @Override
    WebRadioButton shouldBeSelected();

    @Override
    WebRadioButton shouldNotBeSelected();

    // ScrollTo

    @Override
    WebRadioButton scrollTo();

    // WebComponents

    @Override
    WebRadioButton componentShouldBePresent(@NotNull String componentName);

    @Override
    WebRadioButton componentShouldNotBePresent(@NotNull String componentName);

    @Override
    WebRadioButton componentShouldBeDisplayed(@NotNull String componentName);

    @Override
    WebRadioButton componentShouldNotBeDisplayed(@NotNull String componentName);

    // WebProperties

    @Override
    WebRadioButton shouldHavePropertyValue(@NotNull String propertyName, @NotNull StringValue expectedValue);

    @Override
    WebRadioButton shouldHavePropertyValue(@NotNull String propertyName, @NotNull NumberValue<?> expectedValue);

    @Override
    WebRadioButton shouldNotHavePropertyValue(@NotNull String propertyName, @NotNull StringValue expectedValue);

    @Override
    WebRadioButton shouldNotHavePropertyValue(@NotNull String propertyName, @NotNull NumberValue<?> expectedValue);

}
