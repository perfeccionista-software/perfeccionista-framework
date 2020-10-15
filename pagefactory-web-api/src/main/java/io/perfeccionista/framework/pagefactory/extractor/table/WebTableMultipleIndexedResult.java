package io.perfeccionista.framework.pagefactory.extractor.table;

import io.perfeccionista.framework.invocation.runner.InvocationName;
import io.perfeccionista.framework.matcher.result.WebMultipleIndexedResultMatcher;
import io.perfeccionista.framework.pagefactory.elements.WebTable;
import io.perfeccionista.framework.pagefactory.filter.table.WebTableFilter;
import io.perfeccionista.framework.pagefactory.filter.table.WebTableFilterBuilder;
import io.perfeccionista.framework.result.WebMultipleIndexedResult;
import io.perfeccionista.framework.result.WebSingleIndexedResult;
import org.jetbrains.annotations.NotNull;

import java.util.Map;

import static io.perfeccionista.framework.invocation.wrappers.CheckInvocationWrapper.runCheck;
import static io.perfeccionista.framework.pagefactory.elements.actions.WebElementActionNames.GET_EXTRACTED_VALUES_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.actions.WebElementActionNames.GET_SIZE_ELEMENTS_METHOD;
import static io.perfeccionista.framework.pagefactory.filter.WebFilters.emptyWebTableFilter;

public class WebTableMultipleIndexedResult<T> implements WebMultipleIndexedResult<T, WebTable> {

    private final WebTable element;
    private final WebTableFilterBuilder filterBuilder;
    private final WebTableCellValueExtractor<T> extractor;

    private WebTableMultipleIndexedResult(WebTable element,
                                          WebTableFilterBuilder filterBuilder,
                                          WebTableCellValueExtractor<T> extractor) {
        this.element = element;
        this.filterBuilder = filterBuilder;
        this.extractor = extractor;
    }

    public static <T> WebTableMultipleIndexedResult<T> of(@NotNull WebTable element,
                                                          @NotNull WebTableFilterBuilder filterBuilder,
                                                          @NotNull WebTableCellValueExtractor<T> extractor) {
        return new WebTableMultipleIndexedResult<>(element, filterBuilder, extractor);
    }

    public static <T> WebTableMultipleIndexedResult<T> of(@NotNull WebTable element,
                                                          @NotNull WebTableCellValueExtractor<T> extractor) {
        return new WebTableMultipleIndexedResult<>(element, emptyWebTableFilter(), extractor);
    }

    @Override
    public @NotNull WebTable getElement() {
        return element;
    }

    @Override
    public Map<Integer, T> getValues() {
        return runCheck(element.getEnvironment(), InvocationName.of(GET_EXTRACTED_VALUES_METHOD, element, filterBuilder, extractor),
                () -> extractor.extractValues(filterBuilder.build(element)));
    }

    @Override
    public int getSize() {
        WebTableFilter webTableFilter = filterBuilder.build(element);
        return runCheck(element.getEnvironment(), InvocationName.of(GET_SIZE_ELEMENTS_METHOD, element, filterBuilder),
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
