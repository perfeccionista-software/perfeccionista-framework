package io.perfeccionista.framework.pagefactory.elements.impl;

import io.perfeccionista.framework.pagefactory.elements.locators.WebLocator;
import io.perfeccionista.framework.pagefactory.elements.mapping.WebColumnMapper;
import io.perfeccionista.framework.pagefactory.elements.methods.ElementMethod;
import io.perfeccionista.framework.pagefactory.elements.AbstractWebChildElement;
import io.perfeccionista.framework.pagefactory.elements.WebSimpleTable;
import io.perfeccionista.framework.pagefactory.elements.methods.JsScrollToStringTableRowElement;
import io.perfeccionista.framework.pagefactory.elements.methods.JsSize;
import io.perfeccionista.framework.pagefactory.extractor.WebSimpleTableRowValueExtractor;
import io.perfeccionista.framework.pagefactory.filter.MultipleResult;
import io.perfeccionista.framework.pagefactory.filter.JsStringTableRowFilter;
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
@ElementMethod(type = SCROLL_TO_ELEMENT_METHOD, implementation = JsScrollToStringTableRowElement.class)
@ElementMethod(type = SIZE_METHOD, implementation = JsSize.class)
public class WebSimpleTableImpl extends AbstractWebChildElement implements WebSimpleTable {

    protected Map<String, WebColumnMapper> columnMappers;

    @Override
    public OperationResult<String> getHeaderValue(String columnName) {
        return OperationResult.of(() -> new WebSimpleTableRowValueExtractor(columnName).extractSingleHeaderValue(this).getItem());
    }

    @Override
    public OperationResult<MultipleResult<String>> getValues(String columnName) {
        return OperationResult.of(() -> new WebSimpleTableRowValueExtractor(columnName).extractMultipleValues(this, MultipleResult.empty()));
    }

    @Override
    public OperationResult<MultipleResult<String>> getValues(String columnName, JsStringTableRowFilter filter) {
        MultipleResult<Integer> result = filter.multipleResult(this);
        return OperationResult.of(() -> new WebSimpleTableRowValueExtractor(columnName).setHash(result.getElementHash())
                .extractMultipleValues(this, result));
    }

    @Override
    public OperationResult<String> getFooterValue(String columnName) {
        return OperationResult.of(() -> new WebSimpleTableRowValueExtractor(columnName).extractSingleFooterValue(this).getItem());
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
    public Optional<WebColumnMapper> getColumnMapper(String columnName) {
        return Optional.ofNullable(columnMappers.get(columnName));
    }

}
