package io.perfeccionista.framework.pagefactory.elements.impl;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.perfeccionista.framework.exceptions.LocatorNotFound;
import io.perfeccionista.framework.exceptions.attachments.WebElementAttachmentEntry;
import io.perfeccionista.framework.name.WebElementIdentifier;
import io.perfeccionista.framework.pagefactory.dispatcher.WebBrowserDispatcher;
import io.perfeccionista.framework.pagefactory.elements.actions.base.EndpointHandlerRegistry;
import io.perfeccionista.framework.pagefactory.elements.base.WebChildElementBase;
import io.perfeccionista.framework.pagefactory.elements.base.WebParentHolder;
import io.perfeccionista.framework.pagefactory.elements.selectors.WebSelectorChain;
import io.perfeccionista.framework.pagefactory.elements.selectors.WebSelectorHolder;
import io.perfeccionista.framework.pagefactory.elements.selectors.WebSelectorRegistry;
import io.perfeccionista.framework.pagefactory.emulator.keys.Key;
import io.perfeccionista.framework.pagefactory.operation.WebElementOperationHandler;
import io.perfeccionista.framework.pagefactory.operation.handler.EndpointHandler;
import io.perfeccionista.framework.pagefactory.operation.type.WebActionOperationType;
import io.perfeccionista.framework.pagefactory.operation.type.WebGetInnerHtmlOperationType;
import io.perfeccionista.framework.pagefactory.operation.type.WebGetOuterHtmlOperationType;
import io.perfeccionista.framework.pagefactory.operation.type.WebPressKeyOperationType;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;

import static io.perfeccionista.framework.exceptions.messages.PageFactoryApiMessages.ELEMENT_LOCATOR_NOT_FOUND;
import static io.perfeccionista.framework.invocation.wrapper.MultipleAttemptInvocationWrapper.repeatInvocation;
import static io.perfeccionista.framework.pagefactory.elements.ElementComponents.ROOT;
import static io.perfeccionista.framework.utils.JsonUtils.createObjectNode;

public class AbstractWebChildElementBase implements WebChildElementBase {

    protected WebParentHolder parentHolder;
    protected WebSelectorRegistry selectorRegistry;
    protected EndpointHandlerRegistry actionRegistry;

    protected WebElementIdentifier elementIdentifier;

    public @NotNull WebBrowserDispatcher getWebBrowserDispatcher() {
        return parentHolder.getParent().getWebBrowserDispatcher();
    }

    @Override
    public @NotNull WebSelectorHolder getRequiredSelector(@NotNull String componentName) {
        return selectorRegistry.getOptionalSelector(componentName)
                .orElseThrow(() -> LocatorNotFound.exception(ELEMENT_LOCATOR_NOT_FOUND.getMessage(componentName))
                        .addLastAttachmentEntry(WebElementAttachmentEntry.of(this)));
    }

    @Override
    public Optional<WebSelectorHolder> getOptionalSelector(@NotNull String componentName) {
        return selectorRegistry.getOptionalSelector(componentName);
    }

    @Override
    public @NotNull WebParentHolder getParentHolder() {
        return parentHolder;
    }

    @Override
    public @NotNull WebSelectorChain getSelectorChainTo(@NotNull String componentName) {
        if (ROOT.equals(componentName)) {
            return getSelectorChain();
        }
        Optional<WebSelectorHolder> optionalLocator = selectorRegistry.getOptionalSelector(componentName);
        if (optionalLocator.isPresent()) {
            return getSelectorChain().addLastSelector(optionalLocator.get());
        }
        return getSelectorChain();
    }

    @Override
    public @NotNull WebSelectorChain getSelectorChain() {
        return parentHolder.getSelectorChain().addLastSelector(getRequiredSelector(ROOT));
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
        repeatInvocation(operationType.getInvocationName(),
                () -> WebElementOperationHandler.of(this, operationType, ROOT).executeAction());
        return this;
    }

    // Add

    @Override
    public WebChildElementBase addName(@NotNull String elementName) {
        elementIdentifier.addName(elementName);
        return this;
    }

    @Override
    public WebChildElementBase addComponent(@NotNull String componentName, @NotNull WebSelectorHolder selector) {
        selectorRegistry.addSelector(componentName, selector);
        return this;
    }

    // GetDescription

    @Override
    public @NotNull String getInnerHtml() {
        WebGetInnerHtmlOperationType operationType = WebGetInnerHtmlOperationType.of(this);
        return repeatInvocation(operationType.getInvocationName(),
                () -> WebElementOperationHandler.of(this, operationType, ROOT).executeGetter());
    }

    @Override
    public @NotNull String getOuterHtml() {
        WebGetOuterHtmlOperationType operationType = WebGetOuterHtmlOperationType.of(this);
        return repeatInvocation(operationType.getInvocationName(),
                () -> WebElementOperationHandler.of(this, operationType, ROOT).executeGetter());
    }

    // PressKey

    @Override
    public WebChildElementBase press(@NotNull Key key) {
        WebPressKeyOperationType operationType = WebPressKeyOperationType.of(this, key);
        repeatInvocation(operationType.getInvocationName(),
                () -> WebElementOperationHandler.of(this, operationType, ROOT).executeAction());
        return this;
    }

    @Override
    public @NotNull JsonNode toJson() {
        ObjectNode rootNode = createObjectNode();
        rootNode.set("elementIdentifier", this.elementIdentifier.toJson());
        rootNode.put("elementClass", this.getClass().getCanonicalName())
                .put("parent", this.parentHolder.getParent().getClass().getCanonicalName());
        rootNode.set("selectors", this.selectorRegistry.toJson());
        rootNode.set("actions", this.actionRegistry.toJson());
        return rootNode;
    }

}
