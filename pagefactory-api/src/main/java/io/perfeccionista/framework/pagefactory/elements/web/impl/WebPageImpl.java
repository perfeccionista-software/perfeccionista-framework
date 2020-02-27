package io.perfeccionista.framework.pagefactory.elements.web.impl;

import io.perfeccionista.framework.name.NamesRegistry;
import io.perfeccionista.framework.pagefactory.ElementsConfiguration;
import io.perfeccionista.framework.pagefactory.driver.DriverInstance;
import io.perfeccionista.framework.pagefactory.elements.locators.LocatorChain;
import io.perfeccionista.framework.pagefactory.elements.locators.LocatorHolder;
import io.perfeccionista.framework.pagefactory.elements.locators.LocatorsRegistry;
import io.perfeccionista.framework.pagefactory.elements.registry.WebElementRegistry;
import io.perfeccionista.framework.pagefactory.elements.web.WebChildElement;
import io.perfeccionista.framework.pagefactory.elements.web.WebPage;
import io.perfeccionista.framework.pagefactory.operations.OperationResult;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.util.Optional;
import java.util.Set;

public class WebPageImpl implements WebPage {

    protected final DriverInstance<RemoteWebDriver> driverInstance;
    protected final ElementsConfiguration configuration;

    protected WebElementRegistry elementsRegistry;
    protected LocatorsRegistry locatorRegistry;
    protected NamesRegistry namesRegistry;

    public WebPageImpl(DriverInstance<RemoteWebDriver> driverInstance, ElementsConfiguration configuration) {
        this.driverInstance = driverInstance;
        this.configuration = configuration;
    }

    @Override
    public ElementsConfiguration getElementsConfiguration() {
        return configuration;
    }

    @Override
    public DriverInstance<RemoteWebDriver> getDriverInstance() {
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
    public Optional<WebChildElement> getElementByPath(String elementPath) {
        return elementsRegistry.getElementByPath(elementPath);
    }

    @Override
    public <T extends WebChildElement> Optional<T> getElementByPath(String elementPath, Class<T> elementClass) {
        return elementsRegistry.getElementByPath(elementPath, elementClass);
    }

    @Override
    public void invokeMethod(String methodName, Object... args) {
        elementsRegistry.invokeMethodByName(methodName, this, args);
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
