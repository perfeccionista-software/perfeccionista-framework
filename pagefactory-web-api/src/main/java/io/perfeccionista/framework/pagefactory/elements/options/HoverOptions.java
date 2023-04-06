package io.perfeccionista.framework.pagefactory.elements.options;

import org.jetbrains.annotations.NotNull;

import static io.perfeccionista.framework.pagefactory.elements.ElementComponents.ROOT;

// TODO: Актуализировать хэндлер
public class HoverOptions {

    private String componentName;
    private boolean withOutOfBounds;
    private int xOffset = 0;
    private int yOffset = 0;

    private HoverOptions(String componentName, boolean withOutOfBounds) {
        this.componentName = componentName;
        this.withOutOfBounds = withOutOfBounds;
    }

    public static HoverOptions hoverOptions() {
        return new HoverOptions(ROOT, false);
    }

    public HoverOptions withOutOfBounds() {
        this.withOutOfBounds = true;
        this.xOffset = -50;
        this.yOffset = -50;
        return this;
    }

    public HoverOptions withOutOfBounds(int xOffset, int yOffset) {
        this.withOutOfBounds = true;
        this.xOffset = xOffset;
        this.yOffset = yOffset;
        return this;
    }

    public HoverOptions forComponent(@NotNull String componentName) {
        this.componentName = componentName;
        return this;
    }

    public @NotNull String getComponentName() {
        return componentName;
    }

    public boolean isWithOutOfBounds() {
        return withOutOfBounds;
    }

    public int getxOffset() {
        return xOffset;
    }

    public int getyOffset() {
        return yOffset;
    }

}
