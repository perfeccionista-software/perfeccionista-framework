package io.perfeccionista.framework.pagefactory.elements;

import io.perfeccionista.framework.pagefactory.elements.methods.ClickToElementAvailable;
import io.perfeccionista.framework.pagefactory.elements.methods.Dimensions;
import io.perfeccionista.framework.pagefactory.elements.methods.Location;
import io.perfeccionista.framework.pagefactory.elements.methods.ScrollToElementAvailable;
import io.perfeccionista.framework.pagefactory.elements.methods.SizeAvailable;
import io.perfeccionista.framework.pagefactory.extractor.list.WebListBlockValueExtractor;
import io.perfeccionista.framework.pagefactory.filter.MultipleResult;
import io.perfeccionista.framework.pagefactory.filter.list.WebListFilter;
import io.perfeccionista.framework.pagefactory.filter.list.WebListFilterResult;
import io.perfeccionista.framework.pagefactory.screenshots.Screenshot;
import io.perfeccionista.framework.value.Value;
import io.perfeccionista.framework.value.number.NumberValue;
import io.perfeccionista.framework.value.string.StringValue;


public interface WebList extends WebChildElement,
        ScrollToElementAvailable<WebListFilter>, ClickToElementAvailable<WebListFilter>, SizeAvailable {

    default WebListFilterResult filter(WebListFilter filter) {
        return filter.filter(this);
    }

    <V> MultipleResult<V> extractAll(WebListBlockValueExtractor<V> extractor);

    @Override
    WebList executeAction(String name, Object... args);

    @Override
    WebList executeInteraction(String name, WebChildElement other, Object... args);


    @Override
    WebList clickToElement(WebListFilter filter); // Тут нужно еще скроллить к элементу

    @Override
    WebList hoverTo(boolean withOutOfBounds);

    @Override
    WebList scrollTo();

    @Override
    WebList scrollToElement(WebListFilter filter);


    @Override
    WebList shouldBePresent();

    @Override
    WebList shouldNotBePresent();

    @Override
    WebList shouldBeDisplayed();

    @Override
    WebList shouldNotBeDisplayed();

    @Override
    WebList shouldBeInFocus();

    @Override
    WebList shouldNotBeInFocus();


    @Override
    WebList shouldHaveSize(Value<Integer> integerValue);

    @Override
    WebList shouldHavePropertyValue(String propertyName, StringValue stringValue);

    @Override
    WebList shouldHavePropertyValue(String propertyName, NumberValue<?> numberValue);

    @Override
    WebList shouldNotHavePropertyValue(String propertyName, StringValue stringValue);

    @Override
    WebList shouldNotHavePropertyValue(String propertyName, NumberValue<?> numberValue);


    @Override
    WebList componentShouldBePresent(String componentName);

    @Override
    WebList componentShouldNotBePresent(String componentName);

    @Override
    WebList componentShouldBeDisplayed(String componentName);

    @Override
    WebList componentShouldNotBeDisplayed(String componentName);

    @Override
    WebList componentShouldHaveDimensions(String componentName, Dimensions dimensions);

    @Override
    WebList componentShouldNotHaveDimensions(String componentName, Dimensions dimensions);

    @Override
    WebList componentShouldHaveLocation(String componentName, Location location);

    @Override
    WebList componentShouldNotHaveLocation(String componentName, Location location);

    @Override
    WebList componentShouldLooksLike(String componentName, Screenshot screenshot);

    @Override
    WebList componentShouldNotLooksLike(String componentName, Screenshot screenshot);
}
