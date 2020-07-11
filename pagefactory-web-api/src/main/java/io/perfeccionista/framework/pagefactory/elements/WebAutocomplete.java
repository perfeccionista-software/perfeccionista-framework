package io.perfeccionista.framework.pagefactory.elements;

import io.perfeccionista.framework.asserts.WebAssertCondition;
import io.perfeccionista.framework.invocation.runner.InvocationName;
import io.perfeccionista.framework.pagefactory.elements.base.WebChildElement;
import io.perfeccionista.framework.pagefactory.elements.locators.WebLocator;
import io.perfeccionista.framework.pagefactory.elements.methods.ClearAvailable;
import io.perfeccionista.framework.pagefactory.elements.methods.Dimensions;
import io.perfeccionista.framework.pagefactory.elements.methods.Location;
import io.perfeccionista.framework.pagefactory.elements.methods.SendKeysAvailable;
import io.perfeccionista.framework.pagefactory.filter.list.WebListFilter;
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
public interface WebAutocomplete extends WebDropDownList,
        SendKeysAvailable, ClearAvailable {

    // Actions

    @Override
    WebAutocomplete executeAction(@NotNull String name, Object... args);

    @Override
    WebAutocomplete executeInteraction(@NotNull String name, @NotNull WebChildElement other, Object... args);

    // Asserts

    @Override
    WebAutocomplete should(WebAssertCondition assertCondition);

    @Override
    WebAutocomplete should(WebAssertCondition assertCondition, InvocationName invocationName);

    // Clear

    @Override
    WebAutocomplete clear();

    // Click

    @Override
    WebAutocomplete click();

    // ClickToElement

    @Override
    WebAutocomplete clickToElement(@NotNull WebListFilter filter); // Тут нужно еще скроллить к элементу

    // Close

    @Override
    WebAutocomplete close();

    // Get Color

    @Override
    WebAutocomplete componentShouldHaveColor(@NotNull String componentName, @NotNull String cssProperty, @NotNull Color expectedColor);

    @Override
    WebAutocomplete componentShouldNotHaveColor(@NotNull String componentName, @NotNull String cssProperty, @NotNull Color expectedColor);

    // Get Dimensions

    @Override
    WebAutocomplete componentShouldHaveDimensions(@NotNull String componentName, @NotNull Dimensions expectedDimensions);

    @Override
    WebAutocomplete componentShouldNotHaveDimensions(@NotNull String componentName, @NotNull Dimensions expectedDimensions);

    // Get Label

    @Override
    WebAutocomplete shouldHaveLabel(@NotNull StringValue expectedValue);

    @Override
    WebAutocomplete shouldHaveLabel(@NotNull NumberValue<?> expectedValue);

    @Override
    WebAutocomplete shouldNotHaveLabel(@NotNull StringValue expectedValue);

    @Override
    WebAutocomplete shouldNotHaveLabel(@NotNull NumberValue<?> expectedValue);

    // Get Location

    @Override
    WebAutocomplete componentShouldHaveLocation(@NotNull String componentName, @NotNull Location expectedLocation);

    @Override
    WebAutocomplete componentShouldNotHaveLocation(@NotNull String componentName, @NotNull Location expectedLocation);

    // Get Screenshot

    @Override
    WebAutocomplete componentShouldLooksLike(@NotNull String componentName, @NotNull Screenshot expectedScreenshot);

    @Override
    WebAutocomplete componentShouldNotLooksLike(@NotNull String componentName, @NotNull Screenshot expectedScreenshot);

    // Get Text

    @Override
    WebAutocomplete shouldHaveText(@NotNull StringValue expectedValue);

    @Override
    WebAutocomplete shouldHaveText(@NotNull NumberValue<?> expectedValue);

    @Override
    WebAutocomplete shouldNotHaveText(@NotNull StringValue expectedValue);

    @Override
    WebAutocomplete shouldNotHaveText(@NotNull NumberValue<?> expectedValue);

    // HoverTo

    @Override
    WebAutocomplete hoverTo(boolean withOutOfBounds);

    // IsDisplayed

    @Override
    WebAutocomplete shouldBeDisplayed();

    @Override
    WebAutocomplete shouldNotBeDisplayed();

    // IsInFocus

    @Override
    WebAutocomplete shouldBeInFocus();

    @Override
    WebAutocomplete shouldNotBeInFocus();

    // IsOpen

    @Override
    WebAutocomplete shouldBeOpen();

    @Override
    WebAutocomplete shouldBeClosed();

    // IsPresent

    @Override
    WebAutocomplete shouldBePresent();

    @Override
    WebAutocomplete shouldNotBePresent();

    // Open

    @Override
    WebAutocomplete open();

    // ScrollTo

    @Override
    WebAutocomplete scrollTo();

    // ScrollToElement

    @Override
    WebAutocomplete scrollToElement(@NotNull WebListFilter filter);

    // SendKeys

    @Override
    WebAutocomplete sendKeys(CharSequence... keys);

    // Size

    @Override
    WebAutocomplete shouldHaveSize(@NotNull NumberValue<Integer> expectedSize);

    // WebComponents

    @Override
    WebAutocomplete componentShouldBePresent(@NotNull String componentName);

    @Override
    WebAutocomplete componentShouldNotBePresent(@NotNull String componentName);

    @Override
    WebAutocomplete componentShouldBeDisplayed(@NotNull String componentName);

    @Override
    WebAutocomplete componentShouldNotBeDisplayed(@NotNull String componentName);

    // WebProperties

    @Override
    WebAutocomplete shouldHavePropertyValue(@NotNull String propertyName, @NotNull StringValue expectedValue);

    @Override
    WebAutocomplete shouldHavePropertyValue(@NotNull String propertyName, @NotNull NumberValue<?> expectedValue);

    @Override
    WebAutocomplete shouldNotHavePropertyValue(@NotNull String propertyName, @NotNull StringValue expectedValue);

    @Override
    WebAutocomplete shouldNotHavePropertyValue(@NotNull String propertyName, @NotNull NumberValue<?> expectedValue);

}
