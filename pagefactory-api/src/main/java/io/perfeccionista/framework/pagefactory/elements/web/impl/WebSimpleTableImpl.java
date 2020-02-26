package io.perfeccionista.framework.pagefactory.elements.web.impl;

import io.perfeccionista.framework.pagefactory.elements.locators.Locator;
import io.perfeccionista.framework.pagefactory.elements.mapping.ColumnMapper;
import io.perfeccionista.framework.pagefactory.elements.methods.ElementMethod;
import io.perfeccionista.framework.pagefactory.elements.web.AbstractWebChildElement;
import io.perfeccionista.framework.pagefactory.elements.web.WebSimpleTable;
import io.perfeccionista.framework.pagefactory.elements.web.methods.JsScrollToStringTableRowElement;
import io.perfeccionista.framework.pagefactory.elements.web.methods.JsSize;
import io.perfeccionista.framework.pagefactory.itemextractor.WebSimpleTableRowValueExtractor;
import io.perfeccionista.framework.pagefactory.itemfilter.MultipleResult;
import io.perfeccionista.framework.pagefactory.itemfilter.js.JsStringTableRowFilter;
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
@ElementMethod(type = SCROLL_TO_ELEMENT_METHOD, implementation = JsScrollToStringTableRowElement.class)
@ElementMethod(type = SIZE_METHOD, implementation = JsSize.class)
public class WebSimpleTableImpl extends AbstractWebChildElement implements WebSimpleTable {

    protected Map<String, ColumnMapper> columnMappers;

    @Override
    public OperationResult<String> getHeaderValue(String columnName) {
        return OperationResult.execute(() -> new WebSimpleTableRowValueExtractor(columnName).extractSingleHeaderValue(this).getItem());
    }

    @Override
    public OperationResult<MultipleResult<String>> getValues(String columnName) {
        return OperationResult.execute(() -> new WebSimpleTableRowValueExtractor(columnName).extractMultipleValues(this, Set.of()));
    }

    @Override
    public OperationResult<MultipleResult<String>> getValues(String columnName, JsStringTableRowFilter filter) {
        MultipleResult<Integer> result = filter.multipleResult(this);
        return OperationResult.execute(() -> new WebSimpleTableRowValueExtractor(columnName).setHash(result.getElementHash())
                .extractMultipleValues(this, result.getItems().keySet()));
    }

    @Override
    public OperationResult<String> getFooterValue(String columnName) {
        return OperationResult.execute(() -> new WebSimpleTableRowValueExtractor(columnName).extractSingleFooterValue(this).getItem());
    }

    @Override
    public OperationResult<Void> scrollToElement(JsStringTableRowFilter filter) {
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
