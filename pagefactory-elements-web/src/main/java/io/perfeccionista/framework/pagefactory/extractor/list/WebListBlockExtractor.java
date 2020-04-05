package io.perfeccionista.framework.pagefactory.extractor.list;

import io.perfeccionista.framework.pagefactory.elements.WebList;
import io.perfeccionista.framework.pagefactory.elements.WebMappedBlock;
import io.perfeccionista.framework.pagefactory.extractor.WebListBlockValueExtractor;
import io.perfeccionista.framework.pagefactory.filter.MultipleResult;
import io.perfeccionista.framework.pagefactory.filter.SingleResult;

public class WebListBlockExtractor<T extends WebMappedBlock> implements WebListBlockValueExtractor<T> {

    private final Class<T> blockClass;

    public WebListBlockExtractor(Class<T> blockClass) {
        this.blockClass = blockClass;
    }

    @Override
    public WebListBlockExtractor<T> setHash(String hash) {
        return null;
    }

    @Override
    public SingleResult<T> extractSingleValue(WebList element, SingleResult<Integer> filterResult) {
        return null;
    }

    @Override
    public MultipleResult<T> extractMultipleValues(WebList element, MultipleResult<Integer> filterResult) {
        return null;
    }

}
