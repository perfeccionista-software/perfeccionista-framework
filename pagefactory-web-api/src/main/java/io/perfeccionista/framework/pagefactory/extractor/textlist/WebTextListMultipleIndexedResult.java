package io.perfeccionista.framework.pagefactory.extractor.textlist;

import io.perfeccionista.framework.matcher.result.WebMultipleIndexedResultMatcher;
import io.perfeccionista.framework.pagefactory.elements.WebTextList;
import io.perfeccionista.framework.pagefactory.filter.textlist.WebTextListFilter;
import io.perfeccionista.framework.pagefactory.filter.textlist.WebTextListFilterBuilder;
import io.perfeccionista.framework.result.WebMultipleIndexedResult;
import io.perfeccionista.framework.result.WebSingleIndexedResult;
import org.jetbrains.annotations.NotNull;

import java.util.Map;

import static io.perfeccionista.framework.invocation.runner.InvocationName.getterInvocation;
import static io.perfeccionista.framework.invocation.wrapper.CheckInvocationWrapper.runCheck;
import static io.perfeccionista.framework.pagefactory.elements.actions.WebElementActionNames.GET_EXTRACTED_VALUES_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.actions.WebElementActionNames.GET_SIZE_ELEMENTS_METHOD;
import static io.perfeccionista.framework.pagefactory.filter.WebFilters.emptyWebTextListFilter;

public class WebTextListMultipleIndexedResult<T> implements WebMultipleIndexedResult<T, WebTextList> {

    private final WebTextList element;
    private final WebTextListFilterBuilder filterBuilder;
    private final WebTextListBlockValueExtractor<T> extractor;

    private WebTextListMultipleIndexedResult(WebTextList element,
                                             WebTextListFilterBuilder filterBuilder,
                                             WebTextListBlockValueExtractor<T> extractor) {
        this.element = element;
        this.filterBuilder = filterBuilder;
        this.extractor = extractor;
    }

    public static <T> WebTextListMultipleIndexedResult<T> of(@NotNull WebTextList element,
                                                             @NotNull WebTextListFilterBuilder filterBuilder,
                                                             @NotNull WebTextListBlockValueExtractor<T> extractor) {
        return new WebTextListMultipleIndexedResult<>(element, filterBuilder, extractor);
    }

    public static <T> WebTextListMultipleIndexedResult<T> of(@NotNull WebTextList element,
                                                             @NotNull WebTextListBlockValueExtractor<T> extractor) {
        return new WebTextListMultipleIndexedResult<>(element, emptyWebTextListFilter(), extractor);
    }

    @Override
    public @NotNull WebTextList getElement() {
        return element;
    }

    @Override
    public Map<Integer, T> getValues() {
        return runCheck(element.getEnvironment(), getterInvocation(GET_EXTRACTED_VALUES_METHOD, element, filterBuilder, extractor),
                () -> extractor.extractValues(filterBuilder.build(element)));
    }

    @Override
    public int getSize() {
        WebTextListFilter webTextListFilter = filterBuilder.build(element);
        return runCheck(element.getEnvironment(), getterInvocation(GET_SIZE_ELEMENTS_METHOD, element, filterBuilder),
                () -> webTextListFilter.getFilterResult().getIndexes().size());
    }

    @Override
    public WebMultipleIndexedResult<T, WebTextList> should(WebMultipleIndexedResultMatcher<T> matcher) {
        matcher.check(this);
        return this;
    }

    @Override
    public @NotNull WebSingleIndexedResult<T, WebTextList> singleResult() {
        return WebTextListSingleIndexedResult.of(element, filterBuilder, extractor);
    }

}
