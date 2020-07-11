package io.perfeccionista.framework.pagefactory.elements.base;

import io.perfeccionista.framework.asserts.WebAssertCondition;
import io.perfeccionista.framework.invocation.runner.InvocationName;
import io.perfeccionista.framework.name.WebElementIdentifier;
import io.perfeccionista.framework.pagefactory.browser.WebBrowserDispatcher;
import io.perfeccionista.framework.pagefactory.elements.actions.WebElementActionImplementation;
import io.perfeccionista.framework.pagefactory.elements.methods.WebComponentAvailable;
import io.perfeccionista.framework.pagefactory.elements.interactions.WebElementInteractionImplementation;
import io.perfeccionista.framework.pagefactory.elements.methods.GetColorAvailable;
import io.perfeccionista.framework.pagefactory.elements.methods.GetDimensionsAvailable;
import io.perfeccionista.framework.pagefactory.elements.methods.GetLocationAvailable;
import io.perfeccionista.framework.pagefactory.elements.methods.GetScreenshotAvailable;
import io.perfeccionista.framework.pagefactory.elements.methods.HoverToAvailable;
import io.perfeccionista.framework.pagefactory.elements.methods.IsDisplayedAvailable;
import io.perfeccionista.framework.pagefactory.elements.methods.IsInFocusAvailable;
import io.perfeccionista.framework.pagefactory.elements.methods.IsPresentAvailable;
import io.perfeccionista.framework.pagefactory.elements.methods.ScrollToAvailable;
import io.perfeccionista.framework.pagefactory.elements.properties.WebElementPropertyAvailable;
import io.perfeccionista.framework.pagefactory.elements.properties.WebElementPropertyHolder;
import org.jetbrains.annotations.NotNull;

public interface WebChildElement extends ElementBase, WebLocatorChainAvailable,
        IsPresentAvailable, IsDisplayedAvailable,
        HoverToAvailable, ScrollToAvailable, IsInFocusAvailable,
        GetDimensionsAvailable, GetLocationAvailable, GetScreenshotAvailable, GetColorAvailable,
        WebComponentAvailable, WebElementPropertyAvailable<WebElementPropertyHolder> {

    @NotNull <R> WebElementActionImplementation<R> getActionImplementation(@NotNull String actionName, @NotNull Class<R> returnType);

    WebChildElement executeAction(@NotNull String name, Object... args);

    @NotNull WebElementInteractionImplementation getInteractionImplementation(@NotNull String interactionName);

    WebChildElement executeInteraction(@NotNull String name, @NotNull WebChildElement other, Object... args);

    WebChildElement should(WebAssertCondition assertCondition);

    WebChildElement should(WebAssertCondition assertCondition, InvocationName invocationName);

    WebBrowserDispatcher getWebBrowserDispatcher();

    @NotNull WebElementIdentifier getElementIdentifier();

    WebParentElement getParent();

}
