package io.perfeccionista.framework.pagefactory.elements;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.perfeccionista.framework.Environment;
import io.perfeccionista.framework.exceptions.WebElementActionNotFound;
import io.perfeccionista.framework.exceptions.WebLocatorNotFound;
import io.perfeccionista.framework.exceptions.attachments.WebElementAttachmentEntry;
import io.perfeccionista.framework.invocation.runner.InvocationName;
import io.perfeccionista.framework.name.WebElementIdentifier;
import io.perfeccionista.framework.pagefactory.browser.WebBrowserDispatcher;
import io.perfeccionista.framework.pagefactory.elements.actions.base.WebElementActionImplementation;
import io.perfeccionista.framework.pagefactory.elements.actions.base.WebElementActionRegistry;
import io.perfeccionista.framework.pagefactory.elements.actions.base.WebElementJsOperationActionImplementation;
import io.perfeccionista.framework.pagefactory.elements.base.WebChildElementBase;
import io.perfeccionista.framework.pagefactory.elements.base.WebParentHolder;
import io.perfeccionista.framework.pagefactory.elements.locators.WebLocatorChain;
import io.perfeccionista.framework.pagefactory.elements.locators.WebLocatorHolder;
import io.perfeccionista.framework.pagefactory.elements.locators.WebLocatorRegistry;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;

import static io.perfeccionista.framework.exceptions.messages.PageFactoryWebApiMessages.ELEMENT_ACTION_NOT_FOUND;
import static io.perfeccionista.framework.exceptions.messages.PageFactoryWebApiMessages.ELEMENT_JS_OPERATION_ACTION_NOT_FOUND;
import static io.perfeccionista.framework.exceptions.messages.PageFactoryWebApiMessages.ELEMENT_LOCATOR_NOT_FOUND;
import static io.perfeccionista.framework.invocation.wrappers.CheckInvocationWrapper.runCheck;
import static io.perfeccionista.framework.pagefactory.elements.components.WebComponents.ROOT;
import static io.perfeccionista.framework.pagefactory.elements.actions.WebElementActionNames.EXECUTE_ACTION;
import static io.perfeccionista.framework.utils.JsonUtils.createObjectNode;

public class AbstractWebChildElementBase implements WebChildElementBase {

    protected WebParentHolder parentHolder;
    protected WebLocatorRegistry locatorRegistry;
    protected WebElementActionRegistry actionRegistry;

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
                .orElseThrow(() -> WebLocatorNotFound.exception(ELEMENT_LOCATOR_NOT_FOUND.getMessage(componentName))
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
    public @NotNull <R> WebElementJsOperationActionImplementation<R> getJsOperationActionImplementation(@NotNull String actionName, @NotNull Class<R> returnType) {
        return actionRegistry.getJsOperationAction(actionName, returnType)
                .orElseThrow(() -> WebElementActionNotFound.exception(ELEMENT_JS_OPERATION_ACTION_NOT_FOUND.getMessage(actionName)));
    }

    @Override
    public @NotNull <T> WebElementActionImplementation<T> getActionImplementation(@NotNull String actionName, @NotNull Class<T> returnType) {
        return actionRegistry.getAction(actionName, returnType)
                .orElseThrow(() -> WebElementActionNotFound.exception(ELEMENT_ACTION_NOT_FOUND.getMessage(actionName)));
    }

    // Actions

    @Override
    public WebChildElementBase executeAction(@NotNull String name, Object... args) {
        runCheck(getEnvironment(), InvocationName.of(EXECUTE_ACTION, this, name, args), () -> {
            getActionImplementation(name, Void.class)
                    .execute(this, args);
        });
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
