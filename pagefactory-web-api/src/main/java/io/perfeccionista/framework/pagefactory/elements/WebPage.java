package io.perfeccionista.framework.pagefactory.elements;

import io.perfeccionista.framework.Environment;
import io.perfeccionista.framework.matcher.element.WebPageMatcher;
import io.perfeccionista.framework.name.WebPageIdentifier;
import io.perfeccionista.framework.pagefactory.browser.WebBrowserDispatcher;
import io.perfeccionista.framework.pagefactory.elements.base.WebParentElement;
import org.jetbrains.annotations.NotNull;

public interface WebPage extends WebParentElement {

    @NotNull WebPageIdentifier getPageIdentifier();

    WebPage setWebBrowserDispatcher(WebBrowserDispatcher webBrowserDispatcher);

    WebPage setEnvironment(Environment environment);

    WebPage should(@NotNull WebPageMatcher matcher);

    /**
     * Если необходимо, то переопределяем и делаем необходимую проверку на
     * факт открытия страницы.
     * Если страница не открылась, то нужно кидать processed exception чтобы проверка повторялась
     */
    default void validatePageOpen() {}

}