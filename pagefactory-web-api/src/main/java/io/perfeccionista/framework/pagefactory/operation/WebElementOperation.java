package io.perfeccionista.framework.pagefactory.operation;

import com.fasterxml.jackson.databind.node.ObjectNode;
import io.perfeccionista.framework.pagefactory.elements.selectors.WebSelectorChain;
import io.perfeccionista.framework.pagefactory.operation.handler.EndpointHandler;
import io.perfeccionista.framework.pagefactory.operation.type.WebCustomOperationType;
import io.perfeccionista.framework.pagefactory.operation.type.WebElementOperationType;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import static io.perfeccionista.framework.utils.JsonUtils.createObjectNode;

public class WebElementOperation<R> {

    private final WebSelectorChain locatorChain;
    private final WebElementOperationType<R> operationType;

    private boolean withLogs = false;
    private boolean withPageSource = false;

    private WebElementOperation(WebSelectorChain locatorChain, WebElementOperationType<R> operationType) {
        this.locatorChain = locatorChain;
        this.operationType = operationType;
    }

    public static <R> WebElementOperation<R> of(@NotNull WebSelectorChain locatorChain,
                                                @NotNull WebElementOperationType<R> operationType) {
        return new WebElementOperation<>(locatorChain, operationType);
    }

    public static <R> WebElementOperation<R> of(@NotNull WebSelectorChain locatorChain,
                                                @NotNull EndpointHandler<R> endpointHandler) {
        return new WebElementOperation<>(locatorChain, WebCustomOperationType.of(endpointHandler));
    }

    public WebElementOperationType<R> getOperationType() {
        return operationType;
    }

    public WebSelectorChain getLocatorChain() {
        return locatorChain;
    }

    public WebElementOperation<R> updateLocatorChain(@NotNull Consumer<WebSelectorChain> webLocatorChainUpdater) {
        webLocatorChainUpdater.accept(locatorChain);
        return this;
    }

    public WebElementOperation<R> withPageSource() {
        this.withPageSource = true;
        return this;
    }

    public boolean isWithPageSource() {
        return withPageSource;
    }

    public WebElementOperation<R> withLogs() {
        this.withLogs = true;
        return this;
    }

    public List<EndpointHandler<?>> getRequiredFunctions() {
        List<EndpointHandler<?>> requiredFunctions = new ArrayList<>();
        locatorChain.getAllSelectors().forEach(locatorHolder -> requiredFunctions.addAll(locatorHolder.getInvokeOnCallHandlers()));
        requiredFunctions.add(operationType.getEndpointHandler());
        return requiredFunctions;
    }

    public ObjectNode toJson() {
        ObjectNode rootNode = createObjectNode();
        rootNode.set("locatorChain", this.locatorChain.toJson());
        rootNode.set("endpointFunction", this.operationType.getEndpointHandler().toJson());
        if (withLogs) {
            rootNode.put("withLogs", true);
        }
        if (withPageSource) {
            rootNode.put("withPageSource", true);
        }
        return rootNode;
    }

}
