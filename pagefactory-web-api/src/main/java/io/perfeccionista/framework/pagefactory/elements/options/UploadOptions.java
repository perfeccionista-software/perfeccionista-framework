package io.perfeccionista.framework.pagefactory.elements.options;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

// TODO: Актуализировать хэндлер
public class UploadOptions implements ActionOptions {

    private String componentName;

    private UploadOptions(String componentName) {
        this.componentName = componentName;
    }

    public static UploadOptions uploadOptions() {
        return new UploadOptions(null);
    }

    public UploadOptions forComponent(@NotNull String componentName) {
        this.componentName = componentName;
        return this;
    }

    public @Nullable String getComponentName() {
        return componentName;
    }

}
