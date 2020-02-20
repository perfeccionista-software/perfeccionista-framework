package io.perfeccionista.framework.pagefactory.elements;

import io.perfeccionista.framework.pagefactory.elements.base.ChildElement;
import io.perfeccionista.framework.pagefactory.elements.methods.availability.ScrollToElementAvailable;
import io.perfeccionista.framework.pagefactory.elements.methods.availability.SizeAvailable;
import io.perfeccionista.framework.pagefactory.itemextractor.IndexedItems;
import io.perfeccionista.framework.pagefactory.itemfilter.MultipleBlockSimpleFilter;
import io.perfeccionista.framework.pagefactory.operations.OperationResult;

public interface SimpleUnorderedList extends ChildElement,
        SizeAvailable, ScrollToElementAvailable {

    OperationResult<IndexedItems<String>> getValues();

    OperationResult<IndexedItems<String>> getValues(MultipleBlockSimpleFilter filter);

}
