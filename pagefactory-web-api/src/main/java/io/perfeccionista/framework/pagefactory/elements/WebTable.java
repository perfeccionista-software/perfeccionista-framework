package io.perfeccionista.framework.pagefactory.elements;

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
import io.perfeccionista.framework.value.Value;
import io.perfeccionista.framework.value.number.NumberValue;
import io.perfeccionista.framework.value.string.StringValue;


public interface WebTable extends WebChildElement,
        ScrollToElementAvailable<WebTableFilter>, SizeAvailable {

    default WebTableFilterResult filter(WebTableFilter filter) {
        return filter.filter(this);
    }

    <V> SingleResult<V> extractHeader(WebTableCellValueExtractor<V> extractor);

    <V> MultipleResult<V> extractAllRows(WebTableCellValueExtractor<V> extractor);

    <V> SingleResult<V> extractFooter(WebTableCellValueExtractor<V> extractor);

    // TODO: Map<String, SingleResult<T>> extractHeader(Map<String, WebTableCellValueExtractor<V>> columnExtractors);

    // TODO: Map<String, MultipleResult<T>> extractAll(Map<String, WebTableCellValueExtractor<V>> columnExtractors);

    // TODO: Map<String, SingleResult<T>> extractFooter(Map<String, WebTableCellValueExtractor<V>> columnExtractors);


    @Override
    WebTable executeAction(String name, Object... args);

    @Override
    WebTable executeInteraction(String name, WebChildElement other, Object... args);


    @Override
    WebTable hoverTo(boolean withOutOfBounds);

    @Override
    WebTable scrollTo();

    @Override
    WebTable scrollToElement(WebTableFilter filter);


    @Override
    WebTable shouldBePresent();

    @Override
    WebTable shouldNotBePresent();

    @Override
    WebTable shouldBeDisplayed();

    @Override
    WebTable shouldNotBeDisplayed();

    @Override
    WebTable shouldBeInFocus();

    @Override
    WebTable shouldNotBeInFocus();


    @Override
    WebTable shouldHaveSize(Value<Integer> integerValue);

    @Override
    WebTable shouldHavePropertyValue(String propertyName, StringValue stringValue);

    @Override
    WebTable shouldHavePropertyValue(String propertyName, NumberValue<?> numberValue);

    @Override
    WebTable shouldNotHavePropertyValue(String propertyName, StringValue stringValue);

    @Override
    WebTable shouldNotHavePropertyValue(String propertyName, NumberValue<?> numberValue);


    @Override
    WebTable componentShouldBePresent(String componentName);

    @Override
    WebTable componentShouldNotBePresent(String componentName);

    @Override
    WebTable componentShouldBeDisplayed(String componentName);

    @Override
    WebTable componentShouldNotBeDisplayed(String componentName);

    @Override
    WebTable componentShouldHaveDimensions(String componentName, Dimensions dimensions);

    @Override
    WebTable componentShouldNotHaveDimensions(String componentName, Dimensions dimensions);

    @Override
    WebTable componentShouldHaveLocation(String componentName, Location location);

    @Override
    WebTable componentShouldNotHaveLocation(String componentName, Location location);

    @Override
    WebTable componentShouldLooksLike(String componentName, Screenshot screenshot);

    @Override
    WebTable componentShouldNotLooksLike(String componentName, Screenshot screenshot);
}