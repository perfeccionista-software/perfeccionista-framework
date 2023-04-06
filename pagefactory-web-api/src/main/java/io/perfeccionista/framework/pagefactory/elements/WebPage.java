package io.perfeccionista.framework.pagefactory.elements;

import io.perfeccionista.framework.conditions.WebPageCondition;
import io.perfeccionista.framework.name.WebPageIdentifier;
import io.perfeccionista.framework.pagefactory.dispatcher.WebBrowserDispatcher;
import io.perfeccionista.framework.pagefactory.elements.base.WebParentElement;
import org.jetbrains.annotations.NotNull;

public interface WebPage<T extends WebPage> extends WebParentElement {

    @NotNull WebPageIdentifier getPageIdentifier();

    T setWebBrowserDispatcher(WebBrowserDispatcher webBrowserDispatcher);

    // Asserts
    T should(@NotNull WebPageCondition... conditions);
    T shouldNot(@NotNull WebPageCondition... conditions);

    // Checks
    boolean check(@NotNull WebPageCondition... conditions);
    boolean checkNot(@NotNull WebPageCondition... conditions);

    /**
     * Если необходимо, то переопределяем и делаем необходимую проверку на
     * факт открытия страницы.
     * Если страница не открылась, то нужно кидать processed exception чтобы проверка повторялась
     */
    default WebPage shouldBeOpen() {
        // implement if necessary
        return this;
    }

}
