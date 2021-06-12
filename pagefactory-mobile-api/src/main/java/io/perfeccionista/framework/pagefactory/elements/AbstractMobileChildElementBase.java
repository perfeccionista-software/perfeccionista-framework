package io.perfeccionista.framework.pagefactory.elements;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.perfeccionista.framework.Environment;
import io.perfeccionista.framework.exceptions.LocatorNotFound;
import io.perfeccionista.framework.exceptions.attachments.MobileElementAttachmentEntry;
import io.perfeccionista.framework.name.MobileElementIdentifier;
import io.perfeccionista.framework.pagefactory.dispatcher.MobileDeviceDispatcher;
import io.perfeccionista.framework.pagefactory.elements.actions.base.EndpointHandlerRegistry;
import io.perfeccionista.framework.pagefactory.elements.base.MobileChildElementBase;
import io.perfeccionista.framework.pagefactory.elements.base.MobileParentHolder;
import io.perfeccionista.framework.pagefactory.elements.locators.MobileLocatorChain;
import io.perfeccionista.framework.pagefactory.elements.locators.MobileLocatorHolder;
import io.perfeccionista.framework.pagefactory.elements.locators.MobileLocatorRegistry;
import io.perfeccionista.framework.pagefactory.operation.MobileElementOperationHandler;
import io.perfeccionista.framework.pagefactory.operation.handler.EndpointHandler;
import io.perfeccionista.framework.pagefactory.operation.type.MobileActionOperationType;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;

import static io.perfeccionista.framework.exceptions.messages.PageFactoryApiMessages.ELEMENT_LOCATOR_NOT_FOUND;
import static io.perfeccionista.framework.invocation.wrapper.CheckInvocationWrapper.runCheck;
import static io.perfeccionista.framework.pagefactory.elements.ElementComponents.ROOT;
import static io.perfeccionista.framework.utils.JsonUtils.createObjectNode;

public class AbstractMobileChildElementBase implements MobileChildElementBase {

    protected MobileParentHolder parentHolder;
    protected MobileLocatorRegistry locatorRegistry;
    protected EndpointHandlerRegistry actionRegistry;

    protected MobileElementIdentifier elementIdentifier;

    public @NotNull Environment getEnvironment() {
        return parentHolder.getParent().getEnvironment();
    }

    public @NotNull MobileDeviceDispatcher getMobileDeviceDispatcher() {
        return parentHolder.getParent().getMobileDeviceDispatcher();
    }

    @Override
    public @NotNull MobileLocatorHolder getRequiredLocator(@NotNull String componentName) {
        return locatorRegistry.getOptionalLocator(componentName)
                .orElseThrow(() -> LocatorNotFound.exception(ELEMENT_LOCATOR_NOT_FOUND.getMessage(componentName))
                        .addLastAttachmentEntry(MobileElementAttachmentEntry.of(this)));
    }

    @Override
    public Optional<MobileLocatorHolder> getOptionalLocator(@NotNull String componentName) {
        return locatorRegistry.getOptionalLocator(componentName);
    }

    @Override
    public @NotNull MobileParentHolder getParentHolder() {
        return parentHolder;
    }

    @Override
    public @NotNull MobileLocatorChain getLocatorChainTo(@NotNull String locatorName) {
        if (ROOT.equals(locatorName)) {
            return getLocatorChain();
        }
        Optional<MobileLocatorHolder> optionalLocator = locatorRegistry.getOptionalLocator(locatorName);
        if (optionalLocator.isPresent()) {
            return getLocatorChain().addLastLocator(optionalLocator.get());
        }
        return getLocatorChain();
    }

    @Override
    public @NotNull MobileLocatorChain getLocatorChain() {
        return parentHolder.getLocatorChain().addLastLocator(getRequiredLocator(ROOT));
    }

    @Override
    public @NotNull MobileElementIdentifier getElementIdentifier() {
        return elementIdentifier;
    }

    @Override
    public @NotNull <T> Class<? extends EndpointHandler<T>> getEndpointHandler(@NotNull String actionName, @NotNull Class<T> returnType) {
        return actionRegistry.getEndpointHandler(actionName, returnType);
    }

    // Actions

    @Override
    public MobileChildElementBase executeAction(@NotNull String name, Object... args) {
        MobileActionOperationType operationType = MobileActionOperationType.of(this, name, args);
        runCheck(operationType.getInvocationName(),
                () -> MobileElementOperationHandler.of(this, operationType).executeAction());
        return this;
    }

    @Override
    public JsonNode toJson() {
        ObjectNode rootNode = createObjectNode();
        rootNode.set("elementIdentifier", this.elementIdentifier.toJson());
        rootNode.put("elementClass", this.getClass().getCanonicalName())
                .put("parent", this.parentHolder.getParent().getClass().getCanonicalName());
        rootNode.set("locators", this.locatorRegistry.toJson());
        rootNode.set("actions", this.actionRegistry.toJson());
        return rootNode;
    }

}
