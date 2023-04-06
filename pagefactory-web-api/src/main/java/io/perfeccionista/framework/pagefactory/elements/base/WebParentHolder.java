package io.perfeccionista.framework.pagefactory.elements.base;

import io.perfeccionista.framework.pagefactory.elements.selectors.WebSelectorChain;
import org.jetbrains.annotations.NotNull;

public interface WebParentHolder {

    @NotNull WebElementBase getParent();

    @NotNull WebSelectorChain getSelectorChain();

}
