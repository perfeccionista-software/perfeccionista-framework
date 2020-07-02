package io.perfeccionista.framework.pagefactory.elements;

import com.fasterxml.jackson.databind.JsonNode;
import io.perfeccionista.framework.Environment;
import io.perfeccionista.framework.name.WebPageIdentifier;
import io.perfeccionista.framework.pagefactory.browser.WebBrowserDispatcher;
import io.perfeccionista.framework.pagefactory.elements.locators.WebLocatorChain;
import io.perfeccionista.framework.pagefactory.elements.locators.WebLocatorHolder;
import io.perfeccionista.framework.pagefactory.elements.registry.WebElementRegistry;

import java.util.Optional;

import static io.perfeccionista.framework.pagefactory.elements.components.WebComponents.ROOT;
import static io.perfeccionista.framework.utils.JsonUtils.createObjectNode;

public class AbstractWebPage extends AbstractBasicWebElement implements WebPage {

    protected WebBrowserDispatcher webBrowserDispatcher;
    protected WebElementRegistry elementRegistry;
    protected WebPageIdentifier pageIdentifier;
    protected Environment environment;

    @Override
    public WebBrowserDispatcher getWebBrowserDispatcher() {
        return this.webBrowserDispatcher;
    }

    @Override
    public WebElementRegistry getElementRegistry() {
        return this.elementRegistry;
    }

    @Override
    public WebPageIdentifier getPageIdentifier() {
        return pageIdentifier;
    }

    @Override
    public WebPage setEnvironment(Environment environment) {
        this.environment = environment;
        return this;
    }

    @Override
    public Environment getEnvironment() {
        return environment;
    }

    @Override
    public WebPage setWebBrowserDispatcher(WebBrowserDispatcher webBrowserDispatcher) {
        this.webBrowserDispatcher = webBrowserDispatcher;
        return this;
    }

    @Override
    public WebLocatorChain getLocatorChainTo(String locatorName) {
        Optional<WebLocatorHolder> optionalLocator = locatorRegistry.getOptionalLocator(locatorName);
        if (optionalLocator.isPresent()) {
            return getLocatorChain().addLocator(optionalLocator.get());
        }
        return getLocatorChain();
    }

    @Override
    public WebLocatorChain getLocatorChain() {
        Optional<WebLocatorHolder> optionalPageRootLocator = locatorRegistry.getOptionalLocator(ROOT);
        if (optionalPageRootLocator.isPresent()) {
            return WebLocatorChain.empty().addLocator(optionalPageRootLocator.get());
        }
        return WebLocatorChain.empty();
    }

    @Override
    // TODO: Implement
    public JsonNode toJson() {
        return createObjectNode();
    }

}