package io.perfeccionista.framework.pagefactory.jsfunction;

import com.fasterxml.jackson.databind.node.ObjectNode;
import io.perfeccionista.framework.measurements.Point;
import org.jetbrains.annotations.NotNull;

import java.time.Duration;
import java.util.function.Function;

import static io.perfeccionista.framework.utils.JsonUtils.createObjectNode;

public class DragAndDrop implements  JsFunction<Void> {

    private final double targetX;
    private final double targetY;
    private final Duration dropDelay;
    private double offsetX = 0;
    private double offsetY = 0;

    public DragAndDrop(@NotNull Point targetCenter) {
        this.targetX = targetCenter.getX();
        this.targetY = targetCenter.getY();
        this.dropDelay = Duration.ofMillis(300);
    }

    public DragAndDrop(@NotNull Point targetCenter, @NotNull Duration dropDelay) {
        this.targetX = targetCenter.getX();
        this.targetY = targetCenter.getY();
        this.dropDelay = dropDelay;
    }

    public DragAndDrop withOffset(double offsetX, double offsetY) {
        this.offsetX = offsetX;
        this.offsetY = offsetY;
        return this;
    }

    @Override
    public Function<Object, Void> getConverter() {
        return object -> null;
    }

    @Override
    public ObjectNode getJsFunctionInvocation() {
        ObjectNode rootNode = createObjectNode()
                .put("name", getScriptName());
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
    public String getScriptName() {
        return "perfeccionista.web.js.DragAndDrop";
    }

    @Override
    public String getScriptDestination() {
        return "js/DragAndDrop.js";
    }

}
