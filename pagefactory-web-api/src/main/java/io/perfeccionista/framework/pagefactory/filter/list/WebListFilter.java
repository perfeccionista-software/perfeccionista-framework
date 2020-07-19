package io.perfeccionista.framework.pagefactory.filter.list;

import io.perfeccionista.framework.pagefactory.extractor.list.WebListBlockValueExtractor;
import io.perfeccionista.framework.pagefactory.filter.MultipleResult;
import io.perfeccionista.framework.pagefactory.filter.SingleResult;
import io.perfeccionista.framework.pagefactory.filter.WebFilter;
import io.perfeccionista.framework.value.number.NumberValue;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface WebListFilter extends WebFilter {

    @Override
    WebListFilter setInitialHash(@Nullable String initialHash);

    @Override
    WebListFilter shouldHaveSize(@NotNull NumberValue<Integer> expectedSize);

    @NotNull <T> SingleResult<T> extractOne(@NotNull WebListBlockValueExtractor<T> extractor);

    @NotNull <T> MultipleResult<T> extractAll(@NotNull WebListBlockValueExtractor<T> extractor);

}
