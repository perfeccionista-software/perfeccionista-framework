package io.perfeccionista.framework.pagefactory.context.base;

import io.perfeccionista.framework.pagefactory.elements.WebPage;
import io.perfeccionista.framework.pagefactory.elements.base.WebParentElement;
import org.jetbrains.annotations.NotNull;

import java.util.function.Consumer;

// TODO: Сделать именованные контексты и возможность переключаться между ними
public interface WebPageContext {

    // TODO: Внутри этих методом должен быть LogicWrapper
    <T extends WebParentElement> WebPageContext execute(@NotNull Consumer<T> contextConsumer);

    <T extends WebParentElement> WebPageContext execute(@NotNull Consumer<T> contextConsumer,
                                                        @NotNull WebContextLimiter<?>... limiterSequence);


    WebPageContext addContextLimiter(@NotNull WebContextLimiter<?> limiter);

    WebPageContext removeLastContextLimiter();

    WebPageContext removeContextLimiters();


    <T extends WebPage> @NotNull T getPage(@NotNull Class<T> pageClass);

    @NotNull WebPage getPage(@NotNull String pageName);

    @NotNull WebPage getActivePage();

    WebPageContext usePage(@NotNull String pageName);

    WebPageContext usePage(@NotNull Class<? extends WebPage> pageClass);

}
