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

    Optional<TableColumnHolder> getTableColumnHolder(String columnName);

    WebTableFilterResult filter(WebTableFilter filter);

    <V> SingleResult<V> extractHeader(WebTableCellValueExtractor<V> extractor);

    <V> MultipleResult<V> extractAllRows(WebTableCellValueExtractor<V> extractor);

    <V> SingleResult<V> extractFooter(WebTableCellValueExtractor<V> extractor);

    // Actions

    @Override
    WebTable executeAction(String name, Object... args);

    @Override
    WebTable executeInteraction(String name, WebChildElement other, Object... args);

    // Asserts

    @Override
    WebTable should(WebAssertCondition assertCondition);

    @Override
    WebTable should(WebAssertCondition assertCondition, InvocationName invocationName);

    // Get Color

    @Override
    WebTable componentShouldHaveColor(String componentName, String cssProperty, Color expectedColor);

    @Override
    WebTable componentShouldNotHaveColor(String componentName, String cssProperty, Color expectedColor);

    // Get Dimensions

    @Override
    WebTable componentShouldHaveDimensions(String componentName, Dimensions expectedDimensions);

    @Override
    WebTable componentShouldNotHaveDimensions(String componentName, Dimensions expectedDimensions);

    // Get Location

    @Override
    WebTable componentShouldHaveLocation(String componentName, Location expectedLocation);

    @Override
    WebTable componentShouldNotHaveLocation(String componentName, Location expectedLocation);

    // Get Screenshot

    @Override
    WebTable componentShouldLooksLike(String componentName, Screenshot expectedScreenshot);

    @Override
    WebTable componentShouldNotLooksLike(String componentName, Screenshot expectedScreenshot);

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
    WebTable scrollToElement(WebTableFilter filter);

    // Size

    @Override
    WebTable shouldHaveSize(NumberValue<Integer> expectedSize);

    // WebComponents

    @Override
    WebTable componentShouldBePresent(String componentName);

    @Override
    WebTable componentShouldNotBePresent(String componentName);

    @Override
    WebTable componentShouldBeDisplayed(String componentName);

    @Override
    WebTable componentShouldNotBeDisplayed(String componentName);

    // WebProperties

    @Override
    WebTable shouldHavePropertyValue(String propertyName, StringValue expectedValue);

    @Override
    WebTable shouldHavePropertyValue(String propertyName, NumberValue<?> expectedValue);

    @Override
    WebTable shouldNotHavePropertyValue(String propertyName, StringValue expectedValue);

    @Override
    WebTable shouldNotHavePropertyValue(String propertyName, NumberValue<?> expectedValue);

}