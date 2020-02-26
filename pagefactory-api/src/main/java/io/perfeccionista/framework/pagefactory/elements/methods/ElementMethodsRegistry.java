package io.perfeccionista.framework.pagefactory.elements.methods;

import java.util.Map;
import java.util.Map.Entry;
import java.util.function.BiConsumer;
import java.util.stream.Stream;

public class ElementMethodsRegistry<T extends ElementMethodImplementation<?, ?>> {

    private final Map<String, T> methods;

    private ElementMethodsRegistry(Map<String, T> methods) {
        this.methods = methods;
    }

    public static <T extends ElementMethodImplementation<?, ?>> ElementMethodsRegistry<T> of(Map<String, T> methods) {
        return new ElementMethodsRegistry<>(methods);
    }

    public boolean containsElementMethod(String elementMethodName) {
        return methods.containsKey(elementMethodName);
    }

    public <R, T extends ElementMethodImplementation<?, R>> T getElementMethod(String elementMethodName, Class<R> returnType) {
        return (T) methods.get(elementMethodName);
    }

    public Stream<Entry<String, T>> stream() {
        return methods.entrySet().stream();
    }

    public void forEach(BiConsumer<String, T> consumer) {
        methods.forEach(consumer);
    }

}
