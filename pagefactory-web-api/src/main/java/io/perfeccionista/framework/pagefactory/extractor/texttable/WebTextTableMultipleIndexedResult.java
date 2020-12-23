package io.perfeccionista.framework.pagefactory.extractor.texttable;

import io.perfeccionista.framework.matcher.result.WebMultipleIndexedResultMatcher;
import io.perfeccionista.framework.pagefactory.elements.WebTextTable;
import io.perfeccionista.framework.pagefactory.filter.texttable.WebTextTableFilter;
import io.perfeccionista.framework.pagefactory.filter.texttable.WebTextTableFilterBuilder;
import io.perfeccionista.framework.result.WebMultipleIndexedResult;
import io.perfeccionista.framework.result.WebSingleIndexedResult;
import org.jetbrains.annotations.NotNull;

import java.util.Map;

import static io.perfeccionista.framework.invocation.runner.InvocationName.getterInvocation;
import static io.perfeccionista.framework.invocation.wrappers.CheckInvocationWrapper.runCheck;
import static io.perfeccionista.framework.pagefactory.elements.actions.WebElementActionNames.GET_EXTRACTED_VALUES_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.actions.WebElementActionNames.GET_SIZE_ELEMENTS_METHOD;
import static io.perfeccionista.framework.pagefactory.filter.WebFilters.emptyWebTextTableFilter;

public class WebTextTableMultipleIndexedResult<T> implements WebMultipleIndexedResult<T, WebTextTable> {

    private final WebTextTable element;
    private final WebTextTableFilterBuilder filterBuilder;
    private final WebTextTableValueExtractor<T> extractor;

    private WebTextTableMultipleIndexedResult(WebTextTable element,
                                              WebTextTableFilterBuilder filterBuilder,
                                              WebTextTableValueExtractor<T> extractor) {
        this.element = element;
        this.filterBuilder = filterBuilder;
        this.extractor = extractor;
    }

    public static <T> WebTextTableMultipleIndexedResult<T> of(@NotNull WebTextTable element,
                                                              @NotNull WebTextTableFilterBuilder filterBuilder,
                                                              @NotNull WebTextTableValueExtractor<T> extractor) {
        return new WebTextTableMultipleIndexedResult<>(element, filterBuilder, extractor);
    }

    public static <T> WebTextTableMultipleIndexedResult<T> of(@NotNull WebTextTable element,
                                                              @NotNull WebTextTableValueExtractor<T> extractor) {
        return new WebTextTableMultipleIndexedResult<>(element, emptyWebTextTableFilter(), extractor);
    }

    @Override
    public @NotNull WebTextTable getElement() {
        return element;
    }

    @Override
    public Map<Integer, T> getValues() {
        return runCheck(element.getEnvironment(), getterInvocation(GET_EXTRACTED_VALUES_METHOD, element, filterBuilder, extractor),
                () -> extractor.extractValues(filterBuilder.build(element)));
    }

    @Override
    public int getSize() {
        WebTextTableFilter webTextTableFilter = filterBuilder.build(element);
        return runCheck(element.getEnvironment(), getterInvocation(GET_SIZE_ELEMENTS_METHOD, element, filterBuilder),
                () -> webTextTableFilter.getFilterResult().getIndexes().size());
    }

    @Override
    public WebMultipleIndexedResult<T, WebTextTable> should(WebMultipleIndexedResultMatcher<T> matcher) {
        matcher.check(this);
        return this;
    }

    @Override
    public @NotNull WebSingleIndexedResult<T, WebTextTable> singleResult() {
        return WebTextTableSingleIndexedResult.of(element, filterBuilder, extractor);
    }

}
