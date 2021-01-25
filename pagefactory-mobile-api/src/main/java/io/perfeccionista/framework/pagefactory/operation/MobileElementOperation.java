package io.perfeccionista.framework.pagefactory.operation;

import com.fasterxml.jackson.databind.node.ObjectNode;
import io.perfeccionista.framework.pagefactory.operation.type.MobileElementOperationType;
import io.perfeccionista.framework.pagefactory.elements.locators.MobileLocatorChain;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import static io.perfeccionista.framework.utils.JsonUtils.createObjectNode;

public class MobileElementOperation<R> {

    private final MobileLocatorChain locatorChain;
    private final MobileElementOperationType<R> operationType;

    private boolean withLogs = false;
    private boolean withPageSource = false;

    private MobileElementOperation(MobileLocatorChain locatorChain, MobileElementOperationType<R> operationType) {
        this.locatorChain = locatorChain;
        this.operationType = operationType;
    }

    public static <R> MobileElementOperation<R> of(@NotNull MobileLocatorChain locatorChain,
                                                   @NotNull MobileElementOperationType<R> operationType) {
        return new MobileElementOperation<>(locatorChain, operationType);
    }

    public MobileElementOperationType<R> getOperationType() {
        return operationType;
    }

    public MobileLocatorChain getLocatorChain() {
        return locatorChain;
    }

    public MobileElementOperation<R> updateLocatorChain(@NotNull Consumer<MobileLocatorChain> webLocatorChainUpdater) {
        webLocatorChainUpdater.accept(locatorChain);
        return this;
    }

    public MobileElementOperation<R> withPageSource() {
        this.withPageSource = true;
        return this;
    }

    public boolean isWithPageSource() {
        return withPageSource;
    }

    public MobileElementOperation<R> withLogs() {
        this.withLogs = true;
        return this;
    }

    public List<MobileElementOperationType<?>> getRequiredFunctions() {
        List<MobileElementOperationType<?>> requiredFunctions = new ArrayList<>();
        requiredFunctions.add(operationType);
        return requiredFunctions;
    }

    public ObjectNode toJson() {
        ObjectNode rootNode = createObjectNode();
        rootNode.set("locatorChain", this.locatorChain.toJson());
        rootNode.put("endpointFunction", this.operationType.getClass().getCanonicalName());
        if (withLogs) {
            rootNode.put("withLogs", true);
        }
        if (withPageSource) {
            rootNode.put("withPageSource", true);
        }
        return rootNode;
    }

}
