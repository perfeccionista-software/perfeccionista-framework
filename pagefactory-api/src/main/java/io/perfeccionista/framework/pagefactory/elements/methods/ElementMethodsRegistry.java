package io.perfeccionista.framework.pagefactory.elements.methods;

import java.util.Map;
import java.util.Map.Entry;
import java.util.function.BiConsumer;
import java.util.stream.Stream;

public class ElementMethodsRegistry {

    private final Map<String, ElementMethodImplementation<?>> methods;

    private ElementMethodsRegistry(Map<String, ElementMethodImplementation<?>> methods) {
        this.methods = methods;
    }

    public static ElementMethodsRegistry of(Map<String, ElementMethodImplementation<?>> methods) {
        return new ElementMethodsRegistry(methods);
    }

    public boolean containsElementMethod(String elementMethodName) {
        return methods.containsKey(elementMethodName);
    }

    public <T> ElementMethodImplementation<T> getElementMethod(String elementMethodName, Class<T> returnType) {
        return (ElementMethodImplementation<T>) methods.get(elementMethodName);
    }

    public Stream<Entry<String, ElementMethodImplementation<?>>> stream() {
        return methods.entrySet().stream();
    }

    public void forEach(BiConsumer<String, ElementMethodImplementation<?>> consumer) {
        methods.forEach(consumer);
    }

}
