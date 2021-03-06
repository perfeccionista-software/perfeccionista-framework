package io.perfeccionista.framework.pagefactory.operation.handler;

import com.fasterxml.jackson.databind.JsonNode;
import org.jetbrains.annotations.NotNull;

import static io.perfeccionista.framework.utils.JsonUtils.createObjectNode;

// TODO: В опциях можно передавать координаты или сдвиг для точки клика
public class JsClick implements EndpointHandler<Void> {

    @Override
    public Void handle(Object endpoint) {
        return null;
    }

    @Override
    public @NotNull JsonNode toJson() {
        return createObjectNode()
                .put("name", "perfeccionista.web.js.MouseClickLeftButton")
                .put("script", "js/MouseClickLeftButton.js");
    }

}
