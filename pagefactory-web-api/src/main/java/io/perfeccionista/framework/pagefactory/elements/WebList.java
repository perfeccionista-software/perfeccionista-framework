package io.perfeccionista.framework.pagefactory.elements;

import io.perfeccionista.framework.asserts.WebAssertCondition;
import io.perfeccionista.framework.invocation.runner.InvocationName;
import io.perfeccionista.framework.pagefactory.elements.base.WebChildElement;
import io.perfeccionista.framework.pagefactory.elements.locators.WebLocator;
import io.perfeccionista.framework.pagefactory.elements.methods.ClickToElementAvailable;
import io.perfeccionista.framework.pagefactory.elements.methods.Dimensions;
import io.perfeccionista.framework.pagefactory.elements.methods.Location;
import io.perfeccionista.framework.pagefactory.elements.methods.ScrollToElementAvailable;
import io.perfeccionista.framework.pagefactory.elements.methods.SizeAvailable;
import io.perfeccionista.framework.pagefactory.extractor.list.WebListBlockValueExtractor;
import io.perfeccionista.framework.pagefactory.filter.MultipleResult;
import io.perfeccionista.framework.pagefactory.filter.list.WebListFilterBuilder;
import io.perfeccionista.framework.pagefactory.filter.list.WebListFilter;
import io.perfeccionista.framework.pagefactory.screenshots.Screenshot;
import io.perfeccionista.framework.plugin.Color;
import io.perfeccionista.framework.value.number.NumberValue;
import io.perfeccionista.framework.value.string.StringValue;
import org.jetbrains.annotations.NotNull;

import static io.perfeccionista.framework.pagefactory.elements.components.WebComponents.LI;

@WebLocator(component = LI, xpath = ".//li", single = false)
public interface WebList extends WebChildElement,
        ScrollToElementAvailable<WebListFilterBuilder>, ClickToElementAvailable<WebListFilterBuilder>, SizeAvailable {

    @NotNull WebListFilter filter(@NotNull WebListFilterBuilder filterBuilder);

    @NotNull <V> MultipleResult<V> extractAll(@NotNull WebListBlockValueExtractor<V> extractor);

    // Actions

    @Override
    WebList executeAction(@NotNull String name, Object... args);

    @Override
    WebList executeInteraction(@NotNull String name, @NotNull WebChildElement other, Object... args);

    // Asserts

    @Override
    WebList should(WebAssertCondition assertCondition);

    @Override
    WebList should(WebAssertCondition assertCondition, InvocationName invocationName);

    // ClickToElement

    @Override
    WebList clickToElement(@NotNull WebListFilterBuilder filter); // Тут нужно еще скроллить к элементу

    // Get Color

    @Override
    WebList componentShouldHaveColor(@NotNull String componentName, @NotNull String cssProperty, @NotNull Color expectedColor);

    @Override
    WebList componentShouldNotHaveColor(@NotNull String componentName, @NotNull String cssProperty, @NotNull Color expectedColor);

    // Get Dimensions

    @Override
    WebList componentShouldHaveDimensions(@NotNull String componentName, @NotNull Dimensions expectedDimensions);

    @Override
    WebList componentShouldNotHaveDimensions(@NotNull String componentName, @NotNull Dimensions expectedDimensions);

    // Get Location

    @Override
    WebList componentShouldHaveLocation(@NotNull String componentName, @NotNull Location expectedLocation);

    @Override
    WebList componentShouldNotHaveLocation(@NotNull String componentName, @NotNull Location expectedLocation);

    // Get Screenshot

    @Override
    WebList componentShouldLooksLike(@NotNull String componentName, @NotNull Screenshot expectedScreenshot);

    @Override
    WebList componentShouldNotLooksLike(@NotNull String componentName, @NotNull Screenshot expectedScreenshot);

    // HoverTo

    @Override
    WebList hoverTo(boolean withOutOfBounds);

    // IsDisplayed

    @Override
    WebList shouldBeDisplayed();

    @Override
    WebList shouldNotBeDisplayed();

    // IsInFocus

    @Override
    WebList shouldBeInFocus();

    @Override
    WebList shouldNotBeInFocus();

    // IsPresent

    @Override
    WebList shouldBePresent();

    @Override
    WebList shouldNotBePresent();

    // ScrollTo

    @Override
    WebList scrollTo();

    // ScrollToElement

    @Override
    WebList scrollToElement(@NotNull WebListFilterBuilder filter);

    // Size

    @Override
    WebList shouldHaveSize(@NotNull NumberValue<Integer> expectedSize);

    // WebComponents

    @Override
    WebList componentShouldBePresent(@NotNull String componentName);

    @Override
    WebList componentShouldNotBePresent(@NotNull String componentName);

    @Override
    WebList componentShouldBeDisplayed(@NotNull String componentName);

    @Override
    WebList componentShouldNotBeDisplayed(@NotNull String componentName);

    // WebProperties

    @Override
    WebList shouldHavePropertyValue(@NotNull String propertyName, @NotNull StringValue expectedValue);

    @Override
    WebList shouldHavePropertyValue(@NotNull String propertyName, @NotNull NumberValue<?> expectedValue);

    @Override
    WebList shouldNotHavePropertyValue(@NotNull String propertyName, @NotNull StringValue expectedValue);

    @Override
    WebList shouldNotHavePropertyValue(@NotNull String propertyName, @NotNull NumberValue<?> expectedValue);

}
