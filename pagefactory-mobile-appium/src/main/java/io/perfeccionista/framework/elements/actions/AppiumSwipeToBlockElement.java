package io.perfeccionista.framework.elements.actions;

import io.appium.java_client.MobileElement;
import io.perfeccionista.framework.pagefactory.elements.MobileChildElement;
import io.perfeccionista.framework.pagefactory.elements.MobileUnorderedList;
import io.perfeccionista.framework.pagefactory.elements.actions.MobileElementActionImplementation;
import io.perfeccionista.framework.pagefactory.filter.SingleResult;

import java.util.HashMap;

public class AppiumSwipeToBlockElement implements MobileElementActionImplementation<Void> {

    @Override
    public Void execute(MobileChildElement element, Object... args) {
//        MobileUnorderedList unorderedList = (MobileUnorderedList) element;
//        AppiumBlockFilter filter = (AppiumBlockFilter) args[0];
//
//            SingleResult<MobileElement> result = filter.singleResult(unorderedList);
//
//            HashMap<String, String> scrollParams = new HashMap<>();
//            scrollParams.put("direction", "down");
//            scrollParams.put("element", result.get().getId());
//            element.getDriverInstance().getDriver().executeScript("mobile: swipe", scrollParams);
            return null;
    }

}