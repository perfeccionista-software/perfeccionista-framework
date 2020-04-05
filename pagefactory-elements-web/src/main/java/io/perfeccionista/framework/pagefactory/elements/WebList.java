package io.perfeccionista.framework.pagefactory.elements;

import io.perfeccionista.framework.pagefactory.elements.locators.WebLocator;
import io.perfeccionista.framework.pagefactory.elements.methods.Bounds;
import io.perfeccionista.framework.pagefactory.elements.methods.ElementMethod;
import io.perfeccionista.framework.pagefactory.elements.methods.JsScrollToBlockElement;
import io.perfeccionista.framework.pagefactory.elements.methods.JsSize;
import io.perfeccionista.framework.pagefactory.elements.methods.availability.ScrollToElementAvailable;
import io.perfeccionista.framework.pagefactory.elements.methods.availability.SizeAvailable;
import io.perfeccionista.framework.pagefactory.extractor.WebListBlockValueExtractor;
import io.perfeccionista.framework.pagefactory.filter.MultipleResult;
import io.perfeccionista.framework.pagefactory.filter.WebListFilter;
import io.perfeccionista.framework.pagefactory.filter.WebListFilterResult;
import io.perfeccionista.framework.pagefactory.screenshots.Screenshot;
import io.perfeccionista.framework.value.number.NumberValue;
import io.perfeccionista.framework.value.string.StringValue;

import static io.perfeccionista.framework.pagefactory.elements.locators.Components.LI;
import static io.perfeccionista.framework.pagefactory.elements.methods.availability.AvailableMethod.SCROLL_TO_ELEMENT_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.availability.AvailableMethod.SIZE_METHOD;

@WebLocator(component = LI, xpath = ".//li", single = false)
@ElementMethod(type = SCROLL_TO_ELEMENT_METHOD, implementation = JsScrollToBlockElement.class)
@ElementMethod(type = SIZE_METHOD, implementation = JsSize.class)
public interface WebList extends WebChildElement,
        ScrollToElementAvailable<WebListFilter>, SizeAvailable {

    WebListFilterResult filter(WebListFilter filter);

    <V> MultipleResult<V> extractAll(WebListBlockValueExtractor<V> extractor);

    @Override
    WebList hoverTo(boolean withOutOfBounds);

    @Override
    WebList scrollTo();

    @Override
    WebList scrollToElement(WebListFilter filter);

    @Override
    WebList shouldBeDisplayed();

    @Override
    WebList shouldNotBeDisplayed();

    @Override
    WebList shouldHaveBounds(Bounds bounds);

    @Override
    WebList shouldHavePropertyValue(String propertyValue, StringValue stringValue);

    @Override
    WebList shouldHaveSize(NumberValue<Integer> integerValue);

    @Override
    WebList shouldLooksLike(Screenshot screenshot);

    @Override
    WebList stateShouldBeDisplayed(String stateName);

}
