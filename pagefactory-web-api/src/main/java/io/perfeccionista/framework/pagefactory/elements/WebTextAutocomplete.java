package io.perfeccionista.framework.pagefactory.elements;

import io.perfeccionista.framework.asserts.WebAssertCondition;
import io.perfeccionista.framework.invocation.runner.InvocationName;
import io.perfeccionista.framework.pagefactory.elements.base.WebChildElement;
import io.perfeccionista.framework.pagefactory.elements.locators.WebLocator;
import io.perfeccionista.framework.pagefactory.elements.methods.ClearAvailable;
import io.perfeccionista.framework.pagefactory.elements.methods.Dimensions;
import io.perfeccionista.framework.pagefactory.elements.methods.Location;
import io.perfeccionista.framework.pagefactory.elements.methods.SendKeysAvailable;
import io.perfeccionista.framework.pagefactory.filter.textlist.WebTextListFilter;
import io.perfeccionista.framework.pagefactory.screenshots.Screenshot;
import io.perfeccionista.framework.plugin.Color;
import io.perfeccionista.framework.value.number.NumberValue;
import io.perfeccionista.framework.value.string.StringValue;
import org.jetbrains.annotations.NotNull;

import static io.perfeccionista.framework.pagefactory.elements.components.WebComponents.CLEAR;
import static io.perfeccionista.framework.pagefactory.elements.components.WebComponents.INPUT;
import static io.perfeccionista.framework.pagefactory.elements.components.WebComponents.TEXT;

@WebLocator(component = TEXT, xpath = "self::node()//input[@type = 'text']")
@WebLocator(component = INPUT, xpath = "self::node()//input[@type = 'text']")
@WebLocator(component = CLEAR, xpath = "self::node()//input[@type = 'text']")
public interface WebTextAutocomplete extends WebTextDropDownList,
        SendKeysAvailable, ClearAvailable {

    // Actions

    @Override
    WebTextAutocomplete executeAction(@NotNull String name, Object... args);

    @Override
    WebTextAutocomplete executeInteraction(@NotNull String name, @NotNull WebChildElement other, Object... args);

    // Asserts

    @Override
    WebTextAutocomplete should(WebAssertCondition assertCondition);

    @Override
    WebTextAutocomplete should(WebAssertCondition assertCondition, InvocationName invocationName);

    // Clear

    @Override
    WebTextAutocomplete clear();

    // Click

    @Override
    WebTextAutocomplete click();

    // ClickToElement

    @Override
    WebTextAutocomplete clickToElement(@NotNull WebTextListFilter filter);

    // Close

    @Override
    WebTextAutocomplete close();

    // Get Color

    @Override
    WebTextAutocomplete componentShouldHaveColor(@NotNull String componentName, @NotNull String cssProperty, @NotNull Color expectedColor);

    @Override
    WebTextAutocomplete componentShouldNotHaveColor(@NotNull String componentName, @NotNull String cssProperty, @NotNull Color expectedColor);

    // Get Dimensions

    @Override
    WebTextAutocomplete componentShouldHaveDimensions(@NotNull String componentName, @NotNull Dimensions expectedDimensions);

    @Override
    WebTextAutocomplete componentShouldNotHaveDimensions(@NotNull String componentName, @NotNull Dimensions expectedDimensions);

    // Get Label

    @Override
    WebTextAutocomplete shouldHaveLabel(@NotNull StringValue expectedValue);

    @Override
    WebTextAutocomplete shouldHaveLabel(@NotNull NumberValue<?> expectedValue);

    @Override
    WebTextAutocomplete shouldNotHaveLabel(@NotNull StringValue expectedValue);

    @Override
    WebTextAutocomplete shouldNotHaveLabel(@NotNull NumberValue<?> expectedValue);

    // Get Location

    @Override
    WebTextAutocomplete componentShouldHaveLocation(@NotNull String componentName, @NotNull Location expectedLocation);

    @Override
    WebTextAutocomplete componentShouldNotHaveLocation(@NotNull String componentName, @NotNull Location expectedLocation);

    // Get Screenshot

    @Override
    WebTextAutocomplete componentShouldLooksLike(@NotNull String componentName, @NotNull Screenshot expectedScreenshot);

    @Override
    WebTextAutocomplete componentShouldNotLooksLike(@NotNull String componentName, @NotNull Screenshot expectedScreenshot);

    // Get Text

    @Override
    WebTextAutocomplete shouldHaveText(@NotNull StringValue expectedValue);

    @Override
    WebTextAutocomplete shouldHaveText(@NotNull NumberValue<?> expectedValue);

    @Override
    WebTextAutocomplete shouldNotHaveText(@NotNull StringValue expectedValue);

    @Override
    WebTextAutocomplete shouldNotHaveText(@NotNull NumberValue<?> expectedValue);

    // HoverTo

    @Override
    WebTextAutocomplete hoverTo(boolean withOutOfBounds);

    // IsDisplayed

    @Override
    WebTextAutocomplete shouldBeDisplayed();

    @Override
    WebTextAutocomplete shouldNotBeDisplayed();

    // IsInFocus

    @Override
    WebTextAutocomplete shouldBeInFocus();

    @Override
    WebTextAutocomplete shouldNotBeInFocus();

    // IsOpen

    @Override
    WebTextAutocomplete shouldBeOpen();

    @Override
    WebTextAutocomplete shouldBeClosed();

    // IsPresent

    @Override
    WebTextAutocomplete shouldBePresent();

    @Override
    WebTextAutocomplete shouldNotBePresent();

    // Open

    @Override
    WebTextAutocomplete open();

    // ScrollTo

    @Override
    WebTextAutocomplete scrollTo();

    // ScrollToElement

    @Override
    WebTextAutocomplete scrollToElement(@NotNull WebTextListFilter filter);

    // SendKeys

    @Override
    WebTextAutocomplete sendKeys(CharSequence... keys);

    // Size

    @Override
    WebTextAutocomplete shouldHaveSize(@NotNull NumberValue<Integer> expectedSize);

    // WebComponent

    @Override
    WebTextAutocomplete componentShouldBePresent(@NotNull String componentName);

    @Override
    WebTextAutocomplete componentShouldNotBePresent(@NotNull String componentName);

    @Override
    WebTextAutocomplete componentShouldBeDisplayed(@NotNull String componentName);

    @Override
    WebTextAutocomplete componentShouldNotBeDisplayed(@NotNull String componentName);

    // WebProperties

    @Override
    WebTextAutocomplete shouldHavePropertyValue(@NotNull String propertyName, @NotNull StringValue expectedValue);

    @Override
    WebTextAutocomplete shouldHavePropertyValue(@NotNull String propertyName, @NotNull NumberValue<?> expectedValue);

    @Override
    WebTextAutocomplete shouldNotHavePropertyValue(@NotNull String propertyName, @NotNull StringValue expectedValue);

    @Override
    WebTextAutocomplete shouldNotHavePropertyValue(@NotNull String propertyName, @NotNull NumberValue<?> expectedValue);
}
