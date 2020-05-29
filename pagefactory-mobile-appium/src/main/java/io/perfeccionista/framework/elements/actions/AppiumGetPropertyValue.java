package io.perfeccionista.framework.elements.actions;

import io.perfeccionista.framework.pagefactory.elements.MobileChildElement;
import io.perfeccionista.framework.pagefactory.elements.actions.MobileElementActionImplementation;
import io.perfeccionista.framework.pagefactory.elements.properties.MobileElementPropertyHolder;
import org.junit.platform.commons.util.ReflectionUtils;

public class AppiumGetPropertyValue implements MobileElementActionImplementation<String> {

    @Override
    public String execute(MobileChildElement element, Object... args) {
//        MobileElementPropertyHolder elementPropertyHolder = (MobileElementPropertyHolder) args[0];
//
//            ElementPropertyExtractor<MobileChildElement> elementPropertyExtractor = ReflectionUtils.newInstance(elementPropertyHolder.getPropertyExtractor());
//            return elementPropertyExtractor.extract(element, elementPropertyHolder.getLocatorHolder());
        return null;
    }

}
