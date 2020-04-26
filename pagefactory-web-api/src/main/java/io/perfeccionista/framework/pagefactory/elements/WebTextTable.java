package io.perfeccionista.framework.pagefactory.elements;

import io.perfeccionista.framework.pagefactory.elements.methods.Bounds;
import io.perfeccionista.framework.pagefactory.elements.methods.ScrollToElementAvailable;
import io.perfeccionista.framework.pagefactory.elements.methods.SizeAvailable;
import io.perfeccionista.framework.pagefactory.filter.MultipleResult;
import io.perfeccionista.framework.pagefactory.filter.texttable.WebTextTableFilter;
import io.perfeccionista.framework.pagefactory.filter.SingleResult;
import io.perfeccionista.framework.pagefactory.filter.texttable.WebTextTableFilterResult;
import io.perfeccionista.framework.pagefactory.screenshots.Screenshot;
import io.perfeccionista.framework.value.Value;
import io.perfeccionista.framework.value.number.NumberValue;
import io.perfeccionista.framework.value.string.StringValue;


public interface WebTextTable extends WebChildElement,
        ScrollToElementAvailable<WebTextTableFilter>, SizeAvailable {

    default WebTextTableFilterResult filter(WebTextTableFilter filter) {
        return filter.filter(this);
    }

    SingleResult<String> extractHeader(String columnName);

    MultipleResult<String> extractAllRows(String columnName);

    SingleResult<String> extractFooter(String columnName);

    // TODO: Map<String, SingleResult<String>> extractHeader(Set<String> columnNames);

    // TODO: Map<String, MultipleResult<String>> extractAll(Set<String> columnNames);

    // TODO: Map<String, SingleResult<String>> extractFooter(Set<String> columnNames);


    @Override
    WebTextTable executeAction(String name, Object... args);

    @Override
    WebTextTable executeInteraction(String name, WebChildElement other, Object... args);


    @Override
    WebTextTable hoverTo(boolean withOutOfBounds);

    @Override
    WebTextTable scrollTo();

    @Override
    WebTextTable scrollToElement(WebTextTableFilter filter);


    @Override
    WebTextTable shouldBePresent();

    @Override
    WebTextTable shouldNotBePresent();

    @Override
    WebTextTable shouldBeDisplayed();

    @Override
    WebTextTable shouldNotBeDisplayed();

    @Override
    WebTextTable shouldBeInFocus();

    @Override
    WebTextTable shouldNotBeInFocus();


    @Override
    WebTextTable shouldHaveSize(Value<Integer> integerValue);

    @Override
    WebTextTable shouldHavePropertyValue(String propertyName, StringValue stringValue);

    @Override
    WebTextTable shouldHavePropertyValue(String propertyName, NumberValue<?> numberValue);

    @Override
    WebTextTable shouldNotHavePropertyValue(String propertyName, StringValue stringValue);

    @Override
    WebTextTable shouldNotHavePropertyValue(String propertyName, NumberValue<?> numberValue);


    @Override
    WebTextTable componentShouldBePresent(String componentName);

    @Override
    WebTextTable componentShouldNotBePresent(String componentName);

    @Override
    WebTextTable componentShouldBeDisplayed(String componentName);

    @Override
    WebTextTable componentShouldNotBeDisplayed(String componentName);

    @Override
    WebTextTable componentShouldHaveBounds(String componentName, Bounds bounds);

    @Override
    WebTextTable componentShouldNotHaveBounds(String componentName, Bounds bounds);

    @Override
    WebTextTable componentShouldLooksLike(String componentName, Screenshot screenshot);

    @Override
    WebTextTable componentShouldNotLooksLike(String componentName, Screenshot screenshot);
}
