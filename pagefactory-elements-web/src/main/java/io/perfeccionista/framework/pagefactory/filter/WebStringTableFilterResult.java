package io.perfeccionista.framework.pagefactory.filter;

import io.perfeccionista.framework.value.number.NumberValue;

import java.util.Map;
import java.util.Set;

public class WebStringTableFilterResult implements FilterResult<WebStringTableFilter> {

    private final WebStringTableFilter filter;

    public WebStringTableFilterResult(WebStringTableFilter filter) {
        this.filter = filter;
    }

    public SingleResult<String> extractOne(String columnName) {
        return null;
    }

    public Map<String, SingleResult<String>> extractOne(Set<String> columnNames) {
        return null;
    }

    public MultipleResult<String> extractAll(String columnName) {
        return null;
    }

    public Map<String, MultipleResult<String>> extractAll(Set<String> columnNames) {
        return null;
    }

    public WebStringTableFilterResult shouldHaveSize(NumberValue<Integer> integerValue) {
        return null;
    }

}
