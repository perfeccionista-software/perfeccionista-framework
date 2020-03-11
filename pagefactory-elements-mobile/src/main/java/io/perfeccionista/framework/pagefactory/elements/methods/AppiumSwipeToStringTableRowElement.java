package io.perfeccionista.framework.pagefactory.elements.methods;

import io.appium.java_client.MobileElement;
import io.perfeccionista.framework.exceptions.mapper.AppiumExceptionMapper;
import io.perfeccionista.framework.pagefactory.elements.MobileChildElement;
import io.perfeccionista.framework.pagefactory.elements.MobileSimpleTable;
import io.perfeccionista.framework.pagefactory.filter.SingleResult;
import io.perfeccionista.framework.pagefactory.filter.AppiumStringTableRowFilter;
import io.perfeccionista.framework.pagefactory.operations.OperationResult;

import java.util.HashMap;

public class AppiumSwipeToStringTableRowElement implements MobileElementMethodImplementation<Void> {

    @Override
    public OperationResult<Void> execute(MobileChildElement element, Object... args) {
        MobileSimpleTable unorderedList = (MobileSimpleTable) element;
        AppiumStringTableRowFilter filter = (AppiumStringTableRowFilter) args[0];
        return OperationResult.of(() -> element.getDriverInstance().getExceptionMapper(AppiumExceptionMapper.class).map(() -> {
            SingleResult<MobileElement> result = filter.singleResult(unorderedList);
            HashMap<String, String> scrollParams = new HashMap<>();
            scrollParams.put("direction", "down");
            scrollParams.put("element", result.getItem().getId());
            element.getDriverInstance().getDriver().executeScript("mobile: swipe", scrollParams);
        }));
    }

}
