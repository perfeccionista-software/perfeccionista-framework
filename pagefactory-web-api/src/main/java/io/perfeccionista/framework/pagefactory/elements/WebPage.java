package io.perfeccionista.framework.pagefactory.elements;

import io.perfeccionista.framework.Environment;
import io.perfeccionista.framework.matcher.element.WebPageMatcher;
import io.perfeccionista.framework.measurements.HorizontalDirection;
import io.perfeccionista.framework.measurements.VerticalDirection;
import io.perfeccionista.framework.name.WebPageIdentifier;
import io.perfeccionista.framework.pagefactory.dispatcher.WebBrowserDispatcher;
import io.perfeccionista.framework.pagefactory.elements.base.WebChildElement;
import io.perfeccionista.framework.pagefactory.elements.base.WebParentElement;
import org.jetbrains.annotations.NotNull;

public interface WebPage extends WebParentElement {

    @NotNull WebPageIdentifier getPageIdentifier();

    WebPage setWebBrowserDispatcher(WebBrowserDispatcher webBrowserDispatcher);

    WebPage setEnvironment(Environment environment);

    WebPage should(@NotNull WebPageMatcher matcher);

//    @Override
//    WebPage scrollToHorizontally(@NotNull HorizontalDirection scrollDirection, @NotNull WebChildElement childElement);
//    @Override
//    WebPage scrollToVertically(@NotNull VerticalDirection scrollDirection, @NotNull WebChildElement childElement);

    /**
     * Если необходимо, то переопределяем и делаем необходимую проверку на
     * факт открытия страницы.
     * Если страница не открылась, то нужно кидать processed exception чтобы проверка повторялась
     */
    default void validatePageOpen() {}

}
