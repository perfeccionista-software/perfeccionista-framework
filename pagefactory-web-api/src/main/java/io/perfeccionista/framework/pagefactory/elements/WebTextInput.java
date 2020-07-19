package io.perfeccionista.framework.pagefactory.elements;

import io.perfeccionista.framework.asserts.WebAssertCondition;
import io.perfeccionista.framework.invocation.runner.InvocationName;
import io.perfeccionista.framework.pagefactory.elements.base.WebChildElement;
import io.perfeccionista.framework.pagefactory.elements.locators.WebLocator;
import io.perfeccionista.framework.pagefactory.elements.methods.ClearAvailable;
import io.perfeccionista.framework.pagefactory.elements.methods.ClickAvailable;
import io.perfeccionista.framework.pagefactory.elements.methods.Dimensions;
import io.perfeccionista.framework.pagefactory.elements.methods.GetLabelAvailable;
import io.perfeccionista.framework.pagefactory.elements.methods.GetTextAvailable;
import io.perfeccionista.framework.pagefactory.elements.methods.IsEnabledAvailable;
import io.perfeccionista.framework.pagefactory.elements.methods.Location;
import io.perfeccionista.framework.pagefactory.elements.methods.SendKeysAvailable;
import io.perfeccionista.framework.pagefactory.screenshots.Screenshot;
import io.perfeccionista.framework.plugin.Color;
import io.perfeccionista.framework.value.number.NumberValue;
import io.perfeccionista.framework.value.string.StringValue;
import org.jetbrains.annotations.NotNull;

import static io.perfeccionista.framework.pagefactory.elements.components.WebComponents.LABEL;


@WebLocator(component = LABEL, xpath = "preceding::label | following::label | parent::label")
public interface WebTextInput extends WebChildElement,
        GetTextAvailable, GetLabelAvailable, ClickAvailable, SendKeysAvailable, ClearAvailable, IsEnabledAvailable {

    // Actions

    @Override
    WebTextInput executeAction(@NotNull String name, Object... args);

    @Override
    WebTextInput executeInteraction(@NotNull String name, @NotNull WebChildElement other, Object... args);

    // Asserts

    @Override
    WebTextInput should(WebAssertCondition assertCondition);

    @Override
    WebTextInput should(WebAssertCondition assertCondition, InvocationName invocationName);

    // Clear

    @Override
    WebTextInput clear();

    // Click

    @Override
    WebTextInput click();

    // Get Color

    @Override
    WebTextInput componentShouldHaveColor(@NotNull String componentName, @NotNull String cssProperty, @NotNull Color expectedColor);

    @Override
    WebTextInput componentShouldNotHaveColor(@NotNull String componentName, @NotNull String cssProperty, @NotNull Color expectedColor);

    // Get Dimensions

    @Override
    WebTextInput componentShouldHaveDimensions(@NotNull String componentName, @NotNull Dimensions expectedDimensions);

    @Override
    WebTextInput componentShouldNotHaveDimensions(@NotNull String componentName, @NotNull Dimensions expectedDimensions);

    // Get Label

    @Override
    WebTextInput shouldHaveLabel(@NotNull StringValue expectedValue);

    @Override
    WebTextInput shouldHaveLabel(@NotNull NumberValue<?> expectedValue);

    @Override
    WebTextInput shouldNotHaveLabel(@NotNull StringValue expectedValue);

    @Override
    WebTextInput shouldNotHaveLabel(@NotNull NumberValue<?> expectedValue);

    // Get Location

    @Override
    WebTextInput componentShouldHaveLocation(@NotNull String componentName, @NotNull Location expectedLocation);

    @Override
    WebTextInput componentShouldNotHaveLocation(@NotNull String componentName, @NotNull Location expectedLocation);

    // Get Screenshot

    @Override
    WebTextInput componentShouldLooksLike(@NotNull String componentName, @NotNull Screenshot expectedScreenshot);

    @Override
    WebTextInput componentShouldNotLooksLike(@NotNull String componentName, @NotNull Screenshot expectedScreenshot);

    // Get Text

    @Override
    WebTextInput shouldHaveText(@NotNull StringValue expectedValue);

    @Override
    WebTextInput shouldHaveText(@NotNull NumberValue<?> expectedValue);

    @Override
    WebTextInput shouldNotHaveText(@NotNull StringValue expectedValue);

    @Override
    WebTextInput shouldNotHaveText(@NotNull NumberValue<?> expectedValue);

    // HoverTo

    @Override
    WebTextInput hoverTo(boolean withOutOfBounds);

    // IsDisplayed

    @Override
    WebTextInput shouldBeDisplayed();

    @Override
    WebTextInput shouldNotBeDisplayed();

    // IsEnabled

    @Override
    WebTextInput shouldBeEnabled();

    @Override
    WebTextInput shouldBeDisabled();

    // IsInFocus

    @Override
    WebTextInput shouldBeInFocus();

    @Override
    WebTextInput shouldNotBeInFocus();

    // IsPresent

    @Override
    WebTextInput shouldBePresent();

    @Override
    WebTextInput shouldNotBePresent();

    // ScrollTo

    @Override
    WebTextInput scrollTo();

    // SendKeys

    @Override
    WebTextInput sendKeys(CharSequence... keys);

    // WebComponent

    @Override
    WebTextInput componentShouldBePresent(@NotNull String componentName);

    @Override
    WebTextInput componentShouldNotBePresent(@NotNull String componentName);

    @Override
    WebTextInput componentShouldBeDisplayed(@NotNull String componentName);

    @Override
    WebTextInput componentShouldNotBeDisplayed(@NotNull String componentName);

    // WebProperties

    @Override
    WebTextInput shouldHavePropertyValue(@NotNull String propertyName, @NotNull StringValue expectedValue);

    @Override
    WebTextInput shouldHavePropertyValue(@NotNull String propertyName, @NotNull NumberValue<?> expectedValue);

    @Override
    WebTextInput shouldNotHavePropertyValue(@NotNull String propertyName, @NotNull StringValue expectedValue);

    @Override
    WebTextInput shouldNotHavePropertyValue(@NotNull String propertyName, @NotNull NumberValue<?> expectedValue);

}
