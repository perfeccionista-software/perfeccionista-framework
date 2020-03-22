package io.perfeccionista.framework.pagefactory.elements.impl;

import io.perfeccionista.framework.pagefactory.elements.locators.WebLocator;
import io.perfeccionista.framework.pagefactory.elements.methods.ElementMethod;
import io.perfeccionista.framework.pagefactory.elements.AbstractWebChildElement;
import io.perfeccionista.framework.pagefactory.elements.WebRadioGroup;
import io.perfeccionista.framework.pagefactory.elements.methods.JsScrollToBlockElement;
import io.perfeccionista.framework.pagefactory.elements.methods.JsSize;
import io.perfeccionista.framework.pagefactory.extractor.WebRadioButtonValueExtractor;
import io.perfeccionista.framework.pagefactory.filter.MultipleResult;
import io.perfeccionista.framework.pagefactory.filter.WebRadioButtonFilter;
import io.perfeccionista.framework.pagefactory.operations.OperationResult;

import static io.perfeccionista.framework.pagefactory.elements.locators.Components.RADIO;
import static io.perfeccionista.framework.pagefactory.elements.methods.availability.AvailableMethod.SCROLL_TO_ELEMENT_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.availability.AvailableMethod.SIZE_METHOD;

// Сделать реализацию через массив блоков в которых один радио-баттон и индекс хранить в нем

public abstract class WebRadioGroupImpl extends AbstractWebChildElement implements WebRadioGroup {


    @Override
    public <V> MultipleResult<V> getValues(WebRadioButtonValueExtractor<V> extractor) {
        return extractor.extractMultipleValues(this, MultipleResult.empty());
    }

    @Override
    public <V> MultipleResult<V> getValues(WebRadioButtonValueExtractor<V> extractor, WebRadioButtonFilter filter) {
        MultipleResult<Integer> result = filter.multipleResult(this);
        return extractor.setHash(result.getElementHash()).extractMultipleValues(this, result);
    }

    @Override
    public void scrollToElement(WebRadioButtonFilter filter) {
        getMethodImplementation(SCROLL_TO_ELEMENT_METHOD, Void.class).execute(this, filter);
    }

    @Override
    public int size() {
        return getMethodImplementation(SIZE_METHOD, Integer.class).execute(this, RADIO);
    }

}
