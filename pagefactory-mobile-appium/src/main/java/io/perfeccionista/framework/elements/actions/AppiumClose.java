package io.perfeccionista.framework.elements.actions;

import io.perfeccionista.framework.exceptions.mapper.AppiumExceptionMapper;
import io.perfeccionista.framework.pagefactory.elements.MobileChildElement;
import io.perfeccionista.framework.pagefactory.elements.MobileDropDownList;
import io.perfeccionista.framework.pagefactory.elements.actions.MobileElementActionImplementation;


public class AppiumClose implements MobileElementActionImplementation<Void> {

//    @Override
    public Void execute(MobileChildElement element, Object... args) {
//        MobileDropDownList dropDownList = (MobileDropDownList) element;
//        element.getDriverInstance().getExceptionMapper(AppiumExceptionMapper.class)
//                .map(() -> {
//                    boolean isOpenResult = dropDownList.isOpen();
//                    if (isOpenResult) {
//                        MobileElement tapElement = element.findElement(element.getLocatorChainTo(CLOSE)).get();
//                        TouchActions action = new TouchActions(element.getDriverInstance().getDriver()).singleTap(tapElement);
//                        action.perform();
//                    }
//                });
        return null;
    }

}
