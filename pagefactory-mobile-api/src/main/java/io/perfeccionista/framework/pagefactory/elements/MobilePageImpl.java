package io.perfeccionista.framework.pagefactory.elements;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.perfeccionista.framework.Environment;
import io.perfeccionista.framework.matcher.element.MobilePageMatcher;
import io.perfeccionista.framework.name.MobilePageIdentifier;
import io.perfeccionista.framework.pagefactory.dispatcher.MobileDeviceDispatcher;
import io.perfeccionista.framework.pagefactory.elements.actions.base.EndpointHandlerRegistry;
import io.perfeccionista.framework.pagefactory.elements.locators.MobileLocatorChain;
import io.perfeccionista.framework.pagefactory.elements.locators.MobileLocatorHolder;
import io.perfeccionista.framework.pagefactory.elements.locators.MobileLocatorRegistry;
import io.perfeccionista.framework.pagefactory.elements.registry.MobileElementRegistry;
import io.perfeccionista.framework.pagefactory.operation.handler.EndpointHandler;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;

import static io.perfeccionista.framework.pagefactory.elements.ElementComponents.ROOT;
import static io.perfeccionista.framework.utils.JsonUtils.createObjectNode;

public class MobilePageImpl implements MobilePage {

    protected EndpointHandlerRegistry actionRegistry;
    protected MobileLocatorRegistry locatorRegistry;
    protected MobileElementRegistry elementRegistry;
    protected MobilePageIdentifier pageIdentifier;

    protected MobileDeviceDispatcher mobileDeviceDispatcher;
    protected Environment environment;

    @Override
    public @NotNull MobileDeviceDispatcher getMobileDeviceDispatcher() {
        return this.mobileDeviceDispatcher;
    }

    @Override
    public @NotNull MobileElementRegistry getElementRegistry() {
        return this.elementRegistry;
    }

    @Override
    public @NotNull MobilePageIdentifier getPageIdentifier() {
        return pageIdentifier;
    }

    @Override
    public MobilePage setEnvironment(Environment environment) {
        this.environment = environment;
        return this;
    }

    @Override
    public @NotNull Environment getEnvironment() {
        return environment;
    }

    @Override
    public MobilePage setMobileDeviceDispatcher(MobileDeviceDispatcher mobileDeviceDispatcher) {
        this.mobileDeviceDispatcher = mobileDeviceDispatcher;
        return this;
    }

    @Override
    public @NotNull <R> Class<? extends EndpointHandler<R>> getEndpointHandler(@NotNull String actionName, @NotNull Class<R> returnType) {
        return actionRegistry.getEndpointHandler(actionName, returnType);
    }

    @Override
    public MobilePage should(@NotNull MobilePageMatcher matcher) {
        matcher.check(this);
        return this;
    }

//    @Override
//    public MobilePage scrollToHorizontally(@NotNull HorizontalDirection scrollDirection, @NotNull MobileChildElement childElement) {
//        MobileParentScrollToHorizontallyOperationType operationType = MobileParentScrollToHorizontallyOperationType.of(this, scrollDirection, childElement);
//        runCheck(getEnvironment(), operationType.getInvocationName(),
//                () -> MobilePageOperationBuilder.of(this, operationType).executeAction());
//        return this;
//    }
//
//    @Override
//    public MobilePage scrollToVertically(@NotNull VerticalDirection scrollDirection, @NotNull MobileChildElement childElement) {
//        MobileParentScrollToVerticallyOperationType operationType = MobileParentScrollToVerticallyOperationType.of(this, scrollDirection, childElement);
//        runCheck(getEnvironment(), operationType.getInvocationName(),
//                () -> MobilePageOperationBuilder.of(this, operationType).executeAction());
//        return this;
//    }

    @Override
    public @NotNull MobileLocatorChain getLocatorChainTo(@NotNull String locatorName) {
        if (ROOT.equals(locatorName)) {
            return getLocatorChain();
        }
        Optional<MobileLocatorHolder> optionalLocator = locatorRegistry.getOptionalLocator(locatorName);
        if (optionalLocator.isPresent()) {
            return getLocatorChain().addFirstLocator(optionalLocator.get());
        }
        return getLocatorChain();
    }

    @Override
    public @NotNull MobileLocatorChain getLocatorChain() {
        Optional<MobileLocatorHolder> optionalPageRootLocator = locatorRegistry.getOptionalLocator(ROOT);
        if (optionalPageRootLocator.isPresent()) {
            return MobileLocatorChain.empty().addFirstLocator(optionalPageRootLocator.get());
        }
        return MobileLocatorChain.empty();
    }

    @Override
    public @NotNull JsonNode toJson() {
        ObjectNode rootNode = createObjectNode();
        rootNode.put("elementClass", this.getClass().getCanonicalName());
        rootNode.set("pageIdentifier", this.pageIdentifier.toJson());
        rootNode.set("locators", this.locatorRegistry.toJson());
        rootNode.set("elements", this.elementRegistry.toJson());
        rootNode.set("endpointHandlers", this.actionRegistry.toJson());
        return rootNode;
    }

}
