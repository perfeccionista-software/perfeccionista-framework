package io.perfeccionista.framework.pagefactory.filter.texttable;

import io.perfeccionista.framework.pagefactory.extractor.texttable.WebTextTableCellValueExtractor;
import io.perfeccionista.framework.pagefactory.filter.FilterResult;
import io.perfeccionista.framework.pagefactory.filter.MultipleResult;
import io.perfeccionista.framework.pagefactory.filter.SingleResult;
import io.perfeccionista.framework.value.number.NumberValue;

public interface WebTextTableFilterResult extends FilterResult {

    SingleResult<String> extractHeader(String columnName);

    SingleResult<String> extractOneRow(String columnName);

    <T> SingleResult<T> extractOneRow(WebTextTableCellValueExtractor<T> extractor);

    // TODO: Implement: public Map<String, SingleResult<String>> extractOne(Set<String> columnNames)

    MultipleResult<String> extractAllRows(String columnName);

    <T> MultipleResult<T> extractAllRows(WebTextTableCellValueExtractor<T> extractor);

    // TODO: Implement: public Map<String, MultipleResult<String>> extractAll(Set<String> columnNames)

    SingleResult<String> extractFooter(String columnName);

    WebTextTableFilterResult shouldHaveSize(NumberValue<Integer> integerValue);

}
