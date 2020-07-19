package io.perfeccionista.framework.pagefactory.filter.list;

import io.perfeccionista.framework.pagefactory.extractor.list.WebListBlockValueExtractor;
import io.perfeccionista.framework.pagefactory.filter.FilterResult;
import io.perfeccionista.framework.pagefactory.filter.MultipleResult;
import io.perfeccionista.framework.pagefactory.filter.SingleResult;
import io.perfeccionista.framework.value.number.NumberValue;
import org.jetbrains.annotations.NotNull;

import java.util.Set;

public interface WebListFilterResult extends FilterResult {

    @NotNull String getHash();

    Set<Integer> getIndexes();

    @NotNull <T> SingleResult<T> extractOne(@NotNull WebListBlockValueExtractor<T> extractor);

    @NotNull <T> MultipleResult<T> extractAll(@NotNull WebListBlockValueExtractor<T> extractor);

    WebListFilterResult shouldHaveSize(@NotNull NumberValue<Integer> expectedSize);

}
