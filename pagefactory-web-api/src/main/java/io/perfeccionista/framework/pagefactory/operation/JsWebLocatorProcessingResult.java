package io.perfeccionista.framework.pagefactory.operation;

import org.jetbrains.annotations.Nullable;

import java.util.Optional;

public class JsWebLocatorProcessingResult {

    private final long index;
    private final boolean found;
    private final String hash;
    private final Boolean hashCorrect;

    protected JsWebLocatorProcessingResult(long index, boolean found, @Nullable String hash, @Nullable Boolean hashCorrect) {
        this.index = index;
        this.found = found;
        this.hashCorrect = hashCorrect;
        this.hash = hash;
    }

    public static JsWebLocatorProcessingResult of(long index, boolean found, @Nullable String hash, @Nullable Boolean hashCorrect) {
        return new JsWebLocatorProcessingResult(index, found, hash, hashCorrect);
    }

    public long getIndex() {
        return index;
    }

    public boolean isFound() {
        return found;
    }

    public Optional<String> getHash() {
        return Optional.ofNullable(hash);
    }

    public Optional<Boolean> getHashCorrect() {
        return Optional.ofNullable(hashCorrect);
    }

}
