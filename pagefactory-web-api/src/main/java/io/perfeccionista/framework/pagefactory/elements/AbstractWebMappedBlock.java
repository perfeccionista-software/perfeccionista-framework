package io.perfeccionista.framework.pagefactory.elements;

import com.fasterxml.jackson.databind.JsonNode;
import io.perfeccionista.framework.Environment;
import io.perfeccionista.framework.pagefactory.browser.WebBrowserDispatcher;
import io.perfeccionista.framework.pagefactory.elements.base.WebParentInfo;
import io.perfeccionista.framework.pagefactory.elements.base.WebChildElement;
import io.perfeccionista.framework.pagefactory.elements.locators.WebLocatorChain;
import io.perfeccionista.framework.pagefactory.elements.locators.WebLocatorHolder;
import io.perfeccionista.framework.pagefactory.elements.locators.WebLocatorRegistry;
import io.perfeccionista.framework.pagefactory.elements.registry.WebElementRegistry;

import java.util.Optional;

import static io.perfeccionista.framework.utils.JsonUtils.createObjectNode;

public class AbstractWebMappedBlock implements WebMappedBlock {

    protected WebLocatorRegistry locatorRegistry;
    protected WebElementRegistry elementRegistry;
    protected WebChildElement parent;
    protected WebParentInfo parentInfo;


    @Override
    public WebElementRegistry getElementRegistry() {
        return elementRegistry;
    }
    // TODO: Override getLocatorChain


    public WebBrowserDispatcher getWebBrowserDispatcher() {
        return parent.getWebBrowserDispatcher();
    }

    public WebChildElement getParent() {
        return parent;
    }

    public WebParentInfo getParentInfo() {
        return parentInfo;
    }

    public Environment getEnvironment() {
        return parent.getEnvironment();
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
        WebLocatorHolder webLocatorHolder = parentInfo.getWebLocatorHolder()
                .setSingle(true)
                .setIndex(parentInfo.getIndex());
        return parent.getLocatorChain().addLocator(webLocatorHolder);
    }

    @Override
    // TODO: Implement
    public JsonNode toJson() {
        return createObjectNode();
    }


}
