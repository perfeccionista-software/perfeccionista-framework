package io.perfeccionista.framework.pagefactory.operation;

import com.fasterxml.jackson.databind.node.ObjectNode;
import io.perfeccionista.framework.pagefactory.elements.locators.MobileLocatorChain;
import io.perfeccionista.framework.pagefactory.operation.type.MobileElementOperationType;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import static io.perfeccionista.framework.utils.JsonUtils.createObjectNode;

public class MobilePageOperation<R> {

//    private final MobileLocatorChain locatorChain;
//    private final MobileElementOperationType<R> actionResolver;
//
//    private boolean withLogs = false;
//    private boolean withPageSource = false;
//
//    private MobilePageOperation(MobileLocatorChain locatorChain, MobileElementOperationType<R> actionResolver) {
//        this.locatorChain = locatorChain;
//        this.actionResolver = actionResolver;
//    }
//
//    public static <R> MobilePageOperation<R> of(@NotNull MobileLocatorChain locatorChain,
//                                                @NotNull MobileElementOperationType<R> operationType) {
//        return new MobilePageOperation<>(locatorChain, operationType);
//    }
//
//    public MobileElementOperationType<R> getOperationType() {
//        return actionResolver;
//    }
//
//    public MobileLocatorChain getLocatorChain() {
//        return locatorChain;
//    }
//
//    public boolean isWithPageSource() {
//        return withPageSource;
//    }
//
//    public MobilePageOperation<R> withLogs() {
//        this.withLogs = true;
//        return this;
//    }
//
//    public MobilePageOperation<R> withPageSource() {
//        this.withPageSource = true;
//        return this;
//    }
//
//    public List<MobileElementOperationType<?>> getRequiredFunctions() {
//        List<MobileElementOperationType<?>> requiredFunctions = new ArrayList<>();
//        requiredFunctions.add(actionResolver);
//        return requiredFunctions;
//    }
//
//    public ObjectNode toJson() {
//        ObjectNode rootNode = createObjectNode();
//        rootNode.put("endpointFunction", this.actionResolver.getClass().getCanonicalName());
//        if (withLogs) {
//            rootNode.put("withLogs", true);
//        }
//        if (withPageSource) {
//            rootNode.put("withPageSource", true);
//        }
//        return rootNode;
//    }

}
