package io.perfeccionista.framework.pagefactory.elements.options;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

// TODO: Актуализировать хэндлер
public class GetTextOptions implements ActionOptions {

    private String componentName;
    private TextContentType textContentType;

    private GetTextOptions(String componentName, TextContentType textContentType) {
        this.componentName = componentName;
        this.textContentType = textContentType;
    }

    public static GetTextOptions getTextOptions() {
        return new GetTextOptions(null, TextContentType.INNER_TEXT);
    }

    public static GetTextOptions getTextForComponent(@NotNull String componentName) {
        return new GetTextOptions(componentName, TextContentType.INNER_TEXT);
    }

    public GetTextOptions forComponent(@NotNull String componentName) {
        this.componentName = componentName;
        return this;
    }

    public @Nullable String getComponentName() {
        return componentName;
    }

    public GetTextOptions useTextContent() {
        this.textContentType = TextContentType.TEXT_CONTENT;
        return this;
    }

    public GetTextOptions useInnerText() {
        this.textContentType = TextContentType.INNER_TEXT;
        return this;
    }

    public @NotNull TextContentType getTextContentType() {
        return textContentType;
    }

    public enum TextContentType {
        INNER_TEXT,
        TEXT_CONTENT
    }

}
