package io.perfeccionista.framework.pagefactory.operation.type;

import io.perfeccionista.framework.invocation.runner.InvocationInfo;
import io.perfeccionista.framework.measurements.VerticalDirection;
import io.perfeccionista.framework.pagefactory.elements.MobileList;
import io.perfeccionista.framework.pagefactory.filter.list.MobileListFilterBuilder;
import io.perfeccionista.framework.pagefactory.operation.handler.EndpointHandler;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Constructor;

import static io.perfeccionista.framework.pagefactory.elements.ElementActionNames.LIST_SCROLL_TO_VERTICALLY_METHOD;
import static io.perfeccionista.framework.utils.ReflectionUtilsForClasses.getDeclaredConstructor;
import static io.perfeccionista.framework.utils.ReflectionUtilsForClasses.newInstance;

public class MobileListScrollToVerticallyOperationType implements MobileElementOperationType<Void> {

    private final MobileList element;
    private final VerticalDirection scrollDirection;
    private final MobileListFilterBuilder filterBuilder;

    private MobileListScrollToVerticallyOperationType(MobileList element,
                                                        VerticalDirection scrollDirection,
                                                        MobileListFilterBuilder filterBuilder) {
        this.element = element;
        this.scrollDirection = scrollDirection;
        this.filterBuilder = filterBuilder;
    }

    public static MobileListScrollToVerticallyOperationType of(@NotNull MobileList element,
                                                               @NotNull VerticalDirection scrollDirection,
                                                               @NotNull MobileListFilterBuilder filterBuilder) {
        return new MobileListScrollToVerticallyOperationType(element, scrollDirection, filterBuilder);
    }

    @Override
    public @NotNull InvocationInfo getInvocationName() {
        return InvocationInfo.actionInvocation(LIST_SCROLL_TO_VERTICALLY_METHOD, element, scrollDirection, filterBuilder);
    }

    @Override
    public @NotNull EndpointHandler<Void> getEndpointHandler() {
        Class<? extends EndpointHandler<Void>> endpointHandlerClass = element.getEndpointHandler(LIST_SCROLL_TO_VERTICALLY_METHOD, Void.class);
        Constructor<? extends EndpointHandler<Void>> constructor = getDeclaredConstructor(endpointHandlerClass);
        return newInstance(constructor, element, scrollDirection, filterBuilder);
    }

}
