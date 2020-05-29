package io.perfeccionista.framework.pagefactory.filter.list;

import io.perfeccionista.framework.pagefactory.elements.WebList;
import io.perfeccionista.framework.pagefactory.extractor.list.WebListBlockIndexExtractor;
import io.perfeccionista.framework.pagefactory.extractor.list.WebListBlockValueExtractor;
import io.perfeccionista.framework.pagefactory.filter.MultipleResult;
import io.perfeccionista.framework.pagefactory.filter.SingleResult;
import io.perfeccionista.framework.value.number.NumberValue;

public class WebListFilterResultSeleniumImpl implements WebListFilterResult {

    private final WebList element;
    private final WebListFilter filter;
    private String listHash = null;

    public WebListFilterResultSeleniumImpl(WebList element, WebListFilter filter) {
        this.element = element;
        this.filter = filter;
    }

    @Override
    public String getHash() {
        return listHash;
    }

    public <T> SingleResult<T> extractOne(WebListBlockValueExtractor<T> extractor) {
        return extractor
                .extractValues(element, filter)
                .singleResult();
    }

    public <T> MultipleResult<T> extractAll(WebListBlockValueExtractor<T> extractor) {
        return extractor
                .extractValues(element, filter);
    }

    public WebListFilterResult shouldHaveSize(NumberValue<Integer> integerValue) {
        new WebListBlockIndexExtractor()
                .extractValues(element, filter)
                .shouldHaveSize(integerValue);
        return this;
    }

}
