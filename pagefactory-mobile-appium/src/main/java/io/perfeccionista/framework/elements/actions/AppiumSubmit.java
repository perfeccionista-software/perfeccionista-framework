package io.perfeccionista.framework.elements.actions;

import io.perfeccionista.framework.exceptions.mapper.AppiumExceptionMapper;
import io.perfeccionista.framework.pagefactory.elements.MobileChildElement;
import io.perfeccionista.framework.pagefactory.elements.actions.MobileElementActionImplementation;

public class AppiumSubmit implements MobileElementActionImplementation<Void> {

    public Void execute(MobileChildElement element, Object... args) {
//        element.getDriverInstance().getExceptionMapper(AppiumExceptionMapper.class).map(() -> {
//            element.findElement(element.getLocatorChainTo(INPUT)).get().submit();
//        });
        return null;
    }

}
