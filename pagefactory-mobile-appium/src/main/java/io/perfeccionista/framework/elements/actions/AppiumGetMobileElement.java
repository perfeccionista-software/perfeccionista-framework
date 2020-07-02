package io.perfeccionista.framework.elements.actions;

import io.appium.java_client.MobileElement;
import io.perfeccionista.framework.exceptions.mapper.AppiumExceptionMapper;
import io.perfeccionista.framework.pagefactory.elements.MobileChildElement;
import io.perfeccionista.framework.pagefactory.elements.actions.MobileElementActionImplementation;

public class AppiumGetMobileElement implements MobileElementActionImplementation<MobileElement> {

//    @Override
    public MobileElement execute(MobileChildElement element, Object... args) {
//        return element.getDriverInstance().getExceptionMapper(AppiumExceptionMapper.class)
//                .map(() -> element.findElement(element.getLocatorChain()).get());
        return null;
    }

}

