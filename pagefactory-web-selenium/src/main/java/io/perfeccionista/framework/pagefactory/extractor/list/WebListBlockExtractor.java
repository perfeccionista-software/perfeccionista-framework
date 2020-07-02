package io.perfeccionista.framework.pagefactory.extractor.list;

import io.perfeccionista.framework.pagefactory.elements.WebList;
import io.perfeccionista.framework.pagefactory.elements.WebMappedBlock;
import io.perfeccionista.framework.pagefactory.filter.MultipleResult;
import io.perfeccionista.framework.pagefactory.filter.list.WebListFilter;
import io.perfeccionista.framework.pagefactory.filter.list.WebListFilterResult;

public class WebListBlockExtractor<T extends WebMappedBlock> implements WebListBlockValueExtractor<T> {

    private final Class<T> blockClass;

    public WebListBlockExtractor(Class<T> blockClass) {
        this.blockClass = blockClass;
    }

    @Override
    public MultipleResult<T> extractValues(WebList element, WebListFilterResult filter) {
        // Вся логика извлечения и фильтрации здесь

        return null;
    }

}
