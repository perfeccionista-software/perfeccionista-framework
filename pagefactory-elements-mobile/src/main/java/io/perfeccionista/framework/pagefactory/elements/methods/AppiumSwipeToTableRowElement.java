package io.perfeccionista.framework.pagefactory.elements.methods;

import io.appium.java_client.MobileElement;
import io.perfeccionista.framework.pagefactory.elements.MobileChildElement;
import io.perfeccionista.framework.pagefactory.elements.MobileTable;
import io.perfeccionista.framework.pagefactory.filter.SingleResult;
import io.perfeccionista.framework.pagefactory.filter.AppiumTableRowFilter;
import io.perfeccionista.framework.pagefactory.operations.OperationResult;

import java.util.HashMap;

public class AppiumSwipeToTableRowElement implements MobileElementMethodImplementation<Void> {

    @Override
    public OperationResult<Void> execute(MobileChildElement element, Object... args) {
        MobileTable table = (MobileTable) element;
        AppiumTableRowFilter filter = (AppiumTableRowFilter) args[0];
        return OperationResult.of(() -> {
            SingleResult<MobileElement> result = filter.singleResult(table);

            HashMap<String, String> scrollParams = new HashMap<>();
            scrollParams.put("direction", "down");
            scrollParams.put("element", result.getItem().getId());
            element.getDriverInstance().getDriver().executeScript("mobile: swipe", scrollParams);
        });
    }

}
