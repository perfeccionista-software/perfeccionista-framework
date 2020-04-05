package io.perfeccionista.framework.pagefactory.elements;

import io.perfeccionista.framework.pagefactory.elements.locators.WebLocator;
import io.perfeccionista.framework.pagefactory.elements.methods.Bounds;
import io.perfeccionista.framework.pagefactory.elements.methods.ElementMethod;
import io.perfeccionista.framework.pagefactory.elements.methods.JsScrollToStringTableRowElement;
import io.perfeccionista.framework.pagefactory.elements.methods.JsSize;
import io.perfeccionista.framework.pagefactory.elements.methods.availability.ScrollToElementAvailable;
import io.perfeccionista.framework.pagefactory.elements.methods.availability.SizeAvailable;
import io.perfeccionista.framework.pagefactory.filter.MultipleResult;
import io.perfeccionista.framework.pagefactory.filter.WebStringTableFilter;
import io.perfeccionista.framework.pagefactory.filter.SingleResult;
import io.perfeccionista.framework.pagefactory.filter.WebStringTableFilterResult;
import io.perfeccionista.framework.pagefactory.screenshots.Screenshot;
import io.perfeccionista.framework.value.number.NumberValue;
import io.perfeccionista.framework.value.string.StringValue;

import java.util.Map;
import java.util.Set;

import static io.perfeccionista.framework.pagefactory.elements.locators.Components.TBODY_ROW;
import static io.perfeccionista.framework.pagefactory.elements.locators.Components.TFOOT_ROW;
import static io.perfeccionista.framework.pagefactory.elements.locators.Components.THEAD_ROW;
import static io.perfeccionista.framework.pagefactory.elements.methods.availability.AvailableMethod.SCROLL_TO_ELEMENT_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.availability.AvailableMethod.SIZE_METHOD;

/**
 * Внутри каждой ячейки находится CellText
 */
@WebLocator(component = THEAD_ROW, xpath = ".//thead//tr")
@WebLocator(component = TBODY_ROW, xpath = ".//tbody//tr", single = false)
@WebLocator(component = TFOOT_ROW, xpath = ".//tfoot//tr")
@ElementMethod(type = SCROLL_TO_ELEMENT_METHOD, implementation = JsScrollToStringTableRowElement.class)
@ElementMethod(type = SIZE_METHOD, implementation = JsSize.class)

public interface WebStringTable extends WebChildElement,
        ScrollToElementAvailable<WebStringTableFilter>, SizeAvailable {

    SingleResult<String> extractHeader(String columnName);

    Map<String, SingleResult<String>> extractHeader(Set<String> columnNames);

    WebStringTableFilterResult filter(WebStringTableFilter filter);

    MultipleResult<String> extractAll(String columnName);

    Map<String, MultipleResult<String>> extractAll(Set<String> columnNames);

    SingleResult<String> extractFooter(String columnName);

    Map<String, SingleResult<String>> extractFooter(Set<String> columnNames);

    @Override
    WebStringTable hoverTo(boolean withOutOfBounds);

    @Override
    WebStringTable scrollTo();

    @Override
    WebStringTable scrollToElement(WebStringTableFilter filter);

    @Override
    WebStringTable shouldBeDisplayed();

    @Override
    WebStringTable shouldNotBeDisplayed();

    @Override
    WebStringTable shouldHaveBounds(Bounds bounds);

    @Override
    WebStringTable shouldHavePropertyValue(String propertyValue, StringValue stringValue);

    @Override
    WebStringTable shouldHaveSize(NumberValue<Integer> integerValue);

    @Override
    WebStringTable shouldLooksLike(Screenshot screenshot);

    @Override
    WebStringTable stateShouldBeDisplayed(String stateName);

}
