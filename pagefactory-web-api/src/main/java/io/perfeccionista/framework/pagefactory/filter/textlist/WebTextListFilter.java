package io.perfeccionista.framework.pagefactory.filter.textlist;

import io.perfeccionista.framework.pagefactory.extractor.textlist.WebTextListBlockValueExtractor;
import io.perfeccionista.framework.pagefactory.filter.MultipleResult;
import io.perfeccionista.framework.pagefactory.filter.SingleResult;
import io.perfeccionista.framework.pagefactory.filter.WebFilter;
import io.perfeccionista.framework.value.number.NumberValue;
import org.apiguardian.api.API;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import static org.apiguardian.api.API.Status.INTERNAL;
import static org.apiguardian.api.API.Status.STABLE;

public interface WebTextListFilter extends WebFilter {

    @Override
    WebTextListFilter setInitialHash(@Nullable String initialHash);

    @Override
    WebTextListFilter shouldHaveSize(@NotNull NumberValue<Integer> expectedSize);

    @API(status = STABLE)
    @NotNull SingleResult<String> extractOne();

    @API(status = STABLE)
    @NotNull MultipleResult<String> extractAll();

    @API(status = INTERNAL)
    @NotNull <T> SingleResult<T> extractOne(@NotNull WebTextListBlockValueExtractor<T> extractor);

    @API(status = INTERNAL)
    @NotNull <T> MultipleResult<T> extractAll(@NotNull WebTextListBlockValueExtractor<T> extractor);

}
