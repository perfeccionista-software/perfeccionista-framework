package io.perfeccionista.framework.pagefactory.elements;

import io.perfeccionista.framework.name.NamesRegistry;
import io.perfeccionista.framework.pagefactory.elements.base.ChildElement;
import io.perfeccionista.framework.pagefactory.elements.base.ParentElement;
import io.perfeccionista.framework.pagefactory.elements.locators.LocatorsRegistry;
import io.perfeccionista.framework.pagefactory.elements.properties.ElementPropertiesRegistry;
import io.perfeccionista.framework.pagefactory.elements.properties.ElementPropertyHolder;
import io.perfeccionista.framework.pagefactory.elements.states.ElementStateHolder;
import io.perfeccionista.framework.pagefactory.elements.locators.LocatorHolder;
import io.perfeccionista.framework.pagefactory.elements.states.ElementStatesRegistry;

import java.lang.reflect.Field;
import java.util.Optional;
import java.util.Set;

// TODO: Добавить Actions and Interactions
public abstract class AbstractChildElement<T extends ParentElement<?>> implements ChildElement<T> {

    protected ElementPropertiesRegistry elementPropertiesRegistry;
    protected ElementStatesRegistry elementStatesRegistry;
    protected LocatorsRegistry locatorRegistry;
    protected NamesRegistry namesRegistry;
    protected T parent;
    protected boolean required;
    protected Field field;

    @Override
    public T getParent() {
        return this.parent;
    }

    @Override
    public LocatorHolder getLocator(String locatorName) {
        return locatorRegistry.getLocator(locatorName);
    }

    @Override
    public Optional<ElementPropertyHolder> getProperty(String propertyName) {
        return elementPropertiesRegistry.getElementProperty(propertyName);
    }

    @Override
    public Optional<ElementStateHolder> getState(String stateName) {
        return elementStatesRegistry.getElementState(stateName);
    }

    @Override
    public boolean isRequired() {
        return this.required;
    }



    @Override
    public Set<String> getNames() {
        return namesRegistry.names();
    }

}
