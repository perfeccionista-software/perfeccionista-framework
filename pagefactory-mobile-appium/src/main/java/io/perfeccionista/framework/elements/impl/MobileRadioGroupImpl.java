package io.perfeccionista.framework.elements.impl;

import io.perfeccionista.framework.elements.AbstractMobileChildElement;
import io.perfeccionista.framework.pagefactory.elements.MobileRadioGroup;


//@AndroidLocator(component = RADIO, xpath = ".//input", single = false)
//@IosLocator(component = RADIO, xpath = ".//input", single = false)
//@ElementAction(name = SWIPE_TO_ELEMENT_METHOD, implementation = AppiumSwipeToBlockElement.class)
//@ElementAction(name = SIZE_METHOD, implementation = AppiumSize.class)
public abstract class MobileRadioGroupImpl extends AbstractMobileChildElement implements MobileRadioGroup {

//    @Override
//    public <V> OperationResult<MultipleResult<V>> getValues(AppiumRadioButtonValueExtractor<V> extractor) {
//        return OperationResult.of(() -> extractor.extractValues(this, MultipleResult.empty()));
//    }
//
//    @Override
//    public <V> OperationResult<MultipleResult<V>> getValues(AppiumRadioButtonValueExtractor<V> extractor, AppiumRadioButtonFilter filter) {
//        return OperationResult.of(() -> {
//            MultipleResult<MobileElement> result = filter.multipleResult(this);
//            return extractor.setHash(result.getElementHash()).extractValues(this, result);
//        });
//    }
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
