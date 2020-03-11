package io.perfeccionista.framework.pagefactory.elements.methods;

import io.appium.java_client.MobileElement;
import io.perfeccionista.framework.exceptions.mapper.AppiumExceptionMapper;
import io.perfeccionista.framework.pagefactory.elements.MobileChildElement;
import io.perfeccionista.framework.pagefactory.elements.MobileDropDownList;
import io.perfeccionista.framework.pagefactory.operations.OperationResult;
import org.openqa.selenium.interactions.touch.TouchActions;

import static io.perfeccionista.framework.pagefactory.elements.locators.Components.CLOSE;

public class AppiumClose implements MobileElementMethodImplementation<Void> {

    @Override
    public OperationResult<Void> execute(MobileChildElement element, Object... args) {
        MobileDropDownList dropDownList = (MobileDropDownList) element;
        return OperationResult.of(() -> element.getDriverInstance().getExceptionMapper(AppiumExceptionMapper.class)
                .map(() -> {
                    boolean isOpenResult = dropDownList.isOpen().getResultOrThrow();
                    if (isOpenResult) {
                        MobileElement tapElement = element.findElement(element.getLocatorChainTo(CLOSE)).getItem();
                        TouchActions action = new TouchActions(element.getDriverInstance().getDriver()).singleTap(tapElement);
                        action.perform();
                    }
                }));
    }

}
