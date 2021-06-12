package io.perfeccionista.framework.pagefactory.extractor.table;

import io.perfeccionista.framework.matcher.result.WebMultipleIndexedResultMatcher;
import io.perfeccionista.framework.pagefactory.elements.WebTable;
import io.perfeccionista.framework.pagefactory.filter.table.WebTableFilter;
import io.perfeccionista.framework.pagefactory.filter.table.WebTableFilterBuilder;
import io.perfeccionista.framework.result.WebMultipleIndexedResult;
import io.perfeccionista.framework.result.WebSingleIndexedResult;
import org.jetbrains.annotations.NotNull;

import java.util.Map;

import static io.perfeccionista.framework.Web.emptyWebTableFilter;
import static io.perfeccionista.framework.invocation.runner.InvocationName.getterInvocation;
import static io.perfeccionista.framework.invocation.wrapper.CheckInvocationWrapper.runCheck;
import static io.perfeccionista.framework.pagefactory.elements.ElementActionNames.GET_EXTRACTED_VALUES_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.ElementActionNames.GET_SIZE_ELEMENTS_METHOD;

public class WebTableMultipleIndexedResult<T> implements WebMultipleIndexedResult<T, WebTable> {

    private final WebTable element;
    private final WebTableFilterBuilder filterBuilder;
    private final WebTableValueExtractor<T> extractor;

    private WebTableMultipleIndexedResult(WebTable element,
                                          WebTableFilterBuilder filterBuilder,
                                          WebTableValueExtractor<T> extractor) {
        this.element = element;
        this.filterBuilder = filterBuilder;
        this.extractor = extractor;
    }

    public static <T> WebTableMultipleIndexedResult<T> of(@NotNull WebTable element,
                                                          @NotNull WebTableFilterBuilder filterBuilder,
                                                          @NotNull WebTableValueExtractor<T> extractor) {
        return new WebTableMultipleIndexedResult<>(element, filterBuilder, extractor);
    }

    public static <T> WebTableMultipleIndexedResult<T> of(@NotNull WebTable element,
                                                          @NotNull WebTableValueExtractor<T> extractor) {
        return new WebTableMultipleIndexedResult<>(element, emptyWebTableFilter(), extractor);
    }

    @Override
    public @NotNull WebTable getElement() {
        return element;
    }

    @Override
    public Map<Integer, T> getResults() {
        return runCheck(getterInvocation(GET_EXTRACTED_VALUES_METHOD, element, filterBuilder, extractor),
                () -> extractor.extractValues(filterBuilder.build(element)));
    }

    @Override
    public int getSize() {
        WebTableFilter webTableFilter = filterBuilder.build(element);
        return runCheck(getterInvocation(GET_SIZE_ELEMENTS_METHOD, element, filterBuilder),
                () -> webTableFilter.getFilterResult().getIndexes().size());
    }

    @Override
    public WebMultipleIndexedResult<T, WebTable> should(WebMultipleIndexedResultMatcher<T> matcher) {
        matcher.check(this);
        return this;
    }

    @Override
    public @NotNull WebSingleIndexedResult<T, WebTable> singleResult() {
        return WebTableSingleIndexedResult.of(element, filterBuilder, extractor);
    }

}
