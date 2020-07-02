package io.perfeccionista.framework.name;

import io.perfeccionista.framework.pagefactory.elements.WebPage;

import java.util.HashSet;
import java.util.Set;
import java.util.function.Consumer;
import java.util.stream.Stream;

public class WebPageIdentifier {

    private final Set<String> names;
    private final Set<String> urls;
    private String lastUsedName = null;

    private WebPageIdentifier(Set<String> names, Set<String> urls) {
        this.names = names;
        this.urls = urls;
    }

    public static WebPageIdentifier of(Set<String> names, Set<String> urls) {
        return new WebPageIdentifier(names, urls);
    }

    public static WebPageIdentifier of(Class<? extends WebPage> pageClass) {
        Set<String> names = new HashSet<>();
        Set<String> urls = new HashSet<>();

        // TODO: Implement

        return new WebPageIdentifier(names, urls);
    }

    public void setLastUsedName(String lastUsedName) {
        this.lastUsedName = lastUsedName;
    }

    public String getLastUsedName() {
        return lastUsedName == null ? names.stream().findFirst().orElse("undefined") : lastUsedName;
    }

    public boolean containsName(String name) {
        return names.contains(name);
    }

    public Set<String> names() {
        return Set.copyOf(names);
    }

    public Stream<String> namesStream() {
        return names.stream();
    }

    public void forEachName(Consumer<String> consumer) {
        names.forEach(consumer);
    }

    public Set<String> urls() {
        return Set.copyOf(urls);
    }

    public Stream<String> urlsStream() {
        return urls.stream();
    }

    public void forEachUrl(Consumer<String> consumer) {
        urls.forEach(consumer);
    }

}
