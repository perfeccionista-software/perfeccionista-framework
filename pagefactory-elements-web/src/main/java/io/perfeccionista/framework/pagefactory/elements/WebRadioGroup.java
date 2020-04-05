package io.perfeccionista.framework.pagefactory.elements;

import io.perfeccionista.framework.pagefactory.elements.locators.WebLocator;
import io.perfeccionista.framework.pagefactory.elements.methods.Bounds;
import io.perfeccionista.framework.pagefactory.elements.methods.ElementMethod;
import io.perfeccionista.framework.pagefactory.elements.methods.JsScrollToBlockElement;
import io.perfeccionista.framework.pagefactory.elements.methods.JsSize;
import io.perfeccionista.framework.pagefactory.elements.methods.availability.ScrollToElementAvailable;
import io.perfeccionista.framework.pagefactory.elements.methods.availability.SizeAvailable;
import io.perfeccionista.framework.pagefactory.extractor.WebRadioButtonValueExtractor;
import io.perfeccionista.framework.pagefactory.filter.MultipleResult;
import io.perfeccionista.framework.pagefactory.filter.WebRadioButtonFilter;
import io.perfeccionista.framework.pagefactory.filter.WebRadioButtonFilterResult;
import io.perfeccionista.framework.pagefactory.screenshots.Screenshot;
import io.perfeccionista.framework.value.number.NumberValue;
import io.perfeccionista.framework.value.string.StringValue;

import static io.perfeccionista.framework.pagefactory.elements.locators.Components.RADIO;
import static io.perfeccionista.framework.pagefactory.elements.methods.availability.AvailableMethod.SCROLL_TO_ELEMENT_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.availability.AvailableMethod.SIZE_METHOD;

@WebLocator(component = RADIO, xpath = ".//input", single = false)
@ElementMethod(type = SCROLL_TO_ELEMENT_METHOD, implementation = JsScrollToBlockElement.class)
@ElementMethod(type = SIZE_METHOD, implementation = JsSize.class)

public interface WebRadioGroup extends WebChildElement,
        ScrollToElementAvailable<WebRadioButtonFilter>, SizeAvailable {

    WebRadioButton getSelected();

    WebRadioButton getByLabel(StringValue label);

    WebRadioButton getByIndex(NumberValue<Integer> number);

    WebRadioButtonFilterResult filter(WebRadioButtonFilter filter);

    <V> MultipleResult<V> extractAll(WebRadioButtonValueExtractor<V> extractor);

    @Override
    WebRadioGroup hoverTo(boolean withOutOfBounds);

    @Override
    WebRadioGroup scrollTo();

    @Override
    WebRadioGroup scrollToElement(WebRadioButtonFilter filter);

    @Override
    WebRadioGroup shouldBeDisplayed();

    @Override
    WebRadioGroup shouldNotBeDisplayed();

    @Override
    WebRadioGroup shouldHaveBounds(Bounds bounds);

    @Override
    WebRadioGroup shouldHavePropertyValue(String propertyValue, StringValue stringValue);

    @Override
    WebRadioGroup shouldHaveSize(NumberValue<Integer> integerValue);

    @Override
    WebRadioGroup shouldLooksLike(Screenshot screenshot);

    @Override
    WebRadioGroup stateShouldBeDisplayed(String stateName);

}
