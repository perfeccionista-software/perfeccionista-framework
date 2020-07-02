package io.perfeccionista.framework.pagefactory.elements;

import io.perfeccionista.framework.Environment;
import io.perfeccionista.framework.name.WebPageIdentifier;
import io.perfeccionista.framework.pagefactory.browser.WebBrowserDispatcher;
import io.perfeccionista.framework.pagefactory.elements.base.WebParentElement;

public interface WebPage extends WebParentElement {

    WebPage setWebBrowserDispatcher(WebBrowserDispatcher webBrowserDispatcher);

    WebPage setEnvironment(Environment environment);

    WebPageIdentifier getPageIdentifier();

    /**
     * Если необходимо, то переопределяем и делаем необходимую проверку на
     * факт открытия страницы.
     * Если страница не открылась, то нужно кидать processed exception
     */
    default void validatePageOpen() {}

}
