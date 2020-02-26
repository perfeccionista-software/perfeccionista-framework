package io.perfeccionista.framework.pagefactory.elements.web.impl;

import io.perfeccionista.framework.pagefactory.elements.locators.Locator;
import io.perfeccionista.framework.pagefactory.elements.mapping.ColumnMapper;
import io.perfeccionista.framework.pagefactory.elements.methods.ElementMethod;
import io.perfeccionista.framework.pagefactory.elements.web.AbstractWebChildElement;
import io.perfeccionista.framework.pagefactory.elements.web.WebTable;
import io.perfeccionista.framework.pagefactory.elements.web.methods.JsScrollToTableRowElement;
import io.perfeccionista.framework.pagefactory.elements.web.methods.JsSize;
import io.perfeccionista.framework.pagefactory.itemextractor.JsWebTableRowValueExtractor;
import io.perfeccionista.framework.pagefactory.itemfilter.MultipleResult;
import io.perfeccionista.framework.pagefactory.itemfilter.js.JsTableRowFilter;
import io.perfeccionista.framework.pagefactory.operations.OperationResult;

import java.util.Map;
import java.util.Optional;
import java.util.Set;

import static io.perfeccionista.framework.pagefactory.elements.locators.Components.TBODY_ROW;
import static io.perfeccionista.framework.pagefactory.elements.locators.Components.TFOOT_ROW;
import static io.perfeccionista.framework.pagefactory.elements.locators.Components.THEAD_ROW;
import static io.perfeccionista.framework.pagefactory.elements.methods.availability.AvailableMethod.SCROLL_TO_ELEMENT_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.availability.AvailableMethod.SIZE_METHOD;

@Locator(component = THEAD_ROW, xpath = ".//thead//tr")
@Locator(component = TBODY_ROW, xpath = ".//tbody//tr", single = false)
@Locator(component = TFOOT_ROW, xpath = ".//tfoot//tr")
@ElementMethod(type = SCROLL_TO_ELEMENT_METHOD, implementation = JsScrollToTableRowElement.class)
@ElementMethod(type = SIZE_METHOD, implementation = JsSize.class)
public class WebTableImpl extends AbstractWebChildElement implements WebTable {

    protected Map<String, ColumnMapper> columnMappers;

    @Override
    public <V> OperationResult<MultipleResult<V>> getValues(JsWebTableRowValueExtractor<V> extractor) {
        return OperationResult.execute(() -> extractor.extractMultipleValues(this, Set.of()));
    }

    @Override
    public <V> OperationResult<MultipleResult<V>> getValues(JsWebTableRowValueExtractor<V> extractor, JsTableRowFilter filter) {
        return OperationResult.execute(() -> {
            MultipleResult<Integer> result = filter.multipleResult(this);
            return extractor.setHash(result.getElementHash()).extractMultipleValues(this, result.getItems().keySet());
        });
    }

    @Override
    public <V> OperationResult<V> getHeaderValue(JsWebTableRowValueExtractor<V> extractor) {
        return OperationResult.execute(() -> extractor.extractSingleHeaderValue(this).getItem());
    }

    @Override
    public <V> OperationResult<V> getFooterValue(JsWebTableRowValueExtractor<V> extractor) {
        return OperationResult.execute(() -> extractor.extractSingleFooterValue(this).getItem());
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
    public Optional<ColumnMapper> getColumnMapper(String columnName) {
        return Optional.ofNullable(columnMappers.get(columnName));
    }

}
