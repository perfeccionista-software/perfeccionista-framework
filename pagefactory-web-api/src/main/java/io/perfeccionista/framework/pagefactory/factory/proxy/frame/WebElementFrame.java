package io.perfeccionista.framework.pagefactory.factory.proxy.frame;

import io.perfeccionista.framework.json.JsonSerializable;
import org.jetbrains.annotations.NotNull;

public interface WebElementFrame<T> extends JsonSerializable {

    @NotNull Class<? extends T> getElementClass();

}
