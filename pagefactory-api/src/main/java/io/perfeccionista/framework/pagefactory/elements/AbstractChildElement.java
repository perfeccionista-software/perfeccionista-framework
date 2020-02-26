package io.perfeccionista.framework.pagefactory.elements;

import io.perfeccionista.framework.name.NamesRegistry;
import io.perfeccionista.framework.pagefactory.elements.base.ChildElement;
import io.perfeccionista.framework.pagefactory.elements.base.ParentElement;
import io.perfeccionista.framework.pagefactory.elements.locators.LocatorsRegistry;
import io.perfeccionista.framework.pagefactory.elements.properties.ElementPropertiesRegistry;
import io.perfeccionista.framework.pagefactory.elements.properties.ElementPropertyHolder;
import io.perfeccionista.framework.pagefactory.elements.states.ElementStateHolder;
import io.perfeccionista.framework.pagefactory.elements.locators.LocatorHolder;
import io.perfeccionista.framework.pagefactory.elements.locators.LocatorChain;
import io.perfeccionista.framework.pagefactory.elements.states.ElementStatesRegistry;

import java.lang.reflect.Field;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import static io.perfeccionista.framework.pagefactory.elements.locators.Components.ROOT;

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
    public LocatorChain getLocatorChainTo(String locatorName) {
        Optional<LocatorHolder> optionalLocator = locatorRegistry.getOptionalLocator(locatorName);
        if (optionalLocator.isPresent()) {
            return getLocatorChain().addLocator(optionalLocator.get());
        }
        return getLocatorChain();
    }

    @Override
    public LocatorChain getLocatorChain() {
        return getParent().getLocatorChain().addLocator(getLocator(ROOT));
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
    public String toString() {
        StringBuilder sb = new StringBuilder();

        /**
         * TODO: Добавлять цепочку полей и классов в чейн билдер или проходить рекурсивно тут?
         *  Нужен путь к пейджобжекту в виде:
         *      PageClass:
         *          FieldClass: fieldName
         *              ElementClass: elementFieldName
         */

        sb.append("\nNames:");
        this.namesRegistry.forEach(name ->
                sb.append("\n    ").append(name));
        sb.append("\nRequired: ").append(required);
        sb.append("\nClass: ").append(this.getClass().getCanonicalName());
        sb.append("\nLocatorChain:");
        sb.append("\n    ").append(getLocatorChain().getLocatorChain().stream()
                .map(LocatorHolder::getShortDescription)
                .collect(Collectors.joining(" -> ")));
        sb.append("\nLocators:");
        this.locatorRegistry.forEach((key, value) ->
                sb.append("\n    Locator: ").append(key).append(" = ").append(value.toString()));
        sb.append("\nElementProperties:");
        this.elementPropertiesRegistry.forEach((key, value) ->
                sb.append("\n    ElementProperty: ").append(key).append(" = ").append(value.toString()));
        sb.append("\nElementStates:");
        this.elementStatesRegistry.forEach((key, value) ->
                sb.append("\n    ElementState: ").append(key).append(" = ").append(value.toString()));
        return sb.toString();
    }

    @Override
    public Set<String> getNames() {
        return namesRegistry.names();
    }

}
