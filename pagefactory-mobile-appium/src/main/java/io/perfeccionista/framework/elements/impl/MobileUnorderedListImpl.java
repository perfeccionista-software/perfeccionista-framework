package io.perfeccionista.framework.elements.impl;

import io.perfeccionista.framework.elements.AbstractMobileChildElement;
import io.perfeccionista.framework.pagefactory.elements.MobileBlock;
import io.perfeccionista.framework.pagefactory.elements.MobileUnorderedList;


//@AndroidLocator(component = LI, xpath = ".//li", single = false)
//@IosLocator(component = LI, xpath = ".//li", single = false)
//@ElementAction(name = SWIPE_TO_ELEMENT_METHOD, implementation = AppiumSwipeToBlockElement.class)
//@ElementAction(name = SIZE_METHOD, implementation = AppiumSize.class)
public abstract class MobileUnorderedListImpl extends AbstractMobileChildElement implements MobileUnorderedList {

    protected Class<? extends MobileBlock> blockMapper;

//    @Override
//    public <V> OperationResult<MultipleResult<V>> getValues(AppiumBlockValueExtractor<V> extractor) {
//        return OperationResult.of(() -> extractor.extractValues(this, MultipleResult.empty()));
//    }
//
//    @Override
//    public <V> OperationResult<MultipleResult<V>> getValues(AppiumBlockValueExtractor<V> extractor, AppiumBlockFilter filter) {
//        return OperationResult.of(() -> {
//            MultipleResult<MobileElement> result = filter.multipleResult(this);
//            return extractor.setHash(result.getElementHash()).extractValues(this, result);
//        });
//    }

//    @Override
//    public OperationResult<Void> swipeToElement(AppiumBlockFilter filter) {
//        return getMethodImplementation(SWIPE_TO_ELEMENT_METHOD, Void.class).execute(this, filter);
//    }
//
//    @Override
//    public OperationResult<Integer> size() {
//        return getMethodImplementation(SIZE_METHOD, Integer.class).execute(this, LI);
//    }

    protected Class<? extends MobileBlock> getBlockMapper() {
        return blockMapper;
    }

}