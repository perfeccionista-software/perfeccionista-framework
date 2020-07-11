package io.perfeccionista.framework.pagefactory.elements;

import io.perfeccionista.framework.asserts.WebAssertCondition;
import io.perfeccionista.framework.invocation.runner.InvocationName;
import io.perfeccionista.framework.pagefactory.elements.base.WebChildElement;
import io.perfeccionista.framework.pagefactory.elements.locators.WebLocator;
import io.perfeccionista.framework.pagefactory.elements.mapping.TableColumnHolder;
import io.perfeccionista.framework.pagefactory.elements.methods.Dimensions;
import io.perfeccionista.framework.pagefactory.elements.methods.Location;
import io.perfeccionista.framework.pagefactory.elements.methods.ScrollToElementAvailable;
import io.perfeccionista.framework.pagefactory.elements.methods.SizeAvailable;
import io.perfeccionista.framework.pagefactory.extractor.table.WebTableCellValueExtractor;
import io.perfeccionista.framework.pagefactory.filter.MultipleResult;
import io.perfeccionista.framework.pagefactory.filter.table.WebTableFilter;
import io.perfeccionista.framework.pagefactory.filter.SingleResult;
import io.perfeccionista.framework.pagefactory.filter.table.WebTableFilterResult;
import io.perfeccionista.framework.pagefactory.screenshots.Screenshot;
import io.perfeccionista.framework.plugin.Color;
import io.perfeccionista.framework.value.number.NumberValue;
import io.perfeccionista.framework.value.string.StringValue;
import org.jetbrains.annotations.NotNull;

import java.util.Map;
import java.util.Optional;

import static io.perfeccionista.framework.pagefactory.elements.components.WebComponents.TBODY_ROW;
import static io.perfeccionista.framework.pagefactory.elements.components.WebComponents.TFOOT_ROW;
import static io.perfeccionista.framework.pagefactory.elements.components.WebComponents.THEAD_ROW;

// TODO: Map<String, SingleResult<T>> extractHeader(Map<String, WebTableCellValueExtractor<V>> columnExtractors);
// TODO: Map<String, MultipleResult<T>> extractAll(Map<String, WebTableCellValueExtractor<V>> columnExtractors);
// TODO: Map<String, SingleResult<T>> extractFooter(Map<String, WebTableCellValueExtractor<V>> columnExtractors);
@WebLocator(component = THEAD_ROW, xpath = ".//thead//tr")
@WebLocator(component = TBODY_ROW, xpath = ".//tbody//tr", single = false)
@WebLocator(component = TFOOT_ROW, xpath = ".//tfoot//tr")
public interface WebTable extends WebChildElement,
        ScrollToElementAvailable<WebTableFilter>, SizeAvailable {

    Optional<TableColumnHolder> getTableColumnHolder(@NotNull String columnName);

    @NotNull WebTableFilterResult filter(@NotNull WebTableFilter filter);

    @NotNull <V> SingleResult<V> extractHeader(@NotNull WebTableCellValueExtractor<V> extractor);

    @NotNull <V> MultipleResult<V> extractAllRows(@NotNull WebTableCellValueExtractor<V> extractor);

    @NotNull <V> SingleResult<V> extractFooter(@NotNull WebTableCellValueExtractor<V> extractor);

    // Actions

    @Override
    WebTable executeAction(@NotNull String name, Object... args);

    @Override
    WebTable executeInteraction(@NotNull String name, @NotNull WebChildElement other, Object... args);

    // Asserts

    @Override
    WebTable should(WebAssertCondition assertCondition);

    @Override
    WebTable should(WebAssertCondition assertCondition, InvocationName invocationName);

    // Get Color

    @Override
    WebTable componentShouldHaveColor(@NotNull String componentName, @NotNull String cssProperty, @NotNull Color expectedColor);

    @Override
    WebTable componentShouldNotHaveColor(@NotNull String componentName, @NotNull String cssProperty, @NotNull Color expectedColor);

    // Get Dimensions

    @Override
    WebTable componentShouldHaveDimensions(@NotNull String componentName, @NotNull Dimensions expectedDimensions);

    @Override
    WebTable componentShouldNotHaveDimensions(@NotNull String componentName, @NotNull Dimensions expectedDimensions);

    // Get Location

    @Override
    WebTable componentShouldHaveLocation(@NotNull String componentName, @NotNull Location expectedLocation);

    @Override
    WebTable componentShouldNotHaveLocation(@NotNull String componentName, @NotNull Location expectedLocation);

    // Get Screenshot

    @Override
    WebTable componentShouldLooksLike(@NotNull String componentName, @NotNull Screenshot expectedScreenshot);

    @Override
    WebTable componentShouldNotLooksLike(@NotNull String componentName, @NotNull Screenshot expectedScreenshot);

    // HoverTo

    @Override
    WebTable hoverTo(boolean withOutOfBounds);

    // IsDisplayed

    @Override
    WebTable shouldBeDisplayed();

    @Override
    WebTable shouldNotBeDisplayed();

    // IsInFocus

    @Override
    WebTable shouldBeInFocus();

    @Override
    WebTable shouldNotBeInFocus();

    // IsPresent

    @Override
    WebTable shouldBePresent();

    @Override
    WebTable shouldNotBePresent();

    // ScrollTo

    @Override
    WebTable scrollTo();

    // ScrollToElement

    @Override
    WebTable scrollToElement(@NotNull WebTableFilter filter);

    // Size

    @Override
    WebTable shouldHaveSize(@NotNull NumberValue<Integer> expectedSize);

    // WebComponents

    @Override
    WebTable componentShouldBePresent(@NotNull String componentName);

    @Override
    WebTable componentShouldNotBePresent(@NotNull String componentName);

    @Override
    WebTable componentShouldBeDisplayed(@NotNull String componentName);

    @Override
    WebTable componentShouldNotBeDisplayed(@NotNull String componentName);

    // WebProperties

    @Override
    WebTable shouldHavePropertyValue(@NotNull String propertyName, @NotNull StringValue expectedValue);

    @Override
    WebTable shouldHavePropertyValue(@NotNull String propertyName, @NotNull NumberValue<?> expectedValue);

    @Override
    WebTable shouldNotHavePropertyValue(@NotNull String propertyName, @NotNull StringValue expectedValue);

    @Override
    WebTable shouldNotHavePropertyValue(@NotNull String propertyName, @NotNull NumberValue<?> expectedValue);

}