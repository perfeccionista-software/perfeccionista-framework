package io.perfeccionista.framework.elements.actions;

import io.appium.java_client.MobileElement;
import io.perfeccionista.framework.exceptions.mapper.AppiumExceptionMapper;
import io.perfeccionista.framework.pagefactory.elements.MobileChildElement;
import io.perfeccionista.framework.pagefactory.elements.MobileDropDownList;
import io.perfeccionista.framework.pagefactory.elements.actions.MobileElementActionImplementation;
import org.openqa.selenium.interactions.touch.TouchActions;

public class AppiumOpen implements MobileElementActionImplementation<Void> {

    public Void execute(MobileChildElement element, Object... args) {
//        MobileDropDownList dropDownList = (MobileDropDownList) element;
//        element.getDriverInstance().getExceptionMapper(AppiumExceptionMapper.class).map(() -> {
//            boolean isOpenResult = dropDownList.isOpen();
//            if (isOpenResult) {
//                return;
//            }
//            MobileElement tapElement = element.findElement(element.getLocatorChainTo(OPEN)).get();
//            TouchActions action = new TouchActions(element.getDriverInstance().getDriver()).singleTap(tapElement);
//            action.perform();
//        });
        return null;
    }

}

