package io.perfeccionista.framework.pagefactory.elements.methods;

import io.appium.java_client.MobileElement;
import io.perfeccionista.framework.exceptions.mapper.AppiumExceptionMapper;
import io.perfeccionista.framework.pagefactory.elements.MobileChildElement;
import io.perfeccionista.framework.pagefactory.elements.MobileSimpleUnorderedList;
import io.perfeccionista.framework.pagefactory.filter.SingleResult;
import io.perfeccionista.framework.pagefactory.filter.AppiumStringBlockFilter;
import io.perfeccionista.framework.pagefactory.operations.OperationResult;

import java.util.HashMap;

public class AppiumSwipeToStringBlockElement implements MobileElementMethodImplementation<Void> {

    @Override
    public Void execute(MobileChildElement element, Object... args) {
        MobileSimpleUnorderedList unorderedList = (MobileSimpleUnorderedList) element;
        AppiumStringBlockFilter filter = (AppiumStringBlockFilter) args[0];
        element.getDriverInstance().getExceptionMapper(AppiumExceptionMapper.class).map(() -> {
            SingleResult<MobileElement> result = filter.singleResult(unorderedList);
            HashMap<String, String> scrollParams = new HashMap<>();
            scrollParams.put("direction", "down");
            scrollParams.put("element", result.get().getId());
            element.getDriverInstance().getDriver().executeScript("mobile: swipe", scrollParams);
        });
        return null;
    }

}
