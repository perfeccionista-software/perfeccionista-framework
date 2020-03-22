package io.perfeccionista.framework.pagefactory.elements.methods;

import io.appium.java_client.MobileElement;
import io.perfeccionista.framework.exceptions.mapper.AppiumExceptionMapper;
import io.perfeccionista.framework.pagefactory.elements.MobileChildElement;
import io.perfeccionista.framework.pagefactory.operations.OperationResult;

import static io.perfeccionista.framework.pagefactory.elements.locators.Components.LABEL;

public class AppiumGetLabel implements MobileElementMethodImplementation<String> {

    @Override
    public String execute(MobileChildElement element, Object... args) {
        return element.getDriverInstance().getExceptionMapper(AppiumExceptionMapper.class)
                .map(() -> {
                    MobileElement tapElement = element.findElement(element.getLocatorChainTo(LABEL)).get();
                    return tapElement.getText();
                });
    }

}
