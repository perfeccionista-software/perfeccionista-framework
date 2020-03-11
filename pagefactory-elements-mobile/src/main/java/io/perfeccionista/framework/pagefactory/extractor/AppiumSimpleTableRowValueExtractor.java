package io.perfeccionista.framework.pagefactory.extractor;

import io.appium.java_client.MobileElement;
import io.perfeccionista.framework.pagefactory.elements.mapping.MobileColumnMapper;
import io.perfeccionista.framework.pagefactory.elements.MobileSimpleTable;
import io.perfeccionista.framework.pagefactory.filter.MultipleResult;
import io.perfeccionista.framework.pagefactory.filter.SingleResult;

import java.util.Optional;

public class AppiumSimpleTableRowValueExtractor implements AppiumValueExtractor<MobileSimpleTable, String> {

    private final String columnName;
    private String hash;

    public AppiumSimpleTableRowValueExtractor(String columnName) {
        this.columnName = columnName;
    }

    public AppiumSimpleTableRowValueExtractor setHash(String hash) {
        this.hash = hash;
        return this;
    }

    public String getHash() {
        return hash;
    }

    public SingleResult<String> extractSingleHeaderValue(MobileSimpleTable element) {
        Optional<MobileColumnMapper> columnMapper = element.getColumnMapper(columnName);
        return null;
    }

    public SingleResult<String> extractSingleFooterValue(MobileSimpleTable element) {
        Optional<MobileColumnMapper> columnMapper = element.getColumnMapper(columnName);
        return null;
    }

    @Override
    public SingleResult<String> extractSingleValue(MobileSimpleTable element) {
        Optional<MobileColumnMapper> columnMapper = element.getColumnMapper(columnName);
        return null;
    }

    @Override
    public MultipleResult<String> extractMultipleValues(MobileSimpleTable element, MultipleResult<MobileElement> filterResult) {
        return null;
    }

}

