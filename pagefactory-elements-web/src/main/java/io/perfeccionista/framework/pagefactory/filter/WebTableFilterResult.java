package io.perfeccionista.framework.pagefactory.filter;

import io.perfeccionista.framework.pagefactory.extractor.WebTableCellValueExtractor;
import io.perfeccionista.framework.value.number.NumberValue;

public class WebTableFilterResult implements FilterResult<WebTableFilter>{

    private final WebTableFilter filter;

    public WebTableFilterResult(WebTableFilter filter) {
        this.filter = filter;
    }

    public <T> SingleResult<T> extractOne(WebTableCellValueExtractor<T> extractor) {
        return null;
    }

    public <T> MultipleResult<T> extractAll(WebTableCellValueExtractor<T> extractor) {
        return null;
    }

    public WebTableFilterResult shouldHaveSize(NumberValue<Integer> integerValue) {
        return null;
    }

}
