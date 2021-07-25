package io.perfeccionista.framework.pagefactory.elements;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.perfeccionista.framework.Environment;
import io.perfeccionista.framework.exceptions.LocatorNotFound;
import io.perfeccionista.framework.exceptions.attachments.WebElementAttachmentEntry;
import io.perfeccionista.framework.name.WebElementIdentifier;
import io.perfeccionista.framework.pagefactory.dispatcher.WebBrowserDispatcher;
import io.perfeccionista.framework.pagefactory.elements.actions.base.EndpointHandlerRegistry;
import io.perfeccionista.framework.pagefactory.elements.base.WebChildElementBase;
import io.perfeccionista.framework.pagefactory.elements.base.WebParentHolder;
import io.perfeccionista.framework.pagefactory.elements.locators.WebLocatorChain;
import io.perfeccionista.framework.pagefactory.elements.locators.WebLocatorHolder;
import io.perfeccionista.framework.pagefactory.elements.locators.WebLocatorRegistry;
import io.perfeccionista.framework.pagefactory.operation.WebElementOperationHandler;
import io.perfeccionista.framework.pagefactory.operation.handler.EndpointHandler;
import io.perfeccionista.framework.pagefactory.operation.type.WebActionOperationType;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;

import static io.perfeccionista.framework.exceptions.messages.PageFactoryApiMessages.ELEMENT_LOCATOR_NOT_FOUND;
import static io.perfeccionista.framework.invocation.wrapper.CheckInvocationWrapper.runCheck;
import static io.perfeccionista.framework.pagefactory.elements.ElementComponents.ROOT;
import static io.perfeccionista.framework.utils.JsonUtils.createObjectNode;

public class AbstractWebChildElementBase implements WebChildElementBase {

    protected WebParentHolder parentHolder;
    protected WebLocatorRegistry locatorRegistry;
    protected EndpointHandlerRegistry actionRegistry;

    protected WebElementIdentifier elementIdentifier;

    public @NotNull Environment getEnvironment() {
        return parentHolder.getParent().getEnvironment();
    }

    public @NotNull WebBrowserDispatcher getWebBrowserDispatcher() {
        return parentHolder.getParent().getWebBrowserDispatcher();
    }

    @Override
    public @NotNull WebLocatorHolder getRequiredLocator(@NotNull String componentName) {
        return locatorRegistry.getOptionalLocator(componentName)
                .orElseThrow(() -> LocatorNotFound.exception(ELEMENT_LOCATOR_NOT_FOUND.getMessage(componentName))
                        .addLastAttachmentEntry(WebElementAttachmentEntry.of(this)));
    }

    @Override
    public Optional<WebLocatorHolder> getOptionalLocator(@NotNull String componentName) {
        return locatorRegistry.getOptionalLocator(componentName);
    }

    @Override
    public @NotNull WebParentHolder getParentHolder() {
        return parentHolder;
    }

    @Override
    public @NotNull WebLocatorChain getLocatorChainTo(@NotNull String locatorName) {
        if (ROOT.equals(locatorName)) {
            return getLocatorChain();
        }
        Optional<WebLocatorHolder> optionalLocator = locatorRegistry.getOptionalLocator(locatorName);
        if (optionalLocator.isPresent()) {
            return getLocatorChain().addLastLocator(optionalLocator.get());
        }
        return getLocatorChain();
    }

    @Override
    public @NotNull WebLocatorChain getLocatorChain() {
        return parentHolder.getLocatorChain().addLastLocator(getRequiredLocator(ROOT));
    }

    @Override
    public @NotNull WebElementIdentifier getElementIdentifier() {
        return elementIdentifier;
    }

    @Override
    public @NotNull <T> Class<? extends EndpointHandler<T>> getEndpointHandler(@NotNull String actionName, @NotNull Class<T> returnType) {
        return actionRegistry.getEndpointHandler(actionName, returnType);
    }

    // Actions

    @Override
    public WebChildElementBase executeAction(@NotNull String name, Object... args) {
        WebActionOperationType operationType = WebActionOperationType.of(this, name, args);
        runCheck(operationType.getInvocationName(),
                () -> WebElementOperationHandler.of(this, operationType).executeAction());
        return this;
    }

    @Override
    public @NotNull JsonNode toJson() {
        ObjectNode rootNode = createObjectNode();
        rootNode.set("elementIdentifier", this.elementIdentifier.toJson());
        rootNode.put("elementClass", this.getClass().getCanonicalName())
                .put("parent", this.parentHolder.getParent().getClass().getCanonicalName());
        rootNode.set("locators", this.locatorRegistry.toJson());
        rootNode.set("actions", this.actionRegistry.toJson());
        return rootNode;
    }

}
