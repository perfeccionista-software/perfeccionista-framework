package io.perfeccionista.framework.pagefactory.elements.methods;

import io.perfeccionista.framework.pagefactory.elements.MobileChildElement;
import io.perfeccionista.framework.pagefactory.elements.properties.ElementPropertyExtractor;
import io.perfeccionista.framework.pagefactory.elements.properties.MobileElementPropertyHolder;
import io.perfeccionista.framework.pagefactory.operations.OperationResult;
import org.junit.platform.commons.util.ReflectionUtils;

public class AppiumGetPropertyValue implements MobileElementMethodImplementation<String> {

    @Override
    public OperationResult<String> execute(MobileChildElement element, Object... args) {
        MobileElementPropertyHolder elementPropertyHolder = (MobileElementPropertyHolder) args[0];
        return OperationResult.of(() -> {
            ElementPropertyExtractor<MobileChildElement> elementPropertyExtractor = ReflectionUtils.newInstance(elementPropertyHolder.getPropertyExtractor());
            return elementPropertyExtractor.extract(element, elementPropertyHolder.getLocatorHolder());
        });
    }

}
