package io.perfeccionista.framework.pagefactory.elements.methods;

import io.appium.java_client.MobileElement;
import io.perfeccionista.framework.pagefactory.elements.MobileChildElement;
import io.perfeccionista.framework.pagefactory.elements.MobileUnorderedList;
import io.perfeccionista.framework.pagefactory.filter.SingleResult;
import io.perfeccionista.framework.pagefactory.filter.AppiumBlockFilter;
import io.perfeccionista.framework.pagefactory.operations.OperationResult;

import java.util.HashMap;

public class AppiumSwipeToBlockElement implements MobileElementMethodImplementation<Void> {

    @Override
    public OperationResult<Void> execute(MobileChildElement element, Object... args) {
        MobileUnorderedList unorderedList = (MobileUnorderedList) element;
        AppiumBlockFilter filter = (AppiumBlockFilter) args[0];
        return OperationResult.of(() -> {
            SingleResult<MobileElement> result = filter.singleResult(unorderedList);

            HashMap<String, String> scrollParams = new HashMap<>();
            scrollParams.put("direction", "down");
            scrollParams.put("element", result.getItem().getId());
            element.getDriverInstance().getDriver().executeScript("mobile: swipe", scrollParams);
        });
    }

}