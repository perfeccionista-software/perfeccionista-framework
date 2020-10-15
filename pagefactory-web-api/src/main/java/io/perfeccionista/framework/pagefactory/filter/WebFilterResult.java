package io.perfeccionista.framework.pagefactory.filter;

import org.jetbrains.annotations.NotNull;

import java.util.HashSet;
import java.util.Set;

// TODO: Хранить тут же элемент, чтобы в экстракторе не передавать его отдельно
public final class WebFilterResult {

    private final Set<Integer> indexes;
    private final String hash;

    private WebFilterResult(Set<Integer> indexes, String hash) {
        this.indexes = indexes;
        this.hash = hash;
    }

    public static WebFilterResult of(@NotNull Set<Integer> indexes, @NotNull String hash) {
        return new WebFilterResult(indexes, hash);
    }

    public @NotNull Set<Integer> getIndexes() {
        return new HashSet<>(indexes);
    }

    public @NotNull String getHash() {
        return hash;
    }

    public int getSize() {
        return indexes.size();
    }

}
