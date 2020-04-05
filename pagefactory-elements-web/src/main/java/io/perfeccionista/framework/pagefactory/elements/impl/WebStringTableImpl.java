package io.perfeccionista.framework.pagefactory.elements.impl;

import io.perfeccionista.framework.pagefactory.elements.AbstractWebChildElement;
import io.perfeccionista.framework.pagefactory.elements.WebStringTable;


import static io.perfeccionista.framework.pagefactory.elements.locators.Components.TBODY_ROW;
import static io.perfeccionista.framework.pagefactory.elements.methods.availability.AvailableMethod.SCROLL_TO_ELEMENT_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.availability.AvailableMethod.SIZE_METHOD;


public abstract class WebStringTableImpl extends AbstractWebChildElement implements WebStringTable {
//
//    protected Map<String, WebColumnMapper> columnMappers;
//
//    @Override
//    public String getHeaderValue(String columnName) {
//        return new WebSimpleTableRowValueExtractor(columnName).extractSingleHeaderValue(this).get();
//    }
//
//    @Override
//    public MultipleResult<String> getValues(String columnName) {
//        return new WebSimpleTableRowValueExtractor(columnName).extractMultipleValues(this, MultipleResult.empty());
//    }
//
//    @Override
//    public MultipleResult<String> getValues(String columnName, WebStringTableRowFilter filter) {
//        MultipleResult<Integer> result = filter.multipleResult(this);
//        return new WebSimpleTableRowValueExtractor(columnName).setHash(result.getElementHash())
//                .extractMultipleValues(this, result);
//    }
//
//    @Override
//    public String getFooterValue(String columnName) {
//        return new WebSimpleTableRowValueExtractor(columnName).extractSingleFooterValue(this).get();
//    }
//
//    @Override
//    public void scrollToElement(WebStringTableRowFilter filter) {
//        getMethodImplementation(SCROLL_TO_ELEMENT_METHOD, Void.class).execute(this, filter);
//    }
//
//    @Override
//    public int size() {
//        return getMethodImplementation(SIZE_METHOD, Integer.class).execute(this, TBODY_ROW);
//    }
//
//    @Override
//    public Optional<WebColumnMapper> getColumnMapper(String columnName) {
//        return Optional.ofNullable(columnMappers.get(columnName));
//    }

}
