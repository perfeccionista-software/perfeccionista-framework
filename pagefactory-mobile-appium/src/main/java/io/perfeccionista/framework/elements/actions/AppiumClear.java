package io.perfeccionista.framework.elements.actions;

import io.perfeccionista.framework.exceptions.mapper.AppiumExceptionMapper;
import io.perfeccionista.framework.pagefactory.elements.MobileChildElement;
import io.perfeccionista.framework.pagefactory.elements.actions.MobileElementActionImplementation;

import static io.perfeccionista.framework.pagefactory.elements.components.MobileComponents.INPUT;

public class AppiumClear implements MobileElementActionImplementation<Void> {

//    @Override
    public Void execute(MobileChildElement element, Object... args) {
//        element.getDriverInstance().getExceptionMapper(AppiumExceptionMapper.class)
//                .map(() -> element.findElement(element.getLocatorChainTo(INPUT)).get().clear());
        return null;
    }

}