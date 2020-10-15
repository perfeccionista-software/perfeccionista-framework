package io.perfeccionista.framework.pagefactory.elements;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.perfeccionista.framework.Environment;
import io.perfeccionista.framework.matcher.element.WebPageMatcher;
import io.perfeccionista.framework.name.WebPageIdentifier;
import io.perfeccionista.framework.pagefactory.browser.WebBrowserDispatcher;
import io.perfeccionista.framework.pagefactory.elements.locators.WebLocatorChain;
import io.perfeccionista.framework.pagefactory.elements.locators.WebLocatorHolder;
import io.perfeccionista.framework.pagefactory.elements.locators.WebLocatorRegistry;
import io.perfeccionista.framework.pagefactory.elements.registry.WebElementRegistry;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;

import static io.perfeccionista.framework.pagefactory.elements.components.WebComponents.ROOT;
import static io.perfeccionista.framework.utils.JsonUtils.createObjectNode;

public class WebPageImpl implements WebPage {

    protected WebLocatorRegistry locatorRegistry;
    protected WebElementRegistry elementRegistry;
    protected WebPageIdentifier pageIdentifier;

    protected WebBrowserDispatcher webBrowserDispatcher;
    protected Environment environment;

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
    public WebPage setEnvironment(Environment environment) {
        this.environment = environment;
        return this;
    }

    @Override
    public @NotNull Environment getEnvironment() {
        return environment;
    }

    @Override
    public WebPage setWebBrowserDispatcher(WebBrowserDispatcher webBrowserDispatcher) {
        this.webBrowserDispatcher = webBrowserDispatcher;
        return this;
    }

    @Override
    public WebPage should(@NotNull WebPageMatcher matcher) {
        matcher.check(this);
        return this;
    }

    @Override
    public @NotNull WebLocatorChain getLocatorChainTo(@NotNull String locatorName) {
        if (ROOT.equals(locatorName)) {
            return getLocatorChain();
        }
        Optional<WebLocatorHolder> optionalLocator = locatorRegistry.getOptionalLocator(locatorName);
        if (optionalLocator.isPresent()) {
            return getLocatorChain().addFirstLocator(optionalLocator.get());
        }
        return getLocatorChain();
    }

    @Override
    public @NotNull WebLocatorChain getLocatorChain() {
        Optional<WebLocatorHolder> optionalPageRootLocator = locatorRegistry.getOptionalLocator(ROOT);
        if (optionalPageRootLocator.isPresent()) {
            return WebLocatorChain.empty().addFirstLocator(optionalPageRootLocator.get());
        }
        return WebLocatorChain.empty();
    }

    @Override
    public @NotNull JsonNode toJson() {
        ObjectNode rootNode = createObjectNode();
        rootNode.put("elementClass", this.getClass().getCanonicalName());
        rootNode.set("pageIdentifier", this.pageIdentifier.toJson());
        rootNode.set("locators", this.locatorRegistry.toJson());
        rootNode.set("elements", this.elementRegistry.toJson());
        return rootNode;
    }

}