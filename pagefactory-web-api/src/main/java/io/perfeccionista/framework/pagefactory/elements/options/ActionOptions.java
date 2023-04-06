package io.perfeccionista.framework.pagefactory.elements.options;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

// TODO: Реализовать методы toString()
public interface ActionOptions {

    ActionOptions forComponent(@NotNull String componentName);

    /**
     * По умолчанию в ActionOptions не задан компонент и он берется из элемента.
     * Возвращается он отсюда только если задан принудительно пользователем.
     * В этом случае он имеет максимальный приоритет.
     */
    @Nullable String getComponentName();

    default @NotNull String evaluate(@NotNull String inputComponentName) {
        return Objects.nonNull(getComponentName()) ? getComponentName() : inputComponentName;
    }

}
