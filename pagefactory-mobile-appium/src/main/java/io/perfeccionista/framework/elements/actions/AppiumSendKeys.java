package io.perfeccionista.framework.elements.actions;

import io.perfeccionista.framework.exceptions.mapper.AppiumExceptionMapper;
import io.perfeccionista.framework.pagefactory.elements.MobileChildElement;
import io.perfeccionista.framework.pagefactory.elements.actions.MobileElementActionImplementation;


public class AppiumSendKeys implements MobileElementActionImplementation<Void> {

    @Override
    public Void execute(MobileChildElement element, Object... args) {
//        CharSequence[] keysToSend = (CharSequence[]) args[0];
//        element.getDriverInstance().getExceptionMapper(AppiumExceptionMapper.class).map(() -> {
//            element.findElement(element.getLocatorChainTo(INPUT)).get().sendKeys(keysToSend);
//        });
        return null;
    }

}
