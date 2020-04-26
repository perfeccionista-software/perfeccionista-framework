package io.perfeccionista.framework.pagefactory.elements.mapping;

import io.perfeccionista.framework.pagefactory.elements.WebParentElement;
import org.junit.platform.commons.util.ReflectionUtils;

public class WebMappingUtils {

    public static <T extends WebParentElement> T blockMock(Class<T> blockClass) {
        return ReflectionUtils.newInstance(blockClass);
    }

}
