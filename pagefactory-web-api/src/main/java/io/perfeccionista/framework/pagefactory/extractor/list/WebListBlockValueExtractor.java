package io.perfeccionista.framework.pagefactory.extractor.list;

import io.perfeccionista.framework.pagefactory.elements.WebBlock;
import io.perfeccionista.framework.pagefactory.elements.WebList;
import io.perfeccionista.framework.pagefactory.extractor.WebValueExtractor;
import io.perfeccionista.framework.pagefactory.filter.list.WebListFilter;

public interface WebListBlockValueExtractor<R, T extends WebBlock> extends WebValueExtractor<WebList<T>, WebListFilter<T>, R> {
}
