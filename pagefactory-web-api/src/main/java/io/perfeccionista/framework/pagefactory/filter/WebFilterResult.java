package io.perfeccionista.framework.pagefactory.filter;

import java.util.HashSet;
import java.util.Set;

public final class WebFilterResult {

    private final Set<Integer> indexes;
    private final String hash;

    private WebFilterResult(Set<Integer> indexes, String hash) {
        this.indexes = indexes;
        this.hash = hash;
    }

    public static WebFilterResult of(Set<Integer> indexes, String hash) {
        return new WebFilterResult(indexes, hash);
    }

    public Set<Integer> getIndexes() {
        return new HashSet<>(indexes);
    }

    public String getHash() {
        return hash;
    }

}
