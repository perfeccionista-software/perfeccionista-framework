package io.perfeccionista.framework.pagefactory.elements.impl;

import io.perfeccionista.framework.name.NamesRegistry;
import io.perfeccionista.framework.pagefactory.ElementsConfiguration;
import io.perfeccionista.framework.pagefactory.driver.WebDriverInstance;
import io.perfeccionista.framework.pagefactory.elements.context.WebSearchLimiterRegistry;
import io.perfeccionista.framework.pagefactory.elements.locators.LocatorChain;
import io.perfeccionista.framework.pagefactory.elements.locators.LocatorHolder;
import io.perfeccionista.framework.pagefactory.elements.locators.LocatorRegistry;
import io.perfeccionista.framework.pagefactory.elements.registry.WebElementRegistry;
import io.perfeccionista.framework.pagefactory.elements.WebChildElement;
import io.perfeccionista.framework.pagefactory.elements.WebPage;
import io.perfeccionista.framework.pagefactory.operations.OperationResult;

import java.util.Optional;
import java.util.Set;

public abstract class WebPageImpl implements WebPage {

//    protected final WebDriverInstance driverInstance;
//    protected final WebSearchLimiterRegistry searchLimiterRegistry;
//    protected final ElementsConfiguration configuration;
//
//    protected WebElementRegistry elementsRegistry;
//    protected LocatorRegistry locatorRegistry;
//    protected NamesRegistry namesRegistry;
//
//    public WebPageImpl(WebDriverInstance driverInstance, ElementsConfiguration configuration) {
//        this.driverInstance = driverInstance;
//        this.configuration = configuration;
//        this.searchLimiterRegistry = new WebSearchLimiterRegistry(this);
//    }
//
//    @Override
//    public WebSearchLimiterRegistry getSearchLimiterRegistry() {
//        return searchLimiterRegistry;
//    }
//
//    @Override
//    public ElementsConfiguration getElementsConfiguration() {
//        return configuration;
//    }
//
//    @Override
//    public WebDriverInstance getDriverInstance() {
//        return driverInstance;
//    }
//
//    @Override
//    public WebElementRegistry getElementRegistry() {
//        return elementsRegistry;
//    }
//
//    @Override
//    public LocatorChain getLocatorChainTo(String locatorName) {
//        Optional<LocatorHolder> optionalLocator = locatorRegistry.getOptionalLocator(locatorName);
//        if (optionalLocator.isPresent()) {
//            return LocatorChain.of(optionalLocator.get());
//        }
//        return LocatorChain.of();
//    }
//
//    @Override
//    public LocatorChain getLocatorChain() {
//        return LocatorChain.of();
//    }
//
//    @Override
//    public LocatorHolder getLocator(String locatorName) {
//        return locatorRegistry.getLocator(locatorName);
//    }
//
//    @Override
//    public Optional<WebChildElement> getElementByPath(String elementPath) {
//        return searchLimiterRegistry.getContext().getElementRegistry().getElementByPath(elementPath);
//    }
//
//    @Override
//    public <T extends WebChildElement> Optional<T> getElementByPath(String elementPath, Class<T> elementClass) {
//        return searchLimiterRegistry.getContext().getElementRegistry().getElementByPath(elementPath, elementClass);
//    }
//
//    @Override
//    public void invokeMethod(String methodName, Object... args) {
//        searchLimiterRegistry.getContext().getElementRegistry().invokeMethodByName(methodName, this, args);
//    }
//
//    @Override
//    public Set<String> getNames() {
//        return namesRegistry.names();
//    }
//
//    // Этот метод должен переопределяться из интерфейса, если он переопределен там
//    @Override
//    public OperationResult<Boolean> isPageOpen() {
//        return OperationResult.success(true);
//    }

}
