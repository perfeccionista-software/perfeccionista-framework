package io.perfeccionista.framework.pagefactory.elements.base;

import io.perfeccionista.framework.name.WebElementIdentifier;
import io.perfeccionista.framework.pagefactory.elements.selectors.WebSelectorHolder;
import io.perfeccionista.framework.pagefactory.emulator.keys.Key;
import io.perfeccionista.framework.pagefactory.operation.handler.EndpointHandler;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;

public interface WebChildElementBase extends WebElementBase, WebParentHolderAvailable {

    @NotNull WebElementIdentifier getElementIdentifier();

    @NotNull WebSelectorHolder getRequiredSelector(@NotNull String componentName);

    Optional<WebSelectorHolder> getOptionalSelector(@NotNull String componentName);

    @NotNull <R> Class<? extends EndpointHandler<R>> getEndpointHandler(@NotNull String actionName, @NotNull Class<R> returnType);

    WebChildElementBase executeAction(@NotNull String name, Object... args);

    WebChildElementBase press(@NotNull Key key);

    @NotNull String getInnerHtml();

    @NotNull String getOuterHtml();

    WebChildElementBase addName(@NotNull String elementName);

    WebChildElementBase addComponent(@NotNull String componentName, @NotNull WebSelectorHolder selector);

}
