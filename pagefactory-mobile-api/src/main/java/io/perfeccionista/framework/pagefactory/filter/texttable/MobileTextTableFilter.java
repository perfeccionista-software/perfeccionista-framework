package io.perfeccionista.framework.pagefactory.filter.texttable;

import io.perfeccionista.framework.matcher.result.MobileMultipleIndexedResultMatcher;
import io.perfeccionista.framework.pagefactory.elements.MobileTextTable;
import io.perfeccionista.framework.pagefactory.extractor.texttable.MobileTextTableValueExtractor;
import io.perfeccionista.framework.pagefactory.filter.MobileFilter;
import io.perfeccionista.framework.result.MobileMultipleIndexedResult;
import io.perfeccionista.framework.result.MobileSingleIndexedResult;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface MobileTextTableFilter extends MobileFilter<MobileTextTable> {

    @NotNull MobileSingleIndexedResult<String, MobileTextTable> extractHeader(@NotNull String columnName);

    @NotNull MobileSingleIndexedResult<String, MobileTextTable> extractRow(@NotNull String columnName);

    @NotNull <T> MobileSingleIndexedResult<T, MobileTextTable> extractRow(@NotNull MobileTextTableValueExtractor<T> extractor);

    @NotNull MobileMultipleIndexedResult<String, MobileTextTable> extractRows(@NotNull String columnName);

    @NotNull <T> MobileMultipleIndexedResult<T, MobileTextTable> extractRows(@NotNull MobileTextTableValueExtractor<T> extractor);

    @NotNull MobileSingleIndexedResult<String, MobileTextTable> extractFooter(@NotNull String columnName);

    @Override
    MobileTextTableFilter should(@NotNull MobileMultipleIndexedResultMatcher<Integer> matcher);

    @Override
    MobileTextTableFilter setInitialHash(@Nullable String initialHash);

}

