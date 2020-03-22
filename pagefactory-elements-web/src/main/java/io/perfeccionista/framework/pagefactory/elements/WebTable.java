package io.perfeccionista.framework.pagefactory.elements;

import io.perfeccionista.framework.pagefactory.elements.locators.WebLocator;
import io.perfeccionista.framework.pagefactory.elements.mapping.WebColumnMapper;
import io.perfeccionista.framework.pagefactory.elements.methods.ElementMethod;
import io.perfeccionista.framework.pagefactory.elements.methods.JsScrollToTableRowElement;
import io.perfeccionista.framework.pagefactory.elements.methods.JsSize;
import io.perfeccionista.framework.pagefactory.elements.methods.availability.ScrollToElementAvailable;
import io.perfeccionista.framework.pagefactory.elements.methods.availability.SizeAvailable;
import io.perfeccionista.framework.pagefactory.extractor.WebTableRowValueExtractor;
import io.perfeccionista.framework.pagefactory.filter.MultipleResult;
import io.perfeccionista.framework.pagefactory.filter.WebTableRowFilter;
import io.perfeccionista.framework.pagefactory.filter.SingleResult;

import java.util.Optional;

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
        ScrollToElementAvailable<WebTableRowFilter>, SizeAvailable {

    Optional<WebColumnMapper> getColumnMapper(String columnName);

    <V> V getHeaderValue(WebTableRowValueExtractor<V> extractor);

    <V> SingleResult<V> getValue(WebTableRowValueExtractor<V> extractor, WebTableRowFilter filter);

    <V> MultipleResult<V> getValues(WebTableRowValueExtractor<V> extractor);

    <V> MultipleResult<V> getValues(WebTableRowValueExtractor<V> extractor, WebTableRowFilter filter);

    <V> V getFooterValue(WebTableRowValueExtractor<V> extractor);

}