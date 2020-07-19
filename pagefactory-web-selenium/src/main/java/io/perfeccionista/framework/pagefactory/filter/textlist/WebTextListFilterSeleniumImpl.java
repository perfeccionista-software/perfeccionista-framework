package io.perfeccionista.framework.pagefactory.filter.textlist;

import io.perfeccionista.framework.pagefactory.elements.WebTextList;
import io.perfeccionista.framework.pagefactory.extractor.textlist.WebTextListBlockIndexExtractor;
import io.perfeccionista.framework.pagefactory.extractor.textlist.WebTextListBlockStringExtractor;
import io.perfeccionista.framework.pagefactory.extractor.textlist.WebTextListBlockValueExtractor;
import io.perfeccionista.framework.pagefactory.filter.MultipleResult;
import io.perfeccionista.framework.pagefactory.filter.SingleResult;
import io.perfeccionista.framework.pagefactory.filter.WebFilterResult;
import io.perfeccionista.framework.value.number.NumberValue;
import org.apiguardian.api.API;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import static org.apiguardian.api.API.Status.INTERNAL;
import static org.apiguardian.api.API.Status.STABLE;

public class WebTextListFilterSeleniumImpl implements WebTextListFilter {

    private final WebTextList element;
    private final WebTextListFilterBuilder filter;
    private String textListHash = null;

    public WebTextListFilterSeleniumImpl(WebTextList element, WebTextListFilterBuilder filter) {
        this.element = element;
        this.filter = filter;
    }

    @Override
    public WebTextListFilter setInitialHash(@Nullable String initialHash) {
        return null;
    }

    @Override
    public @NotNull WebFilterResult getResult() {
        return null;
    }

    @API(status = STABLE)
    public SingleResult<String> extractOne() {
        return new WebTextListBlockStringExtractor()
                .extractValues(element, filter.build(element))
                .singleResult();
    }

    @API(status = STABLE)
    public MultipleResult<String> extractAll() {
        return new WebTextListBlockStringExtractor()
                .extractValues(element, filter.build(element));
    }

    @API(status = INTERNAL)
    public <T> SingleResult<T> extractOne(WebTextListBlockValueExtractor<T> extractor) {
        return extractor
                .extractValues(element, filter.build(element))
                .singleResult();
    }

    @API(status = INTERNAL)
    public <T> MultipleResult<T> extractAll(WebTextListBlockValueExtractor<T> extractor) {
        return extractor
                .extractValues(element, filter.build(element));
    }

    @Override
    @API(status = STABLE)
    public WebTextListFilter shouldHaveSize(NumberValue<Integer> expectedSize) {
        new WebTextListBlockIndexExtractor()
                .extractValues(element, filter.build(element))
                .shouldHaveSize(expectedSize);
        return this;
    }

}
