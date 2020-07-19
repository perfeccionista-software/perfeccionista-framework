package io.perfeccionista.framework.pagefactory.elements;

import io.perfeccionista.framework.asserts.WebAssertCondition;
import io.perfeccionista.framework.invocation.runner.InvocationName;
import io.perfeccionista.framework.pagefactory.elements.base.WebChildElement;
import io.perfeccionista.framework.pagefactory.elements.locators.WebLocator;
import io.perfeccionista.framework.pagefactory.elements.methods.ClickAvailable;
import io.perfeccionista.framework.pagefactory.elements.methods.CloseAvailable;
import io.perfeccionista.framework.pagefactory.elements.methods.Dimensions;
import io.perfeccionista.framework.pagefactory.elements.methods.GetLabelAvailable;
import io.perfeccionista.framework.pagefactory.elements.methods.GetTextAvailable;
import io.perfeccionista.framework.pagefactory.elements.methods.IsOpenAvailable;
import io.perfeccionista.framework.pagefactory.elements.methods.Location;
import io.perfeccionista.framework.pagefactory.elements.methods.OpenAvailable;
import io.perfeccionista.framework.pagefactory.filter.textlist.WebTextListFilterBuilder;
import io.perfeccionista.framework.pagefactory.screenshots.Screenshot;
import io.perfeccionista.framework.plugin.Color;
import io.perfeccionista.framework.value.number.NumberValue;
import io.perfeccionista.framework.value.string.StringValue;
import org.jetbrains.annotations.NotNull;

import static io.perfeccionista.framework.pagefactory.elements.components.WebComponents.LABEL;
import static io.perfeccionista.framework.pagefactory.elements.components.WebComponents.UL;

@WebLocator(component = UL, xpath = ".//ul")
@WebLocator(component = LABEL, xpath = "self::node()//label")
public interface WebTextDropDownList extends WebTextList,
        ClickAvailable, GetTextAvailable, GetLabelAvailable, IsOpenAvailable, OpenAvailable, CloseAvailable {

    // Actions

    @Override
    WebTextDropDownList executeAction(@NotNull String name, Object... args);

    @Override
    WebTextDropDownList executeInteraction(@NotNull String name, @NotNull WebChildElement other, Object... args);

    // Asserts

    @Override
    WebTextDropDownList should(WebAssertCondition assertCondition);

    @Override
    WebTextDropDownList should(WebAssertCondition assertCondition, InvocationName invocationName);

    // Click

    @Override
    WebTextDropDownList click();

    // ClickToElement

    @Override
    WebTextDropDownList clickToElement(@NotNull WebTextListFilterBuilder filter);

    // Close

    @Override
    WebTextDropDownList close();

    // Get Color

    @Override
    WebTextDropDownList componentShouldHaveColor(@NotNull String componentName, @NotNull String cssProperty, @NotNull Color expectedColor);

    @Override
    WebTextDropDownList componentShouldNotHaveColor(@NotNull String componentName, @NotNull String cssProperty, @NotNull Color expectedColor);

    // Get Dimensions

    @Override
    WebTextDropDownList componentShouldHaveDimensions(@NotNull String componentName, @NotNull Dimensions expectedDimensions);

    @Override
    WebTextDropDownList componentShouldNotHaveDimensions(@NotNull String componentName, @NotNull Dimensions expectedDimensions);

    // Get Label

    @Override
    WebTextDropDownList shouldHaveLabel(@NotNull StringValue expectedValue);

    @Override
    WebTextDropDownList shouldHaveLabel(@NotNull NumberValue<?> expectedValue);

    @Override
    WebTextDropDownList shouldNotHaveLabel(@NotNull StringValue expectedValue);

    @Override
    WebTextDropDownList shouldNotHaveLabel(@NotNull NumberValue<?> expectedValue);

    // Get Location

    @Override
    WebTextDropDownList componentShouldHaveLocation(@NotNull String componentName, @NotNull Location expectedLocation);

    @Override
    WebTextDropDownList componentShouldNotHaveLocation(@NotNull String componentName, @NotNull Location expectedLocation);

    // Get Screenshot

    @Override
    WebTextDropDownList componentShouldLooksLike(@NotNull String componentName, @NotNull Screenshot expectedScreenshot);

    @Override
    WebTextDropDownList componentShouldNotLooksLike(@NotNull String componentName, @NotNull Screenshot expectedScreenshot);

    // Get Text

    @Override
    WebTextDropDownList shouldHaveText(@NotNull StringValue expectedValue);

    @Override
    WebTextDropDownList shouldHaveText(@NotNull NumberValue<?> expectedValue);

    @Override
    WebTextDropDownList shouldNotHaveText(@NotNull StringValue expectedValue);

    @Override
    WebTextDropDownList shouldNotHaveText(@NotNull NumberValue<?> expectedValue);

    // HoverTo

    @Override
    WebTextDropDownList hoverTo(boolean withOutOfBounds);

    // IsDisplayed

    @Override
    WebTextDropDownList shouldBeDisplayed();

    @Override
    WebTextDropDownList shouldNotBeDisplayed();

    // IsInFocus

    @Override
    WebTextDropDownList shouldBeInFocus();

    @Override
    WebTextDropDownList shouldNotBeInFocus();

    // IsOpen

    @Override
    WebTextDropDownList shouldBeOpen();

    @Override
    WebTextDropDownList shouldBeClosed();

    // IsPresent

    @Override
    WebTextDropDownList shouldBePresent();

    @Override
    WebTextDropDownList shouldNotBePresent();

    // Open

    @Override
    WebTextDropDownList open();

    // ScrollTo

    @Override
    WebTextDropDownList scrollTo();

    // ScrollToElement

    @Override
    WebTextDropDownList scrollToElement(@NotNull WebTextListFilterBuilder filter);

    // Size

    @Override
    WebTextDropDownList shouldHaveSize(@NotNull NumberValue<Integer> expectedSize);

    // WebComponent

    @Override
    WebTextDropDownList componentShouldBePresent(@NotNull String componentName);

    @Override
    WebTextDropDownList componentShouldNotBePresent(@NotNull String componentName);

    @Override
    WebTextDropDownList componentShouldBeDisplayed(@NotNull String componentName);

    @Override
    WebTextDropDownList componentShouldNotBeDisplayed(@NotNull String componentName);

    // WebProperties

    @Override
    WebTextDropDownList shouldHavePropertyValue(@NotNull String propertyName, @NotNull StringValue expectedValue);

    @Override
    WebTextDropDownList shouldHavePropertyValue(@NotNull String propertyName, @NotNull NumberValue<?> expectedValue);

    @Override
    WebTextDropDownList shouldNotHavePropertyValue(@NotNull String propertyName, @NotNull StringValue expectedValue);

    @Override
    WebTextDropDownList shouldNotHavePropertyValue(@NotNull String propertyName, @NotNull NumberValue<?> expectedValue);

}
