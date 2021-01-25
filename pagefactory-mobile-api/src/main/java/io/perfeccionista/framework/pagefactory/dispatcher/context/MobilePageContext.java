package io.perfeccionista.framework.pagefactory.dispatcher.context;

import io.perfeccionista.framework.pagefactory.limiter.MobileContextLimiter;
import io.perfeccionista.framework.pagefactory.elements.MobilePage;
import io.perfeccionista.framework.pagefactory.elements.base.MobileParentElement;
import io.perfeccionista.framework.screenshots.Screenshot;
import org.jetbrains.annotations.NotNull;

import java.util.function.Consumer;

public interface MobilePageContext {

    // TODO: Внутри этих методом должен быть LogicWrapper
    <T extends MobileParentElement> MobilePageContext execute(@NotNull Consumer<T> contextConsumer);

    <T extends MobileParentElement> MobilePageContext execute(@NotNull Consumer<T> contextConsumer,
                                                              @NotNull MobileContextLimiter<?>... limiterSequence);


    MobilePageContext addContextLimiter(@NotNull MobileContextLimiter<?> limiter);

    MobilePageContext removeLastContextLimiter();

    MobilePageContext removeContextLimiters();


    <T extends MobilePage> @NotNull T getPage(@NotNull Class<T> pageClass);

    @NotNull MobilePage getPage(@NotNull String pageName);

    @NotNull MobilePage getActivePage();

    MobilePageContext usePage(@NotNull String pageName);

    MobilePageContext usePage(@NotNull Class<? extends MobilePage> pageClass);

    @NotNull String getPageSource();

}
