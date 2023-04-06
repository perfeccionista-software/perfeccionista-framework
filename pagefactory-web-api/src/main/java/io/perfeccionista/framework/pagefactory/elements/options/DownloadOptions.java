package io.perfeccionista.framework.pagefactory.elements.options;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

// TODO: implement
// TODO: Актуализировать хэндлер
public class DownloadOptions implements ActionOptions {

    private String componentName;

    private DownloadOptions(String componentName) {
        this.componentName = componentName;
    }

    public static DownloadOptions downloadOptions() {
        return new DownloadOptions(null);
    }

    public DownloadOptions forComponent(@NotNull String componentName) {
        this.componentName = componentName;
        return this;
    }

    public @Nullable String getComponentName() {
        return componentName;
    }

    //    withFilter()
    //    .withTimeout()
    //    .withCheck(FileMatcher)

}
