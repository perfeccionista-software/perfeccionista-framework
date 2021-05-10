package io.perfeccionista.framework.pagefactory.filter.textlist;

import io.perfeccionista.framework.matcher.result.MobileMultipleIndexedResultMatcher;
import io.perfeccionista.framework.pagefactory.elements.MobileTextList;
import io.perfeccionista.framework.pagefactory.extractor.textlist.MobileTextListBlockValueExtractor;
import io.perfeccionista.framework.pagefactory.filter.MobileFilter;
import io.perfeccionista.framework.result.MobileMultipleIndexedResult;
import io.perfeccionista.framework.result.MobileSingleIndexedResult;
import org.apiguardian.api.API;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import static org.apiguardian.api.API.Status.INTERNAL;
import static org.apiguardian.api.API.Status.STABLE;

public interface MobileTextListFilter extends MobileFilter<MobileTextList> {

    @API(status = STABLE)
    @NotNull MobileSingleIndexedResult<String, MobileTextList> extractOne();

    @API(status = STABLE)
    @NotNull MobileMultipleIndexedResult<String, MobileTextList> extractAll();

    @API(status = INTERNAL)
    @NotNull <T> MobileSingleIndexedResult<T, MobileTextList> extractOne(@NotNull MobileTextListBlockValueExtractor<T> extractor);

    @API(status = INTERNAL)
    @NotNull <T> MobileMultipleIndexedResult<T, MobileTextList> extractAll(@NotNull MobileTextListBlockValueExtractor<T> extractor);

    @Override
    MobileTextListFilter should(@NotNull MobileMultipleIndexedResultMatcher<Integer> matcher);

    @Override
    MobileTextListFilter setInitialHash(@Nullable String initialHash);

}
