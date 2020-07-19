package io.perfeccionista.framework.pagefactory.elements;

import com.fasterxml.jackson.databind.JsonNode;
import io.perfeccionista.framework.Environment;
import io.perfeccionista.framework.pagefactory.browser.WebBrowserDispatcher;
import io.perfeccionista.framework.pagefactory.elements.base.WebParentInfo;
import io.perfeccionista.framework.pagefactory.elements.locators.WebLocatorChain;
import io.perfeccionista.framework.pagefactory.elements.locators.WebLocatorHolder;
import io.perfeccionista.framework.pagefactory.elements.locators.WebLocatorRegistry;
import io.perfeccionista.framework.pagefactory.elements.registry.WebElementRegistry;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;

import static io.perfeccionista.framework.pagefactory.elements.components.WebComponents.ROOT;
import static io.perfeccionista.framework.utils.JsonUtils.createObjectNode;

public class AbstractWebMappedBlock implements WebMappedBlock {

    protected WebLocatorRegistry locatorRegistry;
    protected WebElementRegistry elementRegistry;
    protected WebParentInfo<?> parentInfo;

    @Override
    public @NotNull WebElementRegistry getElementRegistry() {
        return elementRegistry;
    }

    public @NotNull WebBrowserDispatcher getWebBrowserDispatcher() {
        return parentInfo.getParent().getWebBrowserDispatcher();
    }

    public @NotNull WebParentInfo<?> getParentInfo() {
        return parentInfo;
    }

    public @NotNull Environment getEnvironment() {
        return parentInfo.getParent().getEnvironment();
    }

    public Optional<WebLocatorHolder> getLocator(String componentName) {
        return locatorRegistry.getOptionalLocator(componentName);
    }

    public @NotNull WebLocatorChain getLocatorChainTo(@NotNull String locatorName) {
        if (ROOT.equals(locatorName)) {
            return getLocatorChain();
        }
        Optional<WebLocatorHolder> optionalLocator = locatorRegistry.getOptionalLocator(locatorName);
        if (optionalLocator.isPresent()) {
            return getLocatorChain().addLocator(optionalLocator.get());
        }
        return getLocatorChain();
    }

    public @NotNull WebLocatorChain getLocatorChain() {
        WebLocatorChain locatorChain = parentInfo.getParent().getLocatorChain();
        locatorChain.getLastLocator().setExpectedHash(parentInfo.getParentHash());
        locatorChain.addLocators(parentInfo.getParentLocators());
        return locatorChain;
    }

    @Override
    // TODO: Implement
    public JsonNode toJson() {
        return createObjectNode();
    }

}
