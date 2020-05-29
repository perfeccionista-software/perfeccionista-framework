package io.perfeccionista.framework.pagefactory.operation;

import com.fasterxml.jackson.databind.node.ObjectNode;
import io.perfeccionista.framework.pagefactory.jsfunction.JsFunction;
import io.perfeccionista.framework.pagefactory.elements.locators.WebLocatorChain;

import java.util.ArrayList;
import java.util.List;

import static io.perfeccionista.framework.utils.JsonUtils.createObjectNode;

public class JsOperation<R> {

    private final WebLocatorChain locatorChain;
    private final JsFunction<R> endpointJsFunction;
    private boolean traceSearch = false;

    public JsOperation(WebLocatorChain locatorChain, JsFunction<R> endpointJsFunction) {
        this.locatorChain = locatorChain;
        this.endpointJsFunction = endpointJsFunction;
    }

    public static <R> JsOperation<R> of(WebLocatorChain locatorChain, JsFunction<R> jsFunction) {
        return new JsOperation<>(locatorChain, jsFunction);
    }

    public JsFunction<R> getEndpointJsFunction() {
        return endpointJsFunction;
    }

    public JsOperation<R> setTraceSearch(boolean traceSearch) {
        this.traceSearch = traceSearch;
        return this;
    }

    public List<JsFunction<?>> getRequiredFunctions() {
        List<JsFunction<?>> requiredFunctions = new ArrayList<>();
        locatorChain.getAllLocators().forEach(locatorHolder -> requiredFunctions.addAll(locatorHolder.getInvokeOnCallFunctions()));
        requiredFunctions.add(endpointJsFunction);
        return requiredFunctions;
    }

    public ObjectNode toJson() {
        ObjectNode rootNode = createObjectNode();
        rootNode.set("locatorChain", this.locatorChain.toJson());
        rootNode.set("endpointFunction", this.endpointJsFunction.getJsFunctionInvocation());
        if (traceSearch) {
            rootNode.put("traceSearch", true);
        }
        return rootNode;
    }

}
