package io.perfeccionista.framework.pagefactory.elements.web;

import io.perfeccionista.framework.name.NamesRegistry;
import io.perfeccionista.framework.pagefactory.ElementsConfiguration;
import io.perfeccionista.framework.pagefactory.driver.DriverInstance;
import io.perfeccionista.framework.pagefactory.elements.Page;
import io.perfeccionista.framework.pagefactory.elements.base.Element;
import io.perfeccionista.framework.pagefactory.elements.locators.LocatorChain;
import io.perfeccionista.framework.pagefactory.elements.locators.LocatorHolder;
import io.perfeccionista.framework.pagefactory.elements.locators.LocatorsRegistry;
import io.perfeccionista.framework.pagefactory.elements.registry.ElementsRegistry;
import io.perfeccionista.framework.pagefactory.operations.OperationResult;

import java.util.Optional;
import java.util.Set;

public class WebPage<T> implements Page {

    protected final DriverInstance<T> driverInstance;
    protected final ElementsConfiguration configuration;

    protected ElementsRegistry elementsRegistry;
    protected LocatorsRegistry locatorRegistry;
    protected NamesRegistry namesRegistry;

    public WebPage(DriverInstance<T> driverInstance, ElementsConfiguration configuration) {
        this.driverInstance = driverInstance;
        this.configuration = configuration;
    }

    @Override
    public ElementsConfiguration getElementsConfiguration() {
        return configuration;
    }

    @Override
    public ElementsRegistry getElementRegistry() {
        return elementsRegistry;
    }

    @Override
    public Optional<Element> getElementByPath(String elementPath) {
        return elementsRegistry.getElementByPath(elementPath);
    }

    @Override
    public <T extends Element> Optional<T> getElementByPath(String elementPath, Class<T> elementClass) {
        return elementsRegistry.getElementByPath(elementPath, elementClass);
    }

    @Override
    public void invokeMethod(String methodName, Object... args) {
        elementsRegistry.invokeMethodByName(methodName, this, args);
    }

    @Override
    public DriverInstance<T> getDriverInstance() {
        return driverInstance;
    }

    @Override
    public LocatorChain getLocatorChainTo(String locatorName) {
        Optional<LocatorHolder> optionalLocator = locatorRegistry.getOptionalLocator(locatorName);
        if (optionalLocator.isPresent()) {
            return LocatorChain.of(optionalLocator.get());
        }
        return LocatorChain.of();
    }

    @Override
    public LocatorChain getLocatorChain() {
        return LocatorChain.of();
    }

    @Override
    public LocatorHolder getLocator(String locatorName) {
        return locatorRegistry.getLocator(locatorName);
    }

    @Override
    public Set<String> getNames() {
        return namesRegistry.names();
    }

    // Этот метод должен переопределяться из интерфейса, если он переопределен там
    @Override
    public OperationResult<Boolean> isPageOpen() {
        return OperationResult.success(true);
    }

}
