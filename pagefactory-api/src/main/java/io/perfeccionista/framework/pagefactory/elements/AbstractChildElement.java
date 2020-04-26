package io.perfeccionista.framework.pagefactory.elements;

import io.perfeccionista.framework.Environment;
import io.perfeccionista.framework.name.NamesRegistry;
import io.perfeccionista.framework.pagefactory.elements.base.ChildElement;
import io.perfeccionista.framework.pagefactory.elements.base.ParentElement;
import io.perfeccionista.framework.pagefactory.elements.locators.LocatorRegistry;
import io.perfeccionista.framework.pagefactory.elements.locators.LocatorHolder;

import java.lang.reflect.Field;
import java.util.Optional;
import java.util.Set;

// TODO: Добавить Actions and Interactions
public abstract class AbstractChildElement<T extends ParentElement<?>> implements ChildElement<T> {

    // Нужна для оборачивания методов врапперами и для работы с Value
    protected Environment environment;

    protected LocatorRegistry locatorRegistry;
    protected NamesRegistry namesRegistry;
    protected boolean required;
    protected Field field;
    protected T parent;

    @Override
    public T getParent() {
        return this.parent;
    }

    @Override
    public LocatorHolder getLocator(String locatorName) {
        return locatorRegistry.getLocator(locatorName);
    }

    @Override
    public Optional<LocatorHolder> getComponent(String componentName) {
        return locatorRegistry.getOptionalLocator(componentName);
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
