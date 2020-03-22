package io.perfeccionista.framework.pagefactory.elements;

import io.perfeccionista.framework.pagefactory.elements.locators.WebLocator;
import io.perfeccionista.framework.pagefactory.elements.methods.ElementMethod;
import io.perfeccionista.framework.pagefactory.elements.methods.JsScrollToBlockElement;
import io.perfeccionista.framework.pagefactory.elements.methods.JsSize;
import io.perfeccionista.framework.pagefactory.elements.methods.availability.ScrollToElementAvailable;
import io.perfeccionista.framework.pagefactory.elements.methods.availability.SizeAvailable;
import io.perfeccionista.framework.pagefactory.extractor.WebRadioButtonValueExtractor;
import io.perfeccionista.framework.pagefactory.filter.MultipleResult;
import io.perfeccionista.framework.pagefactory.filter.WebRadioButtonFilter;
import io.perfeccionista.framework.pagefactory.filter.SingleResult;
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

    WebRadioButton getByNumber(NumberValue<Integer> number);

    <V> SingleResult<V> getValue(WebRadioButtonValueExtractor<V> extractor, WebRadioButtonFilter filter);

    <V> MultipleResult<V> getValues(WebRadioButtonValueExtractor<V> extractor);

    <V> MultipleResult<V> getValues(WebRadioButtonValueExtractor<V> extractor, WebRadioButtonFilter filter);

}
