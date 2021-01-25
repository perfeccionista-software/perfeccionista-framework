package io.perfeccionista.framework.pagefactory.extractor.list;

import io.perfeccionista.framework.matcher.result.WebMultipleIndexedResultMatcher;
import io.perfeccionista.framework.pagefactory.elements.WebList;
import io.perfeccionista.framework.pagefactory.filter.list.WebListFilter;
import io.perfeccionista.framework.pagefactory.filter.list.WebListFilterBuilder;
import io.perfeccionista.framework.result.WebMultipleIndexedResult;
import io.perfeccionista.framework.result.WebSingleIndexedResult;
import org.jetbrains.annotations.NotNull;

import java.util.Map;

import static io.perfeccionista.framework.Web.emptyWebListFilter;
import static io.perfeccionista.framework.invocation.runner.InvocationName.getterInvocation;
import static io.perfeccionista.framework.invocation.wrapper.CheckInvocationWrapper.runCheck;
import static io.perfeccionista.framework.pagefactory.elements.ElementActionNames.GET_EXTRACTED_VALUES_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.ElementActionNames.GET_SIZE_ELEMENTS_METHOD;

public class WebListMultipleIndexedResult<T> implements WebMultipleIndexedResult<T, WebList> {

    private final WebList element;
    private final WebListFilterBuilder filterBuilder;
    private final WebListBlockValueExtractor<T> extractor;

    private WebListMultipleIndexedResult(WebList element,
                                         WebListFilterBuilder filterBuilder,
                                         WebListBlockValueExtractor<T> extractor) {
        this.element = element;
        this.filterBuilder = filterBuilder;
        this.extractor = extractor;
    }

    public static <T> WebListMultipleIndexedResult<T> of(@NotNull WebList element,
                                                         @NotNull WebListFilterBuilder filterBuilder,
                                                         @NotNull WebListBlockValueExtractor<T> extractor) {
        return new WebListMultipleIndexedResult<>(element, filterBuilder, extractor);
    }

    public static <T> WebListMultipleIndexedResult<T> of(@NotNull WebList element,
                                                         @NotNull WebListBlockValueExtractor<T> extractor) {
        return new WebListMultipleIndexedResult<>(element, emptyWebListFilter(), extractor);
    }

    @Override
    public @NotNull WebList getElement() {
        return element;
    }

    @Override
    public Map<Integer, T> getResults() {
        return runCheck(element.getEnvironment(), getterInvocation(GET_EXTRACTED_VALUES_METHOD, element, filterBuilder, extractor),
                () -> extractor.extractValues(filterBuilder.build(element)));
    }

    @Override
    public int getSize() {
        WebListFilter webListFilter = filterBuilder.build(element);
        return runCheck(element.getEnvironment(), getterInvocation(GET_SIZE_ELEMENTS_METHOD, element, filterBuilder),
                () -> webListFilter.getFilterResult().getIndexes().size());
    }

    @Override
    public WebMultipleIndexedResult<T, WebList> should(WebMultipleIndexedResultMatcher<T> matcher) {
        matcher.check(this);
        return this;
    }

    @Override
    public @NotNull WebSingleIndexedResult<T, WebList> singleResult() {
        return WebListSingleIndexedResult.of(element, filterBuilder, extractor);
    }

}
