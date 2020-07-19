package io.perfeccionista.framework.pagefactory.elements;

import io.perfeccionista.framework.asserts.WebAssertCondition;
import io.perfeccionista.framework.invocation.runner.InvocationName;
import io.perfeccionista.framework.pagefactory.elements.base.WebChildElement;
import io.perfeccionista.framework.pagefactory.elements.locators.WebLocator;
import io.perfeccionista.framework.pagefactory.elements.methods.Dimensions;
import io.perfeccionista.framework.pagefactory.elements.methods.Location;
import io.perfeccionista.framework.pagefactory.elements.methods.ScrollToElementAvailable;
import io.perfeccionista.framework.pagefactory.elements.methods.SizeAvailable;
import io.perfeccionista.framework.pagefactory.extractor.radio.WebRadioButtonValueExtractor;
import io.perfeccionista.framework.pagefactory.filter.MultipleResult;
import io.perfeccionista.framework.pagefactory.filter.radio.WebRadioButtonFilterBuilder;
import io.perfeccionista.framework.pagefactory.filter.radio.WebRadioButtonFilter;
import io.perfeccionista.framework.pagefactory.screenshots.Screenshot;
import io.perfeccionista.framework.plugin.Color;
import io.perfeccionista.framework.value.number.NumberValue;
import io.perfeccionista.framework.value.string.StringValue;
import org.jetbrains.annotations.NotNull;

import static io.perfeccionista.framework.pagefactory.elements.components.WebComponents.RADIO;

@WebLocator(component = RADIO, xpath = ".//input", single = false)
public interface WebRadioGroup extends WebChildElement,
        ScrollToElementAvailable<WebRadioButtonFilterBuilder>, SizeAvailable {

    @NotNull WebRadioButtonFilter filter(@NotNull WebRadioButtonFilterBuilder filterBuilder);

    @NotNull <V> MultipleResult<V> extractAll(@NotNull WebRadioButtonValueExtractor<V> extractor);

    @NotNull WebRadioButton getSelected();

    @NotNull WebRadioButton getByLabel(@NotNull StringValue expectedLabel);

    @NotNull WebRadioButton getByIndex(@NotNull NumberValue<Integer> expectedIndex);

    // Actions

    @Override
    WebRadioGroup executeAction(@NotNull String name, Object... args);

    @Override
    WebRadioGroup executeInteraction(@NotNull String name, @NotNull WebChildElement other, Object... args);

    // Asserts

    @Override
    WebRadioGroup should(WebAssertCondition assertCondition);

    @Override
    WebRadioGroup should(WebAssertCondition assertCondition, InvocationName invocationName);

    // Get Color

    @Override
    WebRadioGroup componentShouldHaveColor(@NotNull String componentName, @NotNull String cssProperty, @NotNull Color expectedColor);

    @Override
    WebRadioGroup componentShouldNotHaveColor(@NotNull String componentName, @NotNull String cssProperty, @NotNull Color expectedColor);

    // Get Dimensions

    @Override
    WebRadioGroup componentShouldHaveDimensions(@NotNull String componentName, @NotNull Dimensions expectedDimensions);

    @Override
    WebRadioGroup componentShouldNotHaveDimensions(@NotNull String componentName, @NotNull Dimensions expectedDimensions);

    // Get Location

    @Override
    WebRadioGroup componentShouldHaveLocation(@NotNull String componentName, @NotNull Location expectedLocation);

    @Override
    WebRadioGroup componentShouldNotHaveLocation(@NotNull String componentName, @NotNull Location expectedLocation);

    // Get Screenshot

    @Override
    WebRadioGroup componentShouldLooksLike(@NotNull String componentName, @NotNull Screenshot expectedScreenshot);

    @Override
    WebRadioGroup componentShouldNotLooksLike(@NotNull String componentName, @NotNull Screenshot expectedScreenshot);

    // HoverTo

    @Override
    WebRadioGroup hoverTo(boolean withOutOfBounds);

    // IsDisplayed

    @Override
    WebRadioGroup shouldBeDisplayed();

    @Override
    WebRadioGroup shouldNotBeDisplayed();

    // IsInFocus

    @Override
    WebRadioGroup shouldBeInFocus();

    @Override
    WebRadioGroup shouldNotBeInFocus();

    // IsPresent

    @Override
    WebRadioGroup shouldBePresent();

    @Override
    WebRadioGroup shouldNotBePresent();

    // ScrollTo

    @Override
    WebRadioGroup scrollTo();

    // ScrollToElement

    @Override
    WebRadioGroup scrollToElement(@NotNull WebRadioButtonFilterBuilder filter);

    // Size

    @Override
    WebRadioGroup shouldHaveSize(@NotNull NumberValue<Integer> expectedSize);

    // WebComponents

    @Override
    WebRadioGroup componentShouldBePresent(@NotNull String componentName);

    @Override
    WebRadioGroup componentShouldNotBePresent(@NotNull String componentName);

    @Override
    WebRadioGroup componentShouldBeDisplayed(@NotNull String componentName);

    @Override
    WebRadioGroup componentShouldNotBeDisplayed(@NotNull String componentName);

    // WebProperties

    @Override
    WebRadioGroup shouldHavePropertyValue(@NotNull String propertyName, @NotNull StringValue expectedValue);

    @Override
    WebRadioGroup shouldHavePropertyValue(@NotNull String propertyName, @NotNull NumberValue<?> expectedValue);

    @Override
    WebRadioGroup shouldNotHavePropertyValue(@NotNull String propertyName, @NotNull StringValue expectedValue);

    @Override
    WebRadioGroup shouldNotHavePropertyValue(@NotNull String propertyName, @NotNull NumberValue<?> expectedValue);

}
