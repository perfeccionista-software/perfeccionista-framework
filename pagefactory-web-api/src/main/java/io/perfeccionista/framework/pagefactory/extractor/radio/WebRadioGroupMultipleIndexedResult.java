package io.perfeccionista.framework.pagefactory.extractor.radio;

import io.perfeccionista.framework.matcher.result.WebMultipleIndexedResultMatcher;
import io.perfeccionista.framework.pagefactory.elements.WebRadioGroup;
import io.perfeccionista.framework.pagefactory.filter.radio.WebRadioGroupFilter;
import io.perfeccionista.framework.pagefactory.filter.radio.WebRadioGroupFilterBuilder;
import io.perfeccionista.framework.result.WebMultipleIndexedResult;
import io.perfeccionista.framework.result.WebSingleIndexedResult;
import org.jetbrains.annotations.NotNull;

import java.util.Map;

import static io.perfeccionista.framework.Web.emptyWebRadioButtonFilter;
import static io.perfeccionista.framework.invocation.runner.InvocationInfo.getterInvocation;
import static io.perfeccionista.framework.invocation.wrapper.CheckInvocationWrapper.runCheck;
import static io.perfeccionista.framework.pagefactory.elements.ElementActionNames.GET_EXTRACTED_VALUES_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.ElementActionNames.GET_SIZE_ELEMENTS_METHOD;

public class WebRadioGroupMultipleIndexedResult<T> implements WebMultipleIndexedResult<T, WebRadioGroup> {

    private final WebRadioGroup element;
    private final WebRadioGroupFilterBuilder filterBuilder;
    private final WebRadioButtonValueExtractor<T> extractor;

    private WebRadioGroupMultipleIndexedResult(WebRadioGroup element,
                                               WebRadioGroupFilterBuilder filterBuilder,
                                               WebRadioButtonValueExtractor<T> extractor) {
        this.element = element;
        this.filterBuilder = filterBuilder;
        this.extractor = extractor;
    }

    public static <T> WebRadioGroupMultipleIndexedResult<T> of(@NotNull WebRadioGroup element,
                                                               @NotNull WebRadioGroupFilterBuilder filterBuilder,
                                                               @NotNull WebRadioButtonValueExtractor<T> extractor) {
        return new WebRadioGroupMultipleIndexedResult<>(element, filterBuilder, extractor);
    }

    public static <T> WebRadioGroupMultipleIndexedResult<T> of(@NotNull WebRadioGroup element,
                                                               @NotNull WebRadioButtonValueExtractor<T> extractor) {
        return new WebRadioGroupMultipleIndexedResult<>(element, emptyWebRadioButtonFilter(), extractor);
    }

    @Override
    public @NotNull WebRadioGroup getElement() {
        return element;
    }

    @Override
    public Map<Integer, T> getResults() {
        return runCheck(getterInvocation(GET_EXTRACTED_VALUES_METHOD, element, filterBuilder, extractor),
                () -> extractor.extractValues(filterBuilder.build(element)));
    }

    @Override
    public int getSize() {
        WebRadioGroupFilter webRadioGroupFilter = filterBuilder.build(element);
        return runCheck(getterInvocation(GET_SIZE_ELEMENTS_METHOD, element, filterBuilder),
                () -> webRadioGroupFilter.getFilterResult().getIndexes().size());
    }

    @Override
    public WebMultipleIndexedResult<T, WebRadioGroup> should(WebMultipleIndexedResultMatcher<T> matcher) {
        matcher.check(this);
        return this;
    }

    @Override
    public @NotNull WebSingleIndexedResult<T, WebRadioGroup> singleResult() {
        return WebRadioGroupSingleIndexedResult.of(element, filterBuilder, extractor);
    }

}
