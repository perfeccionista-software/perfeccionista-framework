package io.perfeccionista.framework.pagefactory.elements.options;

import io.perfeccionista.framework.measurements.Offsets2D;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.time.Duration;

// TODO: Актуализировать хэндлер
public class ContextClickOptions implements ActionOptions {

    private String componentName;
    private Offsets2D offsets;
    private Duration delayAfterClick;

    private ContextClickOptions(String componentName, Offsets2D offsets, Duration delayAfterClick) {
        this.componentName = componentName;
        this.offsets = offsets;
        this.delayAfterClick = delayAfterClick;
    }

    public static ContextClickOptions contextClickOptions() {
        return new ContextClickOptions(null, Offsets2D.of(0d, 0d), Duration.ZERO);
    }

    public ContextClickOptions forComponent(@NotNull String componentName) {
        this.componentName = componentName;
        return this;
    }

    public @Nullable String getComponentName() {
        return componentName;
    }

    public ContextClickOptions withOffset(@NotNull Offsets2D offsets) {
        this.offsets = offsets;
        return this;
    }

    public @NotNull Offsets2D getOffsets() {
        return offsets;
    }

    public ContextClickOptions withDelayAfterClick(@NotNull Duration delayAfterClick) {
        this.delayAfterClick = delayAfterClick;
        return this;
    }

    public @NotNull Duration getDelayAfterClick() {
        return this.delayAfterClick;
    }

}
