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
import io.perfeccionista.framework.pagefactory.filter.MultipleResult;
import io.perfeccionista.framework.pagefactory.filter.textlist.WebTextListFilter;
import io.perfeccionista.framework.pagefactory.filter.textlist.WebTextListFilterResult;
import io.perfeccionista.framework.pagefactory.screenshots.Screenshot;
import io.perfeccionista.framework.plugin.Color;
import io.perfeccionista.framework.value.number.NumberValue;
import io.perfeccionista.framework.value.string.StringValue;
import org.jetbrains.annotations.NotNull;

import static io.perfeccionista.framework.pagefactory.elements.components.WebComponents.LI;

// TODO: Добавить TextBlockExtractor/LinkExtractor
@WebLocator(component = LI, xpath = ".//li", single = false)
public interface WebTextList extends WebChildElement,
        ScrollToElementAvailable<WebTextListFilter>, ClickToElementAvailable<WebTextListFilter>, SizeAvailable {

    @NotNull WebTextListFilterResult filter(@NotNull WebTextListFilter filter);

    @NotNull MultipleResult<String> extractAll();

    // Actions

    @Override
    WebTextList executeAction(@NotNull String name, Object... args);

    @Override
    WebTextList executeInteraction(@NotNull String name, @NotNull WebChildElement other, Object... args);

    // Asserts

    @Override
    WebTextList should(WebAssertCondition assertCondition);

    @Override
    WebTextList should(WebAssertCondition assertCondition, InvocationName invocationName);

    // ClickToElement

    @Override
    WebTextList clickToElement(@NotNull WebTextListFilter filter);

    // Get Color

    @Override
    WebTextList componentShouldHaveColor(@NotNull String componentName, @NotNull String cssProperty, @NotNull Color expectedColor);

    @Override
    WebTextList componentShouldNotHaveColor(@NotNull String componentName, @NotNull String cssProperty, @NotNull Color expectedColor);

    // Get Dimensions

    @Override
    WebTextList componentShouldHaveDimensions(@NotNull String componentName, @NotNull Dimensions expectedDimensions);

    @Override
    WebTextList componentShouldNotHaveDimensions(@NotNull String componentName, @NotNull Dimensions expectedDimensions);

    // Get Location

    @Override
    WebTextList componentShouldHaveLocation(@NotNull String componentName, @NotNull Location expectedLocation);

    @Override
    WebTextList componentShouldNotHaveLocation(@NotNull String componentName, @NotNull Location expectedLocation);

    // Get Screenshot

    @Override
    WebTextList componentShouldLooksLike(@NotNull String componentName, @NotNull Screenshot expectedScreenshot);

    @Override
    WebTextList componentShouldNotLooksLike(@NotNull String componentName, @NotNull Screenshot expectedScreenshot);

    // HoverTo

    @Override
    WebTextList hoverTo(boolean withOutOfBounds);

    // IsDisplayed

    @Override
    WebTextList shouldBeDisplayed();

    @Override
    WebTextList shouldNotBeDisplayed();

    // IsInFocus

    @Override
    WebTextList shouldBeInFocus();

    @Override
    WebTextList shouldNotBeInFocus();

    // IsPresent

    @Override
    WebTextList shouldBePresent();

    @Override
    WebTextList shouldNotBePresent();

    // ScrollTo

    @Override
    WebTextList scrollTo();

    // ScrollToElement

    @Override
    WebTextList scrollToElement(@NotNull WebTextListFilter filter);

    // Size

    @Override
    WebTextList shouldHaveSize(@NotNull NumberValue<Integer> expectedSize);

    // WebComponent

    @Override
    WebTextList componentShouldBePresent(@NotNull String componentName);

    @Override
    WebTextList componentShouldNotBePresent(@NotNull String componentName);

    @Override
    WebTextList componentShouldBeDisplayed(@NotNull String componentName);

    @Override
    WebTextList componentShouldNotBeDisplayed(@NotNull String componentName);

    // WebProperties

    @Override
    WebTextList shouldHavePropertyValue(@NotNull String propertyName, @NotNull StringValue expectedValue);

    @Override
    WebTextList shouldHavePropertyValue(@NotNull String propertyName, @NotNull NumberValue<?> expectedValue);

    @Override
    WebTextList shouldNotHavePropertyValue(@NotNull String propertyName, @NotNull StringValue expectedValue);

    @Override
    WebTextList shouldNotHavePropertyValue(@NotNull String propertyName, @NotNull NumberValue<?> expectedValue);

}