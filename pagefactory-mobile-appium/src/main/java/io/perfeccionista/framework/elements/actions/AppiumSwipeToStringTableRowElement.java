package io.perfeccionista.framework.elements.actions;

import io.perfeccionista.framework.pagefactory.elements.MobileChildElement;
import io.perfeccionista.framework.pagefactory.elements.actions.MobileElementActionImplementation;

public class AppiumSwipeToStringTableRowElement implements MobileElementActionImplementation<Void> {

//    @Override
    public Void execute(MobileChildElement element, Object... args) {
//        MobileSimpleTable unorderedList = (MobileSimpleTable) element;
//        AppiumStringTableRowFilter filter = (AppiumStringTableRowFilter) args[0];
//        element.getDriverInstance().getExceptionMapper(AppiumExceptionMapper.class).map(() -> {
//            SingleResult<MobileElement> result = filter.singleResult(unorderedList);
//            HashMap<String, String> scrollParams = new HashMap<>();
//            scrollParams.put("direction", "down");
//            scrollParams.put("element", result.get().getId());
//            element.getDriverInstance().getDriver().executeScript("mobile: swipe", scrollParams);
//        });
        return null;
    }

}
