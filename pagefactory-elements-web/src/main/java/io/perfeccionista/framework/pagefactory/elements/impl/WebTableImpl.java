package io.perfeccionista.framework.pagefactory.elements.impl;

import io.perfeccionista.framework.pagefactory.elements.AbstractWebChildElement;
import io.perfeccionista.framework.pagefactory.elements.WebTable;
import io.perfeccionista.framework.pagefactory.extractor.WebTableCellValueExtractor;
import io.perfeccionista.framework.pagefactory.filter.MultipleResult;
import io.perfeccionista.framework.pagefactory.filter.WebTableFilter;

import java.util.Map;
import java.util.Optional;

import static io.perfeccionista.framework.pagefactory.elements.locators.Components.TBODY_ROW;
import static io.perfeccionista.framework.pagefactory.elements.methods.availability.AvailableMethod.SCROLL_TO_ELEMENT_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.availability.AvailableMethod.SIZE_METHOD;


public abstract class WebTableImpl extends AbstractWebChildElement implements WebTable {

//    protected Map<String, WebColumnMapper> columnMappers;
//
//    @Override
//    public <V> V getHeaderValue(WebTableCellValueExtractor<V> extractor) {
//        return extractor.extractSingleHeaderValue(this).get();
//    }
//
//    @Override
//    public <V> MultipleResult<V> getValues(WebTableCellValueExtractor<V> extractor) {
//        return extractor.extractMultipleValues(this, MultipleResult.empty());
//    }
//
//    @Override
//    public <V> MultipleResult<V> getValues(WebTableCellValueExtractor<V> extractor, WebTableFilter filter) {
//        MultipleResult<Integer> result = filter.multipleResult(this);
//        return extractor.setHash(result.getElementHash()).extractMultipleValues(this, result);
//    }
//
//    @Override
//    public <V> V getFooterValue(WebTableCellValueExtractor<V> extractor) {
//        return extractor.extractSingleFooterValue(this).get();
//    }
//
//
//    @Override
//    public void scrollToElement(WebTableFilter filter) {
//        getMethodImplementation(SCROLL_TO_ELEMENT_METHOD, Void.class).execute(this, filter);
//    }
//
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
