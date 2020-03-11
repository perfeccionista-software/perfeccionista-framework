package io.perfeccionista.framework.pagefactory.elements.impl;

import io.perfeccionista.framework.pagefactory.elements.locators.WebLocator;
import io.perfeccionista.framework.pagefactory.elements.mapping.WebColumnMapper;
import io.perfeccionista.framework.pagefactory.elements.methods.ElementMethod;
import io.perfeccionista.framework.pagefactory.elements.AbstractWebChildElement;
import io.perfeccionista.framework.pagefactory.elements.WebTable;
import io.perfeccionista.framework.pagefactory.elements.methods.JsScrollToTableRowElement;
import io.perfeccionista.framework.pagefactory.elements.methods.JsSize;
import io.perfeccionista.framework.pagefactory.extractor.JsTableRowValueExtractor;
import io.perfeccionista.framework.pagefactory.filter.MultipleResult;
import io.perfeccionista.framework.pagefactory.filter.JsTableRowFilter;
import io.perfeccionista.framework.pagefactory.operations.OperationResult;

import java.util.Map;
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
public class WebTableImpl extends AbstractWebChildElement implements WebTable {

    protected Map<String, WebColumnMapper> columnMappers;

    @Override
    public <V> OperationResult<MultipleResult<V>> getValues(JsTableRowValueExtractor<V> extractor) {
        return OperationResult.of(() -> extractor.extractMultipleValues(this, MultipleResult.empty()));
    }

    @Override
    public <V> OperationResult<MultipleResult<V>> getValues(JsTableRowValueExtractor<V> extractor, JsTableRowFilter filter) {
        return OperationResult.of(() -> {
            MultipleResult<Integer> result = filter.multipleResult(this);
            return extractor.setHash(result.getElementHash()).extractMultipleValues(this, result);
        });
    }

    @Override
    public <V> OperationResult<V> getHeaderValue(JsTableRowValueExtractor<V> extractor) {
        return OperationResult.of(() -> extractor.extractSingleHeaderValue(this).getItem());
    }

    @Override
    public <V> OperationResult<V> getFooterValue(JsTableRowValueExtractor<V> extractor) {
        return OperationResult.of(() -> extractor.extractSingleFooterValue(this).getItem());
    }

    @Override
    public OperationResult<Void> scrollToElement(JsTableRowFilter filter) {
        return getMethodImplementation(SCROLL_TO_ELEMENT_METHOD, Void.class).execute(this, filter);
    }

    @Override
    public OperationResult<Integer> size() {
        return getMethodImplementation(SIZE_METHOD, Integer.class).execute(this, TBODY_ROW);
    }

    @Override
    public Optional<WebColumnMapper> getColumnMapper(String columnName) {
        return Optional.ofNullable(columnMappers.get(columnName));
    }

}
