package io.perfeccionista.framework.pagefactory.elements.web;

import io.perfeccionista.framework.pagefactory.elements.Block;
import io.perfeccionista.framework.pagefactory.elements.UnorderedList;
import io.perfeccionista.framework.pagefactory.itemextractor.BlockValueExtractor;
import io.perfeccionista.framework.pagefactory.itemextractor.IndexedItems;
import io.perfeccionista.framework.pagefactory.itemfilter.MultipleBlockFilter;
import io.perfeccionista.framework.pagefactory.itemfilter.SingleItemFilter;
import io.perfeccionista.framework.pagefactory.operations.OperationResult;

public class WebUnorderedList extends AbstractWebChildElement implements UnorderedList {

    @Override
    public <V> OperationResult<IndexedItems<V>> getValues(BlockValueExtractor<V> extractor) {
        return null;
    }

    @Override
    public <V> OperationResult<IndexedItems<V>> getValues(BlockValueExtractor<V> extractor, MultipleBlockFilter filter) {
        return null;
    }

    @Override
    public <T extends Block> OperationResult<Void> scrollToElement(SingleItemFilter<T> filter) {
        return null;
    }

    @Override
    public OperationResult<Integer> size() {
        return null;
    }

}
