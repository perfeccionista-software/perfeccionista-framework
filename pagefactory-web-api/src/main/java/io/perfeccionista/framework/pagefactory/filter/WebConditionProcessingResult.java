package io.perfeccionista.framework.pagefactory.filter;

import java.util.Set;

public final class WebConditionProcessingResult {

    private final Set<Integer> indexes;
    private final String hash;

    private WebConditionProcessingResult(Set<Integer> indexes, String hash) {
        this.indexes = indexes;
        this.hash = hash;
    }

    public static WebConditionProcessingResult of(Set<Integer> indexes, String hash) {
        return new WebConditionProcessingResult(indexes, hash);
    }

    public Set<Integer> getIndexes() {
        return indexes;
    }

    public String getHash() {
        return hash;
    }

}
