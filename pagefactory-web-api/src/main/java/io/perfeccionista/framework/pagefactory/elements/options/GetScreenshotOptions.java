package io.perfeccionista.framework.pagefactory.elements.options;

import org.jetbrains.annotations.NotNull;

import static io.perfeccionista.framework.pagefactory.elements.ElementComponents.ROOT;

// TODO: Актуализировать хэндлер
public class GetScreenshotOptions implements ActionOptions {

    private String componentName;
    private String screenshotType = "png";

    public GetScreenshotOptions(String componentName) {
        this.componentName = componentName;
    }

    public static GetScreenshotOptions screenshotOptions() {
        return new GetScreenshotOptions(ROOT);
    }

    public static GetScreenshotOptions screenshotForComponent(@NotNull String componentName) {
        return new GetScreenshotOptions(componentName);
    }

    @Override
    public GetScreenshotOptions forComponent(@NotNull String componentName) {
        this.componentName = componentName;
        return this;
    }

    public GetScreenshotOptions withTypePng() {
        this.screenshotType = "png";
        return this;
    }

    public GetScreenshotOptions withTypeJpeg() {
        this.screenshotType = "jpeg";
        return this;

    }

    public GetScreenshotOptions withTypeBase64() {
        this.screenshotType = "base64";
        return this;
    }

    @Override
    public @NotNull String getComponentName() {
        return componentName;
    }

    public @NotNull String getScreenshotType() {
        return screenshotType;
    }

}
