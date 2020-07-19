package io.perfeccionista.framework.pagefactory.filter.textlist;

import io.perfeccionista.framework.pagefactory.extractor.textlist.WebTextListBlockValueExtractor;
import io.perfeccionista.framework.pagefactory.filter.FilterResult;
import io.perfeccionista.framework.pagefactory.filter.MultipleResult;
import io.perfeccionista.framework.pagefactory.filter.SingleResult;
import io.perfeccionista.framework.value.number.NumberValue;
import org.apiguardian.api.API;
import org.jetbrains.annotations.NotNull;

import static org.apiguardian.api.API.Status.INTERNAL;
import static org.apiguardian.api.API.Status.STABLE;

public interface WebTextListFilterResult extends FilterResult {

    @API(status = STABLE)
    @NotNull String getHash();

    @API(status = STABLE)
    @NotNull SingleResult<String> extractOne();

    @API(status = STABLE)
    @NotNull MultipleResult<String> extractAll();

    @API(status = INTERNAL)
    @NotNull <T> SingleResult<T> extractOne(@NotNull WebTextListBlockValueExtractor<T> extractor);

    @API(status = INTERNAL)
    @NotNull <T> MultipleResult<T> extractAll(@NotNull WebTextListBlockValueExtractor<T> extractor);

    @Override
    @API(status = STABLE)
    WebTextListFilterResult shouldHaveSize(@NotNull NumberValue<Integer> expectedSize);

}
