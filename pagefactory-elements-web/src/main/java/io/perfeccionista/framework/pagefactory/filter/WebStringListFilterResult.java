package io.perfeccionista.framework.pagefactory.filter;

import io.perfeccionista.framework.value.number.NumberValue;

public class WebStringListFilterResult implements FilterResult<WebStringListFilter> {

    private final WebStringListFilter filter;

    public WebStringListFilterResult(WebStringListFilter filter) {
        this.filter = filter;
    }

    public SingleResult<String> extractOne() {
        return null;
    }

    public MultipleResult<String> extractAll() {
        return null;
    }

    public WebStringListFilterResult shouldHaveSize(NumberValue<Integer> integerValue) {
        return null;
    }

}
