package io.perfeccionista.framework.pagefactory.elements.actions.base;

import io.perfeccionista.framework.pagefactory.elements.base.WebChildElementBase;
import org.jetbrains.annotations.NotNull;

public interface WebElementActionImplementation<T> {

    T execute(@NotNull WebChildElementBase element, Object... args);

}