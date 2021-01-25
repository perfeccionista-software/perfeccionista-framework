package io.perfeccionista.framework.pagefactory.filter.list;

import io.perfeccionista.framework.matcher.result.MobileMultipleIndexedResultMatcher;
import io.perfeccionista.framework.pagefactory.elements.MobileList;
import io.perfeccionista.framework.pagefactory.extractor.list.MobileListBlockValueExtractor;
import io.perfeccionista.framework.pagefactory.filter.MobileFilter;
import io.perfeccionista.framework.result.MobileMultipleIndexedResult;
import io.perfeccionista.framework.result.MobileSingleIndexedResult;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface MobileListFilter extends MobileFilter<MobileList> {

    @NotNull <T> MobileMultipleIndexedResult<T, MobileList> extractAll(@NotNull MobileListBlockValueExtractor<T> extractor);

    @NotNull <T> MobileSingleIndexedResult<T, MobileList> extractOne(@NotNull MobileListBlockValueExtractor<T> extractor);

    @Override
    MobileListFilter should(@NotNull MobileMultipleIndexedResultMatcher<Integer> matcher);

    @Override
    MobileListFilter setInitialHash(@Nullable String initialHash);

}

