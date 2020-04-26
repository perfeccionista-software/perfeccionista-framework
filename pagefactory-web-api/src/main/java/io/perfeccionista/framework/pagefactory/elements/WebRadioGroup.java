package io.perfeccionista.framework.pagefactory.elements;

import io.perfeccionista.framework.pagefactory.elements.methods.Bounds;
import io.perfeccionista.framework.pagefactory.elements.methods.ScrollToElementAvailable;
import io.perfeccionista.framework.pagefactory.elements.methods.SizeAvailable;
import io.perfeccionista.framework.pagefactory.extractor.radio.WebRadioButtonValueExtractor;
import io.perfeccionista.framework.pagefactory.filter.MultipleResult;
import io.perfeccionista.framework.pagefactory.filter.radio.WebRadioButtonFilter;
import io.perfeccionista.framework.pagefactory.filter.radio.WebRadioButtonFilterResult;
import io.perfeccionista.framework.pagefactory.screenshots.Screenshot;
import io.perfeccionista.framework.value.Value;
import io.perfeccionista.framework.value.number.NumberValue;
import io.perfeccionista.framework.value.string.StringValue;


public interface WebRadioGroup extends WebChildElement,
        ScrollToElementAvailable<WebRadioButtonFilter>, SizeAvailable {

    default WebRadioButtonFilterResult filter(WebRadioButtonFilter filter) {
        return filter.filter(this);
    }

    <V> MultipleResult<V> extractAll(WebRadioButtonValueExtractor<V> extractor);

    WebRadioButton getSelected();

    WebRadioButton getByLabel(StringValue label);

    WebRadioButton getByIndex(NumberValue<Integer> index);

    @Override
    WebRadioGroup executeAction(String name, Object... args);

    @Override
    WebRadioGroup executeInteraction(String name, WebChildElement other, Object... args);


    @Override
    WebRadioGroup hoverTo(boolean withOutOfBounds);

    @Override
    WebRadioGroup scrollTo();

    @Override
    WebRadioGroup scrollToElement(WebRadioButtonFilter filter);


    @Override
    WebRadioGroup shouldBePresent();

    @Override
    WebRadioGroup shouldNotBePresent();

    @Override
    WebRadioGroup shouldBeDisplayed();

    @Override
    WebRadioGroup shouldNotBeDisplayed();

    @Override
    WebRadioGroup shouldBeInFocus();

    @Override
    WebRadioGroup shouldNotBeInFocus();


    @Override
    WebRadioGroup shouldHaveSize(Value<Integer> integerValue);

    @Override
    WebRadioGroup shouldHavePropertyValue(String propertyName, StringValue stringValue);

    @Override
    WebRadioGroup shouldHavePropertyValue(String propertyName, NumberValue<?> numberValue);

    @Override
    WebRadioGroup shouldNotHavePropertyValue(String propertyName, StringValue stringValue);

    @Override
    WebRadioGroup shouldNotHavePropertyValue(String propertyName, NumberValue<?> numberValue);


    @Override
    WebRadioGroup componentShouldBePresent(String componentName);

    @Override
    WebRadioGroup componentShouldNotBePresent(String componentName);

    @Override
    WebRadioGroup componentShouldBeDisplayed(String componentName);

    @Override
    WebRadioGroup componentShouldNotBeDisplayed(String componentName);

    @Override
    WebRadioGroup componentShouldHaveBounds(String componentName, Bounds bounds);

    @Override
    WebRadioGroup componentShouldNotHaveBounds(String componentName, Bounds bounds);

    @Override
    WebRadioGroup componentShouldLooksLike(String componentName, Screenshot screenshot);

    @Override
    WebRadioGroup componentShouldNotLooksLike(String componentName, Screenshot screenshot);
}
