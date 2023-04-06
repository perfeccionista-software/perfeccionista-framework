package io.perfeccionista.framework.pagefactory.elements.options;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

// TODO: Актуализировать хэндлер
// TODO: Implement properties
public class TypeTextOptions implements ActionOptions {

    private String componentName;

    private TypeTextOptions(String componentName) {
        this.componentName = componentName;
    }

    public static TypeTextOptions typeTextOptions() {
        return new TypeTextOptions(null);
    }

    public TypeTextOptions forComponent(@NotNull String componentName) {
        this.componentName = componentName;
        return this;
    }

    public @Nullable String getComponentName() {
        return componentName;
    }


//    TypeTextOptions append();
//    TypeTextOptions replace();
//    TypeTextOptions withDelayBetweenChars(Duration delay);

}
