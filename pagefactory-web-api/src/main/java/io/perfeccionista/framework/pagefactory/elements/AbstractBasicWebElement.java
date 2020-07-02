package io.perfeccionista.framework.pagefactory.elements;

import io.perfeccionista.framework.Environment;
import io.perfeccionista.framework.pagefactory.browser.WebBrowserDispatcher;
import io.perfeccionista.framework.pagefactory.elements.base.WebParentElement;
import io.perfeccionista.framework.pagefactory.elements.locators.WebLocatorChain;
import io.perfeccionista.framework.pagefactory.elements.locators.WebLocatorHolder;
import io.perfeccionista.framework.pagefactory.elements.locators.WebLocatorRegistry;

import java.util.Optional;

import static io.perfeccionista.framework.pagefactory.elements.components.WebComponents.ROOT;

public class AbstractBasicWebElement {

    protected WebParentElement parent;
    protected WebLocatorRegistry locatorRegistry;

    public Environment getEnvironment() {
        return parent.getEnvironment();
    }

    public WebParentElement getParent() {
        return parent;
    }

    public WebBrowserDispatcher getWebBrowserDispatcher() {
        return parent.getWebBrowserDispatcher();
    }

    public Optional<WebLocatorHolder> getLocator(String componentName) {
        return locatorRegistry.getOptionalLocator(componentName);
    }

    public WebLocatorChain getLocatorChainTo(String locatorName) {
        Optional<WebLocatorHolder> optionalLocator = locatorRegistry.getOptionalLocator(locatorName);
        if (optionalLocator.isPresent()) {
            return getLocatorChain().addLocator(optionalLocator.get());
        }
        return getLocatorChain();
    }

    public WebLocatorChain getLocatorChain() {
        return parent.getLocatorChain().addLocator(locatorRegistry.getLocator(ROOT));
    }

}
