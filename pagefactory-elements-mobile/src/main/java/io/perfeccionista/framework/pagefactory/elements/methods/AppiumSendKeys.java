package io.perfeccionista.framework.pagefactory.elements.methods;

import io.perfeccionista.framework.exceptions.mapper.AppiumExceptionMapper;
import io.perfeccionista.framework.pagefactory.elements.MobileChildElement;
import io.perfeccionista.framework.pagefactory.operations.OperationResult;

import static io.perfeccionista.framework.pagefactory.elements.locators.Components.INPUT;

public class AppiumSendKeys implements MobileElementMethodImplementation<Void> {

    @Override
    public OperationResult<Void> execute(MobileChildElement element, Object... args) {
        CharSequence[] keysToSend = (CharSequence[]) args[0];
        return OperationResult.of(() -> element.getDriverInstance().getExceptionMapper(AppiumExceptionMapper.class).map(() -> {
            element.findElement(element.getLocatorChainTo(INPUT)).getItem().sendKeys(keysToSend);
        }));
    }

}
