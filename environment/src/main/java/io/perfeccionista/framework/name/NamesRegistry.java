package io.perfeccionista.framework.name;

import java.util.Set;
import java.util.function.Consumer;
import java.util.stream.Stream;

public class NamesRegistry {

    private Set<String> names;

    private NamesRegistry(Set<String> names) {
        this.names = names;
    }

    public static NamesRegistry of(Set<String> names) {
        return new NamesRegistry(names);
    }

    public boolean contains(String name) {
        return names.contains(name);
    }

    public Set<String> names() {
        return Set.copyOf(names);
    }

    public Stream<String> stream() {
        return names.stream();
    }

    public void forEach(Consumer<String> consumer) {
        names.forEach(consumer);
    }

}
