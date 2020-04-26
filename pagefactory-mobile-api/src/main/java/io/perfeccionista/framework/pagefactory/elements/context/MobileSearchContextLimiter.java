package io.perfeccionista.framework.pagefactory.elements.context;

import io.perfeccionista.framework.pagefactory.elements.MobileParentElement;

import java.util.stream.Stream;

public interface MobileSearchContextLimiter extends SearchContextLimiter<MobileParentElement> {

    MobileParentElement getContext();

    @Override
    Stream<MobileParentElement> getContexts();

}
