package io.perfeccionista.framework.pagefactory.elements.options;

import io.perfeccionista.framework.measurements.Offsets2D;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.time.Duration;

// TODO: Актуализировать хэндлер
public class DoubleClickOptions implements ActionOptions {

    private String componentName;
    private Offsets2D offsets;
    private Duration delayBetweenClicks;
    private Duration delayAfterClick;

    private DoubleClickOptions(String componentName, Offsets2D offsets, Duration delayBetweenClicks, Duration delayAfterClick) {
        this.componentName = componentName;
        this.offsets = offsets;
        this.delayBetweenClicks = delayBetweenClicks;
        this.delayAfterClick = delayAfterClick;
    }

    public static DoubleClickOptions doubleClickOptions() {
        return new DoubleClickOptions(null, Offsets2D.of(0d, 0d), Duration.ZERO, Duration.ZERO);
    }

    public DoubleClickOptions forComponent(@NotNull String componentName) {
        this.componentName = componentName;
        return this;
    }

    public @Nullable String getComponentName() {
        return componentName;
    }

    public DoubleClickOptions withOffset(@NotNull Offsets2D offsets) {
        this.offsets = offsets;
        return this;
    }

    public @NotNull Offsets2D getOffsets() {
        return offsets;
    }

    public DoubleClickOptions withDelayBetweenClicks(@NotNull Duration delayBetweenClicks) {
        this.delayBetweenClicks = delayBetweenClicks;
        return this;
    }

    public @NotNull Duration getDelayBetweenClicks() {
        return this.delayBetweenClicks;
    }

    public DoubleClickOptions withDelayAfterClicks(@NotNull Duration delayAfterClick) {
        this.delayAfterClick = delayAfterClick;
        return this;
    }

    public @NotNull Duration getDelayAfterClick() {
        return this.delayAfterClick;
    }

}
