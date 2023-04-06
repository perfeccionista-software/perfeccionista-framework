package io.perfeccionista.framework.pagefactory.elements.base;

import io.perfeccionista.framework.json.JsonSerializable;
import io.perfeccionista.framework.pagefactory.dispatcher.WebBrowserDispatcher;

public interface WebElementBase extends JsonSerializable, WebSelectorChainAvailable {

    WebBrowserDispatcher getWebBrowserDispatcher();

}
