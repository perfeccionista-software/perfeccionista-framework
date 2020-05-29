package io.perfeccionista.framework.pagefactory.filter.textlist;

import io.perfeccionista.framework.pagefactory.extractor.textlist.WebTextListBlockValueExtractor;
import io.perfeccionista.framework.pagefactory.filter.FilterResult;
import io.perfeccionista.framework.pagefactory.filter.MultipleResult;
import io.perfeccionista.framework.pagefactory.filter.SingleResult;
import io.perfeccionista.framework.value.number.NumberValue;
import org.apiguardian.api.API;

import static org.apiguardian.api.API.Status.INTERNAL;
import static org.apiguardian.api.API.Status.STABLE;

public interface WebTextListFilterResult extends FilterResult {

    @API(status = STABLE)
    String getHash();

    @API(status = STABLE)
    SingleResult<String> extractOne();

    @API(status = STABLE)
    MultipleResult<String> extractAll();

    @API(status = INTERNAL)
    <T> SingleResult<T> extractOne(WebTextListBlockValueExtractor<T> extractor);

    @API(status = INTERNAL)
    <T> MultipleResult<T> extractAll(WebTextListBlockValueExtractor<T> extractor);

    @Override
    @API(status = STABLE)
    WebTextListFilterResult shouldHaveSize(NumberValue<Integer> integerValue);

}
