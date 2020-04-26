package io.perfeccionista.framework.pagefactory.browser.context;

import io.perfeccionista.framework.exceptions.SearchContextException;
import io.perfeccionista.framework.pagefactory.elements.WebPage;
import io.perfeccionista.framework.pagefactory.elements.WebParentElement;
import io.perfeccionista.framework.pagefactory.elements.context.WebSearchContextLimiter;

import java.util.stream.Stream;

import static io.perfeccionista.framework.exceptions.messages.PageFactoryWebApiMessages.CONTEXT_LIMITER_RETURN_MORE_THAN_ONE_SEARCH_CONTEXT;
import static io.perfeccionista.framework.exceptions.messages.PageFactoryWebApiMessages.CONTEXT_LIMITER_RETURN_NO_ONE_SEARCH_CONTEXT;

// TODO: Сделать именованные контексты и возможность переключаться между ними
public interface WebPageContext {

    <T extends WebParentElement> Stream<T> getSearchContexts(Class<T> contextBlockClass);

    // TODO: Базовый метод для остальных
    // Возвращает всегда актуальный элемент с индексом блока и хэшем, который рассчитывается при первом обращении к контексту
    // Если дека с лимитерами пустая, то возвращаем экземпляр страницы
    // return Stream.of(getActivePage());
    Stream<WebParentElement> getSearchContexts();

    default <T extends WebParentElement> T getSearchContext(Class<T> contextBlockClass) {
        // TODO: Сделать проверку соответствия типов
        return (T) getSearchContext();
    }

    // TODO: Добавить к ошибке описание текущего ограничения контекста
    default WebParentElement getSearchContext() {
        Stream<WebParentElement> contexts = getSearchContexts();
        if (contexts.count() > 1) {
            throw new SearchContextException(CONTEXT_LIMITER_RETURN_MORE_THAN_ONE_SEARCH_CONTEXT.getMessage());
        }
        return contexts
                .findFirst()
                .orElseThrow(() -> new SearchContextException(CONTEXT_LIMITER_RETURN_NO_ONE_SEARCH_CONTEXT.getMessage()));
    }

    WebPageContext resetSearchContextCache();

    <T extends WebParentElement> WebPageContext setLimiter(WebSearchContextLimiter<T> limiter);

    WebPageContext removeLimiters();

    WebPageContext usePage(String pageName);

    WebPageContext usePage(Class<? extends WebPage> pageClass);

    <T extends WebPage> T getPage(Class<T> pageClass);

    WebPage getPage(String pageName);

    WebPage getActivePage();

}
