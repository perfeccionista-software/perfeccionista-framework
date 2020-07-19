package io.perfeccionista.framework.pagefactory.filter.table;

import io.perfeccionista.framework.pagefactory.extractor.table.WebTableCellValueExtractor;
import io.perfeccionista.framework.pagefactory.filter.FilterResult;
import io.perfeccionista.framework.pagefactory.filter.MultipleResult;
import io.perfeccionista.framework.pagefactory.filter.SingleResult;
import io.perfeccionista.framework.value.number.NumberValue;
import org.apiguardian.api.API;
import org.apiguardian.api.API.Status;
import org.jetbrains.annotations.NotNull;

import java.util.Set;

import static org.apiguardian.api.API.Status.STABLE;

public interface WebTableFilterResult extends FilterResult {

    @API(status = STABLE)
    String getHash();

    @API(status = STABLE)
    Set<Integer> getIndexes();

    @NotNull <T> SingleResult<T> extractHeader(@NotNull WebTableCellValueExtractor<T> extractor);

    @NotNull <T> SingleResult<T> extractOneRow(@NotNull WebTableCellValueExtractor<T> extractor);

    // TODO: Implement: public Map<String, SingleResult<T>> extractOneRow(Map<String, WebTableCellValueExtractor<T>> columnExtractors)

    @NotNull <T> MultipleResult<T> extractAllRows(@NotNull WebTableCellValueExtractor<T> extractor);

    // TODO: Implement: public Map<String, MultipleResult<T>> extractAllRows(Map<String, WebTableCellValueExtractor<T>> columnExtractors)

    @NotNull <T> SingleResult<T> extractFooter(@NotNull WebTableCellValueExtractor<T> extractor);

    @API(status = Status.STABLE)
    WebTableFilterResult shouldHaveSize(@NotNull NumberValue<Integer> expectedSize);

}
