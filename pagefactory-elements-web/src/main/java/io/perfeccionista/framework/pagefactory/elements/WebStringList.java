package io.perfeccionista.framework.pagefactory.elements;

import io.perfeccionista.framework.pagefactory.elements.locators.WebLocator;
import io.perfeccionista.framework.pagefactory.elements.methods.Bounds;
import io.perfeccionista.framework.pagefactory.elements.methods.ElementMethod;
import io.perfeccionista.framework.pagefactory.elements.methods.JsScrollToStringBlockElement;
import io.perfeccionista.framework.pagefactory.elements.methods.JsSize;
import io.perfeccionista.framework.pagefactory.elements.methods.availability.ScrollToElementAvailable;
import io.perfeccionista.framework.pagefactory.elements.methods.availability.SizeAvailable;
import io.perfeccionista.framework.pagefactory.filter.MultipleResult;
import io.perfeccionista.framework.pagefactory.filter.WebStringListFilter;
import io.perfeccionista.framework.pagefactory.filter.WebStringListFilterResult;
import io.perfeccionista.framework.pagefactory.screenshots.Screenshot;
import io.perfeccionista.framework.value.number.NumberValue;
import io.perfeccionista.framework.value.string.StringValue;

import static io.perfeccionista.framework.pagefactory.elements.locators.Components.LI;
import static io.perfeccionista.framework.pagefactory.elements.methods.availability.AvailableMethod.SCROLL_TO_ELEMENT_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.availability.AvailableMethod.SIZE_METHOD;

/**
 * Внутри каждого блока находится BlockText
 */
@WebLocator(component = LI, xpath = ".//li", single = false)
@ElementMethod(type = SCROLL_TO_ELEMENT_METHOD, implementation = JsScrollToStringBlockElement.class)
@ElementMethod(type = SIZE_METHOD, implementation = JsSize.class)

public interface WebStringList extends WebChildElement,
        ScrollToElementAvailable<WebStringListFilter>, SizeAvailable {

    WebStringListFilterResult filter(WebStringListFilter filter);

    MultipleResult<String> extractAll();

    @Override
    WebStringList hoverTo(boolean withOutOfBounds);

    @Override
    WebStringList scrollTo();

    @Override
    WebStringList scrollToElement(WebStringListFilter filter);

    @Override
    WebStringList shouldBeDisplayed();

    @Override
    WebStringList shouldNotBeDisplayed();

    @Override
    WebStringList shouldHaveBounds(Bounds bounds);

    @Override
    WebStringList shouldHavePropertyValue(String propertyValue, StringValue stringValue);

    @Override
    WebStringList shouldHaveSize(NumberValue<Integer> integerValue);

    @Override
    WebStringList shouldLooksLike(Screenshot screenshot);

    @Override
    WebStringList stateShouldBeDisplayed(String stateName);

}