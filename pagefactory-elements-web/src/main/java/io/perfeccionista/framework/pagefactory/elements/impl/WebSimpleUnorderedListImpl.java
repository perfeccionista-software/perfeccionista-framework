package io.perfeccionista.framework.pagefactory.elements.impl;

import io.perfeccionista.framework.pagefactory.elements.locators.WebLocator;
import io.perfeccionista.framework.pagefactory.elements.methods.ElementMethod;
import io.perfeccionista.framework.pagefactory.elements.AbstractWebChildElement;
import io.perfeccionista.framework.pagefactory.elements.WebSimpleUnorderedList;
import io.perfeccionista.framework.pagefactory.elements.methods.JsScrollToStringBlockElement;
import io.perfeccionista.framework.pagefactory.elements.methods.JsSize;
import io.perfeccionista.framework.pagefactory.filter.MultipleResult;
import io.perfeccionista.framework.pagefactory.filter.JsStringBlockFilter;
import io.perfeccionista.framework.pagefactory.js.GetText;
import io.perfeccionista.framework.pagefactory.operations.JsOperation;
import io.perfeccionista.framework.pagefactory.operations.OperationResult;
import org.junit.platform.commons.util.ReflectionUtils;

import static io.perfeccionista.framework.pagefactory.elements.locators.Components.LI;
import static io.perfeccionista.framework.pagefactory.elements.methods.availability.AvailableMethod.SCROLL_TO_ELEMENT_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.availability.AvailableMethod.SIZE_METHOD;

@WebLocator(component = LI, xpath = ".//li", single = false)
@ElementMethod(type = SCROLL_TO_ELEMENT_METHOD, implementation = JsScrollToStringBlockElement.class)
@ElementMethod(type = SIZE_METHOD, implementation = JsSize.class)
public class WebSimpleUnorderedListImpl extends AbstractWebChildElement implements WebSimpleUnorderedList {

    @Override
    public OperationResult<MultipleResult<String>> getValues() {
        GetText getTextFunction = ReflectionUtils.newInstance(GetText.class);
        JsOperation<MultipleResult<String>> operation = JsOperation.multiple(getLocatorChainTo(LI), getTextFunction);
        return OperationResult.of(() -> getDriverInstance().getDriverOperationExecutor().executeOperation(operation));
    }

    @Override
    public OperationResult<MultipleResult<String>> getValues(JsStringBlockFilter filter) {
        return OperationResult.of(() -> filter.multipleResult(this));
    }

    @Override
    public OperationResult<Void> scrollToElement(JsStringBlockFilter filter) {
        return getMethodImplementation(SCROLL_TO_ELEMENT_METHOD, Void.class).execute(this, filter);
    }

    @Override
    public OperationResult<Integer> size() {
        return getMethodImplementation(SIZE_METHOD, Integer.class).execute(this, LI);
    }

}
