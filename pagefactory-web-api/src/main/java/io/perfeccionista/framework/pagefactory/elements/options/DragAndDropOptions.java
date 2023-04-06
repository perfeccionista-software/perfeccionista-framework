package io.perfeccionista.framework.pagefactory.elements.options;

import io.perfeccionista.framework.measurements.Offsets2D;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.time.Duration;

import static io.perfeccionista.framework.pagefactory.elements.ElementComponents.ROOT;

// TODO: Актуализировать хэндлер
public class DragAndDropOptions implements ActionOptions {

    private String componentName;
    private String targetComponentName;
    private Offsets2D targetOffset;
    private Duration delayBeforeDrop;

    private DragAndDropOptions(String componentName, String targetComponentName, Offsets2D targetOffset, Duration delayBeforeDrop) {
        this.componentName = componentName;
        this.targetComponentName = targetComponentName;
        this.targetOffset = targetOffset;
        this.delayBeforeDrop = delayBeforeDrop;
    }

    public static DragAndDropOptions dragAndDropOptions() {
        return new DragAndDropOptions(null, ROOT, Offsets2D.of(0, 0), Duration.ZERO);
    }

    @Override
    public DragAndDropOptions forComponent(@NotNull String componentName) {
        this.componentName = componentName;
        return this;
    }

    @Override
    public @Nullable String getComponentName() {
        return componentName;
    }

    public DragAndDropOptions forTargetComponentName(@NotNull String targetComponentName) {
        this.targetComponentName = targetComponentName;
        return this;
    }

    public @NotNull String getTargetComponentName() {
        return targetComponentName;
    }

    public DragAndDropOptions withTargetOffset(@NotNull Offsets2D targetOffset) {
        this.targetOffset = targetOffset;
        return this;
    }

    public @NotNull Offsets2D getTargetOffset() {
        return targetOffset;
    }

    public DragAndDropOptions withDelayBeforeDrop(@NotNull Duration delayBeforeDrop) {
        this.delayBeforeDrop = delayBeforeDrop;
        return this;
    }

    public @NotNull Duration getDelayBeforeDrop() {
        return delayBeforeDrop;
    }

}
