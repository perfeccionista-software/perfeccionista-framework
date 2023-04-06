package io.perfeccionista.framework.pagefactory.elements.options;

import org.jetbrains.annotations.NotNull;

import static io.perfeccionista.framework.pagefactory.elements.ElementComponents.ROOT;

// TODO: Актуализировать хэндлер
public class OnTheScreenOptions implements ActionOptions {

    private String componentName;

    public OnTheScreenOptions(String componentName) {
        this.componentName = componentName;
    }

    public static OnTheScreenOptions onTheScreenOptions() {
        return new OnTheScreenOptions(ROOT);
    }

    public static OnTheScreenOptions onTheScreenForComponent(@NotNull String componentName) {
        return new OnTheScreenOptions(componentName);
    }

    @Override
    public OnTheScreenOptions forComponent(@NotNull String componentName) {
        this.componentName = componentName;
        return this;
    }

    @Override
    public @NotNull String getComponentName() {
        return componentName;
    }

}
