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
import io.perfeccionista.framework.pagefactory.filter.list.WebListFilterBuilder;
import io.perfeccionista.framework.pagefactory.screenshots.Screenshot;
import io.perfeccionista.framework.plugin.Color;
import io.perfeccionista.framework.value.number.NumberValue;
import io.perfeccionista.framework.value.string.StringValue;
import org.jetbrains.annotations.NotNull;

import static io.perfeccionista.framework.pagefactory.elements.components.WebComponents.LABEL;
import static io.perfeccionista.framework.pagefactory.elements.components.WebComponents.UL;

@WebLocator(component = UL, xpath = ".//ul")
@WebLocator(component = LABEL, xpath = "self::node()//label")
public interface WebDropDownList extends WebList,
        ClickAvailable, GetTextAvailable, GetLabelAvailable, IsOpenAvailable, OpenAvailable, CloseAvailable {

    // Actions

    @Override
    WebDropDownList executeAction(@NotNull String name, Object... args);

    @Override
    WebDropDownList executeInteraction(@NotNull String name, @NotNull WebChildElement other, Object... args);

    // Asserts

    @Override
    WebDropDownList should(WebAssertCondition assertCondition);

    @Override
    WebDropDownList should(WebAssertCondition assertCondition, InvocationName invocationName);

    // Click

    @Override
    WebDropDownList click();

    // ClickToElement

    @Override
    WebDropDownList clickToElement(@NotNull WebListFilterBuilder filter); // Тут нужно еще скроллить к элементу

    // Close

    @Override
    WebDropDownList close();

    // Get Color

    @Override
    WebDropDownList componentShouldHaveColor(@NotNull String componentName, @NotNull String cssProperty, @NotNull Color expectedColor);

    @Override
    WebDropDownList componentShouldNotHaveColor(@NotNull String componentName, @NotNull String cssProperty, @NotNull Color expectedColor);

    // Get Dimensions

    @Override
    WebDropDownList componentShouldHaveDimensions(@NotNull String componentName, @NotNull Dimensions expectedDimensions);

    @Override
    WebDropDownList componentShouldNotHaveDimensions(@NotNull String componentName, @NotNull Dimensions expectedDimensions);

    // Get Label

    @Override
    WebDropDownList shouldHaveLabel(@NotNull StringValue expectedValue);

    @Override
    WebDropDownList shouldHaveLabel(@NotNull NumberValue<?> expectedValue);

    @Override
    WebDropDownList shouldNotHaveLabel(@NotNull StringValue expectedValue);

    @Override
    WebDropDownList shouldNotHaveLabel(@NotNull NumberValue<?> expectedValue);

    // Get Location

    @Override
    WebDropDownList componentShouldHaveLocation(@NotNull String componentName, @NotNull Location expectedLocation);

    @Override
    WebDropDownList componentShouldNotHaveLocation(@NotNull String componentName, @NotNull Location expectedLocation);

    // Get Screenshot

    @Override
    WebDropDownList componentShouldLooksLike(@NotNull String componentName, @NotNull Screenshot expectedScreenshot);

    @Override
    WebDropDownList componentShouldNotLooksLike(@NotNull String componentName, @NotNull Screenshot expectedScreenshot);

    // Get Text

    @Override
    WebDropDownList shouldHaveText(@NotNull StringValue expectedValue);

    @Override
    WebDropDownList shouldHaveText(@NotNull NumberValue<?> expectedValue);

    @Override
    WebDropDownList shouldNotHaveText(@NotNull StringValue expectedValue);

    @Override
    WebDropDownList shouldNotHaveText(@NotNull NumberValue<?> expectedValue);

    // HoverTo

    @Override
    WebDropDownList hoverTo(boolean withOutOfBounds);

    // IsDisplayed

    @Override
    WebDropDownList shouldBeDisplayed();

    @Override
    WebDropDownList shouldNotBeDisplayed();

    // IsInFocus

    @Override
    WebDropDownList shouldBeInFocus();

    @Override
    WebDropDownList shouldNotBeInFocus();

    // IsOpen

    @Override
    WebDropDownList shouldBeOpen();

    @Override
    WebDropDownList shouldBeClosed();

    // IsPresent

    @Override
    WebDropDownList shouldBePresent();

    @Override
    WebDropDownList shouldNotBePresent();

    // Open

    @Override
    WebDropDownList open();

    // ScrollTo

    @Override
    WebDropDownList scrollTo();

    // ScrollToElement

    @Override
    WebDropDownList scrollToElement(@NotNull WebListFilterBuilder filter);

    // Size

    @Override
    WebDropDownList shouldHaveSize(@NotNull NumberValue<Integer> expectedSize);

    // WebComponents

    @Override
    WebDropDownList componentShouldBePresent(@NotNull String componentName);

    @Override
    WebDropDownList componentShouldNotBePresent(@NotNull String componentName);

    @Override
    WebDropDownList componentShouldBeDisplayed(@NotNull String componentName);

    @Override
    WebDropDownList componentShouldNotBeDisplayed(@NotNull String componentName);

    // WebProperties

    @Override
    WebDropDownList shouldHavePropertyValue(@NotNull String propertyName, @NotNull StringValue expectedValue);

    @Override
    WebDropDownList shouldHavePropertyValue(@NotNull String propertyName, @NotNull NumberValue<?> expectedValue);

    @Override
    WebDropDownList shouldNotHavePropertyValue(@NotNull String propertyName, @NotNull StringValue expectedValue);

    @Override
    WebDropDownList shouldNotHavePropertyValue(@NotNull String propertyName, @NotNull NumberValue<?> expectedValue);

}
