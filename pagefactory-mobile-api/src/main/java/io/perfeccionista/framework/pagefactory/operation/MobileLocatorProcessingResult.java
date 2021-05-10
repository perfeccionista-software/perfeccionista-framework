package io.perfeccionista.framework.pagefactory.operation;

import com.fasterxml.jackson.databind.JsonNode;
import io.perfeccionista.framework.json.JsonSerializable;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

import static io.perfeccionista.framework.utils.JsonUtils.createObjectNode;

public class MobileLocatorProcessingResult implements JsonSerializable {

    private final long index;
    private final boolean found;
    private final String hash;
    private final Boolean hashCorrect;

    protected MobileLocatorProcessingResult(long index, boolean found, @Nullable String hash, @Nullable Boolean hashCorrect) {
        this.index = index;
        this.found = found;
        this.hashCorrect = hashCorrect;
        this.hash = hash;
    }

    public static MobileLocatorProcessingResult of(long index, boolean found, @Nullable String hash, @Nullable Boolean hashCorrect) {
        return new MobileLocatorProcessingResult(index, found, hash, hashCorrect);
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

    @Override
    public JsonNode toJson() {
        return createObjectNode()
                .put("index", index)
                .put("found", found)
                .put("hash", hash)
                .put("hashCorrect", hashCorrect);
    }

}
