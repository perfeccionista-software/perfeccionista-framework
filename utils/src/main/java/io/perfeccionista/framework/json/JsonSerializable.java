package io.perfeccionista.framework.json;

import com.fasterxml.jackson.databind.JsonNode;
import org.jetbrains.annotations.NotNull;

public interface JsonSerializable {

    @NotNull JsonNode toJson();

    @NotNull default String toFormattedString() {
        return toJson().toPrettyString();
    }

}
