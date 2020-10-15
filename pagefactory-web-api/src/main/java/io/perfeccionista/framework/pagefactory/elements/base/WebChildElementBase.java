package io.perfeccionista.framework.pagefactory.elements.base;

import io.perfeccionista.framework.name.WebElementIdentifier;
import io.perfeccionista.framework.pagefactory.elements.actions.base.WebElementActionImplementation;
import io.perfeccionista.framework.pagefactory.elements.actions.base.WebElementJsOperationActionImplementation;
import io.perfeccionista.framework.pagefactory.elements.locators.WebLocatorHolder;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;

public interface WebChildElementBase extends WebElementBase, WebParentHolderAvailable {

    @NotNull WebLocatorHolder getRequiredLocator(@NotNull String componentName);

    Optional<WebLocatorHolder> getOptionalLocator(@NotNull String componentName);

    @NotNull <R> WebElementJsOperationActionImplementation<R> getJsOperationActionImplementation(@NotNull String actionName, @NotNull Class<R> returnType);

    @NotNull <R> WebElementActionImplementation<R> getActionImplementation(@NotNull String actionName, @NotNull Class<R> returnType);

    WebChildElementBase executeAction(@NotNull String name, Object... args);

    @NotNull WebElementIdentifier getElementIdentifier();

}
