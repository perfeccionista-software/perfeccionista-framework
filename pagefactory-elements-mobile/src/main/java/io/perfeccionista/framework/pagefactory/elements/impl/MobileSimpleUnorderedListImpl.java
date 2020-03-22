package io.perfeccionista.framework.pagefactory.elements.impl;

import io.perfeccionista.framework.exceptions.mapper.AppiumExceptionMapper;
import io.perfeccionista.framework.pagefactory.elements.locators.AndroidLocator;
import io.perfeccionista.framework.pagefactory.elements.locators.IosLocator;
import io.perfeccionista.framework.pagefactory.elements.methods.ElementMethod;
import io.perfeccionista.framework.pagefactory.elements.AbstractMobileChildElement;
import io.perfeccionista.framework.pagefactory.elements.MobileSimpleUnorderedList;
import io.perfeccionista.framework.pagefactory.elements.methods.AppiumSize;
import io.perfeccionista.framework.pagefactory.elements.methods.AppiumSwipeToStringBlockElement;
import io.perfeccionista.framework.pagefactory.filter.MultipleResult;
import io.perfeccionista.framework.pagefactory.filter.AppiumStringBlockFilter;
import io.perfeccionista.framework.pagefactory.operations.OperationResult;

import java.util.Map.Entry;
import java.util.stream.Collectors;

import static io.perfeccionista.framework.pagefactory.elements.locators.Components.LI;
import static io.perfeccionista.framework.pagefactory.elements.methods.availability.AvailableMethod.SIZE_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.availability.AvailableMethod.SWIPE_TO_ELEMENT_METHOD;

@AndroidLocator(component = LI, xpath = ".//li", single = false)
@IosLocator(component = LI, xpath = ".//li", single = false)
@ElementMethod(type = SWIPE_TO_ELEMENT_METHOD, implementation = AppiumSwipeToStringBlockElement.class)
@ElementMethod(type = SIZE_METHOD, implementation = AppiumSize.class)
public abstract class MobileSimpleUnorderedListImpl extends AbstractMobileChildElement implements MobileSimpleUnorderedList {

    @Override
    public OperationResult<MultipleResult<String>> getValues() {
        return OperationResult.of(() -> getDriverInstance().getExceptionMapper(AppiumExceptionMapper.class).map(() ->
                MultipleResult.of(findElements(getLocatorChainTo(LI)).get().entrySet().stream()
                        .collect(Collectors.toMap(Entry::getKey, entry -> entry.getValue().getText())))));
    }

    @Override
    public OperationResult<MultipleResult<String>> getValues(AppiumStringBlockFilter filter) {
        return OperationResult.of(() -> getDriverInstance().getExceptionMapper(AppiumExceptionMapper.class).map(() ->
                MultipleResult.of(filter.multipleResult(this).get().entrySet().stream()
                        .collect(Collectors.toMap(Entry::getKey, entry -> entry.getValue().getText())))));
    }

//    @Override
//    public OperationResult<Void> swipeToElement(AppiumStringBlockFilter filter) {
//        return getMethodImplementation(SWIPE_TO_ELEMENT_METHOD, Void.class).execute(this, filter);
//    }
//
//    @Override
//    public OperationResult<Integer> size() {
//        return getMethodImplementation(SIZE_METHOD, Integer.class).execute(this, LI);
//    }

}
