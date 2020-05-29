package io.perfeccionista.framework.pagefactory.filter.list;

import io.perfeccionista.framework.pagefactory.extractor.list.WebListBlockValueExtractor;
import io.perfeccionista.framework.pagefactory.filter.FilterResult;
import io.perfeccionista.framework.pagefactory.filter.MultipleResult;
import io.perfeccionista.framework.pagefactory.filter.SingleResult;
import io.perfeccionista.framework.value.number.NumberValue;

public interface WebListFilterResult extends FilterResult {

    String getHash();

    <T> SingleResult<T> extractOne(WebListBlockValueExtractor<T> extractor);

    <T> MultipleResult<T> extractAll(WebListBlockValueExtractor<T> extractor);

    WebListFilterResult shouldHaveSize(NumberValue<Integer> integerValue);

}
