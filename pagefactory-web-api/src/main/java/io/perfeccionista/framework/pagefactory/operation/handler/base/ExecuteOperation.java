package io.perfeccionista.framework.pagefactory.operation.handler.base;

import com.fasterxml.jackson.databind.JsonNode;
import io.perfeccionista.framework.pagefactory.operation.handler.EndpointHandler;
import org.jetbrains.annotations.NotNull;

import static io.perfeccionista.framework.utils.JsonUtils.createObjectNode;

public class ExecuteOperation implements EndpointHandler<Void> {

    @Override
    public Void handle(Object endpoint) {
        return null;
    }

    @Override
    public @NotNull JsonNode toJson() {
        return createObjectNode()
                .put("name", "perfeccionista.js.selenium.ExecuteOperation")
                .put("script", "js/base/ExecuteOperation.js");
    }

}
