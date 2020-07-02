package io.perfeccionista.framework.elements.actions;

import io.appium.java_client.MobileElement;
import io.perfeccionista.framework.exceptions.mapper.AppiumExceptionMapper;
import io.perfeccionista.framework.pagefactory.elements.MobileChildElement;
import io.perfeccionista.framework.pagefactory.elements.actions.MobileElementActionImplementation;

public class AppiumGetText implements MobileElementActionImplementation<String> {

//    @Override
    public String execute(MobileChildElement element, Object... args) {
//        return element.getDriverInstance().getExceptionMapper(AppiumExceptionMapper.class)
//                .map(() -> {
//                    MobileElement tapElement = element.findElement(element.getLocatorChainTo(TEXT)).get();
//                    return tapElement.getText();
//                });
        return null;
    }

}
