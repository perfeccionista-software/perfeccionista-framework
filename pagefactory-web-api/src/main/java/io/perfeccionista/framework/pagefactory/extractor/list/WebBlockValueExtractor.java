package io.perfeccionista.framework.pagefactory.extractor.list;

import io.perfeccionista.framework.pagefactory.elements.WebBlock;
import io.perfeccionista.framework.pagefactory.elements.WebList;
import io.perfeccionista.framework.pagefactory.extractor.WebValueExtractor;
import io.perfeccionista.framework.pagefactory.filter.block.WebBlockFilter;

public interface WebBlockValueExtractor<R, T extends WebBlock> extends WebValueExtractor<WebList<T>, WebBlockFilter<T>, R> {
}
