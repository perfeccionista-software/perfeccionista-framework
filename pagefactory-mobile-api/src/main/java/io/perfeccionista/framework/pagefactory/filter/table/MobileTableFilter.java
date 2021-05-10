package io.perfeccionista.framework.pagefactory.filter.table;

import io.perfeccionista.framework.matcher.result.MobileMultipleIndexedResultMatcher;
import io.perfeccionista.framework.pagefactory.elements.MobileTable;
import io.perfeccionista.framework.pagefactory.extractor.table.MobileTableValueExtractor;
import io.perfeccionista.framework.pagefactory.filter.MobileFilter;
import io.perfeccionista.framework.result.MobileMultipleIndexedResult;
import io.perfeccionista.framework.result.MobileSingleIndexedResult;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface MobileTableFilter extends MobileFilter<MobileTable> {

    @NotNull <T> MobileSingleIndexedResult<T, MobileTable> extractHeader(@NotNull MobileTableValueExtractor<T> extractor);

    @NotNull <T> MobileSingleIndexedResult<T, MobileTable> extractRow(@NotNull MobileTableValueExtractor<T> extractor);

    @NotNull <T> MobileMultipleIndexedResult<T, MobileTable> extractRows(@NotNull MobileTableValueExtractor<T> extractor);

    @NotNull <T> MobileSingleIndexedResult<T, MobileTable> extractFooter(@NotNull MobileTableValueExtractor<T> extractor);

    @Override
    MobileTableFilter should(@NotNull MobileMultipleIndexedResultMatcher<Integer> matcher);

    @Override
    MobileTableFilter setInitialHash(@Nullable String initialHash);

}
