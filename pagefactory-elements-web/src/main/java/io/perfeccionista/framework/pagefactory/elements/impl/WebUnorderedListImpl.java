package io.perfeccionista.framework.pagefactory.elements.impl;

import io.perfeccionista.framework.pagefactory.elements.locators.WebLocator;
import io.perfeccionista.framework.pagefactory.elements.methods.ElementMethod;
import io.perfeccionista.framework.pagefactory.elements.AbstractWebChildElement;
import io.perfeccionista.framework.pagefactory.elements.WebBlock;
import io.perfeccionista.framework.pagefactory.elements.WebUnorderedList;
import io.perfeccionista.framework.pagefactory.elements.methods.JsScrollToBlockElement;
import io.perfeccionista.framework.pagefactory.elements.methods.JsSize;
import io.perfeccionista.framework.pagefactory.extractor.JsBlockValueExtractor;
import io.perfeccionista.framework.pagefactory.filter.MultipleResult;
import io.perfeccionista.framework.pagefactory.filter.JsBlockFilter;
import io.perfeccionista.framework.pagefactory.operations.OperationResult;

import static io.perfeccionista.framework.pagefactory.elements.locators.Components.LI;
import static io.perfeccionista.framework.pagefactory.elements.methods.availability.AvailableMethod.SCROLL_TO_ELEMENT_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.availability.AvailableMethod.SIZE_METHOD;

@WebLocator(component = LI, xpath = ".//li", single = false)
@ElementMethod(type = SCROLL_TO_ELEMENT_METHOD, implementation = JsScrollToBlockElement.class)
@ElementMethod(type = SIZE_METHOD, implementation = JsSize.class)
public class WebUnorderedListImpl extends AbstractWebChildElement implements WebUnorderedList {

    protected Class<? extends WebBlock> blockMapper;

    @Override
    public <V> OperationResult<MultipleResult<V>> getValues(JsBlockValueExtractor<V> extractor) {
        return OperationResult.of(() -> extractor.extractMultipleValues(this, MultipleResult.empty()));
    }

    @Override
    public <V> OperationResult<MultipleResult<V>> getValues(JsBlockValueExtractor<V> extractor, JsBlockFilter filter) {
        return OperationResult.of(() -> {
            MultipleResult<Integer> result = filter.multipleResult(this);
            return extractor.setHash(result.getElementHash()).extractMultipleValues(this, result);
        });
    }

    @Override
    public OperationResult<Void> scrollToElement(JsBlockFilter filter) {
        return getMethodImplementation(SCROLL_TO_ELEMENT_METHOD, Void.class).execute(this, filter);
    }

    @Override
    public OperationResult<Integer> size() {
        return getMethodImplementation(SIZE_METHOD, Integer.class).execute(this, LI);
    }

    protected Class<? extends WebBlock> getBlockMapper() {
        return blockMapper;
    }

}
