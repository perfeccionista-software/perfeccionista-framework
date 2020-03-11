package io.perfeccionista.framework.pagefactory.elements.methods;

import io.appium.java_client.MobileElement;
import io.perfeccionista.framework.exceptions.mapper.AppiumExceptionMapper;
import io.perfeccionista.framework.pagefactory.elements.MobileChildElement;
import io.perfeccionista.framework.pagefactory.operations.OperationResult;

import static io.perfeccionista.framework.pagefactory.elements.locators.Components.TEXT;

public class AppiumGetText implements MobileElementMethodImplementation<String> {

    @Override
    public OperationResult<String> execute(MobileChildElement element, Object... args) {
        return OperationResult.of(() -> element.getDriverInstance().getExceptionMapper(AppiumExceptionMapper.class)
                .map(() -> {
                    MobileElement tapElement = element.findElement(element.getLocatorChainTo(TEXT)).getItem();
                    return tapElement.getText();
                }));
    }

}
