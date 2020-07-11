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
import org.jetbrains.annotations.NotNull;

import java.util.Optional;

import static io.perfeccionista.framework.utils.JsonUtils.createObjectNode;

public class AbstractWebMappedBlock implements WebMappedBlock {

    protected WebLocatorRegistry locatorRegistry;
    protected WebElementRegistry elementRegistry;
    protected WebChildElement parent;
    protected WebParentInfo parentInfo;

    @Override
    public @NotNull WebElementRegistry getElementRegistry() {
        return elementRegistry;
    }

    // TODO: Override getLocatorChain


    public @NotNull WebBrowserDispatcher getWebBrowserDispatcher() {
        return parent.getWebBrowserDispatcher();
    }

    public @NotNull WebChildElement getParent() {
        return parent;
    }

    public @NotNull WebParentInfo getParentInfo() {
        return parentInfo;
    }

    public @NotNull Environment getEnvironment() {
        return parent.getEnvironment();
    }

    public Optional<WebLocatorHolder> getLocator(String componentName) {
        return locatorRegistry.getOptionalLocator(componentName);
    }

    public @NotNull WebLocatorChain getLocatorChainTo(@NotNull String locatorName) {
        Optional<WebLocatorHolder> optionalLocator = locatorRegistry.getOptionalLocator(locatorName);
        if (optionalLocator.isPresent()) {
            return getLocatorChain().addLocator(optionalLocator.get());
        }
        return getLocatorChain();
    }

    public @NotNull WebLocatorChain getLocatorChain() {
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
