package io.perfeccionista.framework.pagefactory.elements;

import io.perfeccionista.framework.pagefactory.elements.base.ChildElement;
import io.perfeccionista.framework.pagefactory.elements.methods.availability.ScrollToElementAvailable;
import io.perfeccionista.framework.pagefactory.elements.methods.availability.SizeAvailable;
import io.perfeccionista.framework.pagefactory.itemextractor.BlockValueExtractor;
import io.perfeccionista.framework.pagefactory.itemextractor.IndexedItems;
import io.perfeccionista.framework.pagefactory.itemfilter.MultipleBlockFilter;
import io.perfeccionista.framework.pagefactory.operations.OperationResult;

public interface UnorderedList extends ChildElement,
        SizeAvailable, ScrollToElementAvailable {

    <V> OperationResult<IndexedItems<V>> getValues(BlockValueExtractor<V> extractor);

    <V> OperationResult<IndexedItems<V>> getValues(BlockValueExtractor<V> extractor, MultipleBlockFilter filter);

}