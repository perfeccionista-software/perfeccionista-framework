package io.perfeccionista.framework.pagefactory.filter.textlist;

import io.perfeccionista.framework.pagefactory.elements.WebTextList;
import io.perfeccionista.framework.pagefactory.extractor.textlist.WebTextListBlockIndexExtractor;
import io.perfeccionista.framework.pagefactory.extractor.textlist.WebTextListBlockStringExtractor;
import io.perfeccionista.framework.pagefactory.extractor.textlist.WebTextListBlockValueExtractor;
import io.perfeccionista.framework.pagefactory.filter.MultipleResult;
import io.perfeccionista.framework.pagefactory.filter.SingleResult;
import io.perfeccionista.framework.value.number.NumberValue;
import org.apiguardian.api.API;

import static org.apiguardian.api.API.Status.INTERNAL;
import static org.apiguardian.api.API.Status.STABLE;

public class WebTextListFilterResultSeleniumImpl implements WebTextListFilterResult {

    private final WebTextList element;
    private final WebTextListFilter filter;
    private String textListHash = null;

    public WebTextListFilterResultSeleniumImpl(WebTextList element, WebTextListFilter filter) {
        this.element = element;
        this.filter = filter;
    }

    @Override
    public String getHash() {
        return this.textListHash;
    }

    @API(status = STABLE)
    public SingleResult<String> extractOne() {
        return new WebTextListBlockStringExtractor()
                .extractValues(element, filter.filter(element))
                .singleResult();
    }

    @API(status = STABLE)
    public MultipleResult<String> extractAll() {
        return new WebTextListBlockStringExtractor()
                .extractValues(element, filter.filter(element));
    }

    @API(status = INTERNAL)
    public <T> SingleResult<T> extractOne(WebTextListBlockValueExtractor<T> extractor) {
        return extractor
                .extractValues(element, filter.filter(element))
                .singleResult();
    }

    @API(status = INTERNAL)
    public <T> MultipleResult<T> extractAll(WebTextListBlockValueExtractor<T> extractor) {
        return extractor
                .extractValues(element, filter.filter(element));
    }

    @Override
    @API(status = STABLE)
    public WebTextListFilterResult shouldHaveSize(NumberValue<Integer> integerValue) {
        new WebTextListBlockIndexExtractor()
                .extractValues(element, filter.filter(element))
                .shouldHaveSize(integerValue);
        return this;
    }

}
