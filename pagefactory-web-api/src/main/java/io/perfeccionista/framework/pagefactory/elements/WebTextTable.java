package io.perfeccionista.framework.pagefactory.elements;

import io.perfeccionista.framework.asserts.WebAssertCondition;
import io.perfeccionista.framework.invocation.runner.InvocationName;
import io.perfeccionista.framework.pagefactory.elements.base.WebChildElement;
import io.perfeccionista.framework.pagefactory.elements.locators.WebLocator;
import io.perfeccionista.framework.pagefactory.elements.methods.Dimensions;
import io.perfeccionista.framework.pagefactory.elements.methods.Location;
import io.perfeccionista.framework.pagefactory.elements.methods.ScrollToElementAvailable;
import io.perfeccionista.framework.pagefactory.elements.methods.SizeAvailable;
import io.perfeccionista.framework.pagefactory.filter.MultipleResult;
import io.perfeccionista.framework.pagefactory.filter.texttable.WebTextTableFilterBuilder;
import io.perfeccionista.framework.pagefactory.filter.SingleResult;
import io.perfeccionista.framework.pagefactory.filter.texttable.WebTextTableFilter;
import io.perfeccionista.framework.pagefactory.screenshots.Screenshot;
import io.perfeccionista.framework.plugin.Color;
import io.perfeccionista.framework.value.number.NumberValue;
import io.perfeccionista.framework.value.string.StringValue;
import org.jetbrains.annotations.NotNull;

import static io.perfeccionista.framework.pagefactory.elements.components.WebComponents.TBODY_ROW;
import static io.perfeccionista.framework.pagefactory.elements.components.WebComponents.TFOOT_ROW;
import static io.perfeccionista.framework.pagefactory.elements.components.WebComponents.THEAD_ROW;

// TODO: Map<String, SingleResult<String>> extractHeader(Set<String> columnNames);
// TODO: Map<String, MultipleResult<String>> extractAll(Set<String> columnNames);
// TODO: Map<String, SingleResult<String>> extractFooter(Set<String> columnNames);
// TODO: Добавить TextBlockExtractor/LinkExtractor
// TODO: ClickToHeader / Cell / Footer
@WebLocator(component = THEAD_ROW, xpath = ".//thead//tr")
@WebLocator(component = TBODY_ROW, xpath = ".//tbody//tr", single = false)
@WebLocator(component = TFOOT_ROW, xpath = ".//tfoot//tr")
public interface WebTextTable extends WebChildElement,
        ScrollToElementAvailable<WebTextTableFilterBuilder>, SizeAvailable {

    @NotNull WebTextTableFilter filter(@NotNull WebTextTableFilterBuilder filterBuilder);

    @NotNull SingleResult<String> extractHeader(@NotNull String columnName);

    @NotNull MultipleResult<String> extractAllRows(@NotNull String columnName);

    @NotNull SingleResult<String> extractFooter(@NotNull String columnName);

    // Actions

    @Override
    WebTextTable executeAction(@NotNull String name, Object... args);

    @Override
    WebTextTable executeInteraction(@NotNull String name, @NotNull WebChildElement other, Object... args);

    // Asserts

    @Override
    WebTextTable should(WebAssertCondition assertCondition);

    @Override
    WebTextTable should(WebAssertCondition assertCondition, InvocationName invocationName);

    // Get Color

    @Override
    WebTextTable componentShouldHaveColor(@NotNull String componentName, @NotNull String cssProperty, @NotNull Color expectedColor);

    @Override
    WebTextTable componentShouldNotHaveColor(@NotNull String componentName, @NotNull String cssProperty, @NotNull Color expectedColor);

    // Get Dimensions

    @Override
    WebTextTable componentShouldHaveDimensions(@NotNull String componentName, @NotNull Dimensions expectedDimensions);
    @Override
    WebTextTable componentShouldNotHaveDimensions(@NotNull String componentName, @NotNull Dimensions expectedDimensions);

    // Get Location

    @Override
    WebTextTable componentShouldHaveLocation(@NotNull String componentName, @NotNull Location expectedLocation);
    @Override
    WebTextTable componentShouldNotHaveLocation(@NotNull String componentName, @NotNull Location expectedLocation);

    // Get Screenshot

    @Override
    WebTextTable componentShouldLooksLike(@NotNull String componentName, @NotNull Screenshot expectedScreenshot);
    @Override
    WebTextTable componentShouldNotLooksLike(@NotNull String componentName, @NotNull Screenshot expectedScreenshot);

    // HoverTo

    @Override
    WebTextTable hoverTo(boolean withOutOfBounds);

    // IsDisplayed

    @Override
    WebTextTable shouldBeDisplayed();
    @Override
    WebTextTable shouldNotBeDisplayed();

    // IsInFocus

    @Override
    WebTextTable shouldBeInFocus();
    @Override
    WebTextTable shouldNotBeInFocus();

    // IsPresent

    @Override
    WebTextTable shouldBePresent();
    @Override
    WebTextTable shouldNotBePresent();

    // ScrollTo

    @Override
    WebTextTable scrollTo();

    // ScrollToElement

    @Override
    WebTextTable scrollToElement(@NotNull WebTextTableFilterBuilder filter);

    // Size

    @Override
    WebTextTable shouldHaveSize(@NotNull NumberValue<Integer> expectedSize);

    // WebComponent

    @Override
    WebTextTable componentShouldBePresent(@NotNull String componentName);

    @Override
    WebTextTable componentShouldNotBePresent(@NotNull String componentName);

    @Override
    WebTextTable componentShouldBeDisplayed(@NotNull String componentName);

    @Override
    WebTextTable componentShouldNotBeDisplayed(@NotNull String componentName);

    // WebProperties

    @Override
    WebTextTable shouldHavePropertyValue(@NotNull String propertyName, @NotNull StringValue expectedValue);

    @Override
    WebTextTable shouldHavePropertyValue(@NotNull String propertyName, @NotNull NumberValue<?> expectedValue);

    @Override
    WebTextTable shouldNotHavePropertyValue(@NotNull String propertyName, @NotNull StringValue expectedValue);

    @Override
    WebTextTable shouldNotHavePropertyValue(@NotNull String propertyName, @NotNull NumberValue<?> expectedValue);

}
