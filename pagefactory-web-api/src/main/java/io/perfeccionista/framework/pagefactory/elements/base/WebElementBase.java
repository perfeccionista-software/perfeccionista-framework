package io.perfeccionista.framework.pagefactory.elements.base;

import io.perfeccionista.framework.EnvironmentAvailable;
import io.perfeccionista.framework.json.JsonSerializable;
import io.perfeccionista.framework.pagefactory.browser.WebBrowserDispatcher;

public interface WebElementBase extends EnvironmentAvailable, JsonSerializable, WebLocatorChainAvailable {

    WebBrowserDispatcher getWebBrowserDispatcher();

}
