package io.perfeccionista.framework.pagefactory.filter.table;

import io.perfeccionista.framework.pagefactory.extractor.table.WebTableCellValueExtractor;
import io.perfeccionista.framework.pagefactory.filter.FilterResult;
import io.perfeccionista.framework.pagefactory.filter.MultipleResult;
import io.perfeccionista.framework.pagefactory.filter.SingleResult;
import io.perfeccionista.framework.value.number.NumberValue;
import org.apiguardian.api.API;

import static org.apiguardian.api.API.Status.STABLE;

public interface WebTableFilterResult extends FilterResult {

    @API(status = STABLE)
    String getHash();

    <T> SingleResult<T> extractHeader(WebTableCellValueExtractor<T> extractor);

    <T> SingleResult<T> extractOneRow(WebTableCellValueExtractor<T> extractor);

    // TODO: Implement: public Map<String, SingleResult<T>> extractOneRow(Map<String, WebTableCellValueExtractor<T>> columnExtractors)

    <T> MultipleResult<T> extractAllRows(WebTableCellValueExtractor<T> extractor);

    // TODO: Implement: public Map<String, MultipleResult<T>> extractAllRows(Map<String, WebTableCellValueExtractor<T>> columnExtractors)

    <T> SingleResult<T> extractFooter(WebTableCellValueExtractor<T> extractor);

    WebTableFilterResult shouldHaveSize(NumberValue<Integer> integerValue);

}
