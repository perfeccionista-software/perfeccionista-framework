package io.perfeccionista.framework.pagefactory.elements.methods;

import io.appium.java_client.MobileElement;
import io.perfeccionista.framework.exceptions.mapper.AppiumExceptionMapper;
import io.perfeccionista.framework.pagefactory.elements.MobileChildElement;
import io.perfeccionista.framework.pagefactory.operations.OperationResult;

public class AppiumGetMobileElement implements MobileElementMethodImplementation<MobileElement> {

    @Override
    public MobileElement execute(MobileChildElement element, Object... args) {
        return element.getDriverInstance().getExceptionMapper(AppiumExceptionMapper.class)
                .map(() -> element.findElement(element.getLocatorChain()).get());
    }

}

