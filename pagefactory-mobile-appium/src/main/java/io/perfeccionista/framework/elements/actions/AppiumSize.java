package io.perfeccionista.framework.elements.actions;

import io.perfeccionista.framework.pagefactory.elements.actions.MobileElementActionImplementation;
import io.perfeccionista.framework.pagefactory.elements.MobileChildElement;

public class AppiumSize implements MobileElementActionImplementation<Integer> {

    @Override
    public Integer execute(MobileChildElement element, Object... args) {
//        String component = (String) args[0];
//
//            LocatorChain locatorChain = element.getLocatorChainTo(component);
//            return element.findElements(locatorChain).get().size();
        return null;

    }

}
