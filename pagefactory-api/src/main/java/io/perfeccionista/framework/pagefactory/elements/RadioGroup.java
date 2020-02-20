package io.perfeccionista.framework.pagefactory.elements;

import io.perfeccionista.framework.pagefactory.elements.base.ChildElement;
import io.perfeccionista.framework.pagefactory.elements.methods.availability.SizeAvailable;
import io.perfeccionista.framework.pagefactory.itemextractor.IndexedItems;
import io.perfeccionista.framework.pagefactory.itemextractor.ValueExtractor;
import io.perfeccionista.framework.pagefactory.itemfilter.MultipleItemFilter;
import io.perfeccionista.framework.pagefactory.operations.OperationResult;

public interface RadioGroup extends ChildElement,
        SizeAvailable {

    <V> OperationResult<IndexedItems<V>> getValues(ValueExtractor<RadioButton, V> extractor);

    <V> OperationResult<IndexedItems<V>> getValues(ValueExtractor<RadioButton, V> extractor, MultipleItemFilter<RadioButton> filter);

}
