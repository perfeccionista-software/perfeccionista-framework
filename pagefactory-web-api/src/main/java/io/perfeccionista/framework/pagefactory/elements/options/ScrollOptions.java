package io.perfeccionista.framework.pagefactory.elements.options;

import org.jetbrains.annotations.NotNull;

import static io.perfeccionista.framework.pagefactory.elements.ElementComponents.ROOT;

// TODO: Актуализировать хэндлер
public class ScrollOptions implements ActionOptions {

    private String componentName;
    private String parentNode;

    private ScrollOptions(String componentName, String parentNode) {
        this.componentName = componentName;
        this.parentNode = parentNode;
    }

    public static ScrollOptions scrollOptions() {
        return new ScrollOptions(ROOT, "document.documentElement");
    }

    @Override
    public ScrollOptions forComponent(@NotNull String componentName) {
        this.componentName = componentName;
        return this;
    }

    public ScrollOptions withParentNode(@NotNull String parentNode) {
        this.parentNode = parentNode;
        return this;
    }

    @Override
    public @NotNull String getComponentName() {
        return componentName;
    }

    public String getParentNode() {
        return parentNode;
    }

}
