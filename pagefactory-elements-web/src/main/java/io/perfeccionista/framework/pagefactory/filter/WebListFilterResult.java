package io.perfeccionista.framework.pagefactory.filter;

import io.perfeccionista.framework.pagefactory.extractor.WebListBlockValueExtractor;
import io.perfeccionista.framework.value.number.NumberValue;

public class WebListFilterResult implements FilterResult<WebListFilter> {

    private final WebListFilter filter;

    public WebListFilterResult(WebListFilter filter) {
        this.filter = filter;
    }

    public <T> SingleResult<T> extractOne(WebListBlockValueExtractor<T> extractor) {
        return null;
    }

    public <T> MultipleResult<T> extractAll(WebListBlockValueExtractor<T> extractor) {
        return null;
    }

    public WebListFilterResult shouldHaveSize(NumberValue<Integer> integerValue) {
        return null;
    }

}
