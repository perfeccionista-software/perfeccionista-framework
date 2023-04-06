package io.perfeccionista.framework.pagefactory.elements.impl;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.perfeccionista.framework.conditions.WebElementConditionExceptionHandler;
import io.perfeccionista.framework.conditions.WebPageCondition;
import io.perfeccionista.framework.invocation.runner.InvocationInfo;
import io.perfeccionista.framework.name.WebPageIdentifier;
import io.perfeccionista.framework.pagefactory.dispatcher.WebBrowserDispatcher;
import io.perfeccionista.framework.pagefactory.elements.WebPage;
import io.perfeccionista.framework.pagefactory.elements.actions.base.EndpointHandlerRegistry;
import io.perfeccionista.framework.pagefactory.elements.selectors.WebSelectorChain;
import io.perfeccionista.framework.pagefactory.elements.selectors.WebSelectorHolder;
import io.perfeccionista.framework.pagefactory.elements.selectors.WebSelectorRegistry;
import io.perfeccionista.framework.pagefactory.elements.registry.WebElementRegistry;
import io.perfeccionista.framework.pagefactory.operation.handler.EndpointHandler;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;

import static io.perfeccionista.framework.invocation.wrapper.MultipleAttemptInvocationWrapper.repeatInvocation;
import static io.perfeccionista.framework.invocation.wrapper.SingleAttemptInvocationWrapper.runInvocation;
import static io.perfeccionista.framework.pagefactory.elements.ElementComponents.ROOT;
import static io.perfeccionista.framework.utils.JsonUtils.createObjectNode;
import static io.perfeccionista.framework.utils.WebElementUtils.checkAndCollect;

public class WebPageImpl implements WebPage {

    protected EndpointHandlerRegistry actionRegistry;
    protected WebSelectorRegistry locatorRegistry;
    protected WebElementRegistry elementRegistry;
    protected WebPageIdentifier pageIdentifier;

    protected WebBrowserDispatcher webBrowserDispatcher;

    @Override
    public @NotNull WebBrowserDispatcher getWebBrowserDispatcher() {
        return this.webBrowserDispatcher;
    }

    @Override
    public @NotNull WebElementRegistry getElementRegistry() {
        return this.elementRegistry;
    }

    @Override
    public @NotNull WebPageIdentifier getPageIdentifier() {
        return pageIdentifier;
    }

    @Override
    public WebPage setWebBrowserDispatcher(WebBrowserDispatcher webBrowserDispatcher) {
        this.webBrowserDispatcher = webBrowserDispatcher;
        return this;
    }

    @Override
    public @NotNull <R> Class<? extends EndpointHandler<R>> getEndpointHandler(@NotNull String actionName, @NotNull Class<R> returnType) {
        return actionRegistry.getEndpointHandler(actionName, returnType);
    }

    @Override
    public @NotNull WebSelectorChain getSelectorChainTo(@NotNull String componentName) {
        if (ROOT.equals(componentName)) {
            return getSelectorChain();
        }
        Optional<WebSelectorHolder> optionalLocator = locatorRegistry.getOptionalSelector(componentName);
        if (optionalLocator.isPresent()) {
            return getSelectorChain().addFirstSelector(optionalLocator.get());
        }
        return getSelectorChain();
    }

    @Override
    public @NotNull WebSelectorChain getSelectorChain() {
        Optional<WebSelectorHolder> optionalPageRootLocator = locatorRegistry.getOptionalSelector(ROOT);
        if (optionalPageRootLocator.isPresent()) {
            return WebSelectorChain.empty().addFirstSelector(optionalPageRootLocator.get());
        }
        return WebSelectorChain.empty();
    }

    // Asserts

    @Override
    public WebPage should(@NotNull WebPageCondition... conditions) {
        checkAndCollect(conditions).forEach(condition -> {
            condition.validate(this);
            InvocationInfo invocationInfo = condition.createInvocationInfoForElement(this);
            repeatInvocation(invocationInfo, () -> condition.check(this, invocationInfo));
        });
        return this;
    }

    @Override
    public WebPage shouldNot(@NotNull WebPageCondition... conditions) {
        checkAndCollect(conditions).forEach(condition -> {
            condition.validate(this)
                    .inverse();
            InvocationInfo invocationInfo = condition.createInvocationInfoForElement(this);
            repeatInvocation(invocationInfo, () -> condition.check(this, invocationInfo));
        });
        return this;
    }

    // Checks

    @Override
    public boolean check(@NotNull WebPageCondition... conditions) {
        return checkAndCollect(conditions).stream().allMatch(condition -> {
            condition.validate(this);
            InvocationInfo invocationInfo = condition.createInvocationInfoForElement(this);
            return runInvocation(invocationInfo, () -> WebElementConditionExceptionHandler.of(() -> condition.check(this, invocationInfo))
                    .isSuccess());
        });
    }

    @Override
    public boolean checkNot(@NotNull WebPageCondition... conditions) {
        return checkAndCollect(conditions).stream().allMatch(condition -> {
            condition.validate(this)
                    .inverse();
            InvocationInfo invocationInfo = condition.createInvocationInfoForElement(this);
            return runInvocation(invocationInfo, () -> WebElementConditionExceptionHandler.of(() -> condition.check(this, invocationInfo))
                    .isSuccess());
        });
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
