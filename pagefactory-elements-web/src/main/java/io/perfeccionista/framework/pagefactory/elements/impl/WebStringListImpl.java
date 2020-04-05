package io.perfeccionista.framework.pagefactory.elements.impl;

import io.perfeccionista.framework.pagefactory.elements.AbstractWebChildElement;
import io.perfeccionista.framework.pagefactory.elements.WebStringList;
import io.perfeccionista.framework.pagefactory.filter.MultipleResult;
import io.perfeccionista.framework.pagefactory.filter.WebStringListFilter;
import io.perfeccionista.framework.pagefactory.js.GetText;
import io.perfeccionista.framework.pagefactory.operations.JsOperation;
import org.junit.platform.commons.util.ReflectionUtils;

import static io.perfeccionista.framework.pagefactory.elements.locators.Components.LI;
import static io.perfeccionista.framework.pagefactory.elements.methods.availability.AvailableMethod.SCROLL_TO_ELEMENT_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.availability.AvailableMethod.SIZE_METHOD;


public abstract class WebStringListImpl extends AbstractWebChildElement implements WebStringList {

//    @Override
//    public MultipleResult<String> getValues() {
//        GetText getTextFunction = ReflectionUtils.newInstance(GetText.class);
//        JsOperation<MultipleResult<String>> operation = JsOperation.multiple(getLocatorChainTo(LI), getTextFunction);
//        return getDriverInstance().getDriverOperationExecutor().executeOperation(operation);
//    }
//
//    @Override
//    public MultipleResult<String> getValues(WebStringListFilter filter) {
//        return filter.multipleResult(this);
//    }
//
//    @Override
//    public void scrollToElement(WebStringListFilter filter) {
//        getMethodImplementation(SCROLL_TO_ELEMENT_METHOD, Void.class).execute(this, filter);
//    }
//
//    @Override
//    public int size() {
//        return getMethodImplementation(SIZE_METHOD, Integer.class).execute(this, LI);
//    }

}
