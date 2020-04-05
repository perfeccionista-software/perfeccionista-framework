package io.perfeccionista.framework.pagefactory.elements;

import io.perfeccionista.framework.pagefactory.elements.locators.WebLocator;
import io.perfeccionista.framework.pagefactory.elements.methods.Bounds;
import io.perfeccionista.framework.pagefactory.elements.methods.ElementMethod;
import io.perfeccionista.framework.pagefactory.elements.methods.JsScrollToTableRowElement;
import io.perfeccionista.framework.pagefactory.elements.methods.JsSize;
import io.perfeccionista.framework.pagefactory.elements.methods.availability.ScrollToElementAvailable;
import io.perfeccionista.framework.pagefactory.elements.methods.availability.SizeAvailable;
import io.perfeccionista.framework.pagefactory.extractor.WebTableCellValueExtractor;
import io.perfeccionista.framework.pagefactory.filter.MultipleResult;
import io.perfeccionista.framework.pagefactory.filter.WebTableFilter;
import io.perfeccionista.framework.pagefactory.filter.SingleResult;
import io.perfeccionista.framework.pagefactory.filter.WebTableFilterResult;
import io.perfeccionista.framework.pagefactory.screenshots.Screenshot;
import io.perfeccionista.framework.value.number.NumberValue;
import io.perfeccionista.framework.value.string.StringValue;

import static io.perfeccionista.framework.pagefactory.elements.locators.Components.TBODY_ROW;
import static io.perfeccionista.framework.pagefactory.elements.locators.Components.TFOOT_ROW;
import static io.perfeccionista.framework.pagefactory.elements.locators.Components.THEAD_ROW;
import static io.perfeccionista.framework.pagefactory.elements.methods.availability.AvailableMethod.SCROLL_TO_ELEMENT_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.availability.AvailableMethod.SIZE_METHOD;

@WebLocator(component = THEAD_ROW, xpath = ".//thead//tr")
@WebLocator(component = TBODY_ROW, xpath = ".//tbody//tr", single = false)
@WebLocator(component = TFOOT_ROW, xpath = ".//tfoot//tr")
@ElementMethod(type = SCROLL_TO_ELEMENT_METHOD, implementation = JsScrollToTableRowElement.class)
@ElementMethod(type = SIZE_METHOD, implementation = JsSize.class)
public interface WebTable extends WebChildElement,
        ScrollToElementAvailable<WebTableFilter>, SizeAvailable {

    <V> SingleResult<V> extractHeader(WebTableCellValueExtractor<V> extractor);

    WebTableFilterResult filter(WebTableFilter filter);

    <V> MultipleResult<V> extractAll(WebTableCellValueExtractor<V> extractor);

    <V> SingleResult<V> extractFooter(WebTableCellValueExtractor<V> extractor);

    @Override
    WebTable hoverTo(boolean withOutOfBounds);

    @Override
    WebTable scrollTo();

    @Override
    WebTable scrollToElement(WebTableFilter filter);

    @Override
    WebTable shouldBeDisplayed();

    @Override
    WebTable shouldNotBeDisplayed();

    @Override
    WebTable shouldHaveBounds(Bounds bounds);

    @Override
    WebTable shouldHavePropertyValue(String propertyValue, StringValue stringValue);

    @Override
    WebTable shouldHaveSize(NumberValue<Integer> integerValue);

    @Override
    WebTable shouldLooksLike(Screenshot screenshot);

    @Override
    WebTable stateShouldBeDisplayed(String stateName);
}