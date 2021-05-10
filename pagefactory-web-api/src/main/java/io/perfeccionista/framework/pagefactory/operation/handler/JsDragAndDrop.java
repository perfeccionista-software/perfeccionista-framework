package io.perfeccionista.framework.pagefactory.operation.handler;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.perfeccionista.framework.measurements.Point2D;
import org.jetbrains.annotations.NotNull;

import java.time.Duration;

import static io.perfeccionista.framework.utils.JsonUtils.createObjectNode;

public class JsDragAndDrop implements EndpointHandler<Void> {

    private final double targetX;
    private final double targetY;
    private Duration dropDelay;
    private double offsetX = 0;
    private double offsetY = 0;

    public JsDragAndDrop(@NotNull Point2D targetCenter) {
        this.targetX = targetCenter.getX();
        this.targetY = targetCenter.getY();
        this.dropDelay = Duration.ofMillis(300);
    }

    public JsDragAndDrop withDelay(@NotNull Duration dropDelay) {
        this.dropDelay = dropDelay;
        return this;
    }

    public JsDragAndDrop withOffset(double offsetX, double offsetY) {
        this.offsetX = offsetX;
        this.offsetY = offsetY;
        return this;
    }

    @Override
    public @NotNull JsonNode toJson() {
        ObjectNode rootNode = createObjectNode()
                .put("name", "perfeccionista.web.js.DragAndDrop")
                .put("script", "js/DragAndDrop.js");
        ObjectNode options = createObjectNode()
                .put("targetX", targetX)
                .put("targetY", targetY)
                .put("offsetX", offsetX)
                .put("offsetY", offsetY)
                .put("dropDelay", dropDelay.toMillis());
        rootNode.set("options", options);
        return rootNode;
    }

    @Override
    public Void handle(Object endpoint) {
        return null;
    }

}
