package io.perfeccionista.framework.pagefactory.elements.impl;

import io.appium.java_client.MobileElement;
import io.perfeccionista.framework.pagefactory.elements.locators.AndroidLocator;
import io.perfeccionista.framework.pagefactory.elements.locators.IosLocator;
import io.perfeccionista.framework.pagefactory.elements.methods.ElementMethod;
import io.perfeccionista.framework.pagefactory.elements.AbstractMobileChildElement;
import io.perfeccionista.framework.pagefactory.elements.MobileBlock;
import io.perfeccionista.framework.pagefactory.elements.MobileUnorderedList;
import io.perfeccionista.framework.pagefactory.elements.methods.AppiumSize;
import io.perfeccionista.framework.pagefactory.elements.methods.AppiumSwipeToBlockElement;
import io.perfeccionista.framework.pagefactory.extractor.AppiumBlockValueExtractor;
import io.perfeccionista.framework.pagefactory.filter.MultipleResult;
import io.perfeccionista.framework.pagefactory.filter.AppiumBlockFilter;
import io.perfeccionista.framework.pagefactory.operations.OperationResult;

import static io.perfeccionista.framework.pagefactory.elements.locators.Components.LI;
import static io.perfeccionista.framework.pagefactory.elements.methods.availability.AvailableMethod.SIZE_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.availability.AvailableMethod.SWIPE_TO_ELEMENT_METHOD;

@AndroidLocator(component = LI, xpath = ".//li", single = false)
@IosLocator(component = LI, xpath = ".//li", single = false)
@ElementMethod(type = SWIPE_TO_ELEMENT_METHOD, implementation = AppiumSwipeToBlockElement.class)
@ElementMethod(type = SIZE_METHOD, implementation = AppiumSize.class)
public abstract class MobileUnorderedListImpl extends AbstractMobileChildElement implements MobileUnorderedList {

    protected Class<? extends MobileBlock> blockMapper;

    @Override
    public <V> OperationResult<MultipleResult<V>> getValues(AppiumBlockValueExtractor<V> extractor) {
        return OperationResult.of(() -> extractor.extractMultipleValues(this, MultipleResult.empty()));
    }

    @Override
    public <V> OperationResult<MultipleResult<V>> getValues(AppiumBlockValueExtractor<V> extractor, AppiumBlockFilter filter) {
        return OperationResult.of(() -> {
            MultipleResult<MobileElement> result = filter.multipleResult(this);
            return extractor.setHash(result.getElementHash()).extractMultipleValues(this, result);
        });
    }

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