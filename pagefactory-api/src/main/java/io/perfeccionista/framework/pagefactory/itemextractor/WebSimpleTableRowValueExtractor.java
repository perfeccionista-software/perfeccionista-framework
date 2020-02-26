package io.perfeccionista.framework.pagefactory.itemextractor;

import io.perfeccionista.framework.pagefactory.elements.mapping.ColumnMapper;
import io.perfeccionista.framework.pagefactory.elements.web.WebSimpleTable;
import io.perfeccionista.framework.pagefactory.itemfilter.MultipleResult;
import io.perfeccionista.framework.pagefactory.itemfilter.SingleResult;

import java.util.Collection;
import java.util.Optional;

public class WebSimpleTableRowValueExtractor implements WebValueExtractor<WebSimpleTable, String> {

    private final String columnName;
    private String hash;

    public WebSimpleTableRowValueExtractor(String columnName) {
        this.columnName = columnName;
    }

    public WebSimpleTableRowValueExtractor setHash(String hash) {
        this.hash = hash;
        return this;
    }

    public String getHash() {
        return hash;
    }

    public SingleResult<String> extractSingleHeaderValue(WebSimpleTable element) {
        Optional<ColumnMapper> columnMapper = element.getColumnMapper(columnName);
        return null;
    }

    public SingleResult<String> extractSingleFooterValue(WebSimpleTable element) {
        Optional<ColumnMapper> columnMapper = element.getColumnMapper(columnName);
        return null;
    }

    @Override
    public SingleResult<String> extractSingleValue(WebSimpleTable element) {
        Optional<ColumnMapper> columnMapper = element.getColumnMapper(columnName);
        return null;
    }

    @Override
    public MultipleResult<String> extractMultipleValues(WebSimpleTable element, Collection<Integer> indexes) {
        return null;
    }

}
