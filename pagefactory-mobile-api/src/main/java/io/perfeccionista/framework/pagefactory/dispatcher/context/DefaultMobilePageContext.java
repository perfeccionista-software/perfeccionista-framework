package io.perfeccionista.framework.pagefactory.dispatcher.context;

import io.perfeccionista.framework.Environment;
import io.perfeccionista.framework.exceptions.PageNotInitialized;
import io.perfeccionista.framework.exceptions.mapper.MobileExceptionMapper;
import io.perfeccionista.framework.pagefactory.MobilePageService;
import io.perfeccionista.framework.pagefactory.limiter.MobileContextLimiter;
import io.perfeccionista.framework.pagefactory.dispatcher.MobileDeviceDispatcher;
import io.perfeccionista.framework.pagefactory.elements.MobilePage;
import io.perfeccionista.framework.pagefactory.elements.base.MobileParentElement;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.stream.Stream;

import static io.perfeccionista.framework.exceptions.messages.PageFactoryApiMessages.ACTIVE_PAGE_NOT_INITIALIZED;

public class DefaultMobilePageContext implements MobilePageContext {

    protected final Environment environment;
    protected final MobileDeviceDispatcher dispatcher;
    protected final MobileExceptionMapper exceptionMapper;

    protected final Deque<MobileContextLimiter<?>> contextLimiters = new ArrayDeque<>();

    protected MobilePage activeWebPage = null;

    public DefaultMobilePageContext(@NotNull Environment environment,
                                    @NotNull MobileDeviceDispatcher dispatcher,
                                    @NotNull MobileExceptionMapper exceptionMapper) {
        this.environment = environment;
        this.dispatcher = dispatcher;
        this.exceptionMapper = exceptionMapper;
    }

    @Override
    public <T extends MobileParentElement> MobilePageContext execute(@NotNull Consumer<T> contextConsumer) {
        getContexts(contextLimiters).map(mobileParentElement -> (T) mobileParentElement)
                .forEachOrdered(contextConsumer);
        return this;
    }

    @Override
    public <T extends MobileParentElement> MobilePageContext execute(@NotNull Consumer<T> contextConsumer,
                                                               @NotNull MobileContextLimiter<?>... limiterSequence) {
        Deque<MobileContextLimiter<?>> processedContextLimiters = new ArrayDeque<>(contextLimiters);
        for (MobileContextLimiter<?> limiter : limiterSequence) {
            processedContextLimiters.addLast(limiter);
        }
        getContexts(processedContextLimiters).map(mobileParentElement -> (T) mobileParentElement)
                .forEachOrdered(contextConsumer);
        return this;
    }

    @Override
    public MobilePageContext addContextLimiter(@NotNull MobileContextLimiter<?> limiter) {
        contextLimiters.addLast(limiter);
        return this;
    }

    @Override
    public MobilePageContext removeLastContextLimiter() {
        contextLimiters.removeLast();
        return this;
    }

    @Override
    public MobilePageContext removeContextLimiters() {
        contextLimiters.clear();
        return this;
    }

    @Override
    public <T extends MobilePage> @NotNull T getPage(@NotNull Class<T> pageClass) {
        T pageInstance = environment.getService(MobilePageService.class).getPageInstanceByClass(pageClass);
        pageInstance.setMobileDeviceDispatcher(dispatcher);
        pageInstance.setEnvironment(environment);
        activeWebPage = pageInstance;
        pageInstance.validatePageOpen();
        return pageInstance;
    }

    @Override
    public @NotNull MobilePage getPage(@NotNull String pageName) {
        MobilePage pageInstance = environment.getService(MobilePageService.class).getPageInstanceByName(pageName);
        pageInstance.setMobileDeviceDispatcher(dispatcher);
        pageInstance.setEnvironment(environment);
        activeWebPage = pageInstance;
        pageInstance.validatePageOpen();
        return pageInstance;
    }

    @Override
    public @NotNull MobilePage getActivePage() {
        if (Objects.nonNull(activeWebPage)) {
            activeWebPage.validatePageOpen();
            return activeWebPage;
        }
        throw PageNotInitialized.exception(ACTIVE_PAGE_NOT_INITIALIZED.getMessage());
    }

    @Override
    public MobilePageContext usePage(@NotNull String pageName) {
        getPage(pageName);
        return this;
    }

    @Override
    public MobilePageContext usePage(@NotNull Class<? extends MobilePage> pageClass) {
        getPage(pageClass);
        return this;
    }

    @Override
    public @NotNull String getPageSource() {
        return null;
    }

    protected @NotNull Stream<MobileParentElement> getContexts(Deque<MobileContextLimiter<?>> processedContextLimiters) {
        Stream<MobileParentElement> activeContexts = Stream.of(getActivePage());
        if (processedContextLimiters.isEmpty()) {
            return activeContexts;
        }
        for (MobileContextLimiter<?> processedContextLimiter : processedContextLimiters) {
            activeContexts = (Stream<MobileParentElement>) processedContextLimiter.getContexts(activeContexts);
        }
        return activeContexts;
    }

}
