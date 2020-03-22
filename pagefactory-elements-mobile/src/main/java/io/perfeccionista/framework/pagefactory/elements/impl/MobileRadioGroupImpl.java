package io.perfeccionista.framework.pagefactory.elements.impl;

import io.appium.java_client.MobileElement;
import io.perfeccionista.framework.pagefactory.elements.locators.AndroidLocator;
import io.perfeccionista.framework.pagefactory.elements.locators.IosLocator;
import io.perfeccionista.framework.pagefactory.elements.methods.ElementMethod;
import io.perfeccionista.framework.pagefactory.elements.AbstractMobileChildElement;
import io.perfeccionista.framework.pagefactory.elements.MobileRadioGroup;
import io.perfeccionista.framework.pagefactory.elements.methods.AppiumSize;
import io.perfeccionista.framework.pagefactory.elements.methods.AppiumSwipeToBlockElement;
import io.perfeccionista.framework.pagefactory.extractor.AppiumRadioButtonValueExtractor;
import io.perfeccionista.framework.pagefactory.filter.MultipleResult;
import io.perfeccionista.framework.pagefactory.filter.AppiumRadioButtonFilter;
import io.perfeccionista.framework.pagefactory.operations.OperationResult;

import static io.perfeccionista.framework.pagefactory.elements.locators.Components.RADIO;
import static io.perfeccionista.framework.pagefactory.elements.methods.availability.AvailableMethod.SCROLL_TO_ELEMENT_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.availability.AvailableMethod.SIZE_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.availability.AvailableMethod.SWIPE_TO_ELEMENT_METHOD;

@AndroidLocator(component = RADIO, xpath = ".//input", single = false)
@IosLocator(component = RADIO, xpath = ".//input", single = false)
@ElementMethod(type = SWIPE_TO_ELEMENT_METHOD, implementation = AppiumSwipeToBlockElement.class)
@ElementMethod(type = SIZE_METHOD, implementation = AppiumSize.class)
public abstract class MobileRadioGroupImpl extends AbstractMobileChildElement implements MobileRadioGroup {

    @Override
    public <V> OperationResult<MultipleResult<V>> getValues(AppiumRadioButtonValueExtractor<V> extractor) {
        return OperationResult.of(() -> extractor.extractMultipleValues(this, MultipleResult.empty()));
    }

    @Override
    public <V> OperationResult<MultipleResult<V>> getValues(AppiumRadioButtonValueExtractor<V> extractor, AppiumRadioButtonFilter filter) {
        return OperationResult.of(() -> {
            MultipleResult<MobileElement> result = filter.multipleResult(this);
            return extractor.setHash(result.getElementHash()).extractMultipleValues(this, result);
        });
    }
//
//    @Override
//    public OperationResult<Void> swipeToElement(AppiumRadioButtonFilter filter) {
//        return getMethodImplementation(SCROLL_TO_ELEMENT_METHOD, Void.class).execute(this, filter);
//    }
//
//    @Override
//    public OperationResult<Integer> size() {
//        return getMethodImplementation(SIZE_METHOD, Integer.class).execute(this, RADIO);
//    }

}
