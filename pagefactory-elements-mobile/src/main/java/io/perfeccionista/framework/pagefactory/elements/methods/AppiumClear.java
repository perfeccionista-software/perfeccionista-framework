package io.perfeccionista.framework.pagefactory.elements.methods;

import io.perfeccionista.framework.exceptions.mapper.AppiumExceptionMapper;
import io.perfeccionista.framework.pagefactory.elements.MobileChildElement;
import io.perfeccionista.framework.pagefactory.operations.OperationResult;

import static io.perfeccionista.framework.pagefactory.elements.locators.Components.INPUT;

public class AppiumClear implements MobileElementMethodImplementation<Void> {

    @Override
    public Void execute(MobileChildElement element, Object... args) {
        element.getDriverInstance().getExceptionMapper(AppiumExceptionMapper.class)
                .map(() -> element.findElement(element.getLocatorChainTo(INPUT)).get().clear());
        return null;
    }

}